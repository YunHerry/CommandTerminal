package indi.yunHerry.resolve;

import indi.yunHerry.exception.ParameterParsingException;
import indi.yunHerry.log.InfoPrintExecute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 */
public interface Resolve {
    /**
     * 用于解析命令
     *
     * */
    abstract String analyze(String input);
}
