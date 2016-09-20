package com.wyf.daike.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wyf.daike.Bean.DaiKeOrder;
import com.wyf.daike.R;
import com.wyf.daike.Util.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/24.
 */
public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private  Context mContext;
    private  List<DaiKeOrder> cardData;
    private final int  TYPE_ITEM=0;
    private final int  TYPE_FOOTER=1;
    private final int  TYPE_NO_MORE=2;
    private static boolean showFooter=false;
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

        if(position+1==getItemCount())
            return TYPE_FOOTER;
        return TYPE_ITEM;
    }

    public IndexAdapter(Context context, List<DaiKeOrder> cardData) {
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
            DaiKeOrder  indexCard = cardData.get(position);
            if(indexCard==null)
            {
                return;
            }

            ((MyViewHolder)holder).cardTitle.setText(indexCard.getoTitle());
            ((MyViewHolder)holder).cardClassroom.setText(indexCard.getoClassroom());


                Glide.with(mContext)
                        .load(indexCard.getUserImgUrl())
                        .placeholder(R.drawable.ic_image_loading)
                        .error(R.drawable.ly)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(((MyViewHolder)holder).cardImage);


            ((MyViewHolder)holder).cardMoney.setText(indexCard.getoPrice());
            ((MyViewHolder)holder).cardSubject.setText(indexCard.getoSubject());
            ((MyViewHolder)holder).cardTime.setText(indexCard.getoSchoolTime());
        }
    }

    public  DaiKeOrder getItem(int positon)
    {
        return  cardData==null?null:cardData.get(positon);
    }

    @Override
    public int getItemCount() {

        int begin = showFooter?1:0;

        if(cardData==null)
        {
            return begin;
        }
        return cardData.size()+begin;
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
