package indi.yunherry.resolve;

import indi.yunherry.annotation.ComplexMatch;
import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.factory.bean.Resolve;
import indi.yunherry.model.dto.ResolveResult;
import indi.yunherry.utils.RegexUtil;
import indi.yunherry.utils.StringCollectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @author YunHerry
 */
@indi.yunherry.annotation.Resolve
public class ArgsResolve extends Resolve implements ComplexMatch<Map<String, String>> {
    public ArgsResolve() {
        super("(?<=-)[\u4e00-\u9fa5a-zA-Z0-9]+(=[\u4e00-\u9fa5a-zA-Z0-9]+)?");
    }

    public ArgsResolve(String regex) {
        super(regex);
    }

    @Override
    public ResolveResult analyze(String input) throws ParameterParsingException {
        return new ResolveResult(match(input));
    }

    @Override
    public ResolveResult analyze(String input, ResolveResult resolveResult) throws ParameterParsingException {
        resolveResult.getMethodArgs().putAll(match(input));
        return resolveResult;
    }

    @Override
    public Map<String, String> match(String input) throws ParameterParsingException {
        List<String> str = new ArrayList<>();
        Matcher matcher;
        try {
            matcher = RegexUtil.isFind(input, getRegex());
            do {
                str.add(matcher.group());
            }
            while (matcher.find());
        } catch (ParameterParsingException ignored) {

        }
        return StringCollectionUtil.ToMap(str.toString());
    }
}
