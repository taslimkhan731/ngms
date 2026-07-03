package com.stl.ngms.admin.dao;

import com.stl.ngms.admin.model.*;
import com.stl.ngms.common.MailUtil;
import com.stl.ngms.common.PasswordResetModel;
import com.stl.ngms.common.SendSMS;
import com.stl.ngms.common.model.*;
import com.stl.ngms.guest.dao.GuestDao;
import com.stl.ngms.guest.model.*;
import com.stl.ngms.mdm.model.DesignationModel;
import com.stl.ngms.setting.service.AccessControlService;
import com.stl.ngms.utils.Constants;
import com.stl.ngms.utils.Utils;

import org.apache.log4j.Logger;
import org.aspectj.org.eclipse.jdt.core.compiler.InvalidInputException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

	final static Logger logger = Logger.getLogger(AdminDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	Environment env;

	@Autowired
	MailUtil mail;

	@Autowired
	SendSMS smsSend;

	@Autowired
	private Properties approverQueries;

	@Autowired
	private Properties allotmentQueries;

	@Autowired
	private Properties adminQueries;

	@Autowired
	private GuestDao guestDao;
	
	@Autowired
	AccessControlService accessControlService;
	
	
	@Override
	public List<CommonComboBoxResponseModel> getGuestHouseList(String cityId) {
		String query = approverQueries.getProperty("getAllGuestHouseListSQL");
		List<CommonComboBoxResponseModel> cityList = jdbcTemplate.query(query,
				new RowMapper<CommonComboBoxResponseModel>() {
					@Override
					public CommonComboBoxResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
						CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
						res.setId(rs.getString("block_id"));
						res.setName(rs.getString("block_name"));
						return res;
					}
				}, cityId);
		return cityList;
	}

	@Override
	public List<CommonComboBoxResponseModel> getGuestHouseListByUserRoleCity(String userId, String role,
			String cityId) {
		Integer roleId = Integer.parseInt(role);
		String query = adminQueries.getProperty("getAllMappedGHByUserRoleCity");
		return jdbcTemplate.query(query, (rs, rownumber) -> {
			CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
			res.setId(rs.getString("block_id"));
			res.setName(rs.getString("block_name"));
			return res;
		}, userId, roleId, cityId);
	}

	@Override
	public List<CommonComboBoxResponseModel> getCityList() {
		String query = approverQueries.getProperty("getAllCityListSQL");
		List<CommonComboBoxResponseModel> cityList = new ArrayList<>();
		cityList = jdbcTemplate.query(query, new RowMapper<CommonComboBoxResponseModel>() {
			@Override
			public CommonComboBoxResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
				res.setId(rs.getString("gh_id"));
				res.setName(rs.getString("gh_name"));
				return res;
			}
		});
		return cityList;
	}
	// ***********************************

	@Transactional
	@Override
	public String adminaddBookingDetails(RoomBookingPojo data, String userId, String type) throws Exception {
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		String ghName = null;

		data.setCreatedBy(userId);
		data.setUpdatedBy(userId);

		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {

			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId", rs.getString("gh_id"));
				res.put("bId", rs.getString("block_id"));
				res.put("bName", rs.getString("block_name"));
				res.put("sName", rs.getString("short"));
				return res;
			}
		}, data.getBlockId());
		if (blockList == null || blockList.size() == 0) {
			return null;
		}

		try {
			ghName = getCityName(blockList.get(0).get("ghId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(blockList.get(0).get("ghId"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

		String reqId = "";
		if (type.equalsIgnoreCase("add")) {
			Integer id = get_seqID("req_id");
			update_seqID("req_id", id);
			reqId = "G" + blockList.get(0).get("sName") + id.toString();
		} else if (type.equalsIgnoreCase("split")) {
			reqId = data.getBookingId() + "S";
		}

		logger.info(reqId);
		Date frmDt = !data.getFrmDt().equals("") ? sdf1.parse(data.getFrmDt()) : null;
		Date toDt = !data.getToDt().equals("") ? sdf1.parse(data.getToDt()) : null;
		Date arrDt = !data.getArrDt().equals("") ? sdf.parse(data.getArrDt()) : null;
		Date depDt = !data.getDepDt().equals("") ? sdf.parse(data.getDepDt()) : null;

		Date arrtm = !data.getArrTm().equals("") ? stf.parse(data.getArrDt() + " " + data.getArrTm()) : null;
		Date deptm = !data.getDepTm().equals("") ? stf.parse(data.getDepDt() + " " + data.getDepTm()) : null;

		String q1 = approverQueries.getProperty("saveBookingDetailsSQl");
		String q2 = approverQueries.getProperty("saveArrivalDtlsSQl");
		String q3 = approverQueries.getProperty("saveDepartureDtlsSQl");
		String q4 = approverQueries.getProperty("saveGuestDetailsSQL");

		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			logger.info("saved document file path :: " + filepath);
			if (filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			}
		} else {
			if (type.equals("split")) {
				filepath = data.getSuppDocName();
			}
		}
		String catg_filepath = null;
		if (null != data.getCatgDoc()) {
			String fileSeperator1 = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory1 = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator1;
			catg_filepath = Utils.saveFileInSysPath(data.getCatgDoc(), directory1);
			logger.info("saved document category file path :: " + catg_filepath);
			if (catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
			}
		} else {
			if (type.equals("split")) {
				catg_filepath = data.getCatgDocName();
			}
		}
//		String status = null;
//		if (!data.getRoomType().equalsIgnoreCase("C")) {
//			status = "REQ";
//		} else {
//			status = "ALO";
//		}
		if (data.getRoomType().equalsIgnoreCase("C")) {
			frmDt=null;
			toDt=null;
		}
		try {
			jdbcTemplate.update(q1, new Object[] { reqId, blockList.get(0).get("ghId"), frmDt, toDt,
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
					"0", "REQ", !data.getArrDt().equals("") ? "1" : "0", "GAD", data.getPurp(), "0", data.getOffAddr(),
					data.getMob(),
					data.getBookingType() == null ? "0"
							: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
					"0", "0", data.getMob(), data.getEmailId(),
					data.getExistingBookingId() != null ? data.getExistingBookingId() : "0",
					!data.getDepDt().equals("") ? "1" : "0", data.getBlockId(), // block Id
					filepath, // file path
					data.getCatg(), data.getDesg(), data.getRoomType(), catg_filepath, data.getDuringstay(),
					data.getCatgType(), data.getNoOfDays(), data.getNoOfAdults(), data.getNoOfChildren(),
					data.getBookingFor(), data.getCreatedBy(), data.getUpdatedBy() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		String msg = "";
		if (data.getBookingType() != null) {
			if (data.getBookingType().equalsIgnoreCase("room")) {
				if (data.getNoOfRooms() != null) {
					if (!data.getNoOfRooms().isEmpty()) {
						if (!data.getNoOfRooms().equalsIgnoreCase("0")) {
							msg = data.getBookingType() == null ? "0"
									: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0")
											+ " Rooms in " + blockList.get(0).get("bName") + " " + ghName;
						}
					}
				} else {
					msg = data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0") + " Beds in "
									+ blockList.get(0).get("bName") + " " + ghName;
				}
			} else {
				msg = data.getBookingType() == null ? "0"
						: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0")
								+ " Dormetary Beds in " + blockList.get(0).get("bName") + " " + ghName;
			}
		}
		if (data.getRoomType().equalsIgnoreCase("RC") || data.getRoomType().equalsIgnoreCase("C")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "G_" + vid.toString();
			if(!data.getArrDt().equals("")) {
				jdbcTemplate.update(q2, new Object[] { vehId, reqId, data.getTravelModeFr(), arrtm, data.getArrPla(), "0",
						data.getFlNoFr(), arrDt });
			}
			if(!data.getDepDt().equals("")) {
				jdbcTemplate.update(q3, new Object[] { vehId, reqId, data.getTravelModeTo(), deptm, data.getDepPla(), "0",
						data.getFlNoTo(), depDt });
			}
		}
		String guestName = "Applicant ";
		// if (!data.getRoomType().equalsIgnoreCase("C")) {
		if (data.getList() != null) {
			for (GuestDetailsPojo item : data.getList()) {
				Integer gid = get_seqID("guest_id");
				String guestId = "G_" + gid.toString();
				update_seqID("guest_id", gid);
				try {
					jdbcTemplate.update(q4,
							new Object[] { reqId, guestId, item.getGuestName(), item.getGuestAge(),
									item.getGuestGender(), item.getGuestAddr(), item.getGuestDesignation(),
									item.getGuestDepartment(), item.getGuestMobile(), item.getGuestDocType(),
									item.getGuestDocPath(), "0" });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Guest Details not saved");
				}
			}
			guestName = data.getList().get(0).getGuestName();
		}
		/*
		 * } else { msg = "only conveynce"; }
		 */

//		try {
//			if (data.getEmailId() != null) {
//				if (!data.getEmailId().isEmpty()) {
//					mail.sendMimeMail(data.getEmailId(), "Application Details",
//							"Dear " + guestName + "<br> You have successfully applied for " + msg
//									+ " and your booking id " + reqId + "<br><br> Thanks <br>e-Bhawan Team");// mail
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send..");
//		}
		try {
			if (data.getMob() != null) {
				if (!data.getMob().isEmpty()) {
					String templateID = "1107160707714316860";
					smsSend.sendSMS(data.getMob(), "Dear " + guestName + " You have successfully applied for " + msg
							+ " and your booking id " + reqId + " ", templateID);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}

		return reqId;
	}

	@Override
	public boolean update_seqID(String p_id, Integer p_curvalue) throws Exception {
		String sql = "Update sequence_id set curvalue=? where id=?";
		jdbcTemplate.update(sql, new Object[] { p_curvalue, p_id });
		return true;
	}

	@Override
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

	public String getPropValue(String p_id) throws Exception {
		String sql = "select prop_value from property where prop_id=?";
		String propvalue = null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String propvalue = null;
				propvalue = rs.getString("prop_value");
				return propvalue;
			}
		}, p_id);
		if (null != users && !users.isEmpty()) {
			propvalue = users.get(0);
		}
		return propvalue;
	}

	private String getCityName(String blockId) {
		String query = "select * from gh_details where gh_id = ?";
		String ghId = null;
		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId", rs.getString("gh_name"));
				return res;
			}
		}, blockId);
		if (blockList != null || blockList.size() != 0) {
			ghId = blockList.get(0).get("ghId");
		}
		return ghId;
	}

	@Override
	public String saveBookingDetails(String userId, RoomBookingPojo data, String type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// *******************************************
	@Transactional
	@Override
	public boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		RoomBookingPojo pd = admingetBookingDetailsById(data.getBookingId());
		logger.info(data);
		logger.info(pd);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		boolean status = false;

		Date frmDt = !data.getFrmDt().equals("") ? sdf1.parse(data.getFrmDt()) : null;
		Date toDt = !data.getToDt().equals("") ? sdf1.parse(data.getToDt()) : null;
		Date arrDt = !data.getArrDt().equals("") ? sdf.parse(data.getArrDt()) : null;
		Date depDt = !data.getDepDt().equals("") ? sdf.parse(data.getDepDt()) : null;

		Date arrtm = !data.getArrTm().equals("") ? stf.parse(data.getArrDt() + " " + data.getArrTm()) : null;
		Date deptm = !data.getDepTm().equals("") ? stf.parse(data.getDepDt() + " " + data.getDepTm()) : null;

		String q1 = approverQueries.getProperty("updateBookingDetailsSQl");
		String q2 = approverQueries.getProperty("saveArrivalDtlsSQl");
		String q3 = approverQueries.getProperty("saveDepartureDtlsSQl");
		String q4 = approverQueries.getProperty("saveGuestDetailsSQL");

		String q5 = approverQueries.getProperty("updateArrivalDtlsSQl");
		String q6 = approverQueries.getProperty("updateDepartureDtlsSQl");
		String q7 = approverQueries.getProperty("updateGuestDetailsSQL");
		String q8 = approverQueries.getProperty("deleteGuestDetailsSQL");
		String conveyanceFrom="0";
		String conveyanceTo="0";
		if(data.getArrTravel() != null) {
			if(!data.getArrDt().equals("")) {
				conveyanceFrom="1";
			}
		}
		if(data.getDeptTravel() != null) {
			if(!data.getDepDt().equals("")) {
				conveyanceTo="1";
			}
		}
		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(pd.getBookingId());
			String directory = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			logger.info("saved document file path :: " + filepath);
			if (filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			} else {
				try {
					filepath = get_filePath(pd.getBookingId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				filepath = get_filePath(pd.getBookingId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String catg_filepath = null;
		if (null != data.getCatgDoc()) {
			String fileSeperator1 = String.valueOf(new Date().getTime()) + "_" + String.valueOf(pd.getBookingId());
			String directory1 = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator1;
			catg_filepath = Utils.saveFileInSysPath(data.getCatgDoc(), directory1);
			logger.info("saved document category file path :: " + catg_filepath);
			if (catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
				logger.info(catg_filepath);
			} else {
				try {
					catg_filepath = get_filePath1(pd.getBookingId());
					logger.info(catg_filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				catg_filepath = get_filePath1(pd.getBookingId());
				logger.info(catg_filepath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (data.getBookingFor().equalsIgnoreCase("others")) {
			if (null == data.getgDoc()) {
				GuestDetailsPojo prevGuest = pd.getList().get(0);
				data.getList().get(0).setGuestDocPath(prevGuest.getGuestDocPath());
			}
		}
		
		if (data.getRoomType().equalsIgnoreCase("C")) {
			frmDt=null;
			toDt=null;
		}

		try {
			jdbcTemplate.update(q1, new Object[] { frmDt, toDt,
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
					pd.getStatus(),conveyanceFrom, data.getPurp(), data.getOffAddr(),
					data.getBookingType() == null ? "0"
							: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
					data.getMob(), data.getEmailId(),conveyanceTo, filepath, // file path
					data.getCatg(), data.getDesg(), data.getRoomType(), catg_filepath, data.getDuringstay(),
					data.getCatgType(), data.getNoOfDays(), data.getNoOfAdults(), data.getNoOfChildren(),
					data.getBookingFor(), pd.getBookingId() });

			//Arrival Details Save or update
			if(pd.getaVchId() != null) {
				System.out.println("111111");
				if(!pd.getaVchId().equals("")) {
					System.out.println("2222222");
					int io=jdbcTemplate.update(q5, new Object[] { data.getTravelModeFr(), arrtm, data.getArrPla(),
							data.getFlNoFr(), arrDt, pd.getBookingId(), pd.getaVchId() });
					System.out.println(io);		
				}
				
			}else {
				if(!data.getArrDt().equals("")) {
					Integer vid = get_seqID("veh_id");
					update_seqID("veh_id", vid);
					String vehId = "G_" + vid.toString();
					jdbcTemplate.update(q2, new Object[] { vehId, pd.getBookingId(), data.getTravelModeFr(), arrtm,
							data.getArrPla(), "0", data.getFlNoFr(), arrDt });
				}
			}

			//Departure Details Save or update
			if(pd.getdVchId() != null) {
				if(!pd.getdVchId().equals("")) {
					jdbcTemplate.update(q6, new Object[] { data.getTravelModeTo(), deptm, data.getDepPla(),
							data.getFlNoTo(), depDt, pd.getBookingId(), pd.getdVchId() });
					}
			}else {
				if (!data.getDepDt().equals("")) {
					Integer vid = get_seqID("veh_id");
					update_seqID("veh_id", vid);
					String vehId = "G_" + vid.toString();
					jdbcTemplate.update(q3, new Object[] { vehId, pd.getBookingId(), data.getTravelModeTo(), deptm,
							data.getDepPla(), "0", data.getFlNoTo(), depDt });
				}
			}

			if (data.getList() != null) {
				String id = "";
				Integer i = 0;
				for (GuestDetailsPojo item : data.getList()) {
					i++;
					logger.info(item);
					if (item.getGuestId().equals("")) {
						Integer gid = get_seqID("guest_id");
						String guestId = "G_" + gid.toString();
						id = id + "'" + guestId + "'";
						update_seqID("guest_id", gid);
						jdbcTemplate.update(q4,
								new Object[] { pd.getBookingId(), guestId, item.getGuestName(), item.getGuestAge(),
										item.getGuestGender(), item.getGuestAddr(), item.getGuestDesignation(),
										item.getGuestDepartment(), item.getGuestMobile(), item.getGuestDocType(),
										item.getGuestDocPath(), "0" });
					} else {
						id = id + "'" + item.getGuestId() + "'";
						jdbcTemplate.update(q7,
								new Object[] { item.getGuestName(), item.getGuestAge(), item.getGuestGender(),
										item.getGuestAddr(), item.getGuestDesignation(), item.getGuestDepartment(),
										item.getGuestMobile(), item.getGuestDocType(), item.getGuestDocPath(),
										pd.getBookingId(), item.getGuestId(), });
					}
					if (i < data.getList().size()) {
						id = id + ",";
					}
				}
				if (!id.isEmpty()) {
					q8 = q8.concat(id + ")");
				}
				jdbcTemplate.update(q8, new Object[] { pd.getBookingId() });
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed");
		}
		status = true;
		return status;
	}

	@Override
	public String updateSplitBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		RoomBookingPojo existing = admingetBookingDetailsById(data.getBookingId());
		data.setExistingBookingId(data.getBookingId());
		data.setBlockId(data.getSplitGHouse());
		String guestIdList = data.getList().stream().map(GuestDetailsPojo::getGuestId)
				.collect(Collectors.joining("', '"));
		int adultCount = (int) data.getList().stream().filter(item -> Integer.parseInt(item.getGuestAge()) > 11)
				.count();
		int childrenCount = (int) data.getList().stream().filter(item -> Integer.parseInt(item.getGuestAge()) <= 11)
				.count();
		if (data.getgDocType() != null && !data.getgDocType().isEmpty()) {
			GuestDetailsPojo gdp = jdbcTemplate.queryForObject(adminQueries.getProperty("getDetailsOfPrimaryGuest"), (rs, rn) -> {
				GuestDetailsPojo g = new GuestDetailsPojo();
				g.setGuestDocPath(rs.getString("guest_doc_path"));
				return g;
			}, data.getBookingId());
			if (gdp != null) {
				data.getList().get(0).setGuestDocPath(gdp.getGuestDocPath());
			}
		}
		if(null == data.getCatgDoc()) {
			data.setCatgType(existing.getCatgType());
			data.setCatgDocName(existing.getCatgDocName());
		}
		if(null == data.getSuppDoc()) {
			data.setSuppDocName(existing.getSuppDocName());
		}
		String reqId = adminaddBookingDetails(data, userId, "split");
		if (reqId != null) {
			try {
				String sql = "DELETE FROM public.guest_details WHERE req_id = '" + data.getBookingId()
						+ "' AND guest_id IN ('" + guestIdList + "')";
				jdbcTemplate.update(sql);
				String updateAdAndCh = adminQueries.getProperty("updateAdultAndChildrenCount");
				jdbcTemplate.update(updateAdAndCh, existing.getNoOfAdults() - adultCount,
						existing.getNoOfChildren() - childrenCount, existing.getBookingId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return reqId;
		} else
			return null;
	}

	public String get_filePath(String p_id) throws Exception {
		String sql = "select file_path from gad_request where req_id=?";
		String path = null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String l_curvalue = null;
				l_curvalue = rs.getString("file_path");
				return l_curvalue;
			}
		}, p_id);

		if (null != users && !users.isEmpty()) {
			path = users.get(0);
		}
		return path;
	}

	public String get_filePath1(String p_id) throws Exception {
		String sql = "select catg_file_path from gad_request where req_id=?";
		String path = null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String l_curvalue = null;
				l_curvalue = rs.getString("catg_file_path");
				return l_curvalue;
			}
		}, p_id);

		if (null != users && !users.isEmpty()) {
			path = users.get(0);
		}
		return path;
	}

	@Override
	public RoomBookingPojo admingetBookingDetailsById(String bookingId) {
		String query = approverQueries.getProperty("getBookingDtlsByIdSQL");
		String gquery = approverQueries.getProperty("getGuestDtlsByBookIdSQL");
		String allotQuery = approverQueries.getProperty("getRoomAllotDtlsByBookIdSQL");
		String modifedViewQuery = approverQueries.getProperty("getRoomModifyDtlsByBookIdSQL");
		String pcanRoomDetailsQuery = approverQueries.getProperty("getPCanFromAllotmentByReqId");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
		logger.info(bookingId);
		List<RoomBookingPojo> list = null;
		try {
			list=	jdbcTemplate.query(query, new RowMapper<RoomBookingPojo>() {
			@Override
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				RoomBookingPojo res = new RoomBookingPojo();
				String fromDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String arrDt = rs.getDate("arrival_date") != null ? sdf.format(rs.getDate("arrival_date")) : null;
				String depDt = rs.getDate("depr_date") != null ? sdf.format(rs.getDate("depr_date")) : null;
				String arrTm = rs.getTime("arrival_time") != null ? stf.format(rs.getTime("arrival_time")) : null;
				String depTm = rs.getTime("depr_time") != null ? stf.format(rs.getTime("depr_time")) : null;
				res.setCityId(rs.getString("gh_id"));
				res.setCityName(rs.getString("gh_name"));
				res.setBlockId(rs.getString("block_id"));
				res.setBlockName(rs.getString("block_name"));
				res.setBookingId(rs.getString("req_id"));
				res.setCanStatus(rs.getString("can_status"));
				res.setRoomType(rs.getString("roomtype"));
				res.setExt(rs.getString("ext"));
//				if ((rs.getString("nor_req") != null ? !rs.getString("nor_req").equals("0") : false)
//						|| (rs.getString("nob_g_req") != null ? !rs.getString("nob_g_req").equals("0") : false)) {
//					res.setBookingType("room");
//				}
//				if (rs.getString("nod_g_req") != null ? !rs.getString("nod_g_req").equals("0") : false) {
//					res.setBookingType("dormitory");
//				}
				if (res.getRoomType().equalsIgnoreCase("R") || res.getRoomType().equalsIgnoreCase("RC")) {
					String room = rs.getString("nor_req") != null ? rs.getString("nor_req") : "";
					if (room.equals("0") || room.equals("")) {
						res.setBookingType("dormitory");
					} else {
						res.setBookingType("room");
					}
				}
				res.setFrmDt(fromDt);
				res.setToDt(toDt);
				res.setNoOfRooms(rs.getString("nor_req"));
				res.setNoOfBeds(rs.getString("nod_g_req"));
				res.setNoOfBedsDormitory(rs.getString("nod_g_req"));
				res.setMob(rs.getString("mobile_no"));
				res.setEmailId(rs.getString("email_id"));
				res.setCatg(rs.getString("category_type"));
				res.setCatgName(rs.getString("category"));
				res.setPurp(rs.getString("purpose"));
				res.setPurpName(rs.getString("item_title"));
				res.setDesg(rs.getString("desg"));
				res.setDesgName(rs.getString("desg_category"));
				res.setOffAddr(rs.getString("address"));
				res.setaVchId(rs.getString("veh_id"));
				res.setTravelModeFr(rs.getString("mode_of_travel"));
				res.setFlNoFr(rs.getString("ftno"));
				res.setArrDt(arrDt);
				res.setArrTm(arrTm);
				res.setArrPla(rs.getString("arrival_place"));
				res.setdVchId(rs.getString("dv_id"));
				res.setTravelModeTo(rs.getString("d_mode_travel"));
				res.setFlNoTo(rs.getString("d_fno"));
				res.setDepDt(depDt);
				res.setDepTm(depTm);
				res.setDepPla(rs.getString("depr_place"));
				res.setStatus(rs.getString("status"));
				res.setUserId(rs.getString("userid"));
				if (rs.getString("status") != null) {
					if (rs.getString("status").equalsIgnoreCase("REQ")) {
						res.setStatusDesc("Not taken up for processing");
					}
					if (rs.getString("status").equalsIgnoreCase("CAN")) {
						res.setStatusDesc("Cancelled");
					}
					if (rs.getString("status").equalsIgnoreCase("MOD")) {
						res.setStatusDesc("Under processing");
					}
					if (rs.getString("status").equalsIgnoreCase("PRO")) {
						res.setStatusDesc("Approved");
					}
					if (rs.getString("status").equalsIgnoreCase("ALO")) {
						res.setStatusDesc("Alloted");
					}
					if (rs.getString("status").equalsIgnoreCase("CIN")) {
						res.setStatusDesc("Check In");
					}
					if (rs.getString("status").equalsIgnoreCase("COUT")) {
						res.setStatusDesc("Check Out");
					}
				}
				String modeOfReq = null;
				if (rs.getString("mode_of_req") != null) {
					modeOfReq = rs.getString("mode_of_req").equalsIgnoreCase("PUB") ? "Public" : null;
				}
				res.setModeOfReq(modeOfReq);
				res.setIsExtension(rs.getString("ext").equalsIgnoreCase("1"));
				res.setExtensionId(rs.getString("existing_req_id"));
				res.setIsArrivalConv(rs.getString("is_veh_req").equalsIgnoreCase("1"));
				res.setIsDepartueConv(rs.getString("is_veh_req1").equalsIgnoreCase("1"));
				res.setSuppDocName(rs.getString("file_path"));
				res.setCatgDocName(rs.getString("catg_file_path"));
				res.setDuringstay(rs.getString("during_stay_conveancy"));
				res.setCatgType(rs.getString("catgtype"));
				res.setNoOfDays(rs.getInt("no_of_days"));
				res.setNoOfAdults(rs.getInt("no_of_adults"));
				res.setNoOfChildren(rs.getInt("no_of_children"));
				res.setBookingFor(rs.getString("booking_for"));
				logger.info(res);
				return res;

			}
		}, bookingId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		List<GuestDetailsPojo> guestList = jdbcTemplate.query(gquery, new RowMapper<GuestDetailsPojo>() {
			@Override
			public GuestDetailsPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				GuestDetailsPojo res = new GuestDetailsPojo();
				res.setGuestId(rs.getString("guest_id"));
				res.setGuestName(rs.getString("name"));
				res.setGuestAge(rs.getString("age"));
				res.setGuestGender(rs.getString("gender"));
				res.setGuestAddr(rs.getString("address"));
				res.setGuestDesignation(rs.getString("designation"));
				res.setGuestDepartment(rs.getString("department"));
				res.setGuestMobile(rs.getString("guest_mobile"));
				res.setGuestDocType(rs.getString("guest_doc_type"));
				res.setGuestDocPath(rs.getString("guest_doc_path"));
				return res;
			}
		}, bookingId);
		logger.info(list);
		if (list.size() == 0) {
			return null;
		}
		RoomBookingPojo roomBookingPojo = list.get(0);
		if (roomBookingPojo.getBookingFor() != null && roomBookingPojo.getBookingFor().equalsIgnoreCase("others")) {
			GuestDetailsPojo guestDetailsPojo = guestList.get(0);
			if (guestDetailsPojo != null) {
				roomBookingPojo.setgMobile(guestDetailsPojo.getGuestMobile());
				roomBookingPojo.setgDocType(guestDetailsPojo.getGuestDocType());
				roomBookingPojo.setgDocName(guestDetailsPojo.getGuestDocPath());
			}
		}
		List<RoomDetails> roomList = new ArrayList<>();
		String status = roomBookingPojo.getStatus();
		if (status.equals("PRO") || status.equals("CIN") || status.equals("COUT") || status.equals("BILL") || status.equals("RECT") || status.equals("CAN") || status.equals("ALO") || status.equals("EXT") || status.equals("PEXT") || status.equals("CAN")) {
			roomList = jdbcTemplate.query(allotQuery, (rs, rownumber) -> {
				RoomDetails res = new RoomDetails();
				res.setFromDate(sdf1.format(rs.getTimestamp("from_date")));
				res.setToDate(sdf1.format(rs.getTimestamp("to_date")));
				res.setRoomNo(rs.getString("room_no"));
				res.setBedNo(rs.getString("room_no"));
				res.setRoomRent(rs.getString("room_rent"));
				res.setFromDate(res.getFromDate() + " - " + res.getToDate());
				return res;
			}, bookingId);
		}
		List<RoomDetails> pcanRoomList = new ArrayList<>();
		if (status.equals("PRO") || status.equals("CIN") || status.equals("COUT") || status.equals("BILL") || status.equals("RECT") || status.equals("CAN") || status.equals("ALO")) {
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			pcanRoomList = jdbcTemplate.query(pcanRoomDetailsQuery, (rs, rownumber) -> {
				RoomDetails res = new RoomDetails();
				res.setFromDate(sdf1.format(rs.getTimestamp("from_date")));
				res.setToDate(sdf1.format(rs.getTimestamp("to_date")));
				res.setRoomNo(rs.getString("room_no"));
				res.setBedNo(rs.getString("room_no"));
				res.setRoomRent(rs.getString("room_rent"));
				res.setFromDate(res.getFromDate() + " - " + res.getToDate());
				String canStatus = rs.getString("can_status");
				String trStatus = rs.getString("status");
				String fromDate = sdf1.format(rs.getTimestamp("from_date"));
				if (canStatus.equalsIgnoreCase("FCAN")) {
					String fullCanQuery = approverQueries.getProperty("getNocByReqId");
					try {
						RoomDetails fullCanNoc = jdbcTemplate.queryForObject(fullCanQuery, (rs1, rownumber1) -> {
							RoomDetails res1 = new RoomDetails();
							res1.setFromDate(sdf1.format(rs1.getTimestamp("from_date")));
							res1.setToDate(sdf1.format(rs1.getTimestamp("to_date")));
							return res1;
						}, bookingId);
                        Date frDt = sdf1.parse(fromDate);
                        Date fullCanFromDate = sdf1.parse(fullCanNoc.getFromDate());
                        if(fullCanFromDate.after(frDt)) {
                            res.setType("NOC");
                            double roomRent = Double.parseDouble(res.getRoomRent());
                            res.setRoomRent(String.valueOf((int)Math.round(roomRent * 0.5)));
                        } else {
                            res.setType(trStatus);
                        }
					} catch (Exception e) {
						res.setType(trStatus);
					}
				} else {
					res.setType(trStatus);
				}
				return res;
			}, bookingId);
		}
		List<RoomDetails> modifiedList = new ArrayList<>();	
		if (status.equals("PRO") || status.equals("CIN")) {	
			modifiedList = jdbcTemplate.query(modifedViewQuery, (rs, rownumber) -> {
				RoomDetails res = new RoomDetails();
				String depTm = rs.getTimestamp("prev_order_date") != null ? sdf.format(rs.getTimestamp("prev_order_date")) : null;
				res.setRoomNo(rs.getString("prev_room_no"));
				res.setBedNo(rs.getString("prev_room_no"));
				res.setRoomRent(rs.getString("prev_room_rent"));
				res.setModifiedRoomNo(rs.getString("room_no"));
				res.setModifiedBedNo(rs.getString("room_no"));
				res.setModifiedRoomRent(rs.getString("room_rent"));
				res.setPrevOrderNo(rs.getString("prev_order_no"));
				res.setPrevOrderDate(depTm);
				res.setReason(rs.getString("subject"));
				return res;
			}, bookingId);
		}
		roomBookingPojo.setList(guestList);
		roomBookingPojo.setRoomDetails(roomList);
		roomBookingPojo.setModifiedDetails(modifiedList);
		roomBookingPojo.setPartialCancelRoomDetails(pcanRoomList);
		System.out.println("guest query "+query);
		return roomBookingPojo;

	}

	@Override
	public DataTableResponse getAllotmentLstDtlsAccToReq(DataTableRequest params, HttpSession session) {
		// Create an object for parameters to pass safely using a Map for named parameters
		Map<String, Object> parameters = new HashMap<>();

		// Define the base queries
		String query = allotmentQueries.getProperty("getAllotmentLstDtlsSQL");
		String query1 = allotmentQueries.getProperty("getAllotmentLstDtlsSQLCount");

		// Initialize the where condition
		StringBuilder whereCondition = new StringBuilder("WHERE 1=1 ");

		// Parse the request parameters
		String reqType = params.getType();
		String cityId = params.getCity();
		String ghId = params.getgHouse();
		String bookingId = params.getBookingId();
		String bookingDate = params.getBookingDate();
		String fromDate = params.getFrmDate();
		String purpose = params.getPurpose();
		String mobNo = params.getMobNo();
		String name = params.getName();
		String category = params.getCategory();
		int start = params.getStart();
		
		String orderBy = "";

		// Current and next date for specific query conditions
		LocalDate sqlCurDt = LocalDate.now();
		LocalDate sqlNextDt = sqlCurDt.plusDays(7);
		
		Date curDt = java.sql.Date.valueOf(sqlCurDt);
		Date  nextDt= java.sql.Date.valueOf(sqlNextDt);

		// Add city ID condition
		if (cityId != null && !cityId.isEmpty()) {
		    whereCondition.append(" AND gh.gh_id = :cityId");
		    parameters.put("cityId", cityId);
		} else {
		    ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
		    String cityIdString = String.join("', '", cityIdList);
		    whereCondition.append(" AND gh.gh_id IN (:cityIds)");
		    parameters.put("cityIds", cityIdList);
		}

		// Add other conditions
		if (ghId != null && !ghId.isEmpty()) {
		    whereCondition.append(" AND gh.block_id = :ghId");
		    parameters.put("ghId", ghId);
		}
		if (bookingId != null && !bookingId.isEmpty()) {
		    whereCondition.append(" AND gh.req_id = :bookingId");
		    parameters.put("bookingId", bookingId);
		}
		if (bookingDate != null && !bookingDate.isEmpty()) {
		    try {
		        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
		        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
		        Date parsedDate = sdfInput.parse(bookingDate);
		        String formattedDate = sdfOutput.format(parsedDate);
		        bookingDate = formattedDate;
		        whereCondition.append(" AND gh.req_date::date = CAST(:bookingDate AS DATE)");
		        parameters.put("bookingDate", bookingDate);
		    } catch (ParseException e) {
		        // Optionally log the error if needed
		        // logger.error("Error parsing booking date: {}", bookingDate, e);
		        throw new RuntimeException("Invalid date format for bookingDate", e);
		    }
		}

		if (fromDate != null && !fromDate.isEmpty()) {
		    try {
		        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
		        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
		        Date parsedDate = sdfInput.parse(fromDate);
		        String formattedDate = sdfOutput.format(parsedDate);
		        fromDate = formattedDate;
		        whereCondition.append(" AND gh.from_date::date = CAST(:fromDate AS DATE)");
		        parameters.put("fromDate", fromDate);
		    } catch (ParseException e) {
		        // Optionally log the error if needed
		        // logger.error("Error parsing fromDate: {}", fromDate, e);
		        throw new RuntimeException("Invalid date format for fromDate", e);
		    }
		}

		if (purpose != null && !purpose.isEmpty()) {
		    whereCondition.append(" AND gh.purpose = :purpose");
		    parameters.put("purpose", purpose);
		}
		if (category != null && !category.isEmpty()) {
		    whereCondition.append(" AND gh.category_type = :category");
		    parameters.put("category", category);
		}
		if (mobNo != null && !mobNo.isEmpty()) {
		    whereCondition.append(" AND gh.mobile_no = :mobNo");
		    parameters.put("mobNo", mobNo);
		}
		if (name != null && !name.isEmpty()) {
		    whereCondition.append(" AND gs.name ILIKE :name");
		    parameters.put("name", "%" + name + "%");
		}

//		// Request Type handling (AL, MA, MM, GH)
		if (reqType.equalsIgnoreCase("AL")) {
		    whereCondition.append(" AND gh.mode_of_req IN ('GAD', 'PUB') AND gh.status = 'REQ' ");
		    orderBy = "ORDER BY gh.req_date DESC";
		} else if (reqType.equalsIgnoreCase("MA")) {
		    whereCondition.append(" AND gh.status = 'REQ' AND gh.mode_of_req IN ('GAD', 'PUB') " +
		            "AND (CASE WHEN gh.from_date IS NOT NULL THEN gh.from_date <= :nextDt AND gh.to_date >= :curDt ELSE 1=1 AND " +
		            "(v1.arrival_time >= :curDt OR v2.arrival_time >= :curDt) END) ");
		    orderBy = "ORDER BY gh.req_date ASC";
		    parameters.put("nextDt", nextDt);
		    parameters.put("curDt", curDt);
		} else if (reqType.equalsIgnoreCase("MM")) {
		    whereCondition.append(" AND gh.mode_of_req IN ('GAD', 'PUB') " +
		            "AND (CASE WHEN gh.from_date IS NOT NULL THEN gh.to_date >= :curDt AND gh.status = 'MOD' ELSE " +
		            "(v1.arrival_time >= :curDt OR v2.arrival_time >= :curDt) AND gh.status = 'ALO' END) ");
		    orderBy = "ORDER BY gh.req_date DESC";
		    parameters.put("curDt", curDt);
		} else if (reqType.equalsIgnoreCase("GH")) {
		    whereCondition.append(" AND gh.status = 'REQ' AND gh.mode_of_req = 'GH' " +
		            "AND (CASE WHEN gh.from_date IS NOT NULL THEN gh.to_date >= :curDt ELSE 1=1 AND " +
		            "(v1.arrival_time >= :curDt OR v2.arrival_time >= :curDt) END) ");
		    orderBy = "ORDER BY gh.req_date DESC";
		    parameters.put("curDt", curDt);
		}

		// Combine query with where condition
		query += whereCondition.toString()+orderBy;
		query1 += whereCondition.toString();
		System.out.println(query);
		

		// Pagination logic
		if (params.getLength() != 0) {
		    query += " LIMIT :length OFFSET :start";
		    parameters.put("length", params.getLength());
		    parameters.put("start", start);
		}

		final String countQuery = query1;
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate =new NamedParameterJdbcTemplate(jdbcTemplate);
		// Asynchronously execute the count query
		CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
		    try {
		        return namedParameterJdbcTemplate.queryForObject(countQuery, parameters, Integer.class);
		    } catch (Exception e) {
		        logger.error("Error executing count query", e);
		        return 0; // Return 0 if there's an error
		    }
		}, executorService);

		final String dataQuery = query;
		// Asynchronously execute the data fetching query
		CompletableFuture<List<BookingResponseModel>> dataFuture = CompletableFuture.supplyAsync(() -> {
		    return namedParameterJdbcTemplate.query(dataQuery, parameters, (rs, rowNum) -> setBookingLstFromResultset(rs));
		}, executorService);

		// Combine both results once both are available
		CompletableFuture<DataTableResponse> responseFuture = countFuture.thenCombine(dataFuture, (count, data) -> {
		    DataTableResponse dt = new DataTableResponse();
		    dt.setDraw(params.getDraw());
		    dt.setRecordsTotal(count);
		    dt.setRecordsFiltered(count);
		    dt.setData(data);
		    return dt;
		});

		DataTableResponse dt = null;
		try {
		    dt = responseFuture.get(); // Wait for both tasks to complete and get the final result
		} catch (InterruptedException | ExecutionException e) {
//		    logger.error("Error processing queries", e);
		} finally {
		    executorService.shutdown();  // Shutdown the executor service
		}

		return dt;

	}



	private BookingResponseModel setBookingLstFromResultset(ResultSet rs) throws SQLException {
		BookingResponseModel data = new BookingResponseModel();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			String bookingDt = rs.getDate(2) != null ? sdf.format(rs.getTimestamp(2)) : null;
			String frDt = rs.getDate(4) != null ? sdf.format(rs.getTimestamp(4)) : null;
			String toDt = rs.getDate(5) != null ? sdf.format(rs.getTimestamp(5)) : null;

			data.setBookingId(rs.getString(1));
			data.setBookingDt(bookingDt);
			data.setName(rs.getString(3));
			data.setFromDate(frDt);
			data.setToDate(toDt);
			data.setCityId(rs.getString(6));
			data.setCityName(rs.getString(7));
			data.setGhId(rs.getString(8));
			data.setGhName(rs.getString(9));
			data.setPurposeId(rs.getString(10));
			data.setPurpose(rs.getString(11));
			String category_Desc = "";
			String category = rs.getString(12);
			if (category != null) {
			    if (category.equals("C1")) {
			        category_Desc = "Category 1";
			    }
			    if (category.equals("C2")) {
			        category_Desc = "Category 2";
			    }
			    if (category.equals("C3")) {
			        category_Desc = "Category 3";
			    }
			    if (category.equals("C4")) {
			        category_Desc = "Category 4";
			    }
			} else {
			    category_Desc = "Unknown Category";
			}
			data.setCategory(rs.getString(13));
			data.setDesgId(rs.getString(14));
			data.setDesg(rs.getString(15));
			data.setMobile(rs.getString(16));
			data.setEmail(rs.getString(17));
			String status = rs.getString(18);
			String roomType = rs.getString(20);
			String roomBed = rs.getString("nod_g_req");
			if(roomType.equalsIgnoreCase("R") || roomType.equalsIgnoreCase("RC")) {
				if(roomBed == null || roomBed.equalsIgnoreCase("0") || roomBed.equalsIgnoreCase("")) {
					data.setRoomOrBed("Room");
				}else {
					data.setRoomOrBed("Bed");
				}
			}
			String cancelledBy = rs.getString(23);
			String cancelByWho = "";
			String roomTypeDesc = "";
			String status_desc = "";
			if(roomType != null) {
				if (roomType.equals("R")) {
					roomTypeDesc = "Room";
				}
				if (roomType.equals("RC")) {
					roomTypeDesc = "Room with Conveyance";
				}
				if (roomType.equals("C")) {
					roomTypeDesc = "Conveyance";
				}
				if (roomType.equals("CON")) {
					roomTypeDesc = "Confrence/Open Terrace";
					data.setName(rs.getString("confGst"));
				}
			}				
			if (status.equals("REQ")) {
				status_desc = " Not taken up for processing";
			}
			if (status.equals("CAN") || (status.equals("PRO") && rs.getString("can_status").equals("PCAN")) || (status.equals("CIN") && rs.getString("can_status").equals("PCAN"))) {
				status_desc = "Cancelled";
				if(cancelledBy != null) {
					cancelByWho="Guest";			
				}else {
					cancelByWho="Admin";
				}
			}
			if (status.equalsIgnoreCase("MOD")) {
				status_desc = "Under processing";
			}
			if (status.equalsIgnoreCase("PRO")) {
				status_desc = "Approved";
			}
			if (status.equalsIgnoreCase("ALO")) {
				status_desc = "Allotted";
			}
			if (status.equals("CIN")) {
				status_desc = "Check In";
			}
			if (status.equals("PCIN")) {
				status_desc = "Partial CheckIn";
			}
			if (status.equals("COUT")) {
				status_desc = "Check Out";
			}
			if (status.equals("RCAN")) {
				status_desc = "Cancel Request";
			}
			if (status.equals("PEXT")) {
				status_desc = "Request For Extension";
			}
			if (status.equals("EXT")) {
				status_desc = "Extension";
			}
			if (status.equals("BILL")) {
				status_desc = "Billing";
			}
			if (status.equals("RECT")) {
				status_desc = "Payment Successfully";
			}
			
			data.setStatus(status);
			data.setStatusDesc(status_desc);
			data.setTypeOfRoom(roomTypeDesc);
			data.setCatgId(category_Desc);
			data.setCnclBy(cancelByWho);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<RoomBookingPojo> getGuestDetailsByReqid(String reqid) {

		String sql = adminQueries.getProperty("getModeratorDetailsByReqidSQL");

		// List<GuestDetailsPojo> gstdtlRres = new ArrayList<GuestDetailsPojo>()
		// ;

		List<RoomBookingPojo> alldetails = jdbcTemplate.query(sql, new RowMapper<RoomBookingPojo>() {
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {

				RoomBookingPojo res = new RoomBookingPojo();
				GuestDetailsPojo gres = new GuestDetailsPojo();

				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");

				String fromDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
				String arrDt = rs.getDate("arrival_date") != null ? sdf.format(rs.getDate("arrival_date")) : null;
				String depDt = rs.getDate("depr_date") != null ? sdf.format(rs.getDate("depr_date")) : null;
				String arrTm = rs.getTime("arrival_time") != null ? stf.format(rs.getTime("arrival_time")) : null;
				String depTm = rs.getTime("depr_time") != null ? stf.format(rs.getTime("depr_time")) : null;

				res.setCityId(rs.getString("gh_id"));
				res.setBlockId(rs.getString("block_id"));
//				res.setCityName(rs.getString("gh_name"));
//				res.setBlockName(rs.getString("block_name"));
				res.setBookingId(rs.getString("req_id"));
				gres.setGuestId(rs.getString("guest_id"));
				gres.setGuestName(rs.getString("name"));
				gres.setGuestAge(rs.getString("age"));
				res.setSingle(gres);
				res.setPurp(rs.getString("purpose"));
				res.setPurpName(rs.getString("purp_name"));
				res.setNoOfRooms(rs.getString("nor_req"));
				res.setNoOfBeds(rs.getString("nob_g_req"));
				// res.setRoomType(rs.getString("roomtype"));
				if ((rs.getString("nor_req") != null ? !rs.getString("nor_req").equals("0") : false)
						|| (rs.getString("nob_g_req") != null ? !rs.getString("nob_g_req").equals("0") : false)) {
					res.setBookingType("room");
				} else {
					res.setBookingType("dormitory");
				}
				// if (rs.getString("nod_g_req") != null ?
				// !rs.getString("nod_g_req").equals("0") : false) {
				// res.setBookingType("dormitory");
				// }
				res.setSuppDocName(rs.getString("file_path"));
				res.setResponseSupDoc(rs.getString("doc_path"));
				res.setStatus(rs.getString("status"));
//				if (rs.getString("status") != null) {
//					if (rs.getString("status").equalsIgnoreCase("REQ")) {
//						res.setStatusDesc("REQ");
//					}
//
//					if (rs.getString("status").equalsIgnoreCase("ALO")) {
//						res.setStatusDesc("Alloted");
//					}
//				}
				String modeOfReq = null;
				if (rs.getString("mode_of_req") != null) {
					modeOfReq = rs.getString("mode_of_req").equalsIgnoreCase("PUB") ? "Public" : null;
				}
				res.setModeOfReq(modeOfReq);
				res.setIsArrivalConv(rs.getString("is_veh_req").equalsIgnoreCase("1"));
				res.setDesg(rs.getString("desg"));
				res.setDesgName(rs.getString("desg_category"));
				res.setFrmDt(fromDt);
				res.setToDt(toDt);
				// res.setNoOfBedsDormitory(rs.getString("nod_g_req"));
				// res.setMob(rs.getString("mobile_no"));
				// res.setEmailId(rs.getString("email_id"));
				// res.setCatg(rs.getString("category_type"));
				// res.setCatgName(rs.getString("quota_desc"));
				// res.setPurpName(rs.getString("item_title"));
				// res.setOffAddr(rs.getString("address"));
				// res.setaVchId(rs.getString("veh_id"));
				res.setArrDt(arrDt);
				res.setArrTm(arrTm);
				res.setArrPla(rs.getString("arrival_place"));
				if (rs.getString("mode_of_travel") != null) {
					if (rs.getString("mode_of_travel") == "T") {
						res.setTravelModeFr("Train");
					} else {
						res.setTravelModeFr("Flight");
					}
				}
				res.setFlNoFr(rs.getString("ftno"));
				res.setIsDepartueConv(rs.getString("is_veh_req1").equalsIgnoreCase("1"));
				res.setDepDt(depDt);
				res.setDepTm(depTm);
				res.setDepPla(rs.getString("depr_plc"));
				if (rs.getString("depr_mode_of_travel") != null) {
					if (rs.getString("depr_mode_of_travel") == "T") {
						res.setTravelModeTo("Train");
					} else {
						res.setTravelModeTo("Flight");
					}
				}
				res.setFlNoTo(rs.getString("depr_ftno"));
				// res.setdVchId(rs.getString("dv_id"));
				// res.setIsExtension(rs.getString("ext").equalsIgnoreCase("1"));
				// res.setExtensionId(rs.getString("existing_req_id"));

				return res;
			}
		}, reqid);
		return alldetails;
	}

	@Override
	public List<RoomCategory> getroomCategory() {

		String sql = adminQueries.getProperty("getRoomCategorrySQL");
		List<RoomCategory> alldetails = jdbcTemplate.query(sql, new RowMapper<RoomCategory>() {
			public RoomCategory mapRow(ResultSet rs, int rownumber) throws SQLException {

				RoomCategory response = new RoomCategory();
				response.setQuota_type(rs.getString(1));
				response.setQuota_desc(rs.getString(2));
				return response;
			}
		});
		return alldetails;
	}

	@Override
	public List<RoomCategory> getroomStatus(HttpServletRequest request) {

		final String gh_id = request.getParameter("gh_id");
		final String ctype = request.getParameter("ctype");
		final String fromdate = request.getParameter("fromdate");
		final String todate = request.getParameter("todate");
		final String reqId = request.getParameter("reqId");

		logger.info(reqId);
		String sql = adminQueries.getProperty("getRoomStatusSQL");
		List<RoomCategory> alldetails = jdbcTemplate.query(sql, new RowMapper<RoomCategory>() {
			public RoomCategory mapRow(ResultSet rs, int rownumber) throws SQLException {

				RoomCategory response = new RoomCategory();
				String statusrooms = "";
				String noofroom = "";
				String noofbl = "";
				String noofbg = "";
				String noofdl = "";
				String noofdg = "";
				String block_id = rs.getString(1);
				response.setBlock_id(block_id);
				response.setBock_name(rs.getString(2));
				response.setGh_id(rs.getString(3));
				response.setQuota_type(rs.getString(4));
				response.setNo_rooms(rs.getString(5));
				response.setMax_beds(rs.getString(6));
				response.setDor_g(rs.getString(7));
				response.setDor_l(rs.getString(8));
				try {
					Integer rooms = selectDBrooms(block_id, gh_id, ctype);
					Integer beds = selectDBbeds(block_id, gh_id, ctype);
					Integer dorg = selectDBdorGents(block_id, gh_id, ctype);
					Integer dorl = selectDBdorLadies(block_id, gh_id, ctype);
					logger.info(rooms);
					logger.info(beds);
					Integer occ_rooms = 0;
					Integer occ_GB = 0;
					Integer occ_LB = 0;
					Integer occ_DG = 0;
					Integer occ_DL = 0;
					occ_rooms = selectDBoccupied(gh_id, block_id, ctype, "R", fromdate, todate);
					occ_GB = selectDBoccupied(gh_id, block_id, ctype, "BG", fromdate, todate);
					occ_LB = selectDBoccupied(gh_id, block_id, ctype, "BL", fromdate, todate);
					occ_DG = selectDBoccupied(gh_id, block_id, ctype, "DG", fromdate, todate);
					occ_DL = selectDBoccupied(gh_id, block_id, ctype, "DL", fromdate, todate);

					logger.info(occ_rooms);
					logger.info(occ_GB);
					logger.info(occ_LB);
					if (rooms > 0 || beds > 0) {
						double occu_rooms = rooms - (Double.valueOf(occ_rooms)
								+ Math.ceil(Double.valueOf(occ_GB) / beds) + Math.ceil(Double.valueOf(occ_LB) / beds));
						statusrooms = (int) occu_rooms + "/" + rooms + "-Rooms";

					} else if (dorg > 0 || dorl > 0) {
						double occu_rooms = dorg - (Double.valueOf(occ_DG));
						statusrooms = (int) occu_rooms + "/" + dorg + "-DG&";

						double occu_roomsl = dorl - (Double.valueOf(occ_DL));
						statusrooms = statusrooms + (int) occu_roomsl + "/" + dorl + "-DL";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setStatus(statusrooms);
				try {
					noofroom = modroom(reqId, block_id).toString();
					logger.info(noofroom);
					noofbl = modladies(reqId, block_id).toString();
					noofbg = modgents(reqId, block_id).toString();
					noofdl = moddorladies(reqId, block_id).toString();
					noofdg = moddorgents(reqId, block_id).toString();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setNoofrooms(noofroom);
				response.setNoofbg(noofbg);
				response.setNoofbl(noofbl);
				response.setNoofdg(noofdg);
				response.setNoofdl(noofdl);
				return response;
			}

		}, ctype, gh_id);

		return alldetails;
	}

	public Integer selectDBrooms(String block_id, String gh_id, String quota_type) throws Exception {
		final String sql = "select nu_of_rooms from m_quota_block where block_id='" + block_id + "' and gh_id='" + gh_id
				+ "' and quota_type='" + quota_type + "'";
		Integer noOfrooms = 0;

		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer noOfrooms = 0;
				noOfrooms = rs.getInt("nu_of_rooms");
				logger.info(sql);
				return noOfrooms;
			}
		});
		if (null != users && !users.isEmpty()) {
			noOfrooms = users.get(0);
		}
		return noOfrooms;
	}

	public Integer selectDBbeds(String block_id, String gh_id, String quota_type) throws Exception {
		String sql = "select max_beds from m_quota_block where block_id=? and gh_id=? and quota_type=?";
		Integer noOfbeds = 0;
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer noOfbeds = 0;
				noOfbeds = rs.getInt("max_beds");
				return noOfbeds;
			}
		}, block_id, gh_id, quota_type);
		if (null != users && !users.isEmpty()) {
			noOfbeds = users.get(0);
		}
		return noOfbeds;
	}

	public Integer selectDBdorGents(String block_id, String gh_id, String quota_type) throws Exception {
		String sql = "select dormitory_gents from m_quota_block where block_id=? and gh_id=? and quota_type=?";
		Integer noOfdorg = 0;
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer noOfdorg = 0;
				noOfdorg = rs.getInt("dormitory_gents");
				return noOfdorg;
			}
		}, block_id, gh_id, quota_type);
		if (null != users && !users.isEmpty()) {
			noOfdorg = users.get(0);
		}
		return noOfdorg;
	}

	public Integer selectDBdorLadies(String block_id, String gh_id, String quota_type) throws Exception {
		final String sql = "select dormitory_ladies from m_quota_block where block_id=? and gh_id=? and quota_type=?";
		Integer noOfdorl = 0;
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer noOfdorl = 0;
				noOfdorl = rs.getInt("dormitory_ladies");
				logger.info(sql);
				return noOfdorl;
			}
		}, block_id, gh_id, quota_type);

		if (null != users && !users.isEmpty()) {
			noOfdorl = users.get(0);
		}
		return noOfdorl;
	}

	public Integer selectDBoccupied(String gh_id, String block_id, String quota_type, String type, String checkindate,
			String checkoutdate) throws Exception {
		final String sql = "select sum(total_number) from reservation_main_table where gh_id='" + gh_id
				+ "' and block_id='" + block_id + "' and quota_type='" + quota_type + "' and type='" + type
				+ "' and status!='CAN' and (checkin_date between to_timestamp('" + checkindate
				+ "','yyyy-MM-dd HH24:MI:SS') and to_timestamp('" + checkoutdate
				+ "','yyyy-MM-dd HH24:MI:SS') or checkout_date between to_timestamp('" + checkindate
				+ "','yyyy-MM-dd HH24:MI:SS') and to_timestamp('" + checkoutdate
				+ "','yyyy-MM-dd HH24:MI:SS') or (checkin_date<to_timestamp('" + checkindate
				+ "','yyyy-MM-dd HH24:MI:SS') and checkout_date>to_timestamp('" + checkoutdate
				+ "','yyyy-MM-dd HH24:MI:SS')))";
		Integer sum = 0;
		try {
			List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
				@Override

				public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
					Integer sum = 0;
					sum = rs.getInt("sum");
					logger.info(sum);

					return sum;
				}
			});
			if (null != users && !users.isEmpty()) {
				sum = users.get(0);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return sum;
	}

	public boolean adminmoderationDetails(ModerationPojo data, String id) {
		List<RoomBookingPojo> md = getGuestDetailsByReqid(id);
		RoomBookingPojo md1 = md.get(0);
		String q1 = adminQueries.getProperty("getmoderationSQL");
		String q2 = adminQueries.getProperty("getmoderationRoomSQL");
		String q3 = adminQueries.getProperty("getupdatestatusinGadRequest");
		String q4 = adminQueries.getProperty("updateconveancyinGadRequest");
		logger.info(data);
		logger.info(md);

		Integer id1 = 0;
		try {
			id1 = get_seqID("mod_id");
			update_seqID("mod_id", id1);
			logger.info(id1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mod_id = id1.toString();
		jdbcTemplate.update(q1, new Object[] { mod_id, md1.getBookingId(), data.getVc_id(), data.getQuota_type(),
				data.getIs_vehicle_req(), data.getProcess_id(), "MOD", md1.getCityId(), data.getIs_vehicle_req(), });
		jdbcTemplate.update(q3, new Object[] { "MOD", md1.getBookingId(), });
		jdbcTemplate.update(q4, new Object[] { data.getIs_vehicle_req(), data.getIs_vehicle_req(), md1.getCityId(),
				md1.getBookingId() });

		String rooms = data.getRooms();
		String[] arrSplit = rooms.split(",");
		for (int i = 0; i < arrSplit.length; i++) {
			String rooms1 = arrSplit[i];
			logger.info(rooms1);
			for (int j = 0; j < Integer.parseInt(rooms1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q2, new Object[] { mrr_id, mod_id, md1.getBookingId(), md1.getBlockId(), 1, "R",
						md1.getCityId(), });
			}
		}
		String bg = data.getBg();
		String[] arrSplit1 = bg.split(",");
		for (int i = 0; i < arrSplit1.length; i++) {
			String bg1 = arrSplit1[i];

			for (int j = 0; j < Integer.parseInt(bg1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q2, new Object[] { mrr_id, mod_id, md1.getBookingId(), md1.getBlockId(), 1, "BG",
						md1.getCityId(), });
			}
		}
		String bl = data.getBl();
		String[] arrSplit2 = bl.split(",");
		for (int i = 0; i < arrSplit2.length; i++) {
			String bl1 = arrSplit2[i];
			for (int j = 0; j < Integer.parseInt(bl1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q2, new Object[] { mrr_id, mod_id, md1.getBookingId(), md1.getBlockId(), 1, "BL",
						md1.getCityId(), });
			}
		}
		String dg = data.getDg();
		String[] arrSplit3 = dg.split(",");
		for (int i = 0; i < arrSplit3.length; i++) {
			String dg1 = arrSplit3[i];
			for (int j = 0; j < Integer.parseInt(dg1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q2, new Object[] { mrr_id, mod_id, md1.getBookingId(), md1.getBlockId(), 1, "DG",
						md1.getCityId(), });
			}
		}
		String dl = data.getDl();
		String[] arrSplit4 = dl.split(",");
		for (int i = 0; i < arrSplit4.length; i++) {
			String dl1 = arrSplit4[i];
			for (int j = 0; i < Integer.parseInt(dl1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q2, new Object[] { mrr_id, mod_id, md1.getBookingId(), md1.getBlockId(), 1, "DL",
						md1.getCityId(), });
			}
		}
		return true;
	}

	@Override
	public List<RoomBookingPojo> getmoderationDetailsByReqid(String reqid) {
		logger.info(reqid);
		logger.info("moderation");
		String sql = adminQueries.getProperty("editmoderationClickquery");
		List<RoomBookingPojo> alldetailss = jdbcTemplate.query(sql, new RowMapper<RoomBookingPojo>() {
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				RoomBookingPojo res = new RoomBookingPojo();
				GuestDetailsPojo gres = new GuestDetailsPojo();
				final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
				String fromDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
				String arrDt = rs.getDate("arrival_date") != null ? sdf.format(rs.getDate("arrival_date")) : null;
				String depDt = rs.getDate("depr_date") != null ? sdf.format(rs.getDate("depr_date")) : null;
				String arrTm = rs.getTime("arrival_time") != null ? stf.format(rs.getTime("arrival_time")) : null;
				String depTm = rs.getTime("depr_time") != null ? stf.format(rs.getTime("depr_time")) : null;
				res.setModId(rs.getString("mod_id"));
				String ctype = rs.getString("quota_type");
				String cdesc = null;
				try {
					cdesc = QuatoDesc(ctype);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				res.setQuotatype(ctype);
				res.setQuotadesc(cdesc);
				res.setCityId(rs.getString("gh_id"));
				res.setBlockId(rs.getString("block_id"));
				res.setCityName(rs.getString("gh_name"));
				// res.setBlockName(rs.getString("block_name"));
				res.setBookingId(rs.getString("req_id"));
				gres.setGuestId(rs.getString("guest_id"));
				gres.setGuestName(rs.getString("name"));
				gres.setGuestAge(rs.getString("age"));
				res.setSingle(gres);
				res.setPurp(rs.getString("purpose"));
				res.setPurpName(rs.getString("purp_name"));
				res.setNoOfRooms(rs.getString("nor_req"));
				res.setNoOfBeds(rs.getString("nob_g_req"));
				res.setRoomType(rs.getString("roomtype"));
				if ((rs.getString("nor_req") != null ? !rs.getString("nor_req").equals("0") : false)
						|| (rs.getString("nob_g_req") != null ? !rs.getString("nob_g_req").equals("0") : false)) {
					res.setBookingType("room");
				} else {
					res.setBookingType("dormitory");
				}
				// if (rs.getString("nod_g_req") != null ?
				// !rs.getString("nod_g_req").equals("0") : false) {
				// res.setBookingType("dormitory");
				// }
				res.setSuppDocName(rs.getString("file_path"));
				res.setResponseSupDoc(rs.getString("doc_path"));
				res.setStatus(rs.getString("status"));
				if (rs.getString("status") != null) {
					if (rs.getString("status").equalsIgnoreCase("REQ")) {
						res.setStatusDesc("REQ");
					}

					if (rs.getString("status").equalsIgnoreCase("ALO")) {
						res.setStatusDesc("Alloted");
					}
				}
				String modeOfReq = null;
				if (rs.getString("mode_of_req") != null) {
					modeOfReq = rs.getString("mode_of_req").equalsIgnoreCase("PUB") ? "Public" : null;
				}
				res.setModeOfReq(modeOfReq);
				res.setIsArrivalConv(rs.getString("is_veh_req").equalsIgnoreCase("1"));
				res.setDesg(rs.getString("desg"));
				res.setDesgName(rs.getString("desg_category"));
				res.setFrmDt(fromDt);
				res.setToDt(toDt);
				// res.setNoOfBedsDormitory(rs.getString("nod_g_req"));
				// res.setMob(rs.getString("mobile_no"));
				// res.setEmailId(rs.getString("email_id"));
//				 res.setCatg(rs.getString("category_type"));
//				 res.setCatgName(rs.getString("quota_desc"));
//				 res.setPurpName(rs.getString("item_title"));
				// res.setOffAddr(rs.getString("address"));
				// res.setaVchId(rs.getString("veh_id"));
				res.setArrDt(arrDt);
				res.setArrTm(arrTm);
				res.setArrPla(rs.getString("arrival_place"));
				if (rs.getString("mode_of_travel") != null) {
					if (rs.getString("mode_of_travel") == "T") {
						res.setTravelModeFr("Train");
					} else {
						res.setTravelModeFr("Flight");
					}
				}
				res.setFlNoFr(rs.getString("ftno"));
				res.setIsDepartueConv(rs.getString("is_veh_req1").equalsIgnoreCase("1"));
				res.setDepDt(depDt);
				res.setDepTm(depTm);
				res.setDepPla(rs.getString("depr_plc"));
				if (rs.getString("depr_mode_of_travel") != null) {
					if (rs.getString("depr_mode_of_travel") == "T") {
						res.setTravelModeTo("Train");
					} else {
						res.setTravelModeTo("Flight");
					}
				}
				res.setFlNoTo(rs.getString("depr_ftno"));
				// res.setdVchId(rs.getString("dv_id"));
				// res.setIsExtension(rs.getString("ext").equalsIgnoreCase("1"));
				// res.setExtensionId(rs.getString("existing_req_id"));

				return res;
			}
		}, reqid);
		return alldetailss;
	}

	public String QuatoDesc(String quota_type) throws Exception {
		String sql = "select quota_desc from quota where quota_type=?";
		String QuotaDesc = null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String QuotaDesc = null;
				QuotaDesc = rs.getString("quota_desc");

				return QuotaDesc;
			}
		}, quota_type);
		if (null != users && !users.isEmpty()) {
			QuotaDesc = users.get(0);
		}
		return QuotaDesc;
	}

	public Integer modroom(String req_id, String block_id) throws Exception {
		Integer countroom = 0;
		String room = "select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='R' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countroom = 0;
				countroom = rs.getInt("count");
				logger.info(countroom);
				return countroom;
			}
		}, req_id, req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countroom = users.get(0);
		}
		return countroom;
	}

	public Integer modladies(String req_id, String block_id) throws Exception {
		Integer countladies = 0;
		String room1 = "select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='BL' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room1, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countladies = 0;
				countladies = rs.getInt("count");
				return countladies;
			}
		}, req_id, req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countladies = users.get(0);
		}
		return countladies;
	}

	public Integer modgents(String req_id, String block_id) throws Exception {
		Integer countgents = 0;
		String room2 = "select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='BG' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room2, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countgents = 0;
				countgents = rs.getInt("count");
				return countgents;
			}
		}, req_id, req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countgents = users.get(0);
		}
		return countgents;
	}

	public Integer moddorladies(String req_id, String block_id) throws Exception {
		Integer countmodladies = 0;
		String room3 = "select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='DL' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room3, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countmodladies = 0;
				countmodladies = rs.getInt("count");
				return countmodladies;
			}
		}, req_id, req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countmodladies = users.get(0);
		}
		return countmodladies;
	}

	public Integer moddorgents(String req_id, String block_id) throws Exception {
		Integer countmodgents = 0;
		String room4 = "select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='DG' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room4, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countmodgents = 0;
				countmodgents = rs.getInt("count");
				return countmodgents;
			}
		}, req_id, req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countmodgents = users.get(0);
		}
		return countmodgents;
	}

	public boolean updateModerationDetails(ModerationPojo data, String id) {
		List<RoomBookingPojo> md = getmoderationDetailsByReqid(id);
		RoomBookingPojo md1 = md.get(0);
		String q1 = adminQueries.getProperty("UpdatemoderationSQL");
		String q2 = adminQueries.getProperty("deletemoderationRoomSQL");
		String q3 = adminQueries.getProperty("updateconveancyinGadRequest");
		String q4 = adminQueries.getProperty("updateconveancyinmodreq");
		String q5 = adminQueries.getProperty("getmoderationRoomSQL");
		String q6 = adminQueries.getProperty("updateblockidingadRequest");

		logger.info(data);
		logger.info(md);
		jdbcTemplate.update(q1, new Object[] { data.getVc_id(), data.getQuota_type(), md1.getBookingId() });
		jdbcTemplate.update(q2, new Object[] {

				md1.getBookingId(), md1.getModId(), md1.getCityId() });

		jdbcTemplate.update(q3, new Object[] { data.getIs_vehicle_req(), data.getIs_vehicle_req(), md1.getCityId(),
				md1.getBookingId() });
		jdbcTemplate.update(q4,
				new Object[] { data.getIs_vehicle_req(), data.getIs_vehicle_req(), md1.getBookingId() });

//		  	String block_id=data.getBlock_id();
//		  	String[] blockId=block_id.split(",");
//		  	for (int ii=0; ii < blockId.length; ii++)
//		  	{
//		  	   String blockId1=blockId[ii];
//		  	   logger.info("blockid"+blockId1);
		String rooms = data.getRooms();
		String[] arrSplit = rooms.split(",");
		for (int i = 0; i < arrSplit.length; i++) {
			String rooms1 = arrSplit[i];
			for (int j = 0; j < Integer.parseInt(rooms1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q5, new Object[] { mrr_id, md1.getModId(), md1.getBookingId(), md1.getBlockId(), 1,
						"R", md1.getCityId(), });
			}
		}

		String bg = data.getBg();
		String[] arrSplit1 = bg.split(",");
		for (int i = 0; i < arrSplit1.length; i++) {
			String bg1 = arrSplit1[i];
			for (int j = 0; j < Integer.parseInt(bg1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q5, new Object[] { mrr_id, md1.getModId(), md1.getBookingId(), md1.getBlockId(), 1,
						"BG", md1.getCityId(), });
			}
		}
		String bl = data.getBl();
		String[] arrSplit2 = bl.split(",");
		for (int i = 0; i < arrSplit2.length; i++) {
			String bl1 = arrSplit2[i];
			for (int j = 0; j < Integer.parseInt(bl1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q5, new Object[] { mrr_id, md1.getModId(), md1.getBookingId(), md1.getBlockId(), 1,
						"BL", md1.getCityId(), });
			}
		}
		String dg = data.getDg();
		String[] arrSplit3 = dg.split(",");
		for (int i = 0; i < arrSplit3.length; i++) {
			String dg1 = arrSplit3[i];
			for (int j = 0; j < Integer.parseInt(dg1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q5, new Object[] { mrr_id, md1.getModId(), md1.getBookingId(), md1.getBlockId(), 1,
						"DG", md1.getCityId(), });
			}
		}

		String dl = data.getDl();
		String[] arrSplit4 = dl.split(",");
		for (int i = 0; i < arrSplit4.length; i++) {
			String dl1 = arrSplit4[i];
			for (int j = 0; j < Integer.parseInt(dl1); j++) {
				Integer id2 = 0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q5, new Object[] { mrr_id, md1.getModId(), md1.getBookingId(), md1.getBlockId(), 1,
						"DL", md1.getCityId(), });
			}
		}

		jdbcTemplate.update(q6, new Object[] { md1.getBlockId(), md1.getBookingId() });
		return true;
	}

	@Override
	public List<ProcessModeration> getallProcessApplicationList(HttpServletRequest request) {
		List<ProcessModeration> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			cityName = request.getParameter("cityName");
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			guesthouseName = request.getParameter("guesthouseName");
		if (cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("ProcessallList");
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration response = new ProcessModeration();
					Date bookDate = rs.getDate("req_date") != null ? rs.getTimestamp("req_date") : null;
					Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
					Date ToDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
					response.setBlockId(rs.getString(1));
					response.setBookingId(rs.getString(2));
					response.setStatus(rs.getString(3));
					response.setVcId(rs.getString(4));
					response.setName(rs.getString("name"));
					response.setReqDate(sdf1.format(bookDate));
					String category_Desc = "";
					String categoryType = rs.getString(6);
					if (categoryType.equals("C1")) {
						category_Desc = "Category 1";
					}
					if (categoryType.equals("C2")) {
						category_Desc = "Category 2";
					}
					if (categoryType.equals("C3")) {
						category_Desc = "Category 3";
					}
					if (categoryType.equals("C4")) {
						category_Desc = "Category 4";
					}
					response.setQuotatype(category_Desc);
					response.setType(rs.getString(7));
					response.setFrmDt(sdf1.format(fromDate));
					response.setToDt(sdf1.format(ToDate));
					return response;
				}

			});

		}
		if (!cityName.equals("") && !guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("ProcessallListwithGHouseAndCity");

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {

				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration response = new ProcessModeration();
					Date bookDate = rs.getDate("req_date") != null ? rs.getTimestamp("req_date") : null;
					Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
					Date ToDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
					response.setBlockId(rs.getString(1));
					response.setBookingId(rs.getString(2));
					response.setStatus(rs.getString(3));
					response.setVcId(rs.getString(4));
					response.setReqDate(sdf1.format(bookDate));
					response.setName(rs.getString("name"));
					String category_Desc = "";
					String categoryType = rs.getString(6);
					if (categoryType.equals("C1")) {
						category_Desc = "Category 1";
					}
					if (categoryType.equals("C2")) {
						category_Desc = "Category 2";
					}
					if (categoryType.equals("C3")) {
						category_Desc = "Category 3";
					}
					if (categoryType.equals("C4")) {
						category_Desc = "Category 4";
					}
					response.setQuotatype(category_Desc);
					//response.setQuotatype(rs.getString(6));
					response.setType(rs.getString(7));
					response.setFrmDt(sdf1.format(fromDate));
					response.setToDt(sdf1.format(ToDate));
					return response;
				}

			}, cityName, guesthouseName);
		}
		return alldetailsValue;
	}

	@Transactional
	public boolean adminsaveProcessTable(String id, HttpServletRequest request) {
		final String userId = id;
		final String ghId = request.getParameter("cityId");
		final String blockId = request.getParameter("guesthouseId");
		final String selected = request.getParameter("selected");
		String sign = request.getParameter("sign");
		String signature = "";
		String sign_desig = "";
		List<ProcessModeration> alldetailsValue = null;
		ProcessModeration md = null;
		String q1 = adminQueries.getProperty("saveProcesstable");
		String q2 = adminQueries.getProperty("getCurrentModTable");
		String q3 = adminQueries.getProperty("saveinReservationMainTable");
		String q4 = adminQueries.getProperty("updateStatusModReq");
		String q5 = adminQueries.getProperty("canModRequestForModRoomTbl");
		String q6 = adminQueries.getProperty("getCanListFromGADReq");
		String q7 = adminQueries.getProperty("createCancelReservation");
		String q8 = approverQueries.getProperty("updateCanStatusByReqIdSaveAsDraft");
		String q9 = approverQueries.getProperty("getAllModifiedDataForApprove");
		String q10 = adminQueries.getProperty("insertModifyReservation");
		String q11 = approverQueries.getProperty("getAllotIdsForModify");
		String q12 = adminQueries.getProperty("updateAllotmentModifyStatus");
		String q13 = approverQueries.getProperty("updatePartialCancelRequestByBookingId");
		String q14 = adminQueries.getProperty("ConveyanceListWithGHouseAndCitySQL");
		String q15 = adminQueries.getProperty("getAllConferenceListForApprove");

		if (sign.equals("1")) {
			try {
				signature = getPropValue("gad_secretary");
				sign_desig = getPropValue("gad_designation_sec");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sign.equals("2")) {
			try {
				signature = getPropValue("gad_additional");
				sign_desig = getPropValue("gad_additional_desg");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sign.equals("3")) {
			try {
				signature = getPropValue("gad_go_signature_name");
				sign_desig = getPropValue("gad_designation_onduty");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sign.equals("4")) {
			try {
				signature = getPropValue("gad_go_sign_onduty");
				sign_desig = getPropValue("gad_designation");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				signature = getPropValue("gad_under");
				sign_desig = getPropValue("gad_under_desg");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String gosignature = signature;
		String gosignaturedesig = sign_desig;
		Integer id1 = 0;
		try {
			id1 = get_seqID("process_id");
			update_seqID("process_id", id1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String process_id = id1.toString();
		jdbcTemplate.update(q1,
				new Object[] { process_id, userId, "TCNF", null, ghId, gosignature, gosignaturedesig, blockId });

		q2 += " WHERE gh_id = '" + ghId + "' AND block_id = '" + blockId + "'" +
				" AND status != 'CAN' AND from_date::date >= now()::date";
		if (selected != null && !selected.equalsIgnoreCase("ALL")) {
			q2 += " AND req_id IN ('" + selected + "')";
		}

		q2 += " ORDER BY priority,req_date";

		alldetailsValue = jdbcTemplate.query(q2, (rs, rownumber) -> {
			ProcessModeration response = new ProcessModeration();
			response.setBookingId(rs.getString("req_id"));
			response.setVcId(rs.getString("vc_id"));
			response.setQuotatype(rs.getString("quota_type"));
			response.setStatus(rs.getString("status"));
			response.setCityId(rs.getString("gh_id"));
			response.setBlockId(rs.getString("block_id"));
			response.setType(rs.getString("type"));
			response.setRoomRent(rs.getString("room_rent"));
			response.setNoOfRooms(rs.getString("nor_req"));
			response.setNoOfBeds(rs.getString("nob_g_req"));
			response.setFrmDt(rs.getString("from_date"));
			response.setToDt(rs.getString("to_date"));
			response.setReqDate(rs.getString("req_date"));
			return response;
		});
		logger.info(alldetailsValue);

		int i = 0;
		Integer id2 = 0;
		for (i = 0; i < alldetailsValue.size(); i++) {
			md = alldetailsValue.get(i);
			logger.info(md);
			try {
				id2 = get_seqID("res_id");
				update_seqID("res_id", id2);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			String res_id = id2.toString();
			jdbcTemplate.update(q3, new Object[] { res_id, md.getFrmDt(), md.getToDt(), md.getFrmDt(), md.getToDt(),
					md.getType(), "TCNF", blockId, md.getBookingId(), process_id, ghId, 1, md.getQuotatype(),

			});
			if (md.getStatus().equals("ALO")) {
				jdbcTemplate.update(q4, new Object[] { "PRO", process_id, md.getBookingId() });
			}

			if (md.getStatus().equals("ALO")) {
				jdbcTemplate.update(q5, new Object[] { "PRO", md.getBookingId() });
			}
		}

		/* Conveyance Process Starts */
		List<ProcessModeration> conveyanceList = jdbcTemplate.query(q14, new Object[] { ghId, blockId }, (rs, rownumber) -> {
			ProcessModeration response = new ProcessModeration();
			response.setBookingId(rs.getString("rid"));
			response.setQuotatype(rs.getString("quota_type"));
			response.setStatus(rs.getString("status"));
			response.setCityId(rs.getString("gh_id"));
			response.setBlockId(rs.getString("block_id"));
			response.setType(rs.getString("roomtype"));
			response.setFrmDt(rs.getString("arrival_time"));
			response.setToDt(rs.getString("departure_time"));
			response.setReqDate(rs.getString("req_date"));
			return response;
		});

		for(ProcessModeration conv : conveyanceList) {
			try {
				id2 = get_seqID("res_id");
				update_seqID("res_id", id2);
				jdbcTemplate.update(q3, id2, conv.getFrmDt(), conv.getToDt(), conv.getFrmDt(), conv.getToDt(),
						conv.getType(), "TCNF", blockId, conv.getBookingId(), process_id, ghId, 1, conv.getQuotatype());

				jdbcTemplate.update(q4, "PRO", process_id, conv.getBookingId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		/* Conveyance Process Ends */

		/* Conference Process Starts */
		q15 += " AND gr.gh_id = '" + ghId + "' AND gr.block_id = '" + blockId + "'";
		List<ProcessModeration> confList = jdbcTemplate.query(q15, (rs, rownumber) -> {
			ProcessModeration response = new ProcessModeration();
			response.setBookingId(rs.getString("req_id"));
			response.setQuotatype(rs.getString("quota_type"));
			response.setStatus(rs.getString("status"));
			response.setCityId(rs.getString("gh_id"));
			response.setBlockId(rs.getString("block_id"));
			response.setType(rs.getString("roomtype"));
			response.setFrmDt(rs.getString("from_date"));
			response.setToDt(rs.getString("to_date"));
			response.setReqDate(rs.getString("req_date"));
			return response;
		}, "ALO");

		for(ProcessModeration conf : confList) {
			try {
				id2 = get_seqID("res_id");
				update_seqID("res_id", id2);
				jdbcTemplate.update(q3, id2, conf.getFrmDt(), conf.getToDt(), conf.getFrmDt(), conf.getToDt(),
						conf.getType(), "TCNF", blockId, conf.getBookingId(), process_id, ghId, 1, conf.getQuotatype());

				jdbcTemplate.update(q4, "PRO", process_id, conf.getBookingId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		/* Conference  Process Ends */

		List<Map<String, Object>> canList = null;
		try {
			q6 += " AND gr.gh_id = '" + ghId + "' AND gr.block_id = '" + blockId + "'";
			canList = jdbcTemplate.queryForList(q6);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		for (Map<String, Object> map : canList) {
			Integer canId = 0;
			String reqId = (String) map.get("req_id");
			String canStatus = (String) map.get("can_status");
			try {
				canId = get_seqID("cancel_id");
				update_seqID("cancel_id", canId);

				if ((null != canStatus) && canStatus.equals("PCAN")) {
					jdbcTemplate.update(q7,
							new Object[] { canId, reqId, process_id, ghId, blockId, "CREQ", process_id, "PARTIAL" });
					jdbcTemplate.update(q13, new Object[] { "PPRO", reqId });
				} else {
					jdbcTemplate.update(q7,
							new Object[] { canId, reqId, process_id, ghId, blockId, "CREQ", process_id, "FULL" });
					jdbcTemplate.update(q8, "CCAN", userId, reqId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

		List<BookingResponseModel> modifyList = null;
		try {
			q9 += " AND g.gh_id = '" + ghId + "' AND g.block_id = '" + blockId + "'";
			modifyList = jdbcTemplate.query(q9, (rs, rownumber) -> {
				BookingResponseModel response = new BookingResponseModel();
				response.setBookingId(rs.getString("req_id"));
				return response;
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		for (BookingResponseModel brm : modifyList) {
			Integer modId = 0;
			try {
				modId = get_seqID("modf_id");
				update_seqID("modf_id", modId);

				jdbcTemplate.update(q10, new Object[] { modId, brm.getBookingId(), process_id, ghId, blockId, "TCNF" });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		List<String> modList = null;
		try {
			q11 += " AND gh_id = '" + ghId + "' AND block_id = '" + blockId + "'";
			modList = jdbcTemplate.query(q11, (rs, rownumber) -> rs.getString("allot_id"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		for (String allotId : modList) {
			try {
				jdbcTemplate.update(q12, new Object[] { "MODF", allotId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return true;
	}


	@Override
	@Transactional
	public boolean cancelRequest(String reqId, String userId) {
		String sql = adminQueries.getProperty("canRequestForGH");
		String extensionExists = adminQueries.getProperty("extensionExists");
		String prevReqBackOperation = adminQueries.getProperty("prevReqBackOperation");
		String dltGad = adminQueries.getProperty("deleteFromGad");
		String dltGst = adminQueries.getProperty("deleteFromGuest");
		String query = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		ExtensionRequestModel extensionRequestModel = new ExtensionRequestModel();
		try {
			extensionRequestModel = jdbcTemplate.queryForObject(extensionExists, (rs1, rowNum) -> {
				ExtensionRequestModel erm = new ExtensionRequestModel();
				erm.setExt(rs1.getString("ext"));
				erm.setExisting_req_id(rs1.getString("existing_req_id"));
				return erm;
			}, reqId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Extension record fecth time");
		}
		if (extensionRequestModel.getExt().equals("0")) {
			jdbcTemplate.update(sql, new Object[] { "CAN", userId, reqId });
		} else {
			Integer noOfGst = null;
			Integer noOfChckGst = null;
			noOfGst = jdbcTemplate.queryForObject(query, new Object[] { extensionRequestModel.getExisting_req_id() }, Integer.class);
			noOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { extensionRequestModel.getExisting_req_id() }, Integer.class);
			if (noOfGst == noOfChckGst) {
				jdbcTemplate.update(prevReqBackOperation,
						new Object[] { "CIN", "PEXT", extensionRequestModel.getExisting_req_id() });
			} else {
				jdbcTemplate.update(prevReqBackOperation,
						new Object[] { "PCIN", "PEXT", extensionRequestModel.getExisting_req_id() });
			}
			jdbcTemplate.update(dltGad, new Object[] { reqId });
			jdbcTemplate.update(dltGst, new Object[] { reqId });
		}
		return true;
	}

	public List<ProcessModeration> getallDraftApplicationList(HttpServletRequest request) {
		List<ProcessModeration> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";

		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			cityName = request.getParameter("cityName");
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			guesthouseName = request.getParameter("guesthouseName");
		if (cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("DraftallList");
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					String crDate = sdf.format(rs.getTimestamp(3));
					ProcessModeration process = new ProcessModeration();
					process.setProcessId(rs.getString(1));
					process.setCreatedBy(rs.getString(2));
					process.setCreatedDate(crDate);
					String status_desc = "";
					if (rs.getString(4).equals("REQ")) {
						status_desc = " Not taken up for processing";
					}
					if (rs.getString(4).equals("CAN")) {
						status_desc = "Cancelled";
					}
					if (rs.getString(4).equalsIgnoreCase("MOD")) {
						status_desc = "Under processing";
					}
					if (rs.getString(4).equalsIgnoreCase("PRO")) {
						status_desc = "Approved";
					}
					if (rs.getString(4).equalsIgnoreCase("ALO")) {
						status_desc = "Allotted";
					}
					if (rs.getString(4).equals("CIN")) {
						status_desc = "Check In";
					}
					if (rs.getString(4).equals("COUT")) {
						status_desc = "Check Out";
					}
					if (rs.getString(4).equals("TCNF")) {
						status_desc = "Temporary Confirm";
					}
					if (rs.getString(4).equals("CNF")) {
						status_desc = "Confirm";
					}
					process.setStatus(status_desc);
					process.setCityId(rs.getString(7));
					process.setGoSign(rs.getString(8));
					process.setGoSignDesig(rs.getString(9));
					process.setBlockId(rs.getString(10));

					return process;
				}

			});

		}
		if (!cityName.equals("") && !guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("DraftallListwithGHouseAndCity");

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {

				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					String crDate = sdf.format(rs.getTimestamp(3));
					ProcessModeration process = new ProcessModeration();
					process.setProcessId(rs.getString(1));
					process.setCreatedBy(rs.getString(2));
					process.setCreatedDate(crDate);
					String status_desc = "";
					if (rs.getString(4).equals("REQ")) {
						status_desc = " Not taken up for processing";
					}
					if (rs.getString(4).equals("CAN")) {
						status_desc = "Cancelled";
					}
					if (rs.getString(4).equalsIgnoreCase("MOD")) {
						status_desc = "Under processing";
					}
					if (rs.getString(4).equalsIgnoreCase("PRO")) {
						status_desc = "Approved";
					}
					if (rs.getString(4).equalsIgnoreCase("ALO")) {
						status_desc = "Allotted";
					}
					if (rs.getString(4).equals("CIN")) {
						status_desc = "Check In";
					}
					if (rs.getString(4).equals("COUT")) {
						status_desc = "Check Out";
					}
					if (rs.getString(4).equals("TCNF")) {
						status_desc = "Temporary Confirm";
					}
					if (rs.getString(4).equals("CNF")) {
						status_desc = "Confirm";
					}
					process.setStatus(status_desc);
					process.setCityId(rs.getString(7));
					process.setGoSign(rs.getString(8));
					process.setGoSignDesig(rs.getString(9));
					process.setBlockId(rs.getString(10));
					process.setBlockName(rs.getString("block_name"));
					process.setCityName(rs.getString("gh_name"));
					return process;
				}

			}, cityName, guesthouseName);
			System.out.println("Approver  List  "+sql);
		}
		return alldetailsValue;
	}

	@Override
	public ProcessModeration getDetailsByprocessId(String process_id) {
		String sql = adminQueries.getProperty("detailsbyid");
		ProcessModeration processallData = jdbcTemplate.queryForObject(sql, new Object[] { String.valueOf(process_id) },
				new RowMapper<ProcessModeration>() {
					public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						String crDate = sdf.format(rs.getTimestamp(3));
						ProcessModeration process = new ProcessModeration();
						process.setProcessId(rs.getString(1));
						process.setCreatedBy(rs.getString(2));
						process.setCreatedDate(crDate);
						process.setStatus(rs.getString(4));

						process.setCityId(rs.getString(7));
						process.setGoSign(rs.getString(8));
						process.setGoSignDesig(rs.getString(9));
						process.setBlockId(rs.getString(10));
						process.setOfficeorderno(rs.getString("officeorderno"));
						return process;

					}
				});
		return processallData;
	}

	@Override
	@Transactional
	public boolean cancelProcessRequest(String processId) {
		String q1 = adminQueries.getProperty("canProcessRequest");
		String q2 = adminQueries.getProperty("canProcessRequestmod_req");
		String q3 = adminQueries.getProperty("updatereservationtablestaus");
		String q4 = adminQueries.getProperty("updatecanstatusinGadRequest");
		String q5 = adminQueries.getProperty("canModRequestForModRoomTbl");
		String q6 = adminQueries.getProperty("updateCancelReservationStatusWithProcessId");
		String q7 = adminQueries.getProperty("getAllotIdByProcessId");
		String q8 = adminQueries.getProperty("updateAllotmentModifyStatus");
		String q9 = adminQueries.getProperty("updateModifyReservationByProcessId");
		String q10 = approverQueries.getProperty("updatePartialCancelRequestByBookingId");
		String q11 = adminQueries.getProperty("canModRequestForModTbl");
		jdbcTemplate.update(q1, "CAN", processId);
		jdbcTemplate.update(q2, "ALO", processId);
		jdbcTemplate.update(q6, "CAN", processId);
		List<ProcessModeration> processallData = selectDBpro(processId);
		for (int i = 0; i < processallData.size(); i++) {
			ProcessModeration md1 = processallData.get(i);
			if(md1.getType().equalsIgnoreCase("C") || md1.getType().equalsIgnoreCase("CON")) {
				jdbcTemplate.update(q11, "ALO", md1.getReqid());
			} else {
				jdbcTemplate.update(q5, "ALO", md1.getReqid());
				jdbcTemplate.update(q11, "ALO", md1.getReqid());
			}
			jdbcTemplate.update(q3, "CAN", processId);
		}
		List<ProcessModeration> cancelProcessData = getCanResByProcessId(processId);
		for (int i = 0; i < cancelProcessData.size(); i++) {
			ProcessModeration md1 = cancelProcessData.get(i);
			if (md1.getStatus().equalsIgnoreCase("PARTIAL")) {
				jdbcTemplate.update(q10, "ALO", md1.getReqid());
				jdbcTemplate.update(q4, "PCAN", md1.getReqid());
			} else {
				jdbcTemplate.update(q4, "ACAN", md1.getReqid());
			}
		}
		List<String> modList = jdbcTemplate.queryForList(q7, new Object[] { processId }, String.class);
		if (modList != null && !modList.isEmpty()) {
			for (String modId : modList) {
				jdbcTemplate.update(q8, "MMODF", modId);
			}
		}
		jdbcTemplate.update(q9, "CAN", processId);
		return true;
	}

	private List<ProcessModeration> getCanResByProcessId(String processId) {
		String q1 = adminQueries.getProperty("getCanResByProcessId");

		List<ProcessModeration> processalData = jdbcTemplate.query(q1, new Object[] { processId }, (rs, rownumber) -> {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fromDate = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : "";
			String toDate = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : "";
			ProcessModeration process = new ProcessModeration();
			process.setReqid(rs.getString("req_id"));
			process.setProcessId(rs.getString("process_id"));
			process.setCityId(rs.getString("gh_id"));
			process.setBlockId(rs.getString("block_id"));
			process.setStatus(rs.getString("mode_of_cancel"));
			process.setFrmDt(fromDate);
			process.setToDt(toDate);
			return process;
		});
		return processalData;
	}

	@Override
	public List<RoomBookingPojo> getdraftViewdetails(String process_id) {
		String sql = adminQueries.getProperty("draftviewQuery");
		List<RoomBookingPojo> alldetailss = jdbcTemplate.query(sql, new RowMapper<RoomBookingPojo>() {
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				RoomBookingPojo res = new RoomBookingPojo();
				final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
				String fromDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
				res.setCityId(rs.getString("gh_id"));
				res.setBlockId(rs.getString("block_id"));
				// res.setCityName(rs.getString("gh_name"));
				// res.setBlockName(rs.getString("block_name"));
				String reqid = rs.getString("req_id");
				res.setBookingId(reqid);
				String t_purpose = null;
				String status = null;
				if (rs.getString("purpose") != null) {
					if (rs.getString("purpose").equals("Official")) {
						t_purpose = "(offl)";
					} else if (rs.getString("purpose").equals("Onduty")) {
						t_purpose = "(Onduty)";
					} else if (rs.getString("purpose").equals("public")) {
						t_purpose = "(public)";
					} else if (rs.getString("purpose").equals("civil service(PT)")) {
						t_purpose = "(civil service(PT))";
					} else {
						t_purpose = "(Pvt)";
					}
				}

				String t_confirmed_show = "";
				String t_waiting_show = "";
				Integer countroom = 0;
				Integer countladies = 0;
				Integer countgents = 0;
				Integer countladiesDor = 0;
				Integer countgentsDor = 0;
				String block_name_short_conf = null;
				String block_name_short_wait = null;
				Integer countroomw = 0;
				Integer countladiesw = 0;
				Integer countgentsw = 0;
				Integer countladieswDor = 0;
				Integer countgentswDor = 0;
				String vehicle = null;
				String vehicle1 = null;
				try {
					countroom = conformroom(rs.getString("req_id"));
					countladies = conformladies(rs.getString("req_id"));
					countgents = conformgents(rs.getString("req_id"));
					countladiesDor = conformladiesDor(rs.getString("req_id"));
					countgentsDor = conformgentsDor(rs.getString("req_id"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (countroom != 0) {
					if (countroom == 1) {
						t_confirmed_show = t_confirmed_show + countroom + " Room";
					} else {
						t_confirmed_show = t_confirmed_show + countroom + " Rooms";
					}
				}

				if (countladies != 0) {
					if (countladies == 1) {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countladies + " Ladies Bed";
					} else {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countladies + " Ladies Beds";
					}
				}

				if (countgents != 0) {
					if (countgents == 1) {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countgents + " Gents Bed";
					} else {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countgents + " Gents Beds";
					}
				}

				if (countladiesDor != 0) {
					if (countladiesDor == 1) {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Bed";
					} else {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countladiesDor + " Ladies Beds";
					}
				}

				if (countgentsDor != 0) {
					if (countgentsDor == 1) {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Bed";
					} else {
						if (!t_confirmed_show.equals("")) {
							t_confirmed_show = t_confirmed_show + ",";
						}
						t_confirmed_show = t_confirmed_show + countgentsDor + " Gents Beds";
					}
				}

				try {
					block_name_short_conf = short_conform_blocks(rs.getString("req_id"), rs.getString("gh_id"));
					block_name_short_wait = short_wait_blocks(rs.getString("req_id"), rs.getString("gh_id"));
					countroomw = conformroomw(rs.getString("req_id"));
					countladiesw = conformladiesw(rs.getString("req_id"));
					countgentsw = conformgentsw(rs.getString("req_id"));
					countladieswDor = conformladiesDorw(rs.getString("req_id"));
					countgentswDor = conformgentsDorw(rs.getString("req_id"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (countroomw != 0) {
					if (countroomw == 1) {
						t_waiting_show = t_waiting_show + countroomw + " Room";
					} else {
						t_waiting_show = t_waiting_show + countroomw + " Rooms";
					}
				}

				if (countladiesw != 0) {
					if (countladiesw == 1) {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countladiesw + " Ladies Bed";
					} else {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countladiesw + " Ladies Beds";
					}
				}

				if (countgentsw != 0) {
					if (countgentsw == 1) {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countgentsw + " Gents Bed";
					} else {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countgentsw + " Gents Beds";
					}
				}

				if (countladieswDor != 0) {
					if (countladieswDor == 1) {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countladieswDor + " Ladies Bed";
					} else {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countladieswDor + " Ladies Beds";
					}
				}

				if (countgentswDor != 0) {
					if (countgentswDor == 1) {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countgentswDor + " Gents Bed";
					} else {
						if (!t_waiting_show.equals("")) {
							t_waiting_show = t_waiting_show + ",";
						}
						t_waiting_show = t_waiting_show + countgentswDor + " Gents Beds";
					}
				}
				if (countroom != 0 || countladies != 0 || countgents != 0 || countladiesDor != 0
						|| countgentsDor != 0) {
					status = "Confirmed:" + t_confirmed_show + "-" + block_name_short_conf + t_purpose;
				}
				if (countroomw != 0 || countladiesw != 0 || countgentsw != 0 || countladieswDor != 0
						|| countgentswDor != 0) {
					status = "Waiting List:" + t_waiting_show + "-" + block_name_short_wait + t_purpose;
				}
				logger.info(status);
				String name = rs.getString("name");
				String t_ext = rs.getString("ext");
				if (t_ext.equals("1")) {
					t_ext = "Ext";
				} else {
					t_ext = "";
				}
				String t_family = rs.getString("family");
				if (t_family.equals("1")) {
					t_family = " (With Family/Others)";
				} else {
					t_family = "";
				}
				String ext_fam = "";
				if (!t_family.equals("")) {
					ext_fam = ""; // " - "+t_family;
				}
				if (!t_ext.equals("")) {
					ext_fam = ext_fam + " - " + t_ext;
				}

				if (rs.getString("is_veh_req").equalsIgnoreCase("1")) {
					List<VehicleDetails> vehicle2 = null;

					try {
						vehicle2 = getvehicleArrival(rs.getString("req_id"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					VehicleDetails vd = vehicle2.get(0);
					String arrivaltime = vd.getArrTm();
					String arrivalplace = vd.getArrPla();
					String arrivaldate = vd.getArrDt();
					String ftno = vd.getFlNoFr();
					vehicle = ftno + "," + arrivaldate + "," + arrivaltime + "," + arrivalplace;
				}

				if (rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
					List<VehicleDetails> vehicle3 = null;

					try {
						vehicle3 = getvehicleDepature(rs.getString("req_id"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					VehicleDetails vd = vehicle3.get(0);
					String depaturetime = vd.getDepTm();
					String depatureplace = vd.getDepPla();
					String depaturedate = vd.getDepDt();
					String ftno = vd.getFlNoTo();
					vehicle1 = ftno + "," + depaturedate + "," + depaturetime + "," + depatureplace;
				}
				logger.info(vehicle);
				logger.info(vehicle1);
				res.setArrivaldetails(vehicle);
				res.setDepaturedetails(vehicle1);
				List<GuestDetailsPojo> guest = null;

				try {
					guest = getGuestDetails(rs.getString("req_id"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String ret = "";
				for (int i = 0; i < guest.size(); i++) {

					GuestDetailsPojo guest1 = guest.get(i);
					logger.info(guest1);
					String name1 = guest1.getGuestName();
					String desg = guest1.getGuestDesignation();
					String dept = guest1.getGuestDepartment();
					if (!ret.equals("")) {
						ret = ret + " & ";
					}
					ret = ret + name1;
					String t_desig = desg != null ? desg : "";
					if (!t_desig.equals("")) {
						ret = ret + "," + t_desig;
					}
					String t_depa = dept != null ? dept : "";
					if (!t_depa.equals("")) {
						ret = ret + "," + t_depa;
					}

				}
				logger.info(ret);
				res.setGuestdetails(ret);
				res.setDesg(rs.getString("designation"));
				res.setFrmDt(fromDt);
				res.setToDt(toDt);
				res.setStatus(status);
				return res;
			}
		}, process_id, process_id, process_id, process_id, process_id);
		return alldetailss;

	}

	public Integer conformroom(String req_id) throws Exception {
		Integer countroom = 0;
		String room = "select count(*) from reservation_main_table where type='R' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countroom = 0;
				countroom = rs.getInt("count");
				return countroom;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countroom = users.get(0);
		}
		return countroom;
	}

	public Integer conformladies(String req_id) throws Exception {
		Integer countdorlady = 0;
		String room = "select count(*) from reservation_main_table where type='BL' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countdorlady = 0;
				countdorlady = rs.getInt("count");
				return countdorlady;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countdorlady = users.get(0);
		}
		return countdorlady;
	}

	public Integer conformgents(String req_id) throws Exception {
		Integer countgents = 0;
		String room = "select count(*) from reservation_main_table where type='BG' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countgents = 0;
				countgents = rs.getInt("count");
				return countgents;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countgents = users.get(0);
		}
		return countgents;
	}

	public Integer conformgentsDor(String req_id) throws Exception {
		Integer countdorgents = 0;
		String room = "select count(*) from reservation_main_table where type='DG' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countdorgents = 0;
				countdorgents = rs.getInt("count");
				return countdorgents;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countdorgents = users.get(0);
		}
		return countdorgents;
	}

	public Integer conformladiesDor(String req_id) throws Exception {
		Integer countladies = 0;
		String room = "select count(*) from reservation_main_table where type='DL' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countladies = 0;
				countladies = rs.getInt("count");
				return countladies;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countladies = users.get(0);
		}
		return countladies;
	}

	public Integer conformroomw(String req_id) throws Exception {
		Integer countroom = 0;
		String room = "select count(*) from reservation_waitinglist where type='R' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countroom = 0;
				countroom = rs.getInt("count");
				return countroom;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countroom = users.get(0);
		}
		return countroom;
	}

	public Integer conformladiesw(String req_id) throws Exception {
		Integer countdorlady = 0;
		String room = "select count(*) from reservation_waitinglist where type='BL' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countdorlady = 0;
				countdorlady = rs.getInt("count");
				return countdorlady;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countdorlady = users.get(0);
		}
		return countdorlady;
	}

	public Integer conformgentsw(String req_id) throws Exception {
		Integer countgents = 0;
		String room = "select count(*) from reservation_waitinglist where type='BG' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countgents = 0;
				countgents = rs.getInt("count");
				return countgents;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countgents = users.get(0);
		}
		return countgents;
	}

	public Integer conformgentsDorw(String req_id) throws Exception {
		Integer countdorgents = 0;
		String room = "select count(*) from reservation_waitinglist where type='DG' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countdorgents = 0;
				countdorgents = rs.getInt("count");
				return countdorgents;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countdorgents = users.get(0);
		}
		return countdorgents;
	}

	public Integer conformladiesDorw(String req_id) throws Exception {
		Integer countladies = 0;
		String room = "select count(*) from reservation_waitinglist where type='DL' and req_id=? and status!='CAN'";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countladies = 0;
				countladies = rs.getInt("count");
				return countladies;
			}
		}, req_id);
		if (null != users && !users.isEmpty()) {
			countladies = users.get(0);
		}
		return countladies;
	}

	public String short_conform_blocks(String req_id, String gh_id) throws Exception {
		String sql = "select DISTINCT(block_name_short) from(((select gh_id,block_id from reservation_main_table where status!='CAN' and req_id = ? and gh_id=?))) cc join (select * from room_block) dd on cc.gh_id=dd.gh_id and cc.block_id=dd.block_id";
		String block_name_short = null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String block_name_short = null;
				block_name_short = rs.getString("block_name_short");

				return block_name_short;
			}
		}, req_id, gh_id);
		if (null != users && !users.isEmpty()) {
			block_name_short = users.get(0);
		}
		return block_name_short;
	}

	public String short_wait_blocks(String req_id, String gh_id) throws Exception {
		String sql = "select DISTINCT(block_name_short) from(((select gh_id,block_id from reservation_waitinglist where status!='CAN' and req_id = ? and gh_id=?))) cc join (select * from room_block) dd on cc.gh_id=dd.gh_id and cc.block_id=dd.block_id";
		String short_wait_blocks = null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String short_wait_blocks = null;
				short_wait_blocks = rs.getString("block_name_short");

				return short_wait_blocks;
			}
		}, req_id, gh_id);
		if (null != users && !users.isEmpty()) {
			short_wait_blocks = users.get(0);
		}
		return short_wait_blocks;
	}

	public List<GuestDetailsPojo> getGuestDetails(String req_id) throws Exception {
		String sql = "select * from guest_details where req_id=? order by guest_id";
		List<GuestDetailsPojo> users = jdbcTemplate.query(sql, new RowMapper<GuestDetailsPojo>() {
			@Override
			public GuestDetailsPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				GuestDetailsPojo gres = new GuestDetailsPojo();
				gres.setGuestId(rs.getString("guest_id"));
				gres.setGuestName(rs.getString("name"));
				gres.setGuestDepartment(rs.getString("department"));
				gres.setGuestDesignation(rs.getString("designation"));
				gres.setGuestAddr(rs.getString("address"));
				return gres;
			}
		}, req_id);
		return users;
	}

	public List<VehicleDetails> getvehicleArrival(String req_id) throws Exception {
		String sql = "select * from vehicle_req where req_id=?";
		List<VehicleDetails> users = jdbcTemplate.query(sql, new RowMapper<VehicleDetails>() {
			@Override
			public VehicleDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
				VehicleDetails veh = new VehicleDetails();
				final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
				String arrDt = rs.getDate("arrival_date") != null ? sdf.format(rs.getDate("arrival_date")) : null;

				String arrTm = rs.getTime("arrival_time") != null ? stf.format(rs.getTime("arrival_time")) : null;

				veh.setdVchId(rs.getString("veh_id"));
				veh.setReqid(rs.getString("req_id"));
				veh.setArrDt(arrTm);
				veh.setTravelModeFr(rs.getString("mode_of_travel"));
				veh.setArrPla(rs.getString("arrival_place"));
				veh.setArrTm(arrDt);
				veh.setFlNoFr(rs.getString("ftno"));
				return veh;
			}
		}, req_id);
		return users;
	}

	public List<VehicleDetails> getvehicleDepature(String req_id) throws Exception {
		String sql = "select * from vehicle_req1 where req_id=?";
		List<VehicleDetails> users = jdbcTemplate.query(sql, new RowMapper<VehicleDetails>() {
			@Override
			public VehicleDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
				VehicleDetails veh = new VehicleDetails();
				final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
				String depDt = rs.getDate("arrival_date") != null ? sdf.format(rs.getDate("arrival_date")) : null;
				String depTm = rs.getTime("arrival_time") != null ? stf.format(rs.getTime("arrival_time")) : null;
				veh.setdVchId(rs.getString("veh_id"));
				veh.setReqid(rs.getString("req_id"));
				veh.setDepDt(depTm);
				veh.setTravelModeTo(rs.getString("mode_of_travel"));
				veh.setDepPla(rs.getString("arrival_place"));
				veh.setDepTm(depDt);
				veh.setFlNoTo(rs.getString("ftno"));
				return veh;
			}
		}, req_id);
		return users;
	}

	@Override
	@Transactional
	public boolean confirmProcess(String proid) {
		ProcessModeration md = getDetailsByprocessId(proid);
		String q1 = adminQueries.getProperty("updateProcessTable");
		String q2 = adminQueries.getProperty("updatemodreqStatusTable");
		String q3 = adminQueries.getProperty("updatereservationtablestaus");
		String q4 = adminQueries.getProperty("getupdatestatusinGadRequest");
		String q5 = adminQueries.getProperty("canModRequestForModRoomTbl");
		String q6 = adminQueries.getProperty("updateCancelReservationStatusWithProcessId");
		String q7 = adminQueries.getProperty("updateModifyReservationByProcessId");
		String q8 = approverQueries.getProperty("updatePartialCancelRequestByBookingId");
		String q9 = adminQueries.getProperty("updateTrAllotMentByReqIdAndStatus");
		String q10 = adminQueries.getProperty("updatecanstatusinGadRequest");
		String q11 = adminQueries.getProperty("count24can");
		String extensionExists = adminQueries.getProperty("extensionExists");
		String prevReqBackOperation = adminQueries.getProperty("prevReqBackOperation");
		String query = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		logger.info(proid);
		logger.info(md);
		jdbcTemplate.update(q1, md.getOfficeorderno(), "CNF", proid);
		jdbcTemplate.update(q2, "PRO", proid);
		jdbcTemplate.update(q6, "CCAN", proid);
		List<ProcessModeration> processallData = selectDBpro(proid);
		for (int i = 0; i < processallData.size(); i++) {
			ProcessModeration md1 = processallData.get(i);
			if(!md1.getType().equalsIgnoreCase("C") && !md1.getType().equalsIgnoreCase("CON")) {
				jdbcTemplate.update(q5, "PRO", md1.getReqid());
				jdbcTemplate.update(q9, "PRO", md1.getReqid(), "ALO");
			}
			jdbcTemplate.update(q3, "CNF", proid);
			jdbcTemplate.update(q4, "PRO", md1.getReqid());
		}
		List<ProcessModeration> cancelProcessData = getCanResByProcessId(proid);
		for (int i = 0; i < cancelProcessData.size(); i++) {
			ProcessModeration md1 = cancelProcessData.get(i);
			if (md1.getStatus().equalsIgnoreCase("PARTIAL")) {
				jdbcTemplate.update(q8, "PRO", md1.getReqid());
			} else {
				createNOC(md1.getReqid());
				jdbcTemplate.update(q10, "24CAN", md1.getReqid());
				ExtensionRequestModel extensionRequestModel = new ExtensionRequestModel();
				try {
					extensionRequestModel = jdbcTemplate.queryForObject(extensionExists, (rs1, rowNum) -> {
						ExtensionRequestModel erm = new ExtensionRequestModel();
						erm.setExt(rs1.getString("ext"));
						erm.setExisting_req_id(rs1.getString("existing_req_id"));
						return erm;
					}, md1.getReqid());
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Extension record fecth time");
				}
				if (extensionRequestModel.getExt().equals("1")) {
					Integer noOfGst = null;
					Integer noOfChckGst = null;
					noOfGst = jdbcTemplate.queryForObject(query, new Object[] { extensionRequestModel.getExisting_req_id() }, Integer.class);
					noOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { extensionRequestModel.getExisting_req_id() }, Integer.class);
					if (noOfGst == noOfChckGst) {
						jdbcTemplate.update(prevReqBackOperation,
								new Object[] { "CIN", "PEXT", extensionRequestModel.getExisting_req_id() });
					} else {
						jdbcTemplate.update(prevReqBackOperation,
								new Object[] { "PCIN", "PEXT", extensionRequestModel.getExisting_req_id() });
					}
				}
			}
		}
		jdbcTemplate.update(q7, "CNF", proid);
		sendSmsByProcessId(proid);
		return true;
	}

	@Async
	protected void sendSmsByProcessId(String proid) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String query = adminQueries.getProperty("getAllotmentDetailsForSms");
		String q11 = adminQueries.getProperty("getModifyRoomSm");
		String q12 = allotmentQueries.getProperty("selectModifyRoomDetails");
		String q13 = adminQueries.getProperty("getFullCancelUserDeailsForSms");
		String q14 = adminQueries.getProperty("getPartialCancelUserDeailsForSms");

		List<BookingResponseModel> brm = jdbcTemplate.query(query, (rs, rn) -> {
			BookingResponseModel brm1 = new BookingResponseModel();
			brm1.setBookingId(rs.getString("req_id"));
			brm1.setMobile(rs.getString("userid"));
			brm1.setFromDate(sdf.format(rs.getDate("from_date")));
			brm1.setToDate(sdf.format(rs.getDate("to_date")));
			brm1.setNoRooms(rs.getString("room_no"));
			brm1.setName(rs.getString("name"));
			brm1.setCityName(rs.getString("gh_name"));
			brm1.setGhName(rs.getString("block_name"));
			brm1.setEmail(rs.getString("email_id"));
			return brm1;
		}, proid);
		for (BookingResponseModel brm1 : brm) {
			String templateId = "1107166028578492328";
			String message = "Dear " + brm1.getName() + " Room number " + brm1.getNoRooms() + " is allotted in "
					+ brm1.getGhName() + ", " + brm1.getCityName() + " for " + brm1.getFromDate() + " to " +
					brm1.getToDate() + " against your Request No " + brm1.getBookingId() + " -Odisha Home Dept";
			smsSend.sendSMS(brm1.getMobile(), message, templateId);
			if (brm1.getEmail() != null && !brm1.getEmail().isEmpty()) {
				//mail.sendMimeMail(brm1.getEmail(), "Room Allotment", message);
			}
		}

		//for modification room message
		List<String> reqIds = jdbcTemplate.queryForList(q11, String.class, proid);
		for (String reqId : reqIds) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			RoomDetails roomDetailsSms = new RoomDetails();
			try {
				roomDetailsSms = jdbcTemplate.queryForObject(q12, new Object[] { reqId }, (rs, rownumber) -> {
					RoomDetails rd = new RoomDetails();
					rd.setName(rs.getString("name"));

					Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
					Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
					rd.setFromDate(sdf1.format(fromDate));
					rd.setToDate(sdf1.format(toDate));
					rd.setRoomNo(rs.getString("room_no"));
					rd.setPrevOrderNo(rs.getString("prev_room_no"));
					rd.setBookingId(rs.getString("req_id"));
					rd.setBlockName(rs.getString("block_name"));
					rd.setCityId(rs.getString("gh_name"));
					rd.setModifiedRoomRent(rs.getString("mobile_no"));
					rd.setType(rs.getString("email_id"));
					return rd;
				});
			} catch (Exception e) {
				e.printStackTrace();
			}

			String subject = "Dear " + roomDetailsSms.getName() + ", Room number " + roomDetailsSms.getRoomNo()
					+ " is allotted instead of Room number " + roomDetailsSms.getPrevOrderNo() + " in "
					+ roomDetailsSms.getBlockName() + ", " + roomDetailsSms.getCityId() + " from "
					+ roomDetailsSms.getFromDate() + " to " + roomDetailsSms.getToDate() + " against your Booking ID "
					+ roomDetailsSms.getBookingId() + " - Odisha Home Dept";
			System.out.println(subject);
			String templateId = "1107168976021691330";
			try {
				if (roomDetailsSms.getModifiedRoomRent() != null) {
					if (!roomDetailsSms.getModifiedRoomRent().isEmpty()) {
						smsSend.sendSMS(roomDetailsSms.getModifiedRoomRent(), subject, templateId);
					}
				}
			} catch (Exception e) {
				logger.error("Error occured in sms " + e.getMessage());
			}
//			try {
//				if (roomDetailsSms.getType() != null) {
//					if (!roomDetailsSms.getType().isEmpty()) {
//						mail.sendMimeMail(roomDetailsSms.getType(), "Modify Allotment", subject);
//					}
//				}
//			} catch (Exception e) {
//				logger.error("error occured in sms or email send..");
//			}
		}
		//NOC or FullCancel Message
		List<NocWithFullCanceldDetail> Cancelusers = new ArrayList<>();
		try {
			Cancelusers = jdbcTemplate.query(q13, new RowMapper<NocWithFullCanceldDetail>() {
				@Override
				public NocWithFullCanceldDetail mapRow(ResultSet rs, int rownumber) throws SQLException {
					NocWithFullCanceldDetail veh = new NocWithFullCanceldDetail();
					final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
					String frmDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
					String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
					String nfrmDt = rs.getDate("nfrom_date") != null ? sdf.format(rs.getDate("nfrom_date")) : null;
					String ntoDt = rs.getDate("nto_date") != null ? sdf.format(rs.getDate("nto_date")) : null;
					veh.setName(rs.getString("name"));
					veh.setReqId(rs.getString("req_id"));
					veh.setBlockName(rs.getString("block_name"));
					veh.setGhName(rs.getString("gh_name"));
					veh.setRoomNo(rs.getString("room_numbers"));
					veh.setFromDate(frmDt);
					veh.setToDate(toDt);
					veh.setNocFromDt(nfrmDt);
					veh.setNocToDt(ntoDt);
					veh.setEmail(rs.getString("email_id"));
					veh.setPhone(rs.getString("mobile_no"));
					veh.setNocCharge(rs.getString("noc_charge"));
					return veh;
				}
			}, proid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		for (NocWithFullCanceldDetail singleUser : Cancelusers) {
			String subject = "";
			if (!singleUser.getNocCharge().equals("0")) {
				subject = "Dear " + singleUser.getName() + ", Room number " + singleUser.getRoomNo()
						+ " has been cancelled in " + singleUser.getBlockName() + ", " + singleUser.getGhName() + " from "
						+ singleUser.getNocFromDt() + " to " + singleUser.getNocToDt() + " against your Booking ID "
						+ singleUser.getReqId() + " and NOC from " + singleUser.getFromDate() + " to "
						+ singleUser.getNocFromDt() + " amounting to Rs " + singleUser.getNocCharge()
						+ " is to be deposited - Odisha Home Dept";

//			try {
//				if (singleUser.getEmail() != null) {
//					if (!singleUser.getEmail().isEmpty()) {
//						mail.sendMimeMail(singleUser.getEmail(), "Cancel Allotment", subject);
//					}
//				}
//			} catch (Exception e) {
//				logger.error("error occured in sms or email send..");
//			}

				String templateId = "1107168975996962785";
				try {
					if (singleUser.getPhone() != null) {
						if (!singleUser.getPhone().isEmpty()) {
							smsSend.sendSMS(singleUser.getPhone(), subject, templateId);
						}
					}
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
				}

			} else {
				subject = "Dear " + singleUser.getName() + ", Room number " + singleUser.getRoomNo()
						+ " has been cancelled in " + singleUser.getBlockName() + ", " + singleUser.getGhName() + " from "
						+ singleUser.getFromDate() + " to " + singleUser.getToDate() + " against your Booking ID "
						+ singleUser.getReqId() + " - Odisha Home Dept";
//			try {
//				if (singleUser.getEmail() != null) {
//					if (!singleUser.getEmail().isEmpty()) {
//						mail.sendMimeMail(singleUser.getEmail(), "Cancel Allotment", subject);
//					}
//				}
//			} catch (Exception e) {
//				logger.error("error occured in sms or email send..");
//			}
				String templateId = "1107168975969587469";
				try {
					if (singleUser.getPhone() != null) {
						if (!singleUser.getPhone().isEmpty()) {
							smsSend.sendSMS(singleUser.getPhone(), subject, templateId);
						}
					}
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
				}
			}
		}
		// Partail or FullCancel Message
		List<NocWithFullCanceldDetail> partialCancelusers = new ArrayList<>();
		try {
			partialCancelusers = jdbcTemplate.query(q14, new RowMapper<NocWithFullCanceldDetail>() {
				@Override
				public NocWithFullCanceldDetail mapRow(ResultSet rs, int rownumber) throws SQLException {
					NocWithFullCanceldDetail veh = new NocWithFullCanceldDetail();
					final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String frmDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
					String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
					veh.setName(rs.getString("name"));
					veh.setReqId(rs.getString("req_id"));
					veh.setBlockName(rs.getString("block_name"));
					veh.setGhName(rs.getString("gh_name"));
					veh.setRoomNo(rs.getString("room_numbers"));
					veh.setFromDate(frmDt);
					veh.setToDate(toDt);
					veh.setEmail(rs.getString("email_id"));
					veh.setPhone(rs.getString("mobile_no"));
					return veh;
				}
			}, proid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		for (NocWithFullCanceldDetail singleUser : partialCancelusers) {
			String subject = "";

			subject = "Dear " + singleUser.getName() + ", Room number " + singleUser.getRoomNo() + " has been cancelled in "
					+ singleUser.getBlockName() + ", " + singleUser.getGhName() + " from " + singleUser.getFromDate()
					+ " to " + singleUser.getToDate() + " against your Booking ID " + singleUser.getReqId()
					+ " - Odisha Home Dept";
//		try {
//			if (singleUser.getEmail() != null) {
//				if (!singleUser.getEmail().isEmpty()) {
//					mail.sendMimeMail(singleUser.getEmail(), "Cancel Allotment", subject);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send..");
//		}

			String templateId = "1107168975969587469";
			try {
				if (singleUser.getPhone() != null) {
					if (!singleUser.getPhone().isEmpty()) {
						smsSend.sendSMS(singleUser.getPhone(), subject, templateId);
					}
				}
			} catch (Exception e) {
				logger.error("Error occured in sms " + e.getMessage());
			}
		}
	}

	private void createNOC(String reqId) {
		String q1 = approverQueries.getProperty("getNocByReqId");
		String q2 = approverQueries.getProperty("getFromDateToDateFromGadRequest");
		String q3 = approverQueries.getProperty("getRoomRentByFromDateToDate");
		String q4 = approverQueries.getProperty("updateNocChargeByNocId");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		NocModel nocModel = null;
		try {
			nocModel = jdbcTemplate.queryForObject(q1, (rs, rn) -> {
				NocModel noc = new NocModel();
				noc.setNocId(rs.getLong("noc_id"));
				noc.setFromDate(sdf.format(rs.getDate("from_date")));
				noc.setToDate(sdf.format(rs.getDate("to_date")));
				return noc;
			}, reqId);
		} catch (Exception e) {
			return;
		}
		try {
			BookingResponseModel brm = jdbcTemplate.queryForObject(q2, (rs, rn) -> {
				BookingResponseModel brm1 = new BookingResponseModel();
				brm1.setFromDate(sdf.format(rs.getDate("from_date")));
				brm1.setToDate(sdf.format(rs.getDate("to_date")));
				return brm1;
			}, reqId);

			LocalDate fromDate = LocalDate.parse(brm.getFromDate());
			LocalDate cancelDate = LocalDate.parse(nocModel.getFromDate());

			Double amount = 0.0;

			if (cancelDate.isBefore(fromDate)) {
				System.out.println("No need for NOC");
			} else {
				List<LocalDate> dates = Utils.getDatesBetween(fromDate, cancelDate);
				for (LocalDate date : dates) {
					Date frDt = sdf.parse(date.toString());
					Date nextDate = sdf.parse(date.plusDays(1).toString());
					amount += jdbcTemplate.queryForObject(q3, Double.class, frDt, nextDate, reqId);
				}
				double nocAmount = Math.round(amount * 0.5);
				jdbcTemplate.update(q4, nocAmount, nocModel.getNocId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while creating NOC");
		}
	}

	public List<ProcessModeration> selectDBpro(String process_id) {
		String sql = "select * from reservation_main_table where process_id=?";
		List<ProcessModeration> processallData = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {
			public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
				ProcessModeration process = new ProcessModeration();
				process.setProcessId(rs.getString("process_id"));
				process.setReqid(rs.getString("req_id"));
				process.setStatus(rs.getString("status"));
				process.setCityId(rs.getString("gh_id"));
				process.setBlockId(rs.getString("block_id"));
				process.setType(rs.getString("type"));
				return process;

			}
		}, process_id);
		return processallData;
	}
	
	@Override
	public DataTableResponse getconfirmDraftlist(DataTableRequest request) {
	    DataTableResponse response = new DataTableResponse();
	    ExecutorService executorService = Executors.newFixedThreadPool(2);
	    List<ProcessModeration> allDetailsValue = new ArrayList<>();
	    String cityName = request.getCity() != null ? request.getCity() : "";
	    String guesthouseName = request.getgHouse() != null ? request.getgHouse() : "";
	    int start = request.getStart();
	    
	    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	    Map<String, Object> params = new HashMap<>();
	    params.put("cityName", cityName);
	    params.put("guesthouseName", guesthouseName);
	    params.put("start", start);
	    params.put("length", request.getLength());

	    if (cityName.equals("") && guesthouseName.equals("")) {
	        String sqlData = adminQueries.getProperty("confirmDraftallList");
	        String sqlCount = adminQueries.getProperty("confirmDraftallListCount");
	        if (request.getLength() != 0) {
	            sqlData += " LIMIT :length OFFSET :start ";
	        }
	        final String queryData = sqlData;
	        final String queryCount = sqlCount;
	        CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
	            try {
	                return namedParameterJdbcTemplate.queryForObject(queryCount, params, Integer.class);
	            } catch (Exception e) {
	                logger.error("Error fetching count", e);
	                return 0;
	            }
	        }, executorService);

	        CompletableFuture<List<ProcessModeration>> dataFuture = CompletableFuture.supplyAsync(() -> {
	            return namedParameterJdbcTemplate.query(queryData, params, (rs, rowNum) -> {
	                final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                String createdDate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date")) : null;
	                ProcessModeration process = new ProcessModeration();
	                process.setProcessId(rs.getString(1));
	                process.setCreatedBy(rs.getString(2));
	                process.setCreatedDate(createdDate);
	                process.setStatus(rs.getString(4));
	                process.setCityId(rs.getString(7));
	                process.setGoSign(rs.getString(8));
	                process.setGoSignDesig(rs.getString(9));
	                process.setBlockId(rs.getString(10));
	                process.setBlockName(rs.getString("block_name"));
	                process.setCityName(rs.getString("gh_name"));
	                return process;
	            });
	        }, executorService);

	        DataTableResponse dt = new DataTableResponse();
	        try {
	            dt.setDraw(request.getDraw());
	            dt.setRecordsTotal(countFuture.get());
	            dt.setRecordsFiltered(countFuture.get());
	            dt.setData(dataFuture.get());
	        } catch (InterruptedException | ExecutionException e) {
	            e.printStackTrace();
	        } finally {
	            executorService.shutdown();
	        }
	        return dt;
	    }

	    if (!cityName.equals("") && guesthouseName.equals("")) {
	        String sqlData = adminQueries.getProperty("confirmDraftallListwithCity");
	        String sqlCount = adminQueries.getProperty("confirmDraftallListwithCityCount");
	        if (request.getLength() != 0) {
	            sqlData += " LIMIT " + request.getLength() + " OFFSET " + start;
	        }
	        final String queryData = sqlData;
	        final String queryCount = sqlCount;
	        CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
	            try {
	                return namedParameterJdbcTemplate.queryForObject(queryCount, params, Integer.class);
	            } catch (Exception e) {
	                logger.error("Error fetching count", e);
	                return 0;
	            }
	        }, executorService);

	        CompletableFuture<List<ProcessModeration>> dataFuture = CompletableFuture.supplyAsync(() -> {
	            return namedParameterJdbcTemplate.query(queryData, params, (rs, rowNum) -> {
	                final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                String createdDate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date")) : null;
	                ProcessModeration process = new ProcessModeration();
	                process.setProcessId(rs.getString(1));
	                process.setCreatedBy(rs.getString(2));
	                process.setCreatedDate(createdDate);
	                process.setStatus(rs.getString(4));
	                process.setCityId(rs.getString(7));
	                process.setGoSign(rs.getString(8));
	                process.setGoSignDesig(rs.getString(9));
	                process.setBlockId(rs.getString(10));
	                process.setBlockName(rs.getString("block_name"));
	                process.setCityName(rs.getString("gh_name"));
	                return process;
	            });
	        }, executorService);

	        DataTableResponse dt = new DataTableResponse();
	        try {
	            dt.setDraw(request.getDraw());
	            dt.setRecordsTotal(countFuture.get());
	            dt.setRecordsFiltered(countFuture.get());
	            dt.setData(dataFuture.get());
	        } catch (InterruptedException | ExecutionException e) {
	            e.printStackTrace();
	        } finally {
	            executorService.shutdown();
	        }
	        return dt;
	    }

	    if (!cityName.equals("") && !guesthouseName.equals("")) {
	        String sqlData = adminQueries.getProperty("confirmDraftallListwithGHouseAndCity");
	        String sqlCount = adminQueries.getProperty("confirmDraftallListwithGHouseAndCityCount");
	        if (request.getLength() != 0) {
	            sqlData += " LIMIT :length OFFSET :start";
	        }
	        final String queryData = sqlData;
	        final String queryCount = sqlCount;
	        CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
	            try {
	                return namedParameterJdbcTemplate.queryForObject(queryCount, params, Integer.class);
	            } catch (Exception e) {
	                logger.error("Error fetching count", e);
	                return 0;
	            }
	        }, executorService);

	        CompletableFuture<List<ProcessModeration>> dataFuture = CompletableFuture.supplyAsync(() -> {
	            return namedParameterJdbcTemplate.query(queryData, params, (rs, rowNum) -> {
	                final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                String createdDate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date")) : null;
	                ProcessModeration process = new ProcessModeration();
	                process.setProcessId(rs.getString(1));
	                process.setCreatedBy(rs.getString(2));
	                process.setCreatedDate(createdDate);
	                process.setStatus(rs.getString(4));
	                process.setCityId(rs.getString(7));
	                process.setGoSign(rs.getString(8));
	                process.setGoSignDesig(rs.getString(9));
	                process.setBlockId(rs.getString(10));
	                process.setBlockName(rs.getString("block_name"));
	                process.setCityName(rs.getString("gh_name"));
	                return process;
	            });
	        }, executorService);

	        DataTableResponse dt = new DataTableResponse();
	        try {
	            dt.setDraw(request.getDraw());
	            dt.setRecordsTotal(countFuture.get());
	            dt.setRecordsFiltered(countFuture.get());
	            dt.setData(dataFuture.get());
	        } catch (InterruptedException | ExecutionException e) {
	            e.printStackTrace();
	        } finally {
	            executorService.shutdown();
	        }
	        return dt;
	    }
	    return response;
	}


	/* Prasad AdminDaoImpl Starts */
	@Override
	public boolean addModeratorDetails(RoomBookingPojo data, String userId) {
		System.out.println("Room Booking Pojo in add moderation ===== " + data);
		System.out.println("Type of Room ==================== " + data.getRoomType());
		String query1 = allotmentQueries.getProperty("getAddGhDtlsAsPerModReqSQL");
		String query2 = allotmentQueries.getProperty("getModReqTbInsertAsPerModReqSQL");
		String query3 = allotmentQueries.getProperty("getModReqRoomTbInsertAsPerModReqSQL");
		String query4 = allotmentQueries.getProperty("setExtInGad");
		String query5 = allotmentQueries.getProperty("getAlreadyExtApplyOrNot");
		Integer modId = null;
		String status = null;
		String conveyanceFrom="0";
		String conveyanceTo="0";
		if (data.getRoomType().equalsIgnoreCase("C")) {
			status = "ALO";
		} else {
			status = "MOD";
		}
		if(data.getConvayance().equalsIgnoreCase("1")) {
		  if(data.getArrivalConv()) {
				conveyanceFrom="1";
			}else {
				conveyanceFrom="0";
			}
		  if(data.getDepartueConv()) {
				conveyanceTo="1";
			}else {
				conveyanceTo="0";
			}
		}
		
//		sandip block start
		String alreadyExt="";
		try {
			alreadyExt=jdbcTemplate.queryForObject(query5,new Object[] {data.getBookingId(),"CAN"},String.class);
		}catch(Exception e) {
			e.printStackTrace();
			alreadyExt="0";
		}
		if(!alreadyExt.equals("1") && data.getRoomType().equals("R")) {
			if(data.getExt().equals("2")) {
				jdbcTemplate.update(query4,new Object[] {data.getExt(),data.getBookingId()});
			}else {
				jdbcTemplate.update(query4,new Object[] {data.getExt(),data.getBookingId()});
			}
		}
		
//		sandip block end
		jdbcTemplate.update(query1, new Object[] { data.getBlockId(),conveyanceFrom, conveyanceTo,
				data.getDuringstay(), status, data.getNoOfRooms(), data.getNoOfBedsDormitory(), data.getBookingId() });
		try {
			modId = guestDao.get_seqID("mod_id");
			guestDao.update_seqID("mod_id", modId);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		jdbcTemplate.update(query2, new Object[] { data.getBookingId(), modId, data.getCityId(), data.getCatg(),
				data.getDesg(), conveyanceFrom, conveyanceTo, data.getDuringstay(), status });

		Integer roomNo = Integer.parseInt(data.getNoOfRooms() != null && data.getNoOfRooms().trim() != "" ? data.getNoOfRooms() : "0");
		Integer rbdNo = Integer.parseInt(data.getNoOfBedsDormitory() != null && data.getNoOfBedsDormitory().trim() != "" ? data.getNoOfBedsDormitory(): "0");
		Integer totalCount = roomNo + rbdNo;
		while (totalCount > 0) {
			if (roomNo > 0) {
				Integer mrrId = null;
				try {
					mrrId = guestDao.get_seqID("mrr_id");
					guestDao.update_seqID("mrr_id", mrrId);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
				jdbcTemplate.update(query3, new Object[] { data.getBookingId(), mrrId, modId, data.getCityId(),
						data.getBlockId(), 1, "R", status });
				roomNo--;

			}
			if (rbdNo > 0) {
				Integer mrrId = null;
				try {
					mrrId = guestDao.get_seqID("mrr_id");
					guestDao.update_seqID("mrr_id", mrrId);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
				jdbcTemplate.update(query3, new Object[] { data.getBookingId(), mrrId, modId, data.getCityId(),
						data.getBlockId(), 1, "BG", status });
				rbdNo--;

			}
			totalCount--;
		}

		return true;
	}

	@Override
	public boolean modifyModeratorDetails(RoomBookingPojo data, String userId) {
		String query1 = allotmentQueries.getProperty("getUpdateGhDtlsAsPerModReqSQL");
		String query2 = allotmentQueries.getProperty("getModReqTbUpdateAsPerModReqSQL");
		String query3 = allotmentQueries.getProperty("getModReqRoomTbDeleteAsPerModReqSQL");
		String query4 = allotmentQueries.getProperty("getModReqRoomTbInsertAsPerModReqSQL");
		String query5 = allotmentQueries.getProperty("getModIdFromModReqByBookingId");
		String query6 = allotmentQueries.getProperty("setExtInGad");
		String query7 = allotmentQueries.getProperty("getAlreadyExtApplyOrNot");
		String conveyanceFrom="0";
		String conveyanceTo="0";
		if(data.getConvayance().equalsIgnoreCase("1")) {
			  if(data.getArrivalConv()) {
					conveyanceFrom="1";
				}else {
					conveyanceFrom="0";
				}
			  if(data.getDepartueConv()) {
					conveyanceTo="1";
				}else {
					conveyanceTo="0";
				}
		}
//		sandip block start
		String alreadyExt="";
		try {
			alreadyExt=jdbcTemplate.queryForObject(query7,new Object[] {data.getBookingId(),"CAN"},String.class);
		}catch(Exception e) {
			e.printStackTrace();
			alreadyExt="0";
		}
		if (!alreadyExt.equals("1") && data.getRoomType().equals("R")) {
			if (data.getExt().equals("2")) {
				jdbcTemplate.update(query6, new Object[] { data.getExt(), data.getBookingId() });
			} else {
				jdbcTemplate.update(query6, new Object[] { data.getExt(), data.getBookingId() });
			}
		}
//		sandip block end
		jdbcTemplate.update(query1, new Object[] { data.getBlockId(), conveyanceFrom, conveyanceTo,
				data.getDuringstay(), data.getNoOfRooms(), data.getNoOfBedsDormitory(), data.getBookingId() });

		jdbcTemplate.update(query2, new Object[] { data.getCatg(), data.getDesg(),  conveyanceFrom, conveyanceTo, data.getDuringstay(), data.getBookingId() });

		jdbcTemplate.update(query3, new Object[] { data.getBookingId() });
		String modId = jdbcTemplate.queryForObject(query5, String.class, data.getBookingId());

		Integer roomNo = Integer.parseInt(data.getNoOfRooms() != null && data.getNoOfRooms().trim() != "" ? data.getNoOfRooms() : "0");
		Integer rbdNo = Integer.parseInt(data.getNoOfBedsDormitory() != null && data.getNoOfBedsDormitory().trim() != "" ? data.getNoOfBedsDormitory(): "0");
		Integer totalCount = roomNo + rbdNo;
		while (totalCount > 0) {
			if (roomNo > 0) {
				Integer mrrId = null;
				try {
					mrrId = guestDao.get_seqID("mrr_id");
					guestDao.update_seqID("mrr_id", mrrId);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
				jdbcTemplate.update(query4, new Object[] { data.getBookingId(), mrrId, modId, data.getCityId(),
						data.getBlockId(), 1, "R", "MOD" });
				roomNo--;

			}
			if (rbdNo > 0) {
				Integer mrrId = null;
				try {
					mrrId = guestDao.get_seqID("mrr_id");
					guestDao.update_seqID("mrr_id", mrrId);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
				jdbcTemplate.update(query4, new Object[] { data.getBookingId(), mrrId, modId, data.getCityId(),
						data.getBlockId(), 1, "BG", "MOD" });
				rbdNo--;

			}
			totalCount--;
		}
		return true;
	}

	@Override
	public boolean cancelModerationRequest(String reqId) {
		String sql = adminQueries.getProperty("updateGadReqSql");
		String sql2 = adminQueries.getProperty("deleteModReqData");
		String sql3 = adminQueries.getProperty("deleteModReqRoomData");
		jdbcTemplate.update(sql, new Object[] { "REQ", reqId });
		jdbcTemplate.update(sql2, new Object[] { reqId });
		jdbcTemplate.update(sql3, new Object[] { reqId });
		return true;
	}

	@Override
	public List<BookingResponseModel> getCancellationLstDtlsAccToReq(Map<String, Object> params) {
		List<BookingResponseModel> data;
		String orderByCondition = " ORDER BY b.from_date DESC";
		String query = approverQueries.getProperty("getBookingListForAdminCancelSQL");

		String whereCondition = "";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.block_id='" + ghId + "'";
		}

		query = query + whereCondition + orderByCondition;
		logger.info(query);
		data = jdbcTemplate.query(query, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				return setCancelReservationLstFromResultset(rs);
			}
		});

		return data;
	}

	private BookingResponseModel setCancelReservationLstFromResultset(ResultSet rs) throws SQLException {
		BookingResponseModel data = new BookingResponseModel();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			String bookingDt = rs.getDate(2) != null ? sdf.format(rs.getDate(2)) : null;
			String frDt = rs.getDate(4) != null ? sdf1.format(rs.getTimestamp(4)) : null;
			String toDt = rs.getDate(5) != null ? sdf1.format(rs.getTimestamp(5)) : null;

			data.setBookingId(rs.getString(1));
			data.setBookingDt(bookingDt);
			data.setName(rs.getString(3));
			data.setFromDate(frDt);
			data.setToDate(toDt);
			data.setDesg(rs.getString(6));
			data.setMobile(rs.getString(7));
			data.setEmail(rs.getString(8));
			String status = rs.getString(9);
			String status_desc = "";
			if (status.equals("REQ")) {
				status_desc = " Not taken up for processing";
			}
			if (status.equals("CAN")) {
				status_desc = "Cancelled";
			}
			if (status.equalsIgnoreCase("MOD")) {
				status_desc = "Under processing";
			}
			if (status.equalsIgnoreCase("PRO")) {
				status_desc = "Approved";
			}
			if (status.equalsIgnoreCase("ALO")) {
				status_desc = "Allotted";
			}
			if (status.equals("CIN")) {
				status_desc = "Check In";
			}
			if (status.equals("COUT")) {
				status_desc = "Check Out";
			}
			if (status.equals("RCAN")) {
				status_desc = "Cancelled";
			}
			data.setStatus(status);
			data.setStatusDesc(status_desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<BookingResponseModel> getCancellationModLstDtls(Map<String, Object> params) {
		List<BookingResponseModel> data;
		String orderByCondition = " ORDER BY b.from_date DESC";
		String query = approverQueries.getProperty("getBookingListForModeratorCancelSQL");

		String whereCondition = " WHERE 1=1";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String type = (String) params.get("type");

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.block_id='" + ghId + "'";
		}
		if (type.equalsIgnoreCase("MOD")) {
			whereCondition = whereCondition + " AND (b.status='RCAN' AND b.can_status='RCAN' "
					+ "OR (c.status = 'REQ' AND DATE(NOW()) <= DATE(b.to_date)))";
		} else {
			whereCondition = whereCondition + " AND (b.status='CAN' AND b.can_status='MCAN' "
					+ "OR (c.status = 'MOD' AND DATE(NOW()) <= DATE(b.to_date)))";
		}
		whereCondition += " AND (b.mode_of_req='GAD' OR b.mode_of_req='PUB') AND DATE(NOW()) <= DATE(b.to_date)";
		query = query + whereCondition + orderByCondition;
		logger.info(query);
		data = jdbcTemplate.query(query, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				return setModCancelReservationLstFromResultset(rs);
			}
		});

		return data;
	}

	private BookingResponseModel setModCancelReservationLstFromResultset(ResultSet rs) throws SQLException {
		BookingResponseModel data = new BookingResponseModel();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			String bookingDt = rs.getDate(2) != null ? sdf.format(rs.getDate(2)) : null;
			String frDt = rs.getDate(4) != null ? sdf1.format(rs.getTimestamp(4)) : null;
			String toDt = rs.getDate(5) != null ? sdf1.format(rs.getTimestamp(5)) : null;

			data.setBookingId(rs.getString(1));
			data.setBookingDt(bookingDt);
			data.setName(rs.getString(3));
			data.setFromDate(frDt);
			data.setToDate(toDt);
			data.setDesg(rs.getString(6));
			data.setMobile(rs.getString(7));
			data.setEmail(rs.getString(8));
			String status = rs.getString(9);
			String status_desc = "";
			if (status.equals("REQ")) {
				status_desc = " Not taken up for processing";
			}
			if (status.equals("CAN")) {
				status_desc = "Cancelled";
			}
			if (status.equalsIgnoreCase("MOD")) {
				status_desc = "Under processing";
			}
			if (status.equalsIgnoreCase("PRO")) {
				status_desc = "Approved";
			}
			if (status.equalsIgnoreCase("ALO")) {
				status_desc = "Allotted";
			}
			if (status.equals("CIN")) {
				status_desc = "Check In";
			}
			if (status.equals("COUT")) {
				status_desc = "Check Out";
			}
			if (status.equals("RCAN") || status.equals("MCAN")) {
				status_desc = "Cancelled";
			}
			data.setStatus(status);
			data.setStatusDesc(status_desc);
			int partialC = rs.getInt("partial_c");
			data.setCanStatus(rs.getString("can_status"));
			if (partialC == 1) {
				data.setTypeOfRoom("Partial Cancel");
			} else {
				data.setTypeOfRoom("Full Cancel");
			}
			data.setNoOfDays(rs.getInt("no_of_days"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	@Transactional
	public boolean cancelRequest1(BookingResponseModel requestPojo, String userId) {
		String sql = approverQueries.getProperty("updateStatusAndCanStatusByReqId");
		String sql1 = approverQueries.getProperty("createNoc");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Integer nocId = null;
		try {
			nocId = get_seqID("noc_id");
			update_seqID("noc_id", nocId);
			jdbcTemplate.update(sql, "RCAN", "RCAN", userId, null, requestPojo.getBookingId());

			if (requestPojo.getTwentyFourCancel() == 0) {
				jdbcTemplate.update(sql1, nocId, requestPojo.getBookingId(), sdf.parse(requestPojo.getFromDate()),
						sdf.parse(requestPojo.getToDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while cancelling request");
		}
		return true;
	}

	@Override
	public boolean cancelModRequest(String reqId) {
		String sql = adminQueries.getProperty("canModRequestForModTbl");
		String sql1 = adminQueries.getProperty("canModRequestForModRoomTbl");
		String sql2 = adminQueries.getProperty("canModRequestForGadTbl");
		jdbcTemplate.update(sql, new Object[] { "CAN", reqId });
		jdbcTemplate.update(sql1, new Object[] { "CAN", reqId });
		jdbcTemplate.update(sql2, new Object[] { "CAN", "MCAN", reqId });
		return true;
	}

		@Override
		public List<BookingResponseModel> getAllCancelDataForApprove(Map<String, Object> params) {List<BookingResponseModel> data;
	    String orderByCondition = " ORDER BY a.from_date DESC";
	    String query = approverQueries.getProperty("getCancelListForApproverSQL");
	
	    StringBuilder whereCondition = new StringBuilder();
	    whereCondition.append(" WHERE (a.can_status = 'ACAN' OR (c.status = 'ALO')) AND DATE(NOW()) <= a.to_date::date");
	
	    String cityId = (String) params.get("cId");
	    String ghId = (String) params.get("gId");
	
	    List<Object> queryParams = new ArrayList<>();
	
	    if (cityId != null && !cityId.isEmpty()) {
	        whereCondition.append(" AND a.gh_id = ?");
	        queryParams.add(cityId);
	    }
	    if (ghId != null && !ghId.isEmpty()) {
	        whereCondition.append(" AND a.block_id = ?");
	        queryParams.add(ghId);
	    }
	
	    query = query + whereCondition.toString() + orderByCondition;
	    logger.info(query);
	
	    try {
	        data = jdbcTemplate.query(query, queryParams.toArray(), new RowMapper<BookingResponseModel>() {
	            @Override
	            public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
	                return setCancelReservationLstFromApproverResultset(rs);
	            }
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error occurred while fetching cancellation list");
	    }
	    return data;
    }

	private BookingResponseModel setCancelReservationLstFromApproverResultset(ResultSet rs) throws SQLException {
		BookingResponseModel data = new BookingResponseModel();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			Date bookDate = rs.getDate("req_date") != null ? rs.getTimestamp("req_date") : null;
			String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
			String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
			data.setBookingId(rs.getString("req_id"));
			data.setBookingDt(sdf1.format(bookDate));
			data.setName(rs.getString("name"));
			//data.setNoOfChildren(rs.getString("concatenated_string"));
			data.setFromDate(frDt);
			data.setToDate(toDt);
			data.setMobile(rs.getString("mobile_no"));
			data.setEmail(rs.getString("email_id"));
			String status = rs.getString("status");
			String status_desc = "";
			if (status.equals("REQ")) {
				status_desc = " Not taken up for processing";
			}
			if (status.equals("CAN")) {
				status_desc = "Cancelled";
			}
			if (status.equalsIgnoreCase("MOD")) {
				status_desc = "Under processing";
			}
			if (status.equalsIgnoreCase("PRO")) {
				status_desc = "Approved";
			}
			if (status.equalsIgnoreCase("ALO")) {
				status_desc = "Allotted";
			}
			if (status.equals("CIN")) {
				status_desc = "Check In";
			}
			if (status.equals("COUT")) {
				status_desc = "Check Out";
			}
			if (status.equals("RCAN") || status.equals("MCAN")) {
				status_desc = "Cancelled";
			}						
			data.setStatusDesc(status_desc);
			
			int canStatus = rs.getInt("partial_c");
			String can_status_desc = "";
			if (canStatus == 1) {
				can_status_desc = "Partial Cancel";
			} else {
				can_status_desc = "Full Cancel";
			}
			data.setStatus(can_status_desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public List<ProcessModeration> getAllDetailsByProcessid(String processId, String type) {
		String status = "";
		List<ProcessModeration> data;
		String query = approverQueries.getProperty("getAllDetailsByProcessidSQL");
		if (type.equalsIgnoreCase("DRAFT")) {
			status = " AND a.status = 'TCNF'";
		} else {
			status = " AND a.status = 'CNF'";
		}
		query = query + status ;
		logger.info(query);
		data = jdbcTemplate.query(query, new RowMapper<ProcessModeration>() {
			public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
				ProcessModeration pm = new ProcessModeration();
				pm.setProcessId(rs.getString("process_id"));
				
				String status_desc = "";
				
				if (rs.getString("status").equals("TCNF")) {
					status_desc = "Temporary Confirm";
				}
				if (rs.getString("status").equals("CNF")) {
					status_desc = "Confirm";
				}
				pm.setStatus(status_desc);
				pm.setBlockName(rs.getString("block_name"));
				pm.setCityName(rs.getString("gh_name"));
				pm.setGoSign(rs.getString("go_sign"));
				pm.setCreatedDate(sdf.format(rs.getDate("created_date")));
				return pm;
			}
		}, processId);
		System.out.println("Confirmation query for process model--------" + query);
		return data;
	}

	@Override
	public List<ProcessModeration> getAllCanDetailsByProcessid(String processId, String type) {
		String status = "";
		List<ProcessModeration> data;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String query = approverQueries.getProperty("getAllCanDetailsByProcessidSQL");
		if (type.equalsIgnoreCase("DRAFT")) {
			status = " AND a.status = 'CREQ'";
		} else {
			status = " AND a.status = 'CCAN'";
		}
		query = query + status;
		logger.info(query);
		try {

			data = jdbcTemplate.query(query, new RowMapper<ProcessModeration>() {
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration pm = new ProcessModeration();
					pm.setProcessId(rs.getString("process_id"));
					pm.setBookingId(rs.getString("req_id"));
					String name=rs.getString("name");
					String address=rs.getString("address");
					Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
					Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
					pm.setFrmDt(sdf.format(fromDate));
					pm.setToDt(sdf.format(toDate));
					String status_desc = "";
					String status = rs.getString("status");
					if (status.equals("CREQ")) {
						status_desc = "Temporary Confirm";
					} else if (status.equals("CCAN")) {
						status_desc = "Confirm";
					}
					if (address != null && !address.isEmpty()) {
						name += "," + address;
					}
					pm.setName(name);
					pm.setStatus(status_desc);
					pm.setBlockName(rs.getString("block_name"));
					pm.setCityName(rs.getString("gh_name"));
					pm.setRoomNo(rs.getString("room_no"));
					String FormDate = sdf.format(fromDate);
					String ToDate = sdf.format(toDate);
					String periodOfStay = FormDate + " to " + ToDate;
					System.out.println("formdate and todate" + periodOfStay);
					pm.setPeriodOfStay(periodOfStay);
					int canStatus = rs.getInt("partial_c");
					if (canStatus == 1) {
						pm.setModeOfCancel("PARTIAL");
					} else {
						pm.setModeOfCancel("FULL");
					}
					return pm;
				}
			}, processId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("Cancelation query for process model request" + query);
		return data;
	}

	@Override
	public List<BookingResponseModel> getGadRequestTableDataForExtension(Map<String, Object> params) {
		List<BookingResponseModel> data;
		String orderByCondition = " ORDER BY a.req_id,a.guest_id";
		String query = approverQueries.getProperty("getGadRequestTableDataForExtensionSql");

		String whereCondition = "where b.status IN ('CIN', 'PCIN') 	AND b.to_date>now()";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String name = (String) params.get("name");
		String desg = (String) params.get("desg");
		String bookingDate = (String) params.get("fromDate");

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.block_id='" + ghId + "'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.req_id like'" + bookingId + "'";
		}
		if (name != null ? !name.isEmpty() : false) {
			whereCondition = whereCondition + " AND upper(name) like upper('%" + name + "%')";
		}
		if (desg != null ? !desg.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.mobile_no = '" + desg + "'";
		}
		if (bookingDate != null ? !bookingDate.isEmpty() : false) {
			Date date;
		
			try {
				date = sdf.parse(bookingDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			bookingDate = sdf1.format(date);
			whereCondition = whereCondition + " AND '" + bookingDate + "' between date(from_date) and date(to_date)";
		}
		query = query + whereCondition + orderByCondition;
		logger.info(query);
		data = jdbcTemplate.query(query, new RowMapper<BookingResponseModel>() {
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel pm = new BookingResponseModel();
				pm.setBookingId(rs.getString("req_id"));
				pm.setBookingDt(sdf.format(rs.getTimestamp("req_date")));
				pm.setFromDate(rs.getString("from_date"));
				pm.setToDate(rs.getString("to_date"));
				pm.setName(rs.getString("name"));
				pm.setDesg(rs.getString("designation"));
				pm.setMobile(rs.getString("mobile_no"));
				pm.setCityId(rs.getString("gh_id"));
				pm.setGhId(rs.getString("block_id"));
				return pm;
			}
		});
		return data;
	}

	@Override
	public boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception {
//		Integer id = get_seqID("req_id");
//		update_seqID("req_id", id);
//		String bookingId = "G" + id.toString();
		String reqId = requestPojo.getReqId();
		String bookingId = reqId + "E";
		Boolean status = false;
		logger.info("BookingId " + bookingId);
		requestPojo = getBookingDetails(requestPojo);
		System.out.println("Previous Booking Details : " + requestPojo);
		// requestPojo.setReqId(bookingId);

		// Request Details Save
		// requestPojo.setFromDate(DateUtils.addHours(requestPojo.getFromDate(), 1));
		String sql = approverQueries.getProperty("saveBookingDetailsSQL");
		int i = jdbcTemplate.update(sql,
				new Object[] { bookingId, requestPojo.getGhId(), requestPojo.getReq_date(), requestPojo.getFromDate(),
						requestPojo.getToDate(), requestPojo.getNor_req(), requestPojo.getNob_g_req(),
						requestPojo.getStatus(), 0, requestPojo.getMode_of_req(), requestPojo.getPurpose(),
						requestPojo.getExt(), requestPojo.getAddress(), requestPojo.getMobile_no(),
						requestPojo.getReq_date(), requestPojo.getNod_g_req(), requestPojo.getMobile_no(),
						requestPojo.getExisting_req_id(), 0, requestPojo.getBlock_id(), requestPojo.getFile_path(),
						requestPojo.getCategory_type(), requestPojo.getDesg(), requestPojo.getRoomType(),
						requestPojo.getEmail(), requestPojo.getCatg_file_path(), requestPojo.getDuring_stay_conveancy(),
						requestPojo.getBookingFor(), requestPojo.getNoOfDays(), requestPojo.getNoOfAdults(),
						requestPojo.getNoOfChildren(), requestPojo.getCatgDocType(), userId, userId });
		List<GuestDetailsResponse> list = getExtensionGuestDetails(reqId);
		if (list != null) {
			sql = approverQueries.getProperty("saveGuestDetailsSQL");
			for (GuestDetailsResponse item : list) {
				Integer id = get_seqID("guest_id");
				String bookingId1 = "G_" + id.toString();
				update_seqID("guest_id", id);
				jdbcTemplate.update(sql,
						new Object[] { bookingId, bookingId1, item.getGuestName(), item.getAge(), item.getGender(),
								item.getAddress(), item.getDesg(), item.getDepartment(), item.getGuestMobile(),
								item.getGuestDocType(), item.getGuestDocPath(), item.getGuestId() });
			}
			status = true;
		}

		if (i != 0 && status == true) {
			sql = approverQueries.getProperty("changeStatusinExtension");
			jdbcTemplate.update(sql, new Object[] { "PEXT", reqId });
			status = true;
		}

		return status;
	}

	public ExtensionRequestModel getBookingDetails(ExtensionRequestModel response) throws Exception {
		String sql = adminQueries.getProperty("getExensionBookingDetailsByIdSQL");
		final Date toDate = response.getToDate();
		Integer noOfDays = response.getNoOfDays();
		List<ExtensionRequestModel> users = jdbcTemplate.query(sql, new RowMapper<ExtensionRequestModel>() {
			@Override
			public ExtensionRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				ExtensionRequestModel res = new ExtensionRequestModel();
				res.setGhId(rs.getString("gh_id"));
				res.setAddress(rs.getString("address"));
				res.setBlock_id(rs.getString("block_id"));
				res.setCategory_type(rs.getString("category_type"));
				res.setDesg(rs.getString("desg"));
				res.setExisting_req_id(rs.getString("req_id"));
				res.setExt("1");
				res.setFile_path(rs.getString("file_path"));
				res.setFromDate(rs.getTimestamp("to_date"));
				res.setToDate(toDate);
				res.setMobile_no(rs.getString("mobile_no"));
				res.setMode_of_req("GAD");
				res.setNob_g_req(rs.getString("nob_g_req"));
				res.setNod_g_req(rs.getString("nod_g_req"));
				res.setNor_req(rs.getString("nor_req"));
				res.setPurpose(rs.getString("purpose"));
				res.setRoomType(rs.getString("roomtype"));
				res.setReq_date(new Date());
				res.setStatus("REQ");
				res.setCatg_file_path(rs.getString("catg_file_path"));
				res.setDuring_stay_conveancy(rs.getString("during_stay_conveancy"));
				res.setEmail(rs.getString("email_id"));
				res.setNoOfAdults(rs.getInt("no_of_adults"));
				res.setNoOfChildren(rs.getInt("no_of_children"));
				res.setBookingFor(rs.getString("booking_for"));
				res.setNoOfDays(noOfDays);
				res.setCatgDocType(rs.getString("catgtype"));
				return res;
			}
		}, response.getReqId());

		if (null != users && !users.isEmpty()) {
			response = users.get(0);
		}
		return response;
	}

	public List<GuestDetailsResponse> getExtensionGuestDetails(String bookingId) throws Exception {
		String sql = approverQueries.getProperty("getGuestDetailsByIdSQL");
		List<GuestDetailsResponse> users = jdbcTemplate.query(sql, new RowMapper<GuestDetailsResponse>() {
			@Override
			public GuestDetailsResponse mapRow(ResultSet rs, int rownumber) throws SQLException {
				GuestDetailsResponse res = new GuestDetailsResponse();
				res.setGuestId(rs.getString("guest_id"));
				res.setAddress(rs.getString("address"));
				res.setAge(rs.getString("age"));
				res.setGender(rs.getString("gender"));
				res.setGuestName(rs.getString("name"));
				res.setDesg(rs.getString("designation"));
				res.setDepartment(rs.getString("department"));
				res.setGuestMobile(rs.getString("guest_mobile"));
				res.setGuestDocType(rs.getString("guest_doc_type"));
				res.setGuestDocPath(rs.getString("guest_doc_path"));
				return res;
			}
		}, bookingId);

		return users;
	}

	@Override
	public Map<String, Object> saveNewShiftData(String userId, Map<String, Object> params) {
		Map<String, Object> resp=new HashMap<>();
		String ghId = (String) params.get("cityName");
		String blockId = (String) params.get("guesthouseName");
		ShiftMasterModel st=checkStatusInShiftMaster(ghId,blockId);
		if(st!=null) {
			resp.put("status", false);
			resp.put("msg", "Shift already open");
			return resp;
		}
		resp.put("status", false);
		resp.put("msg", "Something went wrong");
		String query = adminQueries.getProperty("saveNewShiftDataSQL");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		
		
		String openTime = (String) params.get("openTime");
		String openBalRefund = (String) params.get("openBalRefund");
		String openBalRegular = (String) params.get("openBalRegular");
		String openBalTotal = (String) params.get("openBalTotal");
		String shift = (String) params.get("shift");

		String fromDate = "";
		String toDate = "";
		String onlyDate = null;
		String nextDate = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		onlyDate = format1.format(cal.getTime());

		Calendar call = Calendar.getInstance();
		call.add(Calendar.DATE, 1);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		nextDate = sdf1.format(call.getTime());

		if (shift.equalsIgnoreCase("1")) {
			fromDate = onlyDate + " 06:00";
		}
		if (shift.equalsIgnoreCase("1")) {
			toDate = onlyDate + " 14:00";
		}
		if (shift.equalsIgnoreCase("2")) {
			System.out.println("Within Shift 2");
			fromDate = onlyDate + " 14:01";
		}
		if (shift.equalsIgnoreCase("2")) {
			toDate = onlyDate + " 22:00";
		}
		if (shift.equalsIgnoreCase("3")) {
			fromDate = onlyDate + " 22:01";
		}
		if (shift.equalsIgnoreCase("3")) {
			toDate = nextDate + " 05:59";
		}
		Date fromTime = null;
		Date toTime = null;
		Date openDate = null;
		try {
			fromTime = !fromDate.equals("") ? sdf.parse(fromDate) : null;
			toTime = !toDate.equals("") ? sdf.parse(toDate) : null;
			openDate = !openTime.equals("") ? sdf.parse(openTime) : null;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Integer id = 0;
		try {
			id = get_seqID("shift_id");
			update_seqID("shift_id", id);
			logger.info(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String shift_id = id.toString();

		Integer status = jdbcTemplate.update(query, new Object[] { shift_id, ghId, userId, openDate, openBalRefund,
				openBalRegular, openBalTotal, 'O', shift, fromTime, toTime, blockId });

		if (status == 1) {
			resp.put("status", true);
			resp.put("msg", "Opened a new shift successfully");
		}
		return resp;
	}

	@Override
	public ShiftMasterModel checkStatusInShiftMaster(String cityId, String blockId) {
		List<ShiftMasterModel> data = new ArrayList<ShiftMasterModel>();
		ShiftMasterModel response = null;
		String sql = adminQueries.getProperty("isShiftOpenInShiftMasterSql");
		try {
			data = jdbcTemplate.query(sql, new RowMapper<ShiftMasterModel>() {
				public ShiftMasterModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					ShiftMasterModel sm = new ShiftMasterModel();
					sm.setBlockId(rs.getString("block_id"));
					sm.setCityId(rs.getString("gh_id"));
					sm.setShiftId(rs.getString("par_shiftid"));
					sm.setUserId(rs.getString("createdby"));
					sm.setStatus(rs.getString("status"));
					sm.setShiftNum(rs.getString("shift_id"));
					return sm;
				}
			}, cityId, blockId);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		if (null != data && !data.isEmpty()) {
			response = data.get(0);
		}
		return response;
	}

	@Override
	public List<BookingResponseModel> getAllConveyanceDataForApprove(HttpServletRequest request) {
		List<BookingResponseModel> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			cityName = request.getParameter("cityName");
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			guesthouseName = request.getParameter("guesthouseName");
		if (cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("ConveyanceListSQL");
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					BookingResponseModel response = new BookingResponseModel();
					response.setCityId(rs.getString("gh_id"));
					response.setGhId(rs.getString("block_id"));
					response.setTypeOfRoom(rs.getString("roomtype"));
					response.setBookingId(rs.getString("rid"));
					response.setBookingDt(rs.getString("req_date"));
					response.setArrivalDate(rs.getString("arrival_time"));
					response.setDepartureDate(rs.getString("departure_time"));
					response.setName(rs.getString("name"));
					String Category_Desc = "";
					if (rs.getString("quota_type").equalsIgnoreCase("C1")) {
						Category_Desc = "Category 1";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C2")) {
						Category_Desc = "Category 2";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C3")) {
						Category_Desc = "Category 3";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C4")) {
						Category_Desc = "Category 4";
					}
					response.setCategory(Category_Desc);
					response.setStatus(rs.getString("status"));
					return response;
				}

			});

		}
		if (!cityName.equals("") && !guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("ConveyanceListWithGHouseAndCitySQL");	
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					String bookDate = rs.getDate("req_date") != null ? sdf.format(rs.getTimestamp("req_date")) : null;
					String fromTime = rs.getDate("arrival_time") != null ? sdf.format(rs.getTimestamp("arrival_time")) : null;
					String toTime = rs.getDate("departure_time") != null ? sdf.format(rs.getTimestamp("departure_time")) : null;
					BookingResponseModel response = new BookingResponseModel();
					response.setCityId(rs.getString("gh_id"));
					response.setGhId(rs.getString("block_id"));
					response.setTypeOfRoom(rs.getString("roomtype"));
					response.setBookingId(rs.getString("rid"));
					response.setBookingDt(bookDate);
					response.setArrivalDate(fromTime);
					response.setDepartureDate(toTime);
					response.setName(rs.getString("name"));
					String Category_Desc = "";
					if (rs.getString("quota_type").equalsIgnoreCase("C1")) {
						Category_Desc = "Category 1";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C2")) {
						Category_Desc = "Category 2";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C3")) {
						Category_Desc = "Category 3";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C4")) {
						Category_Desc = "Category 4";
					}
					response.setCategory(Category_Desc);
					response.setStatus(rs.getString("status"));
					return response;
				}

			}, cityName, guesthouseName);
		}
		return alldetailsValue;
	}

	@Override
	@Transactional
	public boolean saveCancelModeration(String userId, BookingResponseModel requestPojo) throws Exception {
		String extensionExists = adminQueries.getProperty("extensionExists");
		String prevReqBackOperation = adminQueries.getProperty("prevReqBackOperation");
		String dltGad = adminQueries.getProperty("deleteFromGad");		
		String dltGst = adminQueries.getProperty("deleteFromGuest");
		String query = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		Boolean status = false;
		String mobileNo = null;
		String cancatMsg = null;
		String emailId = null;
		Integer canId = 0;
		canId = get_seqID("cancel_id");
		update_seqID("cancel_id", canId);
		RoomBookingPojo pd = new RoomBookingPojo();
		try {
			pd = admingetBookingDetailsById(requestPojo.getBookingId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		mobileNo = pd.getMob();
		emailId = pd.getEmailId();
		System.out.println("Guest Mobile Number ==== " + mobileNo);
		if (requestPojo.getRemarks() != null) {
			cancatMsg = requestPojo.getModReason() + " " + requestPojo.getRemarks();
		}else {
			cancatMsg = requestPojo.getModReason();
		}
		int i = 0;
		String sql = adminQueries.getProperty("saveCancelModerationSQL");
		String updSql = adminQueries.getProperty("updateCancelModerationSQL");
		int countCanRes = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM cancel_reservation where req_id = ? ",
				 new Object[] {requestPojo.getBookingId()}, Integer.class);
		
		if(countCanRes > 0) {
			i = jdbcTemplate.update(updSql, new Object[] { "CCAN", "MODCAN", cancatMsg, requestPojo.getBookingId()});
		} else {
			i = jdbcTemplate.update(sql, new Object[] { canId, requestPojo.getBookingId(), null,
					requestPojo.getCityId(), requestPojo.getGhId(), "CCAN", null, "MODCAN", cancatMsg });
		}
		if (i != 0) {
			sql = adminQueries.getProperty("changeStatusinGadReqTblSQL");
			jdbcTemplate.update(sql, new Object[] { "CAN", requestPojo.getBookingId() });
			status = true;
		}
		if(requestPojo.getModReason().equals("others")) {
			requestPojo.setModReason(requestPojo.getRemarks());
		}
		ExtensionRequestModel extensionRequestModel = new ExtensionRequestModel();
		try {
			extensionRequestModel = jdbcTemplate.queryForObject(extensionExists, (rs1, rowNum) -> {
				ExtensionRequestModel erm = new ExtensionRequestModel();
				erm.setExt(rs1.getString("ext"));
				erm.setExisting_req_id(rs1.getString("existing_req_id"));
				return erm;
			}, requestPojo.getBookingId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Extension record fecth time");
		}
		if (extensionRequestModel.getExt().equals("1")) {
			Integer noOfGst = null;
			Integer noOfChckGst = null;
			noOfGst = jdbcTemplate.queryForObject(query, new Object[] { extensionRequestModel.getExisting_req_id() }, Integer.class);
			noOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { extensionRequestModel.getExisting_req_id() }, Integer.class);
			if (noOfGst == noOfChckGst) {
				jdbcTemplate.update(prevReqBackOperation,
						new Object[] { "CIN", "PEXT", extensionRequestModel.getExisting_req_id() });
			} else {
				jdbcTemplate.update(prevReqBackOperation,
						new Object[] { "PCIN", "PEXT", extensionRequestModel.getExisting_req_id() });
			}
			jdbcTemplate.update(dltGad, new Object[] { requestPojo.getBookingId() });
			jdbcTemplate.update(dltGst, new Object[] { requestPojo.getBookingId() });
		}
		
		try {
			String message = "Your Request Number " + requestPojo.getBookingId() +
					" for guest house is cancelled for reason "+requestPojo.getModReason() +
					", Odisha Home Dept";
			System.out.println(message);
			if (mobileNo != null && !mobileNo.isEmpty()) {
				String templateID = "1107160707775267623";
				smsSend.sendSMS(mobileNo, message, templateID);
			}
//			if (emailId != null && !emailId.isEmpty()) {
//				mail.sendMimeMail(emailId, "Request Cancelled", message);
//			}
		} catch (Exception e) {
			logger.error("Error occurred in sms " + e.getMessage());
		}
		return status;
	}
	
	@Override
	public DataTableResponse getModifiedAllotmentList(DataTableRequest params) {
		System.out.println(params.toString());

		String sql = approverQueries.getProperty("getModifiedAllotmentReportSQL");
		String sqlCount = approverQueries.getProperty("getModifiedAllotmentReportSQLCount");
		StringBuilder whereCondition = new StringBuilder(" WHERE a.status != 'CAN' AND b.status = 'MODF'");
		String groupBy=" group by c.req_id,d.name,c.from_date,c.to_date,b.prev_order_no,b.subject,a.status";
				
		Map<String, Object> queryParams = new HashMap<>();

		String cityId = params.getCity();
		String ghId = params.getgHouse();
		String bookingId = params.getBookingId();		
		String mobNo = params.getMobNo();
		String name = params.getName();
		int start = params.getStart();
		
		System.out.println("cityId"+cityId+"GuestHouse"+ghId);				

		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false) {
			System.out.println("inside city block");
			whereCondition.append(" AND c.gh_id = :cityId");
	        queryParams.put("cityId", cityId);
		} 
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			System.out.println("inside guest house block");
			whereCondition.append(" AND c.block_id = :ghId");
	        queryParams.put("ghId", ghId);
		}	
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition.append(" AND c.req_id = :bookingId");
	        queryParams.put("bookingId", bookingId);
		}
		if (name != null ? !name.isEmpty() : false) {
			whereCondition.append(" AND d.name ILIKE :name");
	        queryParams.put("name", "%" + name + "%");
		}
		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition.append(" AND c.mobile_no = :mobNo");
	        queryParams.put("mobNo", mobNo);
		}		
		
		sql += whereCondition+groupBy;
		final String countSql = sqlCount + whereCondition;	
		
		if (params.getLength() != 0) {
	        sql += " LIMIT :length OFFSET :start";
	        queryParams.put("length", params.getLength());
	        queryParams.put("start", start);
	    }
		
		final String query = sql;

	    ExecutorService executorService = Executors.newFixedThreadPool(2);

	    CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
	        try {
	        	NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	            return namedParameterJdbcTemplate.queryForObject(countSql, queryParams, Integer.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }, executorService);
	    
	    CompletableFuture<List<RoomDetails>> dataFuture = CompletableFuture.supplyAsync(() -> {
	    	NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	        return namedParameterJdbcTemplate.query(query, queryParams, new RowMapper<RoomDetails>() {
	            @Override
	            public RoomDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
	                RoomDetails rd = new RoomDetails();
	                rd.setBookingId(rs.getString("req_id"));
	                rd.setName(rs.getString("name"));
	                rd.setFromDate(rs.getString("from_date"));
	                rd.setToDate(rs.getString("to_date"));
	                rd.setPrevOrderNo(rs.getString("prev_order_no"));
	                rd.setReason(rs.getString("subject"));
	                rd.setRoomNo(rs.getString("prev_room_no"));
	                rd.setRoomRent(rs.getString("prev_room_rent"));
	                rd.setModifiedRoomNo(rs.getString("room_no"));
	                rd.setModifiedRoomRent(rs.getString("room_rent"));
	                return rd;
	            }
	        });
	    }, executorService);
	    
	    DataTableResponse dt = new DataTableResponse();
	    try {
	        dt.setDraw(params.getDraw());
	        dt.setRecordsTotal(countFuture.get());
	        dt.setRecordsFiltered(countFuture.get());
	        dt.setData(dataFuture.get());
	        System.out.println("+++++++" + countFuture.get());
	    } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
	    } finally {
	        executorService.shutdown();
	    }
		return dt;
	}
	
	@Override
	public DataTableResponse getShiftReportList(DataTableRequest params) {
		System.out.println(params.toString());
		List<ShiftMasterModel> shiftList=new ArrayList<ShiftMasterModel>();

		String sql = approverQueries.getProperty("getShiftReportSQL");
		String sql2 = approverQueries.getProperty("getShiftReportCountSQL");
		String whereCondition = "WHERE 1=1";
		String orderBy="ORDER BY lastmodified desc";
		
		String cityId = params.getCity();
		String ghId = params.getgHouse();
		String shiftId = params.getShiftId();		
		String createdBy = params.getCreatedBy();
		String createdDate = params.getCreatedDate();
		int start = params.getStart();

		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false) {
			System.out.println("inside city block");
			whereCondition ="where a.gh_id='"+cityId+"'";
		} 
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			 System.out.println("inside guest house block");
			whereCondition =  whereCondition +  "AND a.block_id='"+ghId+"'";
		}	
		if (shiftId != null ? !shiftId.isEmpty() : false) {
			whereCondition = whereCondition + " AND a.shift_id='" + shiftId + "'";
		}

		if (createdBy != null ? !createdBy.isEmpty() : false) {
			whereCondition = whereCondition + " AND a.createdby ILIKE '%" + createdBy + "%'";
		}			
		
		if (createdDate != null ? !createdDate.isEmpty() : false) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(createdDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			createdDate = sdf1.format(date);
			whereCondition = whereCondition + " AND '" + createdDate + "' between date(from_time) and date(to_time)";
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		sql += whereCondition + orderBy;
		final String countSql = sql2 + whereCondition;
		
		if (params.getLength() != 0) {
	        sql += " LIMIT " + params.getLength() + " OFFSET " + start;
	    }
		
		final String query = sql;
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
	    CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
	        try {
	            return jdbcTemplate.queryForObject(countSql, Integer.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }, executorService);
	    
	    CompletableFuture<List<ShiftMasterModel>> dataFuture = CompletableFuture.supplyAsync(() -> {
	        return jdbcTemplate.query(query, new RowMapper<ShiftMasterModel>() {
	            @Override
	            public ShiftMasterModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	                ShiftMasterModel sm = new ShiftMasterModel();
	                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	                sm.setShiftId(rs.getString("shift_id"));
	                sm.setUserId(rs.getString("createdby"));
	                sm.setBlockId(rs.getString("block_name"));
	                sm.setCityId(rs.getString("gh_name"));
	                sm.setCreatedTime(rs.getTimestamp("createdtime") != null ? sdf1.format(rs.getTimestamp("createdtime")) : null);
	                sm.setCloseTime(rs.getTimestamp("close_time") != null ? sdf1.format(rs.getTimestamp("close_time")) : "Not Closed");
	                return sm;
	            }
	        });
	    }, executorService);

	    DataTableResponse dt = new DataTableResponse();
	    try {
	        dt.setDraw(params.getDraw());
	        dt.setRecordsTotal(countFuture.get());
	        dt.setRecordsFiltered(countFuture.get());
	        dt.setData(dataFuture.get());
	        System.out.println("+++++++"+countFuture.get());
	    } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
	    } finally {
	        executorService.shutdown();
	    }

	    return dt;
	}
	
	public String getGuestDetailsForOrder(String req_id)throws Exception{				
		String sql = "select * from guest_details where req_id=? order by guest_id";
		List<String> guestList = new ArrayList<>();
		try {
		    guestList = jdbcTemplate.query(sql, new Object[]{req_id}, rs -> {
		        List<String> list = new ArrayList<>();
		        int i = 0;
		        while (rs.next()) {
		            i++;
		            StringBuilder sb = new StringBuilder();
		            if(i>1) {
		            	sb = sb.append(" & ");
		            }
		            sb.append(rs.getString("name"));
		            String t_desig = rs.getString("designation") != null ? rs.getString("designation") : "";
		            if (!t_desig.equals("")) {
		                sb.append(",").append(t_desig);
		            }
		            String t_depa = rs.getString("department") != null ? rs.getString("department") : "";
		            if (!t_depa.equals("")) {
		                sb.append(",").append(t_depa);
		            }
		            list.add(sb.toString());
		        }
		        return list;
		    });
		} catch (Exception e) {
		    e.printStackTrace();
		}
		String totalDetails = "";
		for (String guest : guestList) {
			totalDetails = totalDetails + guest;
		}
		return totalDetails;
	}
	
	@Override
	public List<ReceiptModel> getPartialDuesReportDetails(Map<String, Object> params, HttpSession session) {
		System.out.println("inside dao impl");
		List<ReceiptModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY req_id DESC";
		String query = adminQueries.getProperty("getPartialDuesReportDetailsSQL");
		String whereCondition = " where a.status = 'RECT' AND a.full_partial = 'P' ";

		Map<String, Object> queryParams = new HashMap<>();
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String fromDate = (String) params.get("fromDate");		

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition += " AND c.gh_id = :cityId";
	        queryParams.put("cityId", cityId);
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			whereCondition += " AND c.gh_id IN (:cityIds)";
	        queryParams.put("cityIds", cityIdList);
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition += " AND c.block_id = :ghId";
	        queryParams.put("ghId", ghId);
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition += " AND c.req_id = :bookingId";
	        queryParams.put("bookingId", bookingId);
		}
		if (fromDate != null ? !fromDate.isEmpty() : false) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(fromDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			fromDate = sdf1.format(date);
	        whereCondition += " AND c.from_date::date = CAST(:fromDate AS DATE)";
	        queryParams.put("fromDate", fromDate);
		}
	
		query += whereCondition + orderByCondition;
		logger.info(query);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		data = namedParameterJdbcTemplate.query(query, queryParams, new RowMapper<ReceiptModel>() {
			@Override
			public ReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				ReceiptModel rm = new ReceiptModel();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String billDt = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
				rm.setBookingId(rs.getString("req_id"));
				rm.setBillNo(rs.getString("billno"));
				rm.setBillId(rs.getString("bill_id"));
				rm.setGhId(rs.getString("block_id"));
				rm.setName(rs.getString("name"));
				rm.setFromDate(frDt);
				rm.setToDate(toDt);
				rm.setTotal(rs.getString("total"));
				rm.setPaidAmount(rs.getString("advance"));
				rm.setDueAmount(rs.getString("paidTo"));
				rm.setDate(billDt);
				rm.setOnlyName(rs.getString("onlyname"));
				rm.setMobileNo(rs.getString("mobile_no"));
				return rm;
			}
		});
		System.out.println("partial dues report   "+query);
		logger.info(data);
		return data;
	}
	
	@Override
	public List<ReceiptModel> getDuesReportDetails(Map<String, Object> params, HttpSession session) {
		List<ReceiptModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY req_id DESC";
		String query = adminQueries.getProperty("getDuesReportDetailsSQL");
		String whereCondition = " where a.status = 'BILL' ";
		
		Map<String, Object> queryParams = new HashMap<>();

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String fromDate = (String) params.get("fromDate");		

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition += " AND c.gh_id = :cityId";
	        queryParams.put("cityId", cityId);
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			whereCondition += " AND c.gh_id IN (:cityIds)";
	        queryParams.put("cityIds", cityIdList);
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition += " AND c.block_id = :ghId";
		    queryParams.put("ghId", ghId);
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition += " AND c.req_id = :bookingId";
	        queryParams.put("bookingId", bookingId);
		}
		if (fromDate != null ? !fromDate.isEmpty() : false) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(fromDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			fromDate = sdf1.format(date);
			whereCondition += " AND c.from_date::date = CAST(:fromDate AS DATE)";
	        queryParams.put("fromDate", fromDate);
		}
	
		query = query + whereCondition + orderByCondition;
		logger.info(query);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		data = namedParameterJdbcTemplate.query(query, queryParams, new RowMapper<ReceiptModel>() {
			@Override
			public ReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				ReceiptModel rm = new ReceiptModel();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String billDt = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
				rm.setBookingId(rs.getString("req_id"));
				rm.setBillNo(rs.getString("billno"));
				rm.setName(rs.getString("name"));
				rm.setFromDate(frDt);
				rm.setToDate(toDt);
				rm.setTotal(rs.getString("total"));
				rm.setPaidAmount(rs.getString("advance"));
				rm.setDueAmount(rs.getString("paidTo"));
				rm.setDate(billDt);
				return rm;
			}
		});
		logger.info(data);
		return data;
	}

	
	@Override
	public List<ReceiptModel> getDuesListReportDetails(Map<String, Object> params, HttpSession session) {
		List<ReceiptModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY req_id DESC";
		String query = adminQueries.getProperty("getDuesListReportDetailsSQL");
		String sql = adminQueries.getProperty("getModeOfPayment");
		String whereCondition = " where 1=1";
		
		Map<String, Object> queryParams = new HashMap<>();

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String fromDate = (String) params.get("fromDate");		

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition += " AND c.gh_id = :cityId";
	        queryParams.put("cityId", cityId);
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			whereCondition += " AND c.gh_id IN (:cityIds)";
	        queryParams.put("cityIds", cityIdList);
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition += " AND c.block_id = :ghId";
		    queryParams.put("ghId", ghId);
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition += " AND c.req_id = :bookingId";
	        queryParams.put("bookingId", bookingId);
		}
		if (fromDate != null ? !fromDate.isEmpty() : false) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(fromDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			fromDate = sdf1.format(date);
			whereCondition += " AND c.from_date::date = CAST(:fromDate AS DATE)";
	        queryParams.put("fromDate", fromDate);
		}
	
		query += whereCondition + orderByCondition;
		logger.info(query);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		data = namedParameterJdbcTemplate.query(query, queryParams, new RowMapper<ReceiptModel>() {
			@Override
			public ReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				ReceiptModel rm = new ReceiptModel();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String billDt = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
				String recptDt = rs.getDate("date_rece") != null ? sdf1.format(rs.getTimestamp("date_rece")):null;
				rm.setBookingId(rs.getString("req_id"));
				rm.setBillNo(rs.getString("billno"));
				rm.setName(rs.getString("name"));
				rm.setFromDate(frDt);
				rm.setToDate(toDt);
				rm.setPaidAmount(rs.getString("balance"));				
				rm.setDate(billDt);
				rm.setOnlyName(rs.getString("onlyname"));
				rm.setRectDate(recptDt);
				rm.setMobileNo(rs.getString("mobile_no"));
				rm.setBillId(rs.getString("bill_id"));
				rm.setRecId(rs.getString("rec_id"));
				rm.setPaymentModes(jdbcTemplate.queryForList(sql, rm.getRecId()));
				String catg = rs.getString("category_type");
				String Category_Desc = "";
				if (catg.equalsIgnoreCase("C1")) {
					Category_Desc = "Category 1";
				}
				if (catg.equalsIgnoreCase("C2")) {
					Category_Desc = "Category 2";
				}
				if (catg.equalsIgnoreCase("C3")) {
					Category_Desc = "Category 3";
				}
				if (catg.equalsIgnoreCase("C4")) {
					Category_Desc = "Category 4";
				}
				rm.setCategory(Category_Desc);
				rm.setGuestHouseName(rs.getString("block_name"));
				rm.setCityName(rs.getString("gh_name"));
				return rm;
			}
		});
		System.out.println("query for dues report  "+query);
		logger.info(data);
		return data;
	}
	
	@Override
	@Transactional
	public boolean savePartialDueReceipt(ReceiptModel model) {
		String sql = adminQueries.getProperty("createReceipt");
		String sql1 = adminQueries.getProperty("createReceiptPaymentMode");
		String sql2 = adminQueries.getProperty("createReceiptVsBill");
		String sql3 = adminQueries.getProperty("updateBillFullPartialStatusByBillId");
		String sql4 = adminQueries.getProperty("updateDuesStatusByBillId");
		String sql5 = adminQueries.getProperty("pendingDuesFillupResponse");
		AtomicReference<String> orderId = new AtomicReference<>("");
		AtomicReference<Double> onlineAmount = new AtomicReference<>(0.0);

		try {
			Integer id = get_seqID("rec_id");
			update_seqID("rec_id", id);

			double totalPaid = model.getMode().entrySet().stream()
					.mapToDouble(e -> {
						if (e.getKey().equalsIgnoreCase("online")) {
							String q = approverQueries.getProperty("getRectOnlinePaymentDetails");
							PGModel pg = jdbcTemplate.queryForObject(q, new Object[] {model.getBillId()}, (rs, rn) -> {
								PGModel p = new PGModel();
								p.setOrderId(rs.getString("order_id"));
								p.setAmount(rs.getString("amount"));
								p.setBankRefNo(rs.getString("bank_ref_no"));
								return p;
							});
							orderId.set(pg.getOrderId());
							onlineAmount.set(Double.parseDouble(pg.getAmount()));
							return Double.parseDouble(pg.getAmount());
						} else
							return e.getValue();
					}).sum();

			String fullPartial = "F";
			if (totalPaid != Double.parseDouble(model.getTotal())) {
				return false;
			}

			jdbcTemplate.update(sql, id, totalPaid, "", "", "RECT", "", model.getMobileNo(), model.getCityId(),
					model.getTotal(), fullPartial, model.getGhId(), "", "");

			for (Map.Entry<String, Double> entry : model.getMode().entrySet()) {
				Integer rpmId = get_seqID("rpm_id");
				update_seqID("rpm_id", rpmId);
				if (entry.getKey().equalsIgnoreCase("CASH")) {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(), "", "", "");
				} else if (entry.getKey().equalsIgnoreCase("POS")) {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(), model.getTransactionNo(), "",
							"");
				} else {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), onlineAmount.get(),
							"", orderId.get(), "");
				}
			}

			jdbcTemplate.update(sql2, id, model.getBillId());
			jdbcTemplate.update(sql3, "F", model.getBillId());
			jdbcTemplate.update(sql4, id,"PAID", model.getBillId());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		GuestDetailsPojo gdp = jdbcTemplate.queryForObject(sql5, (rs, rn) -> {
			GuestDetailsPojo g = new GuestDetailsPojo();
				g.setGuestName(rs.getString("name"));
				g.setGuestMobile(rs.getString("mobile_no"));
				g.setGuestAddr(rs.getString("email_id"));
				g.setGuestDepartment(rs.getString("block_name"));
				g.setGuestDesignation(rs.getString("gh_name"));
				final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String frmDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
				g.setGuestDocPath(frmDt);
				g.setGuestDocType(toDt);
				return g;
		}, model.getBookingId());
		
		String subject = "Dear "+gdp.getGuestName()+", amount of Rs "+model.getTotal()+" has been successfully received towards your"
				+ " pending arrear dues from "+gdp.getGuestDocPath()+" to "+gdp.getGuestDocType()+" in "+gdp.getGuestDepartment()+", "+gdp.getGuestDesignation()
				+ " - Odisha Home Dept";
//		try {
//			if (gdp.getGuestAddr() != null) {
//				if (!gdp.getGuestAddr().isEmpty()) {
//					mail.sendMimeMail(gdp.getGuestAddr(), "Dues Payment", subject);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send..");
//		}
		System.out.println(subject);
		String templateId = "1107168975953100826";
		try {
			if (gdp.getGuestMobile() != null) {
				if (!gdp.getGuestMobile().isEmpty()) {
					smsSend.sendSMS(gdp.getGuestMobile(), subject, templateId);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		return true;
	}
	
	@Override
	public ConfrenceHall getConferenceBookingDetailsByBookingId(String bookingId) {
		List<ConfrenceHall> data = new ArrayList<>();
		ConfrenceHall response = null;
		String query = approverQueries.getProperty("getConferenceBookingDetailsByBookingIdSQL");
		final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		logger.info(bookingId);
		 data = jdbcTemplate.query(query, new RowMapper<ConfrenceHall>() {
			@Override
			public ConfrenceHall mapRow(ResultSet rs, int rownumber) throws SQLException {
				ConfrenceHall res = new ConfrenceHall();
				String fromDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String bookDt = rs.getDate("req_date") != null ? sdf1.format(rs.getTimestamp("req_date")) : null;
				res.setBookingId(rs.getString("req_id"));
				res.setBookingDt(bookDt);
				res.setFromDate(fromDt);
				res.setToDate(toDt);
				res.setName(rs.getString("name"));
				res.setMobile(rs.getString("mobile_no"));
				res.setAddress(rs.getString("address"));
				res.setCaetgoryType(rs.getString("category"));
				res.setConfrenceDesc(rs.getString("hall_category"));
				res.setConfreqnceUseDesc(rs.getString("booking_type"));
				res.setCharge(rs.getString("charge"));
				res.setAdditonalCharge(rs.getString("additionalcharge"));
				res.setAdvanceAmount(rs.getString("advance_amount"));
				res.setCityName(rs.getString("gh_name"));
				res.setGuestHouseName(rs.getString("block_name"));
				res.setCapacity(rs.getString("capacity"));
				return res;

			}
		}, bookingId);
		logger.info(data);
		if (null != data && !data.isEmpty()) {
			response = data.get(0);
		}		
		return response;
	}
	/* Prasad AdminDaoImpl Ends */

	/* Mohit AdminDaoImpl Starts */

	@Override
	public List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit) {
		System.out.println("city    "+city+"   guest house   "+gHouse);
		String sql = adminQueries.getProperty("getReqListByCityAndGH");
		String whereCondition = "";
		if (city != null ? !city.isEmpty() : false) {
			whereCondition = whereCondition+"AND gh_id='" + city + "'";
		}
		if (gHouse != null ? !gHouse.isEmpty() : false) {
			whereCondition = whereCondition + " AND block_id ='" + gHouse + "'";
		}
		if (!bookingIdForSplit.equalsIgnoreCase("undefined")) {
			whereCondition = whereCondition + " AND req_id='" + bookingIdForSplit + "'";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		/*
		List<RoomBookingPojo> roomBookingPojoList = jdbcTemplate.query(sql, (rs, i) -> {
			RoomBookingPojo rbp = new RoomBookingPojo();
			rbp.setBookingId(rs.getString("req_id"));
			String fromDt = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : null;
			String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : null;
			rbp.setFrmDt(fromDt);
			rbp.setToDt(toDt);
			rbp.setStatus(rs.getString("status"));
			return rbp;
		}, city, gHouse);*/
		System.out.println("whereCondition     "+whereCondition);
		sql = sql + whereCondition;
		List<RoomBookingPojo> roomBookingPojoList = new ArrayList<RoomBookingPojo>();
		roomBookingPojoList = jdbcTemplate.query(sql, new RowMapper<RoomBookingPojo>() {
			@Override
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				RoomBookingPojo rbp = new RoomBookingPojo();
				rbp.setBookingId(rs.getString("req_id"));
				String fromDt = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : null;
				rbp.setFrmDt(fromDt);
				rbp.setToDt(toDt);
				rbp.setStatus(rs.getString("status"));
				return rbp;
			}
		});
		System.out.println(sql+"       Y ");

		roomBookingPojoList.forEach(item -> {
			List<GuestDetailsPojo> guestDetailsList;
			try {
				guestDetailsList = getGuestDetails(item.getBookingId());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			item.setList(guestDetailsList);
		});
		return roomBookingPojoList.stream().filter(item -> item.getList().size() > 1).collect(Collectors.toList());
	}

	@Override
	public RoomBookingPojo getSplitBookingDetails(String bookingId, String guestList) {
		guestList = " AND guest_id IN (" + guestList + ")";
		String sql = approverQueries.getProperty("getBookingDtlsByIdSQL");
		String sql2 = adminQueries.getProperty("getSplitBookingGuestDetails") + guestList;
		String sql3 = adminQueries.getProperty("getDetailsOfPrimaryGuest");

		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");

		List<RoomBookingPojo> roomBookingPojoList = jdbcTemplate.query(sql, (rs, rownumber) -> {
			RoomBookingPojo res = new RoomBookingPojo();
			String fromDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
			String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
			String arrDt = rs.getDate("arrival_date") != null ? sdf.format(rs.getDate("arrival_date")) : null;
			String depDt = rs.getDate("depr_date") != null ? sdf.format(rs.getDate("depr_date")) : null;
			String arrTm = rs.getTime("arrival_time") != null ? stf.format(rs.getTime("arrival_time")) : null;
			String depTm = rs.getTime("depr_time") != null ? stf.format(rs.getTime("depr_time")) : null;
			res.setCityId(rs.getString("gh_id"));
			res.setCityName(rs.getString("gh_name"));
			res.setBlockId(rs.getString("block_id"));
			res.setBlockName(rs.getString("block_name"));
			res.setBookingId(rs.getString("req_id"));
			res.setRoomType(rs.getString("roomtype"));
			if ((rs.getString("nor_req") != null ? !rs.getString("nor_req").equals("0") : false)
					|| (rs.getString("nob_g_req") != null ? !rs.getString("nob_g_req").equals("0") : false)) {
				res.setBookingType("room");
			}
			if (rs.getString("nod_g_req") != null ? !rs.getString("nod_g_req").equals("0") : false) {
				res.setBookingType("dormitory");
			}
			res.setFrmDt(fromDt);
			res.setToDt(toDt);
			res.setNoOfRooms(rs.getString("nor_req"));
			res.setNoOfBeds(rs.getString("nob_g_req"));
			res.setNoOfBedsDormitory(rs.getString("nod_g_req"));
			res.setMob(rs.getString("mobile_no"));
			res.setEmailId(rs.getString("email_id"));
			res.setCatg(rs.getString("category_type"));
			res.setCatgName(rs.getString("category"));
			res.setPurp(rs.getString("purpose"));
			res.setPurpName(rs.getString("item_title"));
			res.setDesg(rs.getString("desg"));
			res.setDesgName(rs.getString("desg_category"));
			res.setOffAddr(rs.getString("address"));
			res.setaVchId(rs.getString("veh_id"));
			res.setTravelModeFr(rs.getString("mode_of_travel"));
			res.setFlNoFr(rs.getString("ftno"));
			res.setArrDt(arrDt);
			res.setArrTm(arrTm);
			res.setArrPla(rs.getString("arrival_place"));
			res.setdVchId(rs.getString("dv_id"));
			res.setTravelModeTo(rs.getString("d_mode_travel"));
			res.setFlNoTo(rs.getString("d_fno"));
			res.setDepDt(depDt);
			res.setDepTm(depTm);
			res.setDepPla(rs.getString("depr_place"));
			res.setStatus(rs.getString("status"));
			res.setUserId(rs.getString("userid"));
			if (rs.getString("status") != null) {
				if (rs.getString("status").equalsIgnoreCase("REQ")) {
					res.setStatusDesc("Not taken up for processing");
				}
				if (rs.getString("status").equalsIgnoreCase("CAN")) {
					res.setStatusDesc("Cancelled");
				}
				if (rs.getString("status").equalsIgnoreCase("MOD")) {
					res.setStatusDesc("Under processing");
				}
				if (rs.getString("status").equalsIgnoreCase("PRO")) {
					res.setStatusDesc("Approved");
				}
				if (rs.getString("status").equalsIgnoreCase("ALO")) {
					res.setStatusDesc("Alloted");
				}
				if (rs.getString("status").equalsIgnoreCase("CIN")) {
					res.setStatusDesc("Check In");
				}
				if (rs.getString("status").equalsIgnoreCase("COUT")) {
					res.setStatusDesc("Check Out");
				}
			}
			String modeOfReq = null;
			if (rs.getString("mode_of_req") != null) {
				modeOfReq = rs.getString("mode_of_req").equalsIgnoreCase("PUB") ? "Public" : null;
			}
			res.setModeOfReq(modeOfReq);
			res.setIsExtension(rs.getString("ext").equalsIgnoreCase("1"));
			res.setExtensionId(rs.getString("existing_req_id"));
			res.setIsArrivalConv(rs.getString("is_veh_req").equalsIgnoreCase("1"));
			res.setIsDepartueConv(rs.getString("is_veh_req1").equalsIgnoreCase("1"));
			res.setSuppDocName(rs.getString("file_path"));
			res.setCatgDocName(rs.getString("catg_file_path"));
			res.setDuringstay(rs.getString("during_stay_conveancy"));
			res.setCatgType(rs.getString("catgtype"));
			res.setNoOfDays(rs.getInt("no_of_days"));
			res.setNoOfAdults(rs.getInt("no_of_adults"));
			res.setNoOfChildren(rs.getInt("no_of_children"));
			res.setBookingFor(rs.getString("booking_for"));
			logger.info(res);
			return res;
		}, bookingId);

		List<GuestDetailsPojo> guestDetailsList = jdbcTemplate.query(sql2, (rs, i) -> {
			GuestDetailsPojo guestDetailsPojo = new GuestDetailsPojo();
			guestDetailsPojo.setGuestId(rs.getString("guest_id"));
			guestDetailsPojo.setGuestName(rs.getString("name"));
			guestDetailsPojo.setGuestAge(rs.getString("age"));
			guestDetailsPojo.setGuestGender(rs.getString("gender"));
			guestDetailsPojo.setGuestAddr(rs.getString("address"));
			guestDetailsPojo.setGuestDesignation(rs.getString("designation"));
			guestDetailsPojo.setGuestDepartment(rs.getString("department"));
			guestDetailsPojo.setGuestMobile(rs.getString("guest_mobile"));
			guestDetailsPojo.setGuestDocType(rs.getString("guest_doc_type"));
			guestDetailsPojo.setGuestDocPath(rs.getString("guest_doc_path"));
			return guestDetailsPojo;
		}, bookingId);

		RoomBookingPojo roomBookingPojo = roomBookingPojoList.get(0);
		if (roomBookingPojo.getBookingFor() != null && roomBookingPojo.getBookingFor().equalsIgnoreCase("others")) {
/*
			GuestDetailsPojo guestDetailsPojo = guestDetailsList.get(0);
			if (guestDetailsPojo != null) {
				roomBookingPojo.setgMobile(guestDetailsPojo.getGuestMobile());
				roomBookingPojo.setgDocType(guestDetailsPojo.getGuestDocType());
				roomBookingPojo.setgDocName(guestDetailsPojo.getGuestDocPath());
			}
*/
			GuestDetailsPojo gdp = jdbcTemplate.queryForObject(sql3, (rs, rn) -> {
				GuestDetailsPojo g = new GuestDetailsPojo();
				g.setGuestMobile(rs.getString("guest_mobile"));
				g.setGuestDocType(rs.getString("guest_doc_type"));
				g.setGuestDocPath(rs.getString("guest_doc_path"));
				return g;
			}, bookingId);
			if(gdp != null) {
				roomBookingPojo.setgMobile(gdp.getGuestMobile());
				roomBookingPojo.setgDocType(gdp.getGuestDocType());
				roomBookingPojo.setgDocName(gdp.getGuestDocPath());
			}
		}
		roomBookingPojo.setList(guestDetailsList);
		roomBookingPojo.setNoOfAdults(
				(int) guestDetailsList.stream().filter(item -> Integer.parseInt(item.getGuestAge()) > 11).count());
		roomBookingPojo.setNoOfChildren(
				(int) guestDetailsList.stream().filter(item -> Integer.parseInt(item.getGuestAge()) <= 11).count());
		return roomBookingPojo;
	}

	@Override
	public Pagination getModListForAllotment(Map<String, Object> params, HttpSession session) {
	    List<BookingResponseModel> data;
	    String orderByCondition = " ORDER BY gh.from_date DESC, gh.req_id";
	    String query = allotmentQueries.getProperty("getModListForAllotment");
	    String count = allotmentQueries.getProperty("getModListForAllotmentCount");
	    String whereCondition = " WHERE 1=1";
	    String cityId = (String) params.get("cId");
	    String ghId = (String) params.get("gId");
	    String bookingId = (String) params.get("bookingId");
	    String bookingDate = (String) params.get("bookingDate");
	    String category = (String) params.get("category");
	    String purpose = (String) params.get("purpose");
	    String mobNo = (String) params.get("mobNo");
	    String name = (String) params.get("name");
	    String roomType = (String) params.get("roomType");
	    String type = (String) params.get("type");
	    int pageNo = (int) params.get("pageNo");

	    // Building the where condition with named parameters
	    Map<String, Object> queryParams = new HashMap<>();

	    if (cityId != null && !cityId.isEmpty()) {
	        whereCondition += " AND gh.gh_id = :cityId";
	        queryParams.put("cityId", cityId);
	    } else {
	        List<String> cityIdList = Utils.getCityIdListFromSession(session);
	        whereCondition += " AND gh.gh_id IN (:cityIdList)";
	        queryParams.put("cityIdList", cityIdList);
	    }

	    if (ghId != null && !ghId.isEmpty()) {
	        whereCondition += " AND gh.block_id = :ghId";
	        queryParams.put("ghId", ghId);
	    }
	    if (bookingId != null && !bookingId.isEmpty()) {
	        whereCondition += " AND gh.req_id = :bookingId";
	        queryParams.put("bookingId", bookingId);
	    }
	    if (category != null && !category.isEmpty()) {
	        whereCondition += " AND gh.category_type = :category";
	        queryParams.put("category", category);
	    }
	    if (bookingDate != null && !bookingDate.isEmpty()) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date date = sdf.parse(bookingDate);
	            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	            bookingDate = sdf1.format(date);
	            whereCondition += " AND gh.req_date::date = CAST(:bookingDate AS date)";
	            queryParams.put("bookingDate", bookingDate);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    if (purpose != null && !purpose.isEmpty()) {
	        whereCondition += " AND gh.purpose = :purpose";
	        queryParams.put("purpose", purpose);
	    }
	    if (mobNo != null && !mobNo.isEmpty()) {
	        whereCondition += " AND gh.mobile_no = :mobNo";
	        queryParams.put("mobNo", mobNo);
	    }
	    if (name != null && !name.isEmpty()) {
	        whereCondition += " AND gs.name ILIKE :name";
	        queryParams.put("name", "%" + name + "%");
	    }
	    if (roomType != null && !roomType.isEmpty()) {
	        whereCondition += " AND gh.roomtype = :roomType";
	        queryParams.put("roomType", roomType);
	    }
	    if (type != null && !type.isEmpty()) {
	        if (type.equalsIgnoreCase("changeList")) {
	            whereCondition += " AND gh.status IN ('ALO') AND gh.from_date::date >= current_date::date";
	        } else if (type.equalsIgnoreCase("modifyList")) {
	            query = allotmentQueries.getProperty("getAllotmentLstForModify");
	            whereCondition += " AND gh.status IN ('PRO', 'PCIN', 'CIN', 'PEXT') AND current_date::date <= gh.to_date::date"
	                    + " AND (tr.mod_status IS NULL OR tr.mod_status = '')";
	        } else if (type.equalsIgnoreCase("modifyListMOD")) {
	            query = allotmentQueries.getProperty("getAllotmentLstForModifyMOD");
	            whereCondition += " AND gh.status IN ('PRO', 'PCIN', 'CIN', 'PEXT') AND current_date::date <= gh.to_date::date"
	                    + " AND (tr.mod_status = 'PMODF')";
	        } else {
	            whereCondition += " AND gh.status IN ('MOD', 'PALO') AND gh.from_date::date >= current_date::date";
	        }
	    }

	    query = query + whereCondition + orderByCondition;
	    if (type.equalsIgnoreCase("modifyList")) {
	        count += whereCondition.replace("AND (tr.mod_status IS NULL OR tr.mod_status = '')", "");
	    } else if (type.equalsIgnoreCase("modifyListMOD")) {
	        count += whereCondition.replace("AND (tr.mod_status = 'PMODF')", "");
	    } else {
	        count += whereCondition;
	    }

	    // Add pagination if necessary
	    if (pageNo != 0) {
	        int offset = (pageNo - 1) * Constants.LIMIT;
	        query = query + " LIMIT :limit OFFSET :offset";
	        queryParams.put("limit", Constants.LIMIT);
	        queryParams.put("offset", offset);
	    }

	    logger.info(query);
	    logger.info(count);
	    NamedParameterJdbcTemplate namedParameterJdbcTemplate =new NamedParameterJdbcTemplate(jdbcTemplate);
	    // Use NamedParameterJdbcTemplate for querying
	    data = namedParameterJdbcTemplate.query(query, queryParams, (rs, rownumber) -> {
	        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        BookingResponseModel bookingResponseModel = new BookingResponseModel();
	        String bookingDt = rs.getDate("req_date") != null ? sdf1.format(rs.getTimestamp("req_date")) : null;
	        String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
	        String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
	        if (type.equalsIgnoreCase("modifyListMOD")) {
	            String prevOrderDt = rs.getDate("prev_order_date") != null ? sdf.format(rs.getTimestamp("prev_order_date")) : null;
	            bookingResponseModel.setPrevOrderDt(prevOrderDt);
	        }
	        bookingResponseModel.setBookingId(rs.getString("req_id"));
	        bookingResponseModel.setName(rs.getString("name"));
	        bookingResponseModel.setDesignation(rs.getString("designation"));
	        bookingResponseModel.setDepartment(rs.getString("department"));
	        bookingResponseModel.setBookingDt(bookingDt);
	        bookingResponseModel.setFromDate(frDt);
	        bookingResponseModel.setToDate(toDt);

	        bookingResponseModel.setPurpose(rs.getString("item_title"));
	        String modRoomType = "";
	        String rType = rs.getString("type");
	        if (rType.equals("R")) {
	            modRoomType = "Room";
	        }
	        if (rType.equals("BG")) {
	            modRoomType = "Bed";
	        }
	        bookingResponseModel.setType(modRoomType);
	        bookingResponseModel.setNoOfDays(rs.getInt("no_of_days"));
	        bookingResponseModel.setCityId(rs.getString("gh_id"));
	        bookingResponseModel.setGhId(rs.getString("block_id"));
	        bookingResponseModel.setModId(rs.getString("mod_id"));
	        bookingResponseModel.setMrrId(rs.getString("mrr_id"));
	        bookingResponseModel.setNoOfAdults(rs.getString("no_of_adult"));
	        String category_Desc = "";
	        String categoryType = rs.getString("category_type");
	        if (categoryType.equals("C1")) {
	            category_Desc = "Category 1";
	        }
	        if (categoryType.equals("C2")) {
	            category_Desc = "Category 2";
	        }
	        if (categoryType.equals("C3")) {
	            category_Desc = "Category 3";
	        }
	        if (categoryType.equals("C4")) {
	            category_Desc = "Category 4";
	        }
	        bookingResponseModel.setCategory(category_Desc);
	        if (type.equalsIgnoreCase("modifyList") || type.equalsIgnoreCase("modifyListMOD")) {
	            bookingResponseModel.setNoRooms(rs.getString("row_count"));
	        } else {
	            Integer noOfRooms = rs.getString("nor_req").equals("") ? 0 : rs.getInt("nor_req");
	            Integer noOfBeds = rs.getString("nod_g_req").equals("") ? 0 : rs.getInt("nod_g_req");
	            bookingResponseModel.setNoRooms(String.valueOf(noOfRooms + noOfBeds));
	        }
	        String status = rs.getString("status");
	        if (status != null) {
	            if (status.equals("MOD")) {
	                bookingResponseModel.setStatusDesc("NOT ALLOTTED");
	            } else if (status.equals("CIN") || status.equals("PCIN") || status.equals("PEXT")) {
	                bookingResponseModel.setStatusDesc("CHECKED IN");
	            } else if (status.equals("PRO")) {
	                bookingResponseModel.setStatusDesc("APPROVED");
	            } else {
	                bookingResponseModel.setStatusDesc("ALLOTTED");
	            }
	        } else {
	            status = null;
	        }
	        bookingResponseModel.setStatus(status);
	        if (type.equalsIgnoreCase("modifyListMOD")) {
	            bookingResponseModel.setRemarks(rs.getString("subject"));
	            bookingResponseModel.setRowNo(rs.getString("prev_order_no"));
	        }
	        return bookingResponseModel;
	    });

	    int totalElements = namedParameterJdbcTemplate.queryForObject(count, queryParams, Integer.class);

	    Pagination pagination = new Pagination();

	    pagination.setData(data);
	    pagination.setTotalPages(totalElements);
	    return pagination;
	}


	@Override
	public RoomsModel getTotalRooms(String bookingId, String cityId, String ghId) {
		String query = allotmentQueries.getProperty("getTotalNoOfRooms");
		try {
			RoomsModel roomsModel = jdbcTemplate.queryForObject(query, new Object[] { ghId, cityId, bookingId },
					(rs, rownumber) -> {
						RoomsModel roomsModel1 = new RoomsModel();
						roomsModel1.setCityId(rs.getString("gh_id"));
						roomsModel1.setGhId(rs.getString("block_id"));
						roomsModel1.setTotalRooms(rs.getString("nu_of_rooms"));
						roomsModel1.setFromDate(rs.getDate("from_date").toLocalDate());
						roomsModel1.setToDate(rs.getDate("to_date").toLocalDate());
						return roomsModel1;
					});

			List<LocalDate> dates = Utils.getDatesBetween(roomsModel.getFromDate(), roomsModel.getToDate());

			List<Map<String, Object>> roomNos = jdbcTemplate.queryForList(allotmentQueries.getProperty("getAllRoomsWithBedCount"), ghId, cityId);

			roomsModel.setRoomList(roomNos);

			ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			List<DateRoomModel> dateRoomModels = new ArrayList<>();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			List<Future<DateRoomModel>> futures = new ArrayList<>();
            for (LocalDate date : dates) {
				Callable<DateRoomModel> dateRoomFetcher = new DateRoomFetcher(date, ghId, cityId, roomNos, jdbcTemplate, allotmentQueries);
				Future<DateRoomModel> future = executor.submit(dateRoomFetcher);
				futures.add(future);
			}
			for (Future<DateRoomModel> future : futures) {
				try {
					DateRoomModel drm = future.get();
					dateRoomModels.add(drm);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
			roomsModel.setDrmList(dateRoomModels);
			executor.shutdown();
			try {
				executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return roomsModel;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public RoomsModel getTotalBeds(String bookingId, String cityId, String ghId) {
		String query = allotmentQueries.getProperty("getTotalNoOfRooms");
		try {
			RoomsModel roomsModel = jdbcTemplate.queryForObject(query, new Object[] { ghId, cityId, bookingId },
					(rs, rownumber) -> {
						RoomsModel roomsModel1 = new RoomsModel();
						roomsModel1.setCityId(rs.getString("gh_id"));
						roomsModel1.setGhId(rs.getString("block_id"));
						roomsModel1.setTotalRooms(rs.getString("nu_of_rooms"));
						roomsModel1.setFromDate(rs.getDate("from_date").toLocalDate());
						roomsModel1.setToDate(rs.getDate("to_date").toLocalDate());
						return roomsModel1;
					});

			roomsModel.setRoomNos(jdbcTemplate.queryForList(allotmentQueries.getProperty("getAllRoomNos"),
					new Object[] { ghId, cityId, "D" }, String.class));
			System.out.println(roomsModel.toString());
			List<LocalDate> dates = Utils.getDatesBetween(roomsModel.getFromDate(), roomsModel.getToDate());
			
			List<DateRoomModel> dateRoomModels = new ArrayList<>();
			for (int i = 0; i < dates.size(); i++) {
				DateRoomModel dateRoomModel = new DateRoomModel();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				dateRoomModel.setDateStr(dates.get(i).format(formatter) + " 09:00" + " - "
						+ dates.get(i).plusDays(1).format(formatter) + " 08:00");
				dateRoomModel.setRoomDetails(jdbcTemplate.query(allotmentQueries.getProperty("getBedAllotmentDetails"),
						new Object[] { dates.get(i).toString(), ghId, cityId, ghId, cityId }, (rs, rownumber) -> {
							SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
							String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date"))
									: null;
							String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date"))
									: null;
							RoomDetails roomDetails = new RoomDetails();
							roomDetails.setAllotId(rs.getString("allot_id"));
							roomDetails.setMrrId(rs.getString("mrr_id"));
							roomDetails.setRoomNo(rs.getString("room_no"));
							roomDetails.setBedNo(rs.getString("bed_no"));
							roomDetails.setGuestName(rs.getString("name"));
							roomDetails.setFromDate(frDt);
							roomDetails.setToDate(toDt);
							roomDetails.setRoomRent(rs.getString("room_rent"));
							String status = rs.getString("status");
							if (null == status)
								roomDetails.setOccupied(false);
							else
								roomDetails.setOccupied(true);
							return roomDetails;
						}));
				dateRoomModels.add(dateRoomModel);
			}
			roomsModel.setDrmList(dateRoomModels);
			return roomsModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean allotRooms(AllotSubmitModel allotSubmitModel, String userId) {
		String query = allotmentQueries.getProperty("allotRooms");
		String query2 = allotmentQueries.getProperty("getAllotmentDetails");
		String query3 = allotmentQueries.getProperty("updateModReqRoomStatus");
		String query4 = allotmentQueries.getProperty("getModListForAllotment");
		String query5 = allotmentQueries.getProperty("updateGADReqStatus");
		String sql = adminQueries.getProperty("canModRequestForModTbl");

		try {
			BookingResponseModel bookingResponseModel = jdbcTemplate.queryForObject(query2, new Object[] {
					allotSubmitModel.getBookingId(), allotSubmitModel.getModId(), allotSubmitModel.getMrrId() },
					(rs, rownumber) -> {
						BookingResponseModel bookingResponseModel1 = new BookingResponseModel();
						bookingResponseModel1.setBookingId(rs.getString("req_id"));
						bookingResponseModel1.setCityId(rs.getString("gh_id"));
						bookingResponseModel1.setGhId(rs.getString("block_id"));
						bookingResponseModel1.setType(rs.getString("type"));
						return bookingResponseModel1;
					});

			allotSubmitModel.getRoomDetails().forEach(roomDetails -> {
				Integer id = null;
				try {
					id = get_seqID("allot_id");
					update_seqID("allot_id", id);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				String[] fromDateArr = roomDetails.getFromDate().split(" - ");

				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

				try {
					String type = roomDetails.getBedNo() == null ? "R" : "BG";
					jdbcTemplate.update(query, id, allotSubmitModel.getBookingId(),
                            sdf.parse(fromDateArr[0]), sdf.parse(fromDateArr[1]), type, "ALO",
                            bookingResponseModel.getCityId(), bookingResponseModel.getGhId(), roomDetails.getRoomNo(),
                            roomDetails.getBedNo(), roomDetails.getRoomRent(), allotSubmitModel.getMrrId());
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			});

			jdbcTemplate.update(sql, "ALO", allotSubmitModel.getBookingId());

			jdbcTemplate.update(query3, "ALO", allotSubmitModel.getBookingId(),
                    allotSubmitModel.getModId(), allotSubmitModel.getMrrId());

			boolean pendingAllotment = false;

			query4 += " WHERE gh.req_id = ? AND mr.mod_id = ? AND mrr.status = 'MOD'";

			List<BookingResponseModel> bookingResponseModels = jdbcTemplate.query(query4,
					new Object[] { allotSubmitModel.getBookingId(), allotSubmitModel.getModId() }, (rs, rownumber) -> {
						BookingResponseModel bookingResponseModel1 = new BookingResponseModel();
						bookingResponseModel1.setBookingId(rs.getString("req_id"));
						bookingResponseModel1.setCityId(rs.getString("gh_id"));
						bookingResponseModel1.setGhId(rs.getString("block_id"));
						bookingResponseModel1.setType(rs.getString("type"));
						bookingResponseModel1.setMrrId(rs.getString("mrr_id"));
						bookingResponseModel1.setModId(rs.getString("mod_id"));
						bookingResponseModel1.setStatus(rs.getString("status"));
						return bookingResponseModel1;
					});

			if (bookingResponseModels.size() > 0)
				pendingAllotment = true;

			if (pendingAllotment) {
				jdbcTemplate.update(query5, "PALO", userId, allotSubmitModel.getBookingId());
			} else {
				jdbcTemplate.update(query5, "ALO", userId, allotSubmitModel.getBookingId());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAllottedRooms(AllotSubmitModel allotSubmitModel, String userId) {
		String query = allotmentQueries.getProperty("updateAllottedRooms");
		try {
			allotSubmitModel.getRoomDetails().forEach(roomDetails -> {
				String type = roomDetails.getBedNo() == null ? "R" : "BG";
				jdbcTemplate.update(query, roomDetails.getRoomNo(), roomDetails.getBedNo(),
                        roomDetails.getRoomRent(), type, roomDetails.getAllotId());
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modifyAllottedRooms(AllotSubmitModel allotSubmitModel, String userId) {
		String sql = allotmentQueries.getProperty("getAllotmentByAllotId");
		String sql2 = allotmentQueries.getProperty("updateAllotmentForModify");
		String sql3 = allotmentQueries.getProperty("insertSmsAllotForModify");
		String sql4 = allotmentQueries.getProperty("countForModifyTrSms");
		String sql5 = allotmentQueries.getProperty("updateTrSmsForModify");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		AtomicReference<RoomDetails> rdl = new AtomicReference<RoomDetails>();
		AtomicReference<String> reqId=new AtomicReference<String>("");
		allotSubmitModel.getRoomDetails().forEach(item -> {
			try {
				RoomDetails roomDetails = jdbcTemplate.queryForObject(sql, new Object[] { item.getAllotId() },
						(rs, rownumber) -> {
							RoomDetails rd = new RoomDetails();
							rd.setRoomNo(rs.getString("room_no"));
							rd.setBedNo(rs.getString("bed_no"));
							rd.setRoomRent(rs.getString("room_rent"));
							rd.setBookingId(rs.getString("req_id"));
							rd.setBlockId(rs.getString("block_id"));
							rd.setCityId(rs.getString("gh_id"));
							return rd;
						});
				reqId.set(roomDetails.getBookingId());
				String type = item.getBedNo() == null ? "R" : "BG";
				jdbcTemplate.update(sql2, item.getRoomNo(), roomDetails.getRoomNo(),
						item.getBedNo(), roomDetails.getBedNo(), item.getRoomRent(), roomDetails.getRoomRent(),
						"PMODF", type, item.getAllotId());
				rdl.set(roomDetails);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error in modifyAllottedRooms");
			}
		});

		try {
			Date ModifyDate=null;
			try {
				ModifyDate=sdf.parse(allotSubmitModel.getPrevOrderDt()); 
			}catch(Exception e) {
				e.printStackTrace();
			}
			RoomDetails roomDetails = rdl.get();
			Integer count = jdbcTemplate.queryForObject(sql4, new Object[] { roomDetails.getBookingId() },
								Integer.class);
			if (count > 0) {
				jdbcTemplate.update(sql5, allotSubmitModel.getPrevOrderNo(), allotSubmitModel.getReason(),ModifyDate,roomDetails.getBookingId());
			} else {
				Integer id = get_seqID("a_sms_id");
				update_seqID("a_sms_id", id);

				jdbcTemplate.update(sql3, id, roomDetails.getBookingId(), allotSubmitModel.getReason(),
						"MODF", roomDetails.getCityId(), allotSubmitModel.getPrevOrderNo(),ModifyDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error in modifyAllottedRooms");
		}
		
		return true;
	}

	@Override
	public boolean modModifyRoom(AllotSubmitModel allotSubmitModel, String userId) {
		String sql = allotmentQueries.getProperty("updateAllotmentForModifyMOD");
		String sql1 = allotmentQueries.getProperty("updateTrSmsForModify");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(allotSubmitModel.getPrevOrderDt());
		allotSubmitModel.getRoomDetails().forEach(item -> {
			try {
				Date ModifyDate=null;
				
					ModifyDate=sdf.parse(allotSubmitModel.getPrevOrderDt()); 
				System.out.println(ModifyDate);
				String type = item.getBedNo() == null ? "R" : "BG";
				jdbcTemplate.update(sql, item.getRoomNo(), item.getBedNo(), item.getRoomRent(),
						"MMODF", type, item.getAllotId());
				jdbcTemplate.update(sql1, allotSubmitModel.getPrevOrderNo(), allotSubmitModel.getReason(),ModifyDate,allotSubmitModel.getBookingId());
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Error in modifyAllottedRooms");
			}
		});
		
		return true;
	}

	@Override
	public boolean cancelAllotment(String bookingId, String modId, String mrrId, String userId) {
		String sql = allotmentQueries.getProperty("updateAllotmentStatus");
		String sql1 = allotmentQueries.getProperty("updateModReqRoomStatus");
		String sql2 = allotmentQueries.getProperty("updateGADReqStatus");
		String sql3 = allotmentQueries.getProperty("getModListForAllotment");
		String sql4 = allotmentQueries.getProperty("updateModReqStatus");
		jdbcTemplate.update(sql, new Object[] { "CAN", bookingId, mrrId });
		jdbcTemplate.update(sql1, new Object[] { "MOD", bookingId, modId, mrrId });
		jdbcTemplate.update(sql4, new Object[] { "MOD", bookingId, modId });
		boolean pendingAllotment = false;

		sql3 += " WHERE gh.req_id = ? AND mr.mod_id = ? AND mrr.status = 'ALO'";

		List<BookingResponseModel> bookingResponseModels = jdbcTemplate.query(sql3, new Object[] { bookingId, modId },
				(rs, rownumber) -> {
					BookingResponseModel bookingResponseModel1 = new BookingResponseModel();
					bookingResponseModel1.setBookingId(rs.getString("req_id"));
					bookingResponseModel1.setCityId(rs.getString("gh_id"));
					bookingResponseModel1.setGhId(rs.getString("block_id"));
					bookingResponseModel1.setType(rs.getString("type"));
					bookingResponseModel1.setMrrId(rs.getString("mrr_id"));
					bookingResponseModel1.setModId(rs.getString("mod_id"));
					bookingResponseModel1.setStatus(rs.getString("status"));
					return bookingResponseModel1;
				});

		if (bookingResponseModels.size() > 0)
			pendingAllotment = true;

		if (pendingAllotment) {
			jdbcTemplate.update(sql2, new Object[] { "PALO", userId, bookingId });
		} else {
			jdbcTemplate.update(sql2, new Object[] { "MOD", userId, bookingId });
		}
		return true;
	}

	@Override
	public boolean cancelReservationForAllot(String reqId) {
		String sql = adminQueries.getProperty("updatecanstatusinGadRequest");
		String sql1 = adminQueries.getProperty("canReqForAllotTbl");
		String canQuery = adminQueries.getProperty("updateCanStatusTrAllotment");

		jdbcTemplate.update(canQuery, "FCAN", reqId, "PRO");
		jdbcTemplate.update(sql, "ACAN", reqId);
		jdbcTemplate.update(sql1, "CAN", reqId);
		return true;
	}

	@Override
	public List<BookingResponseModel> getAllModifiedDataForApprove(Map<String, Object> params) {
	    String query = approverQueries.getProperty("getAllModifiedDataForApprove");
	    StringBuilder whereCondition = new StringBuilder();

	    String cityId = (String) params.get("cId");
	    String ghId = (String) params.get("gId");

	    List<Object> queryParams = new ArrayList<>();

	    if (cityId != null && !cityId.isEmpty()) {
	        whereCondition.append(" AND g.gh_id = ?");
	        queryParams.add(cityId);
	    }
	    if (ghId != null && !ghId.isEmpty()) {
	        whereCondition.append(" AND g.block_id = ?");
	        queryParams.add(ghId);
	    }

	    query += whereCondition.toString();
	    logger.info(query);

	    try {
	        List<BookingResponseModel> data = jdbcTemplate.query(query, queryParams.toArray(), new RowMapper<BookingResponseModel>() {
	            @Override
	            public BookingResponseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	                Date bookDate = rs.getDate("req_date") != null ? rs.getTimestamp("req_date") : null;
	                Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
	                Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;

	                BookingResponseModel bookingResponseModel = new BookingResponseModel();

	                String category_Desc = "";
	                String categoryType = rs.getString("category_type");
	                switch (categoryType) {
	                    case "C1":
	                        category_Desc = "Category 1";
	                        break;
	                    case "C2":
	                        category_Desc = "Category 2";
	                        break;
	                    case "C3":
	                        category_Desc = "Category 3";
	                        break;
	                    case "C4":
	                        category_Desc = "Category 4";
	                        break;
	                }

	                bookingResponseModel.setCategory(category_Desc);
	                bookingResponseModel.setBookingId(rs.getString("req_id"));
	                bookingResponseModel.setFromDate(sdf1.format(fromDate));
	                bookingResponseModel.setToDate(sdf1.format(toDate));
	                bookingResponseModel.setBookingDt(sdf1.format(bookDate));
	                bookingResponseModel.setName(rs.getString("name"));
	                bookingResponseModel.setMobile(rs.getString("mobile_no"));
	                bookingResponseModel.setStatus(rs.getString("status"));

	                return bookingResponseModel;
	            }
	        });

	        return data;
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error occurred while fetching modified data for approval");
	    }
	}


	@Override
	public List<RoomDetails> getAllModifyDetailsByProcessId(String processId, String type) {
		String status = "";
		//String groupBy = " GROUP BY a.req_id,a.process_id,b.name,c.from_date,c.to_date,a.status";
		List<RoomDetails> data;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		String query = approverQueries.getProperty("getAllModifyDetailsByProcessId");
		if (type.equalsIgnoreCase("DRAFT")) {
			status = " AND a.status = 'TCNF'";
		} else {
			status = " AND a.status = 'CNF'";
		}
		query = query + status;
		logger.info(query);
		try {
			data = jdbcTemplate.query(query, (rs, rownumber) -> {
				RoomDetails pm = new RoomDetails();
				pm.setProcessId(rs.getString("process_id"));
				pm.setBookingId(rs.getString("req_id"));
				
				String name=rs.getString("name");
				Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
				Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
				Date prevDate = rs.getDate("prev_order_date") != null ? rs.getTimestamp("prev_order_date") : null;
				pm.setFromDate(sdf.format(fromDate));
				pm.setToDate(sdf.format(toDate));
				pm.setPrevOrderDate(sdf1.format(prevDate));
				String status_desc = "";
				if (rs.getString("status").equals("REQ")) {
					status_desc = " Not taken up for processing";
				}
				if (rs.getString("status").equals("CAN")) {
					status_desc = "Cancelled";
				}
				if (rs.getString("status").equalsIgnoreCase("MOD")) {
					status_desc = "Under processing";
				}
				if (rs.getString("status").equalsIgnoreCase("PRO")) {
					status_desc = "Approved";
				}
				if (rs.getString("status").equalsIgnoreCase("ALO")) {
					status_desc = "Allotted";
				}
				if (rs.getString("status").equals("CIN")) {
					status_desc = "Check In";
				}
				if (rs.getString("status").equals("COUT")) {
					status_desc = "Check Out";
				}
				if (rs.getString("status").equals("TCNF")) {
					status_desc = "Temporary Confirm";
				}
				if (rs.getString("status").equals("CNF")) {
					status_desc = "Confirm";
				}
				pm.setStatus(status_desc);
				pm.setRoomNo(rs.getString("room_no"));
				pm.setRoomRent(rs.getString("room_rent"));
				pm.setModifiedRoomNo(rs.getString("prev_room_no"));
				pm.setModifiedRoomRent(rs.getString("prev_room_rent"));
				pm.setReason(rs.getString("subject"));
				pm.setPrevOrderNo(rs.getString("prev_order_no"));
				String addrss=rs.getString("address");
				if (addrss != null && !addrss.isEmpty()) {
					name += "," + addrss;
				}
				pm.setName(name);
				String FormDate = sdf.format(fromDate);
				String ToDate = sdf.format(toDate);
				String periodOfStay = FormDate + " to " + ToDate;
				System.out.println("formdate and todate" + periodOfStay);
				pm.setPeriodOfStay(periodOfStay);
				return pm;
			}, processId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("All modify list by process Id=============== " + query);
		return data;
	}

	@Override
	public List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session) {List<BookingResponseModel> data;
	String orderByCondition = " ORDER BY tc.lastmodified DESC";
	String query = adminQueries.getProperty("getAllBillingList");
	String pcanFromDt = adminQueries.getProperty("getPcanFromDt");
	StringBuilder whereCondition = new StringBuilder();
	List<Object> paramsList = new ArrayList<>();

	String cityId = (String) params.get("cId");
	String ghId = (String) params.get("gId");
	String bookingId = (String) params.get("bookingId");
	String fromDate = (String) params.get("fromDate");
	String purpose = (String) params.get("purpose");
	String mobNo = (String) params.get("mobNo");
	String name = (String) params.get("name");
	String category = (String) params.get("category");

	if (cityId != null && !cityId.isEmpty()) {
	    whereCondition.append(" AND gr.gh_id = ?");
	    paramsList.add(cityId);
	} else {
	    ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
	    whereCondition.append(" AND gr.gh_id IN (");
	    whereCondition.append(String.join(", ", cityIdList.stream().map(id -> "?").toArray(String[]::new)));
	    whereCondition.append(")");
	    paramsList.addAll(cityIdList);
	}
	if (ghId != null && !ghId.isEmpty()) {
	    whereCondition.append(" AND gr.block_id = ?");
	    paramsList.add(ghId);
	}
	if (bookingId != null && !bookingId.isEmpty()) {
	    whereCondition.append(" AND gr.req_id = ?");
	    paramsList.add(bookingId);
	}
	if (category != null && !category.isEmpty()) {
	    whereCondition.append(" AND gr.category_type = ?");
	    paramsList.add(category);
	}
	if (fromDate != null && !fromDate.isEmpty()) {
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        Date date = sdf.parse(fromDate);
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	        fromDate = sdf1.format(date);
	        whereCondition.append(" AND gr.from_date::date = ?::date");
	        paramsList.add(fromDate);
	    } catch (ParseException e) {
	        throw new RuntimeException("Invalid date format", e);
	    }
	}
	if (purpose != null && !purpose.isEmpty()) {
	    whereCondition.append(" AND gr.purpose = ?");
	    paramsList.add(purpose);
	}
	if (mobNo != null && !mobNo.isEmpty()) {
	    whereCondition.append(" AND gr.mobile_no = ?");
	    paramsList.add(mobNo);
	}
	if (name != null && !name.isEmpty()) {
	    whereCondition.append(" AND gs.name ILIKE ?");
	    paramsList.add("%" + name + "%");
	}

	query += whereCondition.toString() + orderByCondition;
	logger.info(query);

	data = jdbcTemplate.query(query, paramsList.toArray(), (rs, i) -> {
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
	    String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
	    BookingResponseModel brm = new BookingResponseModel();
	    brm.setBookingId(rs.getString("req_id"));
	    brm.setName(rs.getString("name"));
	    brm.setFromDate(frDt);
	    brm.setToDate(toDt);
	    if ("PCAN".equals(rs.getString("can_status"))) {
	        try {
	            Date toDtBill = jdbcTemplate.queryForObject(pcanFromDt, new Object[]{brm.getBookingId(), "CAN"}, Date.class);
	            String toDateFormatted = toDtBill != null ? sdf1.format(toDtBill) : "";
	            brm.setToDate(toDateFormatted);
	        } catch (Exception e) {
	            logger.error("Error fetching PCAN data", e);
	        }
	    }
	    brm.setNoOfAdults(rs.getString("no_of_adults"));
	    brm.setNoOfChildren(rs.getString("no_of_children"));
	    brm.setAdvanceAmount(rs.getString("adv_amt"));
	    brm.setTotalAmt(rs.getString("total"));
	    brm.setCategory(rs.getString("desg_category"));

	    String categoryDesc;
	    switch (rs.getString("category_type")) {
	        case "C1":
	            categoryDesc = "Category 1";
	            break;
	        case "C2":
	            categoryDesc = "Category 2";
	            break;
	        case "C3":
	            categoryDesc = "Category 3";
	            break;
	        case "C4":
	            categoryDesc = "Category 4";
	            break;
	        default:
	            categoryDesc = "Unknown Category";
	    }
	    brm.setCatgId(categoryDesc);
	    brm.setPurpose(rs.getString("item_title"));
	    return brm;
	});

	System.out.println("Add bill details " + query);
	System.out.println("Data value " + data);
	return data;
}

	@Override
	public List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session) {List<BookingResponseModel> data;
	String orderByCondition = " ORDER BY bm.date DESC";
	String query = adminQueries.getProperty("getShowBillList");
	StringBuilder whereCondition = new StringBuilder();
	List<Object> paramsList = new ArrayList<>();

	String cityId = (String) params.get("cId");
	String ghId = (String) params.get("gId");
	String bookingId = (String) params.get("bookingId");
	String fromDate = (String) params.get("fromDate");
	String billdate = (String) params.get("billDate");
	String purpose = (String) params.get("purpose");
	String mobNo = (String) params.get("mobNo");
	String name = (String) params.get("name");
	String category = (String) params.get("category");

	if (cityId != null && !cityId.isEmpty()) {
	    whereCondition.append(" AND gr.gh_id = ?");
	    paramsList.add(cityId);
	} else {
	    ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
	    if (!cityIdList.isEmpty()) {
	        whereCondition.append(" AND gr.gh_id IN (");
	        whereCondition.append(String.join(",", Collections.nCopies(cityIdList.size(), "?")));
	        whereCondition.append(")");
	        paramsList.addAll(cityIdList);
	    }
	}

	if (ghId != null && !ghId.isEmpty()) {
	    whereCondition.append(" AND gr.block_id = ?");
	    paramsList.add(ghId);
	}

	if (bookingId != null && !bookingId.isEmpty()) {
	    whereCondition.append(" AND gr.req_id = ?");
	    paramsList.add(bookingId);
	}

	if (fromDate != null && !fromDate.isEmpty()) {
	    Date date;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    try {
	        date = sdf.parse(fromDate);
	    } catch (ParseException e) {
	        throw new RuntimeException(e);
	    }
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	    fromDate = sdf1.format(date);
	    whereCondition.append(" AND gr.from_date::date = ?::date");
	    paramsList.add(fromDate);
	}

	if (billdate != null && !billdate.isEmpty()) {
	    Date date;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    try {
	        date = sdf.parse(billdate);
	    } catch (ParseException e) {
	        throw new RuntimeException(e);
	    }
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	    billdate = sdf1.format(date);
	    whereCondition.append(" AND bm.date::date = ?::date");
	    paramsList.add(billdate);
	}

	if (category != null && !category.isEmpty()) {
	    whereCondition.append(" AND gr.category_type = ?");
	    paramsList.add(category);
	}

	if (purpose != null && !purpose.isEmpty()) {
	    whereCondition.append(" AND gr.purpose = ?");
	    paramsList.add(purpose);
	}

	if (mobNo != null && !mobNo.isEmpty()) {
	    whereCondition.append(" AND gr.mobile_no = ?");
	    paramsList.add(mobNo);
	}

	if (name != null && !name.isEmpty()) {
	    whereCondition.append(" AND gs.name ILIKE ?");
	    paramsList.add("%" + name + "%");
	}

	query = query + whereCondition.toString() + orderByCondition;
	logger.info(query);
	data = jdbcTemplate.query(query, paramsList.toArray(), (rs, i) -> {
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
	    String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
	    String billDate = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
	    BookingResponseModel brm = new BookingResponseModel();
	    brm.setBookingId(rs.getString("req_id"));
	    brm.setName(rs.getString("name"));
	    brm.setFromDate(frDt);
	    brm.setToDate(toDt);
	    brm.setNoOfAdults(rs.getString("no_of_adults"));
	    brm.setNoOfChildren(rs.getString("no_of_children"));
	    brm.setAdvanceAmount(rs.getString("advance"));
	    brm.setTotalAmt(rs.getString("total"));
	    brm.setBillingDt(billDate);
	    brm.setName(rs.getString("name"));
	    brm.setCategory(rs.getString("desg_category"));
	    String categoryDesc = "";
	    String categoryType = rs.getString("category_type");
	    switch (categoryType) {
	        case "C1":
	            categoryDesc = "Category 1";
	            break;
	        case "C2":
	            categoryDesc = "Category 2";
	            break;
	        case "C3":
	            categoryDesc = "Category 3";
	            break;
	        case "C4":
	            categoryDesc = "Category 4";
	            break;
	    }
	    brm.setCatgId(categoryDesc);
	    brm.setPurpose(rs.getString("item_title"));
	    brm.setBillId(rs.getString("bill_id"));
	    return brm;
	});

	System.out.println("showbill details  " + query);
	return data;
}

	@Override
	public boolean partialCancelById(String bookingId, String type) {
		String sql = approverQueries.getProperty("updatePartialCancelRequestByBookingId");
		jdbcTemplate.update(sql, type, bookingId);
		return true;
	}

	@Override
	@Transactional
	public boolean partialCancelAllot(RoomDetails roomDetails, String userId) {
		String sql = adminQueries.getProperty("updateCanStatusTrAllotment");
		String sql1 = approverQueries.getProperty("updateAllotmentStatusByAllotIds");
		String sql2 = approverQueries.getProperty("updateTrCheckinAllotStatusByAllotIds");

		String[] allotIds = roomDetails.getAllotId().split(",");
		String allotIdString = String.join("', '", allotIds);
		String WHERE_CONDITION = " WHERE allot_id in ('" + allotIdString + "')";
		sql1 += WHERE_CONDITION;
		sql2 += WHERE_CONDITION;

		logger.info(allotIdString);
		try {
			jdbcTemplate.update(sql, "PCAN", roomDetails.getBookingId(), "CIN");
			jdbcTemplate.update(sql, "PCAN", roomDetails.getBookingId(), "PRO");
			jdbcTemplate.update(sql1, "CAN");
			partialCancelById(roomDetails.getBookingId(), "ALO");
			jdbcTemplate.update(sql2, "CAN");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error in partialCancelAllot");
		}
		return true;
	}

	@Override
	public boolean foodItemExists(FoodItem foodItem, String type) {
		String sql = adminQueries.getProperty("foodItemExists");
		String sql1 = adminQueries.getProperty("foodItemExistsForUpdate");
		int count = 0;
		if (type.equals("ADD")) {
			count = jdbcTemplate.queryForObject(sql, Integer.class, foodItem.getItemTitle());
		} else {
			count = jdbcTemplate.queryForObject(sql1, Integer.class, foodItem.getItemTitle(), foodItem.getItemId());
		}
		return count > 0;
	}

	@Override
	public boolean addFoodItem(FoodItem foodItem) {
		String sql = adminQueries.getProperty("addFoodItem");
		try {
			Integer id = get_seqID("food_id");
			update_seqID("food_id", id);
			jdbcTemplate.update(sql, id, foodItem.getItemTitle(), foodItem.getUnitPrice());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<FoodItem> getAllFoodItems() {
		String sql = adminQueries.getProperty("getAllFoodItems");
		return jdbcTemplate.query(sql, (rs, i) -> {
			FoodItem foodItem = new FoodItem();
			foodItem.setItemId(rs.getString("item_id"));
			foodItem.setItemTitle(rs.getString("item_title"));
			foodItem.setUnitPrice(rs.getString("unitprice"));
			return foodItem;
		});
	}

	@Override
	public FoodItem getFoodItem(String itemId) {
		String sql = adminQueries.getProperty("getFoodItemById");
		try {
			return jdbcTemplate.queryForObject(sql, (rs, i) -> {
				FoodItem foodItem = new FoodItem();
				foodItem.setItemId(rs.getString("item_id"));
				foodItem.setItemTitle(rs.getString("item_title"));
				foodItem.setUnitPrice(rs.getString("unitprice"));
				return foodItem;
			}, itemId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateFoodItem(FoodItem foodItem) {
		String sql = adminQueries.getProperty("updateFoodItem");
		try {
			jdbcTemplate.update(sql, foodItem.getItemTitle(), foodItem.getUnitPrice(), foodItem.getItemId());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteFoodItem(String itemId) {
		String sql = adminQueries.getProperty("deleteFoodItem");
		try {
			jdbcTemplate.update(sql, itemId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public BillRespModel getBillDetailsByBookingId(String bookingId) {
		logger.info("inside getBillDetailsByBookingId");
		String guestQuery = adminQueries.getProperty("getGuestDetailsForBill");
		String rentQuery = adminQueries.getProperty("getRoomRentForBill");
		String foodChargeQuery = adminQueries.getProperty("getFoodChargesForBill");
		String extensionExists = adminQueries.getProperty("extensionExists");
		double roomRent = 0.0;
		String prevBookingId = bookingId;
		BillRespModel billRespModel = new BillRespModel();
		double nocCharge = 0.0;
		String nocDate = null;
		List<BookingResponseModel> booking = jdbcTemplate.query(guestQuery, (rs, rowNum) -> {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
			String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
			String arrivalDate = rs.getDate("checkin_date") != null
					? sdf1.format(rs.getTimestamp("checkin_date"))
					: null;
			String departureDate = rs.getDate("checkout_date") != null
					? sdf1.format(rs.getTimestamp("checkout_date"))
					: null;
			String name = rs.getString("name");
			String department = rs.getString("department");
			String designation = rs.getString("designation");
			if (department != null && !department.isEmpty()) {
				name += "," + department;
			}
			if (designation != null && !designation.isEmpty()) {
				name += "," + designation;
			}
			BookingResponseModel brm = new BookingResponseModel();
			brm.setBookingId(rs.getString("req_id"));
			brm.setGuestId(rs.getString("guest_id"));
			brm.setRowNo(rs.getString("roll_no"));
			brm.setAdvanceAmount(rs.getString("advance_amt"));
			brm.setCityId(rs.getString("gh_id"));
			brm.setGhId(rs.getString("block_id"));
			brm.setPurpose(rs.getString("purpose"));
			brm.setDesg(rs.getString("desg_category"));
			brm.setCategory(rs.getString("category"));
			brm.setFromDate(frDt);
			brm.setToDate(toDt);
			brm.setArrivalDate(arrivalDate);
			brm.setDepartureDate(departureDate);
			brm.setName(name);
			brm.setAge(rs.getString("age"));
			brm.setNoRooms(rs.getString("room_no"));
			brm.setTrId(rs.getString("tr_id"));
			brm.setNoOfAdults(rs.getString("adults"));
			brm.setNoOfChildren(rs.getString("no_of_children_below12"));
			brm.setCount(rs.getInt("extra_guest"));
			return brm;
		}, bookingId);
		billRespModel.setGuestDetails(booking);

		while (null != prevBookingId) {
			roomRent += jdbcTemplate.queryForObject(rentQuery, Double.class, prevBookingId);
			BillRespModel nocDetails = jdbcTemplate.queryForObject(adminQueries.getProperty("getNocChargeForBill"), (rs, rowNum) -> {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				BillRespModel brm =  new BillRespModel();
				brm.setNocCharge(rs.getDouble("noc"));
				String frDt = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : "";
				String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : "";
				brm.setNocDate(frDt + " - " + toDt);
				return brm;
			}, prevBookingId);
			nocCharge = nocDetails.getNocCharge();
			nocDate = nocDetails.getNocDate();
			try {
				ExtensionRequestModel extensionRequestModel = jdbcTemplate.queryForObject(extensionExists,
						(rs, rowNum) -> {
							ExtensionRequestModel erm = new ExtensionRequestModel();
							erm.setExt(rs.getString("ext"));
							erm.setExisting_req_id(rs.getString("existing_req_id"));
							return erm;
						}, prevBookingId);
				if (extensionRequestModel.getExt().equalsIgnoreCase("1")) {
					prevBookingId = extensionRequestModel.getExisting_req_id();
				} else {
					prevBookingId = null;
				}
			} catch (Exception e) {
				prevBookingId = null;
			}
		}
		billRespModel.setRoomRent(roomRent);
		billRespModel.setFoodCharge(jdbcTemplate.queryForObject(foodChargeQuery, Double.class, bookingId));
		billRespModel.setNocCharge(nocCharge);
		billRespModel.setNocDate(nocDate);
		return billRespModel;
	}

	@Override
	public List<Map<String, Object>> getMastersCharges() {
		String sql = adminQueries.getProperty("getMasterCharges");
		try {
			return jdbcTemplate.queryForList(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional
	public boolean saveBillDetails(String userId, BillReqModel model, String shiftId) {
		String sql = adminQueries.getProperty("createBillMaster");
		String sql1 = adminQueries.getProperty("createBillVsGuest");
		String sql2 = adminQueries.getProperty("createBillVsGuestVsCharges");
		String sql3 = adminQueries.getProperty("createOtherCharges");
		String sql4 = adminQueries.getProperty("getupdatestatusinGadRequest");
		String sql5 = adminQueries.getProperty("updateCheckinStatusByReqId");
		sql5 += " AND status = 'COUT'";
		String sql6 = adminQueries.getProperty("createNocDuringBill");

		double total = model.getCharges().values().stream().mapToDouble(Double::doubleValue).sum();

		try {
			Integer billId = get_seqID("bill_id");
			update_seqID("bill_id", billId);

			Integer billNo = get_seqID("bill_no");
			update_seqID("bill_no", billNo);

			jdbcTemplate.update(sql, billId, billNo, userId, "BILL", shiftId, model.getCityId(), model.getGhId(),
					Math.round(total), Math.round(model.getAdvance()));

			for (String guest : model.getGuestIds()) {
				jdbcTemplate.update(sql1, billId, model.getBookingId(), guest);
			}

			// sort and get the first guest
			String guest = model.getGuestIds().stream().sorted().findFirst().get();

			for (Map.Entry<Integer, Double> entry : model.getCharges().entrySet()) {
				Integer seqId = get_seqID("guest_other_id");
				update_seqID("guest_other_id", seqId);
				jdbcTemplate.update(sql2, billId, model.getBookingId(), guest, entry.getKey(), entry.getValue());
				jdbcTemplate.update(sql3, seqId, model.getBookingId(), guest, entry.getKey(), entry.getValue(), "");
			}

			jdbcTemplate.update(sql4, "BILL", model.getBookingId());
			jdbcTemplate.update(sql5, "BILL", model.getBookingId());
			String date = model.getNocDate();
			if (!model.getNocDate().equals(" - ")) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String[] dates = date.split(" - ");
				String fromDate = dates[0];
				String toDate = dates[1];
				Date fromDt = sdf.parse(fromDate);
				Date toDt = sdf.parse(toDate);
				double nocCharge = model.getCharges().entrySet().stream().filter(e -> e.getKey() == 8).mapToDouble(Map.Entry::getValue).sum();

				Integer nocId = get_seqID("noc_id");
				update_seqID("noc_id", nocId);

				jdbcTemplate.update(sql6, nocId, model.getBookingId(), fromDt, toDt, nocCharge, "PAID", billId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public boolean cancelBill(String bookingId, String billId) {
		String sql = adminQueries.getProperty("updateBillStatusByBillId");
		String sql1 = adminQueries.getProperty("getupdatestatusinGadRequest");
		String sql2 = adminQueries.getProperty("updateCheckinStatusByReqId");

		sql2 += " AND status = 'BILL'";

		jdbcTemplate.update(sql, "CAN", billId);
		jdbcTemplate.update(sql1, "COUT", bookingId);
		jdbcTemplate.update(sql2, "COUT", bookingId);
		return true;
	}

	@Override
	public List<ReceiptModel> getAllReceiptList(Map<String, Object> params, HttpSession session) {List<ReceiptModel> data;
	String orderByCondition = " ORDER BY tb.date DESC";
	String query;
	StringBuilder whereCondition = new StringBuilder();
	List<Object> queryParams = new ArrayList<>();

	String type = (String) params.get("type");
	String cityId = (String) params.get("cId");
	String ghId = (String) params.get("gId");
	String bookingId = (String) params.get("bookingId");
	String fromDate = (String) params.get("fromDate");
	String mobNo = (String) params.get("mobNo");
	String name = (String) params.get("name");
	String category = (String) params.get("category");

	System.out.println("type   " + type);

	if (cityId != null && !cityId.isEmpty()) {
	    whereCondition.append(" AND gr.gh_id = ?");
	    queryParams.add(cityId);
	} else {
	    ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
	    String placeholders = String.join(",", Collections.nCopies(cityIdList.size(), "?"));
	    whereCondition.append(" AND gr.gh_id IN (").append(placeholders).append(")");
	    queryParams.addAll(cityIdList);
	}

	if (ghId != null && !ghId.isEmpty()) {
	    whereCondition.append(" AND gr.block_id = ?");
	    queryParams.add(ghId);
	}

	if (bookingId != null && !bookingId.isEmpty()) {
	    whereCondition.append(" AND gr.req_id = ?");
	    queryParams.add(bookingId);
	}

	if (category != null && !category.isEmpty()) {
	    whereCondition.append(" AND gr.category_type = ?");
	    queryParams.add(category);
	}

	if (fromDate != null && !fromDate.isEmpty()) {
	    try {
	        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
	        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
	        whereCondition.append(" AND gr.from_date::date = ?::date");
	        queryParams.add(formattedDate);
	    } catch (ParseException e) {
	        throw new RuntimeException(e);
	    }
	}

	if (mobNo != null && !mobNo.isEmpty()) {
	    whereCondition.append(" AND gr.mobile_no = ?");
	    queryParams.add(mobNo);
	}

	if (name != null && !name.isEmpty()) {
	    whereCondition.append(" AND gs.name ILIKE ?");
	    queryParams.add("%" + name + "%");
	}

	if ("RECT".equalsIgnoreCase(type)) {
	    orderByCondition = " ORDER BY r.date_rece DESC";
	    query = adminQueries.getProperty("getGeneratedReceiptList") + whereCondition + orderByCondition;
	    try {
	        data = jdbcTemplate.query(query, queryParams.toArray(), (rs, i) -> setGeneratedReceiptResp(rs));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    System.out.println("Generated Receipt Query: " + query);
	    return data;
	}

	query = adminQueries.getProperty("getAllReceiptList") + whereCondition + orderByCondition;
	data = jdbcTemplate.query(query, queryParams.toArray(), (rs, i) -> setMapReceiptResp(rs));
	System.out.println("Query for All Receipts: " + query);
	return data;
	}

	public ReceiptModel setGeneratedReceiptResp(ResultSet rs) throws SQLException {
		String sql = adminQueries.getProperty("getModeOfPayment");
		String sql1 = adminQueries.getProperty("getGuestDetailsForReceipt");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String date = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
		String rectDate = rs.getDate("date_rece") != null ? sdf1.format(rs.getTimestamp("date_rece")) : null;
		String frDt = rs.getDate("checkin_date") != null ? sdf1.format(rs.getTimestamp("checkin_date")) : null;
		String toDt = rs.getDate("checkout_date") != null ? sdf1.format(rs.getTimestamp("checkout_date")) : null;
		ReceiptModel rrm = new ReceiptModel();
		rrm.setRecId(rs.getString("rec_id"));
		rrm.setBillId(rs.getString("bill_id"));
		rrm.setBillNo(rs.getString("billno"));
		rrm.setDate(date);
		rrm.setRectDate(rectDate);
		rrm.setCityId(rs.getString("gh_id"));
		rrm.setGhId(rs.getString("block_id"));
		rrm.setPaidAmount(rs.getString("amount"));
		rrm.setTotal(rs.getString("total_amount"));
		rrm.setBookingId(rs.getString("req_id"));
		rrm.setFromDate(frDt);
		rrm.setToDate(toDt);
		String name = rs.getString("name");
		String department = rs.getString("department");
		String designation = rs.getString("designation");
		if (department != null && !department.isEmpty()) {
			name += ", " + department;
		}
		if (designation != null && !designation.isEmpty()) {
			name += ", " + designation;
		}
		rrm.setName(name);
		rrm.setCategory(rs.getString("category"));
		rrm.setRoomNo(rs.getString("room_no"));
		rrm.setMobileNo(rs.getString("mobile_no"));
		rrm.setPaymentModes(jdbcTemplate.queryForList(sql, rrm.getRecId()));
		List<String> names = jdbcTemplate.query(sql1, (rs2, rn) -> {
			String name1 = rs2.getString("name");
			String department1 = rs2.getString("department");
			String designation1 = rs2.getString("designation");
			if (department1 != null && !department1.isEmpty()) {
				name1 += ", " + department1;
			}
			if (designation1 != null && !designation1.isEmpty()) {
				name1 += ", " + designation1;
			}
			return name1;
		}, rrm.getBookingId(), rrm.getBillId());
		rrm.setGuest(names);
		rrm.setGuestHouseName(rs.getString("block_name"));
		rrm.setCityName(rs.getString("gh_name"));
		System.out.println("data value "+rrm);
		System.out.println("query for receipt generate payment "+sql);
		System.out.println("query for receipt generate payment "+sql1);
		return rrm;
	}

	public ReceiptModel setMapReceiptResp(ResultSet rs) throws SQLException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String date = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
		String frDt = rs.getDate("checkin_date") != null ? sdf1.format(rs.getTimestamp("checkin_date")) : null;
		String toDt = rs.getDate("checkout_date") != null ? sdf1.format(rs.getTimestamp("checkout_date")) : null;
		ReceiptModel rrm = new ReceiptModel();
		rrm.setBillId(rs.getString("bill_id"));
		rrm.setBillNo(rs.getString("billno"));
		rrm.setCreatedBy(rs.getString("createdby"));
		rrm.setDate(date);
		rrm.setCityId(rs.getString("gh_id"));
		rrm.setGhId(rs.getString("block_id"));
		rrm.setTotal(rs.getString("total_amt"));
		rrm.setBookingId(rs.getString("req_id"));
		rrm.setFromDate(frDt);
		rrm.setToDate(toDt);
		String name = rs.getString("name");
		String department = rs.getString("department");
		String designation = rs.getString("designation");
		if (department != null && !department.isEmpty()) {
			name += ", " + department;
		}
		if (designation != null && !designation.isEmpty()) {
			name += ", " + designation;
		}
		rrm.setName(name);
		rrm.setCategory(rs.getString("category"));
		rrm.setRoomNo(rs.getString("room_no"));
		rrm.setMobileNo(rs.getString("userid"));
		return rrm;
	}

	@Override
	public ReceiptModel getReceiptDetails(String bookingId, String billId) {
		String sql = adminQueries.getProperty("getAllReceiptList");
		String query = adminQueries.getProperty("getGuestDetailsForReceipt");

		String WHERE_CONDITION = " AND gr.req_id='" + bookingId + "' AND tb.bill_id='" + billId + "'";
		sql += WHERE_CONDITION;

		ReceiptModel receiptModel = jdbcTemplate.queryForObject(sql, (rs, rn) -> setMapReceiptResp(rs));

		List<String> names = jdbcTemplate.query(query, (rs, rn) -> {
			String name = rs.getString("name");
			String department = rs.getString("department");
			String designation = rs.getString("designation");
			if (department != null && !department.isEmpty()) {
				name += ", " + department;
			}
			if (designation != null && !designation.isEmpty()) {
				name += ", " + designation;
			}
			return name;
		}, bookingId, billId);

		receiptModel.setGuest(names);
		return receiptModel;
	}

	@Override
	@Transactional
	public boolean saveReceipt(ReceiptModel model, String shiftId) {
		String sql = adminQueries.getProperty("createReceipt");
		String sql1 = adminQueries.getProperty("createReceiptPaymentMode");
		String sql2 = adminQueries.getProperty("createReceiptVsBill");
		String sql3 = adminQueries.getProperty("getupdatestatusinGadRequest");
		String sql4 = adminQueries.getProperty("updateStatusAndFullPartialByBillId");
		String sql5 = adminQueries.getProperty("createDue");

		AtomicReference<String> orderId = new AtomicReference<>("");
		AtomicReference<Double> onlineAmount = new AtomicReference<>(0.0);

		try {
			Integer id = get_seqID("rec_id");
			update_seqID("rec_id", id);

			double totalPaid = model.getMode().entrySet().stream()
					.mapToDouble(e -> {
						if (e.getKey().equalsIgnoreCase("online")) {
							String q = approverQueries.getProperty("getRectOnlinePaymentDetails");
							PGModel pg = jdbcTemplate.queryForObject(q, new Object[] {model.getBillId()}, (rs, rn) -> {
								PGModel p = new PGModel();
								p.setOrderId(rs.getString("order_id"));
								p.setAmount(rs.getString("amount"));
								p.setBankRefNo(rs.getString("bank_ref_no"));
								return p;
							});
							orderId.set(pg.getOrderId());
							onlineAmount.set(Double.parseDouble(pg.getAmount()));
							return Double.parseDouble(pg.getAmount());
						} else
							return e.getValue();
					}).sum();

			String fullPartial = "F";
			if (totalPaid < Double.parseDouble(model.getTotal())) {
				fullPartial = "P";
			}
			jdbcTemplate.update(sql, id, totalPaid, "", "", "RECT", shiftId, model.getMobileNo(), model.getCityId(),
					model.getTotal(), fullPartial, model.getGhId(), "", "");

			for (Map.Entry<String, Double> entry : model.getMode().entrySet()) {
				Integer rpmId = get_seqID("rpm_id");
				update_seqID("rpm_id", rpmId);
				if (entry.getKey().equalsIgnoreCase("cash")) {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(), "", "", "");
				} else if (entry.getKey().equalsIgnoreCase("pos")) {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(),
							model.getTransactionNo(), "", "");
				} else {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), onlineAmount.get(),
							"", orderId.get(), "");
				}
			}

			if(fullPartial.equals("P")) {
				Integer dueId = get_seqID("due_id");
				update_seqID("due_id", dueId);
				jdbcTemplate.update(sql5, dueId, null, "DUE", Double.parseDouble(model.getTotal()) - totalPaid,
						model.getGhId(), model.getBillId());
			}

			jdbcTemplate.update(sql2, id, model.getBillId());
			jdbcTemplate.update(sql3, "RECT", model.getBookingId());
			jdbcTemplate.update(sql4, "RECT", fullPartial, model.getBillId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public List<BookingResponseModel> getGuestListForFoodBill(Map<String, Object> params, HttpSession session) {
		List<BookingResponseModel> data;
		String orderByCondition = " ORDER BY from_date DESC,req_id";
		String query = adminQueries.getProperty("getGuestsForFoodBill");
		StringBuilder whereCondition = new StringBuilder();

		Map<String, Object> queryParams = new HashMap<>();
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String Date = (String) params.get("fromDate");
		String mobNo = (String) params.get("mobNo");
		
		if (cityId != null && !cityId.isEmpty()) {
			whereCondition.append(" AND a.gh_id = :cityId");
	        queryParams.put("cityId", cityId);
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			whereCondition.append(" AND a.gh_id IN (:cityIds)");
	        queryParams.put("cityIds", cityIdList);
		}
		if (ghId != null && !ghId.isEmpty()) {
			whereCondition.append(" AND a.block_id = :ghId");
	        queryParams.put("ghId", ghId);
		}
		if (bookingId != null && !bookingId.isEmpty()) {
			whereCondition.append(" AND a.req_id = :bookingId");
	        queryParams.put("bookingId", bookingId);
		}
		if (Date != null && !Date.isEmpty()) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(Date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			Date = sdf1.format(date);
			whereCondition.append(" AND a.from_date::date = CAST(:fromDate AS DATE)");
            queryParams.put("fromDate", Date);
		}
		if (mobNo != null && !mobNo.isEmpty()) {
			whereCondition.append(" AND a.req_id = :mobNo");
	        queryParams.put("mobNo", mobNo);
		}
		query = query + whereCondition + orderByCondition;
		logger.info(query);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate =  new NamedParameterJdbcTemplate(jdbcTemplate);
		data = namedParameterJdbcTemplate.query(query, queryParams, (rs, i) -> {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String fromDate = sdf.format(rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : "");
				String toDate = sdf.format(rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : "");
				BookingResponseModel brm = new BookingResponseModel();
				brm.setBookingId(rs.getString("req_id"));
				brm.setFromDate(fromDate);
				brm.setToDate(toDate);
				String designation = rs.getString("designation");
				String name = rs.getString("name");
				String department = rs.getString("department");
				if (designation != null && !designation.isEmpty()) {
					name = name + " ," + designation;
				}
				if (department != null && !department.isEmpty()) {
					name = name + " ," + department;
				}
				brm.setName(name);
				String status = rs.getString("status");
				brm.setStatus(status);
				brm.setNoRooms(rs.getString("room_no"));
				brm.setGuestId(rs.getString("guest_id"));
				return brm;
			} catch (SQLException e) {
				logger.error("Error mapping row to BookingResponseModel", e);
				throw new RuntimeException("Error mapping row to BookingResponseModel.", e);
			}
		});
		return data;
	}

	@Override
	public boolean addFoodBill(FoodBillModel foodBillModel) {
		String sql = adminQueries.getProperty("addFoodBill");
		try {
			Integer id = get_seqID("food_bill_id");
			update_seqID("food_bill_id", id);
			jdbcTemplate.update(sql, id, foodBillModel.getBookingId(), foodBillModel.getGuestId(),
					foodBillModel.getItemId(), foodBillModel.getUnitPrice(), foodBillModel.getNoOfUnits(),
					foodBillModel.getTotal(), "2", foodBillModel.getFoodTime());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public ShiftMasterModel getCurrentShift(String cityId, String blockId, String userId) {
		String sql = adminQueries.getProperty("isShiftOpenInShiftMasterSql");
		sql += " AND createdby = ?";
		ShiftMasterModel shiftMasterModel = null;
		try {
			shiftMasterModel = jdbcTemplate.queryForObject(sql, new Object[] { cityId, blockId, userId },
					(rs, rowNum) -> {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						ShiftMasterModel smm = new ShiftMasterModel();
						smm.setCityId(rs.getString("gh_id"));
						smm.setBlockId(rs.getString("block_id"));
						smm.setUserId(rs.getString("createdby"));
						smm.setShiftId(rs.getString("shift_id"));
						smm.setCreatedTime(sdf.format(rs.getTimestamp("createdtime")));
						smm.setOpenBalRefund(rs.getString("openbal_refund"));
						smm.setOpenBalRegular(rs.getString("openbal_regular"));
						smm.setOpenBalTotal(rs.getString("openbal_total"));
						smm.setReceiptRefund(rs.getString("receipt_refund"));
						smm.setReceiptRegular(rs.getString("receipt_regular"));
						smm.setReceiptTotal(rs.getString("receipt_total"));
						smm.setPaymentRefund(rs.getString("payment_refund"));
						smm.setPaymentRegular(rs.getString("payment_regular"));
						smm.setPaymentTotal(rs.getString("payment_total"));
						smm.setStatus(rs.getString("status"));
						smm.setShiftNum(rs.getString("par_shiftid"));
						smm.setFromTime(sdf.format(rs.getTimestamp("from_time")));
						smm.setToTime(sdf.format(rs.getTimestamp("to_time")));
						smm.setCloseTime(
								rs.getTimestamp("close_time") != null ? sdf.format(rs.getTimestamp("close_time")) : "");
						return smm;
					});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return shiftMasterModel;
	}

	@Override
	public boolean updateShiftData(ShiftMasterModel shiftMasterModel) {
		String sql = adminQueries.getProperty("updateShiftDataSql");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			jdbcTemplate.update(sql, sdf.parse(shiftMasterModel.getCreatedTime()), shiftMasterModel.getOpenBalRefund(),
					shiftMasterModel.getOpenBalRegular(), shiftMasterModel.getOpenBalTotal(),
					shiftMasterModel.getShiftId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public boolean closePreviousShift(ShiftMasterModel shiftMasterModel) {
		String sql = adminQueries.getProperty("closePreviousShiftSql");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			jdbcTemplate.update(sql, sdf.parse(shiftMasterModel.getCloseTime()), shiftMasterModel.getOpenBalRegular(),
					shiftMasterModel.getOpenBalTotal(), shiftMasterModel.getShiftId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public BillRespModel showBillDetails(String bookingId, String billId) {
		logger.info("inside showBillDetails");
		String guestQuery = adminQueries.getProperty("getGuestDetailsForShowBill");
		String billMaster = adminQueries.getProperty("getBillMasterById");
		String charges = adminQueries.getProperty("getChargesById");
		String chargeTitle = adminQueries.getProperty("getChargeTitleById");
		BillRespModel billRespModel = new BillRespModel();

		billRespModel = jdbcTemplate.queryForObject(billMaster, new Object[] { billId }, (r, rowN) -> {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			BillRespModel br = new BillRespModel();
			br.setBillId(r.getString("bill_id"));
			br.setBillNo(r.getString("billno"));
			br.setBillDate(sdf1.format(r.getTimestamp("date")));
			br.setTotal(r.getString("total"));
			br.setAdvanceAmount(r.getString("advance"));
			String nocFromDate = r.getDate("noc_from_date") != null ? sdf1.format(r.getTimestamp("noc_from_date")) : "";
			String nocToDate = r.getDate("noc_to_date") != null ? sdf1.format(r.getTimestamp("noc_to_date")) : "";
			br.setNocDate(nocFromDate + " - " + nocToDate);

			List<BookingResponseModel> booking = jdbcTemplate.query(guestQuery, (rs, rowNum) -> {
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date"))
						: null;
				String arrivalDate = rs.getDate("checkin_date") != null
						? sdf1.format(rs.getTimestamp("checkin_date"))
						: null;
				String departureDate = rs.getDate("checkout_date") != null
						? sdf1.format(rs.getTimestamp("checkout_date"))
						: null;
				String name = rs.getString("name");
				String department = rs.getString("department");
				String designation = rs.getString("designation");
				if (department != null && !department.isEmpty()) {
					name += "," + department;
				}
				if (designation != null && !designation.isEmpty()) {
					name += "," + designation;
				}
				BookingResponseModel brm = new BookingResponseModel();
				brm.setBookingId(rs.getString("req_id"));
				brm.setGuestId(rs.getString("guest_id"));
				brm.setRowNo(rs.getString("roll_no"));
				brm.setAdvanceAmount(rs.getString("advance_amt"));
				brm.setCityId(rs.getString("gh_id"));
				brm.setGhId(rs.getString("block_id"));
				brm.setPurpose(rs.getString("purpose"));
				brm.setDesg(rs.getString("desg_category"));
				brm.setCategory(rs.getString("category"));
				brm.setFromDate(frDt);
				brm.setToDate(toDt);
				brm.setArrivalDate(arrivalDate);
				brm.setDepartureDate(departureDate);
				brm.setName(name);
				brm.setAge(rs.getString("age"));
				brm.setNoRooms(rs.getString("room_no"));
				brm.setTrId(rs.getString("tr_id"));
				brm.setNoOfAdults(rs.getString("adults"));
				brm.setNoOfChildren(rs.getString("no_of_children_below12"));
				brm.setCityName(rs.getString("gh_name"));
				brm.setGhName(rs.getString("block_name"));
				brm.setCount(rs.getInt("extra_guest"));
				return brm;
			}, bookingId);
			br.setGuestDetails(booking);
			List<CommonComboBoxResponseModel> chargesList = jdbcTemplate.query(charges, new Object[] { billId },
					(rs, rowNum) -> {
						CommonComboBoxResponseModel cbr = new CommonComboBoxResponseModel();
						String id = rs.getString("charges_id");
						String title = jdbcTemplate.queryForObject(chargeTitle, new Object[] { id }, String.class);
						cbr.setName(title);
						cbr.setDesc(rs.getString("amount"));
						return cbr;
					});
			br.setCharges(chargesList);
			return br;
		});
		return billRespModel;
	}

	@Override
	public List<RoomDetails> getCanRoomDetails(String type, String bookingId) {
		List<RoomDetails> roomDetails = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String sql = approverQueries.getProperty("getRoomAllotDtlsByBookIdSQL");
		String sql1 = approverQueries.getProperty("getPartialCancelByReqId");
		String sql2 = approverQueries.getProperty("getNocByReqId");
		if (type.equals("C")) {
			try {
				roomDetails = jdbcTemplate.query(sql, (rs, rn) -> {
					RoomDetails res = new RoomDetails();
					res.setAllotId(rs.getString("allot_id"));
					res.setFromDate(sdf.format(rs.getDate("from_date")));
					res.setToDate(sdf1.format(rs.getTimestamp("to_date")));
					res.setRoomNo(rs.getString("room_no"));
					res.setRoomRent(rs.getString("room_rent"));
					res.setToDate(sdf1.format(rs.getTimestamp("from_date")) + " - " + res.getToDate());
					res.setOccupied(true);
					return res;
				}, bookingId);

				NocModel nocModel = null;
				try {
					nocModel = jdbcTemplate.queryForObject(sql2, (rs, rn) -> {
						NocModel noc = new NocModel();
						noc.setFromDate(sdf.format(rs.getDate("from_date")));
						noc.setToDate(sdf.format(rs.getDate("to_date")));
						return noc;
					}, bookingId);
				} catch (Exception e) {
					nocModel = null;
				}
				if (null != nocModel) {
                    for (int i = 0; i < roomDetails.size(); i++) {
                        RoomDetails rd = roomDetails.get(i);
                        Date date1 = sdf.parse(rd.getFromDate());
                        Date date2 = sdf.parse(nocModel.getFromDate());
                        if (date2.after(date1)) {
                            rd.setOccupied(false);
                        }
                    }
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		} else {
			BookingResponseModel brm = jdbcTemplate.queryForObject(sql1, (rs, rn) -> {
				BookingResponseModel b = new BookingResponseModel();
				b.setFromDate(sdf.format(rs.getDate("from_date")));
				b.setToDate(sdf.format(rs.getDate("to_date")));
				return b;
			}, bookingId);
			List<LocalDate> datesBetween = Utils.getDatesBetween(LocalDate.parse(brm.getFromDate()),
					LocalDate.parse(brm.getToDate()));
			try {
				roomDetails = jdbcTemplate.query(sql, (rs, rn) -> {
					RoomDetails res = new RoomDetails();
					res.setAllotId(rs.getString("allot_id"));
					res.setFromDate(sdf1.format(rs.getTimestamp("from_date")));
					res.setToDate(sdf1.format(rs.getTimestamp("to_date")));
					res.setRoomNo(rs.getString("room_no"));
					res.setRoomRent(rs.getString("room_rent"));
					res.setToDate(res.getFromDate() + " - " + res.getToDate());
					return res;
				}, bookingId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			for(LocalDate date : datesBetween) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String dateStr = date.format(formatter) + " 09:00";
				for(RoomDetails rd : roomDetails) {
					if(rd.getFromDate().equals(dateStr)) {
						rd.setOccupied(true);
					}
				}
			}
		}
		return roomDetails;
	}

	@Override
	public DataTableResponse getNocList(DataTableRequest params) {
		System.out.println(params.toString());
		List<ReceiptModel> nocList;

		String sql = approverQueries.getProperty("getNocList");
		String sql1 = approverQueries.getProperty("getNocListCount");
		StringBuilder whereCondition = new StringBuilder();
		
		Map<String, Object> queryParams = new HashMap<>();

		String cityId = params.getCity();
		String ghId = params.getgHouse();
		String bookingId = params.getBookingId();
		String mobNo = params.getMobNo();
		String name = params.getName();
		String type = params.getType();
		int start = params.getStart();

		System.out.println("cityId"+cityId+"GuestHouse"+ghId);

		if (null != type && type.equalsIgnoreCase("PAID")) {
			whereCondition.append(" AND nt.status = :status");
	        queryParams.put("status", "PAID");
		} else {
			whereCondition.append(" AND nt.status IS NULL");
		}
		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false) {
			whereCondition.append(" AND gr.gh_id = :cityId");
	        queryParams.put("cityId", cityId);
		}
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			whereCondition.append(" AND gr.block_id = :ghId");
		    queryParams.put("ghId", ghId);
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition.append(" AND gr.req_id = :bookingId");
	        queryParams.put("bookingId", bookingId);
		}
		if (name != null ? !name.isEmpty() : false) {
			whereCondition.append(" AND gs.name ILIKE :name");
	        queryParams.put("name", "%" + name + "%");
		}
		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition.append(" AND gr.mobile_no = :mobNo");
	        queryParams.put("mobNo", mobNo);
		}
		
		sql += whereCondition.toString();
	    sql1 += whereCondition.toString();
	    
	    if (params.getLength() != 0) {
	        sql += " LIMIT :length OFFSET :start";
	        queryParams.put("length", params.getLength());
	        queryParams.put("start", start);
	    }

	    logger.info(sql);
	    logger.info(sql1);
	    
	    final String countQuery = sql1;
	    ExecutorService executorService = Executors.newFixedThreadPool(2);

	    CompletableFuture<Integer> count = CompletableFuture.supplyAsync(() -> {
	        try {
	        	NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
				return namedParameterJdbcTemplate .queryForObject(countQuery, queryParams, Integer.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }, executorService);
	    
	    final String dataQuery = sql;

	    CompletableFuture<List<ReceiptModel>> data = CompletableFuture.supplyAsync(() -> {
	    	NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	        return namedParameterJdbcTemplate.query(dataQuery, queryParams, (rs, rowNum) -> {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	            String fromDate = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : "";
	            String toDate = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : "";

	            ReceiptModel rm = new ReceiptModel();
	            rm.setMobileNo(rs.getString("userid"));
	            rm.setBookingId(rs.getString("req_id"));
	            rm.setName(rs.getString("name"));
	            rm.setTotal(rs.getString("noc_charge"));
	            rm.setFromDate(fromDate);
	            rm.setToDate(toDate);
	            rm.setCityId(rs.getString("gh_id"));
	            rm.setGhId(rs.getString("block_id"));
	            rm.setBillId(rs.getString("noc_id"));
	            rm.setStatus(rs.getString("status"));

	            if (type != null && type.equalsIgnoreCase("PAID")) {
	                String sql2 = adminQueries.getProperty("getModeOfPayment");
	                rm.setRecId(rs.getString("rec_id"));
	                rm.setPaymentModes(jdbcTemplate.queryForList(sql2, rm.getRecId()));
	                rm.setPaidAmount(rs.getString("pay_amount"));
	                String rectDate = rs.getDate("date_of_pay") != null ? sdf.format(rs.getTimestamp("date_of_pay")) : "";
	                rm.setRectDate(rectDate);
	            }
	            rm.setGuestHouseName(rs.getString("block_name"));
	            rm.setCityName(rs.getString("gh_name"));
	            return rm;
	        });
	    }, executorService);

	    DataTableResponse dt = new DataTableResponse();
	    try {
	        dt.setDraw(params.getDraw());
	        dt.setRecordsTotal(count.get());
	        dt.setRecordsFiltered(count.get());
	        dt.setData(data.get());
	    } catch (InterruptedException | ExecutionException e) {
	        e.printStackTrace();
	    } finally {
	        executorService.shutdown();
	    }
	    return dt;
	}

	@Override
	public boolean cancelNoc(String nocId) {
		String sql = approverQueries.getProperty("updateNocStatus");
		try {
			jdbcTemplate.update(sql, "CAN", Integer.parseInt(nocId));
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Some error occurred while cancelling noc");
		}
		return true;
	}

	@Override
	public boolean saveNocPayment(ReceiptModel model) {
		String sql = approverQueries.getProperty("updateNocReceipt");
		String sql1 = adminQueries.getProperty("createReceiptPaymentMode");

		AtomicReference<String> orderId = new AtomicReference<>("");
		AtomicReference<Double> onlineAmount = new AtomicReference<>(0.0);
		try {
			Integer nocId = Integer.parseInt(model.getBillId());
			Integer id = get_seqID("rec_id");
			update_seqID("rec_id", id);

			double totalPaid = model.getMode().entrySet().stream()
					.mapToDouble(e -> {
						if (e.getKey().equalsIgnoreCase("online")) {
							String q = approverQueries.getProperty("getNOCOnlinePaymentDetails");
							PGModel pg = jdbcTemplate.queryForObject(q, new Object[] {nocId}, (rs, rn) -> {
								PGModel p = new PGModel();
								p.setOrderId(rs.getString("order_id"));
								p.setAmount(rs.getString("amount"));
								p.setBankRefNo(rs.getString("bank_ref_no"));
								return p;
							});
							orderId.set(pg.getOrderId());
							onlineAmount.set(Double.parseDouble(pg.getAmount()));
							return Double.parseDouble(pg.getAmount());
						} else
							return e.getValue();
					}).sum();

			if (totalPaid < Double.parseDouble(model.getTotal())) {
				return false;
			}

			jdbcTemplate.update(sql, "PAID", id, totalPaid, nocId);

			for (Map.Entry<String, Double> entry : model.getMode().entrySet()) {
				Integer rpmId = get_seqID("rpm_id");
				update_seqID("rpm_id", rpmId);
				if (entry.getKey().equalsIgnoreCase("CASH")) {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(), "", "", "");
				} else if (entry.getKey().equalsIgnoreCase("POS")) {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(), model.getTransactionNo(), "",
							"");
				} else {
					jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), onlineAmount.get(), "", orderId.get(), "");
				}
			}

			String query = approverQueries.getProperty("getNocDetailsForMessage");
			BookingResponseModel booking = jdbcTemplate.queryForObject(query, new Object[] { nocId }, (rs, rowNum) -> {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				BookingResponseModel brm = new BookingResponseModel();
				brm.setFromDate(sdf.format(rs.getDate("from_date")));
				brm.setToDate(sdf.format(rs.getDate("to_date")));
				brm.setGhName(rs.getString("block_name"));
				brm.setCityName(rs.getString("gh_name"));
				brm.setMobile(rs.getString("userid"));
				brm.setName(rs.getString("name"));
				brm.setEmail(rs.getString("email_id"));
				brm.setAdvanceAmount(rs.getString("noc_charge"));
				brm.setBookingId(rs.getString("req_id"));
				return brm;
			});
			String message = "Dear " + booking.getName() + ", amount of Rs " + booking.getAdvanceAmount() + " has" +
					" been successfully received towards your NOC for the previous booking from " +
					booking.getFromDate() + " to " + booking.getToDate() + " in " + booking.getGhName() + ", " +
					booking.getCityName() + " - Odisha Home Dept";
			if (booking.getMobile() != null && !booking.getMobile().isEmpty()) {
				smsSend.sendSMS(booking.getMobile(), message, "1107168975494899044");
			}
			if (booking.getEmail() != null && !booking.getEmail().isEmpty()) {
				//mail.sendMimeMail(booking.getEmail(), "NOC Payment Successful", message);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	@Override
	public List<BookingResponseModel> getAllConferenceDataForApprove(HttpServletRequest request) {
	    List<BookingResponseModel> alldetailsValue = new ArrayList<>();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    String cityName = null;
	    String guesthouseName = null;

	    // Improved parameter checks for cityName and guesthouseName
	    if (request.getParameter("cityName") != null && !request.getParameter("cityName").equals("")) {
	        cityName = request.getParameter("cityName");
	    }
	    if (request.getParameter("guesthouseName") != null && !request.getParameter("guesthouseName").equals("")) {
	        guesthouseName = request.getParameter("guesthouseName");
	    }

	    // Initializing the SQL query and parameter list
	    String sql = adminQueries.getProperty("getAllConferenceListForApprove");
	    List<Object> queryParams = new ArrayList<>();
	    queryParams.add("ALO");
	    // Dynamically build the SQL query with optional conditions
	    if (cityName != null && !cityName.isEmpty()) {
	        sql = sql + " AND gr.gh_id = ?";
	        queryParams.add(cityName);
	    }
	    if (guesthouseName != null && !guesthouseName.isEmpty()) {
	        sql = sql + " AND gr.block_id = ?";
	        queryParams.add(guesthouseName);
	    }

	    try {
	        // Execute the query with parameterized values
	        alldetailsValue = jdbcTemplate.query(sql, queryParams.toArray(), (rs, rownumber) -> {
	            String fromDate = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : "";
	            String toDate = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : "";
	            String reqDate = rs.getDate("req_date") != null ? sdf.format(rs.getTimestamp("req_date")) : "";

	            // Mapping the result set to the model
	            BookingResponseModel response = new BookingResponseModel();
	            response.setCityId(rs.getString("gh_id"));
	            response.setGhId(rs.getString("block_id"));
	            response.setBookingId(rs.getString("req_id"));
	            response.setBookingDt(reqDate);
	            response.setFromDate(fromDate);
	            response.setToDate(toDate);
	            response.setNoOfChildren(rs.getString("name"));
	            response.setName(rs.getString("concat"));
	            response.setCategory(rs.getString("category"));
	            response.setCapacity(rs.getString("capacity"));
	            response.setAdvanceAmount(rs.getString("charge"));
	            response.setConfUseDesc(rs.getString("conf_use_desc"));
	            response.setConfCatDesc(rs.getString("conf_cat_desc"));
	            return response;
	        });

	    } catch (Exception e) {
	        // Log the error message along with SQL query for debugging
	        logger.error("Error occurred while fetching conference list: " + sql, e);
	        throw new RuntimeException("Some error occurred while fetching conference list");
	    }

	    // Optionally log the final SQL query for debugging
	    logger.info("SQL Query executed: " + sql);

	    // Return the list (can be empty if no results)
	    return alldetailsValue;
	}


	@Override
	public List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session) {
	    List<BookingResponseModel> data = new ArrayList<>();
	    String orderByCondition = " ORDER BY gr.req_date DESC";
	    String sql = adminQueries.getProperty("getAllConferenceList");
	    StringBuilder whereCondition = new StringBuilder();

	    String cityId = (String) params.get("cId");
	    String ghId = (String) params.get("gId");
	    String bookingId = (String) params.get("bookingId");
	    String bookingDate = (String) params.get("bookingDate");
	    String mobNo = (String) params.get("mobNo");
	    String name = (String) params.get("name");
	    String category = (String) params.get("category");

	    List<Object> queryParams = new ArrayList<>();
	    queryParams.add("REQ");
	    if (cityId != null && !cityId.isEmpty()) {
	        whereCondition.append(" AND gr.gh_id = ?");
	        queryParams.add(cityId);
	    } else {
	        ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
	        String cityIdString = String.join("', '", cityIdList);
	        whereCondition.append(" AND gr.gh_id IN ('").append(cityIdString).append("')");
	    }

	    if (ghId != null && !ghId.isEmpty()) {
	        whereCondition.append(" AND gr.block_id = ?");
	        queryParams.add(ghId);
	    }
	    
	    if (bookingId != null && !bookingId.isEmpty()) {
	        whereCondition.append(" AND gr.req_id = ?");
	        queryParams.add(bookingId);
	    }
	    
	    if (bookingDate != null && !bookingDate.isEmpty()) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date date = sdf.parse(bookingDate);
	            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	            bookingDate = sdf1.format(date);
	            whereCondition.append(" AND gr.req_date::date = CAST(? AS date)");
	            queryParams.add(bookingDate);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    if (category != null && !category.isEmpty()) {
	        whereCondition.append(" AND gr.category_type = ?");
	        queryParams.add(category);
	    }

	    if (mobNo != null && !mobNo.isEmpty()) {
	        whereCondition.append(" AND gr.mobile_no = ?");
	        queryParams.add(mobNo);
	    }

	    if (name != null && !name.isEmpty()) {
	        whereCondition.append(" AND tr.name ILIKE ?");
	        queryParams.add("%" + name + "%");
	    }

	    sql = sql + whereCondition.toString() + orderByCondition;
	    logger.info(sql);

	    try {
	        data = jdbcTemplate.query(sql, queryParams.toArray(), (rs, rownumber) -> {
	            BookingResponseModel brm = new BookingResponseModel();
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	            String bookingDt = rs.getDate("req_date") != null ? sdf.format(rs.getTimestamp("req_date")) : null;
	            String frDt = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : null;
	            String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : null;
	            brm.setBookingId(rs.getString("req_id"));
	            brm.setBookingDt(bookingDt);
	            brm.setName(rs.getString("name"));
	            brm.setFromDate(frDt);
	            brm.setToDate(toDt);
	            brm.setCityId(rs.getString("gh_id"));
	            brm.setGhId(rs.getString("block_id"));
	            brm.setMobile(rs.getString("mobile_no"));
	            String status_desc = "Not taken up for processing";
	            brm.setStatus(rs.getString("status"));
	            brm.setStatusDesc(status_desc);
	            brm.setCatgId(rs.getString("quota_type"));
	            brm.setCategory(rs.getString("category"));
	            brm.setTypeOfRoom(rs.getString("conf_cat_desc"));
	            brm.setAddress(rs.getString("address"));
	            brm.setCapacity(rs.getString("capacity"));
	            brm.setTotalAmt(rs.getString("charge"));
	            brm.setAdditionalCharge(rs.getString("additionalcharge"));
	            brm.setAdvanceAmount(rs.getString("advance_amount"));
	            brm.setModeOfPayment(rs.getString("mode_of_payment"));
	            brm.setConfBillId(rs.getString("conf_bill_id"));
	            brm.setBillStatus(rs.getString("bill_status"));
	            brm.setConfCatId(rs.getString("conf_cat_id"));
	            brm.setConfUseId(rs.getString("conf_use_id"));
	            brm.setConfUseDesc(rs.getString("conf_use_desc"));
	            return brm;
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Some error occurred while fetching conference list");
	    }
	    return data;
	}


	@Override
	public boolean confModeration(String userId, RoomBookingPojo data) {
		String query = allotmentQueries.getProperty("getModReqTbInsertAsPerModReqSQL");
		String query2 = adminQueries.getProperty("getupdatestatusinGadRequest");
		Integer modId;
		try {
			modId = guestDao.get_seqID("mod_id");
			guestDao.update_seqID("mod_id", modId);
			jdbcTemplate.update(query, data.getBookingId(), modId, data.getCityId(), data.getCatg(),
					data.getDesg(), "0", "0", "0", "ALO");
			jdbcTemplate.update(query2, "ALO", data.getBookingId());
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new RuntimeException("Some error occurred while fetching sequence id");
		}

		return true;
	}

	@Override
	public void updateOrderIdInBill(String orderId, String billId) {
		String query = approverQueries.getProperty("updateOrderIdInBill");
		try {
			jdbcTemplate.update(query, orderId, billId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Some error occurred while saving payment info");
		}
	}

	@Override
	public void updateOrderIdInNOC(String orderId, String nocId) {
		String query = approverQueries.getProperty("updateOrderIdInNOC");
		try {
			jdbcTemplate.update(query, orderId, Integer.parseInt(nocId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Some error occurred while saving payment info");
		}
	}

	@Override
	public void updateOrderIdInConfBill(String orderId, String billId) {
		String query = approverQueries.getProperty("updateOrderIdInConfBill");
		try {
			jdbcTemplate.update(query, orderId, billId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Some error occurred while saving payment info");
		}
	}

    @Override
    public boolean partialCancel(String userId, BookingResponseModel requestPojo) {
		try {
			String sql = approverQueries.getProperty("createPartialCancelRequest");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			Date fromDate = sdf.parse(requestPojo.getFromDate());
			Date toDate = sdf.parse(requestPojo.getToDate());
			Integer id = get_seqID("pcr_id");
			update_seqID("pcr_id", id);
			jdbcTemplate.update(sql, id,
					fromDate,
					toDate,
					requestPojo.getBookingId(),
					"REQ");
			String sql2 = approverQueries.getProperty("updateCanStatusByReqId");
			jdbcTemplate.update(sql2, "PCAN",
					userId,
					null,
					requestPojo.getBookingId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while partial cancel request");
		}
		return true;
    }

    /* Mohit AdminDaoImpl Ends */

	/* Sandip AdminDaoImpl Starts */
	@Override
	public List<OtherguestCheckinDetails> getCheckinOtherGuestData(Map<String, Object> params) {
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String req_id = (String) params.get("req_id");
		String hidval = (String) params.get("hidval");
		String check_qry = adminQueries.getProperty("otherGuestDetails");
		String room_qry = adminQueries.getProperty("otherGuestRoomDetails");
		String otherGstFlqry = check_qry + "and g.guest_id not in ('" + hidval + "')";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		List<OtherguestCheckinDetails> otherGuest1 = new ArrayList<>();
		try {
			otherGuest1 = jdbcTemplate.query(otherGstFlqry, new RowMapper<OtherguestCheckinDetails>() {
				@Override
				public OtherguestCheckinDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					OtherguestCheckinDetails otherGuest = new OtherguestCheckinDetails();
					otherGuest.setReqId(rs.getString("req_id"));
					otherGuest.setGuestId(rs.getString("guest_id"));
					otherGuest.setName(rs.getString("name"));
					otherGuest.setAge(rs.getString("age"));
					otherGuest.setGender(rs.getString("gender"));
					otherGuest.setDesgination(rs.getString("designation"));
					otherGuest.setDepartment(rs.getString("department"));
					otherGuest.setAddress(rs.getString("address"));
					otherGuest.setGuest_mobile(rs.getString("guest_mobile"));
					otherGuest.setStatus(rs.getString("status"));
					otherGuest.setTrId(rs.getString("tr_id"));
					try {
						String arrivalDate = !rs.getTimestamp("arrival_time_date").equals("") ? sdf1.format(rs.getTimestamp("arrival_time_date")) : null;
						otherGuest.setArrivalTimeDate(arrivalDate);
					} catch (Exception e1) {
					}					
					otherGuest.setRollNo(rs.getString("roll_no"));
					otherGuest.setAdvanceAmount(rs.getString("advance_amt"));
					otherGuest.setNoOfChildrenBelow12(rs.getString("no_of_children_below12"));
					otherGuest.setAdults(rs.getString("adults"));
					try {
						RoomDetails roomDetails = jdbcTemplate.queryForObject(room_qry,
								new Object[] { otherGuest.getTrId() }, (ResultSet rsRm, int rownumberRm) -> {
									RoomDetails roomDetails1 = new RoomDetails();
									roomDetails1.setRoomNo(rsRm.getString("room_no"));
									roomDetails1.setBedNo(rsRm.getString("bed_no"));
									roomDetails1.setType(rsRm.getString("type"));
									return roomDetails1;
								});
						otherGuest.setRoomDetails(roomDetails);
					} catch (Exception e) {
						otherGuest.setRoomDetails(null);
					}
					return otherGuest;
				}
			}, cityId, req_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(otherGuest1);
		return otherGuest1;
	}

	@Override
	public List<GuestDetails> getCheckinGuest(Map<String, Object> params) {
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String req_id = (String) params.get("req_id");
		String hidval = (String) params.get("hidval");
		String check_qry = adminQueries.getProperty("otherCheckinRoomDetails");
		String fullQuery = check_qry + " and g.guest_id in ('" + hidval + "')";
		System.out.println(fullQuery);
		List<GuestDetails> otherGuest1 = new ArrayList<>();
		try {
			otherGuest1 = jdbcTemplate.query(fullQuery, new RowMapper<GuestDetails>() {
				@Override
				public GuestDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					GuestDetails checkinGuest = new GuestDetails();
					checkinGuest.setReqId(rs.getString("req_id"));
					checkinGuest.setGuestId(rs.getString("guest_id"));
					checkinGuest.setName(rs.getString("name"));
					checkinGuest.setAge(rs.getString("age"));
					checkinGuest.setGender(rs.getString("gender"));
					checkinGuest.setDesignation(rs.getString("designation"));
					checkinGuest.setDepartment(rs.getString("department"));
					checkinGuest.setAddress(rs.getString("address"));
					return checkinGuest;
				}
			}, ghId, req_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otherGuest1;
	}

//	@Override
//	public List<DesignationModel> getAllcategory() {
//		String Query = "select vc_id,desg_category from visitor_category";
//		List<DesignationModel> otherGuest1 = new ArrayList<>();
//		try {
//			otherGuest1 = jdbcTemplate.query(Query, new RowMapper<DesignationModel>() {
//				@Override
//				public DesignationModel mapRow(ResultSet rs, int rownumber) throws SQLException {
//					DesignationModel checkinGuest = new DesignationModel();
//					checkinGuest.setCategoryId(rs.getString("vc_id"));
//					checkinGuest.setCategoryName(rs.getString("desg_category"));
//					return checkinGuest;
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return otherGuest1;
//	}
//
//	@Override
//	public List<String> getAllpurpose() {
//		String Query = "select * from m_purpose_items";
//		List<String> otherGuest1 = new ArrayList<>();
//		try {
//			otherGuest1 = jdbcTemplate.query(Query, new RowMapper<String>() {
//				@Override
//				public String mapRow(ResultSet rs, int rownumber) throws SQLException {
//					String purpose = new String();
//					purpose = rs.getString("item_title");
//					return purpose;
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return otherGuest1;
//	}

	@Override
	public String getSelectPurpose(Map<String, Object> params) {
		String query = "select b.item_title from gad_request a join m_purpose_items b on  a.purpose=b.item_id where req_id=?";
		String purpose = null;
		try {
			purpose = jdbcTemplate.queryForObject(query, new Object[] { (String) params.get("req_id") }, String.class);

		} catch (Exception e) {
		}
		return purpose;
	}

	@Override
	public DesignationModel getSelectDesgination(Map<String, Object> params) {
		String query = adminQueries.getProperty("selectDesgination");
		String reqId = (String) params.get("req_id");
		DesignationModel designationModel = new DesignationModel();
		try {
			designationModel = jdbcTemplate.queryForObject(query, new Object[] { reqId }, (rs, rownumber) -> {
				DesignationModel designationModel1 = new DesignationModel();
				designationModel1.setId(rs.getString("vc_id"));
				designationModel1.setDesgName(rs.getString("desg_category"));
				return designationModel1;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return designationModel;
	}

	@Override
	public List<CheckinRoom> getRoomDetails(Map<String, Object> params) {
		String blockId = (String) params.get("gId");
		String reqId = (String) params.get("req_id");
		String roomQuery = adminQueries.getProperty("CheckinRoomDetails");
		List<CheckinRoom> room = new ArrayList<>();
		try {
			room = jdbcTemplate.query(roomQuery, new RowMapper<CheckinRoom>() {
				@Override
				public CheckinRoom mapRow(ResultSet rs, int rownumber) throws SQLException {
					CheckinRoom checkinRoom = new CheckinRoom();
					checkinRoom.setRoomNo(rs.getString("room_no"));
					checkinRoom.setType(rs.getString("type"));
					checkinRoom.setBedNo(rs.getString("bed_no"));
					return checkinRoom;
				}
			}, reqId, blockId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return room;
	}

	@Override
	public Pagination getCheckinDetails(Map<String, Object> params, HttpSession session) {String check_qry = approverQueries.getProperty("checkinDetails");
	String room_qry = approverQueries.getProperty("roomDetails");
	String gst_qry = approverQueries.getProperty("guestDetails");
	String gst_sts = approverQueries.getProperty("guestCheckinStatus");
	String countGst = adminQueries.getProperty("countCheckIn");
	String pcanFromDt = adminQueries.getProperty("getPcanFromDt");
	int pageNo = (int) params.get("pageNo");
	String orderByCondition = " ORDER BY c.from_date DESC";
	String whereCondition = " WHERE 1=1 AND date(c.to_date)>=date(now())";

	String cityId = (String) params.get("cId");
	String ghId = (String) params.get("gId");
	String bookingId = (String) params.get("bookingId");
	String fromDate = (String) params.get("fromDate");
	String name = (String) params.get("name");
	String category = (String) params.get("category");

	List<Object> queryParams = new ArrayList<>();
	List<Object> queryParams1 = new ArrayList<>();
	queryParams.add(cityId);
	queryParams.add(ghId);
	queryParams.add(cityId);
	queryParams.add(ghId);
	queryParams1.add(cityId);
	queryParams1.add(ghId);
	queryParams1.add(cityId);
	queryParams1.add(ghId);
	// Construct the WHERE conditions dynamically based on input parameters
//	if (cityId != null && !cityId.isEmpty()) {
//	    whereCondition += " AND c.gh_id = ?";
//	    queryParams.add(cityId);
//	} else {
//	    ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
//	    String cityIdString = String.join("', '", cityIdList);
//	    whereCondition += " AND c.gh_id in ('" + cityIdString + "')";
//	}
//
//	if (ghId != null && !ghId.isEmpty()) {
//	    whereCondition += " AND c.block_id = ?";
//	    queryParams.add(ghId);
//	}

	if (bookingId != null && !bookingId.isEmpty()) {
	    whereCondition += " AND c.req_id = ?";
	    queryParams.add(bookingId);
	}

	if (category != null && !category.isEmpty()) {
	    whereCondition += " AND c.category_type = ?";
	    queryParams.add(category);
	}

	if (fromDate != null && !fromDate.isEmpty()) {
	    Date date;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    try {
	        date = sdf.parse(fromDate);
	    } catch (ParseException e) {
	        throw new RuntimeException(e);
	    }
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	    fromDate = sdf1.format(date);
	    whereCondition += " AND c.from_date::date = CAST(? AS date)";
	    queryParams.add(fromDate);
	}


	if (name != null && !name.isEmpty()) {
	    whereCondition += " AND c.name ILIKE ?";
	    queryParams.add("%" + name + "%");
	}

	check_qry += whereCondition + orderByCondition;
	countGst += whereCondition;

	if (pageNo != 0) {
	    int offset = (pageNo - 1) * Constants.LIMIT;
	    check_qry += " LIMIT " + Constants.LIMIT + " OFFSET " + offset;
	}

	Integer pageCount = null;
	try {
	    pageCount = jdbcTemplate.queryForObject(countGst, queryParams.toArray(), Integer.class);
	} catch (Exception e) {
	    pageCount = 0;
	    e.printStackTrace();
	}

	final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	List<CheckIn> checkIn = jdbcTemplate.query(check_qry, queryParams.toArray(), new RowMapper<CheckIn>() {
	    @Override
	    public CheckIn mapRow(ResultSet rs, int rownumber) throws SQLException {
	        CheckIn rest = new CheckIn();
	        rest.setName(rs.getString("name"));
	        rest.setGh_id(rs.getString("gh_id"));
	        rest.setReq_id(rs.getString("req_id"));
	        rest.setFrom_date(sdf.format(rs.getTimestamp("from_date")));
	        rest.setTo_date(sdf.format(rs.getTimestamp("to_date")));
	        if ("PCAN".equals(rs.getString("can_status"))) {
	            Date toDt = jdbcTemplate.queryForObject(pcanFromDt, new Object[] { rest.getReq_id(), "CAN" }, Date.class);
	            String toDate = toDt != null ? sdf.format(toDt) : "";
	            rest.setTo_date(toDate);
	        }
	        rest.setStatus(rs.getString("status"));

	        String category_Desc = "";
	        String category = rs.getString("category_type");
	        switch (category) {
	            case "C1":
	                category_Desc = "Category 1";
	                break;
	            case "C2":
	                category_Desc = "Category 2";
	                break;
	            case "C3":
	                category_Desc = "Category 3";
	                break;
	            case "C4":
	                category_Desc = "Category 4";
	                break;
	        }
	        rest.setCategoryType(category_Desc);

	        List<RoomDetails> roomDetails = jdbcTemplate.query(room_qry, new Object[] { rest.getReq_id(), ghId }, new RowMapper<RoomDetails>() {
	            @Override
	            public RoomDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
	                RoomDetails resr = new RoomDetails();
	                resr.setBedNo(rs.getString("bed_no"));
	                resr.setRoomBed(rs.getString("roombed"));
	                resr.setRoomNo(rs.getString("room_no"));
	                resr.setType(rs.getString("type"));
	                return resr;
	            }
	        });
	        rest.setRoomDetails(roomDetails);

	        List<GuestDetails> gustDetails = jdbcTemplate.query(gst_qry, new Object[] { rest.getReq_id() }, new RowMapper<GuestDetails>() {
	            @Override
	            public GuestDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
	                GuestDetails res = new GuestDetails();
	                res.setName(rs.getString("name"));
	                res.setDepartment(rs.getString("department"));
	                res.setDesignation(rs.getString("designation"));
	                res.setAddress(rs.getString("address"));
	                res.setGuestId(rs.getString("guest_id"));
	                try {
	                    String status = jdbcTemplate.queryForObject(gst_sts, new Object[] { rest.getReq_id(), res.getGuestId() }, String.class);
	                    res.setStatus(status);
	                } catch (Exception e) {
	                    res.setStatus("null");
	                }
	                return res;
	            }
	        });
	        rest.setGuestDetails(gustDetails);

	        return rest;
	    }
	});

	Pagination pagination = new Pagination();
	pagination.setData(checkIn);
	pagination.setTotalPages(pageCount);

	return pagination;
}

	@Override
	public void saveCheckInDetails(Map<String, Object> params) {
		String ghId = (String) params.get("gId");
		String req_id = (String) params.get("req_id");
		String roomQuery = adminQueries.getProperty("saveCheckInDetails");

	}

	/**
	 * @throws Exception 
	 *
	 */
	@Override
	public boolean setCheckInUser(SaveCheckinDetails saveCheckinDetails, String ghId, String blockId, String reqId){
		String qry = adminQueries.getProperty("saveCheckInDetails");
		String chckReq = adminQueries.getProperty("insertChckReqId");
		String balanceSheet = adminQueries.getProperty("insertBalanceSheet");
		String gadUpdate = adminQueries.getProperty("updateGadStatus");
		String query = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		String getFromDate = adminQueries.getProperty("selectFromDateFromTrAllot");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Date arrivalDate = null;
		
		try {
			arrivalDate = !saveCheckinDetails.getInTime().equals("") ? sdf1.parse(saveCheckinDetails.getInTime())
					: null;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Integer trId = 0;
		Integer advncBill = 0;
		try {
			trId = get_seqID("tr_id");
			update_seqID("tr_id", trId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String formDate="";
		Date dateReqDate=null;
		try {
			formDate = jdbcTemplate.queryForObject(getFromDate, new Object[] { reqId }, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			 dateReqDate=!formDate.equals("") ? sdf3.parse(formDate) : null;
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		 Date updatedDate=null;
		 if (dateReqDate != null) {
	            // Create a Calendar instance and set it to the dateReqDate
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(dateReqDate);
	            
	            // Set the desired time to 8:00
	            calendar.set(Calendar.HOUR_OF_DAY, 9);
	            calendar.set(Calendar.MINUTE, 0);
	            calendar.set(Calendar.SECOND, 0);
	            calendar.set(Calendar.MILLISECOND, 0);
	            
	            // Add one day
	            calendar.add(Calendar.DAY_OF_MONTH, 1);
	            
	            // Get the updated date with the fixed time
	             updatedDate = calendar.getTime();
	            
	            // Convert the updatedDate back to the desired format
	            String updatedDateString = sdf1.format(updatedDate);
	            System.out.println("Updated date: " + updatedDateString);
	        }
		
		
		
		
		
		
		// insert data in tr_checkin
		if (saveCheckinDetails.getAdvncamount() == null || saveCheckinDetails.getAdvncamount().isEmpty()) {
			try {
				jdbcTemplate.update(qry,
						new Object[] { trId, saveCheckinDetails.getRollNo(), saveCheckinDetails.getGstId(), arrivalDate,
								null, "0", reqId, null, null, null, "CIN", saveCheckinDetails.getChildren(),saveCheckinDetails.getShiftId(),
								saveCheckinDetails.getUserId(), saveCheckinDetails.getAdults(), timestamp, null, ghId, "0",
								saveCheckinDetails.getCategory(), saveCheckinDetails.getPurpose(), blockId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Runtime Exception");
			}

		} else {
			try {
				advncBill = get_seqID("advance_bill");
				update_seqID("advance_bill", advncBill);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				jdbcTemplate.update(qry,
						new Object[] { trId, saveCheckinDetails.getRollNo(), saveCheckinDetails.getGstId(), arrivalDate,
								null, saveCheckinDetails.getAdvncamount(), reqId, null, null, null, "CIN",
								saveCheckinDetails.getChildren(),saveCheckinDetails.getShiftId(),
								saveCheckinDetails.getUserId(), saveCheckinDetails.getAdults(),
								timestamp, advncBill, ghId, "0", saveCheckinDetails.getCategory(),
								saveCheckinDetails.getPurpose(), blockId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Runtime Exception");
			}
		}
		// insert into tr_checkin_Req
		try {
			jdbcTemplate.update(chckReq, new Object[] { trId, reqId, "LIV", timestamp });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Runtime Exception");
		}
		// insert into gad_details

		Integer noOfGst = null;
		Integer noOfChckGst = null;
		try {
			noOfGst = jdbcTemplate.queryForObject(query, new Object[] { reqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			noOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { reqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (noOfGst == noOfChckGst) {
			try {
				jdbcTemplate.update(gadUpdate, new Object[] { "CIN", timestamp, reqId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Runtime Exception");
			}

		} else {
			try {
				jdbcTemplate.update(gadUpdate, new Object[] { "PCIN", timestamp, reqId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Runtime Exception");
			}
		}
		// update tr_allotment
		try {
			saveDateInTrAllotment(saveCheckinDetails, updatedDate, trId, blockId, reqId, "S");
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException("Runtime Exception");
		}
		// insert into balance sheet
		if (saveCheckinDetails.getAdvncamount() != null && !saveCheckinDetails.getAdvncamount().isEmpty()) {
			Integer balId = 0;
			try {
				balId = get_seqID("bal_id");
				update_seqID("bal_id", balId);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

			try {
				jdbcTemplate.update(balanceSheet, new Object[] { balId,saveCheckinDetails.getShiftId(), saveCheckinDetails.getAdvncamount(),
						timestamp, timestamp, "CNF", "CASH", "orgadmin", advncBill, ghId, blockId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Runtime Exception");
			}
				
		}
		return true;
	}

	@Transactional
	public boolean saveDateInTrAllotment(SaveCheckinDetails saveCheckinDetails, Date updatedDate, Integer trId,
			String blockId, String reqId, String type) {
		String updateAllot = adminQueries.getProperty("updateAllotMentStatus");
		String exceedTime = adminQueries.getProperty("forNocStatus");
		String exceedTimeStatusUpdate = adminQueries.getProperty("forUpdateNocStatus");
		String forUpdateCinStatus = adminQueries.getProperty("forUpdateCinStatus");
		String NotExceedTimeStatusUpdate = adminQueries.getProperty("forRestStatus");
		String trQry = adminQueries.getProperty("selectAlotId");
		String chckAllot = adminQueries.getProperty("insertChckAlot");
		String query = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		String deleteTrCheckInAllot = adminQueries.getProperty("deleteTrCheckInAllotOnEdit");
		String allotDateNeedToBeChange = adminQueries.getProperty("allotDateNeedToBeChange");
		String updateAllotMentStatusNotTime = adminQueries.getProperty("updateAllotMentStatusNotTime");
		String limitedStatusChange = adminQueries.getProperty("limitedStatusChange");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		Date fromActDate = null;
		String froDt = "";
		Integer noOfGst = null;
		Integer noOfChckGst = null;

		try {
			fromActDate = !saveCheckinDetails.getInTime().equals("") ? sdf1.parse(saveCheckinDetails.getInTime())
					: null;
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		try {
			noOfGst = jdbcTemplate.queryForObject(query, new Object[] { reqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		try {
			noOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { reqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (fromActDate.compareTo(updatedDate) == 0 || fromActDate.compareTo(updatedDate) < 0) {
			froDt = fromActDate != null ? sdf2.format(fromActDate) : null;
			froDt = froDt + " 9:00";
			try {
				fromActDate = !saveCheckinDetails.getInTime().equals("") ? sdf1.parse(froDt) : null;
			} catch (ParseException e1) {
				e1.printStackTrace();
				return false;
			}

			Integer checkInUpdateNeedOrNot = 0;
			try {
				checkInUpdateNeedOrNot = jdbcTemplate.queryForObject(allotDateNeedToBeChange, new Object[] { reqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			if (noOfGst == noOfChckGst) {
				try {
					if(checkInUpdateNeedOrNot==0)
					 jdbcTemplate.update(updateAllot, new Object[] { "CIN", fromActDate, reqId });
					else
					 jdbcTemplate.update(updateAllotMentStatusNotTime, new Object[] { "CIN", reqId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Runtime Exception");
				}

			} else {
				try {
					if(checkInUpdateNeedOrNot==0)
						 jdbcTemplate.update(updateAllot, new Object[] { "PCIN", fromActDate, reqId });
						else
						 jdbcTemplate.update(updateAllotMentStatusNotTime, new Object[] { "PCIN", reqId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Runtime Exception");
				}

			}
			List<String> trReqId = new ArrayList<>();
			trReqId = jdbcTemplate.query(trQry, new Object[] { blockId, reqId }, new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			if (type.equals("S")) {
				for (String i : trReqId) {
					try {
						jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Runtime Exception");
					}
				}
			} else {
				// at the time of edit tr_checkin_allot
				try {
					jdbcTemplate.update(deleteTrCheckInAllot, new Object[] { String.valueOf(trId) });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Runtime Exception");
				}
				for (String i : trReqId) {
					try {
						jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Runtime Exception");
					}
				}
			}
		} else {
			SimpleDateFormat sdft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			sdft.setLenient(false);
			Date fromActTime = null;

			try {
				fromActTime = !saveCheckinDetails.getInTime().equals("") ? sdft.parse(saveCheckinDetails.getInTime())
						: null;
			} catch (ParseException e2) {
				e2.printStackTrace();
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fromActTime);
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date compareTime = calendar.getTime();
			if (fromActTime.after(compareTime)) {
				String froDtToday = fromActDate != null ? sdf2.format(fromActDate) : null;
				froDtToday = froDtToday + " 9:00";
				Date froDtTodayDt = null;

				try {
					froDtTodayDt = !froDtToday.equals("") ? sdf1.parse(froDtToday) : null;
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				List<String> nocStatusTrId = new ArrayList<>();
				try {
					Object[] params = { froDtTodayDt, reqId };
					nocStatusTrId = jdbcTemplate.query(exceedTime, params, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString("allot_id");
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				for (String allotId : nocStatusTrId) {
					try {
						jdbcTemplate.update(exceedTimeStatusUpdate, new Object[] { "NOC", allotId });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Runtime Exception");
					}
				}
				List<String> cinStatusTrId = new ArrayList<>();
				try {
					Object[] params = { froDtTodayDt, reqId };
					cinStatusTrId = jdbcTemplate.query(NotExceedTimeStatusUpdate, params, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString("allot_id");
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				Integer checkInUpdateNeedOrNot = 0;
				try {
					checkInUpdateNeedOrNot = jdbcTemplate.queryForObject(allotDateNeedToBeChange, new Object[] { reqId }, Integer.class);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				for (String allotId : cinStatusTrId) {
					if (noOfGst == noOfChckGst) {
						try {
							if(checkInUpdateNeedOrNot==0)
								jdbcTemplate.update(forUpdateCinStatus, new Object[] { "CIN", froDtTodayDt, allotId });
							else
								jdbcTemplate.update(limitedStatusChange, new Object[] { "CIN", reqId });
							
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					} else {
						try {
							if(checkInUpdateNeedOrNot==0)
								 	jdbcTemplate.update(forUpdateCinStatus, new Object[] { "PCIN", froDtTodayDt, allotId });
								else
									jdbcTemplate.update(limitedStatusChange, new Object[] { "PCIN", reqId });
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					}
				}
				if (type.equals("S")) {
					for (String i : cinStatusTrId) {
						try {
							jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					}
				} else {

					jdbcTemplate.update(deleteTrCheckInAllot, new Object[] { String.valueOf(trId) });

					for (String i : cinStatusTrId) {
						try {
							jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					}
				}
			} else {
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(fromActDate);
				calendar1.add(Calendar.DAY_OF_MONTH, -1);
				Date previousDay = calendar1.getTime();
				String froDtC = previousDay != null ? sdf2.format(previousDay) : null;
				froDtC = froDtC + " 9:00";
				Date froDate = null;
				try {
					froDate = !froDtC.equals("") ? sdf1.parse(froDtC) : null;
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				List<String> nocStatusTrId = new ArrayList<>();
				try {
					Object[] params = { froDate, reqId };
					nocStatusTrId = jdbcTemplate.query(exceedTime, params, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString("allot_id");
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				for (String allotId : nocStatusTrId) {
					try {
						jdbcTemplate.update(exceedTimeStatusUpdate, new Object[] { "NOC", allotId });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Runtime Exception");
					}
				}
				List<String> cinStatusTrId = new ArrayList<>();
				try {
					Object[] params = { froDate, reqId };
					cinStatusTrId = jdbcTemplate.query(NotExceedTimeStatusUpdate, params, new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString("allot_id");
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
				Integer checkInUpdateNeedOrNot = 0;
				try {
					checkInUpdateNeedOrNot = jdbcTemplate.queryForObject(allotDateNeedToBeChange, new Object[] { reqId }, Integer.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (String allotId : cinStatusTrId) {
					if (noOfGst == noOfChckGst) {
						try {
							if(checkInUpdateNeedOrNot==0)
								jdbcTemplate.update(forUpdateCinStatus, new Object[] { "CIN", froDate, allotId });
							else
								jdbcTemplate.update(limitedStatusChange, new Object[] { "CIN", reqId });
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					} else {
						try {
							if(checkInUpdateNeedOrNot==0)
							 	jdbcTemplate.update(forUpdateCinStatus, new Object[] { "PCIN", froDate, allotId });
							else
								jdbcTemplate.update(limitedStatusChange, new Object[] { "PCIN", reqId });
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					}
				}
				if (type.equals("S")) {
					for (String i : cinStatusTrId) {
						try {
							jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					}
				} else {

					jdbcTemplate.update(deleteTrCheckInAllot, new Object[] { String.valueOf(trId) });

					for (String i : cinStatusTrId) {
						try {
							jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("Runtime Exception");
						}
					}
				}
			}
		}
		return true;
	}
	@Override
	public Pagination getCheckOutDetails(Map<String, Object> params, HttpSession session) {
	String checkQry = adminQueries.getProperty("gstCheckOutDetails");
	String roomQry = adminQueries.getProperty("checkOutGstRoom");
	String gstQry = adminQueries.getProperty("checkOutGuest");
	String gstSts = adminQueries.getProperty("checkOutStatus");
	String partialCancel = approverQueries.getProperty("getPartialCancelByReqId");
	String query2 = adminQueries.getProperty("countCheckOut");
	String orderByCondition = " ORDER BY c.from_date DESC";
	String whereCondition = " WHERE 1=1 AND c.req_id IN (SELECT req_id FROM tr_checkin WHERE status !='BILL')";

	String cityId = (String) params.get("cId");
	String ghId = (String) params.get("gId");
	String bookingId = (String) params.get("bookingId");
	String fromDate = (String) params.get("fromDate");
	String name = (String) params.get("name");
	String category = (String) params.get("category");
	int pageNo = (int) params.get("pageNo");
	Integer pageCount = null;

	List<Object> queryParams = new ArrayList<>();
	List<Object> queryParams1 = new ArrayList<>();
	queryParams.add(cityId);
	queryParams.add(ghId);
	queryParams.add(cityId);
	queryParams.add(ghId);

	queryParams1.add(cityId);
	queryParams1.add(ghId);
	queryParams1.add(cityId);
	queryParams1.add(ghId);
	
	// Add bookingId condition
	if (bookingId != null && !bookingId.isEmpty()) {
	    whereCondition += " AND c.req_id = ?";
	    queryParams.add(bookingId);
	    queryParams1.add(bookingId);
	}

	// Add category condition
	if (category != null && !category.isEmpty()) {
	    whereCondition += " AND c.category_type = ?";
	    queryParams.add(category);
	    queryParams1.add(category);
	}

	// Add fromDate condition
//	if (fromDate != null && !fromDate.isEmpty()) {
//	    try {
//	        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
//	        java.sql.fromDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
//	        whereCondition += " AND c.from_date::date = ?";
//	        queryParams.add(fromDate);
//	        queryParams1.add(fromDate);
//	    } catch (ParseException e) {
//	        throw new RuntimeException(e);
//	    }
//	}
	if (fromDate != null && !fromDate.isEmpty()) {
	    try {
	        Date parsedDate = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
	        java.sql.Date sqlFromDate = new java.sql.Date(parsedDate.getTime());
	        whereCondition += " AND c.from_date::date = ?";
	        queryParams.add(sqlFromDate);
	        queryParams1.add(sqlFromDate);
	    } catch (ParseException e) {
	        throw new RuntimeException("Invalid date format: " + fromDate, e);
	    }
	}

	// Add name condition
	if (name != null && !name.isEmpty()) {
	    whereCondition += " AND c.name ILIKE ?";
	    queryParams.add("%" + name + "%");
	    queryParams1.add("%" + name + "%");
	}

	checkQry = checkQry + whereCondition + orderByCondition;
	query2 = query2 + whereCondition;

	if (pageNo != 0) {
	    int offset = (pageNo - 1) * Constants.LIMIT;
	    checkQry = checkQry + " LIMIT ? OFFSET ?";
	    queryParams.add(Constants.LIMIT);
	    queryParams.add(offset);
	}

	try {
	    pageCount = jdbcTemplate.queryForObject(query2, queryParams1.toArray(), Integer.class);
	} catch (Exception e) {
	    pageCount = 0;
	    e.printStackTrace();
	}

	final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	List<CheckIn> checkIn = new ArrayList<>();
	queryParams.stream().forEach(System.out::println);
	try {
	    checkIn = jdbcTemplate.query(checkQry, queryParams.toArray(), new RowMapper<CheckIn>() {
	        @Override
	        public CheckIn mapRow(ResultSet rs, int rownumber) throws SQLException {
	            CheckIn rest = new CheckIn();
	            rest.setReq_id(rs.getString("req_id"));
	            rest.setFrom_date(sdf.format(rs.getTimestamp("from_date")));
	            rest.setTo_date(sdf.format(rs.getTimestamp("to_date")));
	            rest.setStatus(rs.getString("status"));
	            rest.setExt(rs.getString("ext"));
	            String category_Desc = "";
	            String category = rs.getString("category_type");
	            switch (category) {
	                case "C1":
	                    category_Desc = "Category 1";
	                    break;
	                case "C2":
	                    category_Desc = "Category 2";
	                    break;
	                case "C3":
	                    category_Desc = "Category 3";
	                    break;
	                case "C4":
	                    category_Desc = "Category 4";
	                    break;
	            }
	            rest.setCategoryType(category_Desc);

	            List<RoomDetails> roomDetails = jdbcTemplate.query(roomQry, new Object[]{rest.getReq_id()}, new RowMapper<RoomDetails>() {
	                @Override
	                public RoomDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
	                    RoomDetails resr = new RoomDetails();
	                    resr.setBedNo(rs.getString("bed_no"));
	                    resr.setRoomBed(rs.getString("roombed"));
	                    resr.setRoomNo(rs.getString("room_no"));
	                    resr.setType(rs.getString("type"));
	                    return resr;
	                }
	            });

	            rest.setRoomDetails(roomDetails);

	            List<GuestDetails> gustDetails = jdbcTemplate.query(gstQry, new Object[]{rest.getReq_id()}, new RowMapper<GuestDetails>() {
	                @Override
	                public GuestDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
	                    GuestDetails res = new GuestDetails();
	                    res.setName(rs.getString("name"));
	                    res.setDepartment(rs.getString("department"));
	                    res.setDesignation(rs.getString("designation"));
	                    res.setAddress(rs.getString("address"));
	                    res.setGuestId(rs.getString("guest_id"));
	                    try {
	                        String status = jdbcTemplate.queryForObject(gstSts,
	                                new Object[]{rest.getReq_id(), res.getGuestId()}, String.class);
	                        res.setStatus(status);
	                    } catch (Exception e) {
	                        res.setStatus("null");
	                    }
	                    return res;
	                }
	            });
	            rest.setGuestDetails(gustDetails);

	            try {
	                BookingResponseModel brm = jdbcTemplate.queryForObject(partialCancel,
	                        new Object[]{rest.getReq_id()}, (rs1, rownumber1) -> {
	                            BookingResponseModel brm1 = new BookingResponseModel();
	                            brm1.setFromDate(sdf.format(rs1.getDate("from_date")));
	                            brm1.setToDate(sdf.format(rs1.getDate("to_date")));
	                            brm1.setStatus(rs1.getString("status"));
	                            return brm1;
	                        });
	                if (!brm.getStatus().equalsIgnoreCase("PRO")) {
	                    rest.setCanStatus("PENDING");
	                } else {
	                    rest.setCanStatus("PRO");
	                    rest.setTo_date(brm.getFromDate());
	                }
	            } catch (Exception e) {
	                rest.setCanStatus("NA");
	            }

	            return rest;
	        }
	    });

	    System.out.println("CheckOut Query ====" + checkQry);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	Pagination pagination = new Pagination();
	pagination.setData(checkIn);
	pagination.setTotalPages(pageCount);
	return pagination;
}

	@Override
	public List<OtherguestCheckinDetails> getCheckOutOtherGuestData(Map<String, Object> params) {
		String ghId = (String) params.get("ghId");
		String blockId = (String) params.get("blockId");
		String reqId = (String) params.get("reqId");
		String hidval = (String) params.get("hidval");
		String checkOutgstQry = adminQueries.getProperty("otherGuestCheckOutDetails");
		String roomQry = adminQueries.getProperty("guestCheckOutRoomDetails");
		String otherGstFlqry = checkOutgstQry + "and g.guest_id not in ('" + hidval + "')";
		System.out.println(otherGstFlqry);
		System.out.println(ghId);
		System.out.println(reqId);
		List<OtherguestCheckinDetails> otherGuest1 = new ArrayList<>();
		try {
			otherGuest1 = jdbcTemplate.query(otherGstFlqry, new RowMapper<OtherguestCheckinDetails>() {

				@Override
				public OtherguestCheckinDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					OtherguestCheckinDetails otherGuest = new OtherguestCheckinDetails();
					otherGuest.setReqId(rs.getString("req_id"));
					otherGuest.setGuestId(rs.getString("guest_id"));
					otherGuest.setName(rs.getString("name"));
					otherGuest.setAge(rs.getString("age"));
					otherGuest.setGender(rs.getString("gender"));
					otherGuest.setDesgination(rs.getString("designation"));
					otherGuest.setDepartment(rs.getString("department"));
					otherGuest.setAddress(rs.getString("address"));
					otherGuest.setGuest_mobile(rs.getString("guest_mobile"));
					otherGuest.setStatus(rs.getString("status"));
					otherGuest.setTrId(rs.getString("tr_id"));
					otherGuest.setArrivalTimeDate(rs.getString("arrival_time_date"));
					otherGuest.setRollNo(rs.getString("roll_no"));
					otherGuest.setAdvanceAmount(rs.getString("advance_amt"));
					otherGuest.setNoOfChildrenBelow12(rs.getString("no_of_children_below12"));
					otherGuest.setAdults(rs.getString("adults"));
					try {
						RoomDetails roomDetails = jdbcTemplate.queryForObject(roomQry,
								new Object[] { otherGuest.getTrId() }, (ResultSet rsRm, int rownumberRm) -> {
									RoomDetails roomDetails1 = new RoomDetails();
									roomDetails1.setRoomNo(rsRm.getString("room_no"));
									roomDetails1.setBedNo(rsRm.getString("bed_no"));
									roomDetails1.setType(rsRm.getString("type"));
									roomDetails1.setBlockName(rsRm.getString("block_name_short"));
									return roomDetails1;
								});
						otherGuest.setRoomDetails(roomDetails);
					} catch (Exception e) {
						otherGuest.setRoomDetails(null);
					}
					System.out.println("otherGuest" + otherGuest);
					return otherGuest;
				}
			}, ghId, reqId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return otherGuest1;
	}

	@Override
	public List<GuestDetails> getCheckOutGuest(Map<String, Object> params) {
		String ghId = (String) params.get("ghId");
		String blockId = (String) params.get("blockId");
		String reqId = (String) params.get("reqId");
		String hidval = (String) params.get("hidval");
		String checkOutgstQry = adminQueries.getProperty("selectCheckOutGuest");
		String roomQry = adminQueries.getProperty("guestCheckOutRoomDetails");
		String otherGstFlqry = checkOutgstQry + "and g.guest_id in ('" + hidval + "')";
		System.out.println(otherGstFlqry);
		System.out.println(ghId);
		System.out.println(reqId);
		List<GuestDetails> checkOutGst = new ArrayList<>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		try {
			checkOutGst = jdbcTemplate.query(otherGstFlqry, new RowMapper<GuestDetails>() {

				@Override
				public GuestDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					GuestDetails otherGuest = new GuestDetails();
					otherGuest.setReqId(rs.getString("req_id"));
					otherGuest.setGuestId(rs.getString("guest_id"));
					otherGuest.setName(rs.getString("name"));
					otherGuest.setAge(rs.getString("age"));
					otherGuest.setGender(rs.getString("gender"));
					otherGuest.setDesignation(rs.getString("designation"));
					otherGuest.setDepartment(rs.getString("department"));
					otherGuest.setAddress(rs.getString("address"));
					otherGuest.setStatus(rs.getString("status"));
					otherGuest.setTrId(rs.getString("tr_id"));
					otherGuest.setParentTrId(rs.getString("parent_trid"));
					try {
						String arrTime = !rs.getTimestamp("arrival_time_date").equals("") ? sdf1.format(rs.getTimestamp("arrival_time_date")) : null;
						otherGuest.setArrivalTime(arrTime);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					otherGuest.setRollNo(rs.getString("roll_no"));
					otherGuest.setAdvncAmount(rs.getString("advance_amt"));
					try {
						RoomDetails roomDetails = jdbcTemplate.queryForObject(roomQry,
								new Object[] { otherGuest.getTrId() }, (ResultSet rsRm, int rownumberRm) -> {
									RoomDetails roomDetails1 = new RoomDetails();
									roomDetails1.setRoomNo(rsRm.getString("room_no"));
									roomDetails1.setBedNo(rsRm.getString("bed_no"));
									roomDetails1.setType(rsRm.getString("type"));
									roomDetails1.setBlockName(rsRm.getString("block_name_short"));
									return roomDetails1;
								});
						otherGuest.setRoomDetails(roomDetails);
					} catch (Exception e) {
						e.printStackTrace();
						otherGuest.setRoomDetails(null);
					}
					System.out.println("otherGuest" + otherGuest);
					return otherGuest;
				}
			}, ghId, reqId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkOutGst;
	}

	@Override
	public boolean updateDetailsOut(SaveCheckinDetails saveCheckOut, String blockId, String ghId) {
		String checkinGst = adminQueries.getProperty("updateCheckOutGuest");
		String query = adminQueries.getProperty("selectGuestOnReqId");
		String query1 = adminQueries.getProperty("selectGuestDetailsInCheckOut");
		String gadUpdate = adminQueries.getProperty("updateGadStatus");
		String trQry = adminQueries.getProperty("selectAlotId");
		String updateAllot = adminQueries.getProperty("updateAllotMentStatusCout");
		String parentCan = adminQueries.getProperty("updateTrCheckOutExitGstCan");
		String parentCanInCheckAlo = adminQueries.getProperty("updateTrCheckOutExitGstCanInChekcAlo");
		String reqToDate=adminQueries.getProperty("getToDateForCheckout");
		String checkAlo=adminQueries.getProperty("updateTrCheckInCoutStatus");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		Date toDate = null;
		String toDt = "";

		try {
			toDate = !saveCheckOut.getOutTime().equals("") ? sdf1.parse(saveCheckOut.getOutTime()) : null;
		} catch (ParseException e1) {
			
			e1.printStackTrace();
			return false;
		}
		
		toDt = toDate != null ? sdf2.format(toDate) : null;
		toDt = toDt + " 8:00";
		System.out.println("iam in in allotment" + (toDt));
		try {
			toDate = !saveCheckOut.getOutTime().equals("") ? sdf1.parse(toDt) : null;
		} catch (ParseException e1) {
		
			e1.printStackTrace();
			return false;
		}
		Date departureDate = null;
		try {
			departureDate = !saveCheckOut.getOutTime().equals("") ? sdf1.parse(saveCheckOut.getOutTime()) : null;
		} catch (ParseException e1) {
			
			e1.printStackTrace();
			return false;
		}
		// update tr_checkin_table
		try {
			jdbcTemplate.update(checkinGst, new Object[] { departureDate, saveCheckOut.getTrId(), ghId });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		if (!saveCheckOut.getPrentTrId().equals("0")) {
			try {
				jdbcTemplate.update(parentCan, new Object[] { saveCheckOut.getPrentTrId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			try {
				jdbcTemplate.update(parentCanInCheckAlo, new Object[] { saveCheckOut.getPrentTrId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		
		//update gad_request
		try {
			jdbcTemplate.update(gadUpdate, new Object[] { "COUT", timestamp, saveCheckOut.getReqId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}

		// update tr_allotment
		String reqToDateVar="";
		
		 Date modifiedDate=null;
		try {
			reqToDateVar = jdbcTemplate.queryForObject(reqToDate, new Object[] { saveCheckOut.getReqId() }, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Date StartCheckOutDate=null;
		try {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    StartCheckOutDate = sdf.parse(reqToDateVar);

		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(StartCheckOutDate);
		    calendar.add(Calendar.DAY_OF_MONTH, -1);
		    calendar.set(Calendar.HOUR_OF_DAY, 8);
		    calendar.set(Calendar.MINUTE, 0);
		    calendar.set(Calendar.SECOND, 0);
		    calendar.set(Calendar.MILLISECOND, 0);
		     modifiedDate = calendar.getTime();
		} catch (ParseException e) {
		    e.printStackTrace();
		    return false;
		}
		if(departureDate.compareTo(modifiedDate)>0) {
			List<String> trReqId = new ArrayList<>();
			try {
				trReqId = jdbcTemplate.query(trQry, new Object[] { blockId, saveCheckOut.getReqId() },
						new RowMapper<String>() {
							public String mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getString(1);
							}
						});
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(modifiedDate);
		    calendar.add(Calendar.DAY_OF_MONTH, 1);
		    modifiedDate = calendar.getTime();
			for (String i : trReqId) {
				try {
					jdbcTemplate.update(updateAllot, new Object[] { modifiedDate, timestamp, i });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			for (String i : trReqId) {
				try {
					jdbcTemplate.update(checkAlo, new Object[] { "COUT",i });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
		}else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(departureDate);
			calendar.set(Calendar.HOUR_OF_DAY, 8);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Date compare = calendar.getTime();

			if(departureDate.after(compare)) {
				
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(toDate);
				calendar1.add(Calendar.DAY_OF_MONTH, 1);
				calendar1.set(Calendar.HOUR_OF_DAY, 8);
				calendar1.set(Calendar.MINUTE, 0);
				calendar1.set(Calendar.SECOND, 0);
				calendar1.set(Calendar.MILLISECOND, 0);
				toDate = calendar1.getTime();				
				try {
					instantCancel(toDate,saveCheckOut.getReqId(),StartCheckOutDate);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
				
				
			}else {
				Calendar calendar1 = Calendar.getInstance();
				calendar1.setTime(toDate);
				calendar1.set(Calendar.HOUR_OF_DAY, 8);
				calendar1.set(Calendar.MINUTE, 0);
				calendar1.set(Calendar.SECOND, 0);
				calendar1.set(Calendar.MILLISECOND, 0);
				toDate = calendar1.getTime();
				
				try {
					instantCancel(toDate,saveCheckOut.getReqId(),StartCheckOutDate);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
				
				
			}
		}
		
		return true;
	}

	@Transactional
	public boolean instantCancel(Date toDate,String ReqId,Date lastToDate) {
		String spentDate=adminQueries.getProperty("spentDate");
		String leftOverDate=adminQueries.getProperty("leftOverDate");
		String spentDateSts=adminQueries.getProperty("spentDateStatus");
		String leftOverDateSts=adminQueries.getProperty("leftOverStatus");
		String alreadyApllyCanOrNot=adminQueries.getProperty("checkPartialCancelUpdateOrNot");
		String updateGadStatus=adminQueries.getProperty("updateGadUpdateCanStatus");
		String partialCanRequest=adminQueries.getProperty("insertInPartialContentRequest");
		String checkAlo=adminQueries.getProperty("updateTrCheckInCoutStatus");
		Integer isAppliedOrNot = null;
		try {
			isAppliedOrNot = jdbcTemplate.queryForObject(alreadyApllyCanOrNot, new Object[] { ReqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if(!isAppliedOrNot.equals(1)) {
			//update tr_allotment can_status pcan
			List<String> coutStatusAllotId=new ArrayList<>();
			try {
				Object[] params = { toDate, ReqId};
				coutStatusAllotId = jdbcTemplate.query(leftOverDate, params, new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("allot_id");
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for (String allotId : coutStatusAllotId) {
				try {
					jdbcTemplate.update(leftOverDateSts, new Object[] { allotId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			// update tr_checkin_allot
			for (String i : coutStatusAllotId) {
				try {
					jdbcTemplate.update(checkAlo, new Object[] { "CAN",i });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
		//update gad_request can_status pcan
			try {
				jdbcTemplate.update(updateGadStatus, new Object[] { ReqId });
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		//insert data in partial cancel
			Integer pcrId=null;
			try {
				pcrId = get_seqID("pcr_id");
				update_seqID("pcr_id", pcrId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//
				Date parFromDate=null;
			    Calendar calendar = Calendar.getInstance();
		        calendar.setTime(toDate);
		        calendar.set(Calendar.HOUR_OF_DAY, 9);
		        calendar.set(Calendar.MINUTE, 0);
		        calendar.set(Calendar.SECOND, 0);
		        calendar.set(Calendar.MILLISECOND, 0);
		        parFromDate= calendar.getTime();
		        Calendar calendar1 = Calendar.getInstance();
		        calendar1.setTime(lastToDate);
		        calendar1.set(Calendar.HOUR_OF_DAY, 8);
		        calendar1.set(Calendar.MINUTE, 0);
		        calendar1.set(Calendar.SECOND, 0);
		        calendar1.set(Calendar.MILLISECOND, 0);
		        Date lastday=calendar1.getTime();		        
		      
			
		
			try {
				jdbcTemplate.update(partialCanRequest,
						new Object[] {pcrId,parFromDate,lastday,ReqId,"ALO"});
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		List<String> coutnStatusAllotId=new ArrayList<>();
		try {
			Object[] params = { toDate, ReqId};
			coutnStatusAllotId = jdbcTemplate.query(spentDate, params, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("allot_id");
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		for (String allotId : coutnStatusAllotId) {
			try {
				jdbcTemplate.update(spentDateSts, new Object[] { toDate,allotId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		// update tr_checkin_allot
		for (String i : coutnStatusAllotId) {
			try {
				jdbcTemplate.update(checkAlo, new Object[] { "COUT",i });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		return true;
	}
	
	
	@Override
	public List<GuestDetails> viewCheckInCancelDetails(Map<String, Object> params, HttpSession session) {
		String ghId = (String) params.get("ghId");
		String hidval = (String) params.get("hidval");
		String reqId = (String) params.get("reqId");
		String checkQry = adminQueries.getProperty("selectCancelCheckInDetails");
		String roomQry = adminQueries.getProperty("selectCancelCheckInRoomDetails");
		String totalAmount = adminQueries.getProperty("totalAmount");
		String otherGstFlqry = checkQry + " and g.guest_id in ('" + hidval + "')";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		System.out.println(reqId);
		System.out.println(otherGstFlqry);
		List<GuestDetails> checkOutGst = new ArrayList<>();
		try {
			checkOutGst = jdbcTemplate.query(otherGstFlqry, new RowMapper<GuestDetails>() {

				@Override
				public GuestDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					GuestDetails otherGuest = new GuestDetails();
					otherGuest.setReqId(rs.getString("req_id"));
					otherGuest.setGuestId(rs.getString("guest_id"));
					otherGuest.setName(rs.getString("name"));
					otherGuest.setAge(rs.getString("age"));
					otherGuest.setGender(rs.getString("gender"));
					otherGuest.setDesignation(rs.getString("designation"));
					otherGuest.setDepartment(rs.getString("department"));
					otherGuest.setAddress(rs.getString("address"));
					otherGuest.setStatus(rs.getString("status"));
					otherGuest.setTrId(rs.getString("tr_id"));
					otherGuest.setExtraGuest(rs.getString("extra_guest"));
					try {
						String arrTime = !rs.getTimestamp("arrival_time_date").equals("") ? sdf1.format(rs.getTimestamp("arrival_time_date")) : null;
						otherGuest.setArrivalTime(arrTime);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					otherGuest.setRollNo(rs.getString("roll_no"));
					otherGuest.setAdvncAmount(rs.getString("advance_amt"));
					otherGuest.setAdults(rs.getString("adults"));
					otherGuest.setParentTrId(rs.getString("parent_trid"));
					otherGuest.setDesgCategory(rs.getString("desg_category"));
					otherGuest.setPurposes(rs.getString("item_title"));
					otherGuest.setExt(rs.getString("ext"));
					otherGuest.setChildrenBelow(rs.getString("no_of_children_below12"));
					try {
						RoomDetails roomDetails = jdbcTemplate.queryForObject(roomQry,
								new Object[] { otherGuest.getTrId() }, (ResultSet rsRm, int rownumberRm) -> {
									RoomDetails roomDetails1 = new RoomDetails();
									roomDetails1.setRoomNo(rsRm.getString("room_no"));
									roomDetails1.setBedNo(rsRm.getString("bed_no"));
									roomDetails1.setType(rsRm.getString("type"));
									roomDetails1.setBlockName(rsRm.getString("block_name_short"));
									return roomDetails1;
								});
						otherGuest.setRoomDetails(roomDetails);
					} catch (Exception e) {
						e.printStackTrace();
						otherGuest.setRoomDetails(null);
					}
					System.out.println("otherGuest" + otherGuest);
					return otherGuest;
				}
			}, ghId, reqId, reqId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer totalAmount1 = jdbcTemplate.queryForObject(totalAmount, new Object[] { reqId},Integer.class);
		checkOutGst.get(0).setTotalAmount(totalAmount1);
		System.out.println(totalAmount1);
		System.out.println(checkOutGst);
		return checkOutGst;
	}

	@Override
	public boolean updateCancelDetails(GuestDetails gstDetails, String ghId, String blockId) {

		String query = adminQueries.getProperty("updateTrcheckinStatus");
		String updateExistingTrId = adminQueries.getProperty("existingTrCheckinIdStatusUpdate");
		String SelectTrCheckinquery = adminQueries.getProperty("selectTrCheckinDetail");
		String updateBalancSheet = adminQueries.getProperty("updateBalanceSheet");
		String updateTrCheckinAlot = adminQueries.getProperty("updateTrCheckinAlot");
		String query2 = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		String gadUpdate = adminQueries.getProperty("updateGadStatus");
		String reqUpdate = adminQueries.getProperty("updateTrCheckinReq");
		String trQry = adminQueries.getProperty("selectAlotIdForCancel");
		String updateAllot = adminQueries.getProperty("cancelAllotMentStatusPro");
		String existinUpdateAllot = adminQueries.getProperty("existingTrCheckinAllotIdStatusUpdate");
		String parentRqId = adminQueries.getProperty("selectParentReqId");
		String gadStatusPext = adminQueries.getProperty("updateParentTrIdPext");
		String updateExtTrAllot = adminQueries.getProperty("updateExistingTrAllotMent");
		String updateTrAllot = adminQueries.getProperty("updateTrAllotMentByReqIdAndStatus");
		String previousParentTrPresentOrNot = adminQueries.getProperty("selectPreviousExtendsTrId");
		String previousParentTrStatusUpdate = adminQueries.getProperty("updatePreviousExtendsTrIdReturn");
		String gadExtraGstUpdate=adminQueries.getProperty("setNoOfExtraGuest");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String parentReqId = "";
		
		
		if (!(gstDetails.getParentTrId().equals("0")) && !(gstDetails.getExt().equals("0"))) {

			// update tr_checkin table reqId data
			try {
				jdbcTemplate.update(updateExistingTrId, new Object[] { gstDetails.getParentTrId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}

			// update tr_checkin table extend reqId data
			try {
				jdbcTemplate.update(query, new Object[] { gstDetails.getReqId(), ghId, gstDetails.getGuestId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}

			String previousTridPcan = "";
			try {
				previousTridPcan = jdbcTemplate.queryForObject(previousParentTrPresentOrNot, String.class,
						new Object[] { gstDetails.getParentTrId() });
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if (!previousTridPcan.equals("0")) {
				try {
					jdbcTemplate.update(previousParentTrStatusUpdate, new Object[] { previousTridPcan });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			// update tr_checkin_allotment
			try {
				jdbcTemplate.update(updateTrCheckinAlot, new Object[] { gstDetails.getTrId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}

			// update tr_checkin_allotment existing allotment
			try {
				jdbcTemplate.update(existinUpdateAllot, new Object[] { gstDetails.getParentTrId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			// update into gad_details

			Integer noOfGst = null;
			Integer noOfChckGst = null;
			try {
				noOfGst = jdbcTemplate.queryForObject(query2, new Object[] { gstDetails.getReqId() }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				noOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { gstDetails.getReqId() },
						Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (noOfGst == noOfChckGst) {
				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "CIN", timestamp, gstDetails.getReqId() });

				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			} else {
				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "PCIN", timestamp, gstDetails.getReqId() });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			if (noOfGst == 0) {
				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "PRO", timestamp, gstDetails.getReqId() });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			
			// gad request parent status pext

			parentReqId = (String) jdbcTemplate.queryForObject(parentRqId, new Object[] { gstDetails.getParentTrId() },
					String.class);
			try {
				jdbcTemplate.update(gadStatusPext, new Object[] { parentReqId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			// update tr_allotment
			Integer PrenoOfGst = null;
			Integer PrenoOfChckGst = null;
			try {
				PrenoOfGst = jdbcTemplate.queryForObject(query2, new Object[] { parentReqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}

			try {
				PrenoOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { parentReqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}

			if (PrenoOfGst == PrenoOfChckGst) {
				System.out.println(PrenoOfGst);
				System.out.println(PrenoOfChckGst);
				try {
					jdbcTemplate.update(updateExtTrAllot, new Object[] { "CIN", parentReqId });

				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			} else {
				System.out.println(PrenoOfGst);
				System.out.println(PrenoOfChckGst);
				try {
					jdbcTemplate.update(updateExtTrAllot, new Object[] { "PCIN", parentReqId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			try {
				jdbcTemplate.update(updateTrAllot, new Object[] { "PRO", gstDetails.getReqId(), "CIN" });
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			// update tr_checkin table data
			try {
				jdbcTemplate.update(query, new Object[] { gstDetails.getReqId(), ghId, gstDetails.getGuestId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			// select tr_checkin table data
			GuestDetails guestDetailAdvnc = new GuestDetails();
			try {
				guestDetailAdvnc = jdbcTemplate.queryForObject(SelectTrCheckinquery,
						new Object[] { gstDetails.getReqId(), gstDetails.getGuestId(), gstDetails.getTrId() },
						(rs, rownumber) -> {
							GuestDetails guestDetails1 = new GuestDetails();
							guestDetails1.setAdvncAmount(rs.getString("advance_amt"));
							guestDetails1.setAdvncBillId(rs.getString("adv_bill_id"));
							return guestDetails1;
						});
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			// update balance sheet
			if (guestDetailAdvnc.getAdvncAmount() != null && !guestDetailAdvnc.getAdvncAmount().isEmpty()) {
				try {
					jdbcTemplate.update(updateBalancSheet, new Object[] { guestDetailAdvnc.getAdvncBillId(), ghId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			// update tr_checkin_allotment
			try {
				jdbcTemplate.update(updateTrCheckinAlot, new Object[] { gstDetails.getTrId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			// update into gad_details

			Integer noOfGst = null;
			Integer noOfChckGst = null;
			try {
				noOfGst = jdbcTemplate.queryForObject(query2, new Object[] { gstDetails.getReqId() }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}

			try {
				noOfChckGst = jdbcTemplate.queryForObject(query1, new Object[] { gstDetails.getReqId() },
						Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			if (noOfGst == noOfChckGst) {
				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "CIN", timestamp, gstDetails.getReqId() });

				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			} else {
				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "PCIN", timestamp, gstDetails.getReqId() });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			if (noOfGst == 0) {

				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "PRO", timestamp, gstDetails.getReqId() });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}
			// update tr_checkin_reqid
			try {
				jdbcTemplate.update(reqUpdate, new Object[] { gstDetails.getTrId(), gstDetails.getReqId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			// update tr_allotment
			List<String> trReqId = new ArrayList<>();
			try {
				trReqId = jdbcTemplate.query(trQry, new Object[] { blockId, gstDetails.getReqId() },
						new RowMapper<String>() {
							public String mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getString(1);
							}
						});
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}

			if (noOfGst != noOfChckGst) {
				for (String i : trReqId) {
					try {
						jdbcTemplate.update(updateAllot, new Object[] { "PCIN", timestamp, i });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Not Working");
					}
				}
			}
			if (noOfGst == 0) {

				for (String i : trReqId) {
					try {
						jdbcTemplate.update(updateAllot, new Object[] { "PRO", timestamp, i });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Not Working");
					}
				}
			}

		}
		try {
			jdbcTemplate.update(gadExtraGstUpdate, new Object[] { null, gstDetails.getReqId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		return true;
	}

	@Override
	public boolean updateCheckinDetails(GuestDetails gstDetails, String ghId, String blockId) throws Exception {
		String query = adminQueries.getProperty("updateTrCheckDetails");
		String query1 = adminQueries.getProperty("selectAdvBilId");
		String balanceSheet = adminQueries.getProperty("insertBalanceSheet");
		String updateAmount = adminQueries.getProperty("updateAmountBalanceSheet");
		String getFromDate = adminQueries.getProperty("selectFromDateFromTrAllot");
		String getCheckInDate = adminQueries.getProperty("selectArrivalTimeInTrCheckIn");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
		Date arrivalDate = null;
		try {
			arrivalDate = !gstDetails.getArrivalTime().equals("") ? sdf1.parse(gstDetails.getArrivalTime()) : null;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String formDate = "";
		Date dateReqDate = null;
		try {
			formDate = jdbcTemplate.queryForObject(getFromDate, new Object[] { gstDetails.getReqId() }, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			dateReqDate = !formDate.equals("") ? sdf3.parse(formDate) : null;
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		SaveCheckinDetails saveCheckinDetails = new SaveCheckinDetails();
		saveCheckinDetails.setInTime(gstDetails.getArrivalTime());
		Date updatedDate = null;
		if (dateReqDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateReqDate);
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			updatedDate = calendar.getTime();
			String updatedDateString = sdf1.format(updatedDate);
			System.out.println("Updated date: " + updatedDateString);
		}

		if (!(gstDetails.getAdvncAmount().equals("0"))) {
			String adv_bill_id = null;
			Integer advncBill = null;

			adv_bill_id = jdbcTemplate.queryForObject(query1,
					new Object[] { gstDetails.getTrId(), gstDetails.getReqId() }, String.class);

			System.out.println(adv_bill_id);
			if (adv_bill_id == null) {
				try {
					advncBill = get_seqID("advance_bill");
					update_seqID("advance_bill", advncBill);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
				try {
					jdbcTemplate.update(query,
							new Object[] { gstDetails.getRollNo(), gstDetails.getAdvncAmount(), arrivalDate, advncBill,
									gstDetails.getTrId(), gstDetails.getGuestId(), gstDetails.getReqId() });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
				Integer balId = 0;
				try {
					balId = get_seqID("bal_id");
					update_seqID("bal_id", balId);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
				try {
					jdbcTemplate.update(balanceSheet, new Object[] { balId, "3309", gstDetails.getAdvncAmount(),
							timestamp, timestamp, "CNF", "CASH", "orgadmin", advncBill, blockId, ghId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
				// update tr_allotment
				boolean status = saveDateInTrAllotment(saveCheckinDetails, updatedDate,
						Integer.valueOf(gstDetails.getTrId()), blockId, gstDetails.getReqId(), "E");
				if (!status) {
					return false;
				}
			} else {

				jdbcTemplate.update(updateAmount, new Object[] { gstDetails.getAdvncAmount(), adv_bill_id, ghId });
				try {
					jdbcTemplate.update(query,
							new Object[] { gstDetails.getRollNo(), gstDetails.getAdvncAmount(), arrivalDate,
									adv_bill_id, gstDetails.getTrId(), gstDetails.getGuestId(),
									gstDetails.getReqId() });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}

				// update tr_allotment
				try {
					boolean status = saveDateInTrAllotment(saveCheckinDetails, updatedDate,
							Integer.valueOf(gstDetails.getTrId()), blockId, gstDetails.getReqId(), "E");
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");
				}
			}

		} else {
			try {
				jdbcTemplate.update(query, new Object[] { gstDetails.getRollNo(), gstDetails.getAdvncAmount(),
						arrivalDate, null, gstDetails.getTrId(), gstDetails.getGuestId(), gstDetails.getReqId() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			// update tr_allotment
			try {
				boolean status = saveDateInTrAllotment(saveCheckinDetails, updatedDate,
						Integer.valueOf(gstDetails.getTrId()), blockId, gstDetails.getReqId(), "E");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		return true;
	}

	@Override
	public List<CheckIn> getExtensionCheckinDetails(Map<String, Object> params, HttpSession session) {String check_qry = adminQueries.getProperty("extensioncheckinDetails");
	String gst_qry = adminQueries.getProperty("guestDetails");
	String existGuestQry = adminQueries.getProperty("selectPreviousGuestIdThroughNewGusetId");
	String prevGstStatus = adminQueries.getProperty("selectPreviouGstStatusDuringExt");

	String orderByCondition = " ORDER BY c.req_id DESC";
	String whereCondition = "";

	String cityId = (String) params.get("cId");
	String ghId = (String) params.get("gId");
	String bookingId = (String) params.get("bookingId");
	String fromDate = (String) params.get("fromDate");
	String mobNo = (String) params.get("mobNo");
	String name = (String) params.get("name");
	String category = (String) params.get("category");

	List<Object> queryParams = new ArrayList<>();
	queryParams.add(cityId);
	queryParams.add(ghId);
	if (cityId != null && !cityId.isEmpty()) {
	    whereCondition += " AND c.gh_id = ?";
	    queryParams.add(cityId);
	} else {
	    ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
	    String cityIdString = String.join("', '", cityIdList);
	    whereCondition += " AND c.gh_id IN (" + String.join(", ", Collections.nCopies(cityIdList.size(), "?")) + ")";
	    queryParams.addAll(cityIdList);
	}

	if (ghId != null && !ghId.isEmpty()) {
	    whereCondition += " AND c.block_id = ?";
	    queryParams.add(ghId);
	}

	if (bookingId != null && !bookingId.isEmpty()) {
	    whereCondition += " AND c.req_id = ?";
	    queryParams.add(bookingId);
	}

	if (fromDate != null && !fromDate.isEmpty()) {
	    Date date;
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    try {
	        date = sdf.parse(fromDate);
	    } catch (ParseException e) {
	        throw new RuntimeException(e);
	    }
	    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	    fromDate = sdf1.format(date);
	    whereCondition += " AND c.from_date::date = CAST(? AS date)";
	    queryParams.add(fromDate);
	}


	if (mobNo != null && !mobNo.isEmpty()) {
	    whereCondition += " AND c.mobile_no = ?";
	    queryParams.add(mobNo);
	}

	if (name != null && !name.isEmpty()) {
	    whereCondition += " AND c.name ILIKE ?";
	    queryParams.add("%" + name + "%");
	}

	if (category != null && !category.isEmpty()) {
	    whereCondition += " AND c.category_type = ?";
	    queryParams.add(category);
	}

	check_qry = check_qry + whereCondition + orderByCondition;
	final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	System.out.println("guest query: " + check_qry);

	List<CheckIn> checkIn =new ArrayList<>();
	try {
	checkIn= jdbcTemplate.query(check_qry, queryParams.toArray(), new RowMapper<CheckIn>() {
	    @Override
	    public CheckIn mapRow(ResultSet rs, int rownumber) throws SQLException {
	        CheckIn rest = new CheckIn();
	        rest.setName(rs.getString("name"));
	        rest.setGh_id(rs.getString("gh_id"));
	        rest.setReq_id(rs.getString("req_id"));
	        rest.setExt_req_id(rs.getString("existing_req_id"));
	        rest.setFrom_date(sdf.format(rs.getTimestamp("from_date")));
	        rest.setTo_date(sdf.format(rs.getTimestamp("to_date")));
	        rest.setStatus(rs.getString("status"));

	        String category_Desc = "";
	        String categoryType = rs.getString("category_type");
	        switch (categoryType) {
	            case "C1":
	                category_Desc = "Category 1";
	                break;
	            case "C2":
	                category_Desc = "Category 2";
	                break;
	            case "C3":
	                category_Desc = "Category 3";
	                break;
	            case "C4":
	                category_Desc = "Category 4";
	                break;
	        }
	        rest.setCategoryType(category_Desc);

	        List<GuestDetails> gustDetails = jdbcTemplate.query(gst_qry, new Object[] { rest.getReq_id() },
	                new RowMapper<GuestDetails>() {
	                    @Override
	                    public GuestDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
	                        GuestDetails res = new GuestDetails();
	                        res.setName(rs.getString("name"));
	                        res.setDepartment(rs.getString("department"));
	                        res.setDesignation(rs.getString("designation"));
	                        res.setAddress(rs.getString("address"));
	                        res.setGuestId(rs.getString("guest_id"));
	                        try {
	                            String existGstId = jdbcTemplate.queryForObject(existGuestQry, new Object[] { res.getGuestId() }, String.class);
	                            try {
	                                String status = jdbcTemplate.queryForObject(prevGstStatus, new Object[] { existGstId }, String.class);
	                                res.setStatus(status);
	                            } catch (Exception e) {
	                                res.setStatus(null);
	                            }
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        }
	                        return res;
	                    }
	                });

	        rest.setGuestDetails(gustDetails);

	        return rest;
	    }
	});
	}catch(Exception e) {
		e.printStackTrace();
	}
	return checkIn;
}

	@Override
	public List<GuestDetails> getExtensionViewDetails(Map<String, Object> params) {
		String reqId = (String) params.get("req_id");
		String ExReqId = (String) params.get("existing_req_id");
		String hidval = (String) params.get("hidval");
		String query = adminQueries.getProperty("viewExtensionGuestDetails");
		String q2 = adminQueries.getProperty("viewExtensionGuest");
		String roomQry = adminQueries.getProperty("selectCancelCheckInRoomDetails");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		List<GuestDetails> extensionGuest = new ArrayList<>();
		try {
			extensionGuest = jdbcTemplate.query(query, new RowMapper<GuestDetails>() {

				@Override
				public GuestDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					GuestDetails guest = new GuestDetails();
					guest.setReqId(rs.getString("req_id"));
					guest.setGuestId(rs.getString("guest_id"));
					guest.setTrId(rs.getString("tr_id"));
					
					try {
						String toDate = !rs.getTimestamp("checkin_date").equals("") ? sdf1.format(rs.getTimestamp("checkin_date")) : null;
						guest.setArrivalTime(toDate);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					guest.setRollNo(rs.getString("roll_no"));
					guest.setAdvncAmount(rs.getString("advance_amt"));
					guest.setChildrenBelow(rs.getString("no_of_children_below12"));
					guest.setAdults(rs.getString("adults"));
					guest.setAdvncBillId(rs.getString("adv_bill_id"));
					try {
						jdbcTemplate.queryForObject(q2, new Object[] { guest.getGuestId() },
								(ResultSet rsRm, int rownumberRm) -> {
									guest.setAddress(rsRm.getString("address"));
									guest.setDesignation(rsRm.getString("designation"));
									guest.setDepartment(rsRm.getString("department"));
									guest.setName(rsRm.getString("name"));
									return guest;
								});
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						RoomDetails roomDetails = jdbcTemplate.queryForObject(roomQry, new Object[] { guest.getTrId() },
								(ResultSet rsRm, int rownumberRm) -> {
									RoomDetails roomDetails1 = new RoomDetails();
									roomDetails1.setRoomNo(rsRm.getString("room_no"));
									roomDetails1.setBedNo(rsRm.getString("bed_no"));
									roomDetails1.setType(rsRm.getString("type"));
									roomDetails1.setBlockName(rsRm.getString("block_name_short"));
									return roomDetails1;
								});
						guest.setRoomDetails(roomDetails);
					} catch (Exception e) {
						e.printStackTrace();
						guest.setRoomDetails(null);
					}

					return guest;
				}
			}, ExReqId);
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		System.out.println(extensionGuest);
		return extensionGuest;
	}

	@Override
	public boolean saveExtendsGuestDetails(SaveCheckinDetails saveCheckinDetail, String newReqId, String ghId,
			String blockId) throws Exception {
		String query = adminQueries.getProperty("exitingGuestDetailsInTrCheckin");
		String qry = adminQueries.getProperty("saveCheckInDetails");
		String qry1 = adminQueries.getProperty("extendsUpdateTrCheckIn");
		String qry2 = adminQueries.getProperty("insertChckReqId");
		String trQry = adminQueries.getProperty("selectAlotId");
		String chckAllot = adminQueries.getProperty("insertChckAlot");
		String query2 = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		String gadUpdate = adminQueries.getProperty("updateGadStatus");
		String existinGadUpdate = adminQueries.getProperty("updateExistingGadRequestStatus");
		String existingCheckinAlotUpdate = adminQueries.getProperty("updateExistingTrCheckinAllotStatus");
		String updateAllot = adminQueries.getProperty("updateAllotMentStatus");
		String existingGuestTrIdInCheckAlo = adminQueries.getProperty("existingTrCheckinAllotGuest");
		String duringSvExtAlo = adminQueries.getProperty("updateTrAllotMentDuringSaveExtensionData");
		String previousParentTrPresentOrNot = adminQueries.getProperty("selectPreviousExtendsTrId");
		String previousParentTrStatusUpdate = adminQueries.getProperty("updatePreviousExtendsTrId");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		Date inTime = null;
		Date fromDate = null;
		String frmDt = "";
        Date date = new Date(timestamp.getTime());
		try {
			inTime = !saveCheckinDetail.getInTime().equals("") ? sdf1.parse(saveCheckinDetail.getInTime()) : null;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			fromDate = !saveCheckinDetail.getInTime().equals("") ? sdf2.parse(saveCheckinDetail.getInTime()) : null;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frmDt = fromDate != null ? sdf2.format(fromDate) : null;
		frmDt = frmDt + " 9:00";
		System.out.println("iam in in allotment" + (frmDt));
		try {
			fromDate = !saveCheckinDetail.getInTime().equals("") ? sdf1.parse(frmDt) : null;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("in date format" + fromDate);
		try {
			jdbcTemplate.queryForObject(query,
					new Object[] { saveCheckinDetail.getReqId(), saveCheckinDetail.getPrevGstId() },
					(ResultSet rsRm, int rownumberRm) -> {
						saveCheckinDetail.setTrId(rsRm.getString("tr_id"));
						saveCheckinDetail.setAdvBillId(rsRm.getString("adv_bill_id"));
						saveCheckinDetail.setCategory(rsRm.getString("vc_id"));
						saveCheckinDetail.setPurpose(rsRm.getString("purpose"));
						return saveCheckinDetail;
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

		// insert data in tr_checkin
		Integer trId = 0;
		try {
			trId = get_seqID("tr_id");
			update_seqID("tr_id", trId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			jdbcTemplate.update(qry,
					new Object[] { trId, saveCheckinDetail.getRollNo(), saveCheckinDetail.getGstId(), date, null,
							saveCheckinDetail.getAdvncamount(), newReqId, null, null, null, "CIN",
							saveCheckinDetail.getChildren(), "3309", "orgadmin", saveCheckinDetail.getAdults(),
							timestamp, saveCheckinDetail.getAdvBillId(), ghId, saveCheckinDetail.getTrId(),
							saveCheckinDetail.getCategory(), saveCheckinDetail.getPurpose(), blockId });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		String previousTridPcan = "";
		try {
			previousTridPcan = jdbcTemplate.queryForObject(previousParentTrPresentOrNot, String.class,
					new Object[] { saveCheckinDetail.getTrId() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!previousTridPcan.equals("0")) {
			try {
				jdbcTemplate.update(previousParentTrStatusUpdate, new Object[] { previousTridPcan });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		// update tr_checkin existing guest status
		try {
			jdbcTemplate.update(qry1, new Object[] { saveCheckinDetail.getReqId(), saveCheckinDetail.getPrevGstId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		// insert into tr_checkin_Req

		try {
			jdbcTemplate.update(qry2,
					new Object[] { saveCheckinDetail.getTrId(), saveCheckinDetail.getReqId(), "CAN", timestamp });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}

		try {
			jdbcTemplate.update(qry2, new Object[] { trId, saveCheckinDetail.getReqId(), "LIV", timestamp });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}

		try {
			jdbcTemplate.update(qry2, new Object[] { trId, newReqId, "LIV", timestamp });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}

		// insert data in tr_checkin_allot

		// existing alloted room
		List<String> trExistingReqId = new ArrayList<>();
		try {
			trExistingReqId = jdbcTemplate.query(existingGuestTrIdInCheckAlo,
					new Object[] { saveCheckinDetail.getTrId() }, new RowMapper<String>() {
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString(2);
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String i : trExistingReqId) {
			try {
				jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}

		// newly alloted room
		List<String> trReqId = new ArrayList<>();
		try {
			trReqId = jdbcTemplate.query(trQry, new Object[] { blockId, newReqId }, new RowMapper<String>() {
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString(1);
				}
			});
			System.out.println(trReqId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String i : trReqId) {
			try {
				jdbcTemplate.update(chckAllot, new Object[] { trId, i, "LIV", timestamp });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}
		// update into gad_details

		Integer noOfGst = null;
		Integer noOfChckGst = null;
		try {
			noOfChckGst = jdbcTemplate.queryForObject(query2, new Object[] { newReqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			noOfGst = jdbcTemplate.queryForObject(query1, new Object[] { newReqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		try {
			jdbcTemplate.update(gadUpdate, new Object[] { "CIN", timestamp, newReqId });

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}

		// update tr_allotment

		try {
			jdbcTemplate.update(duringSvExtAlo, new Object[] { "ACIN", saveCheckinDetail.getReqId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		try {
			jdbcTemplate.update(updateAllot, new Object[] { "CIN", fromDate, newReqId });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		// update existing gad update EXT

		try {
			jdbcTemplate.update(existinGadUpdate, new Object[] { saveCheckinDetail.getReqId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}

		// update existing tr_checkin_allot
		try {
			jdbcTemplate.update(existingCheckinAlotUpdate, new Object[] { saveCheckinDetail.getTrId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		return true;
	}

	// it's last method
	@Override
	public boolean updateCancelCheckOutDetails(String trId) {
		String query1 = adminQueries.getProperty("updateCancelCheckOutTrCheckin");
		String query2 = adminQueries.getProperty("updateCancelCheckOutTrCheckinAllot");
		String query3 = adminQueries.getProperty("selectParentTrid");
		String query4 = adminQueries.getProperty("selectGuestDetailsInCheckin");
		String query5 = adminQueries.getProperty("selectGuestOnReqId");
		String gadUpdate = adminQueries.getProperty("updateGadStatus");
		String query6 = adminQueries.getProperty("updateTrAllotmentDuringCancelCout");
		String query7 = adminQueries.getProperty("selectReqId");
		String query8 = adminQueries.getProperty("updateCancelCheckOutTrCheckinAllotPrevious");
		String query9 = adminQueries.getProperty("checkPartialCancelOrNot");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String previousTrid = "";
		String newReqId = "";
		String selectPreviousReqid = "";
		try {
			previousTrid = jdbcTemplate.queryForObject(query3, String.class, new Object[] { trId });
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			newReqId = jdbcTemplate.queryForObject(query7, String.class, new Object[] { trId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		System.out.println("iam in if with :" + previousTrid);
		if (previousTrid.equals("0")) {
			// update tr_checkin status
			try {
				jdbcTemplate.update(query1, new Object[] { "CIN", trId, "COUT" });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}
			// update tr_checkin_allot
			try {
				jdbcTemplate.update(query2, new Object[] { trId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}
			// gad request
			Integer noOfGst = null;
			Integer noOfChckGst = null;
			try {
				noOfGst = jdbcTemplate.queryForObject(query5, new Object[] { newReqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			try {
				noOfChckGst = jdbcTemplate.queryForObject(query4, new Object[] { newReqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if (noOfGst == noOfChckGst) {
				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "CIN", timestamp, newReqId });

				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Issue in inserting");
				}
			} else {
				try {
					jdbcTemplate.update(gadUpdate, new Object[] { "PCIN", timestamp, newReqId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Issue in inserting");
				}
			}
			
			// update tr_allotment
			
			Integer pcr_id=null;
			try {
				pcr_id = jdbcTemplate.queryForObject(query9, new Object[] { newReqId }, Integer.class);
			} catch (Exception e) {
				pcr_id=0;
			}
			if(pcr_id.equals(0)) {
				if (noOfGst == noOfChckGst) {
					try {
						jdbcTemplate.update(query6, new Object[] { "CIN", newReqId });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Issue in inserting");
					}
				} else {
					try {
						jdbcTemplate.update(query6, new Object[] { "PCIN", newReqId });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Issue in inserting");
					}
				}
			}else {
				boolean status=partailOpertionInCancelCheckOut(newReqId,pcr_id);
				if(!status) {
					return false;
				}
			}
			
		} else {
			// update tr_checkin parentData
			try {
				jdbcTemplate.update(query1, new Object[] { "PCAN", previousTrid, "CAN" });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}

			try {
				jdbcTemplate.update(query1, new Object[] { "CIN", trId, "COUT" });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}
			// update tr_checkin_allot
			try {
				jdbcTemplate.update(query2, new Object[] { trId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}

			try {
				jdbcTemplate.update(query8, new Object[] { previousTrid });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}
			// update gad_request
			try {
				jdbcTemplate.update(gadUpdate, new Object[] { "CIN", timestamp, newReqId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}
			// update tr_allotment

			Integer pcr_id = null;
			try {
				pcr_id = jdbcTemplate.queryForObject(query9, new Object[] { newReqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				pcr_id = 0;
			}
			// gad request
			Integer noOfGst = null;
			Integer noOfChckGst = null;
			try {
				noOfGst = jdbcTemplate.queryForObject(query5, new Object[] { newReqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			try {
				noOfChckGst = jdbcTemplate.queryForObject(query4, new Object[] { newReqId }, Integer.class);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			if (pcr_id.equals(0)) {
				if (noOfGst == noOfChckGst) {
					try {
						jdbcTemplate.update(query6, new Object[] { "CIN", newReqId });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Issue in inserting");
					}
				} else {
					try {
						jdbcTemplate.update(query6, new Object[] { "PCIN", newReqId });
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("Issue in inserting");
					}
				}
			} else {
				try {
				boolean status = partailOpertionInCancelCheckOut(newReqId, pcr_id);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Issue in inserting");
				}
			}
			
		}
		return true;
	}
	@Transactional
	public boolean partailOpertionInCancelCheckOut(String reqId,Integer pcr_id) {
		String query1 = adminQueries.getProperty("partialTrAllotment");
		String query4 = adminQueries.getProperty("partialTrAllotmentCanToCin");
		String query2 = adminQueries.getProperty("deletePartialCancelRequest");
		String query3=	adminQueries.getProperty("partialgadRequest");
		String query5=	adminQueries.getProperty("selectCheckOutAllotId");
		String query6=	adminQueries.getProperty("selectPCANAllotId");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		List<String> coutStatusAllotId=new ArrayList<>();
		try {
			Object[] params = {reqId};
			coutStatusAllotId = jdbcTemplate.query(query5, params, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("allot_id");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String allotId : coutStatusAllotId) {
			try {
				jdbcTemplate.update(query1, new Object[] { "CIN",allotId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Issue in inserting");
			}
		}
//      tr_allotment can_status pcan
		List<String> partialCancel=new ArrayList<>();
		try {
			Object[] params = {reqId};
			partialCancel = jdbcTemplate.query(query6, params, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getString("allot_id");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String allotId : partialCancel) {
			try {
				jdbcTemplate.update(query4, new Object[] { "CIN",null,allotId });
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
//   	 partial cancel delete  data
		try {
			jdbcTemplate.update(query2, new Object[] { pcr_id });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Issue in inserting");
		}
		//gad_status pcan set to null
		try {
			jdbcTemplate.update(query3, new Object[] { null,reqId });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Issue in inserting");
		}
		
		
		return true;
	}
	@Override
	public Pagination getVehicleAllotedDatas(Map<String, Object> params) {
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    String blockId = (String) params.get("gId");
	    String bookingId = (String) params.get("bookingId");
	    int pageNo = (int) params.get("pageNo");
	    String name = (String) params.get("name");

	    // Initial queries
	    String query1 = adminQueries.getProperty("selectVeicleRequestDetails");
	    String query2 = adminQueries.getProperty("countVeicleRequestDetails");
	    String query6 = adminQueries.getProperty("countVehicleAllotedGuest");
	    
	    // Parameters for the queries
	    List<Object> queryParams = new ArrayList<>();
	    List<Object> queryParams1 = new ArrayList<>();
	    // Building query conditions
	    if (blockId != null && !blockId.equals("ALL") && !blockId.isEmpty()) {
	        query1 += " AND a.block_id = ?";
	        query2 += " AND a.block_id = ?";
	        queryParams.add(blockId);
	        queryParams1.add(blockId);
	    }
	    if (bookingId != null && !bookingId.isEmpty()) {
	        query1 += " AND a.req_id = ?";
	        query2 += " AND a.req_id = ?";
	        queryParams.add(bookingId);
	        queryParams1.add(bookingId);
	    }
	    if (name != null && !name.isEmpty()) {
	        query1 += " AND gd.name ILIKE ?";
	        query2 += " AND a.req_id = ?";
	        queryParams.add("%" + name + "%");
	        queryParams1.add("%" + name + "%");
	    }
	    if (pageNo != 0) {
	        int offset = (pageNo - 1) * Constants.LIMIT;
	        query1 += " LIMIT ? OFFSET ?";
	        queryParams.add(Constants.LIMIT);
	        queryParams.add(offset);
	        
	    }

	    // Initialize pageCount variable
	    Integer pageCount = 0;
	    try {
	        pageCount = jdbcTemplate.queryForObject(query2, queryParams1.toArray(), Integer.class);
	    } catch (Exception e) {
	        logger.error("Error while fetching page count", e);
	    }

	    List<VehiCleRequestModel> listOfVehicleRequest = new ArrayList<>();
	    try {
	        listOfVehicleRequest = jdbcTemplate.query(query1, queryParams.toArray(), new RowMapper<VehiCleRequestModel>() {
	            @Override
	            public VehiCleRequestModel mapRow(ResultSet rs, int rowNumber) throws SQLException {
	                String query5 = approverQueries.getProperty("guestDetails");
	                VehiCleRequestModel vehReqMod = new VehiCleRequestModel();
	                vehReqMod.setReqId(rs.getString("req_id"));
	                vehReqMod.setArrival(rs.getString("is_veh_req"));
	                vehReqMod.setDeparture(rs.getString("is_veh_req1"));

	                // Mapping guest details
	                GuestDetails res = new GuestDetails();
	                res.setName(rs.getString("name"));
	                res.setDepartment(rs.getString("department"));
	                res.setDesignation(rs.getString("designation"));
	                res.setGender(rs.getString("gender"));
	                vehReqMod.setGuestDetails(res);

	                try {
	                    Integer noOfGst = jdbcTemplate.queryForObject(query6, new Object[] { vehReqMod.getReqId() }, Integer.class);
	                    vehReqMod.setCheckStatus(noOfGst);
	                } catch (Exception e) {
	                    vehReqMod.setCheckStatus(0);
	                    logger.error("Error while fetching vehicle allocation status for requestId: " + vehReqMod.getReqId(), e);
	                }

	                // Mapping arrival details
	                ArrivalDepartureModel arrivalDetails = new ArrivalDepartureModel();
	                arrivalDetails.setModeOfTravel(rs.getString("mode_of_travel"));
	                arrivalDetails.setPlace(rs.getString("arrival_place"));
	                try {
	                    String startTime = rs.getString("arrival_time") != null && !rs.getString("arrival_time").isEmpty()
	                            ? sdf1.format(rs.getTimestamp("arrival_time"))
	                            : null;
	                    arrivalDetails.setStratTime(startTime);
	                    arrivalDetails.setVehNumber(rs.getString("ftno"));
	                    vehReqMod.setArrivalDetails(arrivalDetails);
	                } catch (Exception e) {
	                    logger.error("Error while processing arrival details for requestId: " + vehReqMod.getReqId(), e);
	                }

	                // Mapping departure details
	                ArrivalDepartureModel departureDetails = new ArrivalDepartureModel();
	                departureDetails.setModeOfTravel(rs.getString("dep_mode_of_travel"));
	                departureDetails.setPlace(rs.getString("dep_arrival_place"));
	                try {
	                    String startTime = rs.getString("dep_arrival_time") != null && !rs.getString("dep_arrival_time").isEmpty()
	                            ? sdf1.format(rs.getTimestamp("dep_arrival_time"))
	                            : null;
	                    departureDetails.setStratTime(startTime);
	                    departureDetails.setVehNumber(rs.getString("dep_ftno"));
	                    vehReqMod.setDepartureDetails(departureDetails);
	                } catch (Exception e) {
	                    logger.error("Error while processing departure details for requestId: " + vehReqMod.getReqId(), e);
	                }

	                return vehReqMod;
	            }
	        });
	    } catch (Exception e) {
	    	e.printStackTrace();
	        logger.error("Error while fetching vehicle request details", e);
	    }

	    // Wrapping the response in Pagination object
	    Pagination pagination = new Pagination();
	    pagination.setData(listOfVehicleRequest);
	    pagination.setTotalPages(pageCount);

	    return pagination;
	}


	@Override
	public List<DriverWithVehicleDetails> getDriverDetails(Map<String, Object> params) {
		String blockId = (String) params.get("gId");
		String query1 = adminQueries.getProperty("getDriverDetails");
		List<DriverWithVehicleDetails> driverWithVehicleDetails = new ArrayList<>();
		try {
			driverWithVehicleDetails = jdbcTemplate.query(query1, new RowMapper<DriverWithVehicleDetails>() {
				@Override
				public DriverWithVehicleDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					DriverWithVehicleDetails driverWithVehicleDetails1 = new DriverWithVehicleDetails();
					driverWithVehicleDetails1.setDriverId(rs.getString("driver_id"));
					driverWithVehicleDetails1.setDriverName(rs.getString("driver_name"));
					driverWithVehicleDetails1.setDriverMobileNo(rs.getString("driver_mobileno"));
					driverWithVehicleDetails1.setProtocolMobile(rs.getString("protocol_mobileno"));
					driverWithVehicleDetails1.setProtocolName(rs.getString("protocol_name"));
					driverWithVehicleDetails1.setVehicledesc(rs.getString("vehicle_description"));
					driverWithVehicleDetails1.setVehicleNo(rs.getString("vehicle_number"));
					return driverWithVehicleDetails1;
				}
			}, blockId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driverWithVehicleDetails;
	}

	@Override
	public boolean checkVacancyVehicle(String VehicleNo, String startTime, String endTime, String reqId, String ghId,
			String blockId) {
		String query1 = adminQueries.getProperty("checkVehicleAviability");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		boolean status = false;
		List<DriverWithVehicleDetails> driverWithVehicleDetails = new ArrayList<>();
		try {
			ArrivalDepartureModel departureDetails = jdbcTemplate.queryForObject(query1,
					new Object[] { reqId, VehicleNo, startTime, endTime }, (ResultSet rsRm, int rownumberRm) -> {
						ArrivalDepartureModel departureDetails1 = new ArrivalDepartureModel();
						departureDetails1.setVehId(rsRm.getString("veh_id"));
						departureDetails1.setModeOfTravel(rsRm.getString("mode_of_travel"));
						departureDetails1.setPlace(rsRm.getString("arrival_place"));
						departureDetails1.setStratTime(rsRm.getString("arrival_time"));
						departureDetails1.setVehNumber(rsRm.getString("ftno"));
						return departureDetails1;
					});
		} catch (Exception e) {
			status = true;
		}
		return status;

	}

	@Override
	@Transactional
	public boolean setVehicleallotment(VehicleAllotmentModel vehicleAllotmentModel) {
	    String query1 = adminQueries.getProperty("insertDataInVehicleAllot");
	    String query2 = adminQueries.getProperty("updateVehArrivalTime");
	    String query3 = adminQueries.getProperty("updateVehArrivalTime1");
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    Date reqTime = null;
	    Date startTime = null;
	    Date endTime = null;
	    Integer vAllot = 0;
	    boolean smsMode = true;

	    try {
	        reqTime = !vehicleAllotmentModel.getReqTime().equals("") ? sdf1.parse(vehicleAllotmentModel.getReqTime())
	                : null;
	        startTime = !vehicleAllotmentModel.getStartTime().equals("") ? sdf1.parse(vehicleAllotmentModel.getStartTime())
	                : null;
	        endTime = !vehicleAllotmentModel.getEndTime().equals("") ? sdf1.parse(vehicleAllotmentModel.getEndTime())
	                : null;

	        vAllot = get_seqID("v_allot");
	        update_seqID("v_allot", vAllot);

	        if (vehicleAllotmentModel.getTripStatus().equals("A")) {
	        	 jdbcTemplate.update(query2, new Object[]{reqTime,vehicleAllotmentModel.getReqId()});
	        } else {
	        	jdbcTemplate.update(query3, new Object[]{reqTime,vehicleAllotmentModel.getReqId()});
	        }

	        		jdbcTemplate.update(query1,
	                new Object[]{vehicleAllotmentModel.getReqId(), vAllot, vehicleAllotmentModel.getVehicleDetails(),
	                        vehicleAllotmentModel.getVehicleNo(), vehicleAllotmentModel.getDriverName(),
	                        vehicleAllotmentModel.getDriverNo(), reqTime, vehicleAllotmentModel.getPlace(),
	                        vehicleAllotmentModel.getStatus(), vehicleAllotmentModel.getGhId(), startTime, endTime,
	                        vehicleAllotmentModel.getBlockId(), vehicleAllotmentModel.getProtocolName(),
	                        vehicleAllotmentModel.getTripStatus(), vehicleAllotmentModel.getProtocolNo()});

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Error in setVehicleallotment", e);
	    }

	    return true;
	}


	@Override
	public String getStatusSendingOrNot(String mode, String reqId, String ghId) {
		String query1 = adminQueries.getProperty("selectMsgsentOrNot");
		String status = null;
		try {
			status = jdbcTemplate.queryForObject(query1, new Object[] { reqId, ghId, mode }, String.class);

		} catch (Exception e) {

		}

		return status;
	}

	@Override
	public GuestDetails getGuestDetail(String reqId, String ghId) {
		String query1 = adminQueries.getProperty("selectGstDetails");
		GuestDetails guestDetails = new GuestDetails();
		try {
			guestDetails = jdbcTemplate.queryForObject(query1, new Object[] { reqId, ghId },
					(ResultSet rsRm, int rownumberRm) -> {
						GuestDetails guestDetails1 = new GuestDetails();
						guestDetails1.setName(rsRm.getString("name"));
						guestDetails1.setDepartment(rsRm.getString("department"));
						guestDetails1.setMobileNo(rsRm.getString("mobile_no"));
						guestDetails1.setEmailId(rsRm.getString("email_id"));
						return guestDetails1;
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return guestDetails;
	}

	@Override
	public Integer saveGuestSms(SmsAllotmentModel smsAllotmentModel) {
		System.out.println("in dao impl");
		String query1 = adminQueries.getProperty("gstTrSmsAllt");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date reqTime = null;
		String mobileNo = "";
		String emailId = "";
		Integer success = null;
		try {
			reqTime = !smsAllotmentModel.getTimeOfReport().equals("") ? sdf2.parse(smsAllotmentModel.getTimeOfReport())
					: null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer aSmId = 0;
		try {
			aSmId = get_seqID("a_sms_id");
			update_seqID("a_sms_id", aSmId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String Id = "";
		if (smsAllotmentModel.getRole().equals("guest")) {
			Id = "G" + aSmId;
			mobileNo = smsAllotmentModel.getGuestNo();
			emailId = smsAllotmentModel.getMail();
		}

		if (smsAllotmentModel.getRole().equals("driver")) {
			Id = "D_" + aSmId;
			mobileNo = smsAllotmentModel.getDriverNo();
			emailId = "";
		}

		if (smsAllotmentModel.getRole().equals("protocol")) {
			Id = "P_" + aSmId;
			mobileNo = smsAllotmentModel.getProtocoNo();
			emailId = "";
		}
		try {
			success = jdbcTemplate.update(query1,
					new Object[] { Id, smsAllotmentModel.getReqId(), smsAllotmentModel.getGuestName(),
							smsAllotmentModel.getSubject(), mobileNo, smsAllotmentModel.getStatus(),
							smsAllotmentModel.getGhId(), smsAllotmentModel.getPmode(), reqTime, null,
							smsAllotmentModel.getTrIp() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (success.equals(1)) {
//			try {
//				if (emailId != null) {
//					if (!emailId.isEmpty()) {
//						mail.sendMimeMail(smsAllotmentModel.getMail(), "Vehicle Alloted Details",
//								smsAllotmentModel.getSubject());// mail
//					}
//				}
//			} catch (Exception e) {
//				logger.error("error occured in sms or email send..");
//			}
			try {
				if (mobileNo!= null) {
					if (!mobileNo.isEmpty()) {
						vehicleSmsSend(mobileNo, smsAllotmentModel.getSubject(), smsAllotmentModel.getTemplateId());
					}
				}
			} catch (Exception e) {
				logger.error("Error occured in sms " + e.getMessage());
			}

		}

		return success;

	}
	@Async
	public void vehicleSmsSend(String mobileNo,String subject,String templateId) {
		smsSend.sendSMS(mobileNo,subject,templateId);
	}
	@Override
	public ArrivalDepartureModel selectVehicleReq(String reqId) {
		String query1 = adminQueries.getProperty("getGstTravelDetails");
		ArrivalDepartureModel arrivalDepartureModel = new ArrivalDepartureModel();
		try {
			arrivalDepartureModel = jdbcTemplate.queryForObject(query1, new Object[] { reqId },
					(ResultSet rsRm, int rownumberRm) -> {
						ArrivalDepartureModel arrivalDepartureModel1 = new ArrivalDepartureModel();
						arrivalDepartureModel1.setModeOfTravel(rsRm.getString("mode_of_travel"));
						arrivalDepartureModel1.setFtNo(rsRm.getString("ftno"));
						return arrivalDepartureModel1;
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrivalDepartureModel;
	}

	@Override
	public ArrivalDepartureModel selectVehicleReq1(String reqId) {
		String query1 = adminQueries.getProperty("getGstTravelDetailsDeparture");
		ArrivalDepartureModel arrivalDepartureModel = new ArrivalDepartureModel();
		try {
			arrivalDepartureModel = jdbcTemplate.queryForObject(query1, new Object[] { reqId },
					(ResultSet rsRm, int rownumberRm) -> {
						ArrivalDepartureModel arrivalDepartureModel1 = new ArrivalDepartureModel();
						arrivalDepartureModel1.setModeOfTravel(rsRm.getString("mode_of_travel"));
						arrivalDepartureModel1.setFtNo(rsRm.getString("ftno"));
						return arrivalDepartureModel1;
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrivalDepartureModel;
	}

	@Override
	public boolean updateVehicleallotment(VehicleAllotmentModel vehicleAllotmentModel) {
		System.out.println(vehicleAllotmentModel.toString());
		String query1 = adminQueries.getProperty("updateDataInVehicleAllot");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date reqTime = null;
		Date startTime = null;
		Date endTime = null;
		boolean smsMode = true;
		try {
			reqTime = !vehicleAllotmentModel.getReqTime().equals("") ? sdf1.parse(vehicleAllotmentModel.getReqTime())
					: null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			startTime = !vehicleAllotmentModel.getStartTime().equals("")
					? sdf1.parse(vehicleAllotmentModel.getStartTime())
					: null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			endTime = !vehicleAllotmentModel.getEndTime().equals("") ? sdf1.parse(vehicleAllotmentModel.getEndTime())
					: null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			jdbcTemplate.update(query1,
					new Object[] { vehicleAllotmentModel.getReqId(), vehicleAllotmentModel.getVehicleDetails(),
							vehicleAllotmentModel.getVehicleNo(), vehicleAllotmentModel.getDriverName(),
							vehicleAllotmentModel.getDriverNo(), reqTime, vehicleAllotmentModel.getPlace(),
							vehicleAllotmentModel.getStatus(), vehicleAllotmentModel.getGhId(), startTime, endTime,
							vehicleAllotmentModel.getBlockId(), vehicleAllotmentModel.getProtocolName(),
							vehicleAllotmentModel.getProtocolNo(), vehicleAllotmentModel.getVehicleRequestId(),
							vehicleAllotmentModel.getTripStatus() });
		} catch (Exception e) {
			e.printStackTrace();
		}

		return smsMode;
	}

	@Override
	public Integer updateGuestSms(SmsAllotmentModel smsAllotmentModel) {
		String query1 = adminQueries.getProperty("updateSmsAllot");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date reqTime = null;
		String mobileNo = "";
		String emailId = "";
		Integer success = null;
		try {
			reqTime = !smsAllotmentModel.getTimeOfReport().equals("") ? sdf2.parse(smsAllotmentModel.getTimeOfReport())
					: null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String Id = "";
		if (smsAllotmentModel.getRole().equals("guest")) {
			mobileNo = smsAllotmentModel.getGuestNo();
			emailId = smsAllotmentModel.getMail();
		}

		if (smsAllotmentModel.getRole().equals("driver")) {
			mobileNo = smsAllotmentModel.getDriverNo();
			emailId = "";
		}

		if (smsAllotmentModel.getRole().equals("protocol")) {
			mobileNo = smsAllotmentModel.getProtocoNo();
			emailId = "";
		}
		try {
			success = jdbcTemplate.update(query1,
					new Object[] { smsAllotmentModel.getSubject(), mobileNo, smsAllotmentModel.getReqId(),
							smsAllotmentModel.getGhId(), smsAllotmentModel.getStatus(), smsAllotmentModel.getPmode(),
							smsAllotmentModel.getTrIp() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(success);
		if (success.equals(1)) {
//			try {
//				if (emailId != null) {
//					if (!emailId.isEmpty()) {
//						mail.sendMimeMail(emailId, "Vehicle Alloted Details", smsAllotmentModel.getSubject());// mail
//					}
//				}
//			} catch (Exception e) {
//				logger.error("error occured in sms or email send..");
//			}
//		}
		try {
			if (mobileNo != null) {
				if (!mobileNo.isEmpty()) {
					smsSend.sendSMS(mobileNo, smsAllotmentModel.getSubject(), smsAllotmentModel.getTemplateId());
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}

	}

		return success;
	}

	@Override
	public List<VehicleAllotmentModel> getVehicleAllotDataGuestWise(Map<String, Object> params) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String query1 = adminQueries.getProperty("vehicleAllotmentDetailsGuestWise");
		String ghId = (String) params.get("cId");
		String blockId = (String) params.get("gId");
		String reqId = (String) params.get("reqId");
		List<VehicleAllotmentModel> vehicleAllotmentModel = new ArrayList<>();
		try {
			vehicleAllotmentModel = jdbcTemplate.query(query1, new RowMapper<VehicleAllotmentModel>() {
				@Override
				public VehicleAllotmentModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					VehicleAllotmentModel vehicleAllotmentModel1 = new VehicleAllotmentModel();
					vehicleAllotmentModel1.setReqId(rs.getString("req_id"));
					vehicleAllotmentModel1.setVehicleRequestId(rs.getString("vehicle_reqid"));
					vehicleAllotmentModel1.setDriverName(rs.getString("driver_name"));
					vehicleAllotmentModel1.setDriverNo(rs.getString("driver_mobileno"));
					vehicleAllotmentModel1.setProtocolNo(rs.getString("protocol_mobileno"));
					vehicleAllotmentModel1.setProtocolName(rs.getString("protocol_name"));
					vehicleAllotmentModel1.setVehicleDetails(rs.getString("vehicle_details"));
					vehicleAllotmentModel1.setVehicleNo(rs.getString("vehicle_no"));
					vehicleAllotmentModel1.setArrival(rs.getString("is_veh_req"));
					vehicleAllotmentModel1.setDeparture(rs.getString("is_veh_req1"));
					try {
						String strtTime = !rs.getString("vehicle_start_time").equals("")
								? sdf1.format(rs.getTimestamp("vehicle_start_time"))
								: null;
						vehicleAllotmentModel1.setStartTime(strtTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						String endTime = !rs.getString("vehicle_end_time").equals("")
								? sdf1.format(rs.getTimestamp("vehicle_end_time"))
								: null;
						vehicleAllotmentModel1.setEndTime(endTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						String reqTime = !rs.getString("time_of_report").equals("")
								? sdf1.format(rs.getTimestamp("time_of_report"))
								: null;
						vehicleAllotmentModel1.setReqTime(reqTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						String unexpectTime = rs.getDate("unexcepted_time")!=null
								? sdf1.format(rs.getTimestamp("unexcepted_time"))
								: null;
						vehicleAllotmentModel1.setUnexceptedTime(unexpectTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
					vehicleAllotmentModel1.setUnexceptedPlace(rs.getString("unexcepted_place"));
					vehicleAllotmentModel1.setPlace(rs.getString("place_of_report"));
					vehicleAllotmentModel1.setTripStatus(rs.getString("trip_status"));
					return vehicleAllotmentModel1;
				}
			}, reqId, blockId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vehicleAllotmentModel;
	}

	@Override
	public boolean cancelVehicleAllotment(String reqId) {
		String sql = adminQueries.getProperty("cancelVehcileAllotment");
		try {
			jdbcTemplate.update(sql, new Object[] { reqId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean cancelSmsVehicleAllotment(String reqId) {
		String sql = adminQueries.getProperty("cancelSmsVehcileAllotment");
		try {
			jdbcTemplate.update(sql, new Object[] { reqId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public Map<String, Object> getViewVehicleAllotmentReport(Map<String, Object> params) {
		String sql = adminQueries.getProperty("viewVehicleAllotedReport");
		String sql1 = adminQueries.getProperty("getVehicleAllotedReportDeparture");
		String ghId = (String) params.get("cId");
		String blockId = (String) params.get("gId");
		String reqId = (String) params.get("bookingId");
		String name = (String) params.get("name");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Map<String, Object> resp = new HashMap<String, Object>();
		if (!ghId.equals("ALL")&&!ghId.equals("")) {
			sql=sql+"and fa.gh_id='"+ghId+"'";
		}
		if (!blockId.equals("ALL")&&!blockId.equals("")) {
			sql=sql+"and fa.block_id='"+blockId+"'";
		}
		if (reqId!=null&&!reqId.equals("")&&!reqId.equals("ALL")) {
			sql=sql+"and fa.f_id='"+reqId+"'";
		}
		if (name!=null&&!name.equals("")&&!name.equals("ALL")) {
			sql=sql+ "and fa.name ILIKE '%" + name + "%'";
		}
		
		//departure details
		if (!ghId.equals("ALL")&&!ghId.equals("")) {
			sql1=sql1+"and fa.gh_id='"+ghId+"'";
		}
		if (!blockId.equals("ALL")&&!blockId.equals("")) {
			sql1=sql1+"and fa.block_id='"+blockId+"'";
		}
		if (reqId!=null&&!reqId.equals("")) {
			sql1=sql1+"and fa.d_id='"+reqId+"'";
		}
		if (name!=null&&!name.equals("")) {
			sql1=sql1+ "and fa.name ILIKE '%" + name + "%'";
		}
		System.out.println(sql.toString());
		List<VehicleAllotedReport> vehicleAllotmentModel = new ArrayList<>();
		try {
			vehicleAllotmentModel = jdbcTemplate.query(sql, new RowMapper<VehicleAllotedReport>() {
				@Override
				public VehicleAllotedReport mapRow(ResultSet rs, int rownumber) throws SQLException {
					VehicleAllotedReport vehicleAllotmentModel1 = new VehicleAllotedReport();
					vehicleAllotmentModel1.setName(rs.getString("name"));
					vehicleAllotmentModel1.setAge(rs.getString("age"));
					vehicleAllotmentModel1.setDesignation(rs.getString("designation"));
					vehicleAllotmentModel1.setDepartment(rs.getString("department"));
					vehicleAllotmentModel1.setReqId(rs.getString("req_id"));
					
					if(Objects.nonNull(rs.getString("trip_status"))) {
						vehicleAllotmentModel1.setArrDriverName(rs.getString("arr_drv_name"));
						vehicleAllotmentModel1.setArrDriverNo(rs.getString("arr_drv_no"));
						vehicleAllotmentModel1.setArrProtocolNo(rs.getString("arr_pro_name"));
						vehicleAllotmentModel1.setArrProtocolName(rs.getString("arr_pro_no"));
						vehicleAllotmentModel1.setArrVehDetails(rs.getString("arr_veh_detils"));
						vehicleAllotmentModel1.setArrVehNo(rs.getString("arr_veh_no"));
						vehicleAllotmentModel1.setArrivalPlace(rs.getString("arr_place_of_report"));
						vehicleAllotmentModel1.setArrtripSattus(rs.getString("trip_status"));
						vehicleAllotmentModel1.setArrModOfTravel(rs.getString("mode_of_travel"));
						vehicleAllotmentModel1.setArrFtNo(rs.getString("ftno"));
						try {
							String strtTime = !rs.getString("arr_veh_strt_time").equals("")
									? sdf1.format(rs.getTimestamp("arr_veh_strt_time"))
									: null;
									vehicleAllotmentModel1.setArrStrtTime(strtTime);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							String endTime = !rs.getString("arr_veh_end_time").equals("")
									? sdf1.format(rs.getTimestamp("arr_veh_end_time"))
									: null;
									vehicleAllotmentModel1.setArrEndTime(endTime);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							String reqTime = !rs.getString("arr_time_of_report").equals("")
									? sdf1.format(rs.getTimestamp("arr_time_of_report"))
									: null;
									vehicleAllotmentModel1.setArrTimeOfReport(reqTime);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					
					if(Objects.nonNull(rs.getString("d_trip_status"))) {
						vehicleAllotmentModel1.setDepDriverName(rs.getString("dep_drv_name"));
						vehicleAllotmentModel1.setDepDriverNo(rs.getString("dep_drv_no"));
						vehicleAllotmentModel1.setDepProtocolName(rs.getString("dep_pro_name"));
						vehicleAllotmentModel1.setDepProtocolNo(rs.getString("dep_pro_no"));
						vehicleAllotmentModel1.setDepVehDetails(rs.getString("dep_veh_detils"));
						vehicleAllotmentModel1.setDepVehNo(rs.getString("dep_veh_no"));
						vehicleAllotmentModel1.setDeparrivalPlace(rs.getString("dep_place_of_report"));
						vehicleAllotmentModel1.setDepTripSattus(rs.getString("d_trip_status"));
						vehicleAllotmentModel1.setDepModOfTravel(rs.getString("dep_mod_travel"));
						vehicleAllotmentModel1.setDepFtNo(rs.getString("dep_ftno"));	
					
					try {
						String strtTime = !rs.getString("dep_veh_strt_time").equals("")
								? sdf1.format(rs.getTimestamp("dep_veh_strt_time"))
								: null;
								vehicleAllotmentModel1.setDepStrtTime(strtTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						String endTime = !rs.getString("dep_veh_end_time").equals("")
								? sdf1.format(rs.getTimestamp("dep_veh_end_time"))
								: null;
								vehicleAllotmentModel1.setDepEndTime(endTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						String reqTime = !rs.getString("dep_time_of_report").equals("")
								? sdf1.format(rs.getTimestamp("dep_time_of_report"))
								: null;
								vehicleAllotmentModel1.setDepTimeOfReport(reqTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					return vehicleAllotmentModel1;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		List<VehicleAllotedReport> vehicleAllotmentModelDeparture = new ArrayList<>();
		try {
			vehicleAllotmentModelDeparture = jdbcTemplate.query(sql1, new RowMapper<VehicleAllotedReport>() {
				@Override
				public VehicleAllotedReport mapRow(ResultSet rs, int rownumber) throws SQLException {
					VehicleAllotedReport vehicleAllotmentModel1 = new VehicleAllotedReport();
					vehicleAllotmentModel1.setName(rs.getString("name"));
					vehicleAllotmentModel1.setAge(rs.getString("age"));
					vehicleAllotmentModel1.setDesignation(rs.getString("designation"));
					vehicleAllotmentModel1.setDepartment(rs.getString("department"));
					vehicleAllotmentModel1.setReqId(rs.getString("req_id"));
					if(Objects.nonNull(rs.getString("d_trip_status"))) {
						vehicleAllotmentModel1.setDepDriverName(rs.getString("dep_drv_name"));
						vehicleAllotmentModel1.setDepDriverNo(rs.getString("dep_drv_no"));
						vehicleAllotmentModel1.setDepProtocolName(rs.getString("dep_pro_name"));
						vehicleAllotmentModel1.setDepProtocolNo(rs.getString("dep_pro_no"));
						vehicleAllotmentModel1.setDepVehDetails(rs.getString("dep_veh_detils"));
						vehicleAllotmentModel1.setDepVehNo(rs.getString("dep_veh_no"));
						vehicleAllotmentModel1.setDeparrivalPlace(rs.getString("dep_place_of_report"));
						vehicleAllotmentModel1.setDepTripSattus(rs.getString("d_trip_status"));
						vehicleAllotmentModel1.setDepModOfTravel(rs.getString("dep_mod_travel"));
						vehicleAllotmentModel1.setDepFtNo(rs.getString("dep_ftno"));	
					
					try {
						String strtTime = !rs.getString("dep_veh_strt_time").equals("")
								? sdf1.format(rs.getTimestamp("dep_veh_strt_time"))
								: null;
								vehicleAllotmentModel1.setDepStrtTime(strtTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						String endTime = !rs.getString("dep_veh_end_time").equals("")
								? sdf1.format(rs.getTimestamp("dep_veh_end_time"))
								: null;
								vehicleAllotmentModel1.setDepEndTime(endTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						String reqTime = !rs.getString("dep_time_of_report").equals("")
								? sdf1.format(rs.getTimestamp("dep_time_of_report"))
								: null;
								vehicleAllotmentModel1.setDepTimeOfReport(reqTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
					return vehicleAllotmentModel1;
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(vehicleAllotmentModel);
		vehicleAllotmentModel.addAll(vehicleAllotmentModelDeparture);
		resp.put("Data", vehicleAllotmentModel);
		return resp;
	}


	@Override
	public Map<String, Object> getCheckinSlipDetails(Map<String, Object> params) {SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	String sql = adminQueries.getProperty("viewCheckInSlip");
	String query1 = adminQueries.getProperty("viewCheckInSlipIndividualy");
	String query2 = adminQueries.getProperty("countForCheckInSlip");
	String ghId = (String) params.get("cId");
	String blockId = (String) params.get("gId");
	String reqId = (String) params.get("bookingId");
	Map<String, Object> resp = new HashMap<>();
	List<CheckInReport> checkInReport = new ArrayList<>();

	List<Object> paramsList = new ArrayList<>();
	List<Object> paramsList1 = new ArrayList<>();
	if (!ghId.isEmpty()) {
	    query2 += " and gh_id = ?";
	    sql += " and gh_id = ?";
	    paramsList.add(ghId);
	    paramsList1.add(ghId);
	}
	if (!blockId.isEmpty()) {
	    query2 += " and block_id = ?";
	    sql += " and block_id = ?";
	    paramsList.add(blockId);
	    paramsList1.add(blockId);
	}
	if (!reqId.isEmpty()) {
	    query2 += " and req_id = ?";
	    sql += " and req_id = ?";
	    paramsList.add(reqId);
	    paramsList1.add(reqId);
	}

	sql += " GROUP BY req_id ,gh_id)a order by a.arrival_date desc";

	int pageNo = (int) params.get("pageNo");
	if (pageNo != 0) {
	    int offset = (pageNo - 1) * Constants.LIMIT;
	    sql += " LIMIT ? OFFSET ?";
	    paramsList.add(Constants.LIMIT);
	    paramsList.add(offset);
	}
	Integer pageCount;
	try {
	    pageCount = jdbcTemplate.queryForObject(query2, paramsList1.toArray(), Integer.class);
	} catch (Exception e) {
	    pageCount = 0;
	    e.printStackTrace();
	}

	try {
	    checkInReport = jdbcTemplate.query(sql, paramsList.toArray(), new RowMapper<CheckInReport>() {
	        @Override
	        public CheckInReport mapRow(ResultSet rs, int rownumber) throws SQLException {
	            CheckInReport checkInReport1 = new CheckInReport();

	            checkInReport1.setReqId(rs.getString("req_id"));
	            checkInReport1.setTrIdList(rs.getString("tr_id_list"));

	            String[] trIdlist = checkInReport1.getTrIdList().split(",");
	            List<CheckInSlip> listSlip = new ArrayList<>();
	            for (String trId : trIdlist) {
	                try {
	                    jdbcTemplate.queryForObject(query1, new Object[]{trId}, (ResultSet rsRm, int rownumberRm) -> {
	                        CheckInSlip checkInSlip1 = new CheckInSlip();

	                        checkInSlip1.setReqId(rsRm.getString("req_id"));
	                        checkInSlip1.setName(rsRm.getString("name"));
	                        checkInSlip1.setTrId(rsRm.getString("tr_id"));
	                        try {
	                            String strtTime = rsRm.getString("arrival_time_date") != null && !rsRm.getString("arrival_time_date").isEmpty()
	                                    ? sdf1.format(rsRm.getTimestamp("arrival_time_date"))
	                                    : null;
	                            checkInSlip1.setArrivalDate(strtTime);
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                        }

	                        try {
	                            String endTime = rsRm.getString("departure_time_date") != null && !rsRm.getString("departure_time_date").isEmpty()
	                                    ? sdf1.format(rsRm.getTimestamp("departure_time_date"))
	                                    : "NA";
	                            checkInSlip1.setDepartureDate(endTime);
	                        } catch (Exception e) {
	                            e.printStackTrace();
	                            checkInSlip1.setDepartureDate("NA");
	                        }
	                        listSlip.add(checkInSlip1);
	                        return checkInSlip1;
	                    });
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	            checkInReport1.setCheckInSlip(listSlip);
	            return checkInReport1;
	        }
	    });
	} catch (Exception e) {
	    e.printStackTrace();
	}

	Pagination pagination = new Pagination();
	pagination.setData(checkInReport);
	pagination.setTotalPages(pageCount);
	resp.put("Data", pagination);
	return resp;}
	@Override
	public Map<String, Object> viewIndividualCheckinSlipDetails(Map<String, Object> params) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String query1 = adminQueries.getProperty("viewCheckInSlipIndividualy");
		Map<String, Object> resp = new HashMap<String, Object>();
		String trId=(String) params.get("trId");
		System.out.println(trId);
		IndividualCheckInDetails checkInSlip = new IndividualCheckInDetails();
		try {
			checkInSlip = jdbcTemplate.queryForObject(query1, new Object[] { trId },
					(ResultSet rsRm, int rownumberRm) -> {

						IndividualCheckInDetails checkInSlip1 = new IndividualCheckInDetails();

						checkInSlip1.setReqId(rsRm.getString("req_id"));
						checkInSlip1.setTrId(rsRm.getString("tr_id"));
						checkInSlip1.setName(rsRm.getString("name"));
						checkInSlip1.setRegisterNo(rsRm.getString("roll_no"));
						checkInSlip1.setDepartment(rsRm.getString("department"));
						checkInSlip1.setDesignation(rsRm.getString("designation"));
						checkInSlip1.setNoOfAdults(rsRm.getString("adults"));
						checkInSlip1.setNochildrenBelow(rsRm.getString("no_of_children_below12"));
						checkInSlip1.setRoom(rsRm.getString("room_list"));
						checkInSlip1.setAdvanceAmount(rsRm.getString("advance_amt"));
						checkInSlip1.setAdvanceBilldId(rsRm.getString("bill_id"));
						try {
							String strtTime = !rsRm.getString("arrival_time_date").equals("")
									? sdf1.format(rsRm.getTimestamp("arrival_time_date"))
									: null;
									checkInSlip1.setArrivalTime(strtTime);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							String endTime = !rsRm.getString("departure_time_date").equals("")
									? sdf1.format(rsRm.getTimestamp("departure_time_date"))
									: null;
									checkInSlip1.setDepartureTime(endTime);
						} catch (Exception e) {
							checkInSlip1.setDepartureTime("NA");
//							e.printStackTrace();
						}

						return checkInSlip1;
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.put("data", checkInSlip);
		return resp;
	}
	
	@Override
	@Transactional
	public Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String sql = adminQueries.getProperty("saveConfernceBookingDetails");
		String query1 = adminQueries.getProperty("saveConfernceRequest");
		String query2 = adminQueries.getProperty("saveConfrenceBill");
		String query3 = adminQueries.getProperty("getAmountAndCapacity");
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		Integer id = null;
		Integer confId = null;
		Integer confBillId = null;
		Date arrivalDate=null;
		Date departureDate=null;
		
		
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			departureDate = !data.getToDate().equals("") ? sdf1.parse(data.getToDate())
					: null;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			arrivalDate = !data.getFromDate().equals("") ? sdf1.parse(data.getFromDate())
					: null;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		 jdbcTemplate.query(query3, new RowMapper<ConfrenceHallBooking>() {
				@Override
				public ConfrenceHallBooking mapRow(ResultSet rs, int rownumber) throws SQLException {
					data.setAmount(rs.getString("charge"));
					data.setCapacity(rs.getString("capacity"));
					return data;
				}
			},data.getConfCatId(),data.getConfUseId(),data.getBlockId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			id = get_seqID("req_id");
			update_seqID("req_id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			confId = get_seqID("conf_req_id");
			update_seqID("conf_req_id", confId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			confBillId = get_seqID("conf_bill_id");
			update_seqID("conf_bill_id", confBillId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Integer insert=null;
		Integer status=null;
		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {
			
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId", rs.getString("gh_id"));
				res.put("bId", rs.getString("block_id"));
				res.put("bName", rs.getString("block_name"));
				res.put("sName", rs.getString("short"));
				return res;
			}
		}, data.getBlockId());
		if (blockList == null || blockList.size() == 0) {
			return null;
		}
		String reqId = "H" + blockList.get(0).get("sName") + id.toString();
		try {
			insert = jdbcTemplate.update(sql,
					new Object[] { reqId, data.getCityId(), arrivalDate, departureDate, "REQ", "GH", data.getAddress(),
							"CON", data.getBlockId(), data.getCategoryType(), data.getMobileNo(), data.getMobileNo(),
							"0", "0", "0" });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		if (insert.equals(1)) {
			try {
				status = jdbcTemplate.update(query1,
						new Object[] { confId, data.getConfCatId(), arrivalDate, departureDate, data.getConfUseId(),
								"1", data.getName(), data.getAddress(), data.getCityId(), data.getBlockId(),
								data.getCapacity(), reqId });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		}

		try {
			status = jdbcTemplate.update(query2, new Object[] { confBillId, reqId, data.getAmount(), "NB",
					data.getCityId(), "0", data.getAmount(), data.getModeOfPayment() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}

			resp.put("status", true);
			resp.put("msg", "Application Submitted With \n Booking ID:"+reqId);
			
			
			
		return resp;
	}

	@Override
	public Map<String, Object> getConfrenceHallDetails(Map<String, Object> params) {
		String blockId=(String) params.get("gId");
		String sql = adminQueries.getProperty("getConfrenceRoomDetails");
		List<ConfrenceHallDetails> confrenceHallDetails = new ArrayList<>();
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			confrenceHallDetails = jdbcTemplate.query(sql, new RowMapper<ConfrenceHallDetails>() {
				@Override
				public ConfrenceHallDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					ConfrenceHallDetails confrenceHallDetails1 = new ConfrenceHallDetails();
					confrenceHallDetails1.setConfCatId(rs.getString("conf_cat_id"));
					confrenceHallDetails1.setConfCatName(rs.getString("conf_cat_desc"));
					return confrenceHallDetails1;
				}
			},blockId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.put("status", true);
		resp.put("data", confrenceHallDetails);
		return resp;
	}
	
	@Override
	public Map<String, Object> getConfrenceTimePeriodDetails(Map<String, Object> params) {
		String ghId=(String) params.get("gId");
		String conCatId=(String) params.get("conCatId");
		String sql = adminQueries.getProperty("getConfrenceUseDetails");
		List<ConfrenceHallDetails> confrenceHallDetails = new ArrayList<>();
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			confrenceHallDetails = jdbcTemplate.query(sql, new RowMapper<ConfrenceHallDetails>() {
				@Override
				public ConfrenceHallDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
					ConfrenceHallDetails confrenceHallDetails1 = new ConfrenceHallDetails();
					confrenceHallDetails1.setConfUseId(rs.getString("conf_use_id"));
					confrenceHallDetails1.setConfUseName(rs.getString("conf_use_desc"));
					return confrenceHallDetails1;
				}
			},conCatId,ghId);
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("status", false);
			resp.put("data", "something went wrong");
			return resp;
		}
		resp.put("status", true);
		resp.put("data", confrenceHallDetails);
		return resp;
	}
	
	@Override
	public Map<String, Object> getConfrenceAmount(Map<String, Object> params) {
		String conUseId=(String) params.get("conUseId");
		String conCatId=(String) params.get("conCatId");
		String blockId=(String) params.get("gId");
		String sql = adminQueries.getProperty("getConfrenceAmount");
		Map<String, Object> resp = new HashMap<String, Object>();
		String amount = null;
		try {
			amount = jdbcTemplate.queryForObject(sql, new Object[] { conCatId,conUseId,blockId }, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.put("status", true);
		resp.put("data", amount);
		return resp;
	}
	@Override
	public Integer checkHallAviavleAtThisMomentOrNot(String fromDate, String toDate,String confCatId,String cityId) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String sql = adminQueries.getProperty("getVacancy");

		Date arrivalDate=null;
		Date departureDate=null;
		Map<String, Object> resp = new HashMap<String, Object>();
		try {
			arrivalDate = !fromDate.equals("") ? sdf1.parse(fromDate)
					: null;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try {
			departureDate = !toDate.equals("") ? sdf1.parse(toDate)
					: null;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Integer vacancy = null;
		try {
			vacancy = jdbcTemplate.queryForObject(sql, new Object[] { arrivalDate,departureDate,confCatId,cityId }, Integer.class);
		} catch (Exception e) {
			vacancy=0;
			e.printStackTrace();
		}
		
		return vacancy;
	}
	
	@Override
	public Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params) {
	    List<ConfrenceHall> data = new ArrayList<>();
	    String orderByCondition = " ORDER BY gh.req_date DESC";
	    String query = adminQueries.getProperty("getListOfConfrenceRequestAdmin");
	    StringBuilder whereCondition = new StringBuilder(" AND 1=1 ");
	    List<Object> queryParams = new ArrayList<>();

	    String cityId = (String) params.get("cId");
	    String ghId = (String) params.get("gId");
	    String bookingId = (String) params.get("bookingId");
	    String bookingDate = (String) params.get("bookingDate");
	    String mobNo = (String) params.get("mobNo");
	    String name = (String) params.get("name");
	    String blockid = (String) params.get("blockid");
	    String roomType = (String) params.get("roomType");

	    // Parameter handling
	    if (cityId != null && !cityId.isEmpty()) {
	        whereCondition.append(" AND gh.gh_id=?");
	        queryParams.add(cityId);
	    }
	    if (ghId != null && !ghId.isEmpty()) {
	        whereCondition.append(" AND gh.block_id=?");
	        queryParams.add(ghId);
	    }
	    if (bookingId != null && !bookingId.isEmpty()) {
	        whereCondition.append(" AND gh.req_id=?");
	        queryParams.add(bookingId);
	    }
	    if (bookingDate != null && !bookingDate.isEmpty()) {
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date date = sdf.parse(bookingDate);
	            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	            bookingDate = sdf1.format(date);
	            whereCondition.append(" AND DATE(gh.req_date) = CAST(? AS DATE)");
	            queryParams.add(bookingDate);
	        } catch (ParseException e) {
	            throw new RuntimeException("Date parsing error", e);
	        }
	    }
	    if (mobNo != null && !mobNo.isEmpty()) {
	        whereCondition.append(" AND gh.mobile_no=?");
	        queryParams.add(mobNo);
	    }
	    if (name != null && !name.isEmpty()) {
	        whereCondition.append(" AND b.name ILIKE ?");
	        queryParams.add("%" + name + "%");
	    }

	    // Final query construction
	    query = query + whereCondition.toString() + orderByCondition;
	    System.out.println("Query: " + query);

	    // Query execution with parameterized query
	    Map<String, Object> resp = new HashMap<>();
	    try {
	        data = jdbcTemplate.query(query, queryParams.toArray(), new RowMapper<ConfrenceHall>() {
	            @Override
	            public ConfrenceHall mapRow(ResultSet rs, int rowNumber) throws SQLException {
	                ConfrenceHall data1 = new ConfrenceHall();
	                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	                try {
	                    String bookingDt = rs.getDate("req_date") != null ? sdf.format(rs.getDate("req_date")) : null;
	                    String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
	                    String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
	                    data1.setGuestHouseName(rs.getString("block_name"));
	                    data1.setCityName(rs.getString("gh_name"));
	                    data1.setBillId(rs.getString("conf_bill_id"));
	                    data1.setBookingId(rs.getString("req_id"));
	                    data1.setBookingDt(bookingDt);
	                    data1.setName(rs.getString("name"));
	                    data1.setBlockId(rs.getString("block_id"));
	                    data1.setFromDate(frDt);
	                    data1.setToDate(toDt);
	                    data1.setBlockId(rs.getString("block_id"));
	                    data1.setBillStatus(rs.getString("bill_status"));
	                    data1.setStatus(rs.getString("status"));
	                    data1.setAdditonalCharge(rs.getString("additionalcharge"));
	                    data1.setAdvanceAmount(rs.getString("advance_amount"));
	                    data1.setModeOfPayment(rs.getString("mode_of_payment"));
	                    data1.setCharge(rs.getString("charge"));
	                    data1.setConfrenceDesc(rs.getString("conf_cat_desc"));
	                    data1.setConfreqnceUseDesc(rs.getString("conf_use_desc"));
	                    data1.setConfrenceId(rs.getString("conf_cat_id"));
	                    data1.setConfreqnceUseId(rs.getString("conf_use_id"));
	                    data1.setCapacity(rs.getString("capacity"));
	                    int total = Integer.parseInt(rs.getString("charge"));
	                    int additionalCharge = Integer.parseInt(rs.getString("additionalcharge"));
	                    int amount = Integer.parseInt(rs.getString("advance_amount"));
	                    data1.setPendingAmount((total + additionalCharge) - amount);
	                    String categoryDesc = "";
	                    String category = rs.getString("category_type");
	                    if ("C1".equals(category)) categoryDesc = "Category 1";
	                    if ("C2".equals(category)) categoryDesc = "Category 2";
	                    if ("C3".equals(category)) categoryDesc = "Category 3";
	                    if ("C4".equals(category)) categoryDesc = "Category 4";
	                    data1.setCategory(categoryDesc);
	                    data1.setMobile(rs.getString("mobile_no"));
	                    String status = rs.getString("status");
	                    data1.setAddress(rs.getString("address"));
	                    String statusDesc = "";
	                    if ("REQ".equals(status)) statusDesc = "Not taken up for processing";
	                    if ("ALO".equals(status)) statusDesc = "Allotment";
	                    if ("PRO".equals(status)) statusDesc = "Approved";
	                    if ("CAN".equals(status)) statusDesc = "Cancelled";
	                    data1.setStatusDesc(statusDesc);
	                    data1.setTypeOfRoom("Conference");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                return data1;
	            }
	        });
	        resp.put("status", true);
	        resp.put("data", data);
	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.put("status", false);
	        resp.put("message", "Error while fetching data.");
	    }
	    System.out.println("getConfrenceRequestDetails query: " + query);
	    return resp;
	}

	@Override
	public Map<String, Object> saveBillingDetails(Map<String, Object> params) {
		String additionalcharge=(String)params.get("additonalCharges");
		String reqId=(String)params.get("reqId");
		String mode=(String)params.get("status");
		String query = adminQueries.getProperty("updateConfrenceBalance");
		 Map<String, Object> result=new HashMap<>();
		 String status="B";
		 if(mode.equals("P")){
			 status="FB";
		 }
		 
		Integer resp=null;
		try {
			resp =jdbcTemplate.update(query, new Object[] {status,additionalcharge,reqId});
			System.out.println(query);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(resp.equals(1)) {
			result.put("status", true);
			result.put("msg", "Bill generated \n successful");
		}else {
			result.put("status", false);
			result.put("msg", "something went wrong try after some time");
		}
		
		return result;
	}
	@Override
	@Transactional
	public Map<String, Object> editConfrenceDetails(Map<String, Object> params) {
		String reqId=(String)params.get("reqId");
		String address=(String)params.get("address");
		String name=(String)params.get("name");
		String mobileNo=(String)params.get("mobileNo");
		String capacity=(String)params.get("capacity");
		String query = adminQueries.getProperty("editConfrenceDetails");
		String query1 = adminQueries.getProperty("editGadDetails");
		Integer resp=null;
		Map<String, Object> result=new HashMap<>();
		try {
			resp =jdbcTemplate.update(query, new Object[] {name,address,capacity,reqId});
			System.out.println(query);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		try {
			resp =jdbcTemplate.update(query1, new Object[] {address,mobileNo,reqId});
			System.out.println(query);
		}catch(Exception e) {
			throw new RuntimeException("Not Working");
		}
		result.put("status", true);
		result.put("msg", "Edit conference hall \n Successful");
		return result;
	}
	@Override
	@Transactional
	public Map<String, Object> cancelConfrenceDetails(String reqId) {
		String query = adminQueries.getProperty("updateCancelgad");
		String query1 = adminQueries.getProperty("updateCancelConfrenceBalance");
		String query2 = adminQueries.getProperty("updateCancelConfrenceRequest");
		 Map<String, Object> result=new HashMap<>();
		 try {
				jdbcTemplate.update(query, new Object[] {reqId});
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not working");
			}
			try {
				jdbcTemplate.update(query1, new Object[] {reqId});
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not working");
			}
			try {
				jdbcTemplate.update(query2, new Object[] {reqId});
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not working");
			}
			result.put("status", true);
			result.put("msg", "Application Cancelled Successfully");
			
			
		return result;
	}
	@Override
	@Transactional
	public Map<String, Object> saveConfrenceRecieptDetails(Map<String, Object> params) {
		String query2 = adminQueries.getProperty("saveInConfReciept");
		String query3 = adminQueries.getProperty("updateConfrenceBillStatus");
		String sql1 = adminQueries.getProperty("createReceiptPaymentMode");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String reqId=(String) params.get("paymentReqId");
		String billId=(String) params.get("billId");
		String amount=(String) params.get("amount");
		String ghId=(String) params.get("ghId");
		String statusForPartialFull=(String) params.get("status");
		Map<String, Double> model=(Map<String, Double>) params.get("mode");
		String transactionNo=(String) params.get("transactionNo");
		String receiptStatus="A";
		if(statusForPartialFull.equals("F")) {
			receiptStatus="F";	
		}
		 Map<String, Object> result=new HashMap<>();
		 Integer id=null;
		 try {
				id = get_seqID("rec_id");
				update_seqID("rec_id", id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		 try {
			 jdbcTemplate.update(query2, id, billId,amount,receiptStatus, ghId, "", reqId);
		 }catch(Exception e) {
			 e.printStackTrace();
			 throw new RuntimeException("Not Working");
		 }
		
		 try {
				jdbcTemplate.update(query3, new Object[] {statusForPartialFull,billId});
				System.out.println(query3);
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
		 
		 
		 for (Map.Entry<String, Double> entry : model.entrySet()) {
				Integer rpmId = null;
				try {
					rpmId = get_seqID("rpm_id");
					update_seqID("rpm_id", rpmId);
				} catch (Exception e) {
					e.printStackTrace();
					result.put("status", false);
					result.put("msg", "Some thing went Wrong");
					return result;
				}
				if (entry.getKey().equalsIgnoreCase("CASH")) {
					try {
						jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(), "", "", "");
					} catch (Exception e) {
						throw new RuntimeException("Not Working");
					}
				} else if (entry.getKey().equalsIgnoreCase("POS")) {
					try {
						jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), entry.getValue(), transactionNo, "", "");
					} catch (Exception e) {
						throw new RuntimeException("Not Working");
					}
				} else {
					try {
						String q = approverQueries.getProperty("getConfOnlinePaymentDetails");
						PGModel pg = jdbcTemplate.queryForObject(q, new Object[] {billId}, (rs, rn) -> {
							PGModel p = new PGModel();
							p.setOrderId(rs.getString("order_id"));
							p.setAmount(rs.getString("amount"));
							p.setBankRefNo(rs.getString("bank_ref_no"));
							return p;
						});
						jdbcTemplate.update(sql1, rpmId, id, entry.getKey(), Double.parseDouble(pg.getAmount()),
								"", pg.getOrderId(), "");
					} catch (Exception e) {
						throw new RuntimeException("Not Working");
					}
				}
			}
		 
		
		result.put("status", true);
		result.put("msg", "Payment accepted Successfully");
		ConfrenceHallBooking data = jdbcTemplate.queryForObject(adminQueries.getProperty("getConferenceSmsDetails"),
				(rs, rn) -> {
					ConfrenceHallBooking g = new ConfrenceHallBooking();
					g.setReqId(reqId);
					g.setName(rs.getString("name"));
					g.setMobileNo(rs.getString("mobile_no"));
					
					try {
						String strtTime = !rs.getString("fromdate").equals("")
								? sdf.format(rs.getTimestamp("fromdate"))
								: null;
								g.setFromDate(strtTime);
					} catch (Exception e) {
						e.printStackTrace();
					}
					g.setAdvanceAmount(rs.getString("advance_amount"));
					g.setCityId(rs.getString("gh_name"));
					g.setBlockId(rs.getString("block_name"));
					g.setConfCatName(rs.getString("conf_cat_desc"));
					return g;
				}, reqId);
		try {
			String templateId="";
			if (receiptStatus.equals("A")) {
				 templateId = "1107168975520027621";
				String subject = "Dear " + data.getName() + ", amount of Rs " + data.getAdvanceAmount()
						+ " has been successfully received towards your booking of " + data.getConfCatName() + " in "
						+ data.getBlockId() + ", " + data.getCityId() + " on " + data.getFromDate() + " having"
						+ " Booking ID " + data.getReqId() + " - Odisha Home Dept";
				System.out.println(subject);
				if (data.getMobileNo() != null) {
					if (!data.getMobileNo().isEmpty()) {
						smsSend.sendSMS(data.getMobileNo(), subject, templateId);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		
		
		
		return result;
	}
	@Override
	public Map<String, Object> getReceiptDetailsIndividualy(String billId) {
		String sql1 = adminQueries.getProperty("getPaymentType");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		List<RecipetPaymentMode> nocModel=new ArrayList<>();
		Map<String, Object> data=new HashMap<String,Object>();
		
		try {
			nocModel = jdbcTemplate.query(sql1, new RowMapper<RecipetPaymentMode>() {
				@Override
				public RecipetPaymentMode mapRow(ResultSet rs, int rownumber) throws SQLException {
					RecipetPaymentMode noc = new RecipetPaymentMode();
					noc.setModeOfpayment(rs.getString("mode_of_payment"));
					noc.setAmount(rs.getString("amount"));
					noc.setPso_tr_num(rs.getString("pos_tr_num"));
					noc.setStatus(rs.getString("status"));
					noc.setRecId(rs.getString("rec_id"));
					noc.setRecStatus(rs.getString("rec_status"));
					String receiptDate = rs.getDate("receipt_date") != null ? sdf1.format(rs.getTimestamp("receipt_date")) : null;
					String billDate = rs.getDate("bill_date") != null ? sdf1.format(rs.getTimestamp("bill_date")) : null;
					String fromDate = rs.getDate("fromdate") != null ? sdf1.format(rs.getTimestamp("fromdate")) : null;
					String toDate = rs.getDate("todate") != null ? sdf1.format(rs.getTimestamp("todate")) : null;
					noc.setReceiptDate(receiptDate);
					noc.setBillDate(billDate);
					noc.setCityName(rs.getString("gh_name"));
					noc.setGuestHouseName(rs.getString("block_name"));
					noc.setGuestHouseContactNum(rs.getString("contact_num"));
					noc.setGuestHouseMail(rs.getString("gh_mail"));
					noc.setName(rs.getString("name"));
					noc.setGuestHouseAddress(rs.getString("gh_address"));
					noc.setGuestContact(rs.getString("contact_num"));
					noc.setGuestEmail(rs.getString("gh_mail"));
					noc.setBookingId(rs.getString("req_id"));
					noc.setCategory(rs.getString("category"));
					noc.setGuestAddress(rs.getString("guest_address"));
					noc.setAdvancedAmount(rs.getString("advance_amount"));
					noc.setFromDate(fromDate);
					noc.setToDate(toDate);
					noc.setConfType(rs.getString("conf_cat_desc"));
					noc.setCongTimePeriod(rs.getString("conf_use_desc"));
					noc.setTotalAmountOfConf(rs.getString("charge"));
					noc.setAddtionalCharges(rs.getString("additionalcharge"));
					return noc;
				}
			},billId);
		} catch (Exception e) {
			e.printStackTrace();
			data.put("status", false);
			data.put("data", "something went wrong");
			return data;
		}
		System.out.println(sql1);
		data.put("status", true);
		data.put("data", nocModel);
		System.out.println(" getReceiptDetailsIndividualy  "+sql1+"  "+billId);
		System.out.println(" data in admindaoimpl "+nocModel);
		return data;
	}
	@Override
	public Integer getTotalRoomRent(Map<String, Object> params) {
		String blockId = (String) params.get("gId");
		String req_id = (String) params.get("req_id");
		String sql = adminQueries.getProperty("totalRoomRent");
		Integer roomRent = jdbcTemplate.queryForObject(sql,new Object[]{req_id,blockId},Integer.class);
		return roomRent;
	}
	@Override
	public Map<String, Object> deleteVehicleDetails(String reqId) {
		return null;
	}
	@Override
	public Integer noOfGuestOnThisBookingId(String reqId) {
		String query1 = adminQueries.getProperty("selectGuestOnReqId");
		Integer noOfGst=null;
		try {
			noOfGst = jdbcTemplate.queryForObject(query1, new Object[] { reqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return noOfGst;
	}
	@Override
	public boolean saveExtraGuestNumber(String reqId, String extraGuest) {
		String query1 = adminQueries.getProperty("setNoOfExtraGuest");
		try {
			 jdbcTemplate.update(query1, new Object[] { Integer.valueOf(extraGuest),reqId });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public Integer getBeforExistExtraGuest(String reqId) {
		String query1 = adminQueries.getProperty("getNoOfGuest");
		Integer noOfGst=null;
		try {
			noOfGst = jdbcTemplate.queryForObject(query1, new Object[] { reqId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return noOfGst;
	}
	@Override
	public GuestDetails getGuestDetailsforMsg(String blockId, String reqId) {
		String q1=adminQueries.getProperty("getGuestForSms");
		GuestDetails guestDetails = null;
		try {
			guestDetails = jdbcTemplate.queryForObject(q1, (rs, rn) -> {
				GuestDetails noc = new GuestDetails();
				noc.setName(rs.getString("name"));
				noc.setEmailId(rs.getString("email_id"));
				noc.setMobileNo(rs.getString("mobile_no"));
				noc.setBlockId(rs.getString("block_name"));
				noc.setGuestId(rs.getString("gh_name"));
				return noc;
			}, reqId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return guestDetails;
	}
	@Override
	public void sendCheckOutSMS(String templateId, String subject, String emailId, String mobileNo) {
		
		// mail
//		try {
//			if (emailId != null) {
//				if (!emailId.isEmpty()) {
//					mail.sendMimeMail(emailId, "For Feedback Expreince",
//							subject);
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send..");
//		}
		
		try {
			if (mobileNo!= null) {
				if (!mobileNo.isEmpty()) {
					smsSend.sendSMS(mobileNo,subject, templateId);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
	}

	@Override
	public List<ProcessModeration> allProcessApplicationListmoderatorView(HttpServletRequest request) {
		String sql = adminQueries.getProperty("AllotTimeApproverList");
		System.out.println("value of the city   "+request.getParameter("cityName"));
		List<ProcessModeration> alldetailsValue = null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			sql = sql + " AND a.gh_id='" + request.getParameter("cityName") + "'";
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			sql = sql + " AND a.block_id='" + request.getParameter("guesthouseName") + "'";

		sql = sql
				+" GROUP BY a.block_id,b.name,b.designation,b.department,a.req_id,a.status,a.vc_id,a.req_date,a.quota_type,a.from_date,a.to_date,d.during_stay_conveancy"
				  +",v.ftno,v1.ftno,v.arrival_time,v1.arrival_time,v.arrival_place,v1.arrival_place,d.address"
				  +" ORDER BY a.vc_id, a.req_date";
			System.out.println(sql);	
		try {
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration response = new ProcessModeration();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					Date bookDate = rs.getDate("req_date") != null ? rs.getDate("req_date") : null;
					Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
					Date ToDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
					response.setBlockId(rs.getString(1));
					response.setBookingId(rs.getString("req_id"));
					response.setStatus(rs.getString("status"));
					response.setVcId(rs.getString("vc_id"));
					response.setReqDate(sdf.format(bookDate));
					response.setName(rs.getString("concatenated_string"));
					response.setRoomRent(rs.getString("room_rents"));
					response.setRoomNo(rs.getString("room_nos"));
					response.setGoSignDesig(rs.getString("during_stay_conveancy"));
					response.setOfficeorderno(rs.getString("Arrival"));
					response.setPeriodOfStay(rs.getString("departure"));
					String category_Desc = "";
					String categoryType = rs.getString("quota_type");
					if (categoryType.equals("C1")) {
						category_Desc = "Category 1";
					}
					if (categoryType.equals("C2")) {
						category_Desc = "Category 2";
					}
					if (categoryType.equals("C3")) {
						category_Desc = "Category 3";
					}
					if (categoryType.equals("C4")) {
						category_Desc = "Category 4";
					}
					response.setQuotatype(category_Desc);
					response.setType(rs.getString(7));
					response.setFrmDt(sdf1.format(fromDate));
					response.setToDt(sdf1.format(ToDate));
					return response;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();

		}
		return alldetailsValue;
	}

	@Override
	public List<BookingResponseModel> getAllotTimeAllConveyanceDataForApprove(HttpServletRequest request) {
		List<BookingResponseModel> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";
		String sql = adminQueries.getProperty("AllotConveyanceListSQL");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals("")) {
			sql = sql + " AND a.gh_Id='" + request.getParameter("cityName") + "'";
		}
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals("")) {
			sql = sql + " AND a.block_id='" + request.getParameter("guesthouseName") + "'";
		}
		sql = sql + " order by a.rid, req_date";
		System.out.println(sql);
		try {
			return alldetailsValue = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					BookingResponseModel response = new BookingResponseModel();
					response.setCityId(rs.getString("gh_id"));
					response.setGhId(rs.getString("block_id"));
					response.setTypeOfRoom(rs.getString("roomtype"));
					response.setBookingId(rs.getString("rid"));
					response.setBookingDt(rs.getTimestamp("req_date")==null?"":sdf.format(rs.getTimestamp("req_date")));
					response.setArrivalDate(rs.getTimestamp("arrival_time")==null?"":sdf.format(rs.getTimestamp("arrival_time")));
					response.setDepartureDate(rs.getTimestamp("departure_time")==null?"":sdf.format(rs.getTimestamp("departure_time")));
					response.setName(rs.getString("concatenated_string"));
					response.setModId(rs.getString("arrival"));
					response.setMrrId(rs.getString("departure"));
					response.setPrevOrderDt(rs.getString("case"));
					String Category_Desc = "";
					if (rs.getString("quota_type").equalsIgnoreCase("C1")) {
						Category_Desc = "Category 1";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C2")) {
						Category_Desc = "Category 2";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C3")) {
						Category_Desc = "Category 3";
					}
					if (rs.getString("quota_type").equalsIgnoreCase("C4")) {
						Category_Desc = "Category 4";
					}
					response.setCategory(Category_Desc);
					response.setStatus(rs.getString("status"));
					return response;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alldetailsValue;
	}
	@Override
	public List<BookingResponseModel> getAllCancelDataForApproveAllotTime(Map<String, Object> params) {
		List<BookingResponseModel> data;
		
		String orderByCondition = " GROUP BY a.req_id,a.req_date,d.name,a.from_date,n.from_date,n.req_id,a.to_date,a.mobile_no,a.email_id,a.status,a.can_status,c.from_date,c.to_date,c.req_id,"
				+ "concatenated_string ORDER BY a.from_date DESC";
		String query = adminQueries.getProperty("getCancelListForApproverSQLAlo");

		String whereCondition = " WHERE (a.can_status='ACAN'"
				+ " OR (c.status='ALO')) AND DATE(NOW()) <= a.to_date::date";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND a.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND a.block_id='" + ghId + "'";
		}
		query = query + whereCondition + orderByCondition;
		System.out.println(query);
		data = jdbcTemplate.query(query, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel data = new BookingResponseModel();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				try {
					Date bookDate = rs.getDate(2) != null ? rs.getTimestamp(2) : null;
					String frDt = rs.getDate(4) != null ? sdf1.format(rs.getTimestamp(4)) : null;
					String toDt = rs.getDate(5) != null ? sdf1.format(rs.getTimestamp(5)) : null;
					data.setBookingId(rs.getString(1));
					data.setBookingDt(sdf1.format(bookDate));
					data.setName(rs.getString(3));
					data.setNoOfChildren(rs.getString("concatenated_string"));
					data.setFromDate(frDt);
					data.setToDate(toDt);
					data.setMobile(rs.getString(6));
					data.setEmail(rs.getString(7));
					data.setNoRooms(rs.getString("room_no"));
					String status = rs.getString(8);
					String status_desc = "";
					if (status.equals("REQ")) {
						status_desc = " Not taken up for processing";
					}
					if (status.equals("CAN")) {
						status_desc = "Cancelled";
					}
					if (status.equalsIgnoreCase("MOD")) {
						status_desc = "Under processing";
					}
					if (status.equalsIgnoreCase("PRO")) {
						status_desc = "Approved";
					}
					if (status.equalsIgnoreCase("ALO")) {
						status_desc = "Allotted";
					}
					if (status.equals("CIN")) {
						status_desc = "Check In";
					}
					if (status.equals("COUT")) {
						status_desc = "Check Out";
					}
					if (status.equals("RCAN") || status.equals("MCAN")) {
						status_desc = "Cancelled";
					}						
					data.setStatusDesc(status_desc);
					
					int canStatus = rs.getInt("partial_c");
					String can_status_desc = "";
					if (canStatus == 1) {
						can_status_desc = "PARTIAL";
					} else {
						can_status_desc = "FULL";
					}
					data.setStatus(can_status_desc);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return data;
			}
		});
		return data;
	}
	@Override
	public List<BookingResponseModel> getAllModifiedDataForApproveAllotTime(Map<String, Object> params) {
		String query = adminQueries.getProperty("getModifiedListAlloTime");
		String whereCondition = "";
		String grpBy=" GROUP BY g.req_id,"
				+ "g.req_date,g.from_date,g.to_date,g.category_type,g.mobile_no,gs.name,gs.designation,gs.department,g.status,s.subject,s.prev_order_no,s.prev_order_date";
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition += " AND g.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition += " AND g.block_id='" + ghId + "'";
		}

		query += whereCondition;
		query += grpBy;
		System.out.println(query);
		List<BookingResponseModel> data =null;
		try {
				data = jdbcTemplate.query(query, (rs, rowNum) -> {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				Date bookDate = rs.getDate("req_date") != null ? rs.getTimestamp("req_date") : null;
				Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
				Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
				Date prevDt = rs.getDate("prev_order_date") != null ? rs.getTimestamp("prev_order_date") : null;
				BookingResponseModel bookingResponseModel = new BookingResponseModel();
				String category_Desc = "";
				String categoryType = rs.getString("category_type");
				if (categoryType.equals("C1")) {
					category_Desc = "Category 1";
				}
				if (categoryType.equals("C2")) {
					category_Desc = "Category 2";
				}
				if (categoryType.equals("C3")) {
					category_Desc = "Category 3";
				}
				if (categoryType.equals("C4")) {
					category_Desc = "Category 4";
				}
				bookingResponseModel.setPrevOrderDt(sdf.format(prevDt));
				bookingResponseModel.setCategory((category_Desc));
				bookingResponseModel.setBookingId(rs.getString("req_id"));
				bookingResponseModel.setFromDate(sdf1.format(fromDate));
				bookingResponseModel.setToDate(sdf1.format(toDate));
				bookingResponseModel.setBookingDt(sdf1.format(bookDate));
				bookingResponseModel.setName(rs.getString("name"));
				bookingResponseModel.setMobile(rs.getString("mobile_no"));
				bookingResponseModel.setStatus(rs.getString("status"));
				bookingResponseModel.setRemarks(rs.getString("subject"));
				bookingResponseModel.setSubmittedOn(rs.getString("prev_order_no"));
				bookingResponseModel.setPartialCancelFromDt(rs.getString("prev_room_no"));
				bookingResponseModel.setConfBillId(rs.getString("prev_room_rent"));
				bookingResponseModel.setConfUseId(rs.getString("room_rent"));
				bookingResponseModel.setConfCatId(rs.getString("room_no"));
				return bookingResponseModel;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	@Override
	public List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params) {
		String reqId=(String) params.get("reqId");
		List<AllotmentRoom> data =null;
		String query=adminQueries.getProperty("getAllotRoomByReqId");
		try {
				data = jdbcTemplate.query(query, (rs, rowNum) -> {
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
				Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
				AllotmentRoom bookingResponseModel = new AllotmentRoom();
				bookingResponseModel.setFromDate(sdf1.format(fromDate));
				bookingResponseModel.setToDate(sdf1.format(toDate));
				bookingResponseModel.setReqId(rs.getString("req_id"));
				bookingResponseModel.setAllotId(rs.getString("allot_ids"));
				bookingResponseModel.setAllotRoom(rs.getString("room_nos"));
				return bookingResponseModel;
			},reqId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	@Override
	public boolean cancelAllotRoomPerReqId(Map<String, Object> params) {
		String query=adminQueries.getProperty("updateAllotRoomCan");
		query=query+" and allot_id in ('"+params.get("selectCheckAllotRoom")+"')";
		try {
			jdbcTemplate.update(query, new Object[] {"CAN","PRO"});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean adminDaopendingNocCheck(String userId) {
		String sql = adminQueries.getProperty("checkPendingNocSQL");
		Integer count = 0;
		try {
			count = jdbcTemplate.queryForObject(sql, new Object[] { userId }, Integer.class);
			System.out.println("count======================"+count);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return count>0;
	}
	@Override
	public  AdminDashoardCount getAdminDashboradCount(Map<String, Object> params,String userId,String role) {
		String date=(String) params.get("date");
		String ghId=(String) params.get("city");
		String blockId=(String) params.get("gHouse");
		String query1=adminQueries.getProperty("getCategoryPercent");
		String query2=adminQueries.getProperty("getPurposePercent");
		String query3=adminQueries.getProperty("getBarCount");
		String advncCash = adminQueries.getProperty("getCashInCheckIn");
		String recCash = adminQueries.getProperty("getCashInReceipt");
		String nocCash = adminQueries.getProperty("getCashInNoc");
		String confCash = adminQueries.getProperty("getCashInConference");
		String recPos = adminQueries.getProperty("getPosInReceipt");
		String nocPos = adminQueries.getProperty("getPosInNoc");
		String confPos = adminQueries.getProperty("getPosInConference");
		String recOnline = adminQueries.getProperty("getOnlineInReceipt");
		String nocOnline = adminQueries.getProperty("getOnlineInNoc");
		String confOnline = adminQueries.getProperty("getOnlineInConference");
		AdminDashoardCount data =null;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		List<String> blockLists=new ArrayList<String>();
		List<String> cityLists=new ArrayList<String>();
		Date fromDate=null;
		String query="";
		String restQuery="   GROUP BY days"
				+ "),"
				+ "all_days AS ("
				+ "    SELECT generate_series(CURRENT_DATE, CURRENT_DATE + INTERVAL '4 days', INTERVAL '1 day')::date AS days "
				+ ")"
				+ "SELECT"
				+ "   TO_CHAR(all_days.days, 'DD-MM-YYYY') AS days,"
				+ "    COALESCE(dc.count, 0) AS count,"
				+ "    COALESCE(ROUND(dc.count * 100.0 / (SELECT SUM(count) FROM date_counts), 2), 0)"
				+ "    AS percentage_84"
				+ " FROM "
				+ "    all_days "
				+ "LEFT JOIN date_counts dc ON all_days.days = dc.days "
				+ " ORDER BY "
				+ "all_days.days";
		
		Map<String, Object> queryParams = new HashMap<>();
		
		if (ghId.equals("ALL")) {
			JSONObject mappedCityLocsByUser = accessControlService.getAllMappedCityLocByUser(userId, role);
			System.out.println(mappedCityLocsByUser);
			JSONArray jsonArray;
			try {
				jsonArray = new JSONArray(mappedCityLocsByUser.get("data").toString());
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					cityLists.add(jsonObject.getString("gh_id"));
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			for(String cityId : cityLists) {
				List<CommonComboBoxResponseModel> blockList=getGuestHouseListByUserRoleCity(userId, role, cityId);
				for(CommonComboBoxResponseModel commonComboBoxResponseModel:blockList) {
						blockLists.add(commonComboBoxResponseModel.getId());
				}
			}
		} else {
			List<CommonComboBoxResponseModel> blockList=getGuestHouseListByUserRoleCity(userId, role, ghId);
			for(CommonComboBoxResponseModel commonComboBoxResponseModel:blockList) {
					blockLists.add(commonComboBoxResponseModel.getId());
			}
		}
		
		queryParams.put("blockIds", blockLists);
	    queryParams.put("blockId", blockId);
	    
		StringBuilder result = new StringBuilder("(");
	        for (String element : blockLists) {
	            result.append("'").append(element).append("',");
	        }
	    result.deleteCharAt(result.length() - 1);
	    result.append(")");
	    System.out.println(result);
		if(!blockId.equals("ALL")) {
			 advncCash=advncCash+" and gd.block_id= :blockId";
			 recCash=recCash+" and tr.block_id= :blockId";
			 nocCash=nocCash+" and gd.block_id= :blockId";
			 confCash=confCash+" and gd.block_id= :blockId";
			 recPos=recPos+" and tr.block_id= :blockId";
			 nocPos =nocPos+" and gd.block_id= :blockId";
			 confPos=confPos+" and gd.block_id= :blockId";
			 recOnline=recOnline+" and tr.block_id= :blockId";
			 nocOnline =nocOnline+" and gd.block_id= :blockId";
			 confOnline=confOnline+ " and gd.block_id= :blockId";
			 query1=query1+" and block_id= :blockId";
			 query2=query2+" and block_id= :blockId";
			 query3=query3+" and block_id= :blockId";
			 queryParams.put("blockId", blockId);
		}else {
			advncCash=advncCash+" and gd.block_id in"+result;
			 recCash=recCash+" and tr.block_id in"+result;
			 nocCash=nocCash+" and gd.block_id in "+result;
			 confCash=confCash+" and gd.block_id in"+result;
			 recPos=recPos+" and tr.block_id in"+result;
			 nocPos =nocPos+" and gd.block_id in"+result;
			 confPos=confPos+" and gd.block_id in"+result;
			 recOnline=recOnline+" and tr.block_id in"+result;
			 nocOnline =nocOnline+" and gd.block_id in "+result;
			 confOnline=confOnline+ " and gd.block_id in"+result;
			 query1=query1+" and block_id in"+result;
			 query2=query2+" and block_id in"+result;
			 query3=query3+" and block_id in"+result;
		}
		if(date.equals("")) {
			try {
				Date currentDate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String formattedDateStr = dateFormat.format(currentDate);
				fromDate = dateFormat.parse(formattedDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			query="SELECT * FROM get_gad_request_counts(now()::date ,ARRAY"+String.valueOf(result).replace("(", "[").replace(")", "]")+", :blockId)";
			queryParams.put("blockId", blockId);
		}else {
			String fromDateString="";
			try {
				fromDate = date != null ? sdf1.parse(date) : null;
				fromDateString = sdf2.format(fromDate) ;
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			query="SELECT * FROM get_gad_request_counts(:fromDate::date,ARRAY"+String.valueOf(result).replace("(", "[").replace(")", "]")+", :blockId)";
	        queryParams.put("fromDate", fromDateString);
	        queryParams.put("blockId", blockId);
		}
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
			data = namedParameterJdbcTemplate.queryForObject(query, queryParams, (rs, rowNum) -> {
				AdminDashoardCount adminDashoardCount = new AdminDashoardCount();
				adminDashoardCount.setAlloted(rs.getInt("allotment"));
				adminDashoardCount.setApproved(rs.getInt("approved"));
				adminDashoardCount.setModeration(rs.getInt("moderator"));
				adminDashoardCount.setCheckIn(rs.getInt("check_in"));
				adminDashoardCount.setCheckOut(rs.getInt("check_out"));
				adminDashoardCount.setTotalBooking(rs.getInt("total_booking"));
				adminDashoardCount.setOnline(rs.getInt("online"));
				adminDashoardCount.setOffline(rs.getInt("offline"));
				adminDashoardCount.setCancelled(rs.getInt("cancelled"));
				adminDashoardCount.setExcellent(rs.getInt("excellent"));
				adminDashoardCount.setGood(rs.getInt("good"));
				adminDashoardCount.setAverage(rs.getInt("average"));
				adminDashoardCount.setPoor(rs.getInt("poor"));
				adminDashoardCount.setVpoor(rs.getInt("vpoor"));
				return adminDashoardCount;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> paramsQuery = new HashMap<>();
		paramsQuery.put("fromDate", fromDate);
		paramsQuery.put("blockId", blockId);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		try {
			Integer advncCash1 = namedParameterJdbcTemplate.queryForObject(advncCash, paramsQuery, Integer.class);
		    Integer recCash1 = namedParameterJdbcTemplate.queryForObject(recCash, paramsQuery, Integer.class);
		    Integer nocCash1 = namedParameterJdbcTemplate.queryForObject(nocCash, paramsQuery, Integer.class);
		    Integer confCash1 = namedParameterJdbcTemplate.queryForObject(confCash, paramsQuery, Integer.class);
		    Integer recPos1 = namedParameterJdbcTemplate.queryForObject(recPos, paramsQuery, Integer.class);
		    Integer confPos1 = namedParameterJdbcTemplate.queryForObject(confPos, paramsQuery, Integer.class);
		    Integer nocPos1 = namedParameterJdbcTemplate.queryForObject(nocPos, paramsQuery, Integer.class);
		    Integer recOnline1 = namedParameterJdbcTemplate.queryForObject(recOnline, paramsQuery, Integer.class);
		    Integer confOnline1 = namedParameterJdbcTemplate.queryForObject(confOnline, paramsQuery, Integer.class);
		    Integer nocOnline1 = namedParameterJdbcTemplate.queryForObject(nocOnline, paramsQuery, Integer.class);
			data.setCash(advncCash1+recCash1+nocCash1+confCash1);
			data.setPosAmount(recPos1+confPos1+nocPos1);
			data.setOnlineAmount(recOnline1+confOnline1+nocOnline1);
			Integer totalAmount=advncCash1+recCash1+nocCash1+confCash1+recPos1+confPos1+nocPos1+recOnline1+confOnline1+nocOnline1;
			data.setTotalAmount(totalAmount);
		}catch(Exception e) {
			e.printStackTrace();
		}
		AdminDashoardCount category=null;
		try {
		    category = namedParameterJdbcTemplate.queryForObject(query1, paramsQuery, (rs, rowNum) -> {
		        AdminDashoardCount data1 = new AdminDashoardCount();
		        data1.setC1(rs.getInt("C1"));
		        data1.setC2(rs.getInt("C2"));
		        data1.setC3(rs.getInt("C3"));
		        data1.setC4(rs.getInt("C4"));
		        data1.setC1Count(rs.getInt("c1Count"));
		        data1.setC2Count(rs.getInt("c2Count"));
		        data1.setC3Count(rs.getInt("c3Count"));
		        data1.setC4Count(rs.getInt("c4Count"));
		        return data1;
		    });
		} catch (Exception e) {
		    e.printStackTrace();
		}
		AdminDashoardCount purposes=null;
		try {
		    purposes = namedParameterJdbcTemplate.queryForObject(query2, paramsQuery, (rs, rowNum) -> {
		        AdminDashoardCount data1 = new AdminDashoardCount();
		        data1.setP1(rs.getInt("p1"));
		        data1.setP2(rs.getInt("p2"));
		        data1.setP3(rs.getInt("p3"));
		        data1.setP4(rs.getInt("p4"));
		        data1.setP1Count(rs.getInt("p1Count"));
		        data1.setP2Count(rs.getInt("p2Count"));
		        data1.setP3Count(rs.getInt("p3Count"));
		        data1.setP4Count(rs.getInt("p4Count"));
		        return data1;
		    });
		} catch (Exception e) {
		    e.printStackTrace();
		}
		query3=query3+restQuery;
		List<Map<String, Object>> resultData =null;
		try {
		    List<Map<String, Object>> rawData = namedParameterJdbcTemplate.queryForList(query3, paramsQuery);
		    resultData = new ArrayList<>();

		    for (Map<String, Object> row : rawData) {
		        Map<String, Object> resultRow = new LinkedHashMap<>();
		        resultRow.put("days", row.get("days"));
		        resultRow.put("count", row.get("count"));
		        resultRow.put("percentage_84", row.get("percentage_84"));
		        resultData.add(resultRow);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
        
		data.setC1(category.getC1());
		data.setC2(category.getC2());
		data.setC3(category.getC3());
		data.setC4(category.getC4());
		data.setC1Count(category.getC1Count());
		data.setC2Count(category.getC2Count());
		data.setC3Count(category.getC3Count());
		data.setC4Count(category.getC4Count());
		data.setP1(purposes.getP1());
		data.setP2(purposes.getP2());
		data.setP3(purposes.getP3());
		data.setP4(purposes.getP4());
		data.setP1Count(purposes.getP1Count());
		data.setP2Count(purposes.getP2Count());
		data.setP3Count(purposes.getP3Count());
		data.setP4Count(purposes.getP4Count());
		data.setBar(resultData);
		return data;
	}
	@Override
	public DataTableResponse deleteReport(DataTableRequest params) {
	    StringBuilder sql = new StringBuilder(adminQueries.getProperty("getDeleteReport"));
	    StringBuilder sql1 = new StringBuilder(adminQueries.getProperty("getDeleteReportCount"));
	    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    
	    DataTableResponse dt = new DataTableResponse();
	    int start = params.getStart();
	    int length = params.getLength();
	    
	    if (params.getCity() != null ? !params.getCity().isEmpty() : false) {
	    	sql.append(" AND gr.gh_id='" + params.getCity() + "'");
	    	sql1.append(" AND  gr.gh_id='" + params.getCity() + "'");
		}
		if (params.getgHouse() != null ? !params.getgHouse().isEmpty() : false) {
			sql.append(" AND gr.block_id='" + params.getgHouse() + "'");
	    	sql1.append(" AND  gr.block_id='" + params.getgHouse() + "'");
		}
		if (params.getBookingId() != null ? !params.getBookingId().isEmpty() : false) {
	    	sql.append(" AND gr.req_id='" + params.getBookingId() + "'");
	    	sql1.append(" AND  gr.req_id='" + params.getBookingId() + "'");
		}
		if (params.getMobNo() != null ? !params.getMobNo().isEmpty() : false) {
			sql.append(" AND gr.mobile_no='" + params.getMobNo() + "'");
	    	sql1.append(" AND  gr.mobile_no='" + params.getMobNo() + "'");
		}
		if (params.getName() != null ? !params.getName().isEmpty() : false) {
	    	sql.append(" AND gs.name ILIKE '%" + params.getName() + "%'");
	    	sql1.append(" AND gs.name ILIKE '%" + params.getName() + "%'");
		}
		if (params.getFrmDate() != null ? !params.getFrmDate().isEmpty() : false) {
		 try {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date date = sdf.parse(params.getFrmDate());
	            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	            String frmDt = sdf2.format(date);
	            sql.append(" AND gr.from_date::date='" + frmDt + "'::date");
		    	sql1.append(" AND gr.from_date::date='" + frmDt + "'::date");
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
		}
		sql.append(" ORDER BY gr.lastupdated desc");
	    // Adding pagination if length is not zero
	    if (length != 0) {
	        sql.append(" LIMIT ").append(length).append(" OFFSET ").append(start);
	    }

	    ExecutorService executorService = Executors.newFixedThreadPool(2);

	    // Fetching data asynchronously
	    CompletableFuture<List<DataTableRequest>> dataFuture = CompletableFuture.supplyAsync(() -> {
	        try {
	            return jdbcTemplate.query(sql.toString(), new RowMapper<DataTableRequest>() {
	                @Override
	                public DataTableRequest mapRow(ResultSet rs, int rownumber) throws SQLException {
	                    String formDt = rs.getTimestamp("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
	                    String toDt = rs.getTimestamp("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
	                    String cancelDate = rs.getTimestamp("lastupdated") != null ? sdf1.format(rs.getTimestamp("lastupdated")) : null;
	                    String bookingDate = rs.getTimestamp("req_date") != null ? sdf1.format(rs.getTimestamp("req_date")) : null;

	                    DataTableRequest dataTableRequest = new DataTableRequest();
	                    dataTableRequest.setBookingId(rs.getString("req_id"));
	                    dataTableRequest.setBookingDate(bookingDate);
	                    dataTableRequest.setCancelDate(cancelDate);
	                    dataTableRequest.setFrmDate(formDt);
	                    dataTableRequest.setToDate(toDt);
	                    dataTableRequest.setMobNo(rs.getString("mobile_no"));
	                    dataTableRequest.setName(rs.getString("name"));
	                    dataTableRequest.setOrderBy("GUEST".equals(rs.getString("cancelled_by")) ? "Guest" : "Admin");

	                    return dataTableRequest;
	                }
	            });
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Exception occurred while fetching data from the database", e);
	        }
	    }, executorService);

	    // Fetching count asynchronously
	    CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
	        try {
	            return jdbcTemplate.queryForObject(sql1.toString(), Integer.class);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }, executorService);

	    // Wait for both CompletableFutures to complete
	    List<DataTableRequest> dataList = dataFuture.join();
	    Integer count = countFuture.join();

	    // Set the response data
	    dt.setDraw(params.getDraw());
	    dt.setData(dataList);
	    dt.setRecordsTotal(count != null ? count : 0);
	    dt.setRecordsFiltered(count != null ? count : 0);

	    // Shutdown the ExecutorService
	    executorService.shutdown();

	    return dt;
	}
	@Override
	@Transactional
	public boolean cancelModifiedRoomOrBedRequest(String reqId) {
		String query1=allotmentQueries.getProperty("cancelModifiedRoomOrBedRequest");
		String query2=allotmentQueries.getProperty("cancelModificationRoomInSms");
		try {
			jdbcTemplate.update(query1,new Object[] {reqId,"PMODF"});
			jdbcTemplate.update(query2,new Object[] {reqId,"MODF"});
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/* Sandip AdminDaoImpl ends */
	/* Prerana AdminDaoImpl starts   */
	
	@Override
	public String receptionistaddBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		logger.info(data.getBlockId());
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		String ghName = null;

		data.setCreatedBy(userId);
		data.setUpdatedBy(userId);

		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {

			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId", rs.getString("gh_id"));
				res.put("bId", rs.getString("block_id"));
				res.put("bName", rs.getString("block_name"));
				res.put("sName", rs.getString("short"));
				return res;
			}
		}, data.getBlockId());
		if (blockList == null || blockList.size() == 0) {
			return null;
		}

		try {
			ghName = getCityName(blockList.get(0).get("ghId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(blockList.get(0).get("ghId"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

		Integer id = get_seqID("req_id");
		update_seqID("req_id", id);
		String reqId = "H" + blockList.get(0).get("sName") + id.toString();
		logger.info(reqId);
		Date frmDt = !data.getFrmDt().equals("") ? sdf1.parse(data.getFrmDt()) : null;
		Date toDt = !data.getToDt().equals("") ? sdf1.parse(data.getToDt()) : null;
		Date arrDt = !data.getArrDt().equals("") ? sdf.parse(data.getArrDt()) : null;
		Date depDt = !data.getDepDt().equals("") ? sdf.parse(data.getDepDt()) : null;

		Date arrtm = !data.getArrTm().equals("") ? stf.parse(data.getArrDt() + " " + data.getArrTm()) : null;
		Date deptm = !data.getDepTm().equals("") ? stf.parse(data.getDepDt() + " " + data.getDepTm()) : null;

		String q1 = approverQueries.getProperty("saveBookingDetailsSQl");
		String q2 = approverQueries.getProperty("saveArrivalDtlsSQl");
		String q3 = approverQueries.getProperty("saveDepartureDtlsSQl");
		String q4 = approverQueries.getProperty("saveGuestDetailsSQL");

		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			logger.info("saved document file path :: " + filepath);
			if (filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			}
		}
		String catg_filepath = null;
		if (null != data.getCatgDoc()) {
			String fileSeperator1 = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory1 = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator1;
			catg_filepath = Utils.saveFileInSysPath(data.getCatgDoc(), directory1);
			logger.info("saved document category file path :: " + catg_filepath);
			if (catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
			}
		}
		try {
			jdbcTemplate.update(q1, new Object[] { reqId, blockList.get(0).get("ghId"), frmDt, toDt,
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
					"0", "REQ", !data.getArrDt().equals("") ? "1" : "0", "GH", data.getPurp(), "0", data.getOffAddr(),
					data.getMob(),
					data.getBookingType() == null ? "0"
							: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
					"0", "0", data.getMob(), data.getEmailId(),
					data.getExistingBookingId() != null ? data.getExistingBookingId() : "0",
					!data.getDepDt().equals("") ? "1" : "0", data.getBlockId(), // block Id
					filepath, // file path
					data.getCatg(), data.getDesg(), data.getRoomType(), catg_filepath, data.getDuringstay(),
					data.getCatgType(), data.getNoOfDays(), data.getNoOfAdults(), data.getNoOfChildren(),
					data.getBookingFor(), data.getCreatedBy(), data.getUpdatedBy() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");
		}
		String msg = "";
		if (data.getBookingType() != null) {
			if (data.getBookingType().equalsIgnoreCase("room")) {
				if (data.getNoOfRooms() != null) {
					if (!data.getNoOfRooms().isEmpty()) {
						if (!data.getNoOfRooms().equalsIgnoreCase("0")) {
							msg = data.getBookingType() == null ? "0"
									: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0")
											+ " Rooms in " + blockList.get(0).get("bName") + " " + ghName;
						}
					}
				} else {
					msg = data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0") + " Beds in "
									+ blockList.get(0).get("bName") + " " + ghName;
				}
			} else {
				msg = data.getBookingType() == null ? "0"
						: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0")
								+ " Dormetary Beds in " + blockList.get(0).get("bName") + " " + ghName;
			}
		}
		if (data.getRoomType().equalsIgnoreCase("RC") || data.getRoomType().equalsIgnoreCase("C")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "H_" + vid.toString();
			if(!data.getArrDt().equals("")) {
				jdbcTemplate.update(q2, new Object[] { vehId, reqId, data.getTravelModeFr(), arrtm, data.getArrPla(), "0",
						data.getFlNoFr(), arrDt });
			}
			if(!data.getDepDt().equals("")) {
				jdbcTemplate.update(q3, new Object[] { vehId, reqId, data.getTravelModeTo(), deptm, data.getDepPla(), "0",
						data.getFlNoTo(), depDt });
			}
		}		
		String guestName = "Applicant ";
		if (data.getList() != null) {
			for (GuestDetailsPojo item : data.getList()) {
				Integer gid = get_seqID("guest_id");
				String guestId = "H" + gid.toString();
				update_seqID("guest_id", gid);
				try {
					jdbcTemplate.update(q4,
							new Object[] { reqId, guestId, item.getGuestName(), item.getGuestAge(),
									item.getGuestGender(), item.getGuestAddr(), item.getGuestDesignation(),
									item.getGuestDepartment(), item.getGuestMobile(), item.getGuestDocType(),
									item.getGuestDocPath(), "0" });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Guest Details not saved");
				}
			}
			guestName = data.getList().get(0).getGuestName();
		}
//		try {
//			if (data.getEmailId() != null) {
//				if (!data.getEmailId().isEmpty()) {
//					mail.sendMimeMail(data.getEmailId(), "Application Details",
//							"Dear " + guestName + "<br> You have successfully applied for " + msg
//									+ " and your booking id " + reqId + "<br><br> Thanks <br>e-Bhawan Team");// mail
//				}
//			}
//		} catch (Exception e) {
//			logger.error("error occured in sms or email send..");
//		}
		try {
			if (data.getMob() != null) {
				if (!data.getMob().isEmpty()) {
					String templateID = "1107160707714316860";
					smsSend.sendSMS(data.getMob(), "Dear " + guestName + " You have successfully applied for " + msg
							+ " and your booking id " + reqId + " ", templateID);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}

		return reqId;
	}

	@Override
	public boolean receptioniUpdateBookingDetails(RoomBookingPojo data, String userId)
			throws ParseException, IOException {
		RoomBookingPojo pd = admingetBookingDetailsById(data.getBookingId());
		logger.info(data.getBookingId());
		logger.info(pd);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		boolean status = false;

		Date frmDt = !data.getFrmDt().equals("") ? sdf1.parse(data.getFrmDt()) : null;
		Date toDt = !data.getToDt().equals("") ? sdf1.parse(data.getToDt()) : null;
		Date arrDt = !data.getArrDt().equals("") ? sdf.parse(data.getArrDt()) : null;
		Date depDt = !data.getDepDt().equals("") ? sdf.parse(data.getDepDt()) : null;

		Date arrtm = !data.getArrTm().equals("") ? stf.parse(data.getArrDt() + " " + data.getArrTm()) : null;
		Date deptm = !data.getDepTm().equals("") ? stf.parse(data.getDepDt() + " " + data.getDepTm()) : null;

		String q1 = approverQueries.getProperty("updateBookingDetailsSQl");
		String q2 = approverQueries.getProperty("saveArrivalDtlsSQl");
		String q3 = approverQueries.getProperty("saveDepartureDtlsSQl");
		String q4 = approverQueries.getProperty("saveGuestDetailsSQL");

		String q5 = approverQueries.getProperty("updateArrivalDtlsSQl");
		String q6 = approverQueries.getProperty("updateDepartureDtlsSQl");
		String q7 = approverQueries.getProperty("updateGuestDetailsSQL");
		String q8 = approverQueries.getProperty("deleteGuestDetailsSQL");

		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(pd.getBookingId());
			String directory = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			logger.info("saved document file path :: " + filepath);
			if (filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			} else {
				try {
					filepath = get_filePath(pd.getBookingId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				filepath = get_filePath(pd.getBookingId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String catg_filepath = null;
		if (null != data.getCatgDoc()) {
			String fileSeperator1 = String.valueOf(new Date().getTime()) + "_" + String.valueOf(pd.getBookingId());
			String directory1 = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator1;
			catg_filepath = Utils.saveFileInSysPath(data.getCatgDoc(), directory1);
			logger.info("saved document category file path :: " + catg_filepath);
			if (catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
				logger.info(catg_filepath);
			} else {
				try {
					catg_filepath = get_filePath1(pd.getBookingId());
					logger.info(catg_filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				catg_filepath = get_filePath1(pd.getBookingId());
				logger.info(catg_filepath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (data.getBookingFor().equalsIgnoreCase("others")) {
			if (null == data.getgDoc()) {
				GuestDetailsPojo prevGuest = pd.getList().get(0);
				data.getList().get(0).setGuestDocPath(prevGuest.getGuestDocPath());
			}
		}

		try {
			jdbcTemplate.update(q1, new Object[] { frmDt, toDt,
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
					data.getBookingType() == null ? "0"
							: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
					pd.getStatus(), !data.getArrDt().equals("") ? "1" : "0", data.getPurp(), data.getOffAddr(),
					data.getBookingType() == null ? "0"
							: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
					data.getMob(), data.getEmailId(), !data.getDepDt().equals("") ? "1" : "0", filepath, // file path
					data.getCatg(), data.getDesg(), data.getRoomType(), catg_filepath, data.getDuringstay(),
					data.getCatgType(), data.getNoOfDays(), data.getNoOfAdults(), data.getNoOfChildren(),
					data.getBookingFor(), pd.getBookingId() });
			/*if (!pd.getIsArrivalConv() && !data.getArrDt().equals("")) {
				Integer vid = get_seqID("veh_id");
				update_seqID("veh_id", vid);
				String vehId = "P_" + vid.toString();
				jdbcTemplate.update(q2, new Object[] { vehId, pd.getBookingId(), data.getTravelModeFr(), arrtm,
						data.getArrPla(), "0", data.getFlNoFr(), arrDt });
			} else {
				jdbcTemplate.update(q5, new Object[] { data.getTravelModeFr(), arrtm, data.getArrPla(),
						data.getFlNoFr(), arrDt, pd.getBookingId(), pd.getdVchId() });
			}
			if (!pd.getIsDepartueConv() && !data.getDepDt().equals("")) {
				Integer vid = get_seqID("veh_id");
				update_seqID("veh_id", vid);
				String vehId = "P_" + vid.toString();
				jdbcTemplate.update(q3, new Object[] { vehId, pd.getBookingId(), data.getTravelModeTo(), deptm,
						data.getDepPla(), "0", data.getFlNoTo(), depDt });
			} else {
				jdbcTemplate.update(q6, new Object[] { data.getTravelModeTo(), deptm, data.getDepPla(),
						data.getFlNoTo(), depDt, pd.getBookingId(), pd.getdVchId() });
			}*/
			
			//Arrival Details Save or update
			if(pd.getaVchId() != null) {
				if(!pd.getaVchId().equals("")) {
					jdbcTemplate.update(q5, new Object[] { data.getTravelModeFr(), arrtm, data.getArrPla(),
							data.getFlNoFr(), arrDt, pd.getBookingId(), pd.getaVchId() });	
				}
				
			}else {
				if(!data.getArrDt().equals("")) {
					Integer vid = get_seqID("veh_id");
					update_seqID("veh_id", vid);
					String vehId = "H_" + vid.toString();
					jdbcTemplate.update(q2, new Object[] { vehId, pd.getBookingId(), data.getTravelModeFr(), arrtm,
							data.getArrPla(), "0", data.getFlNoFr(), arrDt });
				}
			}

			//Departure Details Save or update
			if(pd.getdVchId() != null) {
				if(!pd.getdVchId().equals("")) {
					jdbcTemplate.update(q6, new Object[] { data.getTravelModeTo(), deptm, data.getDepPla(),
							data.getFlNoTo(), depDt, pd.getBookingId(), pd.getdVchId() });
					}
			}else {
				if (!data.getDepDt().equals("")) {
					Integer vid = get_seqID("veh_id");
					update_seqID("veh_id", vid);
					String vehId = "H_" + vid.toString();
					jdbcTemplate.update(q3, new Object[] { vehId, pd.getBookingId(), data.getTravelModeTo(), deptm,
							data.getDepPla(), "0", data.getFlNoTo(), depDt });
				}
			}
			
			// if (!data.getRoomType().equalsIgnoreCase("C")) {
			if (data.getList() != null) {
				String id = "";
				Integer i = 0;
				for (GuestDetailsPojo item : data.getList()) {
					i++;
					logger.info(item);
					if (item.getGuestId().equals("")) {
						Integer gid = get_seqID("guest_id");
						String guestId = "H_" + gid.toString();
						id = id + "'" + guestId + "'";
						update_seqID("guest_id", gid);
						jdbcTemplate.update(q4,
								new Object[] { pd.getBookingId(), guestId, item.getGuestName(), item.getGuestAge(),
										item.getGuestGender(), item.getGuestAddr(), item.getGuestDesignation(),
										item.getGuestDepartment(), item.getGuestMobile(), item.getGuestDocType(),
										item.getGuestDocPath(), "0" });
					} else {
						id = id + "'" + item.getGuestId() + "'";
						jdbcTemplate.update(q7,
								new Object[] { item.getGuestName(), item.getGuestAge(), item.getGuestGender(),
										item.getGuestAddr(), item.getGuestDesignation(), item.getGuestDepartment(),
										item.getGuestMobile(), item.getGuestDocType(), item.getGuestDocPath(),
										pd.getBookingId(), item.getGuestId(), });
					}
					if (i < data.getList().size()) {
						id = id + ",";
					}
				}
				if (!id.isEmpty()) {
					q8 = q8.concat(id + ")");
				}
				jdbcTemplate.update(q8, new Object[] { pd.getBookingId() });
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed");
		}
		status = true;
		return true;
	}

	@Override
	public List<GuestDetailsResponseForOrder> getAllDetailsByProcessidForOfficeOrder(String processId, String type) {
		List<GuestDetailsResponseForOrder> data;
		String query = adminQueries.getProperty("officeOrderGuestList");
		if (type.equalsIgnoreCase("print"))
			query = adminQueries.getProperty("officeOrderPrintGuestList");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		logger.info(query);
		try {
			data = jdbcTemplate.query(query, new RowMapper<GuestDetailsResponseForOrder>() {
				public GuestDetailsResponseForOrder mapRow(ResultSet rs, int rownumber) throws SQLException {
					GuestDetailsResponseForOrder pm = new GuestDetailsResponseForOrder();
					Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
					Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
					pm.setReqId(rs.getString("req_id"));
					pm.setFrom(sdf.format(fromDate));
					pm.setTo(sdf.format(toDate));
					pm.setRoomNo(rs.getString("room_no"));
					pm.setRent(rs.getString("room_rent"));					
					String duringStay =  rs.getString("during_stay_conveancy");
					if(duringStay.equalsIgnoreCase("0")) {
						pm.setDuringStayConveynce("No");
					}else {
						pm.setDuringStayConveynce("Yes");
					}
					pm.setConveynceFrom(rs.getString("is_veh_req"));
					pm.setConveynceTo(rs.getString("is_veh_req1"));
					String arrDate =  rs.getDate("arrival_time") != null ? sdf.format(rs.getTimestamp("arrival_time")) : null; 
					String depDate =  rs.getDate("departure_time") != null ? sdf.format(rs.getTimestamp("departure_time")) : null;
					String arrFlTrNo = rs.getString("ftno");
					String arrConvPlace = rs.getString("arrival_place");
					String depFlTrNo = rs.getString("departure_ftno");
					String depConvPlace = rs.getString("departure_place");										
					if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
						String arrivalDetails = "Flight/Train No :" + arrFlTrNo + " at " + arrDate + " in " + arrConvPlace;
						pm.setConveynceFromDatails(arrivalDetails);						
					} 
					if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
						String departDetails = "Flight/Train No :" + depFlTrNo + " at " + depDate + " in " + depConvPlace;
						pm.setConveynceToDetails(departDetails);
					}	
					String name = rs.getString("name");
					String addrss = rs.getString("main_address");
					if (addrss != null && !addrss.isEmpty()) {
						name += "," + addrss;
					}
					String FormDate = sdf.format(fromDate);
					String ToDate = sdf.format(toDate);
					String periodOfStay = FormDate + " to " + ToDate;
					System.out.println("formdate and todate" + periodOfStay);
					if(rs.getString("ext").equals("1") || rs.getString("ext").equals("2")) {
						periodOfStay=periodOfStay+" (EXT)";
					}
					pm.setPeriodOfStay(periodOfStay);
					pm.setGuestName(name);
					return pm;
				}
			}, processId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("GuestDetailsResponseForOrder" + query);
		System.out.println(" guest details "+data);
		return data;
	}

	@Override
	public List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params) {
		List<BookingResponseModel> users = new ArrayList<BookingResponseModel>();
		String orderBy = " order by req_date desc)f1  where f1.i =1";
		String sql = approverQueries.getProperty("getallFeedbackListInAdminSession");
		String whereCondition = " WHERE 1=1";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String name = (String) params.get("name");

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition+"AND feedback_system.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND feedback_system.block_id='" + ghId + "'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND feedback_system.req_id='" + bookingId + "'";
		}
		if (name != null ? !name.isEmpty() : false) {
			whereCondition = whereCondition + " AND upper(name) like upper( '%" + name + "%')";
		}
		sql = sql + whereCondition + orderBy;
		try {
			users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
				@Override
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					BookingResponseModel booking = new BookingResponseModel();
					booking.setBookingId(rs.getString("req_id"));
					// booking.setCategory(rs.getString("quota_desc"));
					booking.setMobile(rs.getString("mobile_no"));
					booking.setName(rs.getString("name"));
					booking.setDesg(rs.getString("designation"));
					if (rs.getString("is_veh_req").equalsIgnoreCase("1")) {
						booking.setConveyance("Yes");
					} else {
						booking.setConveyance("No");
					}
					if (rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
						booking.setConveyance("Yes");
					} else {
						if (!booking.getConveyance().equalsIgnoreCase("Yes")) {
							booking.setConveyance("No");
						}
					}

					String formDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
					String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
					String submitDt = rs.getDate("submitted_on") != null ? sdf1.format(rs.getTimestamp("submitted_on"))
							: null;
					String reqDt = rs.getDate("req_date") != null ? sdf1.format(rs.getTimestamp("req_date")) : null;
					booking.setFromDate(formDt);
					booking.setToDate(toDt);
					booking.setSubmittedOn(submitDt);
					booking.setReqDate(reqDt);

					// booking.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name")
					// + ")");
					String purpose = rs.getString("purpose");
					String purpose1 = "";
					if (purpose.equals("1")) {
						purpose1 = "Official";
					}
					if (purpose.equals("2")) {
						purpose1 = "Non-Official";
					}
					if (purpose.equals("3")) {
						purpose1 = "Patient";
					}
					if (purpose.equals("4")) {
						purpose1 = "Student";
					}
					booking.setPurpose(purpose1);
					// String status = rs.getString("finalstatus");
					String sdd = rs.getString("feedbackstatus");
					booking.setFeedbackstatus(sdd);
					String feedback = rs.getString("feedback_id");
					if (feedback != null) {
						booking.setFeedbackId(feedback);
					} else {
						booking.setFeedbackId("NA");
					}
					String status_desc = "";
					if (sdd == null) {
						status_desc = "NA";
					} else if (sdd.equalsIgnoreCase("Opened")) {
						status_desc = "Opened";
					} else if(sdd.equalsIgnoreCase("Viewed")){
						status_desc = "Viewed";
					}else {
						status_desc="Closed";
					}
					booking.setViewForFeedback(rs.getBoolean("view"));
					booking.setStatusDesc(status_desc);
					return booking;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("feedback list   "+sql);
		return users;
	}

	@Override
	public boolean openReplyFeedback(String feedbackId) {
		String sql = approverQueries.getProperty("setTrueForModal");
		jdbcTemplate.update(sql, new Object[] { feedbackId });
		return true;
	}

	@Override
	public boolean saveFeedbackRepliedDetails(FeedbackRequestModel request,Integer userName,String userId) {
		System.out.println("userName     "+userName);
		String sql = approverQueries.getProperty("saveFeedbackRepliedDetails");
		try {
			jdbcTemplate.update(sql,
					new Object[] { request.getRemarks(), "Closed",userName,userId, request.getRequestId(), request.getFeedbackId() });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Not Working");

		}
		System.out.println("save feedback replied details "+sql);
		return true;
	}

	@Override
	public BookingResponseModel getTotalfeedbackCount(Map<String, Object> params) {

		String sql = approverQueries.getProperty("feedbackCount");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String whereCondition = "where 1=1";

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = "WHERE feedback_system.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND feedback_system.block_id='" + ghId + "'";
		}

		sql = sql + whereCondition;
		List<BookingResponseModel> users = new ArrayList<BookingResponseModel>();
		BookingResponseModel user = new BookingResponseModel();
		users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel res = new BookingResponseModel();
				res.setCount(rs.getInt("total"));
				res.setViewFeedbackCount(rs.getInt("viewed"));
				res.setOpenFeedbackCount(rs.getInt("opened"));
				res.setClosedFeedbackCount(rs.getInt("closed"));
				return res;
			}
		});
		//System.out.println("query for count" + sql);

		if (users != null) {
			if (users.size() != 0) {
				user = users.get(0);
			}
		}
		return user;

	}

	@Override
	public List<FeedbackViewModel> getFeedbackReport(Map<String, Object> params) {
		List<FeedbackViewModel> users = new ArrayList<FeedbackViewModel>();
		String sql = approverQueries.getProperty("feedbackReport");
		//String whereCondition = "where (a.status is null or status = 'Closed')";
		String whereCondition = "";
		String groupBy = "GROUP BY c.gh_id,c.gh_name,d.block_id,d.block_name  ) as tab order by block_id ";
		String join="join gh_details c on c.gh_id=d.gh_id ";
		String filter="";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String)params.get("year");
		String month = (String)params.get("month");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fdate, tdate;
		
		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
			whereCondition = "where c.gh_id ='" + cityId + "'";
		}
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND d.block_id='" + ghId + "'";
		}
		if (!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA") && formDate != null ? !formDate.isEmpty() : false) {
			try {
				fdate = sdf.parse(formDate);
				tdate = sdfrmt.parse(toDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			formDate = sdf1.format(fdate);
			toDate = sdf1.format(tdate);

			filter =  " AND a.replied_on between date('" + formDate + "') and date('"
					+ toDate + "')";
		}
		if (!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA") && year != null ? !year.isEmpty() : false) {
			filter = "and replied_on::text LIKE('" + year+"-"+month+"%')";
		}

		sql = sql+filter+join+whereCondition+groupBy;
		try {
			users = jdbcTemplate.query(sql, new RowMapper<FeedbackViewModel>() {
				@Override
				public FeedbackViewModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					FeedbackViewModel booking = new FeedbackViewModel();
					booking.setGuestHouse(rs.getString("block_name"));
					booking.setCityName(rs.getString("gh_name"));
					booking.setCheckInExc(rs.getInt("checkout_excellent"));
					booking.setCheckInExcPercent(rs.getInt("checkout_excellent_percent"));
					booking.setCheckInGood(rs.getInt("checkout_good"));
					booking.setCheckInGoodPercent(rs.getInt("checkout_good_percent"));
					booking.setCheckInAvg(rs.getInt("checkout_average"));
					booking.setCheckInAvgPercent(rs.getInt("checkout_average_percent"));
					booking.setCheckInPoor(rs.getInt("checkout_poor"));
					booking.setCheckInPoorPercent(rs.getInt("checkout_poor_percent"));
					booking.setCheckInVpoor(rs.getInt("checkout_verypoor"));
					booking.setCheckInVpoorPercent(rs.getInt("checkout_verypoor_percent"));

					booking.setUpKeepExc(rs.getInt("keep_room_excellent"));
					booking.setUpKeepExcPercent(rs.getInt("keep_room_excellent_percent"));
					booking.setUpkeepGood(rs.getInt("keep_room_good"));
					booking.setUpkeepGoodPercent(rs.getInt("keep_room_good_percent"));
					booking.setUpKeepAvg(rs.getInt("keep_room_average"));
					booking.setUpKeepAvgPercent(rs.getInt("keep_room_average_percent"));
					booking.setUpKeepPoor(rs.getInt("keep_room_poor"));
					booking.setUpKeepPoorPercent(rs.getInt("keep_room_poor_percent"));
					booking.setUpKeepVpoor(rs.getInt("keep_room_verypoor"));
					booking.setUpKeepVpoorPercent(rs.getInt("keep_room_verypoor_percent"));

					booking.setCleanExc(rs.getInt("cleanliness_hygiene_excellent"));
					booking.setCleanExcPercent(rs.getInt("cleanliness_hygiene_excellent_percent"));
					booking.setCleanGood(rs.getInt("cleanliness_hygiene_good"));
					booking.setCleanGoodPercent(rs.getInt("cleanliness_hygiene_good_percent"));
					booking.setCleanAvg(rs.getInt("cleanliness_hygiene_average"));
					booking.setCleanAvgPercent(rs.getInt("cleanliness_hygiene_average_percent"));
					booking.setCleanPoor(rs.getInt("cleanliness_hygiene_poor"));
					booking.setCleanPoorPercent(rs.getInt("cleanliness_hygiene_poor_percent"));
					booking.setCleanVpoor(rs.getInt("cleanliness_hygiene_verypoor"));
					booking.setCleanVpoorPercent(rs.getInt("cleanliness_hygiene_verypoor_percent"));

					booking.setQualityExc(rs.getInt("quality_food_excellent"));
					booking.setQualityExcPercent(rs.getInt("quality_food_excellent_percent"));
					booking.setQualityGood(rs.getInt("quality_food_good"));
					booking.setQualityGoodPercent(rs.getInt("quality_food_good_percent"));
					booking.setQualityAvg(rs.getInt("quality_food_average"));
					booking.setQualityAvgPercent(rs.getInt("quality_food_average_percent"));
					booking.setQualityPoor(rs.getInt("quality_food_poor"));
					booking.setQualityPoorPercent(rs.getInt("quality_food_poor_percent"));
					booking.setQualityVpoor(rs.getInt("quality_food_verypoor"));
					booking.setQualityVpoorPercent(rs.getInt("quality_food_verypoor_percent"));

					booking.setConductExc(rs.getInt("conduct_of_staff_excellent"));
					booking.setConductExcPercent(rs.getInt("conduct_of_staff_excellent_percent"));
					booking.setConductGood(rs.getInt("conduct_of_staff_good"));
					booking.setConductGoodPercent(rs.getInt("conduct_of_staff_good_percent"));
					booking.setConductAvg(rs.getInt("conduct_of_staff_average"));
					booking.setConductAvgPercent(rs.getInt("conduct_of_staff_average_percent"));
					booking.setConductPoor(rs.getInt("conduct_of_staff_poor"));
					booking.setConductPoorPercent(rs.getInt("conduct_of_staff_poor_percent"));
					booking.setConductVpoor(rs.getInt("conduct_of_staff_verypoor"));
					booking.setConductVpoorPercent(rs.getInt("conduct_of_staff_verypoor_percent"));

					booking.setOverallExc(rs.getInt("overall_excellent"));
					booking.setOverallExcPercent(rs.getInt("overall_excellent_percent"));
					booking.setOverallGood(rs.getInt("overall_good"));
					booking.setOverallGoodPercent(rs.getInt("overall_good_percent"));
					booking.setOverallAvg(rs.getInt("overall_average"));
					booking.setOverallAvgPercent(rs.getInt("overall_average_percent"));
					booking.setOverallPoor(rs.getInt("overall_poor"));
					booking.setOverallPoorPercent(rs.getInt("overall_poor_percent"));
					booking.setOverallVpoor(rs.getInt("overall_verypoor"));
					booking.setOverallVpoorPercent(rs.getInt("overall_verypoor_percent"));

					return booking;
				}
			});
			System.out.println("query-----" + sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<FeedbackViewModel> getTotalFeedbackReport(Map<String, Object> params) {
		System.out.println("total feedback report    "+params.toString());
		List<FeedbackViewModel> users = new ArrayList<FeedbackViewModel>();
		 List<FeedbackViewModel> users1=new ArrayList<FeedbackViewModel>();

		String anotherCondition = "and req_id IN(select req_id from tr_checkin where (status ='CIN' or status='COUT' or status='BILL'))) aa";

		String sql2 = approverQueries.getProperty("totalFeedbackReport");
		String sql3 = approverQueries.getProperty("totalFeedbackReportForTotal");
		String whereCondition2 = "";
		String groupBy = "GROUP BY c.gh_id,c.gh_name,d.block_id ,d.block_name order by block_id";
		String filter="";
		String join="join gh_details c on c.gh_id=d.gh_id ";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String) params.get("year");
		String month = (String) params.get("month");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fdate, tdate;
		
		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
			whereCondition2 = "where d.gh_id ='" + cityId + "'";
		}
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			whereCondition2 = whereCondition2 + " AND a.block_id='" + ghId + "'";
		}
		if (!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA") && formDate != null ? !formDate.isEmpty() : false) {
			try {
				fdate = sdf.parse(formDate);
				tdate = sdfrmt.parse(toDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			formDate = sdf1.format(fdate);
			toDate = sdf1.format(tdate);

			filter =  " and a.submitted_on between date('" + formDate + "') and date('"
					+ toDate + "')";
		}
		if (!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA") && year != null ? !year.isEmpty() : false) {
			filter = " and a.submitted_on::text LIKE('" + year+"-"+month+"%')";
		}

		sql2 = sql2+filter+join+whereCondition2+groupBy;
		try {
			users = jdbcTemplate.query(sql2, new RowMapper<FeedbackViewModel>() {
				@Override
				public FeedbackViewModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					FeedbackViewModel booking = new FeedbackViewModel();
					booking.setCityName(rs.getString("gh_name"));
					booking.setGuestHouse(rs.getString("block_name"));
					booking.setGhId(rs.getString("gh_id"));
					booking.setBlockId(rs.getString("block_id"));
					String sql = approverQueries.getProperty("totalRowOfTotalFeedbackReport");
					String whereCondition = " where 1=1 ";
					String filter ="";
					String formDate = (String) params.get("fromDate");
					String toDate = (String) params.get("toDate");
					
					
					if (booking.getGhId() != null ? !booking.getGhId().isEmpty() : false) {
						whereCondition = "where gh_id='" + booking.getGhId() + "'";
					}
					if (booking.getBlockId() != null ? !booking.getBlockId().isEmpty() : false) {
						whereCondition =whereCondition + "AND block_id='" + booking.getBlockId() + "'";
					}

					if ((!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA")) && formDate != null ? !formDate.isEmpty() : false) {
						System.out.println("inside form and todate block where city and guest house will be not null");
						Date fdate, tdate;
						try {
							fdate = sdf.parse(formDate);
							tdate = sdfrmt.parse(toDate);
						} catch (ParseException e) {
							throw new RuntimeException(e);
						}
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
						formDate = sdf1.format(fdate);
						toDate = sdf1.format(tdate);
						filter =  " and  from_date between date('" + formDate + "') and date('" + toDate
								+ "')";
					}

					if ((!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA")) && year != null ? !year.isEmpty() : false) {
						filter =  " and from_date::text LIKE('"+ year+"-"+month+"%')";
					}

					sql = sql + whereCondition+filter + anotherCondition;

					Integer totalNumOfRow = jdbcTemplate.queryForObject(sql,Integer.class);
					System.out.println("sql "+sql);
					System.out.println("Total number of row"+totalNumOfRow);
					booking.setTotalRow(totalNumOfRow);
					booking.setTotalNumFeedback(rs.getInt("total_no_of_feedbacks"));
					booking.setCheckInExc(rs.getInt("checkout_excellent"));
					booking.setCheckInGood(rs.getInt("checkout_good"));
					booking.setCheckInAvg(rs.getInt("checkout_average"));
					booking.setCheckInPoor(rs.getInt("checkout_poor"));
					booking.setCheckInVpoor(rs.getInt("checkout_verypoor"));

					booking.setUpKeepExc(rs.getInt("keep_room_excellent"));
					booking.setUpkeepGood(rs.getInt("keep_room_good"));
					booking.setUpKeepAvg(rs.getInt("keep_room_average"));
					booking.setUpKeepPoor(rs.getInt("keep_room_poor"));
					booking.setUpKeepVpoor(rs.getInt("keep_room_verypoor"));

					booking.setCleanExc(rs.getInt("cleanliness_hygiene_excellent"));
					booking.setCleanGood(rs.getInt("cleanliness_hygiene_good"));
					booking.setCleanAvg(rs.getInt("cleanliness_hygiene_average"));
					booking.setCleanPoor(rs.getInt("cleanliness_hygiene_poor"));
					booking.setCleanVpoor(rs.getInt("cleanliness_hygiene_verypoor"));

					booking.setQualityExc(rs.getInt("quality_food_excellent"));
					booking.setQualityGood(rs.getInt("quality_food_good"));
					booking.setQualityAvg(rs.getInt("quality_food_average"));
					booking.setQualityPoor(rs.getInt("quality_food_poor"));
					booking.setQualityVpoor(rs.getInt("quality_food_verypoor"));

					booking.setConductExc(rs.getInt("conduct_of_staff_excellent"));
					booking.setConductGood(rs.getInt("conduct_of_staff_good"));
					booking.setConductAvg(rs.getInt("conduct_of_staff_average"));
					booking.setConductPoor(rs.getInt("conduct_of_staff_poor"));
					booking.setConductVpoor(rs.getInt("conduct_of_staff_verypoor"));

					booking.setOverallExc(rs.getInt("overall_excellent"));
					booking.setOverallGood(rs.getInt("overall_good"));
					booking.setOverallAvg(rs.getInt("overall_average"));
					booking.setOverallPoor(rs.getInt("overall_poor"));
					booking.setOverallVpoor(rs.getInt("overall_verypoor"));

					return booking;
				}
			});
			System.out.println("query-----" + sql2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(users.size()==6 || users.size()==3) {
			String whereCondition3 ="where 1=1";
			if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
				whereCondition3="where gh_id ='"+cityId+"'";
			}
			sql3= sql3+whereCondition3+filter;
			try {
				users1 = jdbcTemplate.query(sql3, new RowMapper<FeedbackViewModel>() {
					@Override
					public FeedbackViewModel mapRow(ResultSet rs, int rownumber) throws SQLException {
						FeedbackViewModel booking = new FeedbackViewModel();
						booking.setCityName("");
						booking.setGuestHouse("Total");
						String sql = approverQueries.getProperty("totalRowOfTotalFeedbackReport");
						String whereCondition = " where 1=1 ",filter="";
						String formDate = (String) params.get("fromDate");
						String toDate = (String) params.get("toDate");
						if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false) {
							whereCondition = "where gh_id='" + cityId + "'";
						}

						if ((!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA")) && formDate != null ? !formDate.isEmpty() : false) {
							System.out.println("inside form and todate block where city and guest house will be not null");
							Date fdate, tdate;
							try {
								fdate = sdf.parse(formDate);
								tdate = sdfrmt.parse(toDate);
							} catch (ParseException e) {
								throw new RuntimeException(e);
							}
							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
							// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
							formDate = sdf1.format(fdate);
							toDate = sdf1.format(tdate);
							filter =  " where  from_date between date('" + formDate + "') and date('" + toDate
									+ "')";
						}

						if ((!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA")) && year != null ? !year.isEmpty() : false) {
							filter = " where from_date::text LIKE('"+ year+"-"+month+"%')";
						}

						sql = sql + whereCondition + anotherCondition+filter;
						System.out.println("sql  "+sql);
						Integer totalNumOfRow = jdbcTemplate.queryForObject(sql,Integer.class);
						System.out.println("sql "+sql);
						System.out.println("Total number of row   "+totalNumOfRow);
						booking.setTotalRow(totalNumOfRow);
						booking.setTotalNumFeedback(rs.getInt("total_no_of_feedbacks"));
						booking.setCheckInExc(rs.getInt("checkout_excellent"));
						booking.setCheckInGood(rs.getInt("checkout_good"));
						booking.setCheckInAvg(rs.getInt("checkout_average"));
						booking.setCheckInPoor(rs.getInt("checkout_poor"));
						booking.setCheckInVpoor(rs.getInt("checkout_verypoor"));

						booking.setUpKeepExc(rs.getInt("keep_room_excellent"));
						booking.setUpkeepGood(rs.getInt("keep_room_good"));
						booking.setUpKeepAvg(rs.getInt("keep_room_average"));
						booking.setUpKeepPoor(rs.getInt("keep_room_poor"));
						booking.setUpKeepVpoor(rs.getInt("keep_room_verypoor"));

						booking.setCleanExc(rs.getInt("cleanliness_hygiene_excellent"));
						booking.setCleanGood(rs.getInt("cleanliness_hygiene_good"));
						booking.setCleanAvg(rs.getInt("cleanliness_hygiene_average"));
						booking.setCleanPoor(rs.getInt("cleanliness_hygiene_poor"));
						booking.setCleanVpoor(rs.getInt("cleanliness_hygiene_verypoor"));

						booking.setQualityExc(rs.getInt("quality_food_excellent"));
						booking.setQualityGood(rs.getInt("quality_food_good"));
						booking.setQualityAvg(rs.getInt("quality_food_average"));
						booking.setQualityPoor(rs.getInt("quality_food_poor"));
						booking.setQualityVpoor(rs.getInt("quality_food_verypoor"));

						booking.setConductExc(rs.getInt("conduct_of_staff_excellent"));
						booking.setConductGood(rs.getInt("conduct_of_staff_good"));
						booking.setConductAvg(rs.getInt("conduct_of_staff_average"));
						booking.setConductPoor(rs.getInt("conduct_of_staff_poor"));
						booking.setConductVpoor(rs.getInt("conduct_of_staff_verypoor"));

						booking.setOverallExc(rs.getInt("overall_excellent"));
						booking.setOverallGood(rs.getInt("overall_good"));
						booking.setOverallAvg(rs.getInt("overall_average"));
						booking.setOverallPoor(rs.getInt("overall_poor"));
						booking.setOverallVpoor(rs.getInt("overall_verypoor"));

						return booking;
					}
				});
				System.out.println("query-----" + sql2);
				users.addAll(users1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return users;
	}
	@Override
	public List<FeedbackRequestModel> guestFeedbackReportDetails(Map<String, Object> params)  throws Exception {
		System.out.println("admin dao     "+params.toString());
		System.out.println("form date in dao "+params.get("formDate"));
		System.out.println("to date in dao"+params.get("todate"));
		List<FeedbackRequestModel> users=new ArrayList<FeedbackRequestModel>();
		String sql = approverQueries.getProperty("guestFeedbackReport");
		String sql3 =  approverQueries.getProperty("guestHouseName");
		String sql4 =  approverQueries.getProperty("cityname");
		String whereCondition = "";
		String groupBy = "GROUP BY room_block.block_name,gh_details.gh_name,guest_details.req_id,guest_details.guest_id,gad_request.req_id,feedback_system.feedback_id";
		String orderBy = " order by req_date desc)f1  where f1.i =1";
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDatee = (String) params.get("formDate");
		String toDatee =  (String) params.get("todate");
		String category = (String) params.get("category");
		String feedback =(String) params.get("feedback");
		String sql2 ="select description from feedback_master where id="+feedback+"";
		
		System.out.println("dates "+params.get("formDate")+"  "+params.get("todate") );
		String guestHouseName=jdbcTemplate.queryForObject(sql3, String.class,ghId);
		String cityName =jdbcTemplate.queryForObject(sql4,String.class,cityId);
		
		
		if(!cityId.equalsIgnoreCase("NA") && !ghId.equalsIgnoreCase("NA")  && cityId != null &&  ghId != null ) {
		whereCondition = "where feedback_system.gh_id='"+cityId+"' and feedback_system.block_id='"+ghId+"' and feedback_system.status='Closed'";
		}

		if ( (formDatee != null  && toDatee != null) &&
				(!formDatee.equalsIgnoreCase("NA") && !toDatee.equalsIgnoreCase("NA")) ) {
			System.out.println("inside form and todate block where city and guest house will be not null");
			Date fdate, tdate;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
			try {
				fdate = sdf.parse(formDatee);
				tdate = sdfrmt.parse(toDatee);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			formDatee = sdf1.format(fdate);
			toDatee = sdf1.format(tdate);
			whereCondition = whereCondition + "and  feedback_system.replied_on between date('" + formDatee
					+ "') and date('" + toDatee + "')"; 
		}
		if(!category.equalsIgnoreCase("NA") && !feedback.equalsIgnoreCase("NA")  && category != null &&  feedback != null ) {
			whereCondition = whereCondition + "and " + category + "=" + feedback + "";
			}


		System.out.println("cityId"+cityId+"GuestHouse"+ghId);
		String feedbackDesc = jdbcTemplate.queryForObject(sql2, String.class);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		System.out.println("wherecondition   "+whereCondition);
		sql=sql+whereCondition+groupBy+orderBy;
		try {
	      users = jdbcTemplate.query(sql, new RowMapper<FeedbackRequestModel>() {
			@Override
			public FeedbackRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				FeedbackRequestModel feedback = new FeedbackRequestModel();
				feedback.setCityNameDesc(cityName);
				feedback.setGuestHouseDesc(guestHouseName);
				feedback.setBlock_name(rs.getString("block_name"));
				feedback.setGh_name(rs.getString("gh_name"));
				feedback.setRequestId(rs.getString("req_id"));
				feedback.setName(rs.getString("name"));
				feedback.setMobile(rs.getString("mobile_no"));

				String submitDt = rs.getDate("submitted_on") != null ? sdf.format(rs.getTimestamp("submitted_on")): null;
				String replyedOn = rs.getDate("replied_on") != null ? sdf1.format(rs.getTimestamp("replied_on")): null;

				feedback.setSubmittedOn(submitDt);
				feedback.setRepliedOn(replyedOn);
				feedback.setFeedback(feedbackDesc);
				feedback.setCategory(category);
				feedback.setStatus(rs.getString("feedbackstatus"));
				return feedback;
			}
		});
	      System.out.println("query for guestfeedback   "+sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<FeedbackPojo> getCommentFeedbackReportDetails(Map<String, Object> params) {

		System.out.println(params.toString());
		List<FeedbackPojo> users=new ArrayList<FeedbackPojo>();

		String sql = approverQueries.getProperty("commentReportSQL");
		String whereCondition = "";
		String groupBy = "GROUP BY c.gh_id,c.gh_name,d.block_id,d.block_name order by block_id";
		String join="join gh_details c on c.gh_id=d.gh_id ";
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
			whereCondition = "where d.gh_id ='" + cityId + "'";
		}
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND a.block_id='" + ghId + "'";
		} 
		sql = sql+join+whereCondition+groupBy;

		try {
			  users = jdbcTemplate.query(sql, new RowMapper<FeedbackPojo>() {
					@Override
					public FeedbackPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
						FeedbackPojo feedback = new FeedbackPojo();
						feedback.setBlockId(rs.getString("block_id"));
						feedback.setBlock_name(rs.getString("block_name"));
						feedback.setGh_name(rs.getString("gh_name"));
						return feedback;
			}
		});
			users.forEach(item -> {
			List<FeedbackRequestModel> feedbackReq;
			try {
				feedbackReq = getFeedbackPojoDetails(item.getBlockId(),params);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			item.setList(feedbackReq);
		});
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("sql for comment report    "+sql);
		return users;
	}

	public List<FeedbackRequestModel> getFeedbackPojoDetails(String blockId,Map<String, Object> params) throws Exception {
		String sql = "select submitted_on,replied_on,feedback_sms,replied_sms from feedback_system where block_id='"+blockId+"'";
		String filter="";

		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year = (String) params.get("year");
		String month = (String) params.get("month");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fdate, tdate;

		if (!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA") && formDate != null ? !formDate.isEmpty() : false) {
			try {
				fdate = sdf.parse(formDate);
				tdate = sdfrmt.parse(toDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			formDate = sdf1.format(fdate);
			toDate = sdf1.format(tdate);

			filter =  " and submitted_on between date('" + formDate + "') and date('" + toDate + "')";
		}
		if (!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA") && year != null ? !year.isEmpty() : false) {
			filter = " and submitted_on::text LIKE('" + year+"-"+month+"%')";
		}
		
		sql = sql + filter;
		List<FeedbackRequestModel> users = jdbcTemplate.query(sql, new RowMapper<FeedbackRequestModel>() {
			@Override
			public FeedbackRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				FeedbackRequestModel feedbackReq = new FeedbackRequestModel();
				String submitDt = rs.getDate("submitted_on") != null ? sdf.format(rs.getTimestamp("submitted_on")): null;
				String replyedOn = rs.getDate("replied_on") != null ? sdf1.format(rs.getTimestamp("replied_on")): null;
				feedbackReq.setSubmittedOn(submitDt);
				feedbackReq.setRepliedSms(rs.getString("replied_sms"));
				feedbackReq.setFeedbacksms(rs.getString("feedback_sms"));
				feedbackReq.setRepliedOn(replyedOn);
				return feedbackReq;
			}
		});
		System.out.println("comment feedback report queries    "+sql);
		return users;
	}
	@Override
	public List<FeedbackPojo> getTotalAllotmentList(Map<String, Object> params) {

		System.out.println(params.toString());
		List<FeedbackPojo> users=new ArrayList<FeedbackPojo>();
		List<FeedbackPojo> totalList=new ArrayList<FeedbackPojo>();
		String sqlForTotal =approverQueries.getProperty("allotmentReportForTotalSQL");
		String sql = approverQueries.getProperty("allotmentReportSQL");
		String whereCondition = "";
		String join =" join gh_details c on c.gh_id=a.gh_id ";
		String filter="";
		String groupBy=" group by a.gh_id,c.gh_name,a.block_id,a.block_name order by block_id";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year =  (String) params.get("year");
		String month =  (String) params.get("month");


		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fdate, tdate;
		
		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
			whereCondition = "where b.gh_id ='" + cityId + "'";
		}
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.block_id='" + ghId + "'";
		}
		if (!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA") && formDate != null ? !formDate.isEmpty() : false) {
			try {
				fdate = sdf.parse(formDate);
				tdate = sdfrmt.parse(toDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			formDate = sdf1.format(fdate);
			toDate = sdf1.format(tdate);

			filter =  " and  from_date between date('" + formDate + "') and date('" + toDate + "')";
		}
		if (!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA") && year != null ? !year.isEmpty() : false) {
			filter = " and from_date::text LIKE '" + year+"-"+month + "%'";
		}

		sql = sql+filter+join+whereCondition+groupBy;
		try {
			  users = jdbcTemplate.query(sql, new RowMapper<FeedbackPojo>() {
					@Override
					public FeedbackPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
						FeedbackPojo feedback = new FeedbackPojo();
						feedback.setBlockId(rs.getString("block_id"));
						String sqlForRoomAvl = approverQueries.getProperty("actualRoomAvlSQL");
						String whereCondition = "and block_id='"+feedback.getBlockId()+"'";
						sqlForRoomAvl =  sqlForRoomAvl + whereCondition;
						Integer actualRoomCount = jdbcTemplate.queryForObject(sqlForRoomAvl,Integer.class); 
						feedback.setActualRoomForUser(actualRoomCount);
						feedback.setBlock_name(rs.getString("block_name"));
						feedback.setGh_name(rs.getString("gh_name"));
						feedback.setTotalApplicationOnline(rs.getInt("total_application_online"));
						feedback.setTotalApplicationOffline(rs.getInt("total_application_offline"));
						feedback.setTotalAllotOfficial(rs.getInt("total_allotment_official"));
						feedback.setTotalAllotNonOfficial(rs.getInt("total_allotment_private"));
						feedback.setTotalAllotStudent(rs.getInt("total_allotment_student"));
						feedback.setTotalAllotPatient(rs.getInt("total_allotment_patient"));
						feedback.setActualOccupancy(rs.getInt("total_occupancy"));
						int totalOfficialPrcnt= rs.getInt("total_allotment_official");
						int totalNonOfficialPrcnt = rs.getInt("total_allotment_private");
						int totalNoStudent = rs.getInt("total_allotment_student");
						int totalNoPatient = rs.getInt("total_allotment_patient");
						int actualOccupancy = rs.getInt("total_occupancy");
						String finalPercentage = "";
						 int occupancy_percentage=totalOfficialPrcnt+totalNonOfficialPrcnt+totalNoStudent+totalNoPatient;
						    if (occupancy_percentage==0 && actualOccupancy==0){
						    	occupancy_percentage=1;
						    	int occupancy_percentage2=actualOccupancy*100/occupancy_percentage;
						    	finalPercentage=occupancy_percentage2+"%";

						    }else{
						    int occupancy_percentage2=actualOccupancy*100/occupancy_percentage;
						    finalPercentage=occupancy_percentage2+"%";
						  }
						 feedback.setOccupancyPercentage(finalPercentage);
						return feedback;
			}
		});

		}catch(Exception e) {
			e.printStackTrace();
		}
		if(users.size()==6 || users.size()==3) {
			String whereCondition1 = "where 1=1";
			if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
				whereCondition1="where gh_id ='"+cityId+"'";
			}
			sqlForTotal= sqlForTotal + whereCondition1 + filter;
			try {
				totalList = jdbcTemplate.query(sqlForTotal, new RowMapper<FeedbackPojo>() {
						@Override
						public FeedbackPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
							FeedbackPojo feedback = new FeedbackPojo();
							feedback.setGh_name("");
							feedback.setBlock_name("total");
							String sqlForTotalRoomAvl = approverQueries.getProperty("actualRoomAvlSQL");
							String whereCondition1 = "";
							if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
								whereCondition1="and gh_id ='"+cityId+"'";
							}
							sqlForTotalRoomAvl =  sqlForTotalRoomAvl + whereCondition1;
							Integer actualRoomCount = jdbcTemplate.queryForObject(sqlForTotalRoomAvl,Integer.class); 
							feedback.setActualRoomForUser(actualRoomCount);
							feedback.setTotalApplicationOnline(rs.getInt("total_application_online"));
							feedback.setTotalApplicationOffline(rs.getInt("total_application_offline"));
							feedback.setTotalAllotOfficial(rs.getInt("total_allotment_official"));
							feedback.setTotalAllotNonOfficial(rs.getInt("total_allotment_private"));
							feedback.setTotalAllotStudent(rs.getInt("total_allotment_student"));
							feedback.setTotalAllotPatient(rs.getInt("total_allotment_patient"));
							feedback.setActualOccupancy(rs.getInt("total_occupancy"));
							int totalOfficialPrcnt= rs.getInt("total_allotment_official");
							int totalNonOfficialPrcnt = rs.getInt("total_allotment_private");
							int totalNoStudent = rs.getInt("total_allotment_student");
							int totalNoPatient = rs.getInt("total_allotment_patient");
							int actualOccupancy = rs.getInt("total_occupancy");
							String finalPercentage = "";
							 int occupancy_percentage=totalOfficialPrcnt+totalNonOfficialPrcnt+totalNoStudent+totalNoPatient;
							    if (occupancy_percentage==0 && actualOccupancy==0){
							    	occupancy_percentage=1;
							    	int occupancy_percentage2=actualOccupancy*100/occupancy_percentage;
							    	finalPercentage=occupancy_percentage2+"%";

							    }else{
							    int occupancy_percentage2=actualOccupancy*100/occupancy_percentage;
							    finalPercentage=occupancy_percentage2+"%";
							  }
							feedback.setOccupancyPercentage(finalPercentage);
							return feedback;
				}
			});

			}catch(Exception e) {
				e.printStackTrace();
			}
			users.addAll(totalList);
		}
		System.out.println("sql for allotment report    "+sql);
		return users;
	}
	@Override
	public List<FeedbackPojo> getFinalReport (Map<String, Object> params) {

		System.out.println(params.toString());
		List<FeedbackPojo> users=new ArrayList<FeedbackPojo>();
		List<FeedbackPojo> totalList = new ArrayList<FeedbackPojo>();
		String sql = approverQueries.getProperty("finalreportSQL");
		String sqlForTotal =  approverQueries.getProperty("finalreportTotalSQL");
		String whereCondition = "";
		
		String filter="";
		String join = " left join gh_details c on a.gh_id=c.gh_id";
		String groupBy=" group by a.gh_id,c.gh_name,a.block_id,a.block_name order by a.block_id";
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String formDate = (String) params.get("fromDate");
		String toDate = (String) params.get("toDate");
		String year =  (String) params.get("year");
		String month =  (String) params.get("month");
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date fdate, tdate;
		
		Map<String, Object> queryParams = new HashMap<>();
		
		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
			whereCondition = " where a.gh_id=:cityId";
	        queryParams.put("cityId", cityId);
		}
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			whereCondition += " AND a.block_id=:ghId";
	        queryParams.put("ghId", ghId);
		}
		if (!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA") && formDate != null ? !formDate.isEmpty() : false) {
			try {
				fdate = sdf.parse(formDate);
				tdate = sdfrmt.parse(toDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			formDate = sdf1.format(fdate);
			toDate = sdf1.format(tdate);

			filter = " and from_date between date(:formDate) and date(:toDate)";
	        queryParams.put("formDate", formDate);
	        queryParams.put("toDate", toDate);
		}
		if (!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA") && year != null ? !year.isEmpty() : false) {
			filter = " and from_date::text LIKE :yearMonth";
	        queryParams.put("yearMonth", year + "-" + month + "%");
		}

		sql = sql+filter+join+whereCondition+groupBy;
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
			users = namedParameterJdbcTemplate.query(sql, queryParams, new RowMapper<FeedbackPojo>() {
					@Override
					public FeedbackPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
						FeedbackPojo feedback = new FeedbackPojo();
						feedback.setBlockId(rs.getString("block_id"));
						feedback.setCityId(rs.getString("gh_id"));
						feedback.setBlock_name(rs.getString("block_name"));	
						feedback.setGh_name(rs.getString("gh_name"));
						feedback.setTotalApplicationOnline(rs.getInt("total_application_online"));
						feedback.setTotalApplicationOffline(rs.getInt("total_application_offline"));
						feedback.setTotalAllotOfficial(rs.getInt("total_allotment_online"));
						feedback.setTotalAllotNonOfficial(rs.getInt("total_allotment_offline"));
						int onlineAllot = Integer.parseInt(rs.getString("total_allotment_online"));
						int  offilineAlllot= Integer.parseInt(rs.getString("total_allotment_offline"));
						int totalAllot = onlineAllot+offilineAlllot;
						feedback.setActualOccupancy(totalAllot);
						feedback.setTotalNoRooms(rs.getInt("total_no_of_rooms"));
						feedback.setCheckInCounts(rs.getInt("check_in"));
						feedback.setCheckoutCounts(rs.getInt("check_out"));
						feedback.setFeedbackCounts(rs.getInt("feedback_received"));
						String sqlForRoomAvl = approverQueries.getProperty("actualRoomAvlSQL");
						String whereCondition = "and block_id='"+feedback.getBlockId()+"'";
						sqlForRoomAvl =  sqlForRoomAvl + whereCondition;
						Integer actualRoomCount = jdbcTemplate.queryForObject(sqlForRoomAvl,Integer.class); 
						feedback.setActualRoomForUser(actualRoomCount);
						System.out.println(" sqlForRoomAvl  "+sqlForRoomAvl);
						return feedback;
			}
		});

		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("sql for final report    "+sql);
		if(users.size()==6 || users.size()==3) {
			String whereCondition1 = "where 1=1";
			if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
				whereCondition1 = "where gh_id=:cityId";
	            queryParams.put("cityId", cityId);
			}
			sqlForTotal= sqlForTotal+whereCondition1+filter;
			try {
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
				totalList = namedParameterJdbcTemplate .query(sqlForTotal, queryParams, new RowMapper<FeedbackPojo>() {
						@Override
						public FeedbackPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
							FeedbackPojo feedback = new FeedbackPojo();
							feedback.setGh_name("");
							feedback.setBlock_name("total");
							String sqlForTotalRoomAvl = approverQueries.getProperty("actualRoomAvlSQL");
							String whereCondition1 = "";
							Date fdate, tdate;
							String formDate = (String) params.get("fromDate");
							String toDate = (String) params.get("toDate");
							if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
								whereCondition1 = " and gh_id=:cityId";
								queryParams.put("cityId", cityId);
							}
							if (!formDate.equalsIgnoreCase("NA") && !toDate.equalsIgnoreCase("NA") && formDate != null ? !formDate.isEmpty() : false) {
								try {
									fdate = sdf.parse(formDate);
									tdate = sdfrmt.parse(toDate);
								} catch (ParseException e) {
									throw new RuntimeException(e);
								}
								// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
								formDate = sdf1.format(fdate);
								toDate = sdf1.format(tdate);
							}
							if (!year.equalsIgnoreCase("NA") && !month.equalsIgnoreCase("NA") && year != null ? !year.isEmpty() : false) {
							}
							sqlForTotalRoomAvl =  sqlForTotalRoomAvl + whereCondition1;
							NamedParameterJdbcTemplate namedParameterJdbcTemplate =  new NamedParameterJdbcTemplate(jdbcTemplate);
							Integer actualRoomCount = namedParameterJdbcTemplate.queryForObject(sqlForTotalRoomAvl, queryParams, Integer.class); 
							feedback.setActualRoomForUser(actualRoomCount);
							feedback.setTotalApplicationOnline(rs.getInt("total_application_online"));
							feedback.setTotalApplicationOffline(rs.getInt("total_application_offline"));
							feedback.setTotalAllotOfficial(rs.getInt("total_allotment_online"));
							feedback.setTotalAllotNonOfficial(rs.getInt("total_allotment_offline"));
							int onlineAllot = Integer.parseInt(rs.getString("total_allotment_online"));
							int  offilineAlllot= Integer.parseInt(rs.getString("total_allotment_offline"));
							int totalAllot = onlineAllot+offilineAlllot;
							feedback.setActualOccupancy(totalAllot);
							feedback.setCheckInCounts(rs.getInt("check_in"));
							feedback.setCheckoutCounts(rs.getInt("check_out"));
							feedback.setFeedbackCounts(rs.getInt("feedback_received"));
							return feedback;
				}
						
			});
				System.out.println("query for total check in "+sqlForTotal);
			}catch(Exception e) {
				e.printStackTrace();
			}
			users.addAll(totalList);
		}
		return users;
	}
	@Override
	public List<GuestDetailsResponseForOrder> getAllConveyanceDetailsByProcessId(String processId, String type) {
		
		List<GuestDetailsResponseForOrder> data;
		String query = adminQueries.getProperty("officeOrderConveyanceList");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		logger.info(query);
		try {
			data = jdbcTemplate.query(query, new RowMapper<GuestDetailsResponseForOrder>() {
				public GuestDetailsResponseForOrder mapRow(ResultSet rs, int rownumber) throws SQLException {
					GuestDetailsResponseForOrder pm = new GuestDetailsResponseForOrder();
					String reqId = rs.getString("req_id");
					pm.setReqId(reqId);				
					String duringStay =  rs.getString("during_stay_conveancy");
					if(duringStay.equalsIgnoreCase("0")) {
						pm.setDuringStayConveynce("No");
					}else {
						pm.setDuringStayConveynce("Yes");
					}
					pm.setConveynceFrom(rs.getString("is_veh_req"));
					pm.setConveynceTo(rs.getString("is_veh_req1"));
					String arrDate = rs.getString("arrival_time");
					String depDate = rs.getString("departure_time");
					String arrFlTrNo = rs.getString("ftno");
					String arrConvPlace = rs.getString("arrival_place");
					String depFlTrNo = rs.getString("departure_ftno");
					String depConvPlace = rs.getString("departure_place");										
					if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
						String arrivalDetails = "Flight/Train No :" + arrFlTrNo + " at " + arrDate + " in " + arrConvPlace;
						pm.setConveynceFromDatails(arrivalDetails);						
					} 
					if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
						String departDetails = "Flight/Train No :" + depFlTrNo + " at " + depDate + " in " + depConvPlace;
						pm.setConveynceToDetails(departDetails);
					}	
					String name = rs.getString("name");
					String addrss = rs.getString("main_address");
					if (addrss != null && !addrss.isEmpty()) {
						name += "," + addrss;
					}
					pm.setGuestName(name);
					return pm;
				}
			}, processId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("GuestDetailsResponseForOrder" + query);
		return data;
	}
	@Override
	public List<BookingResponseModel> getAllConferenceDetailsByProcessId(String processId, String type) {
		String status = "";
		//String groupBy = " GROUP BY a.req_id,a.process_id,b.name,c.from_date,c.to_date,a.status";
		List<BookingResponseModel> data;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String query = approverQueries.getProperty("getAllConferenceDetailsByProcessId");
		if (type.equalsIgnoreCase("DRAFT")) {
			status = " AND rmt.status = 'TCNF'";
		} else {
			status = " AND rmt.status = 'CNF'";
		}
		query = query + status;
		logger.info(query);
		try {
			data = jdbcTemplate.query(query, (rs, rownumber) -> {
				BookingResponseModel bookingResp = new BookingResponseModel();
				//bookingResp.setProcessId(rs.getString("process_id"));
				bookingResp.setBookingId(rs.getString("req_id"));
				String name=rs.getString("name");
				Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
				Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;
				bookingResp.setFromDate(sdf.format(fromDate));
				bookingResp.setToDate(sdf.format(toDate));
				String status_desc = "";
				if (rs.getString("status").equals("REQ")) {
					status_desc = " Not taken up for processing";
				}
				if (rs.getString("status").equals("CAN")) {
					status_desc = "Cancelled";
				}
				if (rs.getString("status").equalsIgnoreCase("MOD")) {
					status_desc = "Under processing";
				}
				if (rs.getString("status").equalsIgnoreCase("PRO")) {
					status_desc = "Approved";
				}
				if (rs.getString("status").equalsIgnoreCase("ALO")) {
					status_desc = "Allotted";
				}
				if (rs.getString("status").equals("CIN")) {
					status_desc = "Check In";
				}
				if (rs.getString("status").equals("COUT")) {
					status_desc = "Check Out";
				}
				if (rs.getString("status").equals("TCNF")) {
					status_desc = "Temporary Confirm";
				}
				if (rs.getString("status").equals("CNF")) {
					status_desc = "Confirm";
				}
				bookingResp.setStatus(status_desc);

				String addrss=rs.getString("address");
				if (addrss != null && !addrss.isEmpty()) {
					name += "," + addrss;
				}
				bookingResp.setName(name);
				bookingResp.setAddress(rs.getString("address"));
				bookingResp.setCapacity(rs.getString("capacity"));
				bookingResp.setConfUseDesc(rs.getString("conf_use_desc"));
				bookingResp.setConfCatDesc(rs.getString("conf_cat_desc"));
				bookingResp.setTotalAmt(rs.getString("charge"));
				String FormDate = sdf.format(fromDate);
				String ToDate = sdf.format(toDate);
				String periodOfStay = FormDate + " to " + ToDate;
				System.out.println("formdate and todate" + periodOfStay);
				return bookingResp;
			}, processId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		System.out.println("All Conference list by process Id=============== " + query);
		return data;
	}
	@Override
	public ReceiptModel getDuesInfoForBookingId(String bookingId) {
	
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String query = adminQueries.getProperty("getDuesListForBookingIdSQL");
		String sql = adminQueries.getProperty("getModeOfPayment");
		ReceiptModel response = new ReceiptModel();
		List<ReceiptModel> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<ReceiptModel>() {
			@Override
			public ReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				ReceiptModel tmp = new ReceiptModel();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String billDt = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
				String recptDt = rs.getDate("date_rece") != null ? sdf1.format(rs.getTimestamp("date_rece")):null;
				tmp.setGuestHouseName(rs.getString("block_name"));
				tmp.setCityName(rs.getString("gh_name"));
				tmp.setBookingId(rs.getString("req_id"));
				tmp.setBillNo(rs.getString("billno"));
				tmp.setName(rs.getString("name"));
				tmp.setFromDate(frDt);
				tmp.setToDate(toDt);
				tmp.setPaidAmount(rs.getString("balance"));				
				tmp.setDate(billDt);
				tmp.setOnlyName(rs.getString("onlyname"));
				tmp.setRectDate(recptDt);
				tmp.setMobileNo(rs.getString("mobile_no"));
				tmp.setBillId(rs.getString("bill_id"));
				tmp.setRecId(rs.getString("rec_id"));
				tmp.setPaymentModes(jdbcTemplate.queryForList(sql, tmp.getRecId()));
				tmp.setCategory(rs.getString("category"));
				tmp.setPurpose(rs.getString("item_title"));
				tmp.setTotal(rs.getString("balance"));
				tmp.setGuestAddress(rs.getString("address"));
				tmp.setGuestEmail(rs.getString("email_id"));
				tmp.setNoOfGuest(rs.getString("no_of_guest"));
				tmp.setGuestContact(rs.getString("mobile_no"));
				tmp.setGuestHouseAddress(rs.getString("gh_address"));
				tmp.setGuestHouseContactNum(rs.getString("contact_num"));
				tmp.setGuestHouseMail(rs.getString("gh_mail"));
				tmp.setRoomNo(rs.getString("room_no"));
				
				return tmp;
			}
		},bookingId);
		if(catgList != null) {
			if(catgList.size() != 0) {
				Integer sum = 0;
				String receiptId = "";
				Set<String> set = new HashSet<>();
				String guestName = "";
				Integer count = 0;
				for(ReceiptModel item : catgList) {
					count++;
					if(item.getDueAmount() != null) {
						sum = sum + Integer.parseInt(item.getDueAmount());
					}
					receiptId = receiptId + item.getRecId();
					if(count < catgList.size()) {
						receiptId = receiptId + ",";
					}
					set.add(item.getName());
				}
				for(String va : set) {
					guestName = guestName + va;
					if(count < catgList.size()) {
						guestName = guestName + ",";
					}
				}
				response.setPaymentModes(catgList.get(0).getPaymentModes());
				response.setGuestHouseName(catgList.get(0).getGuestHouseName());
				response.setCityName(catgList.get(0).getCityName());
				response.setRectDate(catgList.get(0).getRectDate());
				response.setGuest(catgList.get(0).getGuest());
				response.setPaidAmount(catgList.get(0).getPaidAmount());
				response.setName(guestName);
				response.setMode(catgList.get(0).getMode());
				response.setRecId(receiptId);
				response.setGuestEmail(catgList.get(0).getGuestEmail());
				response.setGuestContact(catgList.get(0).getGuestContact());
				response.setNoOfGuest(catgList.get(0).getNoOfGuest());
				response.setGuestHouseAddress(catgList.get(0).getGuestHouseAddress());
				response.setGuestHouseContactNum(catgList.get(0).getGuestHouseContactNum());
				response.setGuestHouseMail(catgList.get(0).getGuestHouseMail());
				response.setBookingId(catgList.get(0).getBookingId());
				response.setCategory(catgList.get(0).getCategory());
				response.setFromDate(catgList.get(0).getFromDate());
				response.setToDate(catgList.get(0).getToDate());
				response.setRoomNo(catgList.get(0).getRoomNo());
				response.setTotal(catgList.get(0).getTotal());
				response.setPurpose(catgList.get(0).getPurpose());
				response.setGuestAddress(catgList.get(0).getGuestAddress());
			}
		}
		System.out.println(" payment receipt query  "+query+"  req_id"+bookingId);
		System.out.println(" response "+response);
		return response;
		/*System.out.println("query for dues report  "+query);
		logger.info(data);
		return data;*/
	}
	@Override
	public ReceiptModel getReceiptInfoForRectId(String rectId) {
		
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String query = adminQueries.getProperty("getGeneratedReceiptList");
		String sql = adminQueries.getProperty("getModeOfPayment");
		String sql1 = adminQueries.getProperty("getGuestDetailsForReceipt");
		String whereConditon = "where r.rec_id= '"+rectId+"'";
		ReceiptModel response = new ReceiptModel();
		List<ReceiptModel> catgList = new ArrayList<>();
		query = query + whereConditon;
		catgList = jdbcTemplate.query(query, new RowMapper<ReceiptModel>() {
			@Override
			public ReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				ReceiptModel tmp = new ReceiptModel();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String billDt = rs.getDate("date") != null ? sdf1.format(rs.getTimestamp("date")) : null;
				String recptDt = rs.getDate("date_rece") != null ? sdf1.format(rs.getTimestamp("date_rece")):null;
				String phoneNo = rs.getString("mobile_no");
				System.out.println("Phone number "+phoneNo);
				tmp.setGuestHouseName(rs.getString("block_name"));
				tmp.setCityName(rs.getString("gh_name"));
				tmp.setBookingId(rs.getString("req_id"));
				tmp.setBillId(rs.getString("bill_id"));
				tmp.setBillNo(rs.getString("billno"));
				tmp.setName(rs.getString("name"));
				/*List<String> names = jdbcTemplate.query(sql1, (rs2, rn) -> {
					String name1 = rs2.getString("name");
					String department1 = rs2.getString("department");
					String designation1 = rs2.getString("designation");
					if (department1 != null && !department1.isEmpty()) {
						name1 += ", " + department1;
					}
					if (designation1 != null && !designation1.isEmpty()) {
						name1 += ", " + designation1;
					}
					return name1;
				}, tmp.getBookingId(), tmp.getBillId());*/
				tmp.setFromDate(frDt);
				tmp.setToDate(toDt);			
				tmp.setDate(billDt);
				tmp.setRectDate(recptDt);
				tmp.setMobileNo(phoneNo);
				tmp.setBillId(rs.getString("bill_id"));
				tmp.setRecId(rs.getString("rec_id"));
				tmp.setTotal(rs.getString("total_amount"));
				tmp.setPurpose(rs.getString("item_title"));
				tmp.setGuestAddress(rs.getString("address"));
				tmp.setGuestEmail(rs.getString("email_id"));
				tmp.setNoOfGuest(rs.getString("no_of_guest"));
				tmp.setGuestContact(rs.getString("guest_mobile"));
				tmp.setPaymentModes(jdbcTemplate.queryForList(sql, tmp.getRecId()));
				tmp.setGuestHouseAddress(rs.getString("gh_address"));
				tmp.setGuestHouseContactNum(rs.getString("contact_num"));
				tmp.setGuestHouseMail(rs.getString("gh_mail"));
				tmp.setCategory(rs.getString("category"));
				tmp.setRoomNo(rs.getString("room_no"));
				tmp.setTotalAmount(rs.getString("total"));
				tmp.setAdvancedAmount(rs.getString("advance"));
				tmp.setAmountPaid(rs.getString("amount"));
				tmp.setDueAmount(rs.getString("dues_amount"));
				return tmp;
			}
		});
		if(catgList != null) {
			System.out.println(" catgList  "+catgList);
			if(catgList.size() != 0) {
				Integer sum = 0;
				String receiptId = "";
				Set<String> set = new HashSet<>();
				String guestName = "";
				Integer count = 0;
				for(ReceiptModel item : catgList) {
					count++;
					if(item.getDueAmount() != null) {
						sum = sum + Integer.parseInt(item.getDueAmount());
					}
					receiptId = receiptId + item.getRecId();
					if(count < catgList.size()) {
						receiptId = receiptId + ",";
					}
					set.add(item.getName());
				}
				for(String va : set) {
					guestName = guestName + va;
					if(count < catgList.size()) {
						guestName = guestName + ",";
					}
				}
				response.setPaymentModes(catgList.get(0).getPaymentModes());
				response.setGuestHouseName(catgList.get(0).getGuestHouseName());
				response.setMobileNo(catgList.get(0).getMobileNo());
				response.setCityName(catgList.get(0).getCityName());
				response.setRectDate(catgList.get(0).getRectDate());
				response.setTotal(catgList.get(0).getTotal());
				response.setGuest(catgList.get(0).getGuest());
				response.setPaidAmount(catgList.get(0).getPaidAmount());
				response.setName(guestName);
				response.setMode(catgList.get(0).getMode());
				response.setRecId(receiptId);
				response.setPurpose(catgList.get(0).getPurpose());
				response.setGuestAddress(catgList.get(0).getGuestAddress());
				response.setGuestEmail(catgList.get(0).getGuestEmail());
				response.setGuestContact(catgList.get(0).getGuestContact());
				response.setNoOfGuest(catgList.get(0).getNoOfGuest());
				response.setGuestHouseAddress(catgList.get(0).getGuestHouseAddress());
				response.setGuestHouseContactNum(catgList.get(0).getGuestHouseContactNum());
				response.setGuestHouseMail(catgList.get(0).getGuestHouseMail());
				response.setBookingId(catgList.get(0).getBookingId());
				response.setCategory(catgList.get(0).getCategory());
				response.setFromDate(catgList.get(0).getFromDate());
				response.setToDate(catgList.get(0).getToDate());
				response.setRoomNo(catgList.get(0).getRoomNo());
				response.setAdvancedAmount(catgList.get(0).getAdvancedAmount());
				response.setTotalAmount(catgList.get(0).getTotalAmount());
				response.setAmountPaid(catgList.get(0).getAmountPaid());
				response.setDueAmount(catgList.get(0).getDueAmount());
			}
		}
		System.out.println(" payment receipt query for receipt  "+query+"  req_id"+rectId);
		System.out.println(" response "+response);
		return response;
		/*System.out.println("query for dues report  "+query);
		logger.info(data);
		return data;*/
	
	}
	@Override
	public ReceiptModel getNocInfoForBookingId(String bookingId) {
		String sql = approverQueries.getProperty("getNocList");
		String sql1 = adminQueries.getProperty("getModeOfPayment");
		String whereCondition = "and nt.req_id = '"+bookingId+"'";
		ReceiptModel response = new ReceiptModel();
		List<ReceiptModel> nocReport = new ArrayList<>();
		sql = sql + whereCondition;
		nocReport = jdbcTemplate.query(sql, new RowMapper<ReceiptModel>() {
			@Override
			public ReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				ReceiptModel tmp = new ReceiptModel();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				String payDate = rs.getDate("date_of_pay") != null ? sdf1.format(rs.getTimestamp("date_of_pay")) : null;
				//String recptDt = rs.getDate("date_rece") != null ? sdf1.format(rs.getTimestamp("date_rece")):null;
				tmp.setGuestHouseName(rs.getString("block_name"));
				tmp.setCityName(rs.getString("gh_name"));
				tmp.setBookingId(rs.getString("req_id"));
				tmp.setName(rs.getString("name"));
				tmp.setFromDate(frDt);
				tmp.setToDate(toDt);			
				tmp.setDate(payDate);
				tmp.setMobileNo(rs.getString("mobile_no"));
				tmp.setRecId(rs.getString("rec_id"));
				tmp.setTotal(rs.getString("pay_amount"));
				tmp.setPaymentModes(jdbcTemplate.queryForList(sql1, tmp.getRecId()));
				tmp.setGuestHouseAddress(rs.getString("gh_address"));
				tmp.setGuestHouseContactNum(rs.getString("contact_num"));
				tmp.setGuestHouseMail(rs.getString("gh_mail"));
				tmp.setCategory(rs.getString("category"));
				tmp.setPurpose(rs.getString("item_title"));
				tmp.setGuestEmail(rs.getString("email_id"));
				tmp.setBookingId(rs.getString("req_id"));
				tmp.setNoOfGuest(rs.getString("no_of_guest"));
				tmp.setRoomNo(rs.getString("room_no"));
				tmp.setGuestAddress(rs.getString("address"));
				return tmp;
			}
		});
		if(nocReport != null) {
			System.out.println(" catgList  "+nocReport);
			if(nocReport.size() != 0) {
				Integer sum = 0;
				String receiptId = "";
				Set<String> set = new HashSet<>();
				String guestName = "";
				Integer count = 0;
				for(ReceiptModel item : nocReport) {
					count++;
					if(item.getDueAmount() != null) {
						sum = sum + Integer.parseInt(item.getDueAmount());
					}
					receiptId = receiptId + item.getRecId();
					if(count < nocReport.size()) {
						receiptId = receiptId + ",";
					}
					set.add(item.getName());
				}
				for(String va : set) {
					guestName = guestName + va;
					if(count < nocReport.size()) {
						guestName = guestName + ",";
					}
				}
				response.setPaymentModes(nocReport.get(0).getPaymentModes());
				response.setGuestHouseName(nocReport.get(0).getGuestHouseName());
				response.setCityName(nocReport.get(0).getCityName());
				response.setDate(nocReport.get(0).getDate());
				response.setTotal(nocReport.get(0).getTotal());
				response.setGuest(nocReport.get(0).getGuest());
				response.setPaidAmount(nocReport.get(0).getPaidAmount());
				response.setName(guestName);
				response.setMode(nocReport.get(0).getMode());
				response.setGuestHouseAddress(nocReport.get(0).getGuestHouseAddress());
				response.setGuestHouseContactNum(nocReport.get(0).getGuestHouseContactNum());
				response.setGuestHouseMail(nocReport.get(0).getGuestHouseMail());
				response.setCategory(nocReport.get(0).getCategory());
				response.setPurpose(nocReport.get(0).getPurpose());
				response.setMobileNo(nocReport.get(0).getMobileNo());
				response.setGuestEmail(nocReport.get(0).getGuestEmail());
				response.setBookingId(nocReport.get(0).getBookingId());
				response.setRecId(receiptId);
				response.setFromDate(nocReport.get(0).getFromDate());
				response.setToDate(nocReport.get(0).getToDate());
				response.setNoOfGuest(nocReport.get(0).getNoOfGuest());
				response.setRoomNo(nocReport.get(0).getRoomNo());
				response.setGuestAddress(nocReport.get(0).getGuestAddress());
			}
		}
		System.out.println(" payment receipt query  "+sql+"  req_id"+bookingId);
		System.out.println(" response "+response);
		return response;
		/*System.out.println("query for dues report  "+query);
		logger.info(data);
		return data;*/
	
	}
	
	@Override
	public ConfrenceHall getConfrenceRequestDetailsByBookingId(String bookingId) {
		List<ConfrenceHall> data = new ArrayList<>();
		ConfrenceHall confrenceHall = new ConfrenceHall();
		String orderByCondition = " ORDER BY gh.req_date DESC";
		String query = adminQueries.getProperty("getListOfConfrenceRequest");
		String whereCondition = "AND gh.req_id ='"+bookingId+"'";

		query = query + whereCondition + orderByCondition;
		System.out.println(query.toString());
		data = jdbcTemplate.query(query, new RowMapper<ConfrenceHall>() {
			@Override
			public ConfrenceHall mapRow(ResultSet rs, int rownumber) throws SQLException {
				ConfrenceHall data1 = new ConfrenceHall();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				try {
					String bookingDt = rs.getDate("req_date") != null ? sdf.format(rs.getDate("req_date")) : null;
					String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
					String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
					data1.setGuestHouseName(rs.getString("block_name"));
					data1.setCityName(rs.getString("gh_name"));
					data1.setBillId(rs.getString("conf_bill_id"));
					data1.setBookingId(rs.getString("req_id"));
					data1.setBookingDt(bookingDt);
					data1.setName(rs.getString("name"));
					data1.setFromDate(frDt);
					data1.setToDate(toDt);
					data1.setBillStatus(rs.getString("bill_status"));
					data1.setStatus(rs.getString("status"));
					data1.setAdditonalCharge(rs.getString("additionalcharge"));
					data1.setAdvanceAmount(rs.getString("advance_amount"));
					data1.setModeOfPayment(rs.getString("mode_of_payment"));
					data1.setCharge(rs.getString("charge"));
					data1.setConfrenceDesc(rs.getString("conf_cat_desc"));
					data1.setConfreqnceUseDesc(rs.getString("conf_use_desc"));
					data1.setConfrenceId(rs.getString("conf_cat_id"));
					data1.setConfreqnceUseId(rs.getString("conf_cat_id"));
					data1.setCapacity(rs.getString("capacity"));
					data1.setGuestHouseName(rs.getString("block_name"));
					data1.setCityName(rs.getString("gh_name"));
					int total=Integer.parseInt(rs.getString("charge"));
					data1.setTotalAmnt(total);
					int additionalCharge=Integer.parseInt(rs.getString("additionalcharge"));
					int amount=Integer.parseInt(rs.getString("advance_amount"));
					data1.setPendingAmount((total+additionalCharge)-amount);
					String category_Desc = "";
					String category = rs.getString("category_type");
					if (category.equals("C1")) {
						category_Desc = "Category 1";
					}
					if (category.equals("C2")) {
						category_Desc = "Category 2";
					}
					if (category.equals("C3")) {
						category_Desc = "Category 3";
					}
					if (category.equals("C4")) {
						category_Desc = "Category 4";
					}
					data1.setCategory(category_Desc);
					data1.setMobile(rs.getString("mobile_no"));
					String status = rs.getString("status");
					data1.setAddress(rs.getString("address"));
					String status_desc = "";
				
					if (status.equals("REQ")) {
						status_desc = " Not taken up for processing";
					}
					if (status.equals("ALO")) {
						status_desc = " Allotment";
					}
					if (status.equals("PRO")) {
						status_desc = "Approved";
					}
					data1.setStatusDesc(status_desc);
					data1.setTypeOfRoom("Confrence Hall/Open Terrace");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return data1;
			}
		});
		if(data != null) {
			System.out.println(" data  "+data);
			if(data.size() != 0) {
				Integer sum = 0;
				String confrenceID = "";
				Set<String> set = new HashSet<>();
				String guestName = "";
				Integer count = 0;
				for(ConfrenceHall item : data) {
					count++;
					if(item.getCharge() != null) {
						sum = sum + Integer.parseInt(item.getCharge());
					}
					confrenceID = confrenceID + item.getConfrenceId();
					if(count < data.size()) {
						confrenceID = confrenceID + ",";
					}
					set.add(item.getName());
				}
				for(String va : set) {
					guestName = guestName + va;
					if(count < data.size()) {
						guestName = guestName + ",";
					}
				}
				confrenceHall.setTotalAmnt(data.get(0).getTotalAmnt());
				confrenceHall.setBookingId(data.get(0).getBookingId());
				confrenceHall.setGuestHouseName(data.get(0).getGuestHouseName());
				confrenceHall.setCityName(data.get(0).getCityName());
				confrenceHall.setMobile(data.get(0).getMobile());
				confrenceHall.setConfrenceDesc(data.get(0).getConfrenceDesc());
				confrenceHall.setName(guestName);
				confrenceHall.setAdvanceAmount(data.get(0).getAdvanceAmount());
				confrenceHall.setConfrenceId(data.get(0).getConfrenceId());
				confrenceHall.setPendingAmount(data.get(0).getPendingAmount());
				confrenceHall.setGuestHouseName(data.get(0).getGuestHouseName());
				confrenceHall.setCityName(data.get(0).getCityName());
				confrenceHall.setAddress(data.get(0).getAddress());
				confrenceHall.setConfreqnceUseDesc(data.get(0).getConfreqnceUseDesc());
				confrenceHall.setFromDate(data.get(0).getFromDate());
				confrenceHall.setAdditonalCharge(data.get(0).getAdditonalCharge());
				confrenceHall.setBillStatus(data.get(0).getBillStatus());
			}
		}
		
		System.out.println("getConfrenceRequestDetails   "+query);
		return confrenceHall;
	}
	
	@Override
	public boolean addOfficeOrderDocument(PasswordResetModel data,String userId) throws Exception {
		boolean status=false;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		Date docDt = !data.getDocumentDate().equals("") ? sdf1.parse(data.getDocumentDate()) : null;
		System.out.println("hyyy date      "+docDt);
		try {
			 Integer id=null;
			 try {
					id = get_seqID("upload_id");
					update_seqID("upload_id", id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			String sql = approverQueries.getProperty("saveOfficeOrderDoc");
			jdbcTemplate.update(sql, new Object[] { 
					id.toString(),
					data.getSplitGHouse(),
					data.getSplitCity(),
					docDt,
					data.getDocumentpath(),
					userId
			});	
			 status=true;
			}catch(Exception e) {
				e.printStackTrace();
			}
		return status;
	}
	@Override
	public List<PasswordResetModel> getOfficeOrderDocUploadList(Map<String, Object> params) {
		System.out.println("    gtt      "+params.toString());
		List<PasswordResetModel> officeOrderDocUpload;

		String sql = approverQueries.getProperty("getOfficeOrderUploadSQL");
		StringBuilder whereCondition = new StringBuilder();
		String orderBy = " order by upload_date desc"; 
		
		Map<String, Object> queryParams = new HashMap<>();
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");

		System.out.println("cityId"+cityId+"GuestHouse"+ghId);

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition.append(" WHERE ou.gh_id = :cityId");
	        queryParams.put("cityId", cityId);
		}
		if ( ghId != null ? !ghId.isEmpty() : false) {
			whereCondition.append(" AND ou.block_id = :ghId");
			queryParams.put("ghId", ghId);
		}

		sql = sql + whereCondition + orderBy ;
		System.out.println("office order lsit     "+sql);
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
			officeOrderDocUpload = namedParameterJdbcTemplate .query(sql, queryParams, new RowMapper<PasswordResetModel>() {
	            @Override
	            public PasswordResetModel mapRow(ResultSet rs, int rownumber) throws SQLException {
	                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                PasswordResetModel prm = new PasswordResetModel();
	                String docDt = rs.getDate("upload_date") != null ? sdf.format(rs.getDate("upload_date")) : null;
	                prm.setUploadId(rs.getString("upload_id"));
	                prm.setCreatedBy(rs.getString("created_by"));
	                prm.setDocumentDate(docDt);
	                prm.setSplitGHouse(rs.getString("block_name"));
	                prm.setSplitCity(rs.getString("gh_name"));
	                return prm;
	            }
			});
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Some error occurred while fetching noc list");
		}
		return officeOrderDocUpload;
	}
	
	@Override
	public PasswordResetModel getOfficeorderDocById(String uploadId) {

		List<PasswordResetModel> data = new ArrayList<>();
		PasswordResetModel officeOrderDoc = new PasswordResetModel();
		String query = approverQueries.getProperty("getOfficeOrderUploadSQL");
		String whereCondition = "where upload_id ='"+uploadId+"'";
		
		query = query + whereCondition ;
		System.out.println(query.toString());
		data = jdbcTemplate.query(query, new RowMapper<PasswordResetModel>() {
			@Override
			public PasswordResetModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				PasswordResetModel data1 = new PasswordResetModel();
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				try {
					String docDt = rs.getDate("upload_date") != null ? sdf.format( rs.getDate("upload_date")) : null;
					data1.setCreatedBy(rs.getString("created_by"));
					data1.setDocumentDate(docDt);
					data1.setUploadId(rs.getString("upload_Id"));
					data1.setDocumentpath(rs.getString("upload_path"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return data1;
			}
		});
		if(data != null) {
			System.out.println(" data  "+data);
			if(data.size() != 0) {
				officeOrderDoc.setDocumentpath(data.get(0).getDocumentpath());
				officeOrderDoc.setDocumentDate(data.get(0).getDocumentDate());
				officeOrderDoc.setCreatedBy(data.get(0).getCreatedBy());
				officeOrderDoc.setUploadId(data.get(0).getUploadId());	
			}
		}
		System.out.println("gegtOfficeOrder   "+query);
		return officeOrderDoc;
	
	}
	
	@Override
	public String getcityById(String cityName) {
		String query = approverQueries.getProperty("getCityName");
		String whereCondition = "where gh_id = '"+cityName+"'";
		query = query + whereCondition ;
		System.out.println("query  "+query);
		String cName = jdbcTemplate.queryForObject(query,String.class);
		return cName;
	}

	@Override
	public String getghDetailsById(String guesthouseName) {
		String query = approverQueries.getProperty("getGhName");
		String whereCondition = "where block_id = '"+guesthouseName+"'";
		query = query + whereCondition ;
		System.out.println("query  "+query);
		String ghName = jdbcTemplate.queryForObject(query,String.class);
		return ghName;
	}
	
	@Override
	public List<BookingResponseModel> getModList(Map<String, Object> params) {
		System.out.println("    gtt      "+params.toString());
		List<BookingResponseModel> modList;

		String sql = approverQueries.getProperty("getModListForAllotment");
		String whereCondition="";
		String orderBy = "ORDER BY gh.req_date DESC"; 
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String bookingDate = (String) params.get("bookingDate");
		String category = (String) params.get("category");
		String purpose = (String) params.get("purpose");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		String roomType = (String) params.get("roomType");
		String type = (String) params.get("type");
		LocalDate curDt = LocalDate.now();
		
		System.out.println("cityId"+cityId+"GuestHouse"+ghId);

		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false ) {
			whereCondition =" where gh.gh_id='"+cityId+"'";
		}
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			whereCondition =  whereCondition +  " AND gh.block_id='"+ghId+"'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.req_id='" + bookingId + "'";
		}
		if (category != null ? !category.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.category_type='" + category + "'";
		}
		if (bookingDate != null ? !bookingDate.isEmpty() : false) {
		    Date date;
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		    try {
		        date = sdf.parse(bookingDate);
		    } catch (ParseException e) {
		        throw new RuntimeException(e);
		    }
		    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		    bookingDate = sdf1.format(date);
		    whereCondition = whereCondition + " AND gh.req_date::date='" + bookingDate + "'::date";
		}

		if (purpose != null ? !purpose.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.purpose='" + purpose + "'";
		}

		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.mobile_no='" + mobNo + "'";
		}

		if (name != null ? !name.isEmpty() : false) {
			whereCondition = whereCondition + " AND gs.name ILIKE '%" + name + "%'";
		}

		if (roomType != null ? !roomType.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.roomtype='" + roomType + "'";
		}
		if (type != null ? !type.isEmpty() : false) {
			if (type.equalsIgnoreCase("changeList")) {
				whereCondition += " AND gh.status IN ('ALO') AND gh.from_date::date >= current_date::date";
			} else if (type.equalsIgnoreCase("modifyList")) {
				sql = allotmentQueries.getProperty("getAllotmentLstForModify");
				whereCondition += " AND gh.status IN ('PRO', 'PCIN', 'CIN') AND current_date::date <= gh.to_date::date" +
						" AND (tr.mod_status IS NULL OR tr.mod_status = '')";
			} else if (type.equalsIgnoreCase("modifyListMOD")) {
				sql = allotmentQueries.getProperty("getAllotmentLstForModifyMOD");
				whereCondition += " AND gh.status IN ('PRO', 'PCIN', 'CIN') AND current_date::date <= gh.to_date::date" +
						" AND (tr.mod_status = 'PMODF')";
			} else {
				whereCondition += " AND gh.status IN ('MOD', 'PALO') AND gh.from_date::date >= current_date::date";
			}
		}

		whereCondition = whereCondition
				+ " AND gh.status = 'MOD'  AND gh.mode_of_req IN ('GAD','PUB')" 
				+" AND gh.to_date>= '"+ curDt + "'";

		sql = sql + whereCondition + orderBy ;
		System.out.println("mod list     "+sql);
		try {
			modList = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>()  {
				@Override
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				BookingResponseModel brm = new BookingResponseModel();
				String fromDate = rs.getDate("from_date") != null ? sdf.format( rs.getTimestamp("from_date")) : null;
				String toDate = rs.getDate("to_date") != null ? sdf.format( rs.getTimestamp("to_date")) : null;
				brm.setName(rs.getString("name"));
				brm.setFromDate(fromDate);
				brm.setToDate(toDate);
				brm.setCityName(rs.getString("gh_name"));
				brm.setGhName(rs.getString("block_name"));
				brm.setBookingId(rs.getString("req_id"));
				brm.setCityId(rs.getString("gh_id"));
				brm.setGhId(rs.getString("block_id"));
				brm.setNoRooms(rs.getString("nor_req"));
				brm.setNoBeds(rs.getString("nod_g_req"));
				return brm;
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Some error occurred while fetching noc list");
		}
		System.out.println("modlistquery   "+sql);
		return modList;
	}
	@Override
	public List<ShiftMasterModel> getOpenShiftReportList(Map<String, Object> params) {

		System.out.println(params.toString());
		List<ShiftMasterModel> shiftList=new ArrayList<ShiftMasterModel>();

		String sql = approverQueries.getProperty("getShiftReportSQL");
		String whereCondition = "where status = 'O'";
		String orderBy=" ORDER BY lastmodified desc";
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");		
		String createdBy = (String) params.get("createdBy");
		String createdDate = (String) params.get("createdDate");

		if (!cityId.equalsIgnoreCase("NA") && cityId != null ? !cityId.isEmpty() : false) {
			System.out.println("inside city block");
			whereCondition +="AND a.gh_id='"+cityId+"'";
		} 
		if (!ghId.equalsIgnoreCase("NA") && ghId != null ? !ghId.isEmpty() : false) {
			 System.out.println("inside guest house block");
			whereCondition =  whereCondition +  "AND a.block_id='"+ghId+"'";
		}	
	
		if (createdBy != null ? !createdBy.isEmpty() : false) {
			whereCondition = whereCondition + " AND a.createdby ILIKE '%" + createdBy + "%'";
		}			
		
		if (createdDate != null ? !createdDate.isEmpty() : false) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(createdDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			createdDate = sdf1.format(date);
			whereCondition = whereCondition + " AND '" + createdDate + "' between date(from_time) and date(to_time)";
		}

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		sql = sql+whereCondition+orderBy;
		try {
			shiftList = jdbcTemplate.query(sql, new RowMapper<ShiftMasterModel>() {
				@Override
				public ShiftMasterModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					ShiftMasterModel sm = new ShiftMasterModel();
					String frDt = rs.getDate("createdtime") != null ? sdf1.format(rs.getTimestamp("createdtime")) : null;
					String toDt = rs.getDate("close_time") != null ? sdf1.format(rs.getTimestamp("close_time")) : null;
					if(toDt == null) {
						toDt = "Not Closed";
					}
					sm.setShiftId(rs.getString("shift_id"));
					sm.setUserId(rs.getString("createdby"));
					sm.setBlockId(rs.getString("block_id"));
					sm.setCityId(rs.getString("gh_id"));
					sm.setCreatedTime(frDt);
					sm.setCloseTime(toDt);
					sm.setBlockName(rs.getString("block_name"));
					sm.setCityName(rs.getString("gh_name"));
					if(rs.getString("status").equalsIgnoreCase("O")) {
						sm.setStatus("Open");
					}else {
						sm.setStatus("Closed");
					}
					return sm;
			}
		});

		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("sql for shift report    "+sql);
		return shiftList;
	
	}
	
	@Override
	public ShiftMasterModel getCurrentOpenShift(String shiftId) {
		String sql = adminQueries.getProperty("getOpenShiftInfoSql");
		ShiftMasterModel shiftMasterModel = null;
		try {
			shiftMasterModel = jdbcTemplate.queryForObject(sql, new Object[] { shiftId },
					(rs, rowNum) -> {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						ShiftMasterModel smm = new ShiftMasterModel();
						smm.setCityId(rs.getString("gh_id"));
						smm.setBlockId(rs.getString("block_id"));
						smm.setUserId(rs.getString("createdby"));
						smm.setShiftId(rs.getString("shift_id"));
						smm.setCreatedTime(sdf.format(rs.getTimestamp("createdtime")));
						smm.setOpenBalRefund(rs.getString("openbal_refund"));
						smm.setOpenBalRegular(rs.getString("openbal_regular"));
						smm.setOpenBalTotal(rs.getString("openbal_total"));
						smm.setReceiptRefund(rs.getString("receipt_refund"));
						smm.setReceiptRegular(rs.getString("receipt_regular"));
						smm.setReceiptTotal(rs.getString("receipt_total"));
						smm.setPaymentRefund(rs.getString("payment_refund"));
						smm.setPaymentRegular(rs.getString("payment_regular"));
						smm.setPaymentTotal(rs.getString("payment_total"));
						smm.setStatus(rs.getString("status"));
						smm.setShiftNum(rs.getString("par_shiftid"));
						smm.setFromTime(sdf.format(rs.getTimestamp("from_time")));
						smm.setToTime(sdf.format(rs.getTimestamp("to_time")));
						smm.setCloseTime(
								rs.getTimestamp("close_time") != null ? sdf.format(rs.getTimestamp("close_time")) : "");
						return smm;
					});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("sql        "+sql+"     "+shiftMasterModel);
		return shiftMasterModel;
	}
/*prerana Daoimpl ends*/


/*Dipti Daoimpl Starts*/
	
	@Override
	public DataTableResponse getAllGuestDetails(DataTableRequest params, HttpSession session) {
	    List<BookingResponseModel> data = new ArrayList<>();
	    String orderByCondition = " ORDER BY gh.req_date DESC";
	    String orderByForDailyReport = " ORDER BY category_type ASC";
	    String query = allotmentQueries.getProperty("getAllotmentLstDtlsSQL");
	    String query1 = adminQueries.getProperty("guestReportCount");
	    StringBuilder whereCondition = new StringBuilder(" WHERE 1=1 ");

	    Map<String, Object> queryParams = new HashMap<>();

	    String cityId = params.getCity();
	    String ghId = params.getgHouse();
	    String bookingId = params.getBookingId();
	    String bookingDate = params.getBookingDate();
	    String purpose = params.getPurpose();
	    String mobNo = params.getMobNo();
	    String name = params.getName();
	    String category = params.getCategory();
	    String fromDate = params.getFrmDate();
	    LocalDate curDt = LocalDate.now();
	    LocalDate nextDt = curDt.plusDays(7);
	    int start = params.getStart();
	    int length = params.getLength();

	    if (cityId != null && !cityId.isEmpty()) {
	        whereCondition.append(" AND gh.gh_id = :cityId");
	        queryParams.put("cityId", cityId);
	    } else {
	        ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
	        whereCondition.append(" AND gh.gh_id IN (:cityIdList)");
	        queryParams.put("cityIdList", cityIdList);
	    }

	    if (ghId != null && !ghId.isEmpty()) {
	        whereCondition.append(" AND gh.block_id = :ghId");
	        queryParams.put("ghId", ghId);
	    }

	    if (bookingId != null && !bookingId.isEmpty()) {
	        whereCondition.append(" AND gh.req_id = :bookingId");
	        queryParams.put("bookingId", bookingId);
	    }

	    if (bookingDate != null && !bookingDate.isEmpty()) {
	        Date date;
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        try {
	            date = sdf.parse(bookingDate);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	        bookingDate = sdf1.format(date);
	        whereCondition.append(" AND gh.req_date::date = :bookingDate::date");
	        queryParams.put("bookingDate", bookingDate);
	    }

	    if (fromDate != null && !fromDate.isEmpty()) {
	        Date date;
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        try {
	            date = sdf.parse(fromDate);
	        } catch (ParseException e) {
	            throw new RuntimeException(e);
	        }
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	        fromDate = sdf1.format(date);
	        whereCondition.append(" AND from_date::date = :fromDate::date");
	        queryParams.put("fromDate", fromDate);
	    }

	    if (purpose != null && !purpose.isEmpty()) {
	        whereCondition.append(" AND gh.purpose = :purpose");
	        queryParams.put("purpose", purpose);
	    }

	    if (!params.getType().equals("individual")) {
	        whereCondition.append(" AND gh.status IN ('ALO', 'PALO', 'PRO', 'CIN', 'PCIN', 'COUT', 'BILL', 'RECT', 'PEXT', 'EXT')");
	    } else {
	        whereCondition.append(" AND (gh.status IN ('REQ', 'MOD', 'ALO', 'PALO', 'PRO', 'CIN', 'PCIN', 'COUT', 'BILL', 'RECT', 'PEXT', 'EXT', 'RCAN') " +
	                              "OR (gh.status = 'CAN' AND (gh.can_status IS NULL AND cr.req_id IS NOT NULL) OR (gh.can_status IS NOT NULL)))");
	    }

	    if (category != null && !category.isEmpty()) {
	        whereCondition.append(" AND gh.category_type = :category");
	        queryParams.put("category", category);
	    }

	    if (mobNo != null && !mobNo.isEmpty()) {
	        whereCondition.append(" AND gh.mobile_no = :mobNo");
	        queryParams.put("mobNo", mobNo);
	    }

	    if (name != null && !name.isEmpty()) {
	        whereCondition.append(" AND gs.name ILIKE :name");
	        queryParams.put("name", "%" + name + "%");
	    }

	    if (params.getOrderBy() != null) {
	        query += whereCondition + orderByForDailyReport;
	    } else {
	        query += whereCondition + orderByCondition;
	    }
	    query1 += whereCondition;

	    if (length != 0) {
	        query += " LIMIT :length OFFSET :start";
	        queryParams.put("length", length);
	        queryParams.put("start", start);
	    }

	    logger.info(query);
	    logger.info(query1);

	    Integer totalCount = 0;
	    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		try {
	        totalCount = namedParameterJdbcTemplate .queryForObject(query1, queryParams, Integer.class);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    data = namedParameterJdbcTemplate.query(query, queryParams, (rs, rownumber) -> setBookingLstFromResultset(rs));

	    DataTableResponse dt = new DataTableResponse();
	    dt.setDraw(params.getDraw());
	    dt.setData(data);
	    dt.setRecordsTotal(totalCount);
	    dt.setRecordsFiltered(totalCount);
	    return dt;
	}


	@Override
	public List<BookingResponseModel> getAllGuestCancelReport(Map<String, Object> params, HttpSession session) {
		List<BookingResponseModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY gh.req_date DESC";
		String query = allotmentQueries.getProperty("getCancelGuestReport");
		StringBuilder whereCondition = new StringBuilder(" AND 1=1");
		
		Map<String, Object> queryParams = new HashMap<>();

		String reqType = (String) params.get("reqType");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String bookingDate = (String) params.get("bookingDate");
		String purpose = (String) params.get("purpose");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		String status = (String) params.get("status");
		String roomType = (String) params.get("roomType");
		String category = (String) params.get("category");

		LocalDate curDt = LocalDate.now();
		LocalDate nextDt = curDt.plusDays(7);

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition.append(" AND gh.gh_id = :cityId");
	        queryParams.put("cityId", cityId);
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			whereCondition.append(" AND gh.gh_id IN (:cityIdList)");
	        queryParams.put("cityIdList", cityIdList);
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition.append(" AND gh.block_id = :ghId");
	        queryParams.put("ghId", ghId);
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition.append(" AND gh.req_id = :bookingId");
	        queryParams.put("bookingId", bookingId);
		}
		if (bookingDate != null ? !bookingDate.isEmpty() : false) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(bookingDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			bookingDate = sdf1.format(date);
			whereCondition.append(" AND gh.req_date::date = :bookingDate::date");
	        queryParams.put("bookingDate", bookingDate);
		}
		if (purpose != null ? !purpose.isEmpty() : false) {
			whereCondition.append(" AND gh.purpose = :purpose");
	        queryParams.put("purpose", purpose);
		}
	/*	if (status != null ? !status.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.status='" + status + "'";
		}else {
			whereCondition = whereCondition + " AND gh.status IN ('ALO', 'PALO', 'PRO', 'CIN', 'PCIN', 'COUT', 'BILL', 'RECT', 'EXT')";
		}*/
		if (category != null ? !category.isEmpty() : false) {
			whereCondition.append(" AND gh.category_type = :category");
	        queryParams.put("category", category);
		}

		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition.append(" AND gh.mobile_no = :mobNo");
	        queryParams.put("mobNo", mobNo);
		}

		if (name != null ? !name.isEmpty() : false) {
			whereCondition.append(" AND gs.name ILIKE :name");
	        queryParams.put("name", "%" + name + "%");
		}

		if (roomType != null ? !roomType.isEmpty() : false) {
			whereCondition.append(" AND gh.roomtype = :roomType");
	        queryParams.put("roomType", roomType);
		}
		query = query + whereCondition + orderByCondition;
		logger.info(query);

		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	        data = namedParameterJdbcTemplate.query(query, queryParams, new RowMapper<BookingResponseModel>() {
	            @Override
	            public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
	                BookingResponseModel d = new BookingResponseModel();
	                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	                String bookingDt = rs.getDate("req_date") != null ? sdf.format(rs.getDate("req_date")) : null;
	                String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
	                String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
	                String pFromDate = rs.getDate("pc_from_date") != null ? sdf1.format(rs.getTimestamp("pc_from_date")) : null;
	                String pToDate = rs.getDate("pc_to_date") != null ? sdf1.format(rs.getTimestamp("pc_to_date")) : null;
	                String cFromDate = rs.getDate("c_from_date") != null ? sdf1.format(rs.getTimestamp("c_from_date")) : null;
	                String cToDate = rs.getDate("c_to_date") != null ? sdf1.format(rs.getTimestamp("c_to_date")) : null;

	                d.setBookingId(rs.getString("req_id"));
	                d.setBookingDt(bookingDt);
	                d.setName(rs.getString("name"));
	                d.setFromDate(frDt);
	                d.setToDate(toDt);
	                d.setGhId(rs.getString("gh_id"));
	                d.setGhName(rs.getString("gh_name"));
	                d.setCityId(rs.getString("block_id"));
	                d.setCityName(rs.getString("block_name"));
	                d.setPurposeId(rs.getString("purpose"));
	                d.setPurpose(rs.getString("item_title"));

	                String category_Desc = "";
	                String category = rs.getString("category_type");
	                if (category.equals("C1")) {
	                    category_Desc = "Category 1";
	                }
	                if (category.equals("C2")) {
	                    category_Desc = "Category 2";
	                }
	                if (category.equals("C3")) {
	                    category_Desc = "Category 3";
	                }
	                if (category.equals("C4")) {
	                    category_Desc = "Category 4";
	                }

	                d.setCategory(category_Desc);

	                String status = rs.getString("status");
	                String roomType = rs.getString("roomtype");
	                String roomTypeDesc = "";
	                String status_desc = "";
	                if (roomType.equals("R")) {
	                    roomTypeDesc = "Room";
	                }
	                if (roomType.equals("RC")) {
	                    roomTypeDesc = "Room with Conveyance";
	                }
	                if (roomType.equals("C")) {
	                    roomTypeDesc = "Conveyance";
	                }
	                if (status.equals("REQ")) {
	                    status_desc = " Not taken up for processing";
	                }
	                if (status.equals("CAN")) {
	                    status_desc = "Cancelled";
	                }
	                if (status.equalsIgnoreCase("MOD")) {
	                    status_desc = "Under processing";
	                }
	                if (status.equalsIgnoreCase("PRO")) {
	                    status_desc = "Approved";
	                }
	                if (status.equalsIgnoreCase("ALO")) {
	                    status_desc = "Allotted";
	                }
	                if (status.equals("CIN")) {
	                    status_desc = "Check In";
	                }
	                if (status.equals("COUT")) {
	                    status_desc = "Check Out";
	                }
	                if (status.equals("RCAN")) {
	                    status_desc = "Cancelled";
	                }
	                if (status.equals("PEXT")) {
	                    status_desc = "Request For Extension";
	                }
	                if (status.equals("EXT")) {
	                    status_desc = "Extension";
	                }
	                if (status.equals("BILL")) {
	                    status_desc = "Billing";
	                }
	                if (status.equals("RECT")) {
	                    status_desc = "Payment Successfully";
	                }
	                d.setStatus(status);
	                d.setStatusDesc(status_desc);

	                d.setMobile(rs.getString("mobile_no"));
	                d.setTypeOfRoom(roomTypeDesc);
	                d.setPartialCancelFromDt(pFromDate);
	                d.setPartialCancelToDt(pToDate);
	                d.setCancelFromDt(cFromDate);
	                d.setCancelToDt(cToDate);
	                d.setCancelId(rs.getString("can_id"));
	                String cancelType="";
	                String modeOfCan = rs.getString("mode_of_cancel");
	                if (modeOfCan.equals("MODCAN")) {
	                    cancelType = "Moderator Cancel";
	                } else {
	                    int canStatus = rs.getInt("partial_c");
	                    if (canStatus == 1) {
	                        cancelType = "Partial Cancel";
	                    } else {
	                        cancelType = "Full Cancel";
	                    }
	                }
	                d.setModeOfCancel(rs.getString("mode_of_cancel"));
	                d.setModeOfCancelDesc(cancelType);
	                d.setRemarks(rs.getString("remarks"));

	                return d;
	            }
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		logger.info(data);
		return data;
	}
/*Dipti Daoimpl Ends*/

/*Amit Daoimpl Starts*/
	@Override
	public List<RoomOccupancy> showOccupancyDetails(String city, String gHouse) {
//		  String sql = adminQueries.getProperty("getOccupancyDetails");
		  String sql = adminQueries.getProperty("getOccupancyDetailsUpdated");
		  String extensionExists = adminQueries.getProperty("extensionExists");
		  String fmDtSql=adminQueries.getProperty("getFromDateForOccupancy");
		  List<RoomOccupancy> occupancy = new ArrayList<>();
			try {
				occupancy = jdbcTemplate.query(sql, new RowMapper<RoomOccupancy>() {
					@Override
					public RoomOccupancy mapRow(ResultSet rs, int rownumber) throws SQLException {
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
						RoomOccupancy occupancy = new RoomOccupancy();
						occupancy.setRoomNo(rs.getString("room_no"));
						occupancy.setName(rs.getString("name"));
//						String frmDt = rs.getTimestamp("checkin_date") != null ? sdf1.format(rs.getTimestamp("checkin_date")) : null;
						String toDt = rs.getTimestamp("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
//						String exfrmDt = rs.getTimestamp("ex_from_date") != null ? sdf1.format(rs.getTimestamp("ex_from_date")) : null;
//						String extoDt = rs.getTimestamp("ex_to_date") != null ? sdf1.format(rs.getTimestamp("ex_to_date")) : null;
//						occupancy.setCheckInDate(frmDt);
						occupancy.setToDate(toDt);
//						occupancy.setExfromDate(exfrmDt);
//						occupancy.setExToDate(extoDt);
//						if(frmDt!=null) {
//							occupancy.setCheckInDate(frmDt.replace("-", "."));
//						}
						if(toDt!=null) {
							occupancy.setToDate(toDt.replace("-", "."));
						}
//						if(exfrmDt!=null) {
//							occupancy.setExfromDate(exfrmDt.replace("-", "."));
//						}
//						if(extoDt!=null) {
//							occupancy.setExToDate(extoDt.replace("-", "."));
//						}
						String prevBookingId=rs.getString("req_id");
						while (null != prevBookingId) {
							String formDate1 = jdbcTemplate.queryForObject(fmDtSql, String.class, prevBookingId);
							try {
								ExtensionRequestModel extensionRequestModel = jdbcTemplate
										.queryForObject(extensionExists, (rs1, rowNum) -> {
											ExtensionRequestModel erm = new ExtensionRequestModel();
											erm.setExt(rs1.getString("ext"));
											erm.setExisting_req_id(rs1.getString("existing_req_id"));
											return erm;
										}, prevBookingId);
								if (extensionRequestModel.getExt().equalsIgnoreCase("1")) {
									prevBookingId = extensionRequestModel.getExisting_req_id();
								} else {
									prevBookingId = null;
								}
							} catch (Exception e) {
								prevBookingId = null;
							}
							if(formDate1!=null) {
								occupancy.setCheckInDate(formDate1.replace("-", "."));
							}
						}
						occupancy.setExName(rs.getString("ex_name"));
						occupancy.setMaxNoBeds(rs.getString("max_no_beds"));
						occupancy.setRoomType(rs.getString("room_type"));
						occupancy.setGhName(rs.getString("gh_name"));
						occupancy.setBlockName(rs.getString("block_name"));
						occupancy.setType(rs.getString("type"));
//						occupancy.setExpectType(rs.getString("expect_type"));
						occupancy.setNorReq(rs.getString("nor_req"));
						occupancy.setNodGreq(rs.getString("nod_g_req"));
//						occupancy.setExRoom(rs.getString("exroom"));
//						occupancy.setExNob(rs.getString("exnob"));
						occupancy.setReason(rs.getString("reason"));
						return occupancy;
					}
				}, gHouse,city);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		  return occupancy;
	}


	@Override
	@Transactional
	public Map<String, Object> testing(Map<String, Object> params) {
	    String query1 = adminQueries.getProperty("updateTrConfBillStatus");
	    String query2 = adminQueries.getProperty("getTrConfBillDetails");
	    String query3 = adminQueries.getProperty("saveTrConfReceipt");
	    String query6 = adminQueries.getProperty("createReceiptPaymentMode");
	    String query7 = adminQueries.getProperty("getPgInfoDetails");
	    String query4 = adminQueries.getProperty("getBankRefRecPayMode");
	    String query5 = adminQueries.getProperty("updatePgInfo");

	    String reqId = (String) params.get("reqId");
	    Map<String, Object> resp = new HashMap<>();

	    TrConfBill trConfBill = null;
	    try {
	        jdbcTemplate.update(query1, "P", reqId);
	        
	        trConfBill = jdbcTemplate.queryForObject(query2, new Object[]{reqId}, (rs, rowNum) -> {
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	            TrConfBill tcb = new TrConfBill();
	            tcb.setConfBillId(rs.getString("conf_bill_id"));
	            tcb.setReqId(rs.getString("req_id"));
	            tcb.setAdditionalCharge(rs.getString("additionalcharge"));
	            tcb.setAdvanceAmount(rs.getString("advance_amount"));
	            tcb.setEntryDate(sdf.format(rs.getTimestamp("entrydate")));
	            tcb.setCharge(rs.getString("charge"));
	            tcb.setGhId(rs.getString("gh_id"));
	            tcb.setModeOfpayment(rs.getString("mode_of_payment"));
	            tcb.setOrderId(rs.getString("order_id"));
	            tcb.setStatus(rs.getString("status"));
	            return tcb;
	        });
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }

	    Integer id;
	    try {
	        id = get_seqID("rec_id");
	        update_seqID("rec_id", id);
	    } catch (Exception e) {
	        throw new RuntimeException("Error generating sequence ID", e);
	    }

	    try {
	        jdbcTemplate.update(query3, id, trConfBill.getConfBillId(), trConfBill.getAdvanceAmount(), "A", "", "", trConfBill.getReqId());
	        
	        Integer rpmId = get_seqID("rpm_id");
	        update_seqID("rpm_id", rpmId);
	        String orderId = jdbcTemplate.queryForObject(query7, new Object[]{reqId}, String.class);
	        jdbcTemplate.update(query6, rpmId, id, "online", Integer.valueOf(trConfBill.getAdvanceAmount()), "", orderId, "");
	    } catch (Exception e) {
	        throw new RuntimeException("Error in payment mode creation", e);
	    }

	    try {
	        String bankRef = jdbcTemplate.queryForObject(query4, new Object[]{id.toString()}, String.class);
	        resp.put("bankReference", bankRef);
	        jdbcTemplate.update(query5, "Shipped", bankRef);
	    } catch (Exception e) {
	        throw new RuntimeException("Error updating PG info", e);
	    }

	    return resp;
	}

	
	@Override
	public DataTableResponse getRebookingList(DataTableRequest params, HttpSession session) {
		Map<String, Object> parameters = new HashMap<>();

		String query = adminQueries.getProperty("getRebookingLstDtlsSQL");
		String query1 = adminQueries.getProperty("getRebookingLstDtlsSQLCount");

		// Initialize the where condition
		StringBuilder whereCondition = new StringBuilder(" WHERE 1=1 AND cr.status = 'CCAN' AND gd.status = 'CAN' AND cr.mode_of_cancel = 'MODCAN' AND gd.from_date > NOW() ");

		// Parse the request parameters
		String reqType = params.getType();
		String cityId = params.getCity();
		String ghId = params.getgHouse();
		String bookingId = params.getBookingId();
		String bookingDate = params.getBookingDate();
		String fromDate = params.getFrmDate();
		String purpose = params.getPurpose();
		String mobNo = params.getMobNo();
		String name = params.getName();
		String category = params.getCategory();
		int start = params.getStart();
		
		String orderBy = "";

		// Add city ID condition
		if (cityId != null && !cityId.isEmpty()) {
		    whereCondition.append(" AND gd.gh_id = :cityId");
		    parameters.put("cityId", cityId);
		}
		else {
		    ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
		    String cityIdString = String.join("', '", cityIdList);
		    whereCondition.append(" AND gd.gh_id IN (:cityIds)");
		    parameters.put("cityIds", cityIdList);
		}

		// Add other conditions
		if (ghId != null && !ghId.isEmpty()) {
		    whereCondition.append(" AND gd.block_id = :ghId");
		    parameters.put("ghId", ghId);
		}
		if (bookingId != null && !bookingId.isEmpty()) {
		    whereCondition.append(" AND gd.req_id = :bookingId");
		    parameters.put("bookingId", bookingId);
		}
		if (bookingDate != null && !bookingDate.isEmpty()) {
		    try {
		        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
		        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
		        Date parsedDate = sdfInput.parse(bookingDate);
		        String formattedDate = sdfOutput.format(parsedDate);
		        bookingDate = formattedDate;
		        whereCondition.append(" AND gd.req_date::date = CAST(:bookingDate AS DATE)");
		        parameters.put("bookingDate", bookingDate);
		    } catch (ParseException e) {
		        throw new RuntimeException("Invalid date format for bookingDate", e);
		    }
		}

		if (fromDate != null && !fromDate.isEmpty()) {
		    try {
		        SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
		        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
		        Date parsedDate = sdfInput.parse(fromDate);
		        String formattedDate = sdfOutput.format(parsedDate);
		        fromDate = formattedDate;
		        whereCondition.append(" AND gd.from_date::date = CAST(:fromDate AS DATE)");
		        parameters.put("fromDate", fromDate);
		    } catch (ParseException e) {
		        // Optionally log the error if needed
		        // logger.error("Error parsing fromDate: {}", fromDate, e);
		        throw new RuntimeException("Invalid date format for fromDate", e);
		    }
		}

		if (purpose != null && !purpose.isEmpty()) {
		    whereCondition.append(" AND gd.purpose = :purpose");
		    parameters.put("purpose", purpose);
		}
		if (category != null && !category.isEmpty()) {
		    whereCondition.append(" AND gd.category_type = :category");
		    parameters.put("category", category);
		}
		if (mobNo != null && !mobNo.isEmpty()) {
		    whereCondition.append(" AND gd.mobile_no = :mobNo");
		    parameters.put("mobNo", mobNo);
		}
		if (name != null && !name.isEmpty()) {
		    whereCondition.append(" AND gs.name ILIKE :name");
		    parameters.put("name", "%" + name + "%");
		}

		// Combine query with where condition
		query += whereCondition.toString()+orderBy;
		query1 += whereCondition.toString();
		System.out.println(query);
		

		if (params.getLength() != 0) {
		    query += " LIMIT :length OFFSET :start";
		    parameters.put("length", params.getLength());
		    parameters.put("start", start);
		}

		final String countQuery = query1;
		System.out.println("Count Query -- " + countQuery);
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate =new NamedParameterJdbcTemplate(jdbcTemplate);
		// Asynchronously execute the count query
		CompletableFuture<Integer> countFuture = CompletableFuture.supplyAsync(() -> {
		    try {
		        return namedParameterJdbcTemplate.queryForObject(countQuery, parameters, Integer.class);
		    } catch (Exception e) {
		        logger.error("Error executing count query", e);
		        return 0;
		    }
		}, executorService);

		final String dataQuery = query;
		System.out.println("Main Query -- " + dataQuery);
		// Asynchronously execute the data fetching query
		CompletableFuture<List<BookingResponseModel>> dataFuture = CompletableFuture.supplyAsync(() -> {
		    return namedParameterJdbcTemplate.query(dataQuery, parameters, (rs, rowNum) -> {
		        BookingResponseModel booking = new BookingResponseModel();
		        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		        String bookingDt = rs.getDate("req_date") != null ? sdf1.format(rs.getTimestamp("req_date")) : null;
		        String fromDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
		        booking.setBookingId(rs.getString("req_id"));
		        booking.setName(rs.getString("name"));
		        booking.setMobile(rs.getString("mobile_no"));
		        String roomType = rs.getString("roomtype");
		        String roomTypeDesc = "";
		        if(roomType != null) {
					if (roomType.equals("R")) {
						roomTypeDesc = "Room";
					}
					if (roomType.equals("RC")) {
						roomTypeDesc = "Room with Conveyance";
					}
					if (roomType.equals("C")) {
						roomTypeDesc = "Conveyance";
					}
					if (roomType.equals("CON")) {
						roomTypeDesc = "Confrence/Open Terrace";
					}
				}
		        booking.setTypeOfRoom(roomTypeDesc);
		        booking.setFromDate(fromDt);
		        booking.setToDate(toDt);
		        booking.setReqDate(bookingDt);
		        booking.setPurpose(rs.getString("item_title"));
		        booking.setCategory(rs.getString("category"));
		        booking.setStatus(rs.getString("status"));
		        String cancelType="";
                String modeOfCan = rs.getString("mode_of_cancel");
                if (modeOfCan.equals("MODCAN")) {
                    cancelType = "Moderator Cancel";
                }
                booking.setModeOfCancel(rs.getString("mode_of_cancel"));
                booking.setModeOfCancelDesc(cancelType);
		        booking.setStatusDesc(rs.getString("status"));
		        return booking;
		    });
		}, executorService);


		// Combine both results once both are available
		CompletableFuture<DataTableResponse> responseFuture = countFuture.thenCombine(dataFuture, (count, data) -> {
		    DataTableResponse dt = new DataTableResponse();
		    dt.setDraw(params.getDraw());
		    dt.setRecordsTotal(count);
		    dt.setRecordsFiltered(count);
		    dt.setData(data);
		    return dt;
		});

		DataTableResponse dt = null;
		try {
		    dt = responseFuture.get();
		} catch (InterruptedException | ExecutionException e) {
		} finally {
		    executorService.shutdown();
		}

		return dt;

	}

	@Transactional
	@Override
	public Map<String, Object> revertCancellationByBookingId(String reqid) {
		Map<String, Object> resp = new HashMap<String, Object>();
		String q1 = adminQueries.getProperty("updateGadReqStatus");
		String q2 = adminQueries.getProperty("updateCanReservationStatus");
		try {
			int data1 = jdbcTemplate.update(q1, reqid);
	        int data2 = jdbcTemplate.update(q2, reqid);

	        if (data1 > 0 && data2 > 0) {
	            resp.put("success", true);
	            resp.put("msg", "Cancelled booking successfully reverted and rebooked!");
	        } else {
	            resp.put("success", false);
	            resp.put("msg", "Failed to revert booking cancellation. No records updated.");
	        }
		} catch(Exception e) {
			logger.error("Error reverting booking cancellation", e);
	        resp.put("success", false);
	        resp.put("msg", "An error occurred while reverting the booking cancellation.");
		}
		resp.put("success", true);
		resp.put("msg", "Booking successfully reverted!");
		return resp;
	}
	
	
}

