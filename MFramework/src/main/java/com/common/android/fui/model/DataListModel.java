package com.common.android.fui.model;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by macbook on 16/6/1.
 */
public class DataListModel<T> extends DataModel {

    public ArrayList<T> data;

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }


    public static DataListModel fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(DataListModel.class, clazz);
        return gson.fromJson(json, objectType);
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(DataListModel.class, clazz);
        return gson.toJson(this, objectType);
    }

    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
