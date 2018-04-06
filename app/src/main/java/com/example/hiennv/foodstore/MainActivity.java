package com.example.hiennv.foodstore;

import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.hiennv.foodstore.model.Food;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AdapterFood adapterFood;
    private ListView listView;
    private ArrayList<Food> listFood;

    private static final String URL = "http://192.168.43.11:8080/foodws/foodws?WSDL";

    private static final String NAMESPACE = "http://ws/";
    private static final String SOAP_ACTION = "http://ws/foodws/getAllFood";
    private static final String METHOD_NAME = "getAllFood";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CallWebService().execute();
        init();
        loadAllFood();


    }

    private void init() {
        new CallWebService().execute();
        listView = findViewById(R.id.listView);
        listFood = new ArrayList<>();
        Food food = new Food("hhh", 123, 12, "http://192.168.43.11:8080/images/burger/bigstar.png");
        listFood.add(food);
        adapterFood = new AdapterFood(this, listFood);
        listView.setAdapter(adapterFood);

    }
    private void loadAllFood(){
        adapterFood.notifyDataSetChanged();
    }

    class CallWebService extends AsyncTask<Void, Food, List<Food>> {
        @Override
        protected List<Food> doInBackground(Void... voids) {
            List<Food> result = new ArrayList<>();

            SoapObject soapObject = new SoapObject(NAMESPACE, METHOD_NAME);

            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setType(Food.class);
            propertyInfo.setName("food");
            propertyInfo.setValue(voids[0]);

            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

            try {
                httpTransportSE.call(SOAP_ACTION, envelope);
                SoapPrimitive soapPrimitive = (SoapPrimitive)envelope.getResponse();
                result = (List<Food>) soapPrimitive.getValue();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }

    }

}
