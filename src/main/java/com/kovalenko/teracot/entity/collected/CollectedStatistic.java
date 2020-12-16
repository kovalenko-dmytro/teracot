package com.kovalenko.teracot.entity.collected;

import com.kovalenko.teracot.entity.ai.ActionItemCount;
import com.kovalenko.teracot.entity.test.TestResult;
import com.kovalenko.teracot.entity.time.TimeInfo;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "collected_statistics")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CollectedStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collected_statistic_id")
    private long collectedStatisticID;

    @Column(name = "version")
    private String version;

    @Column(name = "dialect_pair")
    private String dialectPair;

    @Column(name = "source_connection")
    private String sourceConnection;

    @Column(name = "target_connection")
    private String targetConnection;

    @Column(name = "gc_memory")
    private String gcMemory;

    @Column(name = "tree_validator_state")
    private String treeValidatorState;

    @Column(name = "all_obj_count")
    private int allObjectsCount;

    @Column(name = "transformed_obj_count")
    private int transformedObjectsCount;

    @Column(name = "applied_obj_count")
    private int appliedObjectsCount;

    @Column(name = "transformed_from_error_report_count")
    private int transformerFromErrorReportCount;

    @Column(name = "npe_count")
    private int nullPointerExceptionsCount;

    @Column(name = "project_size")
    private String projectSize;

    @Column(name = "target_size")
    private String targetSize;

    @Column(name = "source_size")
    private String sourceSize;

    @OneToMany(mappedBy = "collectedStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimeInfo> timeInfo = new LinkedHashSet<>();

    @OneToMany(mappedBy = "collectedStatistic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ActionItemCount> actionItemCounts = new LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "test_result_id")
    private TestResult testResult;
}
