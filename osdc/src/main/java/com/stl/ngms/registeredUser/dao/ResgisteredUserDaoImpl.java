package com.stl.ngms.registeredUser.dao;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.stl.ngms.common.MailUtil;
import com.stl.ngms.common.SendSMS;
import com.stl.ngms.guest.dao.GuestDao;
import com.stl.ngms.guest.model.BookingResponseModel;
import com.stl.ngms.guest.model.GuestDetailsPojo;
import com.stl.ngms.guest.model.RoomBookingPojo;
import com.stl.ngms.utils.Utils;

@Repository("resgisteredUserDao")
public class ResgisteredUserDaoImpl implements ResgisteredUserDao {
	
	final static Logger LOGGER = Logger.getLogger(ResgisteredUserDaoImpl.class);

	@Autowired 
	private JdbcTemplate jdbcTemplate;

	@Autowired 
	private Environment env;
	
	@Autowired 
	private MailUtil mail;
		
	@Autowired 
	private SendSMS smsSend;

	@Autowired 
	private Properties approverQueries;
	
	@Autowired 
	private Properties allotmentQueries;
	
	@Autowired
	private GuestDao guestDao;
	
	@Override
	public boolean addBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		List<Map<String, String>> blockList = null;
		String ghName = null;
		
		try {
			blockList = guestDao.getBlockList(data.getBlockId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LOGGER.info(blockList.get(0).get("ghId"));
		try {
			ghName = guestDao.getCityName(blockList.get(0).get("ghId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat stf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
		LOGGER.info(data.getRequestid());
		String reqId=null;
		if(null==data.getRequestid()){
			Integer id = guestDao.get_seqID("req_id");
			guestDao.update_seqID("req_id", id);
			reqId = "P" + id.toString();
		}else{
			Integer id = guestDao.get_seqID("req_id");
			guestDao.update_seqID("req_id", id);
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
//		String q5=  approverQueries.getProperty("updateOrisaoneDetailsSQL");
		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(reqId);
			String directory = env.getProperty("media.base.path") + File.separator + 
					env.getProperty("sup.doc.folder") + File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			LOGGER.info("saved document file path :: "+filepath);
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
			LOGGER.info("saved document category file path :: "+catg_filepath);
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
		LOGGER.info(iddd);
		LOGGER.info(data.getBookingType());
		
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
			Integer vid = guestDao.get_seqID("veh_id");
			guestDao.update_seqID("veh_id", vid);
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
					Integer gid = guestDao.get_seqID("guest_id");
					String guestId = "P_" + gid.toString();
					guestDao.update_seqID("guest_id", gid);
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
//			if(data.getRequestid()!=null)
//			{
//				if(reqId!=null)
//				{
//					jdbcTemplate.update(q5, new Object[] { 
//							reqId,
//							data.getRequestid()
//					});		
//				}
//			}
//		try {
//			if(data.getEmailId() != null) {
//				if(!data.getEmailId().isEmpty()) {
//					mail.sendMimeMail(data.getEmailId(),"Application Details","Dear " + guestName + "<br> You have successfully applied for " + msg + " and your booking id " + reqId + "<br><br> Thanks <br>e-Bhawan Team");//mail is send to the corresponding Email
//				}
//			}
//		} catch (Exception e) {
//			LOGGER.error("error occured in sms or email send..");
//		}
		
		try {
			if(data.getMob() != null) {
				if(!data.getMob().isEmpty()) {
					String templateID="1077054700132341208";
					smsSend.sendSMS(
							data.getMob(),
							"Dear Madam/Sir, you have successfully applied for "
							+ msg + " and your Booking ID is " + reqId + ". For details please login to the portal. Home Dept., Govt. of Odisha ",
							templateID
					);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Error occured in sms " + e.getMessage());
		}
		
		return true;
	}

	@Override
	public boolean modifyBookingDetails(RoomBookingPojo data, String userId) throws Exception {
		RoomBookingPojo pd = guestDao.getBookingDetailsById(data.getBookingId(),userId);
		LOGGER.info(data);
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
//		String q8 = approverQueries.getProperty("deleteGuestDetailsSQL");

		String filepath = null;
		if (null != data.getSuppDoc()) {
			String fileSeperator = String.valueOf(new Date().getTime()) + "_" + String.valueOf(pd.getBookingId());
			String directory = env.getProperty("media.base.path") + File.separator + 
					env.getProperty("sup.doc.folder") + File.separator + fileSeperator;
			filepath = Utils.saveFileInSysPath(data.getSuppDoc(), directory);
			LOGGER.info("saved document file path :: "+filepath);
			if(filepath != null) {
				filepath = filepath.contains("\\") ? filepath.substring(filepath.indexOf("\\"))
						: filepath.substring(filepath.indexOf("/"));
			}else {
				try {
					filepath = guestDao.get_filePath(pd.getBookingId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		else {
			try {
				filepath = guestDao.get_filePath(pd.getBookingId());
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
			LOGGER.info("saved document category file path :: "+catg_filepath);
			if(catg_filepath != null) {
				catg_filepath = catg_filepath.contains("\\") ? catg_filepath.substring(catg_filepath.indexOf("\\"))
						: catg_filepath.substring(catg_filepath.indexOf("/"));
				LOGGER.info(catg_filepath);
			}
			else {
				try {
					catg_filepath = guestDao.get_filePath1(pd.getBookingId());
					LOGGER.info(catg_filepath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			}
		else {
			try {
				catg_filepath = guestDao.get_filePath1(pd.getBookingId());
				LOGGER.info(catg_filepath);
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
			Integer vid = guestDao.get_seqID("veh_id");
			guestDao.update_seqID("veh_id", vid);
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
			Integer vid = guestDao.get_seqID("veh_id");
			guestDao.update_seqID("veh_id", vid);
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
					LOGGER.info(item);
					i++;
					if(item.getGuestId().equals("")) {
						Integer gid = guestDao.get_seqID("guest_id");
						String guestId = "P_" + gid.toString();
						id = id + "'" + guestId + "'";
						guestDao.update_seqID("guest_id", gid);
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
//					if(i < data.getList().size()) {
//						id = id + ",";
//					}
				}
//				if(!id.isEmpty()) {
//					q8 = q8.concat(id + ")");
//				}
//				jdbcTemplate.update(q8, new Object[] {pd.getBookingId()});	
			}
		}
		status = true;
		return status;
	}

	@Override
	public boolean modifyGuestDetails(RoomBookingPojo data) throws Exception {
		LOGGER.info(data);
		boolean status = false;

		String query1 = approverQueries.getProperty("saveGuestDetailsSQL");
		String query2 = approverQueries.getProperty("updateGuestDetailsSQL");
		String query3 = allotmentQueries.getProperty("deleteGuestDtlsSQL");
		
		if(data.getList() != null) {
			for(GuestDetailsPojo item : data.getList()) {
				LOGGER.info(item);
				if(item.getGuestId().equals("")) {
					Integer gid = guestDao.get_seqID("guest_id");
					String guestId = "P_" + gid.toString();
					guestDao.update_seqID("guest_id", gid);
					jdbcTemplate.update(query1, new Object[] { 
							data.getBookingId(),
							guestId,
							item.getGuestName(),
							item.getGuestAge(),
							item.getGuestGender(),
							item.getGuestAddr(),
							item.getGuestDesignation(),
							item.getGuestDepartment()
					});		
				} else {
					jdbcTemplate.update(query2, new Object[] { 
							item.getGuestName(),
							item.getGuestAge(),
							item.getGuestGender(),
							item.getGuestAddr(),
							item.getGuestDesignation(),
							item.getGuestDepartment(),
							data.getBookingId(),
							item.getGuestId(),
					});
				}
			}
			if(!data.getGuestDtlsStr().isEmpty()) {
				String Ids = Arrays.asList(data.getGuestDtlsStr().split(",")).stream().map(i->("'"+i+"'")).collect(Collectors.joining(","));
				query3 = query3.concat(Ids + ")");
				
				jdbcTemplate.update(query3, new Object[] {data.getBookingId()});
			}
				
		}
		status = true;
		return status;
	}
	
	private BookingResponseModel setBookingLstFromResultset(ResultSet rs) throws SQLException{
		BookingResponseModel data = new BookingResponseModel();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			String bookingDt = rs.getDate(2)!=null ? sdf.format(rs.getDate(2)) : null;
			String frDt = rs.getDate(4)!=null ? sdf.format(rs.getDate(4)) : null;
			String toDt = rs.getDate(5)!=null ? sdf.format(rs.getDate(5)) : null;
			
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
			data.setCatgId(rs.getString(12));
			data.setCategory(rs.getString(13));
			data.setDesgId(rs.getString(14));
			data.setDesg(rs.getString(15));
			data.setMobile(rs.getString(16));
			data.setEmail(rs.getString(17));
			data.setStatus(rs.getString(18));
//			data.setStatusDesc(rs.getString(19));	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Override
	public List<BookingResponseModel> getAllotmentLstDtlsAccToReq(Map<String, Object> params) {
		List<BookingResponseModel> data = new ArrayList<>();
		String orderByCondition = " ORDER BY gh.req_date DESC";
		String query = allotmentQueries.getProperty("getAllotmentLstDtlsSQL");
		String whereCondition = " WHERE 1=1";
		
		String reqType = (String) params.get("reqType");
		String cityId = (String) params.get("cId");
		String ghId = (String) params.get("gId");
		
		LocalDate curDt = LocalDate.now();
		LocalDate nextDt = curDt.plusDays(6);
		
		if(cityId!=null?!cityId.isEmpty():false) {
			whereCondition = whereCondition + " AND gh.gh_id='"+cityId+"'";
		}
		if(ghId!=null?!ghId.isEmpty():false) {
			whereCondition = whereCondition + " AND gh.block_id='"+ghId+"'";
		}
		if(reqType.equalsIgnoreCase("AL")) {
			whereCondition = whereCondition + " AND gh.status='REQ' AND gh.mode_of_req IN ('GAD','PUB')";
		} else if(reqType.equalsIgnoreCase("MA")) {
			whereCondition = whereCondition + " AND gh.status='REQ' AND gh.mode_of_req IN ('GAD','PUB') AND gh.from_date<='"+nextDt+"' AND gh.to_date>='"+curDt+"'";
		} else if(reqType.equalsIgnoreCase("MM")) {
			whereCondition = whereCondition + " AND gh.status='MOD' AND gh.mode_of_req IN ('GAD','PUB') AND gh.to_date>='"+curDt+"'";
		}
		query = query + whereCondition + orderByCondition;
		LOGGER.info(query);
		data = jdbcTemplate.query(query, new RowMapper<BookingResponseModel>() {
			@Override
			public BookingResponseModel mapRow(ResultSet rs, int rownumber) throws SQLException {
				return setBookingLstFromResultset(rs);
			}
		});
		
		return data;
	}
}
