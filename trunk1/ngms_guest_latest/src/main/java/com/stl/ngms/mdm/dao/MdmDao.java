package com.stl.ngms.mdm.dao;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

import com.stl.ngms.mdm.model.CityModel;
import com.stl.ngms.mdm.model.DesignationModel;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mdm.model.NoticeModel;
import com.stl.ngms.mdm.model.PurposeModel;
import com.stl.ngms.mdm.model.RoomDetailsModel;
import com.stl.ngms.mdm.model.RoomRentModel;
import com.stl.ngms.mdm.model.VehicleMasterModel;
import com.stl.ngms.setting.model.AllLocationModel;

public interface MdmDao {

	List<CityModel> getDataCityTable();

	Boolean deleteCreateUserCity(CityModel requestCity);

	boolean saveUserCityTable(CityModel city);

	List<GuestHouseModel> getDataGHTable();

	Boolean deleteCreateUserGH(GuestHouseModel requestGh);

	Boolean deletePurpose(PurposeModel requestPurpose);

	boolean saveUserGHTable(GuestHouseModel gh);

	boolean savePurpose(PurposeModel purpose);

	Boolean deleteCategory(PurposeModel requestPurpose);

	boolean saveCategory(PurposeModel purpose);

	List<DesignationModel> getDesignationDetails();

	Boolean deleteDesignation(DesignationModel requestDesg);

	boolean saveDesignation(DesignationModel desg);

	List<RoomRentModel> getRentDetails();

	Boolean deleteRent(RoomRentModel requestRent);

	boolean saveMasterRoomRent(RoomRentModel rent);

	List<AllLocationModel> getGhDropDown(String cityid, Principal auth);

	List<AllLocationModel> getAllCity();

	List<AllLocationModel> getAllGh(String cityid);

	List<VehicleMasterModel> getVehicleDetails(Principal auth);

	boolean saveVehicle(VehicleMasterModel vehicle);

	Boolean deleteVehicle(VehicleMasterModel requestVeh);

	List<RoomDetailsModel> getRoomDetails(Principal auth);

	Boolean deleteRoomDetails(RoomDetailsModel requestRoom);

	boolean saveRoomDetails(RoomDetailsModel room);

	boolean saveNotice(NoticeModel notice, Principal auth) throws ParseException;

	List<NoticeModel> getNoticeDetails(Principal auth);

	Boolean deleteNotice(NoticeModel notice);
}
