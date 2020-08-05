package com.svetakvetko.util;


import java.util.HashMap;
import java.util.Map;

public final class URLUtilities {
    public static Map<String, String> getQuery(String req) {
        Map<String, String> queryParam = new HashMap<>();
        String[] split = req.split(";");
        for (String value : split) {
            String[] values = value.split("=");
            queryParam.put(values[0], values[1]);
        }
        return queryParam;

    }
}
