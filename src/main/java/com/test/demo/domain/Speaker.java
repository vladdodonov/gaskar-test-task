package com.test.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity(name = "speaker")
@EqualsAndHashCode(callSuper = true)
public class Speaker extends Base {
    private String firstName;
    private String lastName;
    private String patronymic;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private List<Report> reports;
}
