package com.kovalenko.teracot.entity.time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_info_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeInfoType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_info_type_id")
    private long timeInfoTypeID;

    @Column(name = "time_info_type_name")
    private String timeInfoTypeName;
}
