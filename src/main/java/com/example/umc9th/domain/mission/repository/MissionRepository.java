package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    interface HomeMissionItem {
        Long getMissionId();
        Long getStoreId();
        String getStoreName();
        String getConditional();
        Integer getPoint();
        LocalDate getDeadline();
        Integer getDDay();
    }

    //첫 페이지 - 커서 없음
    @Query("""
        select 
            m.id as missionId,
            s.id as storeId,
            s.name as storeName,
            m.conditional as conditional,
            m.point as point,
            m.deadline as deadline,
            function('datediff', m.deadline, :today) as dDay
        from com.example.umc9th.domain.mission.entity.Mission m
        join m.store s
        where s.location.id = :locationId
          and m.deadline >= :today
          and not exists (
              select 1 from com.example.umc9th.domain.mission.entity.MemberMission mm
              where mm.mission = m and mm.member.id = :userId
          )
        order by m.deadline asc, m.id asc
        """)

    Slice<HomeMissionItem> sliceHomeFirst(
            @Param("userId") Long userId,
            @Param("locationId") Long locationId,
            @Param("today") LocalDate today,
            Pageable pageable
    );

    //커서 이후
    @Query("""
        select 
            m.id as missionId,
            s.id as storeId,
            s.name as storeName,
            m.conditional as conditional,
            m.point as point,
            m.deadline as deadline,
            function('datediff', m.deadline, :today) as dDay
        from com.example.umc9th.domain.mission.entity.Mission m
        join m.store s
        where s.location.id = :locationId
          and m.deadline >= :today
          and not exists (
              select 1 from com.example.umc9th.domain.mission.entity.MemberMission mm
              where mm.mission = m and mm.member.id = :userId
          )
          and (
                m.deadline > :cursorDeadline
             or (m.deadline = :cursorDeadline and m.id > :cursorMissionId)
          )
        order by m.deadline asc, m.id asc
        """)
    Slice<HomeMissionItem> sliceHomeAfter(
            Long userId,
            Long locationId,
            LocalDate today,
            LocalDate cursorDeadline,
            Long cursorMissionId,
            Pageable pageable
    );
}
