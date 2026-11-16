  package com.stl.ngms.guest.dao;

import java.io.File;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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

import com.stl.ngms.admin.model.*;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
import com.stl.ngms.guest.model.UserPaymentDetails;
import com.stl.ngms.setting.model.AllLocationModel;
import com.stl.ngms.utils.Utils;
import org.springframework.transaction.annotation.Transactional;


  @Repository("guestDao")
public class GuestDaoImpl implements GuestDao{

	final static Logger logger = Logger.getLogger(GuestDaoImpl.class);

	@Autowired JdbcTemplate jdbcTemplate;

	@Autowired Environment env;
	
	@Autowired MailUtil mail;
		
	@Autowired SendSMS smsSend;

	@Autowired private Properties approverQueries;

	@Autowired private Properties adminQueries;

	@Override
	public List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId) {
	    final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	    String sql = approverQueries.getProperty("getRoomWithConveyance");
	    StringBuilder queryBuilder = new StringBuilder(sql);
	    Map<String, Object> queryParams = new HashMap<>();
	    queryParams.put("userId", userId);
	    // Dynamically build query and populate parameters
	    if (params.get("cityId") != null && !params.get("cityId").toString().isEmpty()) {
	        queryBuilder.append(" and a.gh_id = :cityId");
	        queryParams.put("cityId", params.get("cityId"));
	    }
	    if (params.get("blockId") != null && !params.get("blockId").toString().isEmpty()) {
	        queryBuilder.append(" and a.block_id = :blockId");
	        queryParams.put("blockId", params.get("blockId"));
	    }
	    if (params.get("bookingId") != null && !params.get("bookingId").toString().isEmpty()) {
	        queryBuilder.append(" and a.req_id = :bookingId");
	        queryParams.put("bookingId", params.get("bookingId"));
	    }
	    if (params.get("bookingDate") != null && !params.get("bookingDate").toString().isEmpty()) {
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date bookingDt = sdf.parse(params.get("bookingDate").toString());
	            queryBuilder.append(" and a.req_date::date = CAST(:bookingDate AS DATE)");
	            queryParams.put("bookingDate", sdf1.format(bookingDt));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	    }
	    if (params.get("status") != null && !params.get("status").toString().isEmpty()) {
	        queryBuilder.append(" and a.status = :status");
	        queryParams.put("status", params.get("status"));
	    }

	    queryBuilder.append(") AS f1 WHERE f1.i = 1 ORDER BY req_date::date DESC");
	    NamedParameterJdbcTemplate namedJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
	    List<BookingResponseModel> users = new ArrayList<>();
	    try {
	        users = namedJdbcTemplate.query(queryBuilder.toString(), queryParams, new RowMapper<BookingResponseModel>() {
	            @Override
	            public BookingResponseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	                BookingResponseModel booking = new BookingResponseModel();
	                booking.setBookingId(rs.getString("req_id"));
	                booking.setCategory(rs.getString("quota_desc"));
	                booking.setConveyance(rs.getString("is_veh_req").equalsIgnoreCase("1") ? "Yes" : "No");
	                if (rs.getString("is_veh_req1").equalsIgnoreCase("1")) {
	                    booking.setConveyance("Yes");
	                }
	                if (rs.getString("during_stay_conveancy").equalsIgnoreCase("1")) {
	                    booking.setDuringStay("Yes");
	                } else {
	                    booking.setDuringStay("No");
	                }
	                booking.setFromDate(rs.getString("from_date") == null ? "" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(rs.getTimestamp("from_date")));
	                booking.setToDate(rs.getString("to_date") == null ? "" : new SimpleDateFormat("dd-MM-yyyy HH:mm").format(rs.getTimestamp("to_date")));
	                booking.setBookingDt(rs.getString("req_date") == null ? "" : sdf.format(rs.getDate("req_date")));
	                booking.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
	                booking.setCityName(rs.getString("block_id"));
	                booking.setPurpose(rs.getString("item_title"));
	                booking.setMobile(rs.getString("mobile_no"));
	                booking.setName(rs.getString("name"));
	                booking.setStatus(rs.getString("status"));
	                booking.setStatusDesc(mapStatusToDescription(rs.getString("status")));
	                String status=rs.getString("status");
	                String roomType = rs.getString("roomtype");
					String bookingType = "Room";
					if (roomType.equals("RC"))
						bookingType = "Room With Conveyance";
					else if (roomType.equals("C"))
						bookingType = "Conveyance";
					booking.setBookingType(bookingType);
					if (status.equalsIgnoreCase("PRO")) {
						Integer twentyFourCancel = getUserCancel(booking.getFromDate(), booking.getToDate(), roomType);
						booking.setTwentyFourCancel(twentyFourCancel);
					} else if (status.equals("CIN")||status.equals("PCIN")) {
						String canStatus = rs.getString("can_status");
						if (canStatus != null && canStatus.equals("PCAN")) {
							booking.setPartialCancel(0);
						} else {
							booking.setPartialCancel(getUserPartialCancel(booking.getToDate()));
						}
					} else if (status.equals("BILL") || status.equals("RECT")) {
						String billId = getBillIdByBookingId(booking.getBookingId());
						booking.setBillId(billId);
					}
					String nocStatus = checkForNoc(booking.getBookingId());
					booking.setNoc(nocStatus);
					String verifyStatus = checkForPgForApplyOrNot(booking.getBookingId());
					booking.setPgVerifyStatus(verifyStatus);
					booking.setCanStatus(rs.getString("can_status"));
					booking.setFeedbackId(rs.getString("feedback_id"));
					booking.setDuesStatus(rs.getString("dues_status"));
	                return booking;
	            }
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    System.out.println("Query for booking list: " + queryBuilder.toString());
	    System.out.println(users);
	    return users;
	}

	private String mapStatusToDescription(String status) {
	    switch (status) {
	        case "REQ": return "Not taken up for Processing";
	        case "CAN": case "CCAN": return "Cancelled";
	        case "MOD": case "PALO": return "Under Processing";
	        case "ALO": return "Allotted";
	        case "PRO": return "Approved";
	        case "CIN": return "Check In";
	        case "PCIN": return "Partial Check In";
	        case "COUT": return "Check Out";
	        case "BILL": return "Billing";
	        case "PEXT": return "Applied for Extension";
	        case "EXT": return "Extension";
	        case "RECT": return "Payment Successfully";
	        default: return "Unknown";
	    }
	}


	public String checkForNoc(String bookingId) {
		String sql = approverQueries.getProperty("checkForNoc");
		try {
			String status = jdbcTemplate.queryForObject(sql, new Object[] { bookingId }, String.class);
			if (status == null)
				status = "UNPAID";
			return status;
		} catch (Exception e) {
			return null;
		}
	}
	public String checkForPgForApplyOrNot(String bookingId) {
		String sql = approverQueries.getProperty("checkForPgForApplyOrNot");
		try {
			String status = jdbcTemplate.queryForObject(sql, new Object[] { bookingId }, String.class);
			return status;
		} catch (Exception e) {
			return null;
		}
	}
	  public String getBillIdByBookingId(String bookingId) {
		  String sql = approverQueries.getProperty("getBillIdByBookingId");
		  return jdbcTemplate.queryForObject(sql, new Object[] { bookingId }, String.class);
	  }

	@Override
	public boolean cancelRequest(String reqId, String userId) {
		String sql = approverQueries.getProperty("canRequestForGH");
		jdbcTemplate.update(sql, "CAN",
                "GUEST",
                userId,
                reqId);
		return true;
	}
	@Override
	public boolean cancelRequest1(BookingResponseModel requestPojo, String userId) {
		String sql = approverQueries.getProperty("updateStatusAndCanStatusByReqId");
		String sql1 = approverQueries.getProperty("createNoc");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Integer nocId = null;
		try {
			nocId = get_seqID("noc_id");
			update_seqID("noc_id", nocId);
			jdbcTemplate.update(sql, "RCAN", "RCAN", userId, "GUEST", requestPojo.getBookingId());

			if (requestPojo.getTwentyFourCancel() == 0) {
				jdbcTemplate.update(sql1,
						nocId,
						requestPojo.getBookingId(),
						sdf.parse(requestPojo.getFromDate()),
						sdf.parse(requestPojo.getToDate())
				);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while cancelling request");
		}
		return true;
	}
	@Override
	public BookingResponseModel getRequest(String reqId, String userId) {
		String sql = approverQueries.getProperty("getBookingListByIdSQL");
		BookingResponseModel booking = null;
		List<BookingResponseModel> users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {		
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				String fromDate = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : "";
				String toDate = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : "";
				String bookingDate = rs.getDate("req_date") != null ? sdf1.format(rs.getTimestamp("req_date")) : "";
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("category"));
				if(rs.getString("during_stay_conveancy").equalsIgnoreCase("1")) {
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
				booking.setFromDate(fromDate);
				booking.setToDate(toDate);
				booking.setCityName(rs.getString("gh_name"));
				booking.setGhName(rs.getString("block_name"));
				booking.setPurpose(rs.getString("purpose"));
				booking.setBookingDt(bookingDate);
				booking.setNoRooms(rs.getString("nor_req"));
				booking.setNoBeds(rs.getString("nod_g_req"));
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
					status_desc = "Approved";
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
				booking.setName(rs.getString("name"));
				return booking;
			}
		},userId,reqId);
		if(null != users && !users.isEmpty()){
			booking = users.get(0);
		}
		System.out.println("query for getrequest "+sql);
		System.out.println(" reqId "+ reqId);
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
		logger.info(users);
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
				logger.info(purpose);
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
				logger.info(purpose1);
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
					status_desc = "Approved";
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

	public Integer getUserCancel(String fromDate, String toDate, String roomType) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date fromDt = null;
		Date toDt = null;
		Date currDate = new Date();
		try {
			if (fromDate != null && !fromDate.equals("")) {
				fromDt = sdf.parse(fromDate);
			}

			if (toDate != null && !toDate.equals("")) {
				toDt = sdf.parse(toDate);
			}

			if (roomType.equals("C")) {
				if (fromDt != null) {
					if (fromDt.after(currDate)) {
						return 1;
					}
				}
				if (toDt != null) {
					if (toDt.after(currDate)) {
						return 1;
					}
				}
			} else {
				Date oneDayBefore = new Date(toDt.getTime() - 24 * 3600 * 1000);
				if (oneDayBefore.after(currDate)) {
					return 1;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return 0;
	}
	
	public Integer getUserPartialCancel(String toDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date toDt;
		Date currDate = new Date();
		try {
			toDt = sdf.parse(toDate);
			Date oneDayBefore = new Date(toDt.getTime() - 24 * 3600 * 1000);
			if (oneDayBefore.after(currDate)) {
				return 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return 0;
	}

	@Override
	public boolean updateBookingExtension(String userId, ExtensionRequestModel requestPojo) throws Exception {
		
		String reqId = requestPojo.getReqId();
		String bookingId = reqId + "E";
		Boolean status = false;
		logger.info("BookingId " + bookingId);
		requestPojo = getBookingDetails(requestPojo, userId);
		//requestPojo.setReqId(bookingId);

		//Request Details Save
		requestPojo.setFromDate(DateUtils.addHours(requestPojo.getFromDate(), 1));
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
				requestPojo.getDuring_stay_conveancy(),
				requestPojo.getBookingFor(),
				requestPojo.getNoOfDays(),
				requestPojo.getNoOfAdults(),
				requestPojo.getNoOfChildren(),
				requestPojo.getCatgDocType(),
				userId,
				userId
		});
		List<GuestDetailsResponse> list = getGuestDetails(reqId);
		if(list != null) {
			sql = approverQueries.getProperty("saveGuestDetailsSQL");
			for(GuestDetailsResponse item : list) {
				Integer id = get_seqID("guest_id");
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
						item.getDepartment(),
						item.getGuestMobile(),
						item.getGuestDocType(),
						item.getGuestDocPath(),
						item.getGuestId()
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

    @Override
	@Transactional
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
					"GUEST",
					requestPojo.getBookingId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while partial cancel request");
		}
		return true;
    }

	@Override
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

	@Override
	public boolean isBookedSameDate(String userId, RoomBookingPojo data, String type) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date frmDate;
		Date toDate;
		try {
			frmDate = !data.getFrmDt().equals("")?sdf.parse(data.getFrmDt()):null;
			toDate =  !data.getToDt().equals("")?sdf.parse(data.getToDt()):null;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		String sql = approverQueries.getProperty("isBookedSameDate");
		String sql1 = approverQueries.getProperty("isBookedSameDateForUpdate");
		//get count of rooms booked for the same date by the same user
		Integer count = 0;
		try {
			if(type.equalsIgnoreCase("ADD")) {
				count = jdbcTemplate.queryForObject(sql, new Object[] {userId, frmDate, toDate}, Integer.class);
			} else {
				count = jdbcTemplate.queryForObject(sql1, new Object[] {userId, frmDate, toDate, data.getBookingId()}, Integer.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error while checking same date booking");
		}
		System.out.println("count: "+count);
		return count > 0;
	}

	@Override
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
				res.setNoOfAdults(rs.getInt("no_of_adults"));
				res.setNoOfChildren(rs.getInt("no_of_children"));
				res.setBookingFor(rs.getString("booking_for"));
				res.setNoOfDays(noOfDays);
				res.setCatgDocType(rs.getString("catgtype"));
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
				res.setGuestMobile(rs.getString("guest_mobile"));
				res.setGuestDocType(rs.getString("guest_doc_type"));
				res.setGuestDocPath(rs.getString("guest_doc_path"));
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
				res.setName(rs.getString("category"));
				return res;
			}
		});
		return catgList;
	}
	@Override
	public List<CommonComboBoxResponseModel> getPurposeList(String userId, String catgId) {
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
		}, catgId);
		return catgList;
	}

	@Transactional
	@Override
	public String addBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		String ghName = null;
		List<Map<String, String>> blockList = null;
		data.setCreatedBy(userId);
		data.setUpdatedBy(userId);
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId",rs.getString("gh_id"));
				res.put("bId",rs.getString("block_id"));
				res.put("bName",rs.getString("block_name"));
				res.put("sName", rs.getString("short"));
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
		
		logger.info(blockList.get(0).get("ghId"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		logger.info(data.getRequestid());
		String reqId=null;
		if(null==data.getRequestid()){
			Integer id = get_seqID("req_id");
			update_seqID("req_id", id);
			 reqId = "P" + blockList.get(0).get("sName") + id.toString();
		}else{
			Integer id = get_seqID("req_id");
			update_seqID("req_id", id);
			 reqId = "O" + blockList.get(0).get("sName") + id.toString();
		}
		Date frmDt = !data.getFrmDt().equals("")?sdf1.parse(data.getFrmDt()):null;
		Date toDt = !data.getToDt().equals("")?sdf1.parse(data.getToDt()):null;
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
			logger.info("saved document file path :: "+filepath);
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
			logger.info("saved document category file path :: "+catg_filepath);
			if(catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
			}
		}
		String status = null;
//		if(!data.getRoomType().equalsIgnoreCase("C")) {
//			status = "REQ";
//		}else {
//			status = "ALO";
//		}
		if (data.getRoomType().equalsIgnoreCase("C")) {
			frmDt=null;
			toDt=null;
		}
		
		int iddd=jdbcTemplate.update(q1, new Object[] { 
			reqId,
			blockList.get(0).get("ghId"),
			frmDt,
			toDt,
			data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
			data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
			"0",			
			"REQ",
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
			data.getCatgType(),
			data.getNoOfDays(),
			data.getNoOfAdults(),
			data.getNoOfChildren(),
			data.getBookingFor(),
			data.getCreatedBy(),
			data.getUpdatedBy(),
		});
		logger.info(iddd);
		logger.info(data.getBookingType());
		
		String msg = "";
		if(data.getBookingType() != null) {
			if(data.getBookingType().equalsIgnoreCase("room")) {
				if(data.getNoOfRooms() != null) {
					if(!data.getNoOfRooms().isEmpty()) {
						if(!data.getNoOfRooms().equalsIgnoreCase("0")) {
							msg = data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0") + " Rooms/Beds/Dormitory ";
						}
					}
				}else {
					msg = data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0") + " Rooms/Beds/Dormitory";
				}
			}else {
				msg = data.getBookingType() == null ? "0" : (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0") + " Rooms/Beds/Dormitory";
			}
		}				
		if (data.getRoomType().equalsIgnoreCase("RC") || data.getRoomType().equalsIgnoreCase("C")) {
			Integer vid = get_seqID("veh_id");
			update_seqID("veh_id", vid);
			String vehId = "P_" + vid.toString();
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
							item.getGuestDepartment(),
							item.getGuestMobile(),
							item.getGuestDocType(),
							item.getGuestDocPath(),
							"0"
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
		if (!data.getRoomType().equalsIgnoreCase("C")) {
//			try {
//				if(data.getEmailId() != null) {
//					if(!data.getEmailId().isEmpty()) {
//						mail.sendMimeMail(data.getEmailId(),"Application Details","Dear " + guestName + "<br> You have successfully applied for " + msg + " and your booking id " + reqId + "<br><br> Thanks <br>e-Bhawan Team");//mail is send to the corresponding Email
//					}
//				}
//			} catch (Exception e) {
//				logger.error("error occured in sms or email send..");
//			}

			try {
				if(data.getMob() != null) {
					if(!data.getMob().isEmpty()) {
						String templateID="1077054700132341208";
						smsSend.sendSMS(
								data.getMob(),
								"Dear Madam/Sir, you have successfully applied for "
								+ msg + " and your Booking ID is " + reqId + ". For details please login to the portal. Home Dept., Govt. of Odisha ",
								templateID);
					}
				}
			} catch (Exception e) {
				logger.error("Error occured in sms " + e.getMessage());
			}
		}
		return reqId;
	}

	@Transactional
	@Override
	public boolean updateBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		RoomBookingPojo pd = getBookingDetailsById(data.getBookingId(),userId);
		System.out.println("Get Data1 ======== " + pd);
		System.out.println("Get Data2 ======== " + data);
		logger.info(data);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		boolean status = false;

		Date frmDt = !data.getFrmDt().equals("")?sdf1.parse(data.getFrmDt()):null;
		Date toDt =  !data.getToDt().equals("")?sdf1.parse(data.getToDt()):null;
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
			String directory = env.getProperty("media.base.path") + File.separator + 
					env.getProperty("sup.doc.folder") + File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			logger.info("saved document file path :: "+filepath);
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
			logger.info("saved document category file path :: "+catg_filepath);
			if(catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
				logger.info(catg_filepath);
			}
			else {
				try {
					catg_filepath = get_filePath1(pd.getBookingId());
					logger.info(catg_filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		else {
			try {
				catg_filepath = get_filePath1(pd.getBookingId());
				logger.info(catg_filepath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (data.getRoomType().equalsIgnoreCase("C")) {
			frmDt=null;
			toDt=null;
		}
		
		jdbcTemplate.update(q1, new Object[] {
				frmDt,
				toDt,
				data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfRooms() : "0"),
				data.getBookingType() == null ? "0" : (data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBeds() : "0"),
				pd.getStatus(),
				conveyanceFrom,
				data.getPurp(),
				data.getOffAddr(),
				data.getBookingType() == null ? "0" : (!data.getBookingType().equalsIgnoreCase("room") ? data.getNoOfBedsDormitory() : "0"),
				data.getMob(),
				data.getEmailId(),
				conveyanceTo,
				filepath, // file path
				data.getCatg(),
				data.getDesg(),
				data.getRoomType(),
				catg_filepath,
				data.getDuringstay(),
				data.getCatgType(),
				data.getNoOfDays(),
				data.getNoOfAdults(),
				data.getNoOfChildren(),
				data.getBookingFor(),
				pd.getBookingId()
		});		
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
				String vehId = "P_" + vid.toString();
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
				String vehId = "P_" + vid.toString();
				jdbcTemplate.update(q3, new Object[] { vehId, pd.getBookingId(), data.getTravelModeTo(), deptm,
						data.getDepPla(), "0", data.getFlNoTo(), depDt });
			}
		}

		//if(!data.getRoomType().equalsIgnoreCase("C")) {
		if(data.getList() != null) {
			String id = "";;
			Integer i = 0;
			for(GuestDetailsPojo item : data.getList()) {
			logger.info(item);
				i++;
				if(item.getGuestId().equals("")) {
					System.out.println("Inside Guest Iffffffffffffffffffffffffffffffffff");
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
							item.getGuestDepartment(),
							item.getGuestMobile(),
							item.getGuestDocType(),
							item.getGuestDocPath(),
							"0"
					});
				} else {
					System.out.println("Inside Guest ELSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
					id = id + "'" + item.getGuestId() + "'";
					jdbcTemplate.update(q7, new Object[] {
							item.getGuestName(),
							item.getGuestAge(),
							item.getGuestGender(),
							item.getGuestAddr(),
							item.getGuestDesignation(),
							item.getGuestDepartment(),
							item.getGuestMobile(),
							item.getGuestDocType(),
							item.getGuestDocPath(),
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
		//}
		status = true;
		return status;
	}

	@Override
	public RoomBookingPojo getBookingDetailsById(String bookingId,String Userid) {
		String query = approverQueries.getProperty("getBookingDtlsByIdSQL");
		String gquery = approverQueries.getProperty("getGuestDtlsByBookIdSQL");
		String allotQuery = approverQueries.getProperty("getRoomAllotDtlsByBookIdSQL");
//		if (Userid !=null) {
//			query += "and userid='"+Userid.toString()+"'";
//			gquery += "and b.userid='"+Userid.toString()+"'";
//		}
		System.out.println("query"+query);
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		final SimpleDateFormat stf = new SimpleDateFormat("hh:mm a");
		List<RoomBookingPojo> list = jdbcTemplate.query(query, new RowMapper<RoomBookingPojo>() {
			@Override
			public RoomBookingPojo mapRow(ResultSet rs, int rownumber) throws SQLException{
				RoomBookingPojo res = new RoomBookingPojo();

					String fromDt = rs.getDate("from_date")!=null? sdf1.format(rs.getTimestamp("from_date")) :null;
					String toDt = rs.getDate("to_date")!=null? sdf1.format(rs.getTimestamp("to_date")) :null;
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

				/*
				 * if((rs.getString("nor_req")!=null?!rs.getString("nor_req").equals("0"):false)
				 * ||(rs.getString("nob_g_req")!=null?!rs.getString("nob_g_req").equals("0"):
				 * false)) { res.setBookingType("room"); }
				 * if(rs.getString("nod_g_req")!=null?!rs.getString("nod_g_req").equals("0"):
				 * false) { res.setBookingType("dormitory"); }
				 */
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
						res.setStatusDesc("Approved");
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
				res.setNoOfDays(rs.getInt("no_of_days"));
				res.setNoOfAdults(rs.getInt("no_of_adults"));
				res.setNoOfChildren(rs.getInt("no_of_children"));
				res.setBookingFor(rs.getString("booking_for"));
				return res;
			}
		},bookingId);
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
				res.setGuestMobile(rs.getString("guest_mobile"));
				res.setGuestDocType(rs.getString("guest_doc_type"));
				res.setGuestDocPath(rs.getString("guest_doc_path"));
				return res;
			}
		}, bookingId);
		if(list.size()==0) {
			return null;
		}
		RoomBookingPojo roomBookingPojo = list.get(0);
		if(roomBookingPojo.getBookingFor() != null && roomBookingPojo.getBookingFor().equalsIgnoreCase("others")) {
			GuestDetailsPojo guestDetailsPojo = guestList.get(0);
			if(guestDetailsPojo != null) {
				roomBookingPojo.setgMobile(guestDetailsPojo.getGuestMobile());
				roomBookingPojo.setgDocType(guestDetailsPojo.getGuestDocType());
				roomBookingPojo.setgDocName(guestDetailsPojo.getGuestDocPath());
			}
		}
		List<RoomDetails> roomList = new ArrayList<>();
		String status = roomBookingPojo.getStatus();
		if (status.equals("PRO") || status.equals("CIN") || status.equals("COUT") || status.equals("BILL") || status.equals("RECT")) {
			roomList = jdbcTemplate.query(allotQuery, (rs, rownumber) -> {
				RoomDetails res = new RoomDetails();
				res.setFromDate(sdf1.format(rs.getTimestamp("from_date")));
				res.setToDate(sdf1.format(rs.getTimestamp("to_date")));
				res.setRoomNo(rs.getString("room_no"));
				res.setRoomRent(rs.getString("room_rent"));
				res.setFromDate(res.getFromDate() + " - " + res.getToDate());
				return res;
			}, bookingId);
		}
		roomBookingPojo.setList(guestList);
		roomBookingPojo.setRoomDetails(roomList);
		logger.info(list);
		return roomBookingPojo;
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
			logger.info(ghId);
			logger.info(blockList);
		}
		return ghId;
	}
	
	public String getCityName(String blockId) {
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
	
	public List<Map<String, String>> getBlockList(String blockId) {
		String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
		List<Map<String, String>> blockList = null;
		blockList = jdbcTemplate.query(query, new RowMapper<Map<String, String>>() {
			@Override
			public Map<String, String> mapRow(ResultSet rs, int rownumber) throws SQLException {
				Map<String, String> res = new HashMap<String, String>();
				res.put("ghId",rs.getString("gh_id"));
				res.put("bId",rs.getString("block_id"));
				res.put("bName",rs.getString("block_name"));
				return res;
			}
		}, blockId);
		if(blockList==null||blockList.size()==0) {
			return null;
		}
		return blockList;
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
	public List<CommonComboBoxResponseModel> getDesgList(String userId,String viewDesg) {
		String query;
		if(viewDesg.equalsIgnoreCase("ALL")) {
			query = "select * from visitor_category order by priority asc";
		}else {
			query = "select * from visitor_category where category = '"+viewDesg+"' order by priority asc ";
		} 
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
		String sql1 = adminQueries.getProperty("getGuestDetailsForReceipt");
		PaymentReceiptModel response = new PaymentReceiptModel();
		List<PaymentReceiptModel> catgList = new ArrayList<>();
		catgList = jdbcTemplate.query(query, new RowMapper<PaymentReceiptModel>() {
			@Override
			public PaymentReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException{
				PaymentReceiptModel tmp = new PaymentReceiptModel();
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
				}, tmp.getBookingId(), tmp.getBillId());
				tmp.setGuest(names);
				System.out.println(" name "+names);
				String billDt = rs.getDate("date") != null ? sdf.format(rs.getTimestamp("date")) : null;
				tmp.setAmount(rs.getString("total_amount"));
				/*tmp.setGuestId(rs.getString("guest_id"));*/
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
				String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
				tmp.setGuestName(rs.getString("onlyname"));
				tmp.setBillDate(billDt);
				tmp.setBillNo(rs.getString("billno"));
				tmp.setFromDate(frDt);
				tmp.setToDate(toDt);
				//tmp.setMode(rs.getString("mode"));
				tmp.setCityName(rs.getString("gh_name"));
				tmp.setGuestHouse(rs.getString("block_name"));
				tmp.setPartial(rs.getString("full_partial"));
				tmp.setReceiptId(rs.getString("rec_id"));
				tmp.setRecDate(rs.getDate("date_rece") == null ? null : sdf.format(rs.getDate("date_rece")));
				tmp.setTotal(rs.getString("total_amount"));
				tmp.setPurpose(rs.getString("item_title"));
				tmp.setGuestAddress(rs.getString("address"));
				tmp.setGuestEmail(rs.getString("email_id"));
				tmp.setNoOfGuest(rs.getString("no_of_guest"));
				tmp.setGuestContact(rs.getString("guest_mobile"));
				tmp.setGuestHouseAddress(rs.getString("gh_address"));
				tmp.setGuestHouseContactNum(rs.getString("contact_num"));
				tmp.setGuestHouseMail(rs.getString("gh_mail"));
				tmp.setCategory(rs.getString("category"));
				tmp.setRoomNo(rs.getString("room_no"));
				tmp.setTotalAmount(rs.getString("total"));
				tmp.setAdvancedAmount(rs.getString("advance"));
				tmp.setAmountPaid(rs.getString("amount"));
				tmp.setBookingId(rs.getString("req_id"));
				tmp.setDuesAmount(rs.getString("dues_amount"));
				return tmp;
			}
		},reqId);
		catgList.forEach(item -> {
			List<ReceiptModel> paymentMode;
			try {
				paymentMode = getPaymentMode(item.getReceiptId());
				System.out.println("paymentMode  "+paymentMode);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			item.setReceiptModel(paymentMode);
		});
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
				count=0;
				for(String va : set) {
					count++;
					guestName = guestName + va;
					if(count < catgList.size()) {
						guestName = guestName + ",";
					}
				}
				response.setRecDate(catgList.get(0).getRecDate());
				response.setPartial(catgList.get(0).getPartial());
				/*response.setGuestId(catgList.get(0).getGuestId());*/
				response.setAmount(String.valueOf(sum));
				response.setGuestName(guestName);
				response.setGuest(catgList.get(0).getGuest());
				response.setReceiptId(receiptId);
				response.setBillDate(catgList.get(0).getBillDate());
				response.setBillNo(catgList.get(0).getBillNo());
				response.setReceiptModel(catgList.get(0).getReceiptModel());
				response.setCityName(catgList.get(0).getCityName());
				response.setGuestHouse(catgList.get(0).getGuestHouse());
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
				response.setDuesAmount(catgList.get(0).getDuesAmount());
			}
		}
		System.out.println(" payment receipt query  "+query+"  req_id"+reqId);
		System.out.println(" guest  receipt query  "+sql1+"  req_id"+reqId);
		System.out.println(" response "+response);
		return response;
	}
	
	private List<ReceiptModel> getPaymentMode(String receiptId) {
		String query = "SELECT mode_of_payment,amount FROM public.receipt_payment_mode where rec_id='"+receiptId+"'";
		List<ReceiptModel> modesOfPay = jdbcTemplate.query(query, new RowMapper<ReceiptModel>() {
			@Override
			public ReceiptModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				ReceiptModel rectMod = new ReceiptModel();
				rectMod.setModeOfPayment (rs.getString("mode_of_payment"));
				rectMod.setAmounts(rs.getInt("amount"));
				return rectMod;
			}
		});
		System.out.println("modeofpay"+query);
		System.out.println("modeofpay"+modesOfPay);
		return modesOfPay;
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
				booking.setDesg(rs.getString("desg_category"));
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
				logger.info(purpose);
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
				logger.info(purpose1);
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
					status_desc = "Approved";
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
		System.out.println(" data "+requestId+"  "+mobileNo);
		System.out.println("get feedback details for feedback   "+sql);
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
	public BookingResponseModel getFeedbackDetails(String userId,String Id) {
		String sql = approverQueries.getProperty("getdetailsListForUserNotGivenFeedback");
		List<BookingResponseModel> users=new ArrayList<BookingResponseModel>();
		BookingResponseModel user=new BookingResponseModel();
		try {
	      users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				BookingResponseModel booking = new BookingResponseModel();
				booking.setBookingId(rs.getString("req_id"));
				booking.setCategory(rs.getString("quota_desc"));
				booking.setMobile(rs.getString("mobile_no"));
				booking.setName(rs.getString("name"));
				booking.setDesg(rs.getString("desg_category"));
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
				logger.info(purpose);
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
				logger.info(purpose1);
				booking.setPurpose(purpose1);
//				String status = rs.getString("finalstatus");
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
			
				
				//booking.setStatus(status);
				booking.setStatusDesc(status_desc);
				return booking;
			}
		},Id,userId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(users != null) {
			if(users.size() != 0) {
				user = users.get(0);
			}
		}
		return user;
	}

	@Override
	public List<FeedbackRequestModel> getFeedbackDetailsofUser(String feedbackId, String requestId)throws Exception {
		String sql = approverQueries.getProperty("getfeedbackListForUserAfterFeedback");	
		List<FeedbackRequestModel> users = jdbcTemplate.query(sql, new RowMapper<FeedbackRequestModel>() {
			@Override
			public FeedbackRequestModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				FeedbackRequestModel booking = new FeedbackRequestModel();
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String subDate=  rs.getDate("replied_on") != null ? sdf1.format(rs.getTimestamp("replied_on")) : "";;
				String rplyDate =  rs.getDate("submitted_on") != null ? sdf1.format(rs.getTimestamp("submitted_on")) : "";;
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
				booking.setOverall(String.valueOf(rs.getInt("overall")));
				booking.setRepliedOn(subDate);				
				booking.setRepliedSms(rs.getString("replied_sms"));
				booking.setFeedbacksms(rs.getString("feedback_sms"));
				booking.setStatus(rs.getString("status"));
				booking.setSubmittedOn(rplyDate);
				booking.setRepliedByRole(rs.getString("role_name"));
				booking.setRepliedByUserId(rs.getString("replied_by_user_id"));
				return booking;
			}
		},feedbackId,requestId);
		System.out.println("feedback replied details  "+sql);
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

	  /* ----------------- Mohit Starts ------------------ */

	  @Override
	  public BillRespModel showBillDetails(String bookingId, String billId) {
		  String guestQuery = adminQueries.getProperty("getGuestDetailsForShowBill");
		  String billMaster = adminQueries.getProperty("getBillMasterById");
		  String charges = adminQueries.getProperty("getChargesById");
		  String chargeTitle = adminQueries.getProperty("getChargeTitleById");
		  BillRespModel billRespModel;

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
				  brm.setCategory(rs.getString("desg_category"));
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
				  brm.setGhName(rs.getString("block_name"));
				  brm.setCityName(rs.getString("gh_name"));
				  brm.setDesg(rs.getString("designation"));
				  brm.setDepartment(rs.getString("department"));
				  brm.setCityName(rs.getString("gh_name"));
				  brm.setGhName(rs.getString("block_name"));
				  brm.setCatgId(rs.getString("category"));
				  brm.setCount(rs.getInt("extra_guest"));
				  return brm;
			  }, bookingId);
			  br.setGuestDetails(booking);

			  List<CommonComboBoxResponseModel> chargesList = jdbcTemplate.query(charges, new Object[] { billId }, (rs, rowNum) -> {
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
      public ReceiptModel getNoc(String bookingId) {
		  String sql = approverQueries.getProperty("getNocForGuest");
		  String sql1 = adminQueries.getProperty("getModeOfPayment");
		  System.out.println("sql  "+sql);
		  System.out.println("sql1  "+sql1);
		  try {
			  return jdbcTemplate.queryForObject(sql,  (rs, rowNum) -> {
				  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				  String fromDate = rs.getDate("from_date") != null ? sdf.format(rs.getTimestamp("from_date")) : "";
				  String toDate = rs.getDate("to_date") != null ? sdf.format(rs.getTimestamp("to_date")) : "";
				  String payDate = rs.getDate("date_of_pay") != null ? sdf.format(rs.getTimestamp("date_of_pay")) : "";
				  ReceiptModel rm = new ReceiptModel();
				  rm.setMobileNo(rs.getString("userid"));
				  rm.setBookingId(rs.getString("req_id"));
				  rm.setName(rs.getString("name"));
				  rm.setTotal(rs.getString("noc_charge"));
				  rm.setFromDate(fromDate);
				  rm.setToDate(toDate);
				  rm.setCityId(rs.getString("gh_id"));
				  rm.setGhId(rs.getString("block_id"));
				  rm.setGuestHouseName(rs.getString("block_name"));
				  rm.setCityName(rs.getString("gh_name"));
				  rm.setBillId(rs.getString("noc_id"));
				  rm.setStatus(rs.getString("status"));
				  rm.setRecId(rs.getString("rec_id"));
				  rm.setPaymentModes(jdbcTemplate.queryForList(sql1, rm.getRecId()));
				  rm.setPaidAmount(rs.getString("pay_amount"));
				  String rectDate = rs.getDate("date_of_pay") != null ? sdf.format(rs.getTimestamp("date_of_pay")) : "";
				  rm.setRectDate(rectDate);
				  rm.setDate(payDate);
				  rm.setGuestHouseAddress(rs.getString("gh_address"));
				  rm.setGuestHouseContactNum(rs.getString("contact_num"));
				  rm.setGuestHouseMail(rs.getString("gh_mail"));
				  rm.setCategory(rs.getString("category"));
				  rm.setPurpose(rs.getString("item_title"));
				  rm.setGuestEmail(rs.getString("email_id"));
				  rm.setBookingId(rs.getString("req_id"));
				  rm.setNoOfGuest(rs.getString("no_of_guest"));
				  rm.setRoomNo(rs.getString("room_no"));
				  rm.setGuestAddress(rs.getString("address"));
				  return rm;
			  }, bookingId);
		  } catch (Exception e) {
			  e.printStackTrace();
			  return null;
		  }
      }

	  @Override
	  public void updateNocReceipt(String amount, String nocId, String orderId) {
		  String sql = approverQueries.getProperty("updateNocReceipt");
		  String sql1 = adminQueries.getProperty("createReceiptPaymentMode");

		  try {
			  Integer nocIntId = Integer.parseInt(nocId);
			  Double finalAmount = Double.parseDouble(amount);
			  Integer id = get_seqID("rec_id");
			  update_seqID("rec_id", id);

			  jdbcTemplate.update(sql, "PAID", id, finalAmount, nocIntId);

			  Integer rpmId = get_seqID("rpm_id");
			  update_seqID("rpm_id", rpmId);

			  jdbcTemplate.update(sql1, rpmId, id, "online", finalAmount, "", orderId, "");

			  String query = approverQueries.getProperty("getNocDetailsForMessage");
			  BookingResponseModel booking = jdbcTemplate.queryForObject(query, new Object[] { nocIntId }, (rs, rowNum) -> {
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
			  String message = "Dear Madam/Sir, amount of Rs. "
						+ booking.getAdvanceAmount()
						+ " has been successfully received towards your NOC for the previous booking from "
						+ booking.getFromDate() + " to " + booking.getToDate() + " having Booking ID" + booking.getBookingId()
						+ ". For details please login to the portal. Home Dept., Govt. of Odisha";
			  if (booking.getMobile() != null && !booking.getMobile().isEmpty()) {
				  smsSend.sendSMS(booking.getMobile(), message, "1077246730133435176");
			  }
			  if (booking.getEmail() != null && !booking.getEmail().isEmpty()) {
				  //mail.sendMimeMail(booking.getEmail(), "NOC Payment Successful", message);
			  }
		  } catch (Exception e) {
			  throw new RuntimeException(e);
		  }
	  }

      /* ----------------- Mohit Ends ------------------ */

	  /* ----------------- Prasad Starts ------------------ */
	@Override
	public boolean pendingNocCheck(String userId) {
		String sql = approverQueries.getProperty("checkPendingNocSQL");
		Integer count = 0;
		try {
			count = jdbcTemplate.queryForObject(sql, new Object[] { userId }, Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return count>0;
	}
	  /* ----------------- Prasad Ends ------------------ */

	  /* ----------------- Sandip Starts ------------------ */
	  @Override
		public Map<String, Object> getConfrenceHallDetails(Map<String, Object> params) {
		  String blockId=(String) params.get("gId");
			String sql = adminQueries.getProperty("getConfrenceRoomDetailsGuestSide");
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
			String blockId=(String) params.get("gId");
			String conCatId=(String) params.get("conCatId");
			String sql = adminQueries.getProperty("getConfrenceUseDetailsGuestSide");
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
				},conCatId,blockId);
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
		@Transactional
		public Map<String, Object> saveConfrenceHallDetails(ConfrenceHallBooking data,String UserId) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String sql = adminQueries.getProperty("saveConfernceBookingDetails");
			String query = approverQueries.getProperty("getBlockDetailsByBlockIdSQL");
			String query1 = adminQueries.getProperty("saveConfernceRequest");
			String query2 = adminQueries.getProperty("saveConfrenceBill");
			String query3 = adminQueries.getProperty("getGhId");
			String query4 = adminQueries.getProperty("getAmountAndCapacity");
			Integer id = null;
			Integer confId = null;
			Integer confBillId = null;
			Date arrivalDate=null;
			Date departureDate=null;


			Map<String, Object> resp = new HashMap<String, Object>();
			resp.put("status", false);
			try {
				departureDate = !data.getToDate().equals("") ? sdf1.parse(data.getToDate())
						: null;
			} catch (ParseException e1) {
				e1.printStackTrace();
			}

			try {
				arrivalDate = !data.getFromDate().equals("") ? sdf1.parse(data.getFromDate())
						: null;
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String ghId="";
			try {
				ghId= jdbcTemplate.queryForObject(query3, new Object[] { data.getBlockId() }, String.class);
			}catch(Exception e) {
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

			String reqId="";
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
			reqId = "P" + blockList.get(0).get("sName") + id.toString();
			
			
			try {
				jdbcTemplate.query(query4, new RowMapper<ConfrenceHallBooking>() {
					@Override
					public ConfrenceHallBooking mapRow(ResultSet rs, int rownumber) throws SQLException {
						data.setAmount(rs.getString("charge"));
						data.setAdvanceAmount(rs.getString("charge"));
						data.setCapacity(rs.getString("capacity"));
						return data;
					}
				}, data.getConfCatId(), data.getConfUseId(), data.getBlockId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				insert = jdbcTemplate.update(sql,
						new Object[] { reqId, ghId, arrivalDate, departureDate, "REQ", "PUB", data.getAddress(), "CON",
								data.getBlockId(), data.getCategoryType(), data.getMobileNo(), UserId, "0", "0", "0" });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			if (insert.equals(1)) {
				try {
					status = jdbcTemplate.update(query1,
							new Object[] { confId, data.getConfCatId(), arrivalDate, departureDate, data.getConfUseId(),
									"1", data.getName(), data.getAddress(), ghId, data.getBlockId(), data.getCapacity(),
									reqId });
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Not Working");

				}
			}

			try {
				status = jdbcTemplate.update(query2, new Object[] { confBillId, reqId, data.getAmount(), "NB", ghId,
						"0", data.getAmount(), data.getModeOfPayment() });
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
				resp.put("billId", confBillId);
				resp.put("reqId", reqId);
				resp.put("status", true);
				resp.put("amount", data.getAmount());
				resp.put("msg", "Your Conference Hall Booking accepted Your Booking Id :"+reqId);

			return resp;
		}
		@Override
		public ConfrenceHallBooking getGuestPaymentDetails(String reqId) {
			String sql = adminQueries.getProperty("guestPaymentDetails");
			ConfrenceHallBooking gdp=new ConfrenceHallBooking();
		try {
			 gdp = jdbcTemplate.queryForObject(sql, (rs, rn) -> {
				ConfrenceHallBooking g = new ConfrenceHallBooking();
				g.setName(rs.getString("name"));
				g.setAddress(rs.getString("address"));
				g.setReqId(rs.getString("req_id"));
				g.setAdvanceAmount(rs.getString("advance_amount"));
				g.setCapacity(rs.getString("capacity"));
				g.setAmount(rs.getString("charge"));
				g.setMobileNo(rs.getString("mobile_no"));
				g.setConfCatName(rs.getString("conf_cat_desc"));
				g.setConfUseName(rs.getString("conf_use_desc"));
				g.setBillId(rs.getInt("conf_bill_id"));
				return g;
			},reqId);
		}catch(Exception e) {
			e.printStackTrace();
		}

			return gdp;
		}
		@Override
		@Transactional
		public Map<String, Object> saveRecipetDetails(Map<String, Object> params) {
			String query2 = adminQueries.getProperty("saveInConfReciept");
			String query3 = adminQueries.getProperty("updateConfrenceBillStatus");
			String sql1 = adminQueries.getProperty("createReceiptPaymentMode");
			String reqId=(String) params.get("reqId");
			String billId=(String) params.get("billId");
			String amount=(String) params.get("amount");
			String orderId=(String) params.get("orderId");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			 Map<String, Object> result=new HashMap<>();
			 Integer id=null;
			 Integer rpmId=null;
			 try {
					rpmId = get_seqID("rpm_id");
					update_seqID("rpm_id", rpmId);
				} catch (Exception e) {
					e.printStackTrace();
					result.put("status", false);
					result.put("msg", "Some thing went Wrong");
					return result;
				}
			 try {
					id = get_seqID("rec_id");
					update_seqID("rec_id", id);
				} catch (Exception e) {
					e.printStackTrace();
				}

			 try {
				 jdbcTemplate.update(query2, id, billId,amount,"A", "", "", reqId);
			 }catch(Exception e) {
				 e.printStackTrace();
				 result.put("status", false);
				result.put("msg", "Payment updated successfully");
			 }

			 try {
					jdbcTemplate.update(query3, new Object[] {"P",billId});
					System.out.println(query3);
				}catch(Exception e) {
					e.printStackTrace();
					result.put("status", false);
					result.put("msg", "something went wrong try after some time");
					return result;
				}
				try {
					jdbcTemplate.update(sql1, rpmId, id, "online", Double.parseDouble(amount), "", orderId, "");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
					String templateId = "1077267440133358546";
					String subject = "Dear Madam/Sir, amount of Rs. " + data.getAdvanceAmount()
							+ " has been successfully received towards your booking on " + data.getConfCatName()
							+ " having Booking ID "
							+ data.getReqId() + ". For details please login to the portal. Home Dept., Govt. of Odisha";
					System.out.println(subject);
					if (data.getMobileNo() != null) {
						if (!data.getMobileNo().isEmpty()) {
							smsSend.sendSMS(data.getMobileNo(), subject, templateId);
						}
					}
					
				} catch (Exception e) {
					logger.error("Error occured in sms " + e.getMessage());
				}
				
				
				
			result.put("reqId", reqId);
			result.put("status", "error");
			result.put("msg", "Payment  accepted Successfully");
			return result;
		}

		@Override
		public Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId) {
		    List<ConfrenceHall> data = new ArrayList<>();
		    String baseQuery = adminQueries.getProperty("getListOfConfrenceRequest");
		    StringBuilder whereCondition = new StringBuilder();
		    Map<String, Object> queryParams = new HashMap<>();

		    String cityId = (String) params.get("cId");
		    String ghId = (String) params.get("gId");
		    String bookingId = (String) params.get("bookingId");
		    String bookingDate = (String) params.get("bookingDate");
		    String mobNo = (String) params.get("mobNo");

		    // Add conditions dynamically
		    if (cityId != null && !cityId.isEmpty()) {
		        whereCondition.append(" AND gh.gh_id = :cityId");
		        queryParams.put("cityId", cityId);
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
		        try {
		            SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MM-yyyy");
		            SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd");
		            String formattedDate = sdfOutput.format(sdfInput.parse(bookingDate));
		            // Adjusting the SQL query for explicit date comparison
		            whereCondition.append(" AND gh.req_date::date = CAST(:bookingDate AS DATE)");
		            queryParams.put("bookingDate", formattedDate); // Add the formatted date to the parameters
		        } catch (ParseException e) {
		            throw new RuntimeException("Invalid date format", e);
		        }
		    }

		    if (mobNo != null && !mobNo.isEmpty()) {
		        whereCondition.append(" AND gh.mobile_no = :mobNo");
		        queryParams.put("mobNo", mobNo);
		    }

		    // Add the userId condition
		    whereCondition.append(" AND gh.userid = :userId");
		    queryParams.put("userId", userId);

		    // Final query
		    String finalQuery = baseQuery + whereCondition + " ORDER BY gh.req_date DESC";

		    // Execute query
		    System.out.println(finalQuery); // Debugging
		    NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(jdbcTemplate);
		    try {
		    data = namedParameterJdbcTemplate.query(finalQuery, queryParams, new RowMapper<ConfrenceHall>() {
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
						int total=Integer.parseInt(rs.getString("charge"));
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
						if (status.equals("REQ")) {
							status_desc = " Not taken up for processing";
						}
						if (status.equals("ALO")) {
							status_desc = " Allotment";
						}
						if (status.equals("PRO")) {
							status_desc = "Approved";
						}
						if (status.equals("CAN")) {
							status_desc = "Cancelled";
						}
						data1.setStatusDesc(status_desc);
						data1.setTypeOfRoom("Confrence Hall/Open Terrace");
					} catch (Exception e) {
						e.printStackTrace();
					}
		            return data1;
		        }
		    });
		    }catch(Exception e) {
		    	e.printStackTrace();
		    }
		    // Response construction
		    Map<String, Object> resp = new HashMap<>();
		    resp.put("status", true);
		    resp.put("data", data);
		    return resp;
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
			 Map<String, Object> result=new HashMap<>();
			Integer resp=null;
			
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
				e.printStackTrace();
				throw new RuntimeException("Not Working");
			}
			result.put("status", true);
			result.put("msg", "Edit conference data \n successful");
			
			
			return result;
		}
		@Override
		public Map<String, Object> cancelConfrenceDetails(String reqId) {
			String query = adminQueries.getProperty("updateCancelgad");
			String query1 = adminQueries.getProperty("updateCancelConfrenceBalance");
			String query2 = adminQueries.getProperty("updateCancelConfrenceRequest");
			 Map<String, Object> result=new HashMap<>();
			 try {
					jdbcTemplate.update(query, new Object[] {reqId});
				}catch(Exception e) {
					e.printStackTrace();
					result.put("status", false);
					result.put("msg", "something went wrong try after some time");
					return result;
				}
				try {
					jdbcTemplate.update(query1, new Object[] {reqId});
				}catch(Exception e) {
					e.printStackTrace();
					result.put("status", false);
					result.put("msg", "something went wrong try after some time");
					return result;
				}
				try {
					jdbcTemplate.update(query2, new Object[] {reqId});
				}catch(Exception e) {
					e.printStackTrace();
					result.put("status", false);
					result.put("msg", "something went wrong try after some time");
					return result;
				}
				
				result.put("status", true);
				result.put("msg", "Cancel Updated Successfully");
				
				
			return result;
		}
		@Override
		public UserPaymentDetails getUserOrderOrBlockId(String reqId) {
			String sql=adminQueries.getProperty("getUserOrderOrBlockId");
			UserPaymentDetails userPaymentDetails=new UserPaymentDetails();
			userPaymentDetails=jdbcTemplate.queryForObject(sql,new Object[]{reqId,"Success","P"},(rs,rowN)->{
				UserPaymentDetails u=new UserPaymentDetails();
				u.setNocCharge(rs.getInt("noc_charge"));
				u.setNocId(rs.getInt("noc_id"));
				u.setOrderId(rs.getString("order_id"));
				u.setBlockId(rs.getString("block_id"));
				u.setReqId(reqId);
				return u;
			});
			return userPaymentDetails;
		}
		@Override
		public boolean checkProfileDocPresentOrNot(String userId) {
			String sql=adminQueries.getProperty("checkProfileDocPresentOrNot");
			boolean status=false;
			try {
				 status=jdbcTemplate.queryForObject(sql,new Object[] {userId},Boolean.class);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return status;
			
		}
	  /* ----------------- Sandip Ends ------------------ */

	  /*prerana guestDaoImpl starts*/
		@Override
		public List<BookingResponseModel> getFeedbackDetailsList(String userId) {
			String sql = approverQueries.getProperty("getdetailsListForUserGivenFeedback");
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
					//String status = rs.getString("finalstatus");
					String sdd=rs.getString("feedback_status");
					System.out.println("sdd--------------"+sdd);
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
				
					
					//booking.setStatus(status);
					booking.setStatusDesc(status_desc);
					return booking;
				}
			},userId);
		      System.out.println();
		      System.out.println("query-----"+sql);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return users;
		}
		
		@Override
		public ReceiptModel getDuesPaymentReceipt(String reqId) {
			
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
			},reqId);
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
					response.setDate(catgList.get(0).getDate());
					response.setBillNo(catgList.get(0).getBillNo());
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
					response.setOnlyName(catgList.get(0).getOnlyName());

				}
			}
			System.out.println("dues report query  "+query+"  req_id"+reqId);
			System.out.println(" response "+response);
			return response;
			/*System.out.println("query for dues report  "+query);
			logger.info(data);
			return data;*/
		}
		/*prerana guestDaoImpl ends*/

	//@Override
	//public List<BookingResponseModel> getFeedbackforQr(String requestId, String mobileNo) {
	//	String q
	//	return requestId;
	//}	
	
	
}
