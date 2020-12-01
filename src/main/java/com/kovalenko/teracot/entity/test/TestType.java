package com.kovalenko.teracot.entity.test;

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
@Table(name = "test_types")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_type_id")
    private long testTypeID;

    @Column(name = "test_type_name")
    private String testTypeName;
}
