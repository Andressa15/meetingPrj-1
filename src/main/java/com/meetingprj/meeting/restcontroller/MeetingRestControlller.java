package com.meetingprj.meeting.restcontroller;

import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.meetingprj.exception.ArgumentoNaoValidoErro;
import com.meetingprj.meeting.model.Meeting;
import com.meetingprj.meeting.service.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingRestControlller {

	@Autowired
	private MeetingService service;

	@PostMapping
	public ResponseEntity<String> saveMeeting(@RequestBody @Valid Meeting meeting, BindingResult result , UriComponentsBuilder uriBuilder) {
		if(result.hasErrors())
			throw new ArgumentoNaoValidoErro(result.getFieldErrors());
		
		Meeting meetingRegistered = service.saveMeeting(meeting);
		URI uri = uriBuilder.path("meetingBasic/{id}").build(meetingRegistered.getId());
		return ResponseEntity.created(uri).body("Created");
	}

	@GetMapping("simple/{id}")
	public ResponseEntity<Meeting> getByMeeting(@PathVariable("id") Long id) {
		Optional<Meeting> opt = service.findMeetingById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<Meeting>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(opt.get());
	}

	@GetMapping
	public ResponseEntity<List<Meeting>> allMeeting() {
		List<Meeting> allMeeting = service.findAll();
		return ResponseEntity.ok(allMeeting);
	}

	@PutMapping("simple")
	public ResponseEntity<Optional<Meeting>> updateMeeting(Meeting meeting) {
		Optional<Meeting> opt = service.findMeetingById(meeting.getId());
		if (opt == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opt);
	}

	@GetMapping("/teste")
	public ResponseEntity<String> deleteMeeting(
			@RequestParam("data") @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") Calendar data) {
		System.out.println(data.getTime());
		return ResponseEntity.ok("OK");
	}

	@DeleteMapping("simple")
	public ResponseEntity<String> deleteMeeting(@PathVariable long id) {
		service.deleteByMeetingId(id);
		return ResponseEntity.ok("OK");
	}

}
