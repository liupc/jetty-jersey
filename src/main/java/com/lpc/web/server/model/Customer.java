package com.lpc.web.server.model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Copyright 2017, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */

@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Customer {

  enum Sex {
    MAN,
    WOMAN
  }

  @XmlElement
  private String id;
  @XmlElement
  private int age;
  @XmlElement
  private String name;
  @XmlElement
  private Sex sex;

  public Customer(int age, String name, Sex sex) {
    this.id = UUID.randomUUID().toString();
    this.age = age;
    this.name = name;
    this.sex = sex;
  }
}
