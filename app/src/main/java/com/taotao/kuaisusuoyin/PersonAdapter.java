package com.taotao.kuaisusuoyin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fanxintao on 2017/8/17.
 */

public class PersonAdapter extends BaseAdapter {

    private List<Person> list;
    private LayoutInflater inflater;

    public PersonAdapter(Context context,List<Person> list) {
        inflater=LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item,null);
            holder.tv_name=convertView.findViewById(R.id.tv_name);
            holder.tv_world=convertView.findViewById(R.id.tv_word);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        String word=list.get(position).getHeaderWord();
        holder.tv_world.setText(word);
        holder.tv_name.setText(list.get(position).getName());
        //将相同字母的头合并在一起
        if (position==0){
            //第一个一定是显示的
            holder.tv_world.setVisibility(View.VISIBLE);
        }else {
            //判断后面一个字母是否与第一个字母相同，相同则隐藏
            String headerWord=list.get(position-1).getHeaderWord();
            if (word.equals(headerWord)){
                holder.tv_world.setVisibility(View.INVISIBLE);
            }else {
                holder.tv_world.setVisibility(View.VISIBLE);
            }
        }

        return convertView;
    }


    private class ViewHolder{
        private TextView tv_world;
        private TextView tv_name;
    }

}
