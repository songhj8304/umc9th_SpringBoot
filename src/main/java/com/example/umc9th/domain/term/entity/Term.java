package com.example.umc9th.domain.term.entity;

import com.example.umc9th.domain.term.enums.TermName;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private TermName name;
}
