package com.myself.console.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_student")
@Data
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 92389236732922971L;

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long cId;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_age")
    private Integer age;

}
