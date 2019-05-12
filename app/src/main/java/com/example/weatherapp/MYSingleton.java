package com.example.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MYSingleton{

    private RequestQueue mrequestQueau;
    private static MYSingleton mInstance;
    private Context mContext;

    private MYSingleton(Context context){

        mContext=context;
        mrequestQueau=getRequestQueau();
    }

    public RequestQueue getRequestQueau(){
        if(mrequestQueau==null){
            mrequestQueau= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mrequestQueau;
    }

    public static synchronized MYSingleton getInstance(Context context){
        if (mInstance==null){
            mInstance=new MYSingleton(context);
        }
        return mInstance;
    }

    public void addTORequestQueue(Request request){
        mrequestQueau.add(request);
    }

}
