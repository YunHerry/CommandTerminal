package indi.yunHerry.resolve;

import indi.yunHerry.exception.ParameterParsingException;
import indi.yunHerry.log.InfoPrintExecute;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author YunHerry
 */
public class ArgsResolve extends Resolver {
    public ArgsResolve() {
        super("(?<=-)[\u4e00-\u9fa5a-zA-Z0-9]+(=[\u4e00-\u9fa5a-zA-Z0-9]+)?");
    }
    public ArgsResolve(String regex) {
        super(regex);
    }
    @Override
    public String analyze(String input) {
        Matcher matcher = this.getRegex().matcher(input);
        if (!matcher.find()) {
            InfoPrintExecute.ErrorPrint(new ParameterParsingException("No has the Args!"));
            return "";
        } else {
            //匹配的元素集合
            List<String> str = new ArrayList<>();
            do {
                str.add(matcher.group());
            }
            while (matcher.find());

            return str.toString();
        }
    }
}
