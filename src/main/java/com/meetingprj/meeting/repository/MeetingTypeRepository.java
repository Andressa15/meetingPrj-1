package com.meetingprj.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meetingprj.meeting.model.MeetingType;

@Repository
public interface MeetingTypeRepository extends JpaRepository<MeetingType,Long>{
	



}
