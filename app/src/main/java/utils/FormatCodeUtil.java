package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wuqi on 15/7/3.
 */
public class FormatCodeUtil {
    public static String codingFormat(String str) {

        try {
            String utfStr = URLEncoder.encode(str, "utf-8");
            return utfStr;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "" ;
    }
}
