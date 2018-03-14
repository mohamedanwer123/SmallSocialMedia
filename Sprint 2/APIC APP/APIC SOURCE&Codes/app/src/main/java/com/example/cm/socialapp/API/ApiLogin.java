package com.example.cm.socialapp.API;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
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

public class ApiLogin {

    Context context;

    public ApiLogin(Context context) {
        this.context = context;
    }



    public void LOGIN(final String name, final String password) {
        String path = "https://inexpedient-church.000webhostapp.com/chat_get_clientInfo.php?username=" + name + "&password=" + password;

        AndroidNetworking.get(path)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject jsonObject = null;
                        try {

                            jsonObject = response.getJSONObject(0);

                            if (jsonObject.length() == 2) {
                                Intent intent = new Intent(context, Home.class);
                                intent.putExtra("name", name);
                                context.startActivity(intent);
                            } else {


                                String erroe = jsonObject.getString("error");
                                // Toast.makeText(context, erroe, Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, erroe, Toast.LENGTH_LONG).show();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(context, "error", Toast.LENGTH_LONG).show();
                    }
                });
    }

}
