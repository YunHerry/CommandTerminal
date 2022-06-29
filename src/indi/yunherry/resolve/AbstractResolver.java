package indi.yunherry.resolve;

import indi.yunherry.annotation.Aggregate;
import indi.yunherry.exception.ParameterParsingException;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 * 解析器抽象类
 */
public abstract class AbstractResolver {
    private Pattern regex;
    private String resolveName;
    public AbstractResolver(String regex) {
        this.resolveName = this.getClass().getName();
        this.regex = Pattern.compile(regex);
    }
    public Pattern getRegex() {
        return regex;
    }

    protected void setRegex(String regex) {
        this.regex = Pattern.compile(regex);
    }

    protected String getResolveName() {
        return resolveName;
    }

    public void setResolveName(String resolveName) {
        this.resolveName = resolveName;
    }
    /**
     * 解析Command数据
     * @param input 传入的Command,当通过解析器的解析,则会进入下一个解析器,如果没有通过,将会抛出异常
     * @exception ParameterParsingException 在无法解析命令时抛出
     * */
    protected abstract String analyze(String input) throws ParameterParsingException;

}
