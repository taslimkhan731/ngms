  package com.stl.ngms.guest.dao;

import java.io.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stl.ngms.common.MailUtil;
import com.stl.ngms.common.SendSMS;
import com.stl.ngms.common.model.FeedbackRequestModel;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.CommonComboBoxResponseModel;
import com.stl.ngms.guest.model.CommonResponseModel;
import com.stl.ngms.guest.model.DashboardResponse;
import com.stl.ngms.guest.model.ExtensionRequestModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.GuestDetailsResponse;
import com.stl.ngms.guest.model.GuestDetailsResponseForOrder;
import com.stl.ngms.guest.model.PaymentReceiptModel;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.utils.Utils;


@Repository("guestDao")
public class GuestDaoImpl implements GuestDao{

	final static Logger logger = Logger.getLogger(GuestDaoImpl.class);

	@Autowired JdbcTemplate jdbcTemplate;

	@Autowired Environment env;
	
	@Autowired MailUtil mail;
		
	@Autowired SendSMS smsSend;

	@Autowired private Properties approverQueries;

	@Override
	public List<BookingResponseModel> getUserRequestList(String id, String userId) {
		String sql = approverQueries.getProperty("getBookingListSQL");
		if(id != null) {
			if(!id.isEmpty()) {
				if(id.equalsIgnoreCase("con")) {
					sql = approverQueries.getProperty("getConvenceDetails");
				}else if(id.equalsIgnoreCase("GH")){
					sql = sql.concat(" and a.roomtype!='C'");
				}
			}
		}
		sql=sql.concat("order by req_date::date Desc");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<BookingResponseModel> users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("quota_desc"));
				if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					booking.setConveyance("No");
				}
				if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					if(!booking.getConveyance().equalsIgnoreCase("Yes")) {
						booking.setConveyance("No");
					}
				}
				
				booking.setFromDate(rs.getString("from_date"));
				booking.setToDate(rs.getString("to_date"));
				booking.setBookingDt(rs.getString("req_date") == null ? "" : sdf.format(rs.getDate("req_date")));
				booking.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				booking.setCityName(rs.getString("block_id"));
				booking.setPurpose(rs.getString("item_title"));
				String status = rs.getString("status");
				String status_desc = "";
				if(status.equals("REQ")){
					status_desc = " Not taken up for processing";
				}
				if(status.equals("CAN")){
					status_desc = "Cancelled";
				}
				if(status.equalsIgnoreCase("MOD")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("PRO")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("ALO")){
					status_desc = "Alloted";
				}
				if(status.equals("CIN")){
					status_desc = "Check In";
				}
				if(status.equals("COUT")){
					status_desc = "Check Out";
				}
				booking.setStatus(status);
				booking.setStatusDesc(status_desc);
				if((rs.getString("nor_req")!=null?!rs.getString("nor_req").equals("0"):false)||(rs.getString("nob_g_req")!=null?!rs.getString("nob_g_req").equals("0"):false)) {
					booking.setBookingType("room");
				}
				if(rs.getString("nod_g_req")!=null?!rs.getString("nod_g_req").equals("0"):false) {
					booking.setBookingType("dormitory");
				}
				
				return booking;
			}
		},userId);
		return users;
	}

	@Override
	public boolean cancelRequest(String reqId, String userId) {
		String sql = approverQueries.getProperty("canRequestForGH");
		jdbcTemplate.update(sql, new Object[] {
				"CAN",
				userId,
				reqId
		});		
		return true;
	}
	@Override
	public boolean cancelRequest1(String reqId, String userId) {
		String sql = approverQueries.getProperty("canRequestForGH");
		jdbcTemplate.update(sql, new Object[] {
				"RCAN",
				userId,
				reqId
		});		
		return true;
	}
	@Override
	public BookingResponseModel getRequest(String reqId, String userId) {
		String sql = approverQueries.getProperty("getBookingListByIdSQL");
		BookingResponseModel booking = null;
		List<BookingResponseModel> users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("quota_desc"));
				if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					booking.setConveyance("No");
				}
				if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					if(!booking.getConveyance().equalsIgnoreCase("Yes")) {
						booking.setConveyance("No");
					}
				}
				booking.setFromDate(rs.getString("from_date").toString());
				booking.setToDate(rs.getString("to_date").toString());
				booking.setCityName(rs.getString("gh_name"));
				booking.setGhName(rs.getString("block_name"));
				booking.setPurpose(rs.getString("purpose"));
				booking.setBookingDt(rs.getString("req_date").toString());
				booking.setNoRooms(rs.getString("nor_req"));
				booking.setNoBeds(rs.getString("nob_g_req"));
				booking.setMobile(rs.getString("mobile_no"));
				booking.setOffice(rs.getString("address"));
				booking.setDesg(rs.getString("desg"));
				String status = rs.getString("status");
				String status_desc = "";
				if(status.equals("REQ")){
					status_desc = " Not taken up for processing";
				}
				if(status.equals("CAN")){
					status_desc = "Cancelled";
				}
				if(status.equalsIgnoreCase("MOD")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("PRO")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("ALO")){
					status_desc = "Alloted";
				}
				if(status.equals("CIN")){
					status_desc = "Check In";
				}
				if(status.equals("COUT")){
					status_desc = "Check Out";
				}
				booking.setStatus(status);
				booking.setStatusDesc(status_desc);
				return booking;
			}
		},userId,reqId);
		if(null != users && !users.isEmpty()){
			booking = users.get(0);
		}
		return booking;
	}

	@Override
	public List<DashboardResponse> getGuestDashboard(String userId) {
		String sql = approverQueries.getProperty("getBookingListSQL");
		DashboardResponse delBha = new DashboardResponse();
		DashboardResponse delNiw = new DashboardResponse();
		DashboardResponse delSad = new DashboardResponse();
		DashboardResponse mumBha = new DashboardResponse();
		DashboardResponse utkBha = new DashboardResponse();
		DashboardResponse cheBha = new DashboardResponse();
		List<DashboardResponse> users = jdbcTemplate.query(sql, new RowMapper<DashboardResponse>() {
			@Override
			public DashboardResponse mapRow(ResultSet rs, int rownumber) throws SQLException {
				DashboardResponse booking = new DashboardResponse();
				booking.setBlockId(rs.getString("block_id"));
				String status = rs.getString("status");
				booking.setStatus(status);
				return booking;
			}
		},userId);

		List<DashboardResponse> list = new ArrayList<>();
		System.out.println(users);
		if(users != null) {
			for(DashboardResponse item : users) {
				if(item.getBlockId().equalsIgnoreCase("01")) {
					delBha.setBlockId(item.getBlockId());
					if(item.getStatus().equals("REQ") || item.getStatus().equals("CAN")){
						if(delBha.getAppliedCount() == null) {
							delBha.setAppliedCount(1);
						}else {
							delBha.setAppliedCount(delBha.getAppliedCount() + 1);
						}
					}else {
						if(delBha.getAppliedCount() == null) {
							delBha.setAppliedCount(1);
						}else {
							delBha.setAppliedCount(delBha.getAppliedCount() + 1);
						}

						if(delBha.getActionCount() == null) {
							delBha.setActionCount(1);
						}else {
							delBha.setActionCount(delBha.getActionCount() + 1);
						}
					}
				}else if(item.getBlockId().equalsIgnoreCase("02")) {
					delNiw.setBlockId(item.getBlockId());
					if(item.getStatus().equals("REQ") || item.getStatus().equals("CAN")){
						if(delNiw.getAppliedCount() == null) {
							delNiw.setAppliedCount(1);
						}else {
							delNiw.setAppliedCount(delNiw.getAppliedCount() + 1);
						}
					}else {
						if(delNiw.getAppliedCount() == null) {
							delNiw.setAppliedCount(1);
						}else {
							delNiw.setAppliedCount(delNiw.getAppliedCount() + 1);
						}
						if(delNiw.getActionCount() == null) {
							delNiw.setActionCount(1);
						}else {
							delNiw.setActionCount(delNiw.getActionCount() + 1);
						}
					}
				}else if(item.getBlockId().equalsIgnoreCase("03")) {
					delSad.setBlockId(item.getBlockId());
					if(item.getStatus().equals("REQ") || item.getStatus().equals("CAN")){
						if(delSad.getAppliedCount() == null) {
							delSad.setAppliedCount(1);
						}else {
							delSad.setAppliedCount(delSad.getAppliedCount() + 1);
						}
					}else {
						if(delSad.getAppliedCount() == null) {
							delSad.setAppliedCount(1);
						}else {
							delSad.setAppliedCount(delSad.getAppliedCount() + 1);
						}

						if(delSad.getActionCount() == null) {
							delSad.setActionCount(1);
						}else {
							delSad.setActionCount(delSad.getActionCount() + 1);
						}
					}
				}else if(item.getBlockId().equalsIgnoreCase("04")) {
					mumBha.setBlockId(item.getBlockId());
					if(item.getStatus().equals("REQ") || item.getStatus().equals("CAN")){
						if(mumBha.getAppliedCount() == null) {
							mumBha.setAppliedCount(1);
						}else {
							mumBha.setAppliedCount(mumBha.getAppliedCount() + 1);
						}
					}else {
						if(mumBha.getAppliedCount() == null) {
							mumBha.setAppliedCount(1);
						}else {
							mumBha.setAppliedCount(mumBha.getAppliedCount() + 1);
						}

						if(mumBha.getActionCount() == null) {
							mumBha.setActionCount(1);
						}else {
							mumBha.setActionCount(mumBha.getActionCount() + 1);
						}
					}
				}else if(item.getBlockId().equalsIgnoreCase("05")) {
					utkBha.setBlockId(item.getBlockId());
					if(item.getStatus().equals("REQ") || item.getStatus().equals("CAN")){
						if(utkBha.getAppliedCount() == null) {
							utkBha.setAppliedCount(1);
						}else {
							utkBha.setAppliedCount(utkBha.getAppliedCount() + 1);
						}
					}else {
						if(utkBha.getAppliedCount() == null) {
							utkBha.setAppliedCount(1);
						}else {
							utkBha.setAppliedCount(utkBha.getAppliedCount() + 1);
						}

						if(utkBha.getActionCount() == null) {
							utkBha.setActionCount(1);
						}else {
							utkBha.setActionCount(utkBha.getActionCount() + 1);
						}
					}
				}else if(item.getBlockId().equalsIgnoreCase("06")) {
					cheBha.setBlockId(item.getBlockId());
					if(item.getStatus().equals("REQ") || item.getStatus().equals("CAN")){
						if(cheBha.getAppliedCount() == null) {
							cheBha.setAppliedCount(1);
						}else {
							cheBha.setAppliedCount(cheBha.getAppliedCount() + 1);
						}
					}else {
						if(cheBha.getAppliedCount() == null) {
							cheBha.setAppliedCount(1);
						}else {
							cheBha.setAppliedCount(cheBha.getAppliedCount() + 1);
						}

						if(cheBha.getActionCount() == null) {
							cheBha.setActionCount(1);
						}else {
							cheBha.setActionCount(cheBha.getActionCount() + 1);
						}
					}
				}
			}
			if(delBha.getBlockId() != null) {
				list.add(delBha);
			}
			if(delNiw.getBlockId() != null) {
				list.add(delNiw);
			}
			if(delSad.getBlockId() != null) {
				list.add(delSad);
			}
			if(mumBha.getBlockId() != null) {
				list.add(mumBha);
			}
			if(utkBha.getBlockId() != null) {
				list.add(utkBha);
			}
			if(cheBha.getBlockId() != null) {
				list.add(cheBha);
			}
		}
		return list;
	}

	@Override
	public List<CommonResponseModel> getGhResponse() {
		String sql = "select * from gh_details order by gh_id";
		List<CommonResponseModel> users = jdbcTemplate.query(sql, new RowMapper<CommonResponseModel>() {
			@Override
			public CommonResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				CommonResponseModel booking = new CommonResponseModel();
				booking.setCityId(rs.getString("gh_id"));
				booking.setCityName(rs.getString("gh_name"));
				return booking;
			}
		});
		return users;
	}

	@Override
	public List<BookingResponseModel> getUserExtension(String userId) {
		String sql = approverQueries.getProperty("getBookingListForExtensionSQL");
		List<BookingResponseModel> users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("quota_desc"));
				if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					booking.setConveyance("No");
				}
				if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					if(!booking.getConveyance().equalsIgnoreCase("Yes")) {
						booking.setConveyance("No");
					}
				}
				booking.setFromDate(rs.getString("from_date"));
				booking.setToDate(rs.getString("to_date"));
				booking.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				String purpose=rs.getString("purpose");
				System.out.println(purpose);
				String purpose1="";
				if(purpose.equals("1"))
				{
					purpose1="Official";
				}
				if(purpose.equals("2"))
				{
					purpose1="Non-Official";
				}
				if(purpose.equals("3"))
				{
					purpose1="Patient";
				}
				if(purpose.equals("4"))
				{
					purpose1="Student";
				}
				System.out.println(purpose1);
				booking.setPurpose(purpose1);
				String status = rs.getString("status");
				String status_desc = "";
				if(status.equals("REQ")){
					status_desc = " Not taken up for processing";
				}
				if(status.equals("CAN")){
					status_desc = "Cancelled";
				}
				if(status.equalsIgnoreCase("MOD")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("PRO")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("ALO")){
					status_desc = "Alloted";
				}
				if(status.equals("CIN")){
					status_desc = "Check In";
				}
				if(status.equals("COUT")){
					status_desc = "Check Out";
				}
				booking.setStatus(status);
				booking.setStatusDesc(status_desc);
				return booking;
			}
		},userId);
		return users;
	}
	@Override
	public List<BookingResponseModel> getUserCancel(String userId) {
		String sql = approverQueries.getProperty("getBookingListForCancelSQL");
		List<BookingResponseModel> users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("quota_desc"));
				if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					booking.setConveyance("No");
				}
				if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					if(!booking.getConveyance().equalsIgnoreCase("Yes")) {
						booking.setConveyance("No");
					}
				}
				booking.setFromDate(rs.getString("from_date"));
				booking.setToDate(rs.getString("to_date"));
				booking.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				String purpose=rs.getString("purpose");
				System.out.println(purpose);
				String purpose1="";
				if(purpose.equals("1"))
				{
					purpose1="Official";
				}
				if(purpose.equals("2"))
				{
					purpose1="Non-Official";
				}
				if(purpose.equals("3"))
				{
					purpose1="Patient";
				}
				if(purpose.equals("4"))
				{
					purpose1="Student";
				}
				System.out.println(purpose1);
				booking.setPurpose(purpose1);
				String status = rs.getString("status");
				String status_desc = "";
				if(status.equals("REQ")){
					status_desc = " Not taken up for processing";
				}
				if(status.equals("CAN")){
					status_desc = "Cancelled";
				}
				if(status.equalsIgnoreCase("MOD")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("PRO")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("ALO")){
					status_desc = "Alloted";
				}
				if(status.equals("CIN")){
					status_desc = "Check In";
				}
				if(status.equals("COUT")){
					status_desc = "Check Out";
				}
				booking.setStatus(status);
				booking.setStatusDesc(status_desc);
				return booking;
			}
		},userId);
		return users;
	}

	@Override
	public boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception {
		Integer id = get_seqID("req_id");
		update_seqID("req_id", id);
		String bookingId = "P" + id.toString();
		String reqId = requestPojo.getReqId();
		Boolean status = false;
		System.out.println("BookingId " + bookingId);
		requestPojo = getBookingDetails(requestPojo, userId);
		//requestPojo.setReqId(bookingId);

		//Request Details Save
		String sql = approverQueries.getProperty("saveBookingDetailsSQL");
		int i=jdbcTemplate.update(sql, new Object[] { 
				bookingId,
				requestPojo.getGhId(),
				requestPojo.getReq_date(),
				requestPojo.getFromDate(),
				requestPojo.getToDate(),
				requestPojo.getNor_req(),
				requestPojo.getNob_g_req(),
				requestPojo.getStatus(),
				0,
				requestPojo.getMode_of_req(),
				requestPojo.getPurpose(),
				requestPojo.getExt(),
				requestPojo.getAddress(),
				userId,
				requestPojo.getReq_date(),
				requestPojo.getNod_g_req(),
				requestPojo.getMobile_no(),
				requestPojo.getExisting_req_id(),
				0,
				requestPojo.getBlock_id(),
				requestPojo.getFile_path(),
				requestPojo.getCategory_type(),
				requestPojo.getDesg(),
				requestPojo.getRoomType(),
				requestPojo.getEmail(),
				requestPojo.getCatg_file_path(),
				requestPojo.getDuring_stay_conveancy()
		});		
		List<GuestDetailsResponse> list = getGuestDetails(reqId);
		if(list != null) {
			sql = approverQueries.getProperty("saveGuestDetailsSQL");
			for(GuestDetailsResponse item : list) {
				id = get_seqID("guest_id");
				String bookingId1 = "P_" + id.toString();
				update_seqID("guest_id", id);
				jdbcTemplate.update(sql, new Object[] { 
						bookingId,
						bookingId1,
						item.getGuestName(),
						item.getAge(),
						item.getGender(),
						item.getAddress(),
						item.getDesg(),
						item.getDepartment()
				});		
			}
			status = true;
		}
		
		if(i!=0 && status==true) {
			sql = approverQueries.getProperty("changeStatusinExtension");
			jdbcTemplate.update(sql, new Object[] { 
					"PEXT",
					reqId
			});
			status=true;
			}
		
		return status;
	}

	public Integer get_seqID(String p_id) throws Exception {
		String sql = "select curvalue from sequence_id where id=? for update";
		Integer l_curvalue =0;
		List<Integer> users = jdbcTemplate.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {
				Integer l_curvalue =0;
				l_curvalue = rs.getInt("curvalue");
				return l_curvalue;
			}
		},p_id);

		if(null != users && !users.isEmpty()){
			l_curvalue = users.get(0);
		}
		return l_curvalue + 1;
	}
	

	public String  getFeedbackDescription(Integer id){
		System.out.println("jbbakbkj"+id);
		String sql = "SELECT  description FROM public.feedback_master where id=?";
		String l_curvalue =null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String l_curvalue =null;
				l_curvalue = rs.getString("description");
				return l_curvalue;
			}
		},id);

		if(null != users && !users.isEmpty()){
			l_curvalue = users.get(0);
		}
		return l_curvalue ;
	}
	
	public String  getFeedbackRequest(String id){
		System.out.println("jbbakbkj"+id);
		String sql = "SELECT  req_id FROM public.feedback_system where req_id=? and status='opened'";
		String l_curvalue =null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String l_curvalue =null;
				l_curvalue = rs.getString("req_id");
				return l_curvalue;
			}
		},id);

		if(null != users && !users.isEmpty()){
			l_curvalue = users.get(0);
		}
		return l_curvalue ;
	}
	public String get_filePath(String p_id) throws Exception {
		String sql = "select file_path from gad_request where req_id=?";
		String path =null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String l_curvalue =null;
				l_curvalue = rs.getString("file_path");
				return l_curvalue;
			}
		},p_id);

		if(null != users && !users.isEmpty()){
			path = users.get(0);
		}
		return path;
	}
	public String get_filePath1(String p_id) throws Exception {
		String sql = "select catg_file_path from gad_request where req_id=?";
		String path =null;
		List<String> users = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {
				String l_curvalue =null;
				l_curvalue = rs.getString("catg_file_path");
				return l_curvalue;
			}
		},p_id);

		if(null != users && !users.isEmpty()){
			path = users.get(0);
		}
		return path;
	}

	public boolean update_seqID(String p_id, Integer p_curvalue) throws Exception {
		String sql = "Update sequence_id set curvalue=? where id=?";
		jdbcTemplate.update(sql, new Object[] {
				p_curvalue,
				p_id
		});	
		return true;
	}

	public ExtensionRequestModel getBookingDetails(ExtensionRequestModel response,String userId) throws Exception {
		String sql = approverQueries.getProperty("getBookingDetailsByIdSQL");
		final Date toDate = response.getToDate();
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
				res.setFromDate(rs.getDate("to_date"));
				res.setToDate(toDate);
				res.setMobile_no(rs.getString("mobile_no"));
				res.setMode_of_req(rs.getString("mode_of_req"));
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
		
				return res;
			}
		},userId,response.getReqId());

		if(null != users && !users.isEmpty()){
			response = users.get(0);
		}
		return response;
	}

	public List<GuestDetailsResponse> getGuestDetails(String bookingId) throws Exception {
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
				return res;
			}
		},bookingId);

		return users;
	}

	@Override
	public List<CommonComboBoxResponseModel> getDesignationList(String userId, String catId) {
		String query = approverQueries.getProperty("getAllDesgListSQL");
		List<CommonComboBoxResponseModel> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<CommonComboBoxResponseModel>() {
			@Override
			public CommonComboBoxResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
				res.setId(rs.getString("vc_id"));
				res.setName(rs.getString("desg_category"));
				return res;
			}
		},catId);
		return catgList;
	}
	@Override
	public List<CommonComboBoxResponseModel> getCategoryList(String userId) {
		String query = approverQueries.getProperty("getAllCatgListSQL");
		List<CommonComboBoxResponseModel> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<CommonComboBoxResponseModel>() {
			@Override
			public CommonComboBoxResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
				res.setId(rs.getString("quota_type"));
				res.setName(rs.getString("quota_desc"));
				return res;
			}
		});
		return catgList;
	}
	@Override
	public List<CommonComboBoxResponseModel> getPurposeList(String userId) {
		String query = approverQueries.getProperty("getAllPurpListSQL");
		List<CommonComboBoxResponseModel> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<CommonComboBoxResponseModel>() {
			@Override
			public CommonComboBoxResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
				res.setId(rs.getString("item_id"));
				res.setName(rs.getString("item_title"));
				return res;
			}
		});
		return catgList;
	}

	@Override
	public String addBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		String ghName = null;
		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException{
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId",rs.getString("gh_id"));
				res.put("bId",rs.getString("block_id"));
				res.put("bName",rs.getString("block_name"));
				return res;
			}
		}, data.getBlockId());
		if(blockList==null||blockList.size()==0) {
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
		System.out.println(data.getRequestid());
		String reqId=null;
		if(null==data.getRequestid())
		{
		Integer id = get_seqID("req_id");
		update_seqID("req_id", id);
		 reqId = "P" + id.toString();
		}else{
			Integer id = get_seqID("req_id");
			update_seqID("req_id", id);
			 reqId = "O" + id.toString();
		}
		Date frmDt = !data.getFrmDt().equals("")?sdf.parse(data.getFrmDt()):null;
		Date toDt = !data.getToDt().equals("")?sdf.parse(data.getToDt()):null;
		Date arrDt = !data.getArrDt().equals("")?sdf.parse(data.getArrDt()):null;
		Date depDt = !data.getDepDt().equals("")?sdf.parse(data.getDepDt()):null;

		Date arrtm = !data.getArrTm().equals("")?stf.parse(data.getArrDt() + " " + data.getArrTm()):null;
		Date deptm = !data.getDepTm().equals("")?stf.parse(data.getDepDt() + " " + data.getDepTm()):null;

		String q1 = approverQueries.getProperty("saveBookingDetailsSQl");
		String q2 = approverQueries.getProperty("saveArrivalDtlsSQl");
		String q3 = approverQueries.getProperty("saveDepartureDtlsSQl");
		String q4 = approverQueries.getProperty("saveGuestDetailsSQL");
		String q5=  approverQueries.getProperty("updateOrisaoneDetailsSQL");
		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory = env.getProperty("media.base.path") + File.separator + 
					env.getProperty("sup.doc.folder") + File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			System.out.println("saved document file path :: "+filepath);
			if(filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			}
		}
		String catg_filepath= null;
		if (null != data.getCatgDoc()) {
			String fileSeperator1 = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory1 = env.getProperty("media.base.path") + File.separator + 
					env.getProperty("sup.doc.folder") + File.separator + fileSeperator1;
			catg_filepath = Utils.saveFileInSysPath(data.getCatgDoc(), directory1);
			System.out.println("saved document category file path :: "+catg_filepath);
			if(catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
			}
		}
		String status = null;
		if(!data.getRoomType().equalsIgnoreCase("C")) {
			status = "REQ";
		}else {
			status = "ALO";
		}
		int iddd=jdbcTemplate.update(q1, new Object[] { 
			reqId,
			blockList.get(0).get("ghId"),
			frmDt,
			toDt,
			data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
			data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
			"0",			
			status,
			!data.getArrDt().equals("")?"1":"0",
			"PUB",
			data.getPurp(),
			"0",
			data.getOffAddr(),
			userId,
			data.getBookingType() == null ? "0" : (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
			"0",
			"0",
			data.getMob(),
			data.getEmailId(),
			"0",
			!data.getDepDt().equals("")?"1":"0",
			data.getBlockId(), // block Id
			filepath, // file path
			data.getCatg(),
			data.getDesg(),
			data.getRoomType(),
			catg_filepath,
			data.getDuringstay(),
			data.getCatgType()
		});
		System.out.println(iddd);
		System.out.println(data.getBookingType());
		
		String msg = "";
		if(data.getBookingType() != null) {
			if(data.getBookingType().equalsIgnoreCase("room")) {
				if(data.getNoOfRooms() != null) {
					if(!data.getNoOfRooms().isEmpty()) {
						if(!data.getNoOfRooms().equalsIgnoreCase("0")) {
							msg = data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0") + " Rooms/Beds/Dormitory in " + blockList.get(0).get("bName") + "," + ghName;
						}
					}
				}else {
					msg = data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0") + " Rooms/Beds/Dormitory in " + blockList.get(0).get("bName") + "," + ghName;
				}
			}else {
				msg = data.getBookingType() == null ? "0" : (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0") + " Rooms/Beds/Dormitory in " + blockList.get(0).get("bName") + "," + ghName;
			}
		}
		if(data.getRoomType().equalsIgnoreCase("RC") || data.getRoomType().equalsIgnoreCase("C")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "P_" + vid.toString();

			jdbcTemplate.update(q2, new Object[] { 
					vehId,
					reqId,
					data.getTravelModeFr(),
					arrtm,
					data.getArrPla(),
					"0",
					data.getFlNoFr(),
					arrDt
			});

			jdbcTemplate.update(q3, new Object[] { 
					vehId,
					reqId,
					data.getTravelModeTo(),
					deptm,
					data.getDepPla(),
					"0",
					data.getFlNoTo(),
					depDt
			});
		}
		String guestName = "Applicant ";
// 	if(!data.getRoomType().equalsIgnoreCase("C")) {
			if(data.getList() != null) {
				for(GuestDetailsPojo item : data.getList()) {
					Integer gid = get_seqID("guest_id");
					String guestId = "P_" + gid.toString();
					update_seqID("guest_id", gid);
					jdbcTemplate.update(q4, new Object[] { 
							reqId,
							guestId,
							item.getGuestName(),
							item.getGuestAge(),
							item.getGuestGender(),
							item.getGuestAddr(),
							item.getGuestDesignation(),
							item.getGuestDepartment()
					});		
				}
				guestName = data.getList().get(0).getGuestName();
			}
//		}else {
//			msg = "only conveynce";
//		}
			if(data.getRequestid()!=null)
			{
				if(reqId!=null)
				{
					jdbcTemplate.update(q5, new Object[] { 
							reqId,
							data.getRequestid()
					});		
				}
			}
		try {
			if(data.getEmailId() != null) {
				if(!data.getEmailId().isEmpty()) {
					mail.sendMimeMail(data.getEmailId(),"Application Details","Dear " + guestName + "<br> You have successfully applied for " + msg + " and your booking id " + reqId + "<br><br> Thanks <br>e-Bhawan Team");//mail is send to the corresponding Email
				}
			}
		} catch (Exception e) {
			logger.error("error occured in sms or email send..");
		}
		
		try {
			if(data.getMob() != null) {
				if(!data.getMob().isEmpty()) {
					String templateID="1107161485663493236";
					smsSend.sendSMS(data.getMob(), "Dear "+guestName+" You have successfully applied for " + msg + " and your Booking ID is " + reqId + ", Odisha Home Dept",templateID);
				}
			}
		} catch (Exception e) {
			logger.error("Error occured in sms " + e.getMessage());
		}
		
		return reqId;
	}

	@Override
	public boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		RoomBookingPojo pd = getBookingDetailsById(data.getBookingId(),userId);
		System.out.println(data);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		boolean status = false;

		Date frmDt = !data.getFrmDt().equals("")?sdf.parse(data.getFrmDt()):null;
		Date toDt =  !data.getToDt().equals("")?sdf.parse(data.getToDt()):null;
		Date arrDt = !data.getArrDt().equals("")?sdf.parse(data.getArrDt()):null;
		Date depDt = !data.getDepDt().equals("")?sdf.parse(data.getDepDt()):null;

		Date arrtm = !data.getArrTm().equals("")?stf.parse(data.getArrDt() + " " + data.getArrTm()):null;
		Date deptm = !data.getDepTm().equals("")?stf.parse(data.getDepDt() + " " + data.getDepTm()):null;

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
			String directory = env.getProperty("media.base.path") + File.separator + 
					env.getProperty("sup.doc.folder") + File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			System.out.println("saved document file path :: "+filepath);
			if(filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			}else {
				try {
					filepath = get_filePath(pd.getBookingId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		else {
			try {
				filepath = get_filePath(pd.getBookingId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String catg_filepath= null;
		if (null != data.getCatgDoc()) {
			String fileSeperator1 = String.valueOf(new Date().getTime()) + "_" + String.valueOf(pd.getBookingId());
			String directory1 = env.getProperty("media.base.path") + File.separator + 
					env.getProperty("sup.doc.folder") + File.separator + fileSeperator1;
			catg_filepath = Utils.saveFileInSysPath(data.getCatgDoc(), directory1);
			System.out.println("saved document category file path :: "+catg_filepath);
			if(catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
				System.out.println(catg_filepath);
			}
			else {
				try {
					catg_filepath = get_filePath1(pd.getBookingId());
					System.out.println(catg_filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		else {
			try {
				catg_filepath = get_filePath1(pd.getBookingId());
				System.out.println(catg_filepath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		jdbcTemplate.update(q1, new Object[] { 
				frmDt,
				toDt,
				data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
				data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
				pd.getStatus(),
				!data.getArrDt().equals("")?"1":"0",
				data.getPurp(),
				data.getOffAddr(),
				data.getBookingType() == null ? "0" : (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
				data.getMob(),
				data.getEmailId(),
				!data.getDepDt().equals("")?"1":"0",
				filepath, // file path
				data.getCatg(),
				data.getDesg(),
				data.getRoomType(),
				catg_filepath,
				data.getDuringstay(),
				data.getCatgType(),
				pd.getBookingId()
		});
		if(!pd.getIsArrivalConv() && !data.getArrDt().equals("")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "P_" + vid.toString();
			jdbcTemplate.update(q2, new Object[] { 
					vehId,
					pd.getBookingId(),
					data.getTravelModeFr(),
					arrtm,
					data.getArrPla(),
					"0",
					data.getFlNoFr(),
					arrDt
			});
		} else {
			jdbcTemplate.update(q5, new Object[] { 
					data.getTravelModeFr(),
					arrtm,
					data.getArrPla(),
					data.getFlNoFr(),
					arrDt,
					pd.getBookingId(),
					pd.getdVchId()
			});
		}
		if(!pd.getIsDepartueConv() && !data.getDepDt().equals("")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "P_" + vid.toString();
			jdbcTemplate.update(q3, new Object[] { 
					vehId,
					pd.getBookingId(),
					data.getTravelModeTo(),
					deptm,
					data.getDepPla(),
					"0",
					data.getFlNoTo(),
					depDt
			});
		} else {
			jdbcTemplate.update(q6, new Object[] { 
					data.getTravelModeTo(),
					deptm,
					data.getDepPla(),
					data.getFlNoTo(),
					depDt,
					pd.getBookingId(),
					pd.getdVchId()
			});
		}
		if(!data.getRoomType().equalsIgnoreCase("C")) {
			if(data.getList() != null) {
				String id = "";;
				Integer i = 0;
				for(GuestDetailsPojo item : data.getList()) {
					System.out.println(item);
					i++;
					if(item.getGuestId().equals("")) {
						Integer gid = get_seqID("guest_id");
						String guestId = "P_" + gid.toString();
						id = id + "'" + guestId + "'";
						update_seqID("guest_id", gid);
						jdbcTemplate.update(q4, new Object[] { 
								pd.getBookingId(),
								guestId,
								item.getGuestName(),
								item.getGuestAge(),
								item.getGuestGender(),
								item.getGuestAddr(),
								item.getGuestDesignation(),
								item.getGuestDepartment()
						});		
					} else {
						id = id + "'" + item.getGuestId() + "'";
						jdbcTemplate.update(q7, new Object[] { 
								item.getGuestName(),
								item.getGuestAge(),
								item.getGuestGender(),
								item.getGuestAddr(),
								item.getGuestDesignation(),
								item.getGuestDepartment(),
								pd.getBookingId(),
								item.getGuestId(),
						});
					}
					if(i < data.getList().size()) {
						id = id + ",";
					}
				}
				if(!id.isEmpty()) {
					q8 = q8.concat(id + ")");
				}
				jdbcTemplate.update(q8, new Object[] {pd.getBookingId()});	
			}
		}
		status = true;
		return status;
	}

	@Override
	public RoomBookingPojo getBookingDetailsById(String bookingId,String Userid) {
		String query = approverQueries.getProperty("getBookingDtlsByIdSQL");
		String gquery = approverQueries.getProperty("getGuestDtlsByBookIdSQL");
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
		List<RoomBookingPojo> list = jdbcTemplate.query(query, new RowMapper<RoomBookingPojo>() {
			@Override
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException{
				RoomBookingPojo res = new RoomBookingPojo();

				String fromDt = rs.getDate("from_date")!=null? sdf.format(rs.getDate("from_date")) :null;
				String toDt = rs.getDate("to_date")!=null? sdf.format(rs.getDate("to_date")) :null;
				String arrDt = rs.getDate("arrival_date")!=null? sdf.format(rs.getDate("arrival_date")) :null;
				String depDt = rs.getDate("depr_date")!=null? sdf.format(rs.getDate("depr_date")) :null;

				String arrTm = rs.getTime("arrival_time")!=null? stf.format(rs.getTime("arrival_time")) :null;
				String depTm = rs.getTime("depr_time")!=null? stf.format(rs.getTime("depr_time")) :null;

				res.setCityId(rs.getString("gh_id"));
				res.setCityName(rs.getString("gh_name"));
				res.setBlockId(rs.getString("block_id"));
				res.setBlockName(rs.getString("block_name"));
				res.setBookingId(rs.getString("req_id"));
				res.setRoomType(rs.getString("roomtype"));

				if((rs.getString("nor_req")!=null?!rs.getString("nor_req").equals("0"):false)||(rs.getString("nob_g_req")!=null?!rs.getString("nob_g_req").equals("0"):false)) {
					res.setBookingType("room");
				}
				if(rs.getString("nod_g_req")!=null?!rs.getString("nod_g_req").equals("0"):false) {
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
				if(rs.getString("status") != null){
					if(rs.getString("status").equalsIgnoreCase("REQ")){
						res.setStatusDesc("Not taken up for processing");
					}
					if(rs.getString("status").equalsIgnoreCase("CAN")){
						res.setStatusDesc("Cancelled");
					}
					if(rs.getString("status").equalsIgnoreCase("MOD")){
						res.setStatusDesc("Under processing");
					}
					if(rs.getString("status").equalsIgnoreCase("PRO")){
						res.setStatusDesc("Under processing");
					}
					if(rs.getString("status").equalsIgnoreCase("ALO")){
						res.setStatusDesc("Alloted");
					}
					if(rs.getString("status").equalsIgnoreCase("CIN")){
						res.setStatusDesc("Check In");
					}
					if(rs.getString("status").equalsIgnoreCase("COUT")){
						res.setStatusDesc("Check Out");
					}
				}
				String modeOfReq = null;
				if(rs.getString("mode_of_req")!=null) {
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
				return res;
			}
		},bookingId,Userid);
		List<GuestDetailsPojo> guestList = jdbcTemplate.query(gquery, new RowMapper<GuestDetailsPojo>() {
			@Override
			public GuestDetailsPojo mapRow(ResultSet rs, int rownumber) throws SQLException{
				GuestDetailsPojo res = new GuestDetailsPojo();
				res.setGuestId(rs.getString("guest_id"));
				res.setGuestName(rs.getString("name"));
				res.setGuestAge(rs.getString("age"));
				res.setGuestGender(rs.getString("gender"));
				res.setGuestAddr(rs.getString("address"));
				res.setGuestDesignation(rs.getString("designation"));
				res.setGuestDepartment(rs.getString("department"));
				return res;
			}
		}, bookingId,Userid);
		if(list.size()==0) {
			return null;
		}
		list.get(0).setList(guestList);
		System.out.println(list);
		return list.get(0);
	}
	@Override
	public List<CommonComboBoxResponseModel> getFlightTrainList(String id, String type) {
		String query = approverQueries.getProperty("getConveynceMode");
		List<CommonComboBoxResponseModel> catgList = new ArrayList<>();
		String ghId = getGhId(id);
		catgList = jdbcTemplate.query(query, new RowMapper<CommonComboBoxResponseModel>() {
			@Override
			public CommonComboBoxResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
				res.setId(String.valueOf(rs.getInt("id")));
				res.setName(rs.getString("conveynce_desc"));
				return res;
			}
		},ghId,type);
		return catgList;
	}
	
	private String getGhId(String blockId) {
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		String ghId = null;
		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException{
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId",rs.getString("gh_id"));
				return res;
			}
		}, blockId);
		if(blockList!=null || blockList.size()!=0) {
			ghId = blockList.get(0).get("ghId");
			System.out.println(ghId);
			System.out.println(blockList);
		}
		return ghId;
	}
	
	private String getCityName(String blockId) {
		String query = "select * from gh_details where gh_id = ?";
		String ghId = null;
		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException{
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId",rs.getString("gh_name"));
				return res;
			}
		}, blockId);
		if(blockList!=null||blockList.size()!=0) {
			ghId = blockList.get(0).get("ghId");
		}
		return ghId;
	}

	@Override
	public String getGuestDesignationDetails(String userId, String catId) {
		String query = approverQueries.getProperty("getAllDesgListByIdSQL");
		List<String> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rownumber) throws SQLException{
				String tmp = null;
				tmp = rs.getString("desg_category");
				return tmp;
			}
		},catId);
		return catgList == null ? null : (catgList.size() != 0 ? catgList.get(0) : null );
	}

	@Override
	public List<CommonComboBoxResponseModel> getDesgList(String userId) {
		String query = "select * from visitor_category order by priority asc";
		List<CommonComboBoxResponseModel> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<CommonComboBoxResponseModel>() {
			@Override
			public CommonComboBoxResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				CommonComboBoxResponseModel res = new CommonComboBoxResponseModel();
				res.setId(rs.getString("vc_id"));
				res.setName(rs.getString("desg_category"));
				res.setDesc(rs.getString("category"));
				return res;
			}
		});
		return catgList;
	}

	@Override
	public PaymentReceiptModel getPaymentReceipt(String reqId, String userId) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String query = approverQueries.getProperty("getPaymentReceiptMode");
		PaymentReceiptModel response = new PaymentReceiptModel();
		List<PaymentReceiptModel> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<PaymentReceiptModel>() {
			@Override
			public PaymentReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				PaymentReceiptModel tmp = new PaymentReceiptModel();
				tmp.setAmount(rs.getString("amount"));
				tmp.setGuestId(rs.getString("guest_id"));
				tmp.setGuestName(rs.getString("name"));
				tmp.setMode(rs.getString("mode"));
				tmp.setPartial(rs.getString("full_partial"));
				tmp.setReceiptId(rs.getString("rec_id"));
				tmp.setRecDate(rs.getDate("date_rece") == null ? null : sdf.format(rs.getDate("date_rece")));
				return tmp;
			}
		},reqId);
		if(catgList != null) {
			if(catgList.size() != 0) {
				Integer sum = 0;
				String receiptId = "";
				Set<String> set = new HashSet<>();
				String guestName = "";
				Integer count = 0;
				for(PaymentReceiptModel item : catgList) {
					count++;
					if(item.getAmount() != null) {
						sum = sum + Integer.parseInt(item.getAmount());
					}
					receiptId = receiptId + item.getReceiptId();
					if(count < catgList.size()) {
						receiptId = receiptId + ",";
					}
					set.add(item.getGuestName());
				}
				for(String va : set) {
					guestName = guestName + va;
					if(count < catgList.size()) {
						guestName = guestName + ",";
					}
				}
				response.setRecDate(catgList.get(0).getRecDate());
				response.setPartial(catgList.get(0).getPartial());
				response.setGuestId(catgList.get(0).getGuestId());
				response.setAmount(String.valueOf(sum));
				response.setGuestName(guestName);
				response.setMode(catgList.get(0).getMode());
				response.setReceiptId(receiptId);
			}
		}
		return response;
	}

	@Override
	public List<AllLocationModel> getAllLocation() {
		String sql = approverQueries.getProperty("getAllLocation");
		List<AllLocationModel> users = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				AllLocationModel res = new AllLocationModel();
				res.setCityId(rs.getString("gh_id"));
				res.setCityName(rs.getString("gh_name"));
				res.setGhId(rs.getString("block_id"));
				res.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				return res;
			}
		});

		return users;
	}

	@Override
	public List<AllLocationModel> getAllProcess(final AllLocationModel request) {
		final SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		String sql = approverQueries.getProperty("getAllProcess");
		List<AllLocationModel> users = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				AllLocationModel res = new AllLocationModel();
				res.setUserId(rs.getString("process_id"));
				res.setCityId(rs.getString("gh_id"));
				res.setGhId(rs.getString("block_id"));
				res.setGhName(request.getGhName());
				res.setCityName(stf.format(rs.getTimestamp("created_date")));
				return res;
			}
		},request.getCityId(),request.getGhId());

		return users;
	}

	@Override
	public AllLocationModel getAllProcessById(String processId) {
		final SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		String sql = approverQueries.getProperty("getAllProcessById");
		AllLocationModel response = new AllLocationModel();
		List<AllLocationModel> users = jdbcTemplate.query(sql, new RowMapper<AllLocationModel>() {
			@Override
			public AllLocationModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				AllLocationModel res = new AllLocationModel();
				res.setUserId(rs.getString("go_sign"));
				res.setCityId(rs.getString("gh_id"));
				res.setGhId(rs.getString("block_id"));
				res.setGhName(rs.getString("block_name"));
				res.setCityName(rs.getString("gh_name"));
				res.setCreatedDt(stf.format(rs.getTimestamp("created_date")));
				return res;
			}
		},processId);
		
		if(users != null) {
			if(users.size() != 0) {
				response = users.get(0);
			}
		}

		return response;
	}

	@Override
	public List<GuestDetailsResponseForOrder> getRequestByProcessId(String processId) {
		final SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		String sql = approverQueries.getProperty("getAllRequestByProcessId");
		List<GuestDetailsResponseForOrder> users = jdbcTemplate.query(sql, new RowMapper<GuestDetailsResponseForOrder>() {
			@Override
			public GuestDetailsResponseForOrder mapRow(ResultSet rs, int rownumber) throws SQLException {
				GuestDetailsResponseForOrder res = new GuestDetailsResponseForOrder();
				res.setReqId(rs.getString("req_id"));
				res.setGuestName(rs.getString("name"));
				res.setDesg(rs.getString("designation"));
				res.setFrom(stf.format(rs.getTimestamp("from_date")));
				res.setTo(stf.format(rs.getTimestamp("to_date")));
				res.setRent(rs.getString("room_rent"));
				res.setRoomNo(rs.getString("room_no"));
				res.setAddress(rs.getString("address"));
				res.setConveynceFrom(rs.getString("is_veh_req"));
				res.setConveynceTo(rs.getString("is_veh_req1"));
				return res;
			}
		},processId,processId);
		
		return users;
	}
	
	@Override
	public String getConveynceDetails(String req, String tableName) {
		final SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		GuestDetailsResponseForOrder response = new GuestDetailsResponseForOrder();
		String sql = "select * from " + tableName + " where req_id = ?";
		List<GuestDetailsResponseForOrder> users = jdbcTemplate.query(sql, new RowMapper<GuestDetailsResponseForOrder>() {
			@Override
			public GuestDetailsResponseForOrder mapRow(ResultSet rs, int rownumber) throws SQLException {
				GuestDetailsResponseForOrder res = new GuestDetailsResponseForOrder();
				res.setConveynceFrom(rs.getString("ftno") + ", " + stf.format(rs.getTimestamp("arrival_time")) + ", " + rs.getString("arrival_place"));
				return res;
			}
		},req);
		
		if(users != null) {
			if(users.size() != 0) {
				response = users.get(0);
			}
		}
		return response.getConveynceFrom();
	}

	@Override
	public List<BookingResponseModel> getFeedback(String requestId, String mobileNo) {
		String sql = approverQueries.getProperty("getBookingDetailsOfFeedbackSQL");
		List<BookingResponseModel> users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("category_type"));
				booking.setName(rs.getString("name"));
				booking.setMobile(rs.getString("mobile_no"));
				if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					booking.setConveyance("No");
				}
				if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					if(!booking.getConveyance().equalsIgnoreCase("Yes")) {
						booking.setConveyance("No");
					}
				}
				booking.setFromDate(rs.getString("from_date"));
				booking.setToDate(rs.getString("to_date"));
				booking.setGhName(rs.getString("block_id")); 
				booking.setCityName(rs.getString("gh_id"));
				String purpose=rs.getString("purpose");
				System.out.println(purpose);
				String purpose1="";
				if(purpose.equals("1"))
				{
					purpose1="Official";
				}
				if(purpose.equals("2"))
				{
					purpose1="Non-Official";
				}
				if(purpose.equals("3"))
				{
					purpose1="Patient";
				}
				if(purpose.equals("4"))
				{
					purpose1="Student";
				}
				System.out.println(purpose1);
				booking.setPurpose(purpose1);
				String status = rs.getString("status");
				String status_desc = "";
				if(status.equals("REQ")){
					status_desc = " Not taken up for processing";
				}
				if(status.equals("CAN")){
					status_desc = "Cancelled";
				}
				if(status.equalsIgnoreCase("MOD")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("PRO")){
					status_desc = "Under processing";
				}
				if(status.equalsIgnoreCase("ALO")){
					status_desc = "Alloted";
				}
				if(status.equals("CIN")){
					status_desc = "Check In";
				}
				if(status.equals("COUT")){
					status_desc = "Check Out";
				}
				booking.setStatus(status);
				booking.setStatusDesc(status_desc);
				return booking;
			}
		},requestId,mobileNo);
		return users;
		
	}

	@Override
	public boolean saveFeedback(FeedbackRequestModel request) {
		boolean status=false;
		try {
		Integer id = get_seqID("feedback_id");
		update_seqID("feedback_id", id);		
		String sql = approverQueries.getProperty("saveFeedbackDetails");
		List<BookingResponseModel> bookingResponseModels=getFeedback(request.getRequestId(), request.getMobile());
		BookingResponseModel bookingResponseModel=bookingResponseModels.get(0);
		jdbcTemplate.update(sql, new Object[] { 
				id.toString(),
				request.getRequestId(),
				request.getMobile(),
				bookingResponseModel.getCityName(),
				bookingResponseModel.getGhName(),
				Integer.parseInt(request.getCheckincheckout()),
				Integer.parseInt(request.getHousekeeping()),
				Integer.parseInt(request.getFood()),
				Integer.parseInt(request.getClean()),
				Integer.parseInt(request.getConduct()),
				"Opened",
				request.getFeedbacksms(),
				Integer.parseInt(request.getOverall())
		});	
		 status=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		 return status;
	}

	@Override
	public List<BookingResponseModel> getFeedbackDetails(String userId) {
		String sql = approverQueries.getProperty("getdetailsListForUserNotGivenFeedback");
		List<BookingResponseModel> users=new ArrayList<BookingResponseModel>();
		try {
	      users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("quota_desc"));
				booking.setMobile(rs.getString("mobile_no"));
				booking.setName(rs.getString("name"));
				booking.setDesg(rs.getString("designation"));
				if(rs.getString("is_veh_req").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					booking.setConveyance("No");
				}
				if(rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
					booking.setConveyance("Yes");
				}else {
					if(!booking.getConveyance().equalsIgnoreCase("Yes")) {
						booking.setConveyance("No");
					}
				}
				
				booking.setFromDate(rs.getString("from_date"));
				booking.setToDate(rs.getString("to_date"));
				booking.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				String purpose=rs.getString("purpose");
				System.out.println(purpose);
				String purpose1="";
				if(purpose.equals("1"))
				{
					purpose1="Official";
				}
				if(purpose.equals("2"))
				{
					purpose1="Non-Official";
				}
				if(purpose.equals("3"))
				{
					purpose1="Patient";
				}
				if(purpose.equals("4"))
				{
					purpose1="Student";
				}
				System.out.println(purpose1);
				booking.setPurpose(purpose1);
				String status = rs.getString("finalstatus");
				String sdd=rs.getString("status");
				booking.setFeedbackstatus(sdd);
				String feedback=rs.getString("feedback_id");
				if(feedback !=null) {
				booking.setFeedbackId(feedback);
				}else {
					booking.setFeedbackId("NA");
				}
				String status_desc = "";
				if(sdd==null) {
					status_desc = "NA";
				}else if(sdd.equals("Opened")){
					status_desc = "Opened";
				}else{
					status_desc = "Closed";
				}
			
				
				booking.setStatus(status);
				booking.setStatusDesc(status_desc);
				return booking;
			}
		},userId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<FeedbackRequestModel> getFeedbackDetailsofUser(String feedbackId, String requestId)throws Exception {
		String sql = approverQueries.getProperty("getfeedbackListForUserAfterFeedback");	
		List<FeedbackRequestModel> users = jdbcTemplate.query(sql, new RowMapper<FeedbackRequestModel>() {
			@Override
			public FeedbackRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				FeedbackRequestModel booking = new FeedbackRequestModel();
				booking.setFeedbackId(rs.getString("feedback_id"));
				booking.setRequestId(rs.getString("req_id"));
				booking.setMobile(rs.getString("mobile_no"));
				booking.setGh_name(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
				booking.setBlock_name(rs.getString("block_name"));	
				booking.setCheckincheckout(String.valueOf(rs.getInt("checkin_out")));
				//int clean=rs.getInt("cleanliness_hygiene");
				booking.setClean(String.valueOf(rs.getInt("cleanliness_hygiene")));
				//int conduct=rs.getInt("conduct_of_staff");
				booking.setConduct(String.valueOf(rs.getInt("conduct_of_staff")));
				//int food=rs.getInt("quality_food");
				booking.setFood(String.valueOf(rs.getInt("quality_food")));
				//int keep=rs.getInt("keep_room");
				booking.setHousekeeping(String.valueOf(rs.getInt("keep_room")));
				booking.setRepliedOn(rs.getString("repliedd_on"));
				
				booking.setRepliedSms(rs.getString("replied_sms"));
				booking.setFeedbacksms(rs.getString("feedback_sms"));
				booking.setStatus(rs.getString("status"));
				booking.setSubmittedOn(rs.getString("submittedd_on"));
				return booking;
			}
		},feedbackId,requestId);
		return users;
	}

	
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

	//@Override
	//public List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo) {
	//	String q
	//	return requestId;
	//}	
	
	
}
