package com.meetingprj.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingprj.meeting.model.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long>{

}
