package com.test.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity(name = "report")
@EqualsAndHashCode(callSuper = true)
public class Report extends Base {
    private String name;
}
