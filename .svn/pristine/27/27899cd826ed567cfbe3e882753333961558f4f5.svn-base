package com.stl.ngms.admin.dao;

import java.io.File;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stl.ngms.common.MailUtil;
import com.stl.ngms.common.SendSMS;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.ModerationPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.guest.model.RoomCategory;
import com.stl.ngms.guest.model.VehicleDetails;
import com.stl.ngms.utils.Utils;

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
	private Properties adminQueries;

	@Override
	public List<BookingResponseModel> getallValueDetails(HttpServletRequest request) {
		List<BookingResponseModel> alldetails = null;
		String cityValue = "";
		String guesthouseValue = "";
		if (request.getParameter("cityValue") != null || !request.getParameter("cityValue").equals(""))
			cityValue = request.getParameter("cityValue");
		if (request.getParameter("guesthouseValue") != null || !request.getParameter("guesthouseValue").equals(""))
			guesthouseValue = request.getParameter("guesthouseValue");

		if (cityValue.equals("") && guesthouseValue.equals("")) {
			String sql = approverQueries.getProperty("getallTableDetailsSQL");
			alldetails = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
				@Override
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					BookingResponseModel Response = new BookingResponseModel();

					Response.setBookingId(rs.getString(2));
					Response.setFromDate(rs.getString(3));
					Response.setToDate(rs.getString(4));
					Response.setPurpose(rs.getString(5));
					Response.setCategory(rs.getString(6));
					Response.setMobile(rs.getString(7));
					Response.setEmail(rs.getString(8));
					Response.setName(rs.getString(9));
					return Response;

				}
			});
		}

		if (!cityValue.equals("") && !guesthouseValue.equals("")) {

			String sql = approverQueries.getProperty("getallTableDetailsbyCityandgHouseSQL");
			alldetails = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>()
			{
				@Override
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					BookingResponseModel Response = new BookingResponseModel();
					Response.setBookingId(rs.getString(2));
					Response.setFromDate(rs.getString(3));
					Response.setToDate(rs.getString(4));
					Response.setPurpose(rs.getString(5));
					Response.setCategory(rs.getString(6));
					Response.setMobile(rs.getString(7));
					Response.setEmail(rs.getString(8));
					Response.setName(rs.getString(9));
					return Response;

				}

			}, cityValue, guesthouseValue);
		}
		return alldetails;
	}

	@Override
	public List<CommonComboBoxResponseModel> getGuestHouseList(String cityId) {
		String query = approverQueries.getProperty("getAllGuestHouseListSQL");
		List<CommonComboBoxResponseModel> cityList = new ArrayList<>();
		cityList = jdbcTemplate.query(query, new RowMapper<CommonComboBoxResponseModel>() {
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

	@Override
	public String adminaddBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		System.out.println(data.getBlockId());
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		String ghName = null;

		List<Map<String, String>> blockList = null; 
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {

			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId", rs.getString("gh_id"));
				res.put("bId", rs.getString("block_id"));
				res.put("bName", rs.getString("block_name"));
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

		System.out.println(blockList.get(0).get("ghId"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

		Integer id = get_seqID("req_id");
		update_seqID("req_id", id);
		String reqId = "G" + id.toString();
		System.out.println(reqId);
		Date frmDt = !data.getFrmDt().equals("") ? sdf.parse(data.getFrmDt()) : null;
		Date toDt = !data.getToDt().equals("") ? sdf.parse(data.getToDt()) : null;
		Date arrDt = !data.getArrDt().equals("") ? sdf.parse(data.getArrDt()) : null;
		Date depDt = !data.getDepDt().equals("") ? sdf.parse(data.getDepDt()) : null;

		Date arrtm = !data.getArrTm().equals("") ? stf.parse(data.getArrDt() + " " + data.getArrTm()) : null;
		Date deptm = !data.getDepTm().equals("") ? stf.parse(data.getDepDt() + " " + data.getDepTm()) : null;

		String q1 = approverQueries.getProperty("saveBookingDetailsSQl");
		String q2 = approverQueries.getProperty("saveArrivalDtlsSQl");
		String q3 = approverQueries.getProperty("saveDepartureDtlsSQl");
		String q4 = approverQueries.getProperty("adminsaveGuestDetailsSQL");

		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			System.out.println("saved document file path :: " + filepath);
			if (filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			}
		}
		String status = null;
		if (!data.getRoomType().equalsIgnoreCase("C")) {
			status = "REQ";
		} else {
			status = "ALO";
		}
		jdbcTemplate.update(q1, new Object[] { reqId, blockList.get(0).get("ghId"), frmDt, toDt,
				data.getBookingType() == null ? "0"
						: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
				data.getBookingType() == null ? "0"
						: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
				"0", status, !data.getArrDt().equals("") ? "1" : "0", "PUB", data.getPurp(), "0", data.getOffAddr(),
				userId,
				data.getBookingType() == null ? "0"
						: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
				"0", "0", data.getMob(), data.getEmailId(), "0", !data.getDepDt().equals("") ? "1" : "0",
				data.getBlockId(), // block Id
				filepath, // file path
				data.getCatg(), data.getDesg(), data.getRoomType() });

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
			jdbcTemplate.update(q2, new Object[] { vehId, reqId, data.getTravelModeFr(), arrtm, data.getArrPla(), "0",
					data.getFlNoFr(), arrDt });

			jdbcTemplate.update(q3, new Object[] { vehId, reqId, data.getTravelModeTo(), deptm, data.getDepPla(), "0",
					data.getFlNoTo(), depDt });
		}
		String guestName = "Applicant ";
		if (!data.getRoomType().equalsIgnoreCase("C")) {
			if (data.getList() != null) {
				for (GuestDetailsPojo item : data.getList()) {
					Integer gid = get_seqID("guest_id");
					String guestId = "G_" + gid.toString();
					update_seqID("guest_id", gid);
					jdbcTemplate.update(q4, new Object[] { reqId, guestId, item.getGuestName(), item.getGuestAge(),
							item.getGuestGender(), item.getGuestAddr(),

					});
				}
				guestName = data.getList().get(0).getGuestName();
			}
		} else {
			msg = "only conveynce";
		}

		try {
			if (data.getEmailId() != null) {
				if (!data.getEmailId().isEmpty()) {
					mail.sendMimeMail(data.getEmailId(), "Application Details",
							"Dear " + guestName + "<br> You have successfully applied for " + msg
									+ " and your booking id " + reqId + "<br><br> Thanks <br>e-Bhawan Team");// mail
				}
			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send..");
		}
		try {
			if (data.getMob() != null) {
				if (!data.getMob().isEmpty()) {
					String templateID="1107160707714316860";
					smsSend.sendSMS(data.getMob(), "Dear " + guestName + " You have successfully applied for " + msg
							+ " and your booking id " + reqId + " ",templateID);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}

		return reqId;
	}

	public boolean update_seqID(String p_id, Integer p_curvalue) throws Exception {
		String sql = "Update sequence_id set curvalue=? where id=?";
		jdbcTemplate.update(sql, new Object[] { p_curvalue, p_id });
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
	public String getPropValue(String p_id) throws Exception {
		String sql = "select prop_value from property where prop_id=?";
		String  propvalue = null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String  propvalue = null;
				propvalue = rs.getString("prop_value");
				return propvalue;
			}
		}, p_id);
		if (null != users && !users.isEmpty()) {
			propvalue = users.get(0);
		}
		return propvalue ;
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
	@Override
	public boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		RoomBookingPojo pd = admingetBookingDetailsById(data.getBookingId());
		System.out.println(data.getBookingId());
		System.out.println(pd);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		boolean status = false;

		Date frmDt = !data.getFrmDt().equals("") ? sdf.parse(data.getFrmDt()) : null;
		Date toDt = !data.getToDt().equals("") ? sdf.parse(data.getToDt()) : null;
		Date arrDt = !data.getArrDt().equals("") ? sdf.parse(data.getArrDt()) : null;
		Date depDt = !data.getDepDt().equals("") ? sdf.parse(data.getDepDt()) : null;

		Date arrtm = !data.getArrTm().equals("") ? stf.parse(data.getArrDt() + " " + data.getArrTm()) : null;
		Date deptm = !data.getDepTm().equals("") ? stf.parse(data.getDepDt() + " " + data.getDepTm()) : null;

		String q1 = approverQueries.getProperty("adminupdateBookingDetailsSQl");
		String q2 = approverQueries.getProperty("saveArrivalDtlsSQl");
		String q3 = approverQueries.getProperty("saveDepartureDtlsSQl");
		String q4 = approverQueries.getProperty("adminsaveGuestDetailsSQL");

		String q5 = approverQueries.getProperty("updateArrivalDtlsSQl");
		String q6 = approverQueries.getProperty("updateDepartureDtlsSQl");
		String q7 = approverQueries.getProperty("adminupdateGuestDetailsSQL");
		String q8 = approverQueries.getProperty("deleteGuestDetailsSQL");

		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(pd.getBookingId());
			String directory = env.getProperty("media.base.path") + File.separator + env.getProperty("sup.doc.folder")
					+ File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			System.out.println("saved document file path :: " + filepath);
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
		String cid = null;
		cid = pd.getCityId();
		System.out.println(cid);
		jdbcTemplate.update(q1,
				new Object[] { pd.getCityId(), frmDt, toDt,
						data.getBookingType() == null ? "0"
								: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
						data.getBookingType() == null ? "0"
								: (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
						pd.getStatus(), !data.getArrDt().equals("") ? "1" : "0", data.getPurp(), data.getOffAddr(),
						data.getBookingType() == null ? "0"
								: (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
						data.getMob(), data.getEmailId(), !data.getDepDt().equals("") ? "1" : "0", pd.getBlockId(),
						filepath, // file path
						data.getCatg(), data.getDesg(), data.getRoomType(), pd.getBookingId() });
		if (!pd.getIsArrivalConv() && !data.getArrDt().equals("")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "P_" + vid.toString();
			jdbcTemplate.update(q2, new Object[] { vehId, pd.getBookingId(), data.getTravelModeFr(), arrtm,
					data.getArrPla(), "0", data.getFlNoFr(), arrDt });
		} else {
			jdbcTemplate.update(q5, new Object[] { data.getTravelModeFr(), arrtm, data.getArrPla(), data.getFlNoFr(),
					arrDt, pd.getBookingId(), pd.getdVchId() });
		}
		if (!pd.getIsDepartueConv() && !data.getDepDt().equals("")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "P_" + vid.toString();
			jdbcTemplate.update(q3, new Object[] { vehId, pd.getBookingId(), data.getTravelModeTo(), deptm,
					data.getDepPla(), "0", data.getFlNoTo(), depDt });
		} else {
			jdbcTemplate.update(q6, new Object[] { data.getTravelModeTo(), deptm, data.getDepPla(), data.getFlNoTo(),
					depDt, pd.getBookingId(), pd.getdVchId() });
		}
		if (!data.getRoomType().equalsIgnoreCase("C")) {
			if (data.getList() != null) {
				String id = "";
				;
				Integer i = 0;
				for (GuestDetailsPojo item : data.getList()) {
					i++;
					System.out.println(item);
					if (item.getGuestId().equals("")) {
						Integer gid = get_seqID("guest_id");
						String guestId = "G_" + gid.toString();
						id = id + "'" + guestId + "'";
						update_seqID("guest_id", gid);
						jdbcTemplate.update(q4, new Object[] { pd.getBookingId(), guestId, item.getGuestName(),
								item.getGuestAge(), item.getGuestGender(), item.getGuestAddr()

						});
					} else {
						id = id + "'" + item.getGuestId() + "'";
						jdbcTemplate.update(q7, new Object[] { item.getGuestName(), item.getGuestAge(),
								item.getGuestGender(), item.getGuestAddr(), pd.getBookingId(), item.getGuestId(), });
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
		}
		status = true;
		return status;
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

	@Override
	public RoomBookingPojo admingetBookingDetailsById(String bookingId) {
		System.out.println("Enter the method");
		String query = approverQueries.getProperty("getBookingDtlsByIdSQL");

		String gquery = approverQueries.getProperty("getGuestDtlsByBookIdSQL");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
		System.out.println(bookingId);
		List<RoomBookingPojo> list = jdbcTemplate.query(query, new RowMapper<RoomBookingPojo>() {
			@Override
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				RoomBookingPojo res = new RoomBookingPojo();
				String fromDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
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
				res.setCatgName(rs.getString("quota_desc"));
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
				if (rs.getString("status") != null) {
					if (rs.getString("status").equalsIgnoreCase("REQ")) {
						res.setStatusDesc("Not taken up for processing");
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
				res.setIsExtension(rs.getString("ext").equalsIgnoreCase("1"));
				res.setExtensionId(rs.getString("existing_req_id"));
				res.setIsArrivalConv(rs.getString("is_veh_req").equalsIgnoreCase("1"));
				res.setIsDepartueConv(rs.getString("is_veh_req1").equalsIgnoreCase("1"));
				res.setSuppDocName(rs.getString("file_path"));
				System.out.println(res);
				return res;

			}
		}, bookingId);
		List<GuestDetailsPojo> guestList = jdbcTemplate.query(gquery, new RowMapper<GuestDetailsPojo>() {
			@Override
			public GuestDetailsPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				GuestDetailsPojo res = new GuestDetailsPojo();
				res.setGuestId(rs.getString("guest_id"));
				res.setGuestName(rs.getString("name"));
				res.setGuestAge(rs.getString("age"));
				res.setGuestGender(rs.getString("gender"));
				res.setGuestAddr(rs.getString("address"));
				return res;
			}
		}, bookingId);
		System.out.println("LIST.....");
		System.out.println(list);
		if (list.size() == 0) {
			return null;
		}
		list.get(0).setList(guestList);

		return list.get(0);

	}

	// *************************************

	@Override
	public List<ExtensionRequestModel> getallApplicationValueDetails(HttpServletRequest request) {
		List<ExtensionRequestModel> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";
		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			cityName = request.getParameter("cityName");
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			guesthouseName = request.getParameter("guesthouseName");
		if (cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("getallApplicantDetailsSQL");
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ExtensionRequestModel>()

			{
				public ExtensionRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					ExtensionRequestModel response = new ExtensionRequestModel();

					// response.setRowNo(rs.getString(1));
					// response.setGuestId(rs.getString(2));
					response.setReqId(rs.getString(3));
					response.setGuestName(rs.getString(4));
					response.setGender(rs.getString(5));
					response.setFromDate(rs.getDate(6));
					response.setToDate(rs.getDate(7));
					response.setReq_date(rs.getDate(8));
					return response;
				}

			});

		}
		if (!cityName.equals("") && !guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("getallApplicantDetailsbyCityandgHouseSQL");

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ExtensionRequestModel>()

			{
				public ExtensionRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					ExtensionRequestModel response = new ExtensionRequestModel();

					// response.setRowNo(rs.getString(1));
					// response.setGuestId(rs.getString(2));
					response.setReqId(rs.getString(3));
					response.setGuestName(rs.getString(4));
					response.setGender(rs.getString(5));
					response.setFromDate(rs.getDate(6));
					response.setToDate(rs.getDate(7));
					response.setReq_date(rs.getDate(8));
					return response;
				}
			}, cityName, guesthouseName);
		}
		return alldetailsValue;
	}
	@Override
	public List<ExtensionRequestModel> getallmoderationApplicationList(HttpServletRequest request) {
		List<ExtensionRequestModel> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";
		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			cityName = request.getParameter("cityName");
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			guesthouseName = request.getParameter("guesthouseName");
		if (cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("getallmoderationDetailsSQL");
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ExtensionRequestModel>()

			{
				public ExtensionRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					ExtensionRequestModel response = new ExtensionRequestModel();
					response.setReqId(rs.getString(1));
					response.setFromDate(rs.getDate(2));
					response.setToDate(rs.getDate(3));
					response.setGuestName(rs.getString(6));
					response.setGender(rs.getString(8));
					response.setReq_date(rs.getDate(13));
					return response;
				}

			});

		}
		if (!cityName.equals("") && !guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("getallmoderationDetailsSQLCityAndGhouse");

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ExtensionRequestModel>()

			{
				public ExtensionRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					ExtensionRequestModel response = new ExtensionRequestModel();
					response.setReqId(rs.getString(1));
					response.setFromDate(rs.getDate(2));
					response.setToDate(rs.getDate(3));
					response.setGuestName(rs.getString(6));
					response.setGender(rs.getString(8));
					response.setReq_date(rs.getDate(13));
					return response;
				}
			}, cityName, guesthouseName);
		}
		return alldetailsValue;
	}

	// ********************************

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
		final String reqId=request.getParameter("reqId");
		
		System.out.println(reqId);
		String sql = adminQueries.getProperty("getRoomStatusSQL");
		List<RoomCategory> alldetails = jdbcTemplate.query(sql, new RowMapper<RoomCategory>() {
			public RoomCategory mapRow(ResultSet rs, int rownumber) throws SQLException {
				
				RoomCategory response = new RoomCategory();
				String statusrooms = "";
				String noofroom= "";
				String noofbl= "" ;
				String noofbg="";
				String noofdl="";
				String noofdg="";
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
					System.out.println(rooms);
					System.out.println(beds);
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
						
					
					System.out.println(occ_rooms);
					System.out.println(occ_GB);
					System.out.println(occ_LB);
					if (rooms > 0 || beds > 0) {
						double occu_rooms = rooms - (Double.valueOf(occ_rooms)
								+ Math.ceil(Double.valueOf(occ_GB) / beds) + Math.ceil(Double.valueOf(occ_LB) / beds));
						System.out.println("occu_rooms " + occu_rooms);
						
						statusrooms = (int) occu_rooms + "/" + rooms + "-Rooms";

					} else if (dorg > 0 || dorl > 0) {
						double occu_rooms = dorg - (Double.valueOf(occ_DG));
						System.out.println("occu_roomsg " + occu_rooms);

						statusrooms = (int) occu_rooms + "/" + dorg + "-DG&";

						double occu_roomsl = dorl - (Double.valueOf(occ_DL));
						System.out.println("occu_roomsl " + occu_roomsl);

						statusrooms = statusrooms + (int) occu_roomsl + "/" + dorl + "-DL";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setStatus(statusrooms);
				try {
					 noofroom=modroom(reqId,block_id).toString();
					 System.out.println(noofroom);
					 noofbl=   modladies(reqId,block_id).toString();
					 noofbg=modgents(reqId,block_id).toString();
					 noofdl=moddorladies(reqId,block_id).toString();
					 noofdg=moddorgents(reqId,block_id).toString();
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
		final String sql = "select nu_of_rooms from m_quota_block where block_id='"+block_id+"' and gh_id='"+gh_id+"' and quota_type='"+quota_type+"'";
		Integer noOfrooms = 0;

		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer noOfrooms = 0;
				noOfrooms = rs.getInt("nu_of_rooms");
				System.out.println(sql);
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
				System.out.println(sql);
				return noOfdorl;
			}
		}, block_id, gh_id, quota_type);
		
		if (null != users && !users.isEmpty()) {
			noOfdorl = users.get(0);
		}
		return noOfdorl;
	}

	public Integer selectDBoccupied(String gh_id, String block_id, String quota_type, String type, String checkindate,
			String checkoutdate)
			throws Exception {
		final String sql = "select sum(total_number) from reservation_main_table where gh_id='"+gh_id+"' and block_id='"+block_id+"' and quota_type='"+quota_type+"' and type='"+type+"' and status!='CAN' and (checkin_date between to_timestamp('"+checkindate+"','yyyy-MM-dd HH24:MI:SS') and to_timestamp('"+checkoutdate+"','yyyy-MM-dd HH24:MI:SS') or checkout_date between to_timestamp('"+checkindate+"','yyyy-MM-dd HH24:MI:SS') and to_timestamp('"+checkoutdate+"','yyyy-MM-dd HH24:MI:SS') or (checkin_date<to_timestamp('"+checkindate+"','yyyy-MM-dd HH24:MI:SS') and checkout_date>to_timestamp('"+checkoutdate+"','yyyy-MM-dd HH24:MI:SS')))";
		Integer sum = 0;
		try{
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer sum = 0;
				sum = rs.getInt("sum");
				System.out.println(sum);
				
				return sum;
			}  
		});
		if (null != users && !users.isEmpty()) {
			sum = users.get(0);
		}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return sum;
	}
	
	public boolean adminmoderationDetails(ModerationPojo data,String id)
	{
		List<RoomBookingPojo> md= getGuestDetailsByReqid(id);
		RoomBookingPojo md1 =md.get(0);
		String q1 = adminQueries.getProperty("getmoderationSQL");
		String q2 = adminQueries.getProperty("getmoderationRoomSQL");
		String q3 = adminQueries.getProperty("getupdatestatusinGadRequest");
		String q4 = adminQueries.getProperty("updateconveancyinGadRequest");
		System.out.println(data);
		System.out.println(md);
		
		Integer id1=0;
		try {
			id1 = get_seqID("mod_id");
			update_seqID("mod_id", id1);
			System.out.println(id1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String mod_id = id1.toString();
		jdbcTemplate.update(q1, new Object[] { 
				mod_id, 
				md1.getBookingId(),
				data.getVc_id(),
				data.getQuota_type(),
				data.getIs_vehicle_req(),
				data.getProcess_id(),
				"MOD",
				md1.getCityId(),
				data.getIs_vehicle_req(),
				 });
		  jdbcTemplate.update(q3, new Object[] {
				"MOD",
				md1.getBookingId(),
		});
		  jdbcTemplate.update(q4, new Object[] { 
					data.getIs_vehicle_req(),
					data.getIs_vehicle_req(),
					md1.getCityId(),
					md1.getBookingId()
					 });
		  
			String rooms=data.getRooms();
			String[] arrSplit = rooms.split(",");
			    for (int i=0; i < arrSplit.length; i++)
			    {
			      String rooms1=arrSplit[i];
			      System.out.println(rooms1);
			      for(int j=0;j<Integer.parseInt(rooms1);j++){
						Integer id2=0;
								try {
									id2 = get_seqID("mrr_id");
									update_seqID("mrr_id", id2);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String mrr_id = id2.toString();
						        jdbcTemplate.update(q2, new Object[] { 					
								mrr_id,
								mod_id, 
								md1.getBookingId(),
								md1.getBlockId(),
								1,
								"R",
								md1.getCityId(),
						});
					}
			    }		
			String bg=data.getBg();
			String[] arrSplit1 = bg.split(",");
		    for (int i=0; i < arrSplit1.length; i++)
		    {
		    	String bg1=arrSplit1[i];
		    
		    	for(int j=0;j<Integer.parseInt(bg1);j++){
					Integer id2=0;
					try {
						id2 = get_seqID("mrr_id");
						update_seqID("mrr_id", id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String mrr_id = id2.toString();
					jdbcTemplate.update(q2, new Object[] { 
							mrr_id,
							mod_id, 
							md1.getBookingId(),
							md1.getBlockId(),
							1,
							"BG",
							md1.getCityId(),
					});
				}
		    }
			String bl=data.getBl();
			String[] arrSplit2 = bl.split(",");
		    for (int i=0; i < arrSplit2.length; i++)
		    {
		    	String bl1=arrSplit2[i];
		    	for(int j=0;j<Integer.parseInt(bl1);j++){
					Integer id2=0;
					try {
						id2 = get_seqID("mrr_id");
						update_seqID("mrr_id", id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String mrr_id = id2.toString();
					jdbcTemplate.update(q2, new Object[] { 
							mrr_id,
							mod_id, 
							md1.getBookingId(),
							md1.getBlockId(),
							1,
							"BL",
							md1.getCityId(),
					});
				}
		    }
			String dg=data.getDg();
			String[] arrSplit3 = dg.split(",");
		    for (int i=0; i < arrSplit3.length; i++)
		    {
		    	String dg1=arrSplit3[i];
		    	for(int j=0;j<Integer.parseInt(dg1);j++){
					Integer id2=0;
					try {
						id2 = get_seqID("mrr_id");
						update_seqID("mrr_id", id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String mrr_id = id2.toString();
					jdbcTemplate.update(q2, new Object[] { 
							mrr_id,
							mod_id, 
							md1.getBookingId(),
							md1.getBlockId(),
							1,
							"DG",
							md1.getCityId(),
					});
				}
		    }
			String dl=data.getDl();
			String[] arrSplit4 = dl.split(",");
		    for (int i=0; i < arrSplit4.length; i++)
		    {			
			String dl1=arrSplit4[i];		
			for(int j=0;i<Integer.parseInt(dl1);j++){
				Integer id2=0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q2, new Object[] { 
						mrr_id,
						mod_id, 
						md1.getBookingId(),
						md1.getBlockId(),
						1,
						"DL",
						md1.getCityId(),
				});
			}
		    }
		return true;
	}
	@Override
	public List<RoomBookingPojo> getmoderationDetailsByReqid(String reqid) {
		System.out.println(reqid);
		System.out.println("moderation");
		String sql = adminQueries.getProperty("editmoderationClickquery");
		List<RoomBookingPojo> alldetailss = jdbcTemplate.query(sql, new RowMapper<RoomBookingPojo>() {
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
				System.out.println("moderation....................");
				
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
				String ctype=rs.getString("quota_type");
				String cdesc=null;
				try {
					 cdesc=QuatoDesc(ctype);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				res.setQuotatype(ctype);
				res.setQuotadesc(cdesc);
				res.setCityId(rs.getString("gh_id"));
				res.setBlockId(rs.getString("block_id"));
				res.setCityName(rs.getString("gh_name"));	 
				//res.setBlockName(rs.getString("block_name"));
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
	public String  QuatoDesc( String quota_type) throws Exception {
		String sql = "select quota_desc from quota where quota_type=?";
		String QuotaDesc=null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String QuotaDesc=null;
			    QuotaDesc = rs.getString("quota_desc");

				return QuotaDesc;
			}
		}, quota_type);
		if (null != users && !users.isEmpty()) {
			QuotaDesc = users.get(0);
		}
		return QuotaDesc;
	}
	
	public Integer modroom(String req_id,String block_id) throws Exception
	{
		Integer countroom=0;
		System.out.println("Enter");
		String room="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='R' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countroom = 0;
				countroom = rs.getInt("count");
				System.out.println(countroom);
				return countroom;
			}
		}, req_id,req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countroom = users.get(0);
		}
		return countroom;
	}
	public Integer modladies(String req_id,String block_id) throws Exception
	{
		Integer countladies=0;
		String room1="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='BL' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room1, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countladies = 0;
				countladies = rs.getInt("count");
				return countladies;
			}
		}, req_id, req_id,block_id);
		if (null != users && !users.isEmpty()) {
			countladies = users.get(0);
		}
		return countladies;
	}
	public Integer modgents(String req_id,String block_id) throws Exception
	{
		Integer countgents=0;
		String room2="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='BG' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room2, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countgents = 0;
				countgents = rs.getInt("count");
				return countgents;
			}
		}, req_id,req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countgents = users.get(0);
		}
		return countgents;
	}
	public Integer moddorladies(String req_id,String block_id) throws Exception
	{
		Integer countmodladies=0;
		String room3="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='DL' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room3, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countmodladies = 0;
				countmodladies = rs.getInt("count");
				return countmodladies;
			}
		}, req_id,req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countmodladies = users.get(0);
		}
		return countmodladies;
	}
	public Integer moddorgents(String req_id,String block_id) throws Exception
	{
		Integer countmodgents=0;
		String room4="select count(*) from (select req_id,mod_id from mod_req where req_id=? and status!='CAN') a join (select req_id,mod_id from mod_req_room where type='DG' and req_id=? and block_id=?)b on a.req_id=b.req_id and a.mod_id=b.mod_id";
		List<Integer> users = jdbcTemplate.query(room4, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer countmodgents = 0;
				countmodgents = rs.getInt("count");
				return countmodgents;
			}
		}, req_id,req_id, block_id);
		if (null != users && !users.isEmpty()) {
			countmodgents = users.get(0);
		}
		return countmodgents;
	}
	public boolean updateModerationDetails(ModerationPojo data,String id)
	{
		List<RoomBookingPojo> md= getmoderationDetailsByReqid(id);
		RoomBookingPojo md1 =md.get(0);
		String q1 = adminQueries.getProperty("UpdatemoderationSQL");
		String q2 = adminQueries.getProperty("deletemoderationRoomSQL");
		String q3 = adminQueries.getProperty("updateconveancyinGadRequest");
		String q4 = adminQueries.getProperty("updateconveancyinmodreq");
		String q5= adminQueries.getProperty("getmoderationRoomSQL");
		String q6= adminQueries.getProperty("updateblockidingadRequest");
		
		System.out.println(data);
		System.out.println(md);
		jdbcTemplate.update(q1, new Object[] { 
				data.getVc_id(),
				data.getQuota_type(),
			    md1.getBookingId()
				 });
		  jdbcTemplate.update(q2, new Object[] {
				  
				  md1.getBookingId(),
				  md1.getModId(),
				  md1.getCityId()});
		 
		  jdbcTemplate.update(q3, new Object[] { 
					data.getIs_vehicle_req(),
					data.getIs_vehicle_req(),
					md1.getCityId(),
					md1.getBookingId()
					 });
		  jdbcTemplate.update(q4, new Object[] { 
					data.getIs_vehicle_req(),
					data.getIs_vehicle_req(),
					md1.getBookingId()
					 });
		  
//		  	String block_id=data.getBlock_id();
//		  	String[] blockId=block_id.split(",");
//		  	for (int ii=0; ii < blockId.length; ii++)
//		  	{
//		  	   String blockId1=blockId[ii];		  	   
//		  	   System.out.println("blockid"+blockId1);
			   String rooms=data.getRooms();
			   String[] arrSplit = rooms.split(",");
			    for (int i=0; i < arrSplit.length; i++)
			    {
			      String rooms1=arrSplit[i];
			      System.out.println(rooms1);
			      for(int j=0;j<Integer.parseInt(rooms1);j++){
						Integer id2=0;
								try {
									id2 = get_seqID("mrr_id");
									update_seqID("mrr_id", id2);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String mrr_id = id2.toString();
						        jdbcTemplate.update(q5, new Object[] { 					
								mrr_id,
								md1.getModId(), 
								md1.getBookingId(),
								md1.getBlockId(),
								1,
								"R",
								md1.getCityId(),
						});
					}
			    }
			    
			String bg=data.getBg();
			String[] arrSplit1 = bg.split(",");
		    for (int i=0; i < arrSplit1.length; i++)
		    {
		    	String bg1=arrSplit1[i];  
		    	for(int j=0;j<Integer.parseInt(bg1);j++){
					Integer id2=0;
					try {
						id2 = get_seqID("mrr_id");
						update_seqID("mrr_id", id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String mrr_id = id2.toString();
					jdbcTemplate.update(q5, new Object[] { 
							mrr_id,
							md1.getModId(), 
							md1.getBookingId(),
							md1.getBlockId(),
							1,
							"BG",
							md1.getCityId(),
					});
				}
		    }
			String bl=data.getBl();
			String[] arrSplit2 = bl.split(",");
		    for (int i=0; i < arrSplit2.length; i++)
		    {
		    	String bl1=arrSplit2[i];
		    	for(int j=0;j<Integer.parseInt(bl1);j++){
					Integer id2=0;
					try {
						id2 = get_seqID("mrr_id");
						update_seqID("mrr_id", id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String mrr_id = id2.toString();
					jdbcTemplate.update(q5, new Object[] { 
							mrr_id,
							md1.getModId(), 
							md1.getBookingId(),
							md1.getBlockId(),
							1,
							"BL",
							md1.getCityId(),
					});
				}
		    }
			String dg=data.getDg();
			String[] arrSplit3 = dg.split(",");
		    for (int i=0; i < arrSplit3.length; i++)
		    {
		    	String dg1=arrSplit3[i];
		    	for(int j=0;j<Integer.parseInt(dg1);j++){
					Integer id2=0;
					try {
						id2 = get_seqID("mrr_id");
						update_seqID("mrr_id", id2);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String mrr_id = id2.toString();
					jdbcTemplate.update(q5, new Object[] { 
							mrr_id,
							md1.getModId(),
							md1.getBookingId(),
							md1.getBlockId(),
							1,
							"DG",
							md1.getCityId(),
					});
				}
		    }
		    
			String dl=data.getDl();
			String[] arrSplit4 = dl.split(",");
		    for (int i=0; i < arrSplit4.length; i++)
		    {			
			String dl1=arrSplit4[i];		
			for(int j=0;j<Integer.parseInt(dl1);j++){
				Integer id2=0;
				try {
					id2 = get_seqID("mrr_id");
					update_seqID("mrr_id", id2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String mrr_id = id2.toString();
				jdbcTemplate.update(q5, new Object[] { 
						mrr_id,
						md1.getModId(), 
						md1.getBookingId(),
						md1.getBlockId(),
						1,
						"DL",
						md1.getCityId(),
				});
			}
		  }
		    

		    jdbcTemplate.update(q6, new Object[] { 
		    		md1.getBlockId(),
					md1.getBookingId()
					 });
		    return true;
	}
	
	
	@Override
	public List<ProcessModeration> getallProcessApplicationList(HttpServletRequest request) {
		List<ProcessModeration> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";
		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			cityName = request.getParameter("cityName");
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			guesthouseName = request.getParameter("guesthouseName");
		if (cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("ProcessallList");
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>()

			{
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration response = new ProcessModeration();
					response.setBlockId(rs.getString(1));
					response.setBookingId(rs.getString(2));
					response.setStatus(rs.getString(3));
					response.setVcId(rs.getString(4));
					response.setReqDate(rs.getString(5));
					response.setQuotatype(rs.getString(6));
					response.setType(rs.getString(7));
					response.setFrmDt(rs.getString(8));
					response.setToDt(rs.getString(9));
					return response;
				}

		});

		}
		if (!cityName.equals("") && !guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("ProcessallListwithGHouseAndCity");

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>()

			{
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration response = new ProcessModeration();
					response.setBlockId(rs.getString(1));
					response.setBookingId(rs.getString(2));
					response.setStatus(rs.getString(3));
					response.setVcId(rs.getString(4));
					response.setReqDate(rs.getString(5));
					response.setQuotatype(rs.getString(6));
					response.setType(rs.getString(7));
					response.setFrmDt(rs.getString(8));
					response.setToDt(rs.getString(9));
					return response;
				}
			}, cityName, guesthouseName);
		}
		return alldetailsValue;
	}

	public boolean adminsaveProcessTable(String id,HttpServletRequest request)
	{
		final String userId=id;
	    final String ghId=request.getParameter("cityId");
		final String blockId=request.getParameter("guesthouseId");
		String sign=request.getParameter("sign");
		String signature="";
		String sign_desig="";
		List<ProcessModeration> alldetailsValue = null;
		ProcessModeration md=null;
		 String q1=adminQueries.getProperty("saveProcesstable");
		 String q2=adminQueries.getProperty("getCurrentModTable");
		 String q3=adminQueries.getProperty("saveinReservationMainTable");
		 String q4=adminQueries.getProperty("updateStatusModReq");
		 
		 
		 if(sign.equals("2")){	 	
			 	try {
			 		signature  = getPropValue("gad_go_sign_onduty");
					sign_desig = getPropValue("gad_designation_onduty");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				 try {
					signature  = getPropValue("gad_go_signature_name");
					sign_desig = getPropValue("gad_designation");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
		String	gosignature   = signature;
		String 	gosignaturedesig  = sign_desig;
		Integer id1=0;
		try {
			id1 = get_seqID("process_id");
			update_seqID("process_id", id1);
			System.out.println(id1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String process_id = id1.toString();
		jdbcTemplate.update(q1, new Object[] { 
				process_id,
				userId,
				"TCNF",
				 null,
				 ghId,
				 gosignature,
				 gosignaturedesig,
				 blockId
				 });
	
				alldetailsValue = jdbcTemplate.query(q2, new RowMapper<ProcessModeration>()
				{
					public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
						ProcessModeration response = new ProcessModeration();
						response.setBookingId(rs.getString("req_id"));
						response.setVcId(rs.getString("vc_id"));
						response.setQuotatype(rs.getString("quota_type"));
						response.setStatus(rs.getString("status"));
						response.setCityId(rs.getString("gh_id"));
						response.setBlockId(rs.getString("block_id"));
						response.setType(rs.getString("type"));						
						response.setFrmDt(rs.getString("from_date"));
						response.setToDt(rs.getString("to_date"));
						response.setReqDate(rs.getString("req_date"));
						return response;
					}
        		 },ghId,blockId);
				System.out.println(alldetailsValue);
			 
			 int i=0;
			 for( i=0;i<alldetailsValue.size();i++)
			 {
			 md=alldetailsValue.get(i);
			 System.out.println(md);
				
			 double max_beds=0,total_r=0,total_rep=0,dor_tot_l=0,dor_tot_g=0,total_r_public=0,max_beds_public=0;
			 
			 int number=0,nor_req=0,nob_g_req=0,nob_l_req=0,nod_g_req=0,nod_l_req=0;
			 
			 
			 
				double occu_r = 0;
				double occu_bg = 0;
				double occu_bl = 0;
				double occu_dorl = 0;
				double occu_dorg = 0;

				double occu_r_pub = 0;
				double occu_bg_pub = 0;
				double occu_bl_pub = 0;
				double occu_dorl_pub = 0;
				double occu_dorg_pub = 0;

				double temp_cnf_r = 0;
				double temp_cnf_bg = 0;
				double temp_cnf_bl = 0;
				double temp_cnf_dorg = 0;
				double temp_cnf_dorl = 0;

				double temp_wl_r = 0;
				double temp_wl_bg = 0;
				double temp_wl_bl = 0;
				double temp_wl_dorg = 0;
				double temp_wl_dorl = 0;

				double mod_room_obj_r = 0;
				double mod_room_obj_bg = 0;
				double mod_room_obj_bl = 0;
				double mod_room_obj_dorg = 0;
				double mod_room_obj_dorl = 0;
				
				try {
					total_r =  selectDBrooms(blockId, ghId, md.getQuotatype());
					max_beds = selectDBbeds(blockId, ghId, md.getQuotatype());
					dor_tot_l = selectDBdorGents(blockId, ghId, md.getQuotatype());
					dor_tot_g  = selectDBdorLadies(blockId, ghId, md.getQuotatype());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(md.getQuotatype().equals("C1")){ 
					////repair rooms
//					total_rep = this.get_repair_rooms(gh_id, block_id);
//					System.out.println("GoPiKa bfr total_r ::: "+total_r);
//					total_r = total_r - total_rep;
//					System.out.println("GoPiKa afr total_r ::: "+total_r);
//					System.out.println("GoPiKa 0 bal_room_pub ::: "+total_rep);
					//balance public rooms
					try {
						total_r_public = selectDBrooms(blockId, ghId,"PUBLIC");
						max_beds_public = selectDBbeds(blockId, ghId,"PUBLIC");
						occu_r_pub = selectDBoccupied(ghId, blockId, md.getQuotatype(), "R", md.getFrmDt(), md.getToDt());
						occu_bg_pub = selectDBoccupied(ghId, blockId, md.getQuotatype(), "BG",  md.getFrmDt(), md.getToDt());
						occu_bl_pub = selectDBoccupied(ghId, blockId, md.getQuotatype(), "BL",  md.getFrmDt(), md.getToDt());
						occu_dorg_pub = selectDBoccupied(ghId, blockId, md.getQuotatype(), "DG",  md.getFrmDt(), md.getToDt());
						occu_dorl_pub = selectDBoccupied(ghId, blockId, md.getQuotatype(), "DL", md.getFrmDt(), md.getToDt());
						
					} catch (Exception e) {
						System.out.println("Something Went Wrong....."+e);
					}

					double bal_room_pub = total_r_public - occu_r_pub - (Math.ceil(occu_bl_pub/max_beds_public)) - Math.ceil(occu_bg_pub/max_beds_public);
					System.out.println("GoPiKa 0 bal_room_pub ::: "+bal_room_pub);
					total_r = total_r + bal_room_pub; 
				}
				System.out.println("GoPiKa 1 total_r ::: "+total_r);

				try {
					occu_r = selectDBoccupied(ghId, blockId, md.getQuotatype(), "R", md.getFrmDt(), md.getToDt());
					occu_bg = selectDBoccupied(ghId, blockId, md.getQuotatype(), "BG",  md.getFrmDt(), md.getToDt());
					occu_bl = selectDBoccupied(ghId, blockId, md.getQuotatype(), "BL",  md.getFrmDt(), md.getToDt());
					occu_dorg = selectDBoccupied(ghId, blockId, md.getQuotatype(), "DG",  md.getFrmDt(), md.getToDt());
					occu_dorl = selectDBoccupied(ghId, blockId, md.getQuotatype(), "DL", md.getFrmDt(), md.getToDt());
				} catch (Exception e) {
					System.out.println("Something Went Wrong....."+e);
				}

				if(md.getType().equals("R")) nor_req=1;
				else if(md.getType().equals("BG")) nob_g_req=1; 
				else if(md.getType().equals("BL")) nob_l_req=1;

				else if(md.getType().equals("DG")) nod_g_req=1; 
				else if(md.getType().equals("DL")) nod_l_req=1;

				mod_room_obj_r = nor_req;
				mod_room_obj_bg = nob_g_req;
				mod_room_obj_bl = nob_l_req;
				mod_room_obj_dorg = nod_g_req;
				mod_room_obj_dorl = nod_l_req;

				System.out.println("GoPiKa 2 :::occu_r "+occu_r);
				System.out.println("GoPiKa 3 ::: (Math.ceil(occu_bl/max_beds)) "+(Math.ceil(occu_bl/max_beds)));
				System.out.println("GoPiKa 4 ::: Math.ceil(occu_bg/max_beds) "+Math.ceil(occu_bg/max_beds));
				////////////////////////////////////////////////////////process
				double bal_room = 0.0;

				try {
					bal_room = total_r - occu_r - (Math.ceil(occu_bl/max_beds)) - Math.ceil(occu_bg/max_beds);
				} catch (Exception e) {
					System.out.println("Something Went Wrong....."+e);
				}

				System.out.println("GoPiKa 5 ::: bal_room "+bal_room);
				if(bal_room > 0 )   //if(bal_room - mod_no_of_room >=  0)
				{
					if(bal_room - mod_room_obj_r >=  0)
					{
						temp_cnf_r = mod_room_obj_r;
					}
					else
					{
						temp_cnf_r = mod_room_obj_r - bal_room;
						temp_wl_r = mod_room_obj_r - temp_cnf_r;
					}  
				}
				else 
				{
					temp_wl_r = mod_room_obj_r;

				}       

				bal_room = total_r + temp_cnf_r - occu_r - Math.ceil(occu_bl/max_beds);

				double bal_possible_GB = bal_room * max_beds;
				double	bal_GB = bal_possible_GB - occu_bg;
				if(bal_GB > 0 )  
				{
					if(bal_GB - mod_room_obj_bg >=  0) //if bal_gents_bed- mod_no_of_GB >=  0)
					{
						temp_cnf_bg = mod_room_obj_bg;
					}
					else
					{
						temp_cnf_bg = mod_room_obj_bg-bal_GB;
						temp_wl_bg = mod_room_obj_bg-temp_cnf_bg;
					}   
				}
				else 
				{
					temp_wl_bg = mod_room_obj_bg;
				}       

				/////////////////////c
				bal_room = total_r + temp_cnf_r - occu_r - Math.ceil(occu_bg/max_beds);

				double bal_possible_LB = bal_room * max_beds;
				double	bal_LB = bal_possible_LB - occu_bl;

				if(bal_LB > 0 )  //     if(bal_gents_bed> 0 ) 
				{
					if(bal_LB - mod_room_obj_bl >=  0) //if bal_gents_bed- mod_no_of_GB >=  0)
					{
						temp_cnf_bl = mod_room_obj_bl;
					}
					else
					{
						temp_cnf_bl = mod_room_obj_bl-bal_LB;
						temp_wl_bl = mod_room_obj_bl-temp_cnf_bl;
					}   
				}
				else 
				{
					temp_wl_bl =    mod_room_obj_bl;
				}    


				//////////////////f
				bal_room = dor_tot_g - occu_dorg;
				if(bal_room > 0 )   //if(bal_room - mod_no_of_room >=  0)
				{
					if(bal_room - mod_room_obj_dorg >=  0)
					{
						temp_cnf_dorg = mod_room_obj_dorg;
					}
					else
					{
						temp_cnf_dorg = mod_room_obj_dorg - bal_room;
						temp_wl_dorg = mod_room_obj_dorg - temp_cnf_dorg;
					}  
				}
				else 
				{
					temp_wl_dorg = mod_room_obj_dorg;
				}       

				//////////////////g
				bal_room = dor_tot_l - occu_dorl;

				if(bal_room > 0 )   //if(bal_room - mod_no_of_room >=  0)
				{
					if(bal_room - mod_room_obj_dorl >=  0)
					{
						temp_cnf_dorl = mod_room_obj_dorl;
					}
					else
					{
						temp_cnf_dorl = mod_room_obj_dorl - bal_room;
						temp_wl_dorl = mod_room_obj_dorl - temp_cnf_dorl;
					}  
				}
				else 
				{
					temp_wl_dorl = mod_room_obj_dorl;
				}       
				System.out.println(temp_cnf_r);
				System.out.println(temp_cnf_bg);
				System.out.println(temp_cnf_bl);
				System.out.println(temp_cnf_dorg);
				System.out.println(temp_cnf_dorl);
				
				 for (int j=0;j<temp_cnf_r ;j++)
				    {
							Integer id2=0;
									try {
										id2 = get_seqID("res_id");
										update_seqID("res_id", id2);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									String res_id = id2.toString();
							        jdbcTemplate.update(q3, new Object[] { 					
							        		res_id,
									       md.getFrmDt(),
									       md.getToDt(),
									       md.getFrmDt(),
									       md.getToDt(),
									       "R",
									       "TCNF",
									       blockId,
									       md.getBookingId(),
									       process_id,
									       ghId,
									       1,
									       md.getQuotatype(),
									
							});
						}
				 for (int j=0;j<temp_cnf_bg ;j++)
				    {
							Integer id2=0;
									try {
										id2 = get_seqID("res_id");
										update_seqID("res_id", id2);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									String res_id = id2.toString();
							        jdbcTemplate.update(q3, new Object[] { 					
							        		res_id,
									       md.getFrmDt(),
									       md.getToDt(),
									       md.getFrmDt(),
									       md.getToDt(),
									       "BG",
									       "TCNF",
									       blockId,
									       md.getBookingId(),
									       process_id,
									       ghId,
									       1,
									       md.getQuotatype(),
									
							});
						}
				 for (int j=0;j<temp_cnf_bl ;j++)
				    {
							Integer id2=0;
									try {
										id2 = get_seqID("res_id");
										update_seqID("res_id", id2);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									String res_id = id2.toString();
							        jdbcTemplate.update(q3, new Object[] { 					
							        		res_id,
									       md.getFrmDt(),
									       md.getToDt(),
									       md.getFrmDt(),
									       md.getToDt(),
									       "BL",
									       "TCNF",
									       blockId,
									       md.getBookingId(),
									       process_id,
									       ghId,
									       1,
									       md.getQuotatype(),
									
							});
						}
				 for (int j=0;j<temp_cnf_dorg ;j++)
				    {
							Integer id2=0;
									try {
										id2 = get_seqID("res_id");
										update_seqID("res_id", id2);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									String res_id = id2.toString();
							        jdbcTemplate.update(q3, new Object[] { 					
							        		res_id,
									       md.getFrmDt(),
									       md.getToDt(),
									       md.getFrmDt(),
									       md.getToDt(),
									       "DG",
									       "TCNF",
									       blockId,
									       md.getBookingId(),
									       process_id,
									       ghId,
									       1,
									       md.getQuotatype(),
									
							});
						}
				 for (int j=0;j<temp_cnf_dorl ;j++)
				    {
							Integer id2=0;
									try {
										id2 = get_seqID("res_id");
										update_seqID("res_id", id2);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									String res_id = id2.toString();
							        jdbcTemplate.update(q3, new Object[] { 					
							        	   res_id,
									       md.getFrmDt(),
									       md.getToDt(),
									       md.getFrmDt(),
									       md.getToDt(),
									       "DL",
									       "TCNF",
									       blockId,
									       md.getBookingId(),
									       process_id,
									       ghId,
									       1,
									       md.getQuotatype(),
									
							});
						}
				       if(md.getStatus().equals("MOD")){
						System.out.println("if go ###");
						jdbcTemplate.update(q4, new Object[] { 
								"PRO",
								process_id,
								md.getBookingId()
								 });
					         }	 						 
				    }
				
			       return true;			
			 }	
	
	
	@Override
	public boolean cancelRequest(String reqId, String userId) {
		String sql = adminQueries.getProperty("canRequestForGH");
		jdbcTemplate.update(sql, new Object[] {
				"CAN",
				userId,
				reqId
		});		
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
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>()

			{
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration process = new ProcessModeration();
					process.setProcessId(rs.getString(1));
					process.setCreatedBy(rs.getString(2));
					process.setCreatedDate(rs.getString(3));
					process.setStatus(rs.getString(4));
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

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>()

			{
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					ProcessModeration process = new ProcessModeration();
					process.setProcessId(rs.getString(1));
					process.setCreatedBy(rs.getString(2));
					process.setCreatedDate(rs.getString(3));
					process.setStatus(rs.getString(4));
					process.setCityId(rs.getString(7));
					process.setGoSign(rs.getString(8));
					process.setGoSignDesig(rs.getString(9));
					process.setBlockId(rs.getString(10));
					return process;
				}
			}, cityName, guesthouseName);
		}
		return alldetailsValue;
	}

	@Override
	public ProcessModeration  getDetailsByprocessId(String process_id) 
	{	
	  String sql = adminQueries.getProperty("detailsbyid");
	  ProcessModeration processallData = jdbcTemplate.queryForObject(sql,new Object[]{ String.valueOf(process_id)} ,new RowMapper<ProcessModeration>() 
		{	
			public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException 
			{
				System.out.println("All process details by id");
				ProcessModeration process = new ProcessModeration();	
				process.setProcessId(rs.getString(1));
				process.setCreatedBy(rs.getString(2));
				process.setCreatedDate(rs.getString(3));
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
	public boolean cancelProcessRequest(String processId) {
		String q1 = adminQueries.getProperty("canProcessRequest");
		String q2 = adminQueries.getProperty("canProcessRequestmod_req");
		String q3 = adminQueries.getProperty("updatereservationtablestaus");
		String q4 = adminQueries.getProperty("getupdatestatusinGadRequest");
		jdbcTemplate.update(q1, new Object[] {
				"CAN",
				processId
		});	
		jdbcTemplate.update(q2, new Object[] {
				"MOD",
				processId
		});	
		List<ProcessModeration> processallData =selectDBpro(processId);
		  for(int i=0;i<processallData.size();i++)
		  {
			  ProcessModeration md1=processallData.get(i);
			  jdbcTemplate.update(q3, new Object[] { 
						"CAN",
						processId
						 });
			  
			  jdbcTemplate.update(q4, new Object[] { 
					  	"MOD",
						md1.getReqid()
						 });
		  }		 
		
		
		
		
		return true;
	}
	 @Override
	 public List<RoomBookingPojo> getdraftViewdetails(String process_id)
	 {
		    String sql = adminQueries.getProperty("draftviewQuery");
			List<RoomBookingPojo> alldetailss = jdbcTemplate.query(sql, new RowMapper<RoomBookingPojo>() 
			{
				public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException {
					System.out.println("draftListView...........");
					RoomBookingPojo res = new RoomBookingPojo();
					final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
					String fromDt = rs.getDate("from_date") != null ? sdf.format(rs.getDate("from_date")) : null;
					String toDt = rs.getDate("to_date") != null ? sdf.format(rs.getDate("to_date")) : null;
					res.setCityId(rs.getString("gh_id"));
					res.setBlockId(rs.getString("block_id"));
					//res.setCityName(rs.getString("gh_name"));	 
					//res.setBlockName(rs.getString("block_name"));
					String reqid=rs.getString("req_id");
					res.setBookingId(reqid);
					String t_purpose=null;
					String status=null;
					if (rs.getString("purpose") != null) {
						if (rs.getString("purpose").equals("Official")) {
							t_purpose="(offl)";
						}else if(rs.getString("purpose").equals("Onduty")){
							t_purpose="(Onduty)";
						}else if(rs.getString("purpose").equals("public")){
							t_purpose="(public)";
						}else if(rs.getString("purpose").equals("civil service(PT)")){
							t_purpose="(civil service(PT))";
						}else{
							t_purpose= "(Pvt)";	
						}
					}
					
					String t_confirmed_show = "";
					String t_waiting_show = "";
					Integer countroom =0;
					Integer countladies =0;
					Integer countgents =0;
					Integer countladiesDor =0;
					Integer countgentsDor =0;
					String block_name_short_conf =null;
					String block_name_short_wait = null;
					Integer countroomw =0;
					Integer countladiesw =0;
					Integer countgentsw =0;
					Integer countladieswDor =0;
					Integer countgentswDor =0;
					String vehicle=null;
					String vehicle1=null;
					try {
						countroom =conformroom(rs.getString("req_id"));
						 countladies =conformladies(rs.getString("req_id"));
						 countgents =conformgents(rs.getString("req_id"));
						 countladiesDor =conformladiesDor(rs.getString("req_id"));
						 countgentsDor =conformgentsDor(rs.getString("req_id"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					if(countroom!=0){
						if(countroom==1){
							t_confirmed_show = t_confirmed_show+countroom+" Room";
						}else{
							t_confirmed_show = t_confirmed_show+countroom+" Rooms";
						}
					}
					
					if(countladies!=0){
						if(countladies==1){
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countladies+" Ladies Bed";
						}else{
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countladies+" Ladies Beds";
						}
					}
					
					if(countgents!=0){
						if(countgents==1){
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countgents+" Gents Bed";
						}else{
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countgents+" Gents Beds";
						}
					}
					
					if(countladiesDor!=0){
						if(countladiesDor==1){
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countladiesDor+" Ladies Bed";
						}else{
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countladiesDor+" Ladies Beds";
						}
					}
					
					if(countgentsDor!=0){
						if(countgentsDor==1){
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countgentsDor+" Gents Bed";
						}else{
							if(!t_confirmed_show.equals("")){
								t_confirmed_show = t_confirmed_show+",";
							}
							t_confirmed_show = t_confirmed_show+countgentsDor+" Gents Beds";
						}
					}
					
					try {
						block_name_short_conf = short_conform_blocks(rs.getString("req_id"),rs.getString("gh_id") );
						block_name_short_wait = short_wait_blocks(rs.getString("req_id"),rs.getString("gh_id"));				
						 countroomw =conformroomw(rs.getString("req_id"));
						 countladiesw =conformladiesw(rs.getString("req_id"));
						 countgentsw =conformgentsw(rs.getString("req_id"));
						 countladieswDor =conformladiesDorw(rs.getString("req_id"));
						 countgentswDor =conformgentsDorw(rs.getString("req_id"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					if(countroomw!=0){
						if(countroomw==1){
							t_waiting_show = t_waiting_show+countroomw+" Room";
						}else{
							t_waiting_show = t_waiting_show+countroomw+" Rooms";
						}
					}
					
					if(countladiesw!=0){
						if(countladiesw==1){
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countladiesw+" Ladies Bed";
						}else{
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countladiesw+" Ladies Beds";
						}
					}
					
					if(countgentsw!=0){
						if(countgentsw==1){
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countgentsw+" Gents Bed";
						}else{
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countgentsw+" Gents Beds";
						}
					}
					
					if(countladieswDor!=0){
						if(countladieswDor==1){
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countladieswDor+" Ladies Bed";
						}else{
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countladieswDor+" Ladies Beds";
						}
					}
					
					if(countgentswDor!=0){
						if(countgentswDor==1){
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countgentswDor+" Gents Bed";
						}else{
							if(!t_waiting_show.equals("")){
								t_waiting_show = t_waiting_show+",";
							}
							t_waiting_show = t_waiting_show+countgentswDor+" Gents Beds";
						}
					}
					if(countroom!=0||countladies!=0||countgents!=0||countladiesDor!=0||countgentsDor!=0){ 
						status=  "Confirmed:"+ t_confirmed_show+"-"+block_name_short_conf+t_purpose;
					}if(countroomw!=0||countladiesw!=0||countgentsw!=0||countladieswDor!=0||countgentswDor!=0){
					 status="Waiting List:"+ t_waiting_show+"-"+block_name_short_wait+ t_purpose ;
					}
					System.out.println(status);
						String name=rs.getString("name");
				    	String	t_ext = rs.getString("ext");
						if(t_ext.equals("1")){
							t_ext = "Ext";
						}else{
							t_ext = "";
						}
						String t_family = rs.getString("family");
						if(t_family.equals("1")){
							t_family = " (With Family/Others)";
						}else{
							t_family = "";
						}
						String ext_fam = "";
						if(!t_family.equals("")){
							ext_fam = ""; //" - "+t_family;
						}
						if(!t_ext.equals("")){
							ext_fam = ext_fam +" - "+t_ext;
						}
						
						if(rs.getString("is_veh_req").equalsIgnoreCase("1"))
						{
							List<VehicleDetails> vehicle2=null;
							
							try {
							        vehicle2= getvehicleArrival(rs.getString("req_id"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							VehicleDetails vd=vehicle2.get(0);
							String arrivaltime=vd.getArrTm();
							String arrivalplace=vd.getArrPla();
							String arrivaldate=vd.getArrDt();
							String ftno=vd.getFlNoFr();
							vehicle=ftno+","+arrivaldate+","+arrivaltime+","+arrivalplace;				
						}
					
						if(rs.getString("is_veh_req1").equalsIgnoreCase("1"))
						{
							List<VehicleDetails> vehicle3=null;
							
							try {
							        vehicle3= getvehicleDepature(rs.getString("req_id"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							VehicleDetails vd=vehicle3.get(0);
							String depaturetime=vd.getDepTm();
							String depatureplace=vd.getDepPla();
							String depaturedate=vd.getDepDt();
							String ftno=vd.getFlNoTo();
							vehicle1=ftno+","+depaturedate+","+depaturetime+","+depatureplace;				
						}
						System.out.println(vehicle);
						System.out.println(vehicle1);
					res.setArrivaldetails(vehicle);
					res.setDepaturedetails(vehicle1);
					List<GuestDetailsPojo> guest=null;
					
					try {
						guest= getGuestDetails(rs.getString("req_id"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String ret="";
					for( int i=0;i<guest.size();i++)
					 {
						
					    GuestDetailsPojo guest1= guest.get(i);
					    System.out.println(guest1);
					    String name1=guest1.getGuestName();
					    String desg=guest1.getGuestDesignation();
					    String dept=guest1.getGuestDepartment();
					    if(!ret.equals("")){
							ret = ret + " & ";
						}
						ret = ret + name1;
						String t_desig=desg!=null?desg:"";
						if(!t_desig.equals("")){
							ret = ret + "," + t_desig;
						}
						String t_depa=dept!=null?dept:"";
						if(!t_depa.equals("")){
							ret = ret + "," + t_depa;
						}
						
					 }
					System.out.println(ret);
					res.setGuestdetails(ret);
					res.setDesg(rs.getString("designation"));	
					res.setFrmDt(fromDt);
					res.setToDt(toDt);
					res.setStatus(status);
					return res;
				}
			}, process_id,process_id,process_id,process_id,process_id);
			return alldetailss;
		 	 
	 }
	
	
	 public Integer conformroom(String req_id) throws Exception
		{
			Integer countroom=0;
			String room="select count(*) from reservation_main_table where type='R' and req_id=? and status!='CAN'";
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
	 public Integer conformladies(String req_id) throws Exception
		{
			Integer countdorlady=0;
			String room="select count(*) from reservation_main_table where type='BL' and req_id=? and status!='CAN'";
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
	
	 public Integer conformgents(String req_id) throws Exception
		{
			Integer countgents=0;
			String room="select count(*) from reservation_main_table where type='BG' and req_id=? and status!='CAN'";
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
	 public Integer conformgentsDor(String req_id) throws Exception
		{
			Integer countdorgents=0;
			String room="select count(*) from reservation_main_table where type='DG' and req_id=? and status!='CAN'";
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
	 public Integer conformladiesDor(String req_id) throws Exception
		{
			Integer countladies=0;
			String room="select count(*) from reservation_main_table where type='DL' and req_id=? and status!='CAN'";
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
	 public Integer conformroomw(String req_id) throws Exception
		{
			Integer countroom=0;
			String room="select count(*) from reservation_waitinglist where type='R' and req_id=? and status!='CAN'";
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
	 public Integer conformladiesw(String req_id) throws Exception
		{
			Integer countdorlady=0;
			String room="select count(*) from reservation_waitinglist where type='BL' and req_id=? and status!='CAN'";
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
	
	 public Integer conformgentsw(String req_id) throws Exception
		{
			Integer countgents=0;
			String room="select count(*) from reservation_waitinglist where type='BG' and req_id=? and status!='CAN'";
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
	 public Integer conformgentsDorw(String req_id) throws Exception
		{
			Integer countdorgents=0;
			String room="select count(*) from reservation_waitinglist where type='DG' and req_id=? and status!='CAN'";
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
	 public Integer conformladiesDorw(String req_id) throws Exception
		{
			Integer countladies=0;
			String room="select count(*) from reservation_waitinglist where type='DL' and req_id=? and status!='CAN'";
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
	
	 public String  short_conform_blocks( String req_id,String gh_id) throws Exception {
			String sql = "select DISTINCT(block_name_short) from(((select gh_id,block_id from reservation_main_table where status!='CAN' and req_id = ? and gh_id=?))) cc join (select * from room_block) dd on cc.gh_id=dd.gh_id and cc.block_id=dd.block_id";
			String block_name_short=null;
			List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rownumber) throws SQLException {
					String block_name_short=null;
					block_name_short = rs.getString("block_name_short");

					return block_name_short;
				}
			}, req_id,gh_id);
			if (null != users && !users.isEmpty()) {
				block_name_short = users.get(0);
			}
			return block_name_short;
		}
	 public String  short_wait_blocks( String req_id,String gh_id) throws Exception {
			String sql = "select DISTINCT(block_name_short) from(((select gh_id,block_id from reservation_waitinglist where status!='CAN' and req_id = ? and gh_id=?))) cc join (select * from room_block) dd on cc.gh_id=dd.gh_id and cc.block_id=dd.block_id";
			String short_wait_blocks=null;
			List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
				@Override
				public String mapRow(ResultSet rs, int rownumber) throws SQLException {
					String short_wait_blocks=null;
					short_wait_blocks = rs.getString("block_name_short");

					return short_wait_blocks;
				}
			}, req_id,gh_id);
			if (null != users && !users.isEmpty()) {
				short_wait_blocks = users.get(0);
			}
			return short_wait_blocks;
		}
	 public List<GuestDetailsPojo>  getGuestDetails( String req_id) throws Exception {
			String sql = "select * from guest_details where req_id=? order by guest_id";
			List<GuestDetailsPojo> users = jdbcTemplate.query(sql, new RowMapper <GuestDetailsPojo>() {
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
			},req_id);	
			return users;
		}
	 public List<VehicleDetails>  getvehicleArrival( String req_id) throws Exception {
			String sql = "select * from vehicle_req where req_id=?";
			List<VehicleDetails> users = jdbcTemplate.query(sql, new RowMapper <VehicleDetails>() {
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
			},req_id);	
			return users;
		}
	 public List<VehicleDetails>  getvehicleDepature( String req_id) throws Exception {
			String sql = "select * from vehicle_req1 where req_id=?";
			List<VehicleDetails> users = jdbcTemplate.query(sql, new RowMapper <VehicleDetails>() {
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
			},req_id);	
			return users;
		}
	
	    @Override
		public boolean confirmProcess(String proid) {
		 
		    ProcessModeration md= getDetailsByprocessId(proid);
			String q1 = adminQueries.getProperty("updateProcessTable");
			String q2 = adminQueries.getProperty("updatemodreqStatusTable");
			String q3 = adminQueries.getProperty("updatereservationtablestaus");
			String q4 = adminQueries.getProperty("getupdatestatusinGadRequest");
			
			
			System.out.println(proid);
			System.out.println(md);
			jdbcTemplate.update(q1, new Object[] { 
					md.getOfficeorderno(),
					"PRO",
				    proid
					 });
			  jdbcTemplate.update(q2, new Object[] {
					  
					 "PRO",
					  proid
			  });
			  List<ProcessModeration> processallData =selectDBpro(proid);
			  for(int i=0;i<processallData.size();i++)
			  {
				  ProcessModeration md1=processallData.get(i);
				  jdbcTemplate.update(q3, new Object[] { 
							"CNF",
							proid
							 });
				  
				  jdbcTemplate.update(q4, new Object[] { 
						  	"PRO",
							md1.getReqid()
							 });
			  }		 
			
			return true;
		}
	
	 
	   
		public List<ProcessModeration>  selectDBpro(String process_id) 
		{	
		  String sql =  "select * from reservation_main_table where process_id=?";
		 List<ProcessModeration> processallData = jdbcTemplate.query(sql ,new RowMapper<ProcessModeration>() 
			{	
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException 
				{
					ProcessModeration process = new ProcessModeration();	
					process.setProcessId(rs.getString("process_id"));
					process.setReqid(rs.getString("req_id"));
					process.setStatus(rs.getString("status"));		
					process.setCityId(rs.getString("gh_id"));
					process.setBlockId(rs.getString("block_id"));
					
					return process;
					
				}
			},process_id);
			return processallData;
		}
		@Override
		public List <ProcessModeration>  getconfirmDraftlist(HttpServletRequest request)
		{
			List<ProcessModeration> alldetailsValue = null;
			String cityName = "";
			String guesthouseName = "";
			
			if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
				cityName = request.getParameter("cityName");
			if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
				guesthouseName = request.getParameter("guesthouseName");
			
			
			
			if (cityName.equals("") && guesthouseName.equals("")) {
				String sql = adminQueries.getProperty("confirmDraftallList");
				alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>()

				{
					public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
						final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String ceateddate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date")) : null;
						ProcessModeration process = new ProcessModeration();
						process.setProcessId(rs.getString(1));
						process.setCreatedBy(rs.getString(2));
						process.setCreatedDate(ceateddate);
						process.setStatus(rs.getString(4));
						process.setCityId(rs.getString(7));
						process.setGoSign(rs.getString(8));
						process.setGoSignDesig(rs.getString(9));
						process.setBlockId(rs.getString(10));
						return process;
					}

			});

			}
			if (!cityName.equals("") && !guesthouseName.equals("")) {
				String sql = adminQueries.getProperty("confirmDraftallListwithGHouseAndCity");

				alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>()

				{
					public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
						final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						String ceateddate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date")) : null;
						ProcessModeration process = new ProcessModeration();
						process.setProcessId(rs.getString(1));
						process.setCreatedBy(rs.getString(2));
						process.setCreatedDate(ceateddate);
						process.setStatus(rs.getString(4));
						process.setCityId(rs.getString(7));
						process.setGoSign(rs.getString(8));
						process.setGoSignDesig(rs.getString(9));
						process.setBlockId(rs.getString(10));
						return process;
					}
				}, cityName, guesthouseName);
			}
			return alldetailsValue;
		}
}
	
	
	
	

