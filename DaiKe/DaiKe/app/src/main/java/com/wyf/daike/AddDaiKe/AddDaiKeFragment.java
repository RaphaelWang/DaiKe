package com.wyf.daike.AddDaiKe;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wyf.daike.R;
import com.wyf.daike.main.MainActivity;

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
    private AlertDialog dialogTimePicker,dialogDataPicker;
    private String temp;



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

        getActivity().setTitle("发布代课信息");
        View rootView = inflater.inflate(R.layout.fragment_add_dai_ke, container, false);

        initView(rootView);
        initEvent();

        setHasOptionsMenu(true);
        setReenterTransition(true);
        return rootView;
    }

    private void initEvent() {
        editTakeData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {
                    if(dialogDataPicker==null)
                    {
                        View view = LayoutInflater.from(mContext).inflate(R.layout.widget_data_picker,null);
                        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.widgetDataPicker);
                        dialogDataPicker = new AlertDialog.Builder(mContext)
                                .setView(R.layout.widget_data_picker)
                                .setTitle("请选择日期")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        temp = ""+datePicker.getMonth()+datePicker.getDayOfMonth();
                                        Log.d("wyf", "onClick: "+temp);
                                        editTakeData.setText(temp);
                                        dialogDataPicker.dismiss();
                                    }
                                })
                                .create();
                    }
                    if(!dialogDataPicker.isShowing())
                        dialogDataPicker.show();

                }
            }
        });



        editTakeTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                {
                    if(editTakeTime==null)
                    {
                        View view = LayoutInflater.from(mContext).inflate(R.layout.widget_time_picker,null);
                        final TimePicker timePicker = (TimePicker) view.findViewById(R.id.widgetTimePicker);
                        dialogDataPicker = new AlertDialog.Builder(mContext)
                                .setView(R.layout.widget_data_picker)
                                .setTitle("请选择日期")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                })
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       // temp =""+timePicker.getHour()+timePicker.getMinute();
                                        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                                            @Override
                                            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                                                temp = ""+hourOfDay+"  "+minute;
                                                Log.d("wyf", "onTimeChanged: "+temp);
                                                editTakeTime.setText(temp);
                                                dialogTimePicker.dismiss();

                                            }
                                        });
                                        Log.d("wyf", "onClick: "+temp);
                                    }
                                })
                                .create();
                    }
                    if(!dialogTimePicker.isShowing())
                        dialogTimePicker.show();


                }
            }
        });

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void setSendState(boolean sendStaete) {
        this.sendState = sendState;
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
        parentActivity = (MainActivity ) getActivity();
        parentActivity.setFloatingActionButton(false);




        editTitle = (EditText) rootView.findViewById(R.id.editTitle);
        editSubject = (EditText) rootView.findViewById(R.id.editSubject);
        editPrice = (EditText) rootView.findViewById(R.id.editPrice);
        editClassroom = (EditText) rootView.findViewById(R.id.editClassroom);
        editTakeTime = (EditText) rootView.findViewById(R.id.editTakeClassTime);
        editTakeData = (EditText) rootView.findViewById(R.id.editTakeClassData);
        this.button = (Button) rootView.findViewById(R.id.btnSubmit);
        button.setOnClickListener(this);




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
        presenter.sendDaiKeInfo(subject,classroom,title,money,0);

        Snackbar.make(view,"发布成功",Snackbar.LENGTH_SHORT).show();

    }






    @Override
    public void onDetach() {
        super.onDetach();
       // parentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrameLayout,new DaiKeListFragment()).commit();
        Log.d("TAG", "onDetach: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.d("TAG", "onDestroyView: ");
    }

    @Override
    public void onClick(View v) {

        submit(v);

    }
}
