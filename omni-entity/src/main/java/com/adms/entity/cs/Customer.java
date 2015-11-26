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

	public Customer setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Customer setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Customer setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Customer setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public Customer setCitizenId(String citizenId) {
		this.citizenId = citizenId;
		return this;
	}

	public String getPassportId() {
		return passportId;
	}

	public Customer setPassportId(String passportId) {
		this.passportId = passportId;
		return this;
	}

	public ParamConfig getGender() {
		return gender;
	}

	public Customer setGender(ParamConfig gender) {
		this.gender = gender;
		return this;
	}

	public Date getDob() {
		return dob;
	}

	public Customer setDob(Date dob) {
		this.dob = dob;
		return this;
	}

	public String getMarital() {
		return marital;
	}

	public Customer setMarital(String marital) {
		this.marital = marital;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public Customer setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getHomeNo() {
		return homeNo;
	}

	public Customer setHomeNo(String homeNo) {
		this.homeNo = homeNo;
		return this;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public Customer setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
		return this;
	}

	public String getOtherNo() {
		return otherNo;
	}

	public Customer setOtherNo(String otherNo) {
		this.otherNo = otherNo;
		return this;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public Customer setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
		return this;
	}

	public String getNationality() {
		return nationality;
	}

	public Customer setNationality(String nationality) {
		this.nationality = nationality;
		return this;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public Customer setCitizenship(String citizenship) {
		this.citizenship = citizenship;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Customer setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getAddress1() {
		return address1;
	}

	public Customer setAddress1(String address1) {
		this.address1 = address1;
		return this;
	}

	public String getAddress2() {
		return address2;
	}

	public Customer setAddress2(String address2) {
		this.address2 = address2;
		return this;
	}

	public String getAddress3() {
		return address3;
	}

	public Customer setAddress3(String address3) {
		this.address3 = address3;
		return this;
	}

	public String getPostCode() {
		return postCode;
	}

	public Customer setPostCode(String postCode) {
		this.postCode = postCode;
		return this;
	}

	public Province getProvince() {
		return province;
	}

	public Customer setProvince(Province province) {
		this.province = province;
		return this;
	}

	public String getVisible() {
		return visible;
	}

	public Customer setVisible(String visible) {
		this.visible = visible;
		return this;
	}
	
}
