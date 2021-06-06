package com.bt.utils;

public class Helper {
    public boolean getBoolean(String n) {
        try {
            return Boolean.parseBoolean(n);
        } catch (Exception e){
            return false;
        }
    }

    public int getInteger(String n) {
        try {
            return Integer.parseInt(n);
        } catch (Exception e){
            return 0;
        }
    }

    public float getFloat(String n) {
        try {
            return Float.parseFloat(n);
        } catch (Exception e) {
            return 0;
        }
    }
}
