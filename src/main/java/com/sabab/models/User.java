package com.sabab.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String genders;
	private List<Integer> followers=new ArrayList<>();
	private List<Integer> following=new ArrayList<>();
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPost=new ArrayList<>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String firstName, String lastName, String email, String password, String genders,
			List<Integer> followers, List<Integer> following, List<Post> savedPost) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.genders = genders;
		this.followers = followers;
		this.following = following;
		this.savedPost = savedPost;
	}

	public List<Post> getSavedPost() {
		return savedPost;
	}

	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}

	public String getGenders() {
		return genders;
	}

	public void setGenders(String genders) {
		this.genders = genders;
	}

	public List<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}

	public List<Integer> getFollowing() {
		return following;
	}

	public void setFollowing(List<Integer> following) {
		this.following = following;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", genders=" + genders + ", followers=" + followers + ", following="
				+ following + ", savedPost=" + savedPost + "]";
	}
	

}
