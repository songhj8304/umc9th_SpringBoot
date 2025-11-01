package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    //특정 회원의 진행중, 진행완료 미션 조회
    Slice<MemberMission> findByMember_IdAndCompleteOrderByCreatedAt(
            Long memberId, boolean complete, Pageable pageable
    );
}
