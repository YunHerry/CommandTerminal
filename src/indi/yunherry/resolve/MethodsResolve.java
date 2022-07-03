package indi.yunherry.resolve;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.factory.bean.ResolverBean;
import indi.yunherry.model.dto.ResolveResult;
import indi.yunherry.utils.RegexUtil;

import java.util.regex.Matcher;

/**
 * @author YunHerry
 */
public class MethodsResolve extends ResolverBean {
    public MethodsResolve() {
           super("(?<=^\\/)[\\w\\u4e00-\\u9fa5]+");
    }
    public MethodsResolve(String regex) {
        super(regex);
    }
    @Override
    public ResolveResult analyze(String input) throws ParameterParsingException {
        Matcher matcher = RegexUtil.isFind(input,getRegex());
        return new ResolveResult(matcher.group());
    }
    @Override
    public ResolveResult analyze(String input,ResolveResult resolveResult) throws ParameterParsingException {
        Matcher matcher = RegexUtil.isFind(input,getRegex());
        resolveResult.setMethodName(matcher.group());
        return resolveResult;
    }
}
