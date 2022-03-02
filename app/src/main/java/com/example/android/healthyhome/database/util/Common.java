package com.example.android.healthyhome.database.util;

public class Common {
    public static final String BASE_URL = "https://raptor.kent.ac.uk/proj/comp6000/project/14/";

    public static IMyAPI getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(IMyAPI.class);
    }

}
