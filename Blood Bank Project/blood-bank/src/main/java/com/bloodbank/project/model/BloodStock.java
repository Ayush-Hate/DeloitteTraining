package com.bloodbank.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="bloodstock")
@EntityListeners(AuditingEntityListener.class)
public class BloodStock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLOOD_SEQ")
	@SequenceGenerator(sequenceName="blood_seq", allocationSize = 1, name = "BLOOD_SEQ")
	private Long bottleId;
	
	@OneToOne
	private Donor donor;
	
	private String bloodgroup;
		
	@Temporal(TemporalType.DATE)
	private Date donationDate;
	
	private Long hemoglobinLevel;
	
	private Long plateletCount;
	
	public BloodStock() {
	}

	public Long getBottleId() {
		return bottleId;
	}

	public void setBottleId(Long bottleId) {
		this.bottleId = bottleId;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public String getBloodgroup() {
		return bloodgroup;
	}

	public void setBloodgroup(String bloodgroup) {
		this.bloodgroup = bloodgroup;
	}

	public Date getDonationDate() {
		return donationDate;
	}

	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}

	public Long getHemoglobinLevel() {
		return hemoglobinLevel;
	}

	public void setHemoglobinLevel(Long hemoglobinLevel) {
		this.hemoglobinLevel = hemoglobinLevel;
	}

	public Long getPlateletCount() {
		return plateletCount;
	}

	public void setPlateletCount(Long plateletCount) {
		this.plateletCount = plateletCount;
	}
	
	
}
