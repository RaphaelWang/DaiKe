package com.wyf.daike.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyf.daike.Bean.IndexCard;
import com.wyf.daike.R;
import com.wyf.daike.Util.CircleImageView;
import com.wyf.daike.global.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
   List<IndexCard> cardData;

    public IndexAdapter(Context context,List<IndexCard> cardData) {
        mContext = context;
        this.cardData = cardData;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        IndexCard  indexCard = cardData.get(position);
        if(indexCard==null)
        {
            Log.d("wyf", "onBindViewHolder: "+"数据为空");
            return;
        }
        Log.d("wyf", "onBindViewHolder: "+"数据不为空");
        ((MyViewHolder)holder).cardTitle.setText(indexCard.getCardTitle());
        ((MyViewHolder)holder).cardClassroom.setText(indexCard.getCardClassroom());
        Glide.with(MyApplication.getContext()).load(indexCard.getCardImageUrl()).into(((MyViewHolder)holder).cardImage);
        Glide.with(mContext).load(indexCard.getCardImageUrl()).placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail).into(((MyViewHolder)holder).cardImage);
        ((MyViewHolder)holder).cardMoney.setText(indexCard.getCardMoney());
        ((MyViewHolder)holder).cardSubject.setText(indexCard.getCardSubect());
        ((MyViewHolder)holder).cardTime.setText(indexCard.getTime());


    }

    @Override
    public int getItemCount() {
        return cardData.size();
    }


    public static class MyViewHolder  extends RecyclerView.ViewHolder{
        public View rootView;
        public CircleImageView cardImage;
        public TextView cardTitle;
        public TextView cardSubject;
        public TextView cardClassroom;
        public TextView cardTime;
        public TextView cardMoney;

        public MyViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.cardImage = (CircleImageView) rootView.findViewById(R.id.cardImage);
            this.cardTitle = (TextView) rootView.findViewById(R.id.cardTitle);
            this.cardSubject = (TextView) rootView.findViewById(R.id.cardSubject);
            this.cardClassroom = (TextView) rootView.findViewById(R.id.cardClassroom);
            this.cardTime = (TextView) rootView.findViewById(R.id.cardTime);
            this.cardMoney = (TextView) rootView.findViewById(R.id.cardMoney);
        }

    }
}
