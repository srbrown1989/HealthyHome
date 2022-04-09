package com.example.android.healthyhome.database.util;

import com.example.android.healthyhome.database.Provider;
import com.example.android.healthyhome.database.User;

public class Common {
    public static final String BASE_URL = "https://raptor.kent.ac.uk/proj/comp6000/project/14/";

    public static User currentUser;
    public static Provider currentProvider;

    public static IMyAPI getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(IMyAPI.class);
    }

    public static String dateFormat(String date){
        StringBuilder sb = new StringBuilder();
        sb.append(date.substring(date.length()-2,date.length()));
        sb.append("/");
        sb.append(date.substring(date.length()-5,date.length()-3));
        sb.append("/");
        sb.append(date.substring(0,4));

        return sb.toString();
    }

    public static String timeFormat(String time){
        StringBuilder sb = new StringBuilder(time);
        sb.insert(2,':');
        return sb.toString();
    }

}
