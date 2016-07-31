package com.wyf.daike.AddDaiKe;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wyf.daike.R;

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
        editTitle = (EditText) rootView.findViewById(R.id.editTitle);
        editSubject = (EditText) rootView.findViewById(R.id.editSubject);
        editPrice = (EditText) rootView.findViewById(R.id.editPrice);
        editClassroom = (EditText) rootView.findViewById(R.id.editClassroom);


        this.button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(this);




    }

    private void submit() {
//        if(TextUtils.isEmpty(editTitle.getText().toString()))
//        {
//            return;
//        }
//        if(TextUtils.isEmpty(editSubject.getText().toString()))
//        {
//            return;
//        }
//        if(TextUtils.isEmpty(editPrice.getText().toString()))
//        {
//            return;
//        }
//        if(TextUtils.isEmpty(editClassroom.getText().toString()))
//        {
//            return;
//        }


//        presenter.sendDaiKeInfo(su,editClassroom.getText().toString(),
//                editTitle.getText().toString(),editPrice.getText().toString());
        String title =editTitle.getText().toString();
        String subject = editSubject.getText().toString();
        String money = editPrice.getText().toString();;
        String classroom = editClassroom.getText().toString();
        presenter.sendDaiKeInfo(subject,classroom,title,money);


    }

    @Override
    public void onClick(View v) {

        submit();

    }
}
