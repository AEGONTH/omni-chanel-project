package com.adms.entity.cs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Formula;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name="CUSTOMER")
public class Customer extends BaseAuditDomain {

	private static final long serialVersionUID = 2155291361774705908L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Formula(value = " CONCAT("
			+ "		UPPER(LEFT(FIRST_NAME, 1)), LOWER(SUBSTRING(FIRST_NAME, 2, LEN(FIRST_NAME))) "
			+ "		, ' ' "
			+ "		, UPPER(LEFT(LAST_NAME, 1)), LOWER(SUBSTRING(LAST_NAME, 2, LEN(LAST_NAME)))) ")
	private String fullName;
	
	@Column(name="CITIZEN_ID")
	private String citizenId;
	
	@Column(name="PASSPORT_ID")
	private String passportId;
	
	@ManyToOne
	@JoinColumn(name="GENDER", referencedColumnName="PARAM_KEY")
	private ParamConfig gender;
	
	@Column(name="DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name="NATIONALITY")
	private String nationality;
	
	@Column(name="CITIZENSHIP")
	private String citizenship;
	
	@Column(name="MARITAL")
	private String marital;
	
	@Column(name="HOME_NO")
	private String homeNo;
	
	@Column(name="MOBILE_NO")
	private String mobileNo;
	
	@Column(name="OTHER_NO")
	private String otherNo;
	
	@Column(name="OFFICE_NO")
	private String officeNo;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ADDRESS_1")
	private String address1;

	@Column(name="ADDRESS_2")
	private String address2;

	@Column(name="ADDRESS_3")
	private String address3;

	@Column(name="POST_CODE")
	private String postCode;

	@ManyToOne
	@JoinColumn(name="PROVINCE", referencedColumnName="PROVINCE_CODE")
	private Province province;

	@Column(name="VISIBLE")
	private String visible;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public ParamConfig getGender() {
		return gender;
	}

	public void setGender(ParamConfig gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHomeNo() {
		return homeNo;
	}

	public void setHomeNo(String homeNo) {
		this.homeNo = homeNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo1) {
		this.mobileNo = mobileNo1;
	}

	public String getOtherNo() {
		return otherNo;
	}

	public void setOtherNo(String mobileNo2) {
		this.otherNo = mobileNo2;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", title=" + title + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", fullName=" + fullName + ", citizenId=" + citizenId + ", passportId=" + passportId + ", gender="
				+ gender + ", dob=" + dob + ", marital=" + marital + ", homeNo=" + homeNo + ", mobileNo1=" + mobileNo
				+ ", mobileNo2=" + otherNo + ", officeNo=" + officeNo + "]";
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}
	
}
