package com.test.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Entity(name = "report")
@EqualsAndHashCode(callSuper = true)
public class Report extends Base {
    private String name;
    private LocalDate date;
}
