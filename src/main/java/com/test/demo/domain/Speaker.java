package com.test.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity(name = "speaker")
@EqualsAndHashCode(callSuper = true, exclude = "reports")
public class Speaker extends Base {
    private String firstName;
    private String lastName;
    private String patronymic;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id")
    private Set<Report> reports;
}
