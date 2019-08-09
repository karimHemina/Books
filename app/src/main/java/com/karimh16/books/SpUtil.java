package com.karimh16.books;

/* 
 Created by Karim Hemina on 09/08/2019
 */

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class SpUtil {
    private SpUtil(){}

    public static final String PREF_NAME = "BooksPreferences";
    public static final String POSITION = "position";
    public static final String QUERY = "query";

    public static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getPreferenceString(Context context, String key){
        return getPrefs(context).getString(key, "");
    }

    public static int getPreferencesInt(Context context, String key){
        return getPrefs(context).getInt(key, 0);
    }

    public static void setPrefrenceString(Context context, String key, String value){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setPrefrenceInt(Context context, String key, int value){
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static ArrayList<String> getQueryList(Context context){
        ArrayList<String> queryList = new ArrayList<>();
        for(int i = 1; i <= 5; i++){
            String query = getPrefs(context).getString(QUERY + String.valueOf(i), "");
            if(!query.isEmpty()){
                query = query.replace(","," ");
                queryList.add(query.trim());
            }
        }
        return queryList;
    }
}
