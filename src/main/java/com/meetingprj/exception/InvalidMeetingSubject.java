package com.meetingprj.exception;

public class InvalidMeetingSubject extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMeetingSubject(String message) {
		super(message);
	}
}
