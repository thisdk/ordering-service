package io.thisdk.github.ordering.utils;

import cn.hutool.core.text.StrFormatter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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


    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params 参数值
     * @return 格式化后的文本
     */
    public static String format(String template, Object... params)
    {
        if (isEmpty(params) || isEmpty(template))
        {
            return template;
        }
        return StrFormatter.format(template, params);
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string)
    {
        try
        {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
