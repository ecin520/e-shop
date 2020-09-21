package com.pytap.common.utils;

import java.util.Collections;
import java.util.List;

/**
 * @author Ecin520
 * @date 2020/9/21 16:23
 */
public class SortUtil {
    public static boolean compareList(List<Long> list1, List<Long> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        Collections.sort(list1);
        Collections.sort(list2);
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }
}
