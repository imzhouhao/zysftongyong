package com.boot.zysf.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

  private static final ObjectMapper mapper = new ObjectMapper();

  static {
    //可以进行一些初始化设置工作
//    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
  }

  public static List<Map> parseStr2List(String source){
    if(StringUtil.isNullOrBlank(source)){
      return null;
    }
    List<Map> listMaps = null;
    try {
      listMaps = json2list(source, Map.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return listMaps;
  }

  private JsonUtil() {}
  public static ObjectMapper getObjectMapper() {
    return mapper;
  }

  /**
   * 将json字符串转化为url 的querystring
   * @param jsonStr
   * @return
   */
  public static String jsonStr2Querytring(String jsonStr){
    if(StringUtil.isNullOrBlank(jsonStr)){
      return null;
    }
    try{

      jsonStr = jsonStr.substring(2,jsonStr.length()-1);
      jsonStr = jsonStr.replaceAll("\":\"","=")
          .replaceAll("\",\"","&");
    } catch (Exception e){
      return null;
    }
    return jsonStr;
  }

  //对象转字符串
  public static <T> String obj2String(T obj){
    if (obj == null){
      return null;
    }
    try {
      return obj instanceof String ? (String) obj : mapper.writeValueAsString(obj);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //字符串转对象
  public static <T> T string2Obj(String str,Class<T> clazz){
    if (StringUtil.isNullOrBlank(str) || clazz == null){
      return null;
    }
    try {
      return clazz.equals(String.class)? (T) str :mapper.readValue(str,clazz);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * javaBean、列表数组转换为json字符串,忽略空值
   */
  public static String obj2jsonIgnoreNull(Object obj) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.writeValueAsString(obj);
  }


  /**
   * json字符串转换为map
   */
  public static <T> Map<String, Object> json2map(String jsonString) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    return mapper.readValue(jsonString, Map.class);
  }

  /**
   * json字符串转换为map
   */
  public static <T> Map<String, T> json2map(String jsonString, Class<T> clazz) throws Exception {
    Map<String, Map<String, Object>> map = mapper.readValue(jsonString, new TypeReference<Map<String, T>>() {
    });
    Map<String, T> result = new HashMap<>();
    for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
      result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
    }
    return result;
  }

  /**
   * 深度转换json成map
   *
   * @param json
   * @return
   */
  public static Map<String, Object> json2mapDeeply(String json) throws Exception {
    return json2MapRecursion(json);
  }

  /**
   * 把json解析成list，如果list内部的元素存在jsonString，继续解析
   *
   * @param json
   * @return
   * @throws Exception
   */
  private static List<Object> json2ListRecursion(String json) throws Exception {
    if (json == null) {
      return null;
    }

    List<Object> list = mapper.readValue(json, List.class);

    for (Object obj : list) {
      if (obj != null && obj instanceof String) {
        String str = (String) obj;
        if (str.startsWith("[")) {
          obj = json2ListRecursion(str);
        } else if (obj.toString().startsWith("{")) {
          obj = json2MapRecursion(str);
        }
      }
    }

    return list;
  }

  /**
   * 把json解析成map，如果map内部的value存在jsonString，继续解析
   * @param json
   * @return
   * @throws Exception
   */
  private static Map<String, Object> json2MapRecursion(String json) throws Exception {
    if (json == null) {
      return null;
    }

    Map<String, Object> map = mapper.readValue(json, Map.class);

    for (Map.Entry<String, Object> entry : map.entrySet()) {
      Object obj = entry.getValue();
      if (obj != null && obj instanceof String) {
        String str = ((String) obj);

        if (str.startsWith("[")) {
          List<?> list = json2ListRecursion(str);
          map.put(entry.getKey(), list);
        } else if (str.startsWith("{")) {
          Map<String, Object> mapRecursion = json2MapRecursion(str);
          map.put(entry.getKey(), mapRecursion);
        }
      }
    }

    return map;
  }

  /**
   * 与javaBean json数组字符串转换为列表
   */
  public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {

    JavaType javaType = getCollectionType(ArrayList.class, clazz);
    List<T> lst = (List<T>) mapper.readValue(jsonArrayStr, javaType);
    return lst;
  }


  /**
   * 获取泛型的Collection Type
   *
   * @param collectionClass 泛型的Collection
   * @param elementClasses  元素类
   * @return JavaType Java类型
   * @since 1.0
   */
  public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
    return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
  }


  /**
   * map  转JavaBean
   */
  public static <T> T map2pojo(Map map, Class<T> clazz) {
    return mapper.convertValue(map, clazz);
  }

  /**
   * map 转json
   * @param map
   * @return
   */
  public static String mapToJson(Map map) {
    try {
      return mapper.writeValueAsString(map);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  /**
   * map  转JavaBean
   */
  public static <T> T obj2pojo(Object obj, Class<T> clazz) {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    return mapper.convertValue(obj, clazz);
  }


}
