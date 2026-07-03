package com.stl.ngms.mdm.service;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.stl.ngms.mdm.dao.MdmDao;
import com.stl.ngms.mdm.model.CityModel;
import com.stl.ngms.mdm.model.DesignationModel;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mdm.model.NoticeModel;
import com.stl.ngms.mdm.model.PurposeModel;
import com.stl.ngms.mdm.model.RoomDetailsModel;
import com.stl.ngms.mdm.model.RoomRentModel;
import com.stl.ngms.mdm.model.VehicleMasterModel;
import com.stl.ngms.setting.model.AllLocationModel;

@Service("mdmService")
public class MdmServiceImpl implements MdmService{

	@Autowired MdmDao mdmDao;
	
	@Override
	public List<CityModel> getDataCityTable() {
		return mdmDao.getDataCityTable();
	}

	@Override
	public Boolean deleteCreateUserCity(CityModel requestCity) {
		return mdmDao.deleteCreateUserCity(requestCity);
	}

	@Override
	public boolean saveUserCityTable(CityModel city) {
		return mdmDao.saveUserCityTable(city);
	}

	@Override
	public List<GuestHouseModel> getDataGHTable() {
		return mdmDao.getDataGHTable();
	}

	@Override
	public Boolean deleteCreateUserGH(GuestHouseModel requestGh) {
		return mdmDao.deleteCreateUserGH(requestGh);
	}

	@Override
	public boolean saveUserGHTable(GuestHouseModel gh) {
		return mdmDao.saveUserGHTable(gh);
	}

	@Override
	public Boolean deletePurpose(PurposeModel requestPurpose) {
		return mdmDao.deletePurpose(requestPurpose);
	}

	@Override
	public boolean savePurpose(PurposeModel purpose) {
		return mdmDao.savePurpose(purpose);
	}

	@Override
	public Boolean deleteCategory(PurposeModel requestPurpose) {
		return mdmDao.deleteCategory(requestPurpose);
	}

	@Override
	public boolean saveCategory(PurposeModel purpose) {
		return mdmDao.saveCategory(purpose);
	}

	@Override
	public List<DesignationModel> getDesignationDetails() {
		return mdmDao.getDesignationDetails();
	}

	@Override
	public Boolean deleteDesignation(DesignationModel requestDesg) {
		return mdmDao.deleteDesignation(requestDesg);
	}

	@Override
	public boolean saveDesignation(DesignationModel desg) {
		return mdmDao.saveDesignation(desg);
	}

	@Override
	public List<RoomRentModel> getRentDetails() {
		return mdmDao.getRentDetails();
	}

	@Override
	public Boolean deleteRent(RoomRentModel requestRent) {
		return mdmDao.deleteRent(requestRent);
	}

	@Override
	public boolean saveMasterRoomRent(RoomRentModel rent) {
		return mdmDao.saveMasterRoomRent(rent);
	}

	@Override
	public List<AllLocationModel> getGhDropDown(String cityid,Principal auth) {
		return mdmDao.getGhDropDown(cityid,auth);
	}

	@Override
	public List<AllLocationModel> getAllCity() {
		return mdmDao.getAllCity();
	}

	@Override
	public List<AllLocationModel> getAllGh(String cityid) {
		return mdmDao.getAllGh(cityid);
	}

	@Override
	public List<VehicleMasterModel> getVehicleDetails(Principal auth) {
		return mdmDao.getVehicleDetails(auth);
	}

	@Override
	public boolean saveVehicle(VehicleMasterModel vehicle) {
		return mdmDao.saveVehicle(vehicle);
	}

	@Override
	public Boolean deleteVehicle(VehicleMasterModel requestVeh) {
		return mdmDao.deleteVehicle(requestVeh);
	}

	@Override
	public List<RoomDetailsModel> getRoomDetails(Principal auth) {
		return mdmDao.getRoomDetails(auth);
	}

	@Override
	public Boolean deleteRoomDetails(RoomDetailsModel requestRoom) {
		return mdmDao.deleteRoomDetails(requestRoom);
	}

	@Override
	public boolean saveRoomDetails(RoomDetailsModel room) {
		return mdmDao.saveRoomDetails(room);
	}

	@Override
	public boolean saveNotice(NoticeModel notice, Principal auth) throws ParseException {
		return mdmDao.saveNotice(notice,auth);
	}

	@Override
	public List<NoticeModel> getNoticeDetails(Principal auth) {
		return mdmDao.getNoticeDetails(auth);
	}

	@Override
	public Boolean deleteNotice(NoticeModel notice) {
		return mdmDao.deleteNotice(notice);
	}

}
