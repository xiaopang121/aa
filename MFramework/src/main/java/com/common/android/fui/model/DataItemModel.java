package com.common.android.fui.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by macbook on 16/6/1.
 */
public class DataItemModel<T>extends DataModel {


    public T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static DataItemModel fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(DataItemModel.class, clazz);
        return gson.fromJson(json, objectType);
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
