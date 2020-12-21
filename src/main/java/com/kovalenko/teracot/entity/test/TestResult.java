package com.kovalenko.teracot.entity.test;

import com.kovalenko.teracot.entity.collected.CollectedStatistic;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_results")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "applied_time")
    private LocalDateTime appliedTime;

    @Column(name = "is_applied", columnDefinition = "boolean")
    private boolean isApplied;

    @Column(name = "dev_build_number")
    private String devBuildNumber;

    @Column(name = "test_build_number")
    private String testBuildNumber;

    @OneToOne(mappedBy = "testResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private CollectedStatistic collectedStatistic;

    @ManyToOne
    @JoinColumn(name = "test_type_id")
    private TestType testType;
}
