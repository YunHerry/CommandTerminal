package indi.yunherry.annotation;

import indi.yunherry.exception.ParameterParsingException;
import indi.yunherry.model.dto.ResolveResult;

/**
 * @author YunHerry
 * 当匹配变得复杂时,应该实现这个接口
 * when match gets complicated, this interface should be implemented.
 */
public interface ComplexMatch<T> {
    /**
     * 对解析器内解析方法的封装,避免方法覆写增加函数的复用性
     * Here is the encapsulation of the parsing method in the parser to avoid method overriding and increase the reusability of the function
     * @param input 输入需要匹配的字符串
     * @param input just input string u want to match
     * @exception ParameterParsingException 转换错误时会直接抛出该异常
     * @exception ParameterParsingException This exception will be thrown directly when there is a conversion error
     * @return T 返回对应类型的匹配数据
     * @return T Returns matching data of the corresponding type
     * */
    T match(String input) throws ParameterParsingException;
}
