package com.example.demotest.entity;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@ToString(callSuper = true)
@Table(name = "`test`")
public class Test{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String a;
    private String b;
    private Timestamp createTime;
    private Timestamp updateTime;
}
