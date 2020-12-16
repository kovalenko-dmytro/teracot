package com.kovalenko.teracot.entity.time;

import com.kovalenko.teracot.entity.collected.CollectedStatistic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "time_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "collected_statistic_id")
    private CollectedStatistic collectedStatistic;
}
