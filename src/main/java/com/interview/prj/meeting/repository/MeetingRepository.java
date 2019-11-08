package com.interview.prj.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.prj.meeting.model.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>{

}
