package com.proyectoweb.helper;

import com.proyectoweb.entity.Owner;

public class HelperInformation {
    static public Owner updateInfoWithInformation(Owner oldData, Owner newData) {
        if (newData == null) {
            return oldData;
        }
        return newData;
    }
    static public String updateInfoWithInformation(String oldData, String newData) {
        if (newData == null) {
            return oldData;
        }
        return newData;
    }
    static public Integer updateInfoWithInformation(Integer oldData, Integer newData) {
        if (newData == null) {
            return oldData;
        }
        return newData;
    }
}
