package com.stl.ngms.mobile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.stl.ngms.admin.dao.AdminDao;
import com.stl.ngms.admin.model.AllotmentRoom;
import com.stl.ngms.admin.model.ConfrenceHall;
import com.stl.ngms.admin.model.ReceiptModel;
import com.stl.ngms.admin.model.ShiftMasterModel;
import com.stl.ngms.admin.model.VehicleAllotedReport;
import com.stl.ngms.common.model.Pagination;
import com.stl.ngms.guest.dao.GuestDao;
import com.stl.ngms.guest.dao.GuestDaoImpl;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.ProcessModeration;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.utils.Utils;

@Service("mobileDao")
public class MobileDaoImpl implements MobileDao {

	final static Logger logger = Logger.getLogger(MobileDaoImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	Environment env;

	@Autowired
	private Properties approverQueries;

	@Autowired
	private Properties allotmentQueries;

	@Autowired
	private Properties adminQueries;

	@Autowired
	GuestDao guestDao;
	
	@Autowired
	AdminDao adminDao;

	@Override
	public Pagination getAllotmentLstDtlsAccToReq(Map<String, Object> params, HttpSession session) {
		List<BookingResponseModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY gh.req_date DESC";
		String query = allotmentQueries.getProperty("getAllotmentLstDtlsSQL");
		String query1 = allotmentQueries.getProperty("getAllotmentLstDtlsSQLCount");
		String whereCondition = " WHERE 1=1";

		String reqType = (String) params.get("reqType");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String bookingDate = (String) params.get("bookingDate");
		String purpose = (String) params.get("purpose");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		// String status = (String) params.get("status");
		String roomType = (String) params.get("roomType");
		String category = (String) params.get("category");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");

		System.out.println("pageSize++++++++++++++" + pageSize);
		System.out.println("pageNum++++++++++++++" + pageNum);

		LocalDate curDt = LocalDate.now();
		LocalDate nextDt = curDt.plusDays(7);

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.gh_id='" + cityId + "'";
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			String cityIdString = String.join("', '", cityIdList);
			whereCondition = whereCondition + " AND gh.gh_id in ('" + cityIdString + "')";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.block_id='" + ghId + "'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.req_id='" + bookingId + "'";
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

		if (category != null ? !category.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.category_type='" + category + "'";
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
		if (reqType.equalsIgnoreCase("AL")) {
//			if (status != null ? !status.isEmpty() : false) {
//				whereCondition = whereCondition + " AND gh.status='" + status + "'";
//			} else {
//				whereCondition = whereCondition + " AND gh.status = 'REQ'";
//			}
			whereCondition = whereCondition + " AND gh.mode_of_req IN ('GAD','PUB') AND gh.status = 'REQ'";
		} else if (reqType.equalsIgnoreCase("MA")) {
			whereCondition = whereCondition
					+ " AND gh.status='REQ' AND gh.mode_of_req IN ('GAD','PUB') AND (case when gh.from_date is not null then gh.from_date<='"
					+ nextDt + "' AND gh.to_date>='" + curDt + "' else 1=1 AND (v1.arrival_time>= '" + curDt
					+ "' OR v2.arrival_time>='" + curDt + "') end)";
			orderByCondition = " ORDER BY gh.req_date ASC";
		} else if (reqType.equalsIgnoreCase("MM")) {
			whereCondition = whereCondition
					+ " AND gh.mode_of_req IN ('GAD','PUB') AND (case when gh.from_date is not null then gh.to_date>='"
					+ curDt + "' AND gh.status = 'MOD' else (v1.arrival_time>='" + curDt + "' OR v2.arrival_time>= '"
					+ curDt + "') AND gh.status = 'ALO' end)";
			orderByCondition = " ORDER BY gh.req_date ASC";
		} else if (reqType.equalsIgnoreCase("GH")) {
			whereCondition = whereCondition
					+ " AND gh.status='REQ' AND gh.mode_of_req = 'GH' AND (case when gh.from_date is not null then gh.to_date>='"
					+ curDt + "' else 1=1 AND (v1.arrival_time>= '" + curDt + "' OR v2.arrival_time>='" + curDt
					+ "') end)";
		}
		String limitCondition = " limit '" + pageSize + "' offset '" + (pageNum - 1) * pageSize + "' ";
		query = query + whereCondition + orderByCondition + limitCondition;
		query1 = query1 + whereCondition;
		logger.info(query);
		data = jdbcTemplate.query(query, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				return setBookingLstFromResultset(rs);
			}
		});
		System.out.println("query for modify list " + query);
		Integer pageCount = null;
		try {
			pageCount = jdbcTemplate.queryForObject(query1, Integer.class);
		} catch (Exception e) {
			pageCount = 0;
			e.printStackTrace();
		}
		Pagination p = new Pagination();
		p.setData(data);
		p.setLength(data.size());
		p.setTotalPages(pageCount,data.size());
		return p;
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
			System.out.println("inside IF roomType" + roomType);
			if(roomType.equalsIgnoreCase("R") || roomType.equalsIgnoreCase("RC")) {
				System.out.println("inside IF roomType" + roomType);
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
//			if(status.equals("PCIN")) {
//				if(cancelledBy != null) {
//					cancelByWho = "Guest(Partial Cancel)";
//				}else {
//					cancelByWho = "Admin(Partial Cancel)";
//				}
//			}
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
//				if(cancelledBy != null) {
//					cancelByWho = "Guest(Partial Cancel)";
//				}else {
//					cancelByWho = "Admin(Partial Cancel)";
//				}
			}
			if (status.equals("PCIN")) {
				status_desc = "Partial CheckIn";
			}
			if (status.equals("COUT")) {
				status_desc = "Check Out";
//				if(cancelledBy != null) {
//					cancelByWho = "Guest(Partial Cancel)";
//				}else {
//					cancelByWho = "Admin(Partial Cancel)";
//				}
			}
			if (status.equals("RCAN")) {
				status_desc = "Cancelled";
			}
			if (status.equals("PEXT")) {
				status_desc = "Request For Extension";
//				if(cancelledBy != null) {
//					cancelByWho = "Guest(Partial Cancel)";
//				}else {
//					cancelByWho = "Admin(Partial Cancel)";
//				}
			}
			if (status.equals("EXT")) {
				status_desc = "Extension";
//				if(cancelledBy != null) {
//					cancelByWho = "Guest(Partial Cancel)";
//				}else {
//					cancelByWho = "Admin(Partial Cancel)";
//				}
			}
			if (status.equals("BILL")) {
				status_desc = "Billing";
//				if(cancelledBy != null) {
//					cancelByWho = "Guest(Partial Cancel)";
//				}else {
//					cancelByWho = "Admin(Partial Cancel)";
//				}
			}
			if (status.equals("RECT")) {
				status_desc = "Payment Successfully";
//				if(cancelledBy != null) {
//					cancelByWho = "Guest(Partial Cancel)";
//				}else {
//					cancelByWho = "Admin(Partial Cancel)";
//				}
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
	public Map<String, Object> getConfrenceRequestDetails(Map<String, Object> params, String userId) {
		List<ConfrenceHall> data = new ArrayList<>();
		String orderByCondition = " ORDER BY gh.req_date DESC";
		String query = adminQueries.getProperty("getListOfConfrenceRequest");
		String whereCondition = "AND 1=1  ";
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String bookingDate = (String) params.get("bookingDate");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		// String status = (String) params.get("status");
		String roomType = (String) params.get("roomType");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		System.out.println("pageSize++++++++++++++" + pageSize);
		System.out.println("pageNum++++++++++++++" + pageNum);
		Map<String, Object> resp = new HashMap<String, Object>();

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.gh_id='" + cityId + "'";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.block_id='" + ghId + "'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.req_id='" + bookingId + "'";
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

		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.mobile_no='" + mobNo + "'";
		}

		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";
		whereCondition = whereCondition + " and gh.userid='" + userId + "'";
		query = query + whereCondition + orderByCondition + limitCondition;
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
		resp.put("status", true);
		resp.put("data", data);
		return resp;
	}

	@Override
	public List<BookingResponseModel> getUserRequestList(Map<String, Object> params, String userId) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String cityId = (String) params.get("cityId");
		String blockId = (String) params.get("blockId");
		String bookingId = (String) params.get("bookingId");
		String bookingDate = (String) params.get("bookingDate");
		String status = (String) params.get("status");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		System.out.println("pageSize++++++++++++++" + pageSize);
		System.out.println("pageNum++++++++++++++" + pageNum);

		String sql = approverQueries.getProperty("getRoomWithConveyance");
		// String orderBy = ")f1 where f1.i=1 order by req_date::date Desc";
		String orderBy = ") AS f1 WHERE f1.i = 1 ORDER BY req_date::date DESC";
		Date bookingDt = null;
		if (cityId != null && !cityId.isEmpty()) {
			sql = sql.concat(" and a.gh_id = '" + cityId + "'");
		}
		if (blockId != null && !blockId.isEmpty()) {
			sql = sql.concat(" and a.block_id = '" + blockId + "'");
		}
		if (bookingId != null && !bookingId.isEmpty()) {
			sql = sql.concat(" and a.req_id = '" + bookingId + "'");
		}
		if (bookingDate != null && !bookingDate.isEmpty()) {
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				bookingDt = sdf.parse(bookingDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql = sql.concat(" and a.req_date::date = '" + sdf1.format(bookingDt) + "'::date");
		}
		if (status != null && !status.isEmpty()) {
			sql = sql.concat(" and a.status = '" + status + "'");
		}

		String limitCondition = " limit '" + pageSize + "' offset '" + (pageNum - 1) * pageSize + "' ";

		sql = sql + orderBy + limitCondition;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		List<BookingResponseModel> users = new ArrayList<>();

		try {
			users = jdbcTemplate.query(sql, new RowMapper<BookingResponseModel>() {
				@Override
				public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
					BookingResponseModel booking = new BookingResponseModel();
					booking.setBookingId(rs.getString("req_id"));
					booking.setCategory(rs.getString("quota_desc"));
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
					String duringStayConveyance = rs.getString("during_stay_conveancy");
					if (duringStayConveyance.equalsIgnoreCase("1")) {
						booking.setDuringStay("Yes");
					} else {
						booking.setDuringStay("No");
					}
					booking.setFromDate(
							rs.getString("from_date") == null ? "" : sdf1.format(rs.getTimestamp("from_date")));
					booking.setToDate(rs.getString("to_date") == null ? "" : sdf1.format(rs.getTimestamp("to_date")));
					booking.setBookingDt(rs.getString("req_date") == null ? "" : sdf.format(rs.getDate("req_date")));
					booking.setGhName(rs.getString("block_name") + "(" + rs.getString("gh_name") + ")");
					booking.setCityName(rs.getString("block_id"));
					booking.setPurpose(rs.getString("item_title"));
					booking.setMobile(rs.getString("mobile_no"));
					booking.setName(rs.getString("name"));
					String status = rs.getString("status");
					String status_desc = "";
					if (status.equals("REQ")) {
						status_desc = "Not taken up for Processing";
					}
					if (status.equals("CAN") || status.equals("CCAN")) {
						status_desc = "Cancelled";
					}
					if (status.equalsIgnoreCase("MOD") || (status.equalsIgnoreCase("PALO"))) {
						status_desc = "Under Processing";
					}
					if (status.equalsIgnoreCase("ALO")) {
						status_desc = "Allotted";
					}
					if (status.equalsIgnoreCase("PRO")) {
						status_desc = "Approved";
					}
					if (status.equals("CIN")) {
						status_desc = "Check In";
					}
					if (status.equals("PCIN")) {
						status_desc = "Partial Check In";
					}
					if (status.equals("COUT")) {
						status_desc = "Check Out";
					}
					if (status.equals("BILL")) {
						status_desc = "Billing";
					}
					if (status.equals("PEXT")) {
						status_desc = "Applied for Extension";
					}
					if (status.equals("EXT")) {
						status_desc = "Extension";
					}
					if (status.equals("RECT")) {
						status_desc = "Payment Successfully";
					}
					booking.setStatus(status);
					booking.setStatusDesc(status_desc);
					String roomType = rs.getString("roomtype");
					String bookingType = "Room";
					if (roomType.equals("RC"))
						bookingType = "Room With Conveyance";
					else if (roomType.equals("C"))
						bookingType = "Conveyance";
					booking.setBookingType(bookingType);
					if (status.equalsIgnoreCase("PRO")) {
						Integer twentyFourCancel = guestDao.getUserCancel(booking.getFromDate(),
								booking.getToDate(), roomType);
						booking.setTwentyFourCancel(twentyFourCancel);
					} else if (status.equals("CIN") || status.equals("PCIN")) {
						String canStatus = rs.getString("can_status");
						if (canStatus != null && canStatus.equals("PCAN")) {
							booking.setPartialCancel(0);
						} else {
							booking.setPartialCancel(guestDao.getUserPartialCancel(booking.getToDate()));
						}
					} else if (status.equals("BILL") || status.equals("RECT")) {
						String billId = guestDao.getBillIdByBookingId(booking.getBookingId());
						booking.setBillId(billId);
					}
					String nocStatus = guestDao.checkForNoc(booking.getBookingId());
					booking.setNoc(nocStatus);
					booking.setCanStatus(rs.getString("can_status"));
					booking.setFeedbackId(rs.getString("feedback_id"));
					booking.setDuesStatus(rs.getString("dues_status"));
					return booking;
				}
			}, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("query for bookinglist" + sql);
		System.out.println(users);
		return users;
	}
	
	@Override
	public List<BookingResponseModel> getConfListForModeration(Map<String, Object> params, HttpSession session) {
		List<BookingResponseModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY gr.req_date DESC";
		String sql = adminQueries.getProperty("getAllConferenceList");
		String whereCondition = "";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String bookingDate = (String) params.get("bookingDate");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		String category = (String) params.get("category");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		if (cityId != null && !cityId.isEmpty()) {
			whereCondition = whereCondition + " AND gr.gh_id='" + cityId + "'";
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			String cityIdString = String.join("', '", cityIdList);
			whereCondition = whereCondition + " AND gr.gh_id in ('" + cityIdString + "')";
		}
		if (ghId != null && !ghId.isEmpty()) {
			whereCondition = whereCondition + " AND gr.block_id='" + ghId + "'";
		}
		if (bookingId != null && !bookingId.isEmpty()) {
			whereCondition = whereCondition + " AND gr.req_id='" + bookingId + "'";
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
			whereCondition = whereCondition + " AND gr.req_date::date='" + bookingDate + "'::date";
		}
		if (category != null && !category.isEmpty()) {
			whereCondition = whereCondition + " AND gr.category_type='" + category + "'";
		}
		if (mobNo != null && !mobNo.isEmpty()) {
			whereCondition = whereCondition + " AND gr.mobile_no='" + mobNo + "'";
		}

		if (name != null && !name.isEmpty()) {
			whereCondition = whereCondition + " AND tr.name ILIKE '%" + name + "%'";
		}
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";
		sql = sql + whereCondition + orderByCondition + limitCondition;
		logger.info(sql);
		try {
			data = jdbcTemplate.query(sql, (rs, rownumber) -> {
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
//			brm.setEmail(rs.getString("email"));
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
			}, "REQ");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Some error occurred while fetching conference list");
		}
		return data;
	}
	
	@Override
	public List<BookingResponseModel> getModListForAllotment(Map<String, Object> params, HttpSession session) {

		List<BookingResponseModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY gh.from_date DESC, gh.req_id";
		String query = allotmentQueries.getProperty("getModListForAllotment");
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
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.gh_id='" + cityId + "'";
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			String cityIdString = String.join("', '", cityIdList);
			whereCondition = whereCondition + " AND gh.gh_id in ('" + cityIdString + "')";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.block_id='" + ghId + "'";
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
				query = allotmentQueries.getProperty("getAllotmentLstForModify");
				whereCondition += " AND gh.status IN ('PRO', 'PCIN', 'CIN') AND current_date::date <= gh.to_date::date" +
						" AND (tr.mod_status IS NULL OR tr.mod_status = '')";
			} else if (type.equalsIgnoreCase("modifyListMOD")) {
				query = allotmentQueries.getProperty("getAllotmentLstForModifyMOD");
				whereCondition += " AND gh.status IN ('PRO', 'PCIN', 'CIN') AND current_date::date <= gh.to_date::date" +
						" AND (tr.mod_status = 'PMODF')";
			} else {
				whereCondition += " AND gh.status IN ('MOD', 'PALO') AND gh.from_date::date >= current_date::date";
			}
		}
		
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";
		query = query + whereCondition + orderByCondition + limitCondition;

		logger.info(query);
		data = jdbcTemplate.query(query, (rs, rownumber) -> {
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
			if (type.equalsIgnoreCase("modifyList")||type.equalsIgnoreCase("modifyListMOD")) {
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
				} else if (status.equals("CIN")) {
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
			if(type.equalsIgnoreCase("modifyListMOD")) {
				bookingResponseModel.setRemarks(rs.getString("subject"));
				bookingResponseModel.setRowNo(rs.getString("prev_order_no"));
			}
			return bookingResponseModel;
		});
		return data;
	}
	
	@Override
	public List<RoomBookingPojo> getReqListByCityAndGH(String city, String gHouse, String bookingIdForSplit, int pageNum, int pageSize) {
		String sql = adminQueries.getProperty("getReqListByCityAndGH");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";
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
		
		System.out.println("whereCondition     "+whereCondition);
		sql = sql + whereCondition + limitCondition;
		logger.info(sql);
		
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
				guestDetailsList = adminDao.getGuestDetails(item.getBookingId());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			item.setList(guestDetailsList);
		});
		return roomBookingPojoList.stream().filter(item -> item.getList().size() > 1).collect(Collectors.toList());
	}
	
	@Override
	public Map<String, Object> getViewVehicleAllotmentReport(Map<String, Object> params) {
		String sql = adminQueries.getProperty("viewVehicleAllotedReport");
		String sql1 = adminQueries.getProperty("getVehicleAllotedReportDeparture");
		String ghId = (String) params.get("cId");
		String blockId = (String) params.get("gId");
		String reqId = (String) params.get("bookingId");
		String name = (String) params.get("name");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";
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
		sql = sql + limitCondition;
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
		sql1 = sql1 + limitCondition;
		System.out.println(sql.toString());
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
	public List<BookingResponseModel> getAllBillingList(Map<String, Object> params, HttpSession session) {
		List<BookingResponseModel> data;
		String orderByCondition = " ORDER BY tc.lastmodified DESC";
		String query = adminQueries.getProperty("getAllBillingList");
		String whereCondition = "";

		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String fromDate = (String) params.get("fromDate");
		String purpose = (String) params.get("purpose");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		String category = (String) params.get("category");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.gh_id='" + cityId + "'";
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			String cityIdString = String.join("', '", cityIdList);
			whereCondition = whereCondition + " AND gr.gh_id in ('" + cityIdString + "')";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.block_id='" + ghId + "'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.req_id='" + bookingId + "'";
		}
		if (category != null ? !category.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.category_type='" + category + "'";
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
			whereCondition = whereCondition + " AND gr.from_date::date='" + fromDate + "'::date";
		}
				
		if (purpose != null ? !purpose.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.purpose='" + purpose + "'";
		}

		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.mobile_no='" + mobNo + "'";
		}

		if (name != null ? !name.isEmpty() : false) {
			whereCondition = whereCondition + " AND gs.name ILIKE '%" + name + "%'";
		}
		query = query + whereCondition + orderByCondition + limitCondition;
		logger.info(query);
		data = jdbcTemplate.query(query, (rs, i) -> {
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String frDt = rs.getDate("from_date") != null ? sdf1.format(rs.getTimestamp("from_date")) : null;
			String toDt = rs.getDate("to_date") != null ? sdf1.format(rs.getTimestamp("to_date")) : null;
			BookingResponseModel brm = new BookingResponseModel();
			brm.setBookingId(rs.getString("req_id"));
			brm.setName(rs.getString("name"));
			brm.setFromDate(frDt);
			brm.setToDate(toDt);
			brm.setNoOfAdults(rs.getString("no_of_adults"));
			brm.setNoOfChildren(rs.getString("no_of_children"));
			brm.setAdvanceAmount(rs.getString("adv_amt"));
			brm.setTotalAmt(rs.getString("total"));
			brm.setName(rs.getString("name"));
			brm.setCategory(rs.getString("desg_category"));
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
			brm.setCatgId(category_Desc);
			brm.setPurpose(rs.getString("item_title"));
			return brm;
		});
		System.out.println("Add bill details "+query);
		System.out.println(" data value "+data);
		return data;
	}
	
	@Override
	public List<BookingResponseModel> getShowBillList(Map<String, Object> params, HttpSession session) {
		List<BookingResponseModel> data;
		String orderByCondition = " ORDER BY bm.date DESC";
		String query = adminQueries.getProperty("getShowBillList");
		String whereCondition = "";
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String fromDate = (String) params.get("fromDate");
		String billdate = (String) params.get("billDate");
		String purpose = (String) params.get("purpose");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		String category = (String) params.get("category");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.gh_id='" + cityId + "'";
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			String cityIdString = String.join("', '", cityIdList);
			whereCondition = whereCondition + " AND gr.gh_id in ('" + cityIdString + "')";
		}
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.block_id='" + ghId + "'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.req_id='" + bookingId + "'";
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
			whereCondition = whereCondition + " AND gr.from_date::date='" + fromDate + "'::date";
		}
		
		if (billdate != null ? !billdate.isEmpty() : false) {
			Date date;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				date = sdf.parse(billdate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			billdate = sdf1.format(date);
			whereCondition = whereCondition + " AND bm.date::date='" + billdate + "'::date";
		}
		if (category != null ? !category.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.category_type='" + category + "'";
		}
		if (purpose != null ? !purpose.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.purpose='" + purpose + "'";
		}

		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.mobile_no='" + mobNo + "'";
		}

		if (name != null ? !name.isEmpty() : false) {
			whereCondition = whereCondition + " AND gs.name ILIKE '%" + name + "%'";
		}
		query = query + whereCondition + orderByCondition + limitCondition;
		logger.info(query);
		data = jdbcTemplate.query(query, (rs, i) -> {
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
			brm.setCatgId(category_Desc);
			brm.setPurpose(rs.getString("item_title"));
			brm.setBillId(rs.getString("bill_id"));
			return brm;
		});
		System.out.println("showbill details  "+query);
		return data;
	}
	
	@Override
	public List<ReceiptModel> getAllReceiptList(Map<String, Object> params, HttpSession session) {
		List<ReceiptModel> data;
		String orderByCondition = " ORDER BY tb.date DESC";
		String query = adminQueries.getProperty("getAllReceiptList");
		String whereCondition = "";
		String type = (String) params.get("type");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String fromDate = (String) params.get("fromDate");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		String category = (String) params.get("category");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";
		System.out.println("type   "+type);
		System.out.println(pageSize+" -pages- "+pageNum);

		if (cityId != null && !cityId.isEmpty()) {
			whereCondition = whereCondition + " AND gr.gh_id='" + cityId + "'";
		} else {
			ArrayList<String> cityIdList = Utils.getCityIdListFromSession(session);
			String cityIdString = String.join("', '", cityIdList);
			whereCondition = whereCondition + " AND gr.gh_id in ('" + cityIdString + "')";
		}
		if (ghId != null && !ghId.isEmpty()) {
			whereCondition = whereCondition + " AND gr.block_id='" + ghId + "'";
		}
		if (bookingId != null && !bookingId.isEmpty()) {
			whereCondition = whereCondition + " AND gr.req_id='" + bookingId + "'";
		}
		if (category != null && !category.isEmpty()) {
			whereCondition = whereCondition + " AND gr.category_type='" + category + "'";
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
			whereCondition = whereCondition + " AND gr.from_date::date='" + fromDate + "'::date";
		}
		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition = whereCondition + " AND gr.mobile_no='" + mobNo + "'";
		}

		if (name != null ? !name.isEmpty() : false) {
			whereCondition = whereCondition + " AND gs.name ILIKE '%" + name + "%'";
		}

		if(type.equalsIgnoreCase("RECT")) {
			orderByCondition = " ORDER BY r.date_rece DESC";
			query = adminQueries.getProperty("getGeneratedReceiptList");
			query += whereCondition + orderByCondition + limitCondition;
			try {
				data = jdbcTemplate.query(query, (rs, i) -> adminDao.setGeneratedReceiptResp(rs));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			System.out.println("   dfffff  "+query);
			return data;
		}
		query = query + whereCondition + orderByCondition  + limitCondition;
		data = jdbcTemplate.query(query, (rs, i) -> adminDao.setMapReceiptResp(rs));
		System.out.println(" query for generate receipt "+query);
		return data;
	}
	
	@Override
	public Map<String, Object> getAdminConfrenceRequestDetails(Map<String, Object> params) {
		List<ConfrenceHall> data = new ArrayList<>();
		String orderByCondition = " ORDER BY gh.req_date DESC";
		String query = adminQueries.getProperty("getListOfConfrenceRequestAdmin");
		String whereCondition = " AND 1=1 ";
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String bookingDate = (String) params.get("bookingDate");
		String mobNo = (String) params.get("mobNo");
		String name = (String) params.get("name");
		String blockid = (String) params.get("blockid");
		// String status = (String) params.get("status");
		String roomType = (String) params.get("roomType");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";
		Map<String, Object> resp = new HashMap<String, Object>();

		if (cityId != null ? !cityId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.gh_id='" + cityId + "'";
		} 
		if (ghId != null ? !ghId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.block_id='" + ghId + "'";
		}
		if (bookingId != null ? !bookingId.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.req_id='" + bookingId + "'";
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

		if (mobNo != null ? !mobNo.isEmpty() : false) {
			whereCondition = whereCondition + " AND gh.mobile_no='" + mobNo + "'";
		}

		if (name != null ? !name.isEmpty() : false) {
			whereCondition = whereCondition + " AND b.name ILIKE '%" + name + "%'";
		}
		query = query + whereCondition + orderByCondition + limitCondition;
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
					data1.setTypeOfRoom("Conference");
				} catch (Exception e) {
					e.printStackTrace();
				}
				return data1;
			}
		});
		resp.put("status", true);
		resp.put("data",data);
		System.out.println("getConfrenceRequestDetails   "+query);
		return resp;
	}
	
	@Override
	public List<BookingResponseModel> getAdminFeedbackDetailsList(Map<String, Object> params) {
		List<BookingResponseModel> users = new ArrayList<BookingResponseModel>();
		String orderBy = "order by req_date desc)f1  where f1.i =1 ";
		String sql = approverQueries.getProperty("getallFeedbackListInAdminSession");
		String whereCondition = " WHERE 1=1";
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String bookingId = (String) params.get("bookingId");
		String name = (String) params.get("name");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";

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
		sql = sql + whereCondition + orderBy + limitCondition;
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
					booking.setViewForFeedback(rs.getBoolean(71));
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
	public List<AllotmentRoom> viewAllotmentRoomByReqId(Map<String, Object> params) {
	    String reqId = (String) params.get("reqId");
	    List<AllotmentRoom> data = new ArrayList<>();
	    String query = adminQueries.getProperty("getAllotRoomByReqId");

	    try {
	        data = jdbcTemplate.query(query, (rs, rowNum) -> {
	            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	            Date fromDate = rs.getDate("from_date") != null ? rs.getTimestamp("from_date") : null;
	            Date toDate = rs.getDate("to_date") != null ? rs.getTimestamp("to_date") : null;

	            AllotmentRoom allotmentRoom = new AllotmentRoom();
	            allotmentRoom.setFromDate(sdf1.format(fromDate));
	            allotmentRoom.setToDate(sdf1.format(toDate));
	            allotmentRoom.setReqId(rs.getString("req_id"));

	            String[] allotIds = rs.getString("allot_ids").split(",");
	            String[] roomNos = rs.getString("room_nos").split(",");

	            List<Map<String, String>> alltmentDetails = new ArrayList<>();

	            for (int i = 0; i < allotIds.length; i++) {
	                Map<String, String> detail = new HashMap<>();
	                detail.put("allotId", allotIds[i]);
	                detail.put("allotRoom", roomNos[i]);
	                alltmentDetails.add(detail);
	            }

	            allotmentRoom.setAlltmentDetails(alltmentDetails);

	            return allotmentRoom;
	        }, reqId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return data;
	}
	
	@Override
	public List<ShiftMasterModel> getShiftReportList(Map<String, Object> params) {
		System.out.println(params.toString());
		List<ShiftMasterModel> shiftList=new ArrayList<ShiftMasterModel>();

		String sql = approverQueries.getProperty("getShiftReportSQL");
		String whereCondition = "WHERE 1=1";
		String orderBy="ORDER BY lastmodified desc";
		
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		String shiftId = (String) params.get("shiftId");		
		String createdBy = (String) params.get("createdBy");
		String createdDate = (String) params.get("createdDate");
		int pageSize = (Integer) params.get("pageSize");
		int pageNum = (Integer) params.get("pageNum");
		String limitCondition = " limit " + pageSize + " offset " + (pageNum - 1) * pageSize + " ";

		System.out.println("pageSize++++++"+pageSize);
		System.out.println("pageNum++++++"+pageNum);
		System.out.println("limitCondition+++++"+limitCondition);
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

		sql = sql+whereCondition+orderBy+limitCondition;
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
					sm.setBlockId(rs.getString("block_name"));
					sm.setCityId(rs.getString("gh_name"));
					sm.setCreatedTime(frDt);
					sm.setCloseTime(toDt);
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
	public List<ProcessModeration> getconfirmDraftlist(HttpServletRequest request) {
		List<ProcessModeration> alldetailsValue = null;
		String cityName = "";
		String guesthouseName = "";

		if (request.getParameter("cityName") != null || !request.getParameter("cityName").equals(""))
			cityName = request.getParameter("cityName");
		if (request.getParameter("guesthouseName") != null || !request.getParameter("guesthouseName").equals(""))
			guesthouseName = request.getParameter("guesthouseName");

		if (cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("confirmDraftallList");
			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {
				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String ceateddate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date")): null;
					ProcessModeration process = new ProcessModeration();
					process.setProcessId(rs.getString(1));
					process.setCreatedBy(rs.getString(2));
					process.setCreatedDate(ceateddate);
					process.setStatus(rs.getString(4));
					process.setCityId(rs.getString(7));
					process.setGoSign(rs.getString(8));
					process.setGoSignDesig(rs.getString(9));
					process.setBlockId(rs.getString(10));
					process.setBlockName(rs.getString("block_name"));
					process.setCityName(rs.getString("gh_name"));
					return process;
				}
			});
		}
		if (!cityName.equals("") && guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("confirmDraftallListwithCity");

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {

				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String ceateddate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date"))
							: null;
					ProcessModeration process = new ProcessModeration();
					process.setProcessId(rs.getString(1));
					process.setCreatedBy(rs.getString(2));
					process.setCreatedDate(ceateddate);
					process.setStatus(rs.getString(4));
					process.setCityId(rs.getString(7));
					process.setGoSign(rs.getString(8));
					process.setGoSignDesig(rs.getString(9));
					process.setBlockId(rs.getString(10));
					process.setBlockName(rs.getString("block_name"));
					process.setCityName(rs.getString("gh_name"));
					return process;
				}
			}, cityName);
		}
		if (!cityName.equals("") && !guesthouseName.equals("")) {
			String sql = adminQueries.getProperty("confirmDraftallListwithGHouseAndCity");

			alldetailsValue = jdbcTemplate.query(sql, new RowMapper<ProcessModeration>() {

				public ProcessModeration mapRow(ResultSet rs, int rownumber) throws SQLException {
					final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String ceateddate = rs.getDate("created_date") != null ? sdf.format(rs.getDate("created_date"))
							: null;
					ProcessModeration process = new ProcessModeration();
					process.setProcessId(rs.getString(1));
					process.setCreatedBy(rs.getString(2));
					process.setCreatedDate(ceateddate);
					process.setStatus(rs.getString(4));
					process.setCityId(rs.getString(7));
					process.setGoSign(rs.getString(8));
					process.setGoSignDesig(rs.getString(9));
					process.setBlockId(rs.getString(10));
					process.setBlockName(rs.getString("block_name"));
					process.setCityName(rs.getString("gh_name"));
					return process;
				}
			}, cityName, guesthouseName);
		}
		return alldetailsValue;
	}
	

}
