package com.example.demo.utils;

import java.util.List;

/**
 * Created by AFei on 2017/9/29.
 */
public class listUtils {

    public static boolean listNotNull(List list){
        if(list != null && list.size() > 0){
            return true;
        }
        return false;
    }

}
