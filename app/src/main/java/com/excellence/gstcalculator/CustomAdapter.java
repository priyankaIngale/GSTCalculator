package com.excellence.gstcalculator;

/**
 * Created by Intl on 8/2/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<DataList> objects;


    TextView et_potential;
    public CustomAdapter(Context context, ArrayList<DataList> products) {
        ctx = context;
        objects = products;
               lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.data_list_row, parent, false);
        }

        DataList p = getProduct(position);

        TextView tv_basicamt=((TextView) view.findViewById(R.id.tv_basicamt));
        TextView tv_tax=((TextView) view.findViewById(R.id.tv_tax));
        TextView tv_per=((TextView) view.findViewById(R.id.tv_per));
        TextView tv_price=((TextView) view.findViewById(R.id.tv_price));

        tv_price.setText(""+p.getSelling_amt());
        tv_per.setText(""+p.getPercentage());
        tv_tax.setText(""+p.getTax());
        tv_basicamt.setText(""+p.getBasicPrice());

        return view;
    }

    DataList getProduct(int position) {
        return ((DataList) getItem(position));
    }

  
   
  
}
