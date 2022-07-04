package indi.yunherry.factory.factory;

import indi.yunherry.factory.bean.Command;
/**
 * @author YunHerry
 * 抽象工厂
 */
public abstract class CommandFactory extends Factory {
    /**
     * 创建命令Bean
     * @return CommandBean 返回CommandBean对象
     * */
    public abstract Command createCommand();
}
