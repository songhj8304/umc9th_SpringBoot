package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.food.entity.MemberFood;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.term.entity.MemberTerm;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 3, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MemberFood> favoriteFoods = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MemberTerm> terms = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<MemberMission> missions = new ArrayList<>();
}
