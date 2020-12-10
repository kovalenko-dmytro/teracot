package com.kovalenko.teracot.entity.test;

import java.time.LocalDateTime;
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
@Table(name = "test_results")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id")
    private long testResultID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "dialect_pair")
    private String dialectPair;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "is_applied", columnDefinition = "boolean")
    private boolean isApplied;

    @ManyToOne
    @JoinColumn(name = "test_type_id")
    private TestType testType;
}
