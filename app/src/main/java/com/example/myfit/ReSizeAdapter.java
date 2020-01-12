package com.example.myfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReSizeAdapter extends RecyclerView.Adapter<ReSizeAdapter.SizeView> {
    private Context mContext;
    //adapter에 들어갈 list
    private ArrayList<String> sizeList;

    public ReSizeAdapter(Context context, ArrayList<String> list) {
        mContext=context;
        sizeList=list;
    }

    @NonNull
    @Override
    public SizeView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layoutinflater을 이용해 row_size를 inflate
        //return 인자는 viewholder
        View view= LayoutInflater.from(mContext).inflate(R.layout.row_size,parent,false);
        return new SizeView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeView holder, int position) {
        //item을 하나씩 보여주는 bind 되는 함수
        final String size=sizeList.get(position);
        holder.sizeTitle.setText(size);
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }

    //subview를 setting 해주는 viewholder
    public class SizeView extends RecyclerView.ViewHolder{
        TextView sizeTitle;

        public SizeView(@NonNull View itemView) {
            super(itemView);
            sizeTitle=(TextView)itemView.findViewById(R.id.size_title);
        }
    }
}
