package com.example.hiennv.foodstore.controller;

import android.os.AsyncTask;

import com.example.hiennv.foodstore.model.Food;

import java.util.List;

/**
 * Created by hiennv on 04/04/2018.
 */

public class FoodRequest extends AsyncTask<Void, Food, List<Food>> {
    private static final String URL = "http://192.168.43.11:8080/foodws/foodws?WSDL";

    private static final String NAMESPACE = "http://ws/";
    private static final String SOAP_ACTION = "http://ws/foodws/";
    private static final String METHOD_NAME = "getAllFood";
    private static final String PARAMETER_NAME = "n";

    @Override
    protected List<Food> doInBackground(Void... voids) {
        return null;
    }

}
