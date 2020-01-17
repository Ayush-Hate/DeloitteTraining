package com.bloodbank.project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="recipient")
@EntityListeners(AuditingEntityListener.class)
public class Recipient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECIPIENT_SEQ")
	@SequenceGenerator(sequenceName="recipient_seq", allocationSize = 1, name = "RECIPIENT_SEQ")
	private Long recipientId;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
		
	@NotBlank
	private String email;
	
	@NotBlank
	private String phone;
	
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	private Long weight;
	
	private String bloodgroup;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<BloodStock> bloodStock;
	
	public Recipient() {
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public List<BloodStock> getBloodStock() {
		return bloodStock;
	}

	public void setBloodStock(List<BloodStock> bloodStock) {
		this.bloodStock = bloodStock;
	}
	
	

}
