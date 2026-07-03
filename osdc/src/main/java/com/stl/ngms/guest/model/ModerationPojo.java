package com.stl.ngms.guest.model;

import java.io.Serializable;

public class ModerationPojo implements Serializable
{
	private String mod_id;
	private String req_id;
	private String vc_id;
	private String quota_type;
	private String is_vehicle_req;
	private String process_id;
	private String status;
	private String lastupdate;
	private String mrr_id;
	private String block_id;
	private String number;
	private String type;
	private String gh_id;
	private String rooms;
	private String bg;
	private String bl;
	private String dg;
	private String dl;
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getReq_id() {
		return req_id;
	}
	public void setReq_id(String req_id) {
		this.req_id = req_id;
	}
	public String getVc_id() {
		return vc_id;
	}
	public void setVc_id(String vc_id) {
		this.vc_id = vc_id;
	}
	public String getQuota_type() {
		return quota_type;
	}
	public void setQuota_type(String quota_type) {
		this.quota_type = quota_type;
	}
	public String getIs_vehicle_req() {
		return is_vehicle_req;
	}
	public void setIs_vehicle_req(String is_vehicle_req) {
		this.is_vehicle_req = is_vehicle_req;
	}
	public String getProcess_id() {
		return process_id;
	}
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	public String getMrr_id() {
		return mrr_id;
	}
	public void setMrr_id(String mrr_id) {
		this.mrr_id = mrr_id;
	}
	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGh_id() {
		return gh_id;
	}
	public void setGh_id(String gh_id) {
		this.gh_id = gh_id;
	}
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public String getBg() {
		return bg;
	}
	public void setBg(String bg) {
		this.bg = bg;
	}
	public String getBl() {
		return bl;
	}
	public void setBl(String bl) {
		this.bl = bl;
	}
	public String getDg() {
		return dg;
	}
	public void setDg(String dg) {
		this.dg = dg;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	
	@Override
	public String toString() {
		return "ModerationPojo [mod_id=" + mod_id + ", req_id=" + req_id + ", vc_id=" + vc_id + ", quota_type="
				+ quota_type + ", is_vehicle_req=" + is_vehicle_req + ", process_id=" + process_id + ", status="
				+ status + ", lastupdate=" + lastupdate + ", mrr_id=" + mrr_id + ", block_id=" + block_id + ", number="
				+ number + ", type=" + type + ", gh_id=" + gh_id + ", rooms=" + rooms + ", bg=" + bg + ", bl=" + bl
				+ ", dg=" + dg + ", dl=" + dl + "]";
	}
	
	
}
