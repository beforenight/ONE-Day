package studio.uphie.one.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by beforenight on 2016/3/23.
 * Email: beforenight@163.com
 */
public class JsonUtil
{
    /**
     * 将任意对象（包括数组、Map等数据结构）转换成json字符串
     */
    public static String getJson(Object object)
    {
        return JSON.toJSONString(object);
    }

    /**
     * 将Json解析成对象
     *
     * @param json  json字符串
     * @param clazz 对象类
     */
    public static <T> T getEntity(String json, Class<T> clazz)
    {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 将Json解析成对象数组
     *
     * @param json  json字符串
     * @param clazz 对象类
     */
    public static <T> List<T> getEntities(String json, Class<T> clazz)
    {
        return JSON.parseArray(json, clazz);
    }
}
