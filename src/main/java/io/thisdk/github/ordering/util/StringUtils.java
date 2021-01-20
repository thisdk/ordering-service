package io.thisdk.github.ordering.util;

import java.util.Map;

public class StringUtils {



    /***
     * 判断传入的对象是否为空
     *
     * @param obj
     *            待检查的对象
     * @return 返回的布尔值, 为空或等于0时返回true
     */
    public static boolean isEmpty(java.lang.Object obj) {
        return checkObjectIsEmpty(obj, true);
    }

    /***
     * 判断传入的对象是否不为空
     *
     * @param obj
     *            待检查的对象
     * @return 返回的布尔值, 不为空或不等于0时返回true
     */
    public static boolean isNotEmpty(java.lang.Object obj) {
        return checkObjectIsEmpty(obj, false);
    }

    @SuppressWarnings("rawtypes")
    private static boolean checkObjectIsEmpty(java.lang.Object obj, boolean bool) {
        if (null == obj) {
            return bool;
        } else if (obj == "" || "".equals(obj)) {
            return bool;
        } else if (obj instanceof java.lang.Integer || obj instanceof java.lang.Long || obj instanceof java.lang.Double) {
            try {
                java.lang.Double.parseDouble(obj + "");
            } catch (Exception e) {
                return bool;
            }
        } else if (obj instanceof java.lang.String) {
            if (((java.lang.String) obj).length() <= 0) {
                return bool;
            }
            if ("null".equalsIgnoreCase(obj + "")||"undefind".equalsIgnoreCase(obj + "")) {
                return bool;
            }
        } else if (obj instanceof java.util.Map) {
            if (((Map) obj).size() == 0) {
                return bool;
            }
        } else if (obj instanceof java.util.Collection) {
            if (((java.util.Collection) obj).size() == 0) {
                return bool;
            }
        } else if (obj instanceof java.lang.Object[]) {
            if (((java.lang.Object[]) obj).length == 0) {
                return bool;
            }
        }
        return !bool;
    }
}
