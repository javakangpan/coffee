package com.kangpan.common;

import java.util.Collection;

/**
 * 集合打印工具
 */
public final class Print {
    public static String format(Collection<?> c) {
        if(c.size() == 0) {
            return "[]";
        }
        StringBuffer result = new StringBuffer("[");
        for(Object elem : c) {
            if(c.size() != 1) {
                result.append("\n ");
            }
            result.append(elem);
        }
        if(c.size() != 1) {
            result.append("\n");
        }
        result.append("]");
        return result.toString();
    }
}
