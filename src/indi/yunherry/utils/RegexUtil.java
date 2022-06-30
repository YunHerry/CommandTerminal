package indi.yunherry.utils;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.log.InfoPrintExecute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 * 正则相关的封装方法
 */
public class RegexUtil {
    public static Matcher isFind(String input, Pattern regex) throws ParameterParsingException {
        Matcher matcher = regex.matcher(input);
        if (!matcher.find()) {
            throw new ParameterParsingException("Not Parameter Parsing");
        }
        return matcher;
    }
}
