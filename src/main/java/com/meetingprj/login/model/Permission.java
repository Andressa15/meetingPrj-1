package com.meetingprj.login.model;

import javax.persistence.Id;

import lombok.Data;

@Data
//@Entity
//@Table(name="Permission")
public class Permission {

	@Id
	private Long id;
	private String description;
//	@JsonBackReference
//	@ManyToMany(mappedBy="permissions")
//	private List<Login> login;
}

