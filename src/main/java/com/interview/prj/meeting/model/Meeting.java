package com.interview.prj.meeting.model;

import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.interview.prj.person.model.Person;
import com.interview.prj.room.model.Room;
import com.sun.istack.NotNull;

import lombok.Data;

@Entity
@Table(name = "Meeting")
@Data
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subject;

	@Enumerated(EnumType.ORDINAL)
	private MeetingStatus status = MeetingStatus.CREATED;

	@OneToOne
	private MeetingType meetingType;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar endTime;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Calendar date;

	@OneToOne
	private Room room;

	@ManyToMany
	private Collection<Person> guests;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public MeetingStatus getStatus() {
		return status;
	}

	public void setStatus(MeetingStatus status) {
		this.status = status;
	}

	public MeetingType getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(MeetingType meetingType) {
		this.meetingType = meetingType;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Collection<Person> getGuests() {
		return guests;
	}

	public void setGuests(Collection<Person> guests) {
		this.guests = guests;
	}
}