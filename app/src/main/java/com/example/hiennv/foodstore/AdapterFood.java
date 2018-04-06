package com.example.hiennv.foodstore;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hiennv.foodstore.model.Food;
import com.example.hiennv.foodstore.utils.AsyncTaskLoadImage;

import java.util.ArrayList;

/**
 * Created by hiennv on 01/04/2018.
 */

public class AdapterFood extends BaseAdapter {
    private Activity context;

    private ArrayList<Food> listFood;
    private ImageView imgFood;
    private TextView tvName, tvPrice;
    private Button btnAddToCart;

    public AdapterFood(Activity context, ArrayList<Food> listFood) {
        this.context = context;
        this.listFood = listFood;
    }

    @Override
    public int getCount() {
        return listFood.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_row, null);

        imgFood = row.findViewById(R.id.imgFood);
        tvName = row.findViewById(R.id.tvName);
        tvPrice = row.findViewById(R.id.tvPrice);


        System.out.println(listFood.size());
        Food food1 = listFood.get(i);
        String url = food1.getImageUrl();
        tvName.setText(food1.getProductName());
        tvPrice.setText(food1.getPrice()+"");

//        AsyncTaskLoadImage asyncTaskLoadImage = new AsyncTaskLoadImage(imgAvatar);
//        asyncTaskLoadImage.execute(url);

        new AsyncTaskLoadImage(imgFood).execute(url);

        return row;
    }
}
