package com.adms.entity.sale;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.adms.common.domain.BaseAuditDomain;

@Entity
@Table(name = "S_TSR")
public class Tsr extends BaseAuditDomain {

	private static final long serialVersionUID = -2093244227618224195L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TSR_CODE")
	private String tsrCode;

	@ManyToOne
	@JoinColumn(name = "TSR_POSITION", referencedColumnName = "POSITION_CODE")
	private TsrPosition tsrPosition;

	@ManyToOne
	@JoinColumn(name = "TSR_STATUS", referencedColumnName = "STATUS_CODE")
	private TsrStatus tsrStatus;

	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MID_NAME")
	private String midName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EFFECTIVE_DATE")
	@Temporal(TemporalType.DATE)
	private Date effectiveDate;

	@Column(name = "RESIGN_DATE")
	@Temporal(TemporalType.DATE)
	private Date resignDate;

	@Column(name = "REMARK")
	private String remark;
	
	public Tsr() {
		
	}
	
	public Tsr(String tsrCode) {
		this.tsrCode = tsrCode;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTsrCode()
	{
		return tsrCode;
	}

	public void setTsrCode(String tsrCode)
	{
		this.tsrCode = tsrCode;
	}

	public TsrPosition getTsrPosition()
	{
		return tsrPosition;
	}

	public void setTsrPosition(TsrPosition tsrPosition)
	{
		this.tsrPosition = tsrPosition;
	}

	public TsrStatus getTsrStatus()
	{
		return tsrStatus;
	}

	public void setTsrStatus(TsrStatus tsrStatus)
	{
		this.tsrStatus = tsrStatus;
	}

	public String getEmployeeCode()
	{
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode)
	{
		this.employeeCode = employeeCode;
	}

	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMidName()
	{
		return midName;
	}

	public void setMidName(String midName)
	{
		this.midName = midName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Date getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public Date getResignDate()
	{
		return resignDate;
	}

	public void setResignDate(Date resignDate)
	{
		this.resignDate = resignDate;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Override
	public String toString()
	{
		return "Tsr [id=" + id + ", tsrCode=" + tsrCode + ", tsrPosition=" + tsrPosition + ", tsrStatus=" + tsrStatus + ", employeeCode=" + employeeCode + ", fullName=" + fullName + ", title=" + title + ", firstName=" + firstName + ", midName=" + midName + ", lastName=" + lastName
				+ ", effectiveDate=" + effectiveDate + ", resignDate=" + resignDate + ", remark=" + remark + "]";
	}

}
