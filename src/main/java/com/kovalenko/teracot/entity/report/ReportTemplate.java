package com.kovalenko.teracot.entity.report;

import com.kovalenko.teracot.entity.test.TestType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report_templates")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private long reportID;

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "service_name")
    private String fullQualifiedServiceName;

    @ManyToOne
    @JoinColumn(name = "test_type_id")
    private TestType testType;
}
