package indi.yunherry.filter;

import indi.yunherry.factory.bean.Filter;
import indi.yunherry.model.dto.ResolveResult;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YunHerry
 * 自动类型转换
 */
public class AutoFilter implements Filter {
    private static String value;

    @Override
    public ResolveResult filter(ResolveResult resolveResult) {
//        Map<String,Object> map = new HashMap<>();
//        for (Map.Entry<String, Object> entry : resolveResult.getMethodArgs().entrySet()) {
//            ArrayList<Class<?>> decideClass = new ArrayList<>(List.of(new Class<?>[]{Boolean.class}));
//            if (!("true".equals(entry.getValue()) || "false".equals(entry.getValue()))) {
//                decideClass.remove(Boolean.class);
//            }
//            resolveResult.setTypeArgs(entry.getKey(), typeTransitions(entry.getValue(), decideClass));
//            addIncompatibleClass(decideClass, "[0-9]*\\..[0-9]*D$", Double.class, (String) entry.getValue());
//            addIncompatibleClass(decideClass, "[0-9]*\\..[0-9]*F$", Float.class, (String) entry.getValue());
//            addIncompatibleClass(decideClass, "[0-9]+L$", Long.class, (String) entry.getValue());
//            addIncompatibleClass(decideClass, "[0-9]+I$", Integer.class, (String) entry.getValue());
//            addIncompatibleClass(decideClass, "[0-9]+S$", Short.class, (String) entry.getValue());
//            map.put(entry.getKey(), value);
//        }
//        resolveResult.setMethodArgs(map);
        return resolveResult;
    }

    private List<Class<?>> typeTransitions(Object obj, ArrayList<Class<?>> transitionType) {
        Iterator<Class<?>> classIterator = ((List<Class<?>>) transitionType).iterator();
        while (classIterator.hasNext()) {
            Class<?> tClass = classIterator.next();
            try {
                tClass.getDeclaredConstructor(String.class).newInstance(obj);
            } catch (Exception exception) {
                classIterator.remove();
            }
        }
        return transitionType;
    }

    private void addIncompatibleClass(ArrayList<Class<?>> decideClass, String regex, Class<?> clazz, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println("加入数组");
            decideClass.add(clazz);
            AutoFilter.value = matcher.group().substring(0, matcher.group().length() - 1);
        }
    }
}
