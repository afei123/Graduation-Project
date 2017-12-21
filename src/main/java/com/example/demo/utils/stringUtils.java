package com.example.demo.utils;

/**
 * Created by AFei on 2017/9/29.
 */
public class stringUtils {

    public static boolean stringNotNull(String str){
        if(str != null && !("".equals(str))){
            return true;
        }
        return false;
    }
}
