package com.stl.ngms.orrisaone.dao;

import org.json.JSONObject;

import com.stl.ngms.orrisaone.model.UserModel;



public interface OrisaoneDao {
	JSONObject validateUidAndEmail(String userid);
	JSONObject validateUid(String userid);
	boolean saveGuestDetails(UserModel user);
	UserModel getUserByUserId(String userName);
}
