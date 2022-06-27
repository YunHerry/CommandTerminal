package indi.yunHerry.resolve;

import indi.yunHerry.exception.ParameterParsingException;
import indi.yunHerry.log.InfoPrintExecute;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 */
public class ArgsResolveImpl implements Resolve {
    private Pattern pattern = Pattern.compile("(?<=-)[a-zA-Z]+(=\\w+)?");
    public ArgsResolveImpl() {
    }

    @Override
    public String analyze(String input) {
        Matcher matcher = this.pattern.matcher(input);
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
