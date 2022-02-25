package com.pizzahouse.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pizzahouse.common.entity.Session;

@Entity
@Table(name="USER")
public class User implements Serializable {

	private static final long serialVersionUID = -6346884675078353970L;

	public User (String username, String firstName, String lastName, String password) {
		setUsername(username);
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
	}
	
	
	public User() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int id;

    @Column(name="USER_NAME", nullable=false)
	private String username;

    @Column(name="FIRST_NAME", nullable=false)
	private String firstName;

    @Column(name="LAST_NAME", nullable=false)
	private String lastName;

    @Column(name="PASSWORD", nullable=false)
	private String password;
    
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private Session session;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
    
}