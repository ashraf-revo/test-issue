package com.asrevo.testissue.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Xtable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
