package com.interview.prj.meeting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.prj.meeting.model.MeetingType;

@Repository
public interface MeetingTypeRepository extends JpaRepository<MeetingType,Long>{
	



}
