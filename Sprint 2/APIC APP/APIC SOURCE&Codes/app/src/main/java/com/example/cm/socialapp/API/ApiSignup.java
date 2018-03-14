package com.example.cm.socialapp.API;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.cm.socialapp.Activities.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cm on 14/03/2018.
 */

public class ApiSignup {

    Context context;

    public ApiSignup(Context context) {
        this.context = context;
    }



    public void SIGNUP(final String name, String pass) {
        String path = "https://inexpedient-church.000webhostapp.com/chat_insert_clientInfo.php";

        AndroidNetworking.post(path)
                .addBodyParameter("username", name)
                .addBodyParameter("password", pass)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jsonObject = null;
                        try {

                            jsonObject = response.getJSONObject(0);

                            if (jsonObject.length() == 2) {
                                String ok = jsonObject.getString("ok");
                                Intent intent = new Intent(context, Home.class);
                                intent.putExtra("name", name);
                                context.startActivity(intent);
                            } else {
                                String erroe = jsonObject.getString("error");

                                Toast.makeText(context, erroe, Toast.LENGTH_LONG).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {


                    }
                });
    }
}
