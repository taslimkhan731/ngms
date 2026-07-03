package com.stl.ngms.mdm.dao;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.stl.ngms.admin.model.ConfrenceHall;
import com.stl.ngms.admin.model.ConfrenceHallDetails;
import com.stl.ngms.common.UserLocationInfo;
import com.stl.ngms.common.dao.UacService;
import com.stl.ngms.mdm.model.CityModel;
import com.stl.ngms.mdm.model.DesignationModel;
import com.stl.ngms.mdm.model.GuestHouseModel;
import com.stl.ngms.mdm.model.NoticeModel;
import com.stl.ngms.mdm.model.PurposeModel;
import com.stl.ngms.mdm.model.RoomDetailsModel;
import com.stl.ngms.mdm.model.RoomRentModel;
import com.stl.ngms.mdm.model.VehicleMasterModel;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.setting.model.UserModel;
import com.stl.ngms.utils.Utils;

@Repository("mdmDao")
public class MdmDaoImpl implements MdmDao  {

	final static Logger logger = Logger.getLogger(MdmDaoImpl.class);

	@Autowired JdbcTemplate jdbcTemplate;

	@Autowired Environment env;
	
	@Autowired UacService uacService;

	@Autowired private Properties mdmQueries;
	
	@Autowired private Properties adminQueries;

	@Override
	public List<CityModel> getDataCityTable() {
		String sql =  mdmQueries.getProperty("getAllCityDetails");
		List<CityModel> lstCity= jdbcTemplate.query(sql, new RowMapper<CityModel>() {
			@Override
			public CityModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				CityModel city = new CityModel();
				city.setCityId(rs.getString("gh_id"));
				city.setCityName(rs.getString("gh_name"));
				city.setDistrictName(rs.getString("district_name"));
				city.setState(rs.getString("state_name"));
				city.setPinCode(rs.getString("pin_code"));
				return city;
			}
		});
		return lstCity;
	}

	@Override
	public Boolean deleteCreateUserCity(CityModel requestCity) {
		String sql = mdmQueries.getProperty("deleteCity");
		jdbcTemplate.update(sql, new Object[] {requestCity.getCityId()});	
		return true;
	}

	@Override
	public boolean saveUserCityTable(CityModel city) {
		if("ADD".equalsIgnoreCase(city.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveCitySQL");
			try {
				String gh_id = null;
				Integer id = get_maxID("gh_details","gh_id");
				if(id != null) {
					if(String.valueOf(id).length() <= 9) {
						gh_id = "0" + id;
					}else {
						gh_id = String.valueOf(id);
					}
				}
				jdbcTemplate.update(sql, new Object[] { 
						city.getCityName(),
						city.getCityName(),
						city.getCityName(),
						city.getDistrictName(),
						city.getState(),
						city.getPinCode(),
						gh_id
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(city.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updateCitySQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						city.getCityName(),
						city.getCityName(),
						city.getCityName(),
						city.getDistrictName(),
						city.getState(),
						city.getPinCode(),
						city.getCityId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public Integer get_maxID(String table, String id) throws Exception {
		String sql = "select max(" + id + ") as id from " + table;
		Integer l_curvalue =0;
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer l_curvalue =0;
				l_curvalue = rs.getInt("id");
				return l_curvalue;
			}
		});

		if(null != users && !users.isEmpty()){
			l_curvalue = users.get(0);
		}
		return l_curvalue + 1;
	}

	@Override
	public List<GuestHouseModel> getDataGHTable() {
		String sql =  mdmQueries.getProperty("getAllGHDetails");
		List<GuestHouseModel> lstCity= jdbcTemplate.query(sql, new RowMapper<GuestHouseModel>() {
			@Override
			public GuestHouseModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				GuestHouseModel gh = new GuestHouseModel();
				gh.setCityId(rs.getString("gh_id"));
				gh.setCityName(rs.getString("gh_name"));
				gh.setGhId(rs.getString("block_id"));
				gh.setGhName(rs.getString("block_name"));
				gh.setTotalFloor(rs.getString("nu_of_floors"));
				gh.setTotalRooms(rs.getString("nu_of_rooms"));
				gh.setAddress(rs.getString("address"));
				gh.setContact_num(rs.getString("contact_num"));
				gh.setAddress_details(rs.getString("address_details"));
				gh.setSupport_document(rs.getString("support_document"));
				gh.setEmailId(rs.getString("mail_id"));
				return gh;
			}
		});
		return lstCity;
	}

	@Override
	public Boolean deleteCreateUserGH(GuestHouseModel requestGh) {
		String sql = mdmQueries.getProperty("deleteGH");
		jdbcTemplate.update(sql, new Object[] {requestGh.getGhId()});	
		return true;
	}

	@Override
	public boolean saveUserGHTable(GuestHouseModel gh) {
		if("ADD".equalsIgnoreCase(gh.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveGHSQL");
		
			try {
				String gh_id = null;
				Integer id = get_maxID("room_block","block_id");
				if(id != null) {
					if(String.valueOf(id).length() <= 9) {
						gh_id = "0" + id;
					}else {
						gh_id = String.valueOf(id);
					}
				}
				jdbcTemplate.update(sql, new Object[] { 
						gh_id,
						gh.getGhName(),
						gh.getCityId(),
						gh.getTotalFloor(),
						gh.getTotalRooms(),
						gh.getGhName(),
						gh.getAddress(),
						gh.getContact_num(),
						gh.getEmailId(),
						gh.getAddress_details(),
						gh.getSupport_document()
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(gh.getType())){
			logger.info("Inside type Update");
			System.out.println("inside update daoimpl");
			
			if( gh.getSupport_document() != null ) {
				System.out.println("Guest house model in_________________"+gh.toString());
				String sql = mdmQueries.getProperty("updateGHSQLforSupportDoc");
				try {
					jdbcTemplate.update(sql, new Object[] { 
							gh.getGhName(),
							gh.getCityId(),
							gh.getTotalFloor(),
							gh.getTotalRooms(),
							gh.getGhName(),
							gh.getAddress(),
							gh.getContact_num(),
							gh.getAddress_details(),
							gh.getSupport_document(),
							gh.getGhId()
					});
				} catch (DataAccessException e) {
					e.printStackTrace();
					return false;
				}
			} else if(gh.getSupport_document() == null) {
				System.out.println("Guest house model value null++++++++++"+gh.toString());
				String sql = mdmQueries.getProperty("updateGHSQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						gh.getGhName(),
						gh.getCityId(),
						gh.getTotalFloor(),
						gh.getTotalRooms(),
						gh.getGhName(),
						gh.getAddress(),
						gh.getContact_num(),
						gh.getAddress_details(),
						gh.getGhId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			System.out.println("query for update________"+sql);
			}
		}
		return true;
	}


	@Override
	public List<PurposeModel> getPurposeDetails() {
		String sql =  mdmQueries.getProperty("getPurposeDetailsSQL");
		List<PurposeModel> lstPurp= jdbcTemplate.query(sql, new RowMapper<PurposeModel>() {
			@Override
			public PurposeModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				PurposeModel purp = new PurposeModel();
				purp.setPurposeId(rs.getString("item_id"));
				purp.setPurposeName(rs.getString("item_title"));
				return purp;
			}
		});
		return lstPurp;
	}
	
	@Override
	public Boolean deletePurpose(PurposeModel requestPurpose) {
		String sql = mdmQueries.getProperty("deletePurpose");
		jdbcTemplate.update(sql, new Object[] {requestPurpose.getPurposeId()});	
		return true;
	}

	@Override
	public boolean savePurpose(PurposeModel purpose) {
		if("ADD".equalsIgnoreCase(purpose.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("savePurposeSQL");
			try {
				String purposeId = null;
				Integer id = get_maxID("m_purpose_items","item_id");
				if(id != null) {
					purposeId = String.valueOf(id);
				}
				jdbcTemplate.update(sql, new Object[] { 
						purposeId,
						purpose.getPurposeName()
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(purpose.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updatePurposeSQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						purpose.getPurposeName(),
						purpose.getPurposeId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	@Override
	public List<PurposeModel> getCategoryDetails() {
		String sql =  mdmQueries.getProperty("getCategoryDetailsSQL");
		List<PurposeModel> lstPurp= jdbcTemplate.query(sql, new RowMapper<PurposeModel>() {
			@Override
			public PurposeModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				PurposeModel purp = new PurposeModel();
				purp.setPurposeId(rs.getString("quota_type"));
				purp.setPurposeName(rs.getString("category"));
				purp.setType(rs.getString("quota_desc"));
				return purp;
			}
		});
		return lstPurp;
	}
	@Override
	public Boolean deleteCategory(PurposeModel requestPurpose) {
		String sql = mdmQueries.getProperty("deleteQuota");
		jdbcTemplate.update(sql, new Object[] {requestPurpose.getPurposeId()});	
		return true;
	}

	@Override
	public boolean saveCategory(PurposeModel purpose) {
		if("ADD".equalsIgnoreCase(purpose.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveCategorySQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						purpose.getPurposeId(),
						purpose.getPurposeName()
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(purpose.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updateCategorySQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						purpose.getPurposeName(),
						purpose.getPurposeId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public List<DesignationModel> getDesignationDetails() {
		String sql =  mdmQueries.getProperty("getDesignationSQL");
		List<DesignationModel> lstDesg= jdbcTemplate.query(sql, new RowMapper<DesignationModel>() {
			@Override
			public DesignationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DesignationModel desg = new DesignationModel();
				desg.setId(rs.getString("vc_id"));
				desg.setDesgName(rs.getString("desg_category"));
				desg.setPriority(rs.getInt("priority"));
				desg.setCategoryId(rs.getString("quota_type"));
				desg.setCategoryName(rs.getString("category"));
				return desg;
			}
		});
		return lstDesg;
	}

	@Override
	public Boolean deleteDesignation(DesignationModel requestDesg) {
		String sql = mdmQueries.getProperty("deleteDesg");
		jdbcTemplate.update(sql, new Object[] {requestDesg.getId()});	
		return true;
	}

	@Override
	public boolean saveDesignation(DesignationModel desg) {
		if("ADD".equalsIgnoreCase(desg.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveDesignationSQL");
			try {
				String gh_id = null;
				Integer id = get_seqID("vc_id");
				update_seqID("vc_id", id);
				gh_id=String.valueOf(id);
				jdbcTemplate.update(sql, new Object[] { 
						gh_id,
						desg.getDesgName(),
						desg.getCategoryId(),
						desg.getPriority(),
						1
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(desg.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updateDesgSQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						desg.getDesgName(),
						desg.getCategoryId(),
						desg.getPriority(),
						desg.getId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public Integer get_seqID(String p_id) throws Exception {
		String sql = "select curvalue from sequence_id where id=? for update";
		Integer l_curvalue = 0;
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer l_curvalue = 0;
				l_curvalue = rs.getInt("curvalue");
				return l_curvalue;
			}
		}, p_id);
		if (null != users && !users.isEmpty()) {
			l_curvalue = users.get(0);
		}
		return l_curvalue + 1;
	}

	public boolean update_seqID(String p_id, Integer p_curvalue) throws Exception {
		String sql = "Update sequence_id set curvalue=? where id=?";
		jdbcTemplate.update(sql, new Object[] { p_curvalue, p_id });
		return true;
	}
	@Override
	public List<RoomRentModel> getRentDetails() {
		String sql =  mdmQueries.getProperty("getRentSQL");
		List<RoomRentModel> lstRent= jdbcTemplate.query(sql, new RowMapper<RoomRentModel>() {
			@Override
			public RoomRentModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				RoomRentModel rent = new RoomRentModel();
				rent.setId(rs.getInt("id"));
				rent.setAmount(rs.getLong("rent_amount"));
				return rent;
			}
		});
		return lstRent;
	}

	@Override
	public Boolean deleteRent(RoomRentModel requestRent) {
		String sql = mdmQueries.getProperty("deleteRent");
		jdbcTemplate.update(sql, new Object[] {requestRent.getId()});	
		return true;
	}

	@Override
	public boolean saveMasterRoomRent(RoomRentModel rent) {
		if("ADD".equalsIgnoreCase(rent.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveRentSQL");
			try {
				Integer id = get_maxID("mst_room_rent","id");
				jdbcTemplate.update(sql, new Object[] { 
						id,
						rent.getAmount()
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(rent.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updateRentSQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						rent.getAmount(),
						rent.getId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public List<AllLocationModel> getGhDropDown(String cityid, Principal auth) {
		String sql = mdmQueries.getProperty("getAllMappedLocByUserFilter");
		List<AllLocationModel> lstLo = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setId(rs.getInt("id"));
				location.setUserId(rs.getString("user_id"));
				location.setGhId(rs.getString("gh_id"));
				location.setCityId(rs.getString("location_id"));
				location.setCityName(rs.getString("block_name"));
				location.setGhName(rs.getString("gh_name"));
				return location;
			}
		},auth.getName(),cityid);
		return lstLo;
	}

	@Override
	public List<AllLocationModel> getAllCity() {
		String sql =  adminQueries.getProperty("getAllCity");
		List<AllLocationModel> lstDistrict= jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setGhId(rs.getString("gh_id"));
				location.setGhName(rs.getString("gh_name"));
				return location;
			}
		});
		System.out.println("get all city details "+lstDistrict);
		System.out.println("query for city details "+sql);
		return lstDistrict;
	}

	@Override
	public List<AllLocationModel> getAllGh(String cityid) {
		String sql =  adminQueries.getProperty("getAllGHByCity");
		List<AllLocationModel> lstDistrict= jdbcTemplate.query(sql,new Object[] {cityid}, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				// TODO Auto-generated method stub
				AllLocationModel location = new AllLocationModel();
				location.setCityId(rs.getString("block_id"));
				location.setCityName(rs.getString("block_name"));
				return location;
			}
		});
		return lstDistrict;
	}

	@Override
	public List<VehicleMasterModel> getVehicleDetails(Principal auth) {
	/*	UserLocationInfo info = uacService.getUserLocationInfo(auth);
		System.out.println("auth  "+auth.toString());
		System.out.println("UserLocationInfo   "+info.toString() );
		String sql = mdmQueries.getProperty("getAllMappedLocByUserForSys");
		if(info != null) {
			if(info.getUser() != null) {
				if(!info.getUser().getUserType().equalsIgnoreCase("SYSADMIN")) {
					sql =  sql + "join adminr.user_location e on e.location_id = c.block_id where e.user_id ='" + auth.getName() + "' and e.status=true";
				}
			}
		}
		*/
		String sql = mdmQueries.getProperty("getAllMappedLocByUserForSys");

		List<VehicleMasterModel> lstVeh= jdbcTemplate.query(sql, new RowMapper<VehicleMasterModel>() {
			@Override
			public VehicleMasterModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				VehicleMasterModel veh = new VehicleMasterModel();
				veh.setVehId(rs.getString("driver_id"));
				veh.setDriverName(rs.getString("driver_name"));
				veh.setDriverMob(rs.getString("driver_mobileno"));
				veh.setVehNo(rs.getString("vehicle_number"));
				veh.setGhId(rs.getString("gh_id"));
				veh.setGhName(rs.getString("gh_name"));
				veh.setBlockId(rs.getString("block_id"));
				veh.setBlockName(rs.getString("block_name"));
				veh.setVehDesc(rs.getString("vehicle_description"));
				return veh;
			}
		});
		System.out.println("vehicle details  "+sql);
		System.out.println("vehicle details data "+lstVeh.toString());
		return lstVeh;

	}

	@Override
	public boolean saveVehicle(VehicleMasterModel vehicle) {
		if("ADD".equalsIgnoreCase(vehicle.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveVehicleSQL");
			try {
				Integer id = get_maxID("m_vehicle","driver_id");
				String  vehId = String.valueOf(id);
				jdbcTemplate.update(sql, new Object[] { 
						vehId,
						vehicle.getDriverName(),
						vehicle.getDriverMob(),
						vehicle.getVehNo(),
						vehicle.getVehDesc(),
						vehicle.getGhId(),
						vehicle.getBlockId()
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(vehicle.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updateVehicleSQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						vehicle.getDriverName(),
						vehicle.getDriverMob(),
						vehicle.getVehNo(),
						vehicle.getVehDesc(),
						vehicle.getGhId(),
						vehicle.getBlockId(),
						vehicle.getVehId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public Boolean deleteVehicle(VehicleMasterModel requestVeh) {
		String sql = mdmQueries.getProperty("deleteVehicle");
		jdbcTemplate.update(sql, new Object[] {requestVeh.getVehId()});	
		return true;
	}

	@Override
	public List<RoomDetailsModel> getRoomDetails(Principal auth) {
		/*UserLocationInfo info = uacService.getUserLocationInfo(auth);
		String sql = mdmQueries.getProperty("getAllRoomByUser");
		if(info != null) {
			if(info.getUser() != null) {
				if(!info.getUser().getUserType().equalsIgnoreCase("SYSADMIN")) {
					sql =  sql + "join adminr.user_location e on e.location_id = c.block_id where e.user_id ='" + auth.getName() + "' and e.status=true";
				}
			}
		}*/
		String sql = mdmQueries.getProperty("getAllRoomByUser");

		List<RoomDetailsModel> lstRoom= jdbcTemplate.query(sql, new RowMapper<RoomDetailsModel>() {
			@Override
			public RoomDetailsModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				RoomDetailsModel room = new RoomDetailsModel();
				room.setBlockId(rs.getString("block_id"));
				room.setGhId(rs.getString("gh_id"));
				room.setBlockName(rs.getString("block_name"));
				room.setGhName(rs.getString("gh_name"));
				room.setRoomFloor(rs.getString("floor_no"));
				room.setRoomNoOfBeds(rs.getString("max_no_beds"));
				room.setRoomNo(rs.getString("room_no"));
				room.setRoomName(rs.getString("room_name"));
				room.setRoomType(rs.getString("room_type"));
				room.setStatus(rs.getBoolean("status"));
				return room;
			}
		});
		System.out.println("room details query  "+sql);
		return lstRoom;
	}

	@Override
	public Boolean deleteRoomDetails(RoomDetailsModel requestRoom) {
		String sql = mdmQueries.getProperty("deleteRoomDetails");
		jdbcTemplate.update(sql, new Object[] {requestRoom.getRoomNo(),requestRoom.getBlockId()});	
		return true;
	}

	@Override
	public boolean saveRoomDetails(RoomDetailsModel room) {
		System.out.println("Inside update type "+room.getType());
		if("ADD".equalsIgnoreCase(room.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveRoomDetaildSQL");
			try {
				String sql1 = "select room_no as id from room_details where room_no= '" + room.getRoomNo() + "' and block_id='" + room.getBlockId() + "'";
				Integer l_curvalue =0;
				List<Integer> users = jdbcTemplate.query(sql1, new RowMapper<Integer>() {
					@Override
					public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
						Integer l_curvalue =0;
						l_curvalue = rs.getInt("id");
						return l_curvalue;
					}
				});
				if(users != null) {
					if(users.size() != 0) {
						l_curvalue = users.get(0);
					}
				}
				if(l_curvalue != 0) {
					return false;
				}
				jdbcTemplate.update(sql, new Object[] { 
						room.getRoomNo(),
						room.getRoomName(),
						room.getRoomType(),
						room.getRoomFloor(),
						room.getRoomNoOfBeds(),
						room.getGhId(),
						room.getBlockId(),
						true
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(room.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updateRoomDetailsSQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						room.getRoomName(),
						room.getRoomType(),
						room.getRoomFloor(),
						room.getRoomNoOfBeds(),
						room.getGhId(),
						room.getBlockId(),
						true,
						room.getRoomNo(),
						room.getBlockId()
						
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
			System.out.println("query for update "+sql);
		}
		return true;
	}

	@Override
	public boolean saveNotice(NoticeModel notice, Principal auth) throws ParseException {
		SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyyy");
		String assig = null;
		if(notice.getGhId() != null || notice.getBlockId() != null) {
			if(!notice.getGhId().isEmpty() && notice.getBlockId().isEmpty()) {
				assig = "CITY_" + notice.getGhId();
			}else if(!notice.getBlockId().isEmpty()){
				assig = "GH_" + notice.getBlockId();
			}else {
				assig = "ALL";
			}
		}
		if("ADD".equalsIgnoreCase(notice.getType())){
			logger.info("Inside type Add");
			String sql = mdmQueries.getProperty("saveNoticeDetaildSQL");
			try {
				Integer id = get_maxID("m_notice","id");
				jdbcTemplate.update(sql, new Object[] { 
					id,
					notice.getSubject(),
					notice.getDesc(),
					parse.parse(notice.getStartDt()),
					parse.parse(notice.getEndDt()),
					false,
					auth.getName(),
					new Date(),
					assig
				});
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}	
		}else if("UPDATE".equalsIgnoreCase(notice.getType())){
			logger.info("Inside type Update");
			String sql = mdmQueries.getProperty("updateNoticeDetailsSQL");
			try {
				jdbcTemplate.update(sql, new Object[] { 
						notice.getSubject(),
						notice.getDesc(),
						parse.parse(notice.getStartDt()),
						parse.parse(notice.getEndDt()),
						assig,
						notice.getId()
				});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	@Override
	public List<NoticeModel> getNoticeDetails(Principal auth) {
		final SimpleDateFormat parse = new SimpleDateFormat("dd-MM-yyyy");
		String sql = mdmQueries.getProperty("getNoticeDetailsSQL");
		List<NoticeModel> lstNotice= jdbcTemplate.query(sql, new RowMapper<NoticeModel>() {
			@Override
			public NoticeModel mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				NoticeModel notice = new NoticeModel();
				notice.setId(rs.getInt("id"));
				notice.setSubject(rs.getString("subject"));
				notice.setDesc(rs.getString("description"));
				notice.setStartDt(parse.format(rs.getDate("from_date")));
				notice.setEndDt(parse.format(rs.getDate("to_date")));
				notice.setStatus(rs.getBoolean("is_deleted") ? "INACTIVE" : "ACTIVE");
				return notice;
			}
		},auth.getName());
		return lstNotice;
	}

	@Override
	public Boolean deleteNotice(NoticeModel notice) {
		String sql = mdmQueries.getProperty("deleteNoticeDetailsSQL");
		if(notice.getType().contentEquals("ACTIVE")) {
			jdbcTemplate.update(sql, new Object[] {true,notice.getId()});
		}else {
			jdbcTemplate.update(sql, new Object[] {false,notice.getId()});
		}
		return true;
	}

	@Override
	public boolean saveRepairReason(RoomDetailsModel room) {
		String sql = mdmQueries.getProperty("saveReasonDetails");
		try {
			String sql1 = "update room_details set room_name=?, room_type=?, floor_no=?, max_no_beds=?, gh_id=?, block_id=?, status=? "
							+ "where room_no='"+room.getRoomNo()+"' and block_id='"+room.getBlockId()+"'";

			jdbcTemplate.update(sql1, new Object[] { 
					room.getRoomName(),
					room.getRoomType(),
					room.getRoomFloor(),
					room.getRoomNoOfBeds(),
					room.getGhId(),
					room.getBlockId(),
					room.getStatus()
			});
			jdbcTemplate.update(sql, new Object[] { 
					room.getGhId(),
					room.getBlockId(),
					room.getRoomNo(),
					room.getRepairReason()
			});
			System.out.println("query for repair  "+sql1);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("query for repair  "+sql);
		return true;
	}

	@Override
	public boolean activateRoom(RoomDetailsModel room) {
		System.out.println("RoomDetailsModel  "+room.toString());
	try {
		String sql = mdmQueries.getProperty("deleteRepairInfo");
		
		jdbcTemplate.update(sql, new Object[] {room.getRoomNo(),room.getBlockId()});	
		
		String sql1 =  "update room_details set room_name = ? ,room_type=? , floor_no=?, max_no_beds=?, gh_id=?, block_id=?, status=? "
				+"where room_no='"+room.getRoomNo()+"' and block_id='"+room.getBlockId()+"'";
		jdbcTemplate.update(sql1, new Object[] { 
				room.getRoomName(),
				room.getRoomType(),
				room.getRoomFloor(),
				room.getRoomNoOfBeds(),
				room.getGhId(),
				room.getBlockId(),
				room.getStatus()
		});
		
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
	System.out.println("room details  "+room.toString());
	return true;}

/* sandip starts */
	@Override
	public List<ConfrenceHallDetails> getConferenceHallUse() {
		String sql = mdmQueries.getProperty("getConfrenceRoomDetails");
		List<ConfrenceHallDetails> confrenceHallDetails = new ArrayList<>();
		try {
			confrenceHallDetails = jdbcTemplate.query(sql, new RowMapper<ConfrenceHallDetails>() {
				@Override
				public ConfrenceHallDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					ConfrenceHallDetails confrenceHallDetails1 = new ConfrenceHallDetails();
					confrenceHallDetails1.setConfCatId(rs.getString("conf_cat_id"));
					confrenceHallDetails1.setConfCatName(rs.getString("conf_cat_desc"));
					return confrenceHallDetails1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return confrenceHallDetails;
	}

	@Override
	public List<ConfrenceHallDetails> getConferenceUse() {
		String sql = mdmQueries.getProperty("getConfrenceUseDetails");
		List<ConfrenceHallDetails> confrenceHallDetails = new ArrayList<>();
		try {
			confrenceHallDetails = jdbcTemplate.query(sql, new RowMapper<ConfrenceHallDetails>() {
				@Override
				public ConfrenceHallDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					ConfrenceHallDetails confrenceHallDetails1 = new ConfrenceHallDetails();
					confrenceHallDetails1.setConfUseId(rs.getString("conf_use_id"));
					confrenceHallDetails1.setConfUseName(rs.getString("conf_use_desc"));
					return confrenceHallDetails1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return confrenceHallDetails;
	}

	@Override
	@Transactional
	public boolean saveConfrenceMasterData(Map<String, Object> params) {
		String sql = mdmQueries.getProperty("saveConferenceMasterData");
		String confCatId = (String) params.get("confCatId");
		String confUseId = (String) params.get("confUseId");
		String charge = (String) params.get("amount");
		String status = (String) params.get("active");
		String ghid = (String) params.get("cityId");
		String blockId = (String) params.get("blockId");
		String capacity = (String) params.get("capacity");
		try {
			jdbcTemplate.update(sql, new Object[] { confCatId, confUseId, charge, status, ghid, blockId, capacity });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		return true;
	}

	@Override
	public List<ConfrenceHall> getConferenceDetails() {
		String sql = mdmQueries.getProperty("getConferenceForMaster");
		List<ConfrenceHall> confrenceHallDetails = new ArrayList<>();
		try {
			confrenceHallDetails = jdbcTemplate.query(sql, new RowMapper<ConfrenceHall>() {
				@Override
				public ConfrenceHall mapRow(ResultSet rs, int rownumber) throws SQLException {
					ConfrenceHall confrenceHallDetails1 = new ConfrenceHall();
					confrenceHallDetails1.setConfrenceId(rs.getString("conf_cat_id"));
					confrenceHallDetails1.setConfrenceDesc(rs.getString("conf_cat_desc"));
					confrenceHallDetails1.setConfreqnceUseId(rs.getString("conf_use_id"));
					confrenceHallDetails1.setConfreqnceUseDesc(rs.getString("conf_use_desc"));
					confrenceHallDetails1.setCharge(rs.getString("charge"));
					confrenceHallDetails1.setStatus(rs.getString("status"));
					confrenceHallDetails1.setCityId(rs.getString("gh_id"));
					confrenceHallDetails1.setCityName(rs.getString("gh_name"));
					confrenceHallDetails1.setBlockId(rs.getString("block_id"));
					confrenceHallDetails1.setGuestHouseName(rs.getString("block_name"));
					confrenceHallDetails1.setCapacity(rs.getString("capacity"));
					return confrenceHallDetails1;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return confrenceHallDetails;
	}

	@Override
	public boolean upDateConfrenceMasterData(Map<String, Object> params) {
		String sql = mdmQueries.getProperty("upDateConferenceMasterData");
		String confCatId = (String) params.get("confCatId");
		String confUseId = (String) params.get("confUseId");
		String charge = (String) params.get("amount");
		String status = (String) params.get("active");
		String ghid = (String) params.get("cityId");
		String blockId = (String) params.get("blockId");
		String capacity = (String) params.get("capacity");
		try {
			jdbcTemplate.update(sql, new Object[] {charge,status,capacity,confCatId,confUseId,blockId});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

    /* sandip ends */

	@Override
	public List<Map<String, Object>> getResults(String query) {
        return jdbcTemplate.queryForList(query);
	}

	@Override
	public String updateResults(String sql) {
		try {
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "Successfully inserted/updated";
	}
}
