package indi.yunHerry.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将String数据进行转换
 */
public class StringCollectionUtil {
    /**
     * @param input 输入的字符串
     */
    public static Map<String, String> ToMap(String input) {
        Pattern regex = Pattern.compile("[\u4e00-\u9fa5a-zA-Z0-9]+(=[\u4e00-\u9fa5a-zA-Z0-9]+)?");
        Matcher matcher = regex.matcher(input);
        Map<String, String> args = new HashMap<>();
        while (matcher.find()) {
            String[] map = matcher.group().split("=");
            if (map.length == 1) {
                args.put(map[0], "");
            } else {
                args.put(map[0], map[1]);
            }

        }
        return args;
    }
}
