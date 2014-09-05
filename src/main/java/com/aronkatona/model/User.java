package com.aronkatona.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String email;
	
	private Date registrationDate;
	
	private boolean activated;
	
	private String activationString;
	
	public User(){
		
	}
	
	public User(String name, String email, String password){
		this.name = name;
		this.password = password;
		this.email = email;
		this.activated = false;
		this.activationString = getId() + getName() + getEmail();
		this.registrationDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}
	
	
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getActivationString() {
		return activationString;
	}

	public void setActivationString(String activationString) {
		this.activationString = activationString;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	@Override
	public boolean equals(Object object){
		if(object instanceof User){
			return ((User) object).getId() == this.getId();
		}
		return false;
	}

	@Override
	public String toString(){
		return "Name: " + name + "  email: " + email;
	}
}
