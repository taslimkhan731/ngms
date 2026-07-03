package com.stl.ngms.setting.model;

import java.util.Arrays;
import java.util.List;


public class Employee 
{
	private String emp_id;
	private String  gpf_no;
	private String  name;
	private String  email;
	private String  mobile;
	private String  created_on;
	private String  created_by;
	private String  survey_type;
	//private List<Qa> qa;
	private int qus_type[];
	private String answer[];
	
	/*public List<Qa> getQa() {
		return qa;
	}
	public void setQa(List<Qa> qa) {
		this.qa = qa;
	}*/
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getGpf_no() {
		return gpf_no;
	}
	public void setGpf_no(String gpf_no) {
		this.gpf_no = gpf_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	
	public String getSurvey_type() {
		return survey_type;
	}
	public void setSurvey_type(String survey_type) {
		this.survey_type = survey_type;
	}
	
	
	public int[] getQus_type() {
		return qus_type;
	}
	public void setQus_type(int[] qus_type) {
		this.qus_type = qus_type;
	}
	public String[] getAnswer() {
		return answer;
	}
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	
	
	@Override
	public String toString() {
		return "Employee [emp_id=" + emp_id + ", gpf_no=" + gpf_no + ", name="
				+ name + ", email=" + email + ", mobile=" + mobile
				+ ", created_on=" + created_on + ", created_by=" + created_by
				+ ", survey_type=" + survey_type + ", qus_type="
				+ Arrays.toString(qus_type) + ", answer="
				+ Arrays.toString(answer) + "]";
	}
	
	
}
