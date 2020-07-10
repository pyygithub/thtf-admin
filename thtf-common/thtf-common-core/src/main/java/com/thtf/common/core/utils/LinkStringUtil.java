package com.thtf.common.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * ---------------------------
 * 集合转换地址栏拼接字符串工具类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/20 16:48
 * 版本：  v1.0
 * ---------------------------
 */
public class LinkStringUtil {
    /**
     * map转  链接形式
     *
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createLinkStringByGet(Map<String, Object> params) throws UnsupportedEncodingException {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String str = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = (String) params.get(key);
            value = URLEncoder.encode(value, "UTF-8");
            if (i == keys.size() - 1) {
                str = str + key + "=" + value;
            } else {
                str = str + key + "=" + value + "&";
            }

        }
        return str;
    }
}
