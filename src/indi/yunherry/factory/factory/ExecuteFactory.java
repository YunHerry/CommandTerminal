package indi.yunherry.factory.factory;

import indi.yunherry.factory.bean.Bean;
import indi.yunherry.factory.bean.ExecuteBean;

/**
 * @author YunHerry
 */
public abstract class ExecuteFactory extends Factory {
    /**
     * 用于创建执行器工厂
     *
     * @return ExecuteBean 返回创建的Bean
     * */
     public abstract ExecuteBean createExecute();
}
