package indi.yunherry.resolve;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.log.InfoPrintExecute;

import java.util.regex.Matcher;

/**
 * @author YunHerry
 */
public class MethodsResolve extends AbstractResolver {
    public MethodsResolve() {
           super("(?<=^\\/)[\\w\\u4e00-\\u9fa5]+");
    }
    public MethodsResolve(String regex) {
        super(regex);
    }
    @Override
    public String analyze(String input) {
        Matcher matcher = this.getRegex().matcher(input);
        if (!matcher.find()) {
            InfoPrintExecute.ErrorPrint(new ParameterParsingException("No has the Method!"));
            return "";
        } else {
            return matcher.group();
        }
    }
}
