package com.interview.prj.meeting.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="meeting_type")
@Data
public class MeetingType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;

	@DateTimeFormat
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime time;
	
}
