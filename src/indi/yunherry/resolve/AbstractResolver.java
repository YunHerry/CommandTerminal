package indi.yunherry.resolve;

import indi.yunherry.annotation.Aggregate;
import indi.yunherry.annotation.ComplexMatch;
import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.model.dto.ResolveResult;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 * 解析器抽象类
 */
public abstract class AbstractResolver {
    private Pattern regex;
    private Class<?> resolveClass;
    public AbstractResolver(String regex) {
        this.resolveClass = this.getClass();
        this.regex = Pattern.compile(regex);
    }
    public Pattern getRegex() {
        return regex;
    }

    protected void setRegex(String regex) {
        this.regex = Pattern.compile(regex);
    }

    public Class<?> getResolveClass() {
        return resolveClass;
    }
    /**
     * 解析Command数据
     * @param input 传入的Command,当通过解析器的解析,则会进入下一个解析器,如果没有通过,将会抛出异常
     * @exception ParameterParsingException 在无法解析命令时抛出
     * @return ResolveResult 返回解析后的结果
     * */
    protected abstract ResolveResult analyze(String input) throws ParameterParsingException;
    /**
     * 解析Command数据
     * @param input 传入的Command,当通过解析器的解析,则会进入下一个解析器,如果没有通过,将会抛出异常
     * @param resolveResult 解析器解析后得到的结果,传入下一个解析器
     * @exception ParameterParsingException 当正则无法解析传入命令的情况下,将会直接抛出异常
     * @return ResolveResult 返回解析后的结果
     * */
    protected abstract ResolveResult analyze(String input,ResolveResult resolveResult) throws ParameterParsingException;
}
