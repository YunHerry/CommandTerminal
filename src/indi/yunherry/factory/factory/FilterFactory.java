package indi.yunherry.factory.factory;

import indi.yunherry.constant.enums.ScanTypeEnum;
import indi.yunherry.factory.bean.Execute;
import indi.yunherry.factory.bean.Filter;
import indi.yunherry.model.dto.TerminalContext;
import indi.yunherry.utils.FindClassUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author YunHerry
 */
public class FilterFactory extends Factory{
    protected void create(ArrayList<Class<?>> classes) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        ArrayList<Class<?>> filters = new ArrayList<>();
        finds(classes,filters, null, Filter.class, ScanTypeEnum.SCAN_ONLY_FATHER_CLASS);
        for (Class<?> clazz: filters) {
            TerminalContext.terminalContext.filters.add((Filter)clazz.getDeclaredConstructor().newInstance());
        }
    }
}
