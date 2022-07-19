package indi.yunherry.factory.factory;

import indi.yunherry.constant.enums.ScanTypeEnum;
import indi.yunherry.factory.bean.Execute;
import indi.yunherry.model.dto.TerminalContext;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author YunHerry
 */
public class ExecuteFactory extends Factory {
    @Override
    protected void create(ArrayList<Class<?>> classes) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ArrayList<Class<?>> executes = new ArrayList<>();
        finds(classes,executes, indi.yunherry.annotation.Execute.class, Execute.class, ScanTypeEnum.SCAN_ONLY_FATHER_CLASS);
        for (Class<?> clazz: executes) {
            TerminalContext.terminalContext.executes.add((Execute) clazz.getDeclaredConstructor().newInstance());
        }
    }
}
