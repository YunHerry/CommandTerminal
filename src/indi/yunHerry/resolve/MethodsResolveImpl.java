package indi.yunHerry.resolve;

import indi.yunHerry.exception.ParameterParsingException;
import indi.yunHerry.log.InfoPrintExecute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 */
public class MethodsResolveImpl implements Resolve {
    private Pattern pattern = Pattern.compile("(?<=^\\/)\\w+");
    public MethodsResolveImpl() {

    }

    @Override
    public String analyze(String input) {
        Matcher matcher = this.pattern.matcher(input);
        if (!matcher.find()) {
            InfoPrintExecute.ErrorPrint(new ParameterParsingException("No has the Method!"));
            return "";
        } else {
            return matcher.group();
        }
    }
}
