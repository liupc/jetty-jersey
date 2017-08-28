package com.lpc.jackson.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Copyright 2017, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */
public class JAXBTest {

  public static class POJO1 {
    public int id;
    @JsonView(Views.Public.class)
    public String name;

//    @JsonCreator
//    public POJO1(int id, String name) {
//      this.id = id;
//      this.name = name;
//    }

    public POJO1() {
    }

    public void setId(int id) {
      this.id = id;
    }

//    public void setName(String name) {
//      this.name = name;
//    }
  }

  /**
   * 测试json views, 只有json view注解的fields会被选中到Json/xml序列化中
   * @throws Exception
   */
  /*
  @Test
  public void testJsonViews() throws Exception {
    POJO1 pojo = new POJO1(1, "nihiao");
    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    String serOut = mapper.writerWithView(Views.Public.class)
        .writeValueAsString(pojo);
    System.out.println(serOut);
  }
  */

  /**
   * 测试jsonviews反序列化
   */
  @Test
  public void testJsonViewsDeserialization() throws Exception {
    String json = "{\"id\": 1, \"name\": \"nihao\"}";
    ObjectMapper mapper = new ObjectMapper();
//    mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//    POJO1 pojo = mapper.readerWithView(Views.Public.class)
    POJO1 pojo = mapper.reader().forType(POJO1.class)
        .forType(POJO1.class)
        .readValue(json);
    System.out.println(pojo.id);
    System.out.println(pojo.name);
  }
}
