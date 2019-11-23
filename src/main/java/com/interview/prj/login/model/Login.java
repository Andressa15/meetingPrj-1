package com.interview.prj.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="Login")
@ToString
public class Login {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="Username")
	private String username;
	@Column(name="Password")
	@JsonIgnore
	private String password;
//	@JsonManagedReference
//	@ManyToMany
//	@JoinTable(
//			name= "Login_Permission",
//			joinColumns = @JoinColumn(name="login_id"),
//			inverseJoinColumns = @JoinColumn(name="permission_id")
//	)
//	private List<Permission> permissions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
