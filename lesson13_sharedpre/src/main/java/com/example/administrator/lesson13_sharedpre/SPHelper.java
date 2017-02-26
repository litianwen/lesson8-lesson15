package com.example.administrator.lesson13_sharedpre;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/11/1.
 */

public class SPHelper {

    SharedPreferences sp;

    public SPHelper(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }


    //存
    public void putValue(ContentValue... contentValues) {
        SharedPreferences.Editor et = sp.edit();
        for (ContentValue contentValue : contentValues) {
            //判断类型
            if (contentValue.value instanceof String) {
                et.putString(contentValue.key, contentValue.value.toString());
            } else if (contentValue.value instanceof Integer) {
                et.putInt(contentValue.key, Integer.parseInt(contentValue.value.toString()));
            } else if (contentValue.value instanceof Long) {
                et.putLong(contentValue.key, Long.parseLong(contentValue.value.toString()));
            } else if (contentValue.value instanceof Boolean) {
                et.putBoolean(contentValue.key, Boolean.parseBoolean(contentValue.value.toString()));
            }
        }
        et.commit();
    }

    public String getString(String key) {
        return sp.getString(key, null);
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public int getInt(String key) {
        return sp.getInt(key, -1);
    }

    public long getLong(String key) {
        return sp.getLong(key, -1);
    }


    public void clear() {
        sp.edit().clear().commit();
    }


    public static class ContentValue {
        String key;
        Object value;

        public ContentValue(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public ContentValue() {
        }
    }

}
