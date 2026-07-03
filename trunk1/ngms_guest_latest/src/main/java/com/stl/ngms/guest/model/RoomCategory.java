package com.stl.ngms.guest.model;

public class RoomCategory {
	
	private String Quota_type;
	private String Quota_desc;
	private String block_id;
	private String bock_name;
	private String gh_id;
	private String no_rooms;
	private String max_beds;
	private String dor_g;
	private String dor_l;
	private String status;
	private String noofrooms;
	private String noofbg;
	private String noofbl;
	private String noofdg;
	private String noofdl;
	public String getQuota_type() {
		return Quota_type;
	}
	public void setQuota_type(String quota_type) {
		Quota_type = quota_type;
	}
	public String getQuota_desc() {
		return Quota_desc;
	}
	public void setQuota_desc(String quota_desc) {
		Quota_desc = quota_desc;
	}
	
	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	public String getBock_name() {
		return bock_name;
	}
	public void setBock_name(String bock_name) {
		this.bock_name = bock_name;
	}
	public String getGh_id() {
		return gh_id;
	}
	public void setGh_id(String gh_id) {
		this.gh_id = gh_id;
	}
	public String getNo_rooms() {
		return no_rooms;
	}
	public void setNo_rooms(String no_rooms) {
		this.no_rooms = no_rooms;
	}
	public String getMax_beds() {
		return max_beds;
	}
	public void setMax_beds(String max_beds) {
		this.max_beds = max_beds;
	}
	public String getDor_g() {
		return dor_g;
	}
	public void setDor_g(String dor_g) {
		this.dor_g = dor_g;
	}
	public String getDor_l() {
		return dor_l;
	}
	public void setDor_l(String dor_l) {
		this.dor_l = dor_l;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNoofrooms() {
		return noofrooms;
	}
	public void setNoofrooms(String noofrooms) {
		this.noofrooms = noofrooms;
	}
	public String getNoofbg() {
		return noofbg;
	}
	public void setNoofbg(String noofbg) {
		this.noofbg = noofbg;
	}
	public String getNoofbl() {
		return noofbl;
	}
	public void setNoofbl(String noofbl) {
		this.noofbl = noofbl;
	}
	public String getNoofdg() {
		return noofdg;
	}
	public void setNoofdg(String noofdg) {
		this.noofdg = noofdg;
	}
	public String getNoofdl() {
		return noofdl;
	}
	public void setNoofdl(String noofdl) {
		this.noofdl = noofdl;
	}
	@Override
	public String toString() {
		return "RoomCategory [Quota_type=" + Quota_type + ", Quota_desc=" + Quota_desc + ", block_id=" + block_id
				+ ", bock_name=" + bock_name + ", gh_id=" + gh_id + ", no_rooms=" + no_rooms + ", max_beds=" + max_beds
				+ ", dor_g=" + dor_g + ", dor_l=" + dor_l + ", status=" + status + ", noofrooms=" + noofrooms
				+ ", noofbg=" + noofbg + ", noofbl=" + noofbl + ", noofdg=" + noofdg + ", noofdl=" + noofdl + "]";
	}
	
	
}
