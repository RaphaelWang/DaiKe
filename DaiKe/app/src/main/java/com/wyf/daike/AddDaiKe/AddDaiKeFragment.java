package com.wyf.daike.AddDaiKe;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.wyf.daike.Index.DaiKeListFragment;
import com.wyf.daike.R;
import com.wyf.daike.main.view.MainActivity;

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



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

        new AddDaiKePresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().setTitle("发布代课信息");
        View rootView = inflater.inflate(R.layout.fragment_add_dai_ke, container, false);

        initView(rootView);

        setHasOptionsMenu(true);
        setReenterTransition(true);
        return rootView;
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
        presenter.sendDaiKeInfo(subject,classroom,title,money);
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
