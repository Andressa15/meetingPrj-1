package com.interview.prj.meeting.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.interview.prj.person.model.Person;
import com.interview.prj.room.model.Room;

import lombok.Data;

@Entity
@Table(name="Meeting")
@Data
public class Meeting {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String subject;
	
	@Enumerated(EnumType.ORDINAL)
	private MeetingStatus status = MeetingStatus.CREATED;
	
	@OneToOne
	private MeetingType meetingType;
	
	@DateTimeFormat
	private Calendar endTime;
	
	@DateTimeFormat
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private Calendar date;
	
	@OneToOne
	private Room room;
	
	@OneToMany
	private List<Person> guests;
}
