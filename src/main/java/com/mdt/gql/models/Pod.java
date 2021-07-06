package com.mdt.gql.models;

//import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "POD")
@Data
@NoArgsConstructor
public class Pod {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(length = 20, nullable = true)
    private String name;
    @Column(name = "TYPE", length = 4, nullable = true)
    private String type;
    @Column(name = "DESCRIPTION", length = 250, nullable = true)
    private String description;
    @CreationTimestamp
    @Column(name = "CREATIONTIME", nullable = false)
    private Timestamp creation_time;

    public Pod(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

}