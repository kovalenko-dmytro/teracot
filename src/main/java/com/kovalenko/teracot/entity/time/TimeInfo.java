package com.kovalenko.teracot.entity.time;

import com.kovalenko.teracot.entity.collected.CollectedTestResult;
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
@Table(name = "time_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_info_id")
    private long timeInfoID;

    @Column(name = "time_value")
    private String timeValue;

    @ManyToOne
    @JoinColumn(name = "time_info_type_id")
    private TimeInfoType timeInfoType;

    @ManyToOne
    @JoinColumn(name = "test_result_id")
    private CollectedTestResult collectedTestResult;
}
