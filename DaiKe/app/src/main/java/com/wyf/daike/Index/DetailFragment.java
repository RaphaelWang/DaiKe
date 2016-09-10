package com.wyf.daike.Index;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wyf.daike.Bean.DaiKeOrder;
import com.wyf.daike.R;

/**
 * A simple {@link Fragment} subclass.
 */
@Deprecated
public class DetailFragment extends Fragment implements View.OnClickListener,IndexContract.LoadDetail{

    private DaiKeOrder cardData;
    private TextView textTitle;
    private TextView textSubject;
    private TextView textClassroom;
    private TextView textView4;
    private TextView textPrice;
    private TextView textTakeClassTime;
    private TextView textFaBuTime;
    private TextView textView5;
    private TextView textTel;
    private Button btnSubmit;
    private ImageView imageUser;
    private DetailPresenter detailPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        String id = getArguments().getString("id");
        load(id);

        initView(view);
        return view;
    }







    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSubmit:

                break;
        }
    }

    @Override
    public void load(String id) {
        detailPresenter = new DetailPresenter(this);

        detailPresenter.loadDetail(id);
    }

    public void onSuccess(DaiKeOrder cardData) {
        if (cardData != null) {
            textSubject.setText(cardData.getoSubject());
//            textTitle.setText(cardData.getCardTitle());
//            textTel.setText(cardData.getTelephone());
////            textPrice.setText(cardData.getCardMoney());
//            textFaBuTime.setText(cardData.getCreatedAt());
//            textTakeClassTime.setText(cardData.getTime());
//            textClassroom.setText(cardData.getCardClassroom());

        }
    }
    private void initView(View view) {
        textTitle = (TextView) view.findViewById(R.id.textTitle);
        textSubject = (TextView) view.findViewById(R.id.textSubject);
        textClassroom = (TextView) view.findViewById(R.id.textClassroom);
        textPrice = (TextView) view.findViewById(R.id.textPrice);
        textTakeClassTime = (TextView) view.findViewById(R.id.textTakeClassTime);
        textFaBuTime = (TextView) view.findViewById(R.id.textFaBuTime);
        textTel = (TextView) view.findViewById(R.id.textTel);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        imageUser = (ImageView) view.findViewById(R.id.imageUser);
        btnSubmit.setOnClickListener(this);
    }
}
