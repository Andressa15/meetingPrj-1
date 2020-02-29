package com.meetingprj.meeting.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingprj.meeting.model.MeetingType;
import com.meetingprj.meeting.repository.MeetingTypeRepository;

@Service
public class MeetingTypeService {

	
	@Autowired
	private MeetingTypeRepository meetingTypeRepository;
		

	
	
	public MeetingType saveType(MeetingType meetingType) {
		MeetingType registered = meetingTypeRepository.save(meetingType);
		return registered;
	}

	public Optional<MeetingType> findMeetingTypeById(long id) {
		return meetingTypeRepository.findById(id);
	}

	public List<MeetingType> findAll() {
		return meetingTypeRepository.findAll();
	}

	public void deleteByMeetingTypeId(Long id) {
		meetingTypeRepository.deleteById(id);
	}
	
	@Transactional
	public MeetingType  updateMeetingType(MeetingType meetingType){
		Optional<MeetingType> meetingType2= meetingTypeRepository.findById(meetingType.getId());
		if(meetingType2.isPresent()) {
			MeetingType meetingTypeUpdate = meetingType2.get();
			meetingTypeUpdate.setDescription(meetingType.getDescription());
			meetingTypeUpdate.setTime(meetingType.getTime());
			return meetingTypeUpdate;
			
		}
		return null;
	}

}
