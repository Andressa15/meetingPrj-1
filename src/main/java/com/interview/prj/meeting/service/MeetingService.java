package com.interview.prj.meeting.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.prj.meeting.model.Meeting;
import com.interview.prj.meeting.model.MeetingType;
import com.interview.prj.meeting.repository.MeetingRepository;
import com.interview.prj.meeting.repository.MeetingTypeRepository;

@Service
public class MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;
	
	@Autowired
	private MeetingTypeRepository meetingTypeRepository;
		
	public Meeting save(Meeting meeting) {
		Optional<MeetingType> opt = meetingTypeRepository.findById(meeting.getMeetingType().getId());
		
		if(!opt.isPresent()) {
			System.out.println("Meeting type not registered");
		}
		calculateEndTime(meeting, opt.get());
		
		Meeting registered = meetingRepository.save(meeting);
		return registered;
	}

	private void calculateEndTime(Meeting meeting, MeetingType meetingType) {
		Calendar calendar = meeting.getDate();
		System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(calendar.getTime()));//11:30
	
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(calendar.getTime()); // 11:30
		
		System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(endTime.getTime()));
		
		
		endTime.add(Calendar.MINUTE, meetingType.getTime().getMinute());
		System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(endTime.getTime()));

		meeting.setEndTime(endTime);
	}
	
	public MeetingType saveType(MeetingType meetingType) {
		MeetingType registered = meetingTypeRepository.save(meetingType);
		return registered;
	}

	public Optional<MeetingType> findMeetingTypeById(long id) {
		return meetingTypeRepository.findById(id);
	}
}
