package com.test.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity(name = "report")
@EqualsAndHashCode(callSuper = true, exclude = "speaker")
public class Report extends Base {
    private String name;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "report_id")
    @JsonBackReference
    private Speaker speaker;
}
