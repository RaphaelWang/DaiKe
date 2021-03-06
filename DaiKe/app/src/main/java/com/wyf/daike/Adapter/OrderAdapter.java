package com.wyf.daike.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wyf.daike.Bean.DaiKeOrder;
import com.wyf.daike.R;

import java.util.List;

/**
 * Created by wyf on 2016/8/22.
 */
public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<DaiKeOrder> orderList ;
    private Context mContext;

    public OrderAdapter(List<DaiKeOrder> orderList, Context context) {
        this.orderList = orderList;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.widget_order_card,null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        DaiKeOrder bean = orderList.get(position);

        ((MyViewHolder)holder).textTitle.setText(bean.getoTitle());
        if(bean.getoOrderState()==0)
        {
            ((MyViewHolder)holder).btnStatus.setText("已发布");

        }else if(bean.getoOrderState()==1)
        {
            ((MyViewHolder)holder).btnStatus.setText("代课中");
        }
        else if(bean.getoOrderState()==2)
        { ((MyViewHolder)holder).btnStatus.setText("代课完成");
        }
    }

    @Override
    public int getItemCount() {
        if(orderList==null)
        {
            return 0;
        }
        return orderList.size();

    }

    class  MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private Button btnStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
                textTitle = (TextView) itemView.findViewById(R.id.textTitle);
                btnStatus = (Button) itemView.findViewById(R.id.btnState);
        }
    }
}
