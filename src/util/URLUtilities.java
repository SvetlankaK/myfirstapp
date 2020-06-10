package util;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class URLUtilities {
    public static Map<String, String> getQuery(HttpServletRequest req) {
        Map<String, String> queryParam = new HashMap<>();
        String queryString = req.getQueryString();
        String[] split = queryString.split(";");
        for (String value : split) {
            String[] values = value.split("=");
            queryParam.put(values[0], values[1]);
        }
        return queryParam;

    }
}
