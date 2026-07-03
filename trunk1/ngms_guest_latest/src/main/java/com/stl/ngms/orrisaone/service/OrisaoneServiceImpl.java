package com.stl.ngms.orrisaone.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.ngms.orrisaone.dao.OrisaoneDao;
import com.stl.ngms.orrisaone.model.UserModel;




@Service("orisaService")
public class OrisaoneServiceImpl implements OrisaoneService {
	@Autowired
    private OrisaoneDao orisaoneDao;
	@Override
	public JSONObject validateUidAndEmail(String userid) {
		// TODO Auto-generated method stub
		
		return orisaoneDao.validateUidAndEmail(userid);
	}
	
	/**********************************************************************
	 * Method that will used to check the existance of userId and emailId
	 ***********************************************************************/
	@Override
	public JSONObject validateUid(String userid) {
		// TODO Auto-generated method stub
		return orisaoneDao.validateUid(userid);
	}
	
	@Override
	public boolean saveGuestDetails(UserModel user) {
		user.setActive(true);
		return orisaoneDao.saveGuestDetails(user);
	}
	@Override
	public UserModel getUserByUserId(String userName) {
		return orisaoneDao.getUserByUserId(userName);
	}
}
