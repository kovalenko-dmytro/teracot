package com.kovalenko.teracot.entity.ai;

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
@Table(name = "collected_action_item_counts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActionItemCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_id")
    private long aiID;

    @Column(name = "ai_name")
    private String aiName;

    @Column(name = "ai_count")
    private int aiCount;

    @ManyToOne
    @JoinColumn(name = "test_result_id")
    private CollectedTestResult collectedTestResult;
}
