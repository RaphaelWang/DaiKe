package com.wyf.daike.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wyf.daike.AddDaiKe.AddDaiKeContract;
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

    private  Context mContext;
    private  List<IndexCard> cardData;
    private final int  TYPE_ITEM=0;
    private final int  TYPE_FOOTER=1;
    private final int  TYPE_NO_MORE=2;
    private static boolean showFooter=true;
    private OnItemClickListener mOnItemClickListener;


    public  boolean isShowFooter()
    {
       return this.showFooter;
    }
    public void setShowFooter(boolean showFooter)
    {
        this.showFooter = showFooter;
    }

    @Override
    public int getItemViewType(int position) {

        if(position+1==getItemCount()&&showFooter)
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    public IndexAdapter(Context context, List<IndexCard> cardData) {
        mContext = context;
        this.cardData = cardData;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_ITEM)
        {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return  viewHolder;
        }

        else
         {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewloading, parent, false);
            return  new ItemFooter(view);
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyViewHolder)
        {
            IndexCard  indexCard = cardData.get(position);
            if(indexCard==null)
            {
                return;
            }

            ((MyViewHolder)holder).cardTitle.setText(indexCard.getCardTitle());
            ((MyViewHolder)holder).cardClassroom.setText(indexCard.getCardClassroom());

            Glide.with(mContext).load(indexCard.getCardImageUrl()).placeholder(R.drawable.ic_image_loading)
                    .error(R.mipmap.ic_launcher).into(((MyViewHolder)holder).cardImage);
            ((MyViewHolder)holder).cardMoney.setText(indexCard.getCardMoney());
            ((MyViewHolder)holder).cardSubject.setText(indexCard.getCardSubect());
            ((MyViewHolder)holder).cardTime.setText(indexCard.getCreatedAt());

        }



    }

    public  IndexCard getItem(int positon)
    {
        return  cardData==null?null:cardData.get(positon);
    }

    @Override
    public int getItemCount() {

        return showFooter?cardData.size()+1:cardData.size();
    }


    public void   addItemOnClickListener(OnItemClickListener onItemClickListener)
    {
       mOnItemClickListener =  onItemClickListener;
    }



    public  interface OnItemClickListener
    {
        void itemClick(View view,int postiton);
    }


    public  class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
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
             rootView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(mOnItemClickListener!=null)
            {
                mOnItemClickListener.itemClick(v,this.getPosition());
            }
        }
    }

    public  static  class  ItemFooter extends RecyclerView.ViewHolder {
        public ItemFooter(View itemView) {
            super(itemView);
        }

    }
}
