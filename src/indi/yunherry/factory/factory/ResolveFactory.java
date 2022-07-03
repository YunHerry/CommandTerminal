package indi.yunherry.factory.factory;

import indi.yunherry.annotation.Resolve;
import indi.yunherry.factory.bean.ResolverBean;
import indi.yunherry.factory.factory.Factory;

/**
 * @author YunHerry
 * 解析器工厂
 */
@indi.yunherry.annotation.Factory
public abstract class ResolveFactory extends Factory {
    /**
     * 创建解析器Bean
     * @return ResolverBean 返回解析器Bean对象
     * */
    public abstract ResolverBean createResolveBean();
}
