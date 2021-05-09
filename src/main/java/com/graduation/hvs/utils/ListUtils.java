package com.graduation.hvs.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ListUtils {

    /**
     * 判断list值是否重复
     * @param <T>
     */
    public static <T> Boolean hasRe(List<T> list) {
        // 通过去重之后的HashSet长度来判断原list是否包含重复元素
        boolean isRepeat = list.size() != new HashSet<T>(list).size();
        return isRepeat;
    }

    /**
     * 根据key获取list map对应的list string
     * @param List<Map<String,String>>
     */
    public static List<String> getListValue(List<Map<String,Object>> list, String key) {
        List<String> listV = new ArrayList<>();
        for (Map<String,Object> map : list) {
            listV.add((String) map.get(key));
        }
        return listV;
    }
}
