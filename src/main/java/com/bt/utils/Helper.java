package com.bt.utils;

public class Helper {
    public boolean getBoolean(String n) {
        if (n != null) {
            return Boolean.parseBoolean(n);
        } else {
            return false;
        }
    }

    public int getInteger(String n) {
        if (n != null) {
            return Integer.parseInt(n);
        } else {
            return 0;
        }
    }

    public float getFloat(String n) {
        if (n != null) {
            return Float.parseFloat(n);
        } else {
            return 0;
        }
    }
}
