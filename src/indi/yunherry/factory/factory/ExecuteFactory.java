package indi.yunherry.factory.factory;

import indi.yunherry.factory.bean.Execute;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author YunHerry
 */
public class ExecuteFactory extends Factory {
    @Override
    protected void create(ArrayList<Class> classes) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

    }
    /**
     * 用于创建执行器工厂
     *
     * @return ExecuteBean 返回创建的Bean
     * */
//     public Execute createExecute() {
//
//     }
}
