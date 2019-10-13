package com.dream.city.base.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

public class ListUtils {

    /**
     * list转换成，每个值加上单引号后，再用逗号隔开的string
     *
     * @param stringList
     * @return
     */
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append("'").append(string).append("'");
        }
        return result.toString();
    }


    public static String listToString(Set<String> stringsSet) {
        if (CollectionUtils.isEmpty(stringsSet)){
            return null;
        }
        String strs = null;
        for (String str : stringsSet){
            if (StringUtils.isNotBlank(strs))
                strs += "','"+ str;
            else
                strs = "'"+ str;
        }
        strs += "'";
        return strs;
    }

}
