package com.interview.prj.meeting.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.interview.prj.meeting.model.Meeting;
import com.interview.prj.meeting.model.MeetingType;
import com.interview.prj.meeting.service.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MettingRestController {

	@Autowired
	private MeetingService meetingService;

	@PostMapping
	public ResponseEntity<String> insertMeeting(@RequestBody Meeting meeting,UriComponentsBuilder uriBuilder){
		Meeting registered = meetingService.save(meeting);
		URI uri = uriBuilder.path("meeting/{id}").build(registered.getId());
		return ResponseEntity.created(uri).body("Created");
	}

	@PostMapping("type")
	public ResponseEntity<String> insertMeetingType(@RequestBody MeetingType meetingType,UriComponentsBuilder uriBuilder){
		MeetingType registered = meetingService.saveType(meetingType);
		URI uri = uriBuilder.path("meeting/type/{id}").build(registered.getId());
		return ResponseEntity.created(uri).body("Created");
	}

	@GetMapping("type/{id}")
	public ResponseEntity<MeetingType> getMeetingTypeById(@PathVariable("id") long id){
		Optional<MeetingType> opt = meetingService.findMeetingTypeById(id);
		if(!opt.isPresent()) {
			return new ResponseEntity<MeetingType>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(opt.get());
	}

	@GetMapping("type")
	public ResponseEntity<List<MeetingType>> getAll(){
		List<MeetingType> all = meetingService.findAll();
		return ResponseEntity.ok(all);
	}

	@PutMapping("type")
	public ResponseEntity<MeetingType> update(@RequestBody MeetingType meetingType){
		MeetingType objAtualizado = meetingService.updateMeetingType(meetingType);
		if(objAtualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(objAtualizado);
	}
	@DeleteMapping("type")
	public ResponseEntity<String> deteleTypeMeeting(@PathVariable("id") long id){
		meetingService.deleteByMeetingTypeId(id);
		return ResponseEntity.ok("Ok");
	}
}

