package com.wyf.daike.AddDaiKe;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wyf.daike.R;
import com.wyf.daike.Util.SharedPresferencesUtil;
import com.wyf.daike.main.MainActivity;

import org.apache.http.auth.NTCredentials;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDaiKeFragment extends Fragment implements AddDaiKeContract.View, View.OnClickListener {

    private EditText editTitle;
    private EditText editSubject;
    private EditText editPrice;
    private EditText editClassroom;
    private Button button;
    private boolean sendState;
    private Context mContext;
    private  MainActivity parentActivity;
    private AddDaiKeContract.Presenter presenter;
    private EditText editTakeData,editTakeTime;
    private String temp;
    private Toolbar toolbar;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        new AddDaiKePresenter(this);
    }

    public static AddDaiKeFragment newInstance()
    {
        return new AddDaiKeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_add_dai_ke, container, false);

        initView(rootView);
        initEvent();
        initActionBar();

        return rootView;
    }

    private void initActionBar() {

    }

    private void initEvent() {
        // 获取日历对象
        final Calendar calendar = Calendar.getInstance();
        // 获取当前对应的年、月、日的信息
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour =calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);
        editTakeData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {


                    DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            editTakeData.setText("");
                            editTakeData.setText(""+(monthOfYear+1)+"月"+dayOfMonth+"日");
                        }
                    },year,month,day);
                    datePickerDialog.show();

                }
            }
        });

        editTakeTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {

                    TimePickerDialog timePickerDialog = new
                            TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            editTakeTime.setText("");
                          editTakeTime.setText(hourOfDay+"："+minute);
                        }
                    },hour,minute,false);
                    timePickerDialog.show();
                }
            }
        });

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void setSendState(boolean sendStaete) {
        new AddDaiKePresenter(new AddDaiKeFragment());
        if(sendState)
        {
            Toast.makeText(mContext, "成功", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public void setPresenter(AddDaiKeContract.Presenter persenter) {
        this.presenter = persenter;
    }

    private void initView(View rootView) {
        editTitle = (EditText) rootView.findViewById(R.id.editTitle);
        editSubject = (EditText) rootView.findViewById(R.id.editSubject);
        editPrice = (EditText) rootView.findViewById(R.id.editPrice);
        editClassroom = (EditText) rootView.findViewById(R.id.editClassroom);
        editTakeTime = (EditText) rootView.findViewById(R.id.editTakeClassTime);
        editTakeData = (EditText) rootView.findViewById(R.id.editTakeClassData);
        this.button = (Button) rootView.findViewById(R.id.btnSubmit);
        button.setOnClickListener(this);

        //Toolbar
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbarOther);
        toolbar.setTitle("发布代课");
        toolbar.setTitleTextColor(Color.WHITE);

    }

    private void submit(View view) {
        if(TextUtils.isEmpty(editTitle.getText().toString()))
        {
            Toast.makeText(mContext, "无标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(editSubject.getText().toString()))
        {
            Toast.makeText(mContext, "无课程", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(editPrice.getText().toString()))
        {
            Toast.makeText(mContext, "无价格", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(editClassroom.getText().toString()))
        {
            Toast.makeText(mContext, "无地点", Toast.LENGTH_SHORT).show();
            return;
        }

        String title =editTitle.getText().toString();
        String subject = editSubject.getText().toString();
        String money = editPrice.getText().toString();;
        String classroom = editClassroom.getText().toString();

        String img=(SharedPresferencesUtil.getData(mContext,"userImgUrl"));
        presenter.sendDaiKeInfo(subject,classroom,title,money,
                editTakeData.getText().toString()+editTakeTime.getText().toString(),
                BmobUser.getCurrentUser().getUsername(),img,0);

       Snackbar.make(view,"发布成功",Snackbar.LENGTH_SHORT).show();

    }



    @Override
    public void onClick(View v) {
        submit(v);
    }
}
