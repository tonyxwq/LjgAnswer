package com.ljgandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljganswer.R;

import java.util.List;

/**
 * Author:XWQ
 * Time   2019/1/7
 * Descrition: this is MainAdapter
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VH>
{
    private static final int TYPE_1 = 1;//布局一
    private static final int TYPE_2 = 2;//布局二

    public static class VH extends RecyclerView.ViewHolder
    {
        public final TextView title;

        public VH(View v)
        {
            super(v);
            title = v.findViewById(R.id.tv_main_id);
        }
    }

    private List<String> mDatas;

    public MainAdapter(List<String> data)
    {
        this.mDatas = data;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position % 2 != 0)
        {
            return TYPE_1;
        }
        return TYPE_2;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=null;
        switch (viewType)
        {
            case TYPE_1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layout_item_one, parent, false);
                break;
            case TYPE_2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_layout_item_two, parent, false);
                break;
        }
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position)
    {
        holder.title.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }
}
