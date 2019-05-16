package com.shakil.recyclerviewandjson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyWeather>weathers;
    private Context context;

    public MyAdapter(List<MyWeather> weathers, Context context) {
        this.weathers = weathers;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {

        //problem is here

        viewHolder.textViewCity.setText(weathers.getClass().getName());
        viewHolder.textViewCod.setText(weathers.get(i).getCod());
        viewHolder.textViewMessage.setText(weathers.get());
        viewHolder.textViewCnt.setText(weathers.get(i).getCnt());
    }

    @Override
    public int getItemCount() {
        return weathers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewCity;
        public TextView textViewCod;
        public TextView textViewMessage;
        public TextView textViewCnt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity=(TextView) itemView.findViewById(R.id.cityNames);
            textViewCod=(TextView) itemView.findViewById(R.id.cod);
            textViewMessage=(TextView) itemView.findViewById(R.id.messages);
            textViewCnt=(TextView) itemView.findViewById(R.id.cnt);
        }
    }


}
