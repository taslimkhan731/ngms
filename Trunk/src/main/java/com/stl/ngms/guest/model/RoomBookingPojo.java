package com.stl.ngms.guest.model;

import com.stl.ngms.admin.model.RoomDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class RoomBookingPojo {

    private String type;
    private String cityId;
    private String cityName;
    private String blockId;
    private String blockName;
    private String bookingId;
    private String bookingType;
    private String frmDt;
    private String toDt;
    private String canStatus;
    @Pattern(regexp = "^0*(?:[1-9][0-9]?|100)$")
    @Size(min = 1, max = 100)
    private String noOfRooms;

    private String noOfBeds;

    private String noOfBedsDormitory;
    @Pattern(regexp = "[0-9]+")
    @Size(min = 10, max = 10)
    private String mob;
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    private String emailId;

    private String catg;
    private String catgName;

    private String purp;
    private String purpName;

    private String desg;
    private String desgName;
    private String roomType;
    /*@Pattern(regexp ="[A-Za-z ]+")*/
    private String offAddr;
    private String aVchId;
    private String travelModeFr;
    private String flNoFr;
    private String arrDt;
    private String arrTm;
    private String arrPla;
    private String dVchId;
    private String travelModeTo;
    private String flNoTo;
    private String depDt;
    private String depTm;
    private String depPla;
    private String guestDtlsStr;
    private String status;
    private String statusDesc;
    private String modeOfReq;
    private Boolean isExtension;
    private String extensionId;
    private Boolean isArrivalConv;
    private Boolean IsDepartueConv;
    private List<GuestDetailsPojo> list;
    private List<CommonComboBoxResponseModel> ghList;
    private MultipartFile suppDoc;
    private String suppDocFile;
    private String suppDocName;
    private String catgType;
    private MultipartFile catgDoc;
    private String catgDocFile;
    private String catgDocName;
    private String modId;
    private String quotatype;
    private String quotadesc;
    private String responseSupDoc;
    private GuestDetailsPojo single;
    private String guestdetails;
    private String arrivaldetails;
    private String depaturedetails;
    private String requestid;
    private String duringstay;
    private String convayance;
    private String bookingFor;
    @Pattern(regexp = "^0*(?:[1-9][0-9]?|100)$")
    @Size(min = 1, max = 100)
    private Integer noOfAdults;
    @Pattern(regexp = "^0*(?:[1-9][0-9]?|100)$")
    @Size(max = 100)
    private Integer noOfChildren;
    @Pattern(regexp = "^0*(?:[1-9][0-9]?|100)$")
    @Size(min = 1, max = 100)
    private Integer noOfDays;
    @Pattern(regexp = "^(\\s*|\\d+)$")
    private String gMobile;
    private MultipartFile gDoc;
    private String gDocFile;
    private String gDocName;
    private String gDocType;
    private String createdBy;
    private Date createdOn;
    private String updatedBy;
    private Date updatedOn;
    private String userId;
    private String existingBookingId;
    private String splitGHouse;
    private List<RoomDetails> roomDetails;
    private List<RoomDetails> modifiedDetails;
    private List<RoomDetails> partialCancelRoomDetails;
    private String count;
    private String reqType;
    private String paramType;
    private String requestId;
    private String arrTravel;
    private String deptTravel;
    private String ext;
    
    public String getExt() {
		return ext;
	}


	public void setExt(String ext) {
		this.ext = ext;
	}


	public String getCanStatus() {
		return canStatus;
	}


	public void setCanStatus(String canStatus) {
		this.canStatus = canStatus;
	}


	public List<RoomDetails> getModifiedDetails() {
		return modifiedDetails;
	}


	public void setModifiedDetails(List<RoomDetails> modifiedDetails) {
		this.modifiedDetails = modifiedDetails;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCatgType() {
        return catgType;
    }


    public void setCatgType(String catgType) {
        this.catgType = catgType;
    }


    public String getDuringstay() {
        return duringstay;
    }


    public void setDuringstay(String duringstay) {
        this.duringstay = duringstay;
    }


    public String getRequestid() {
        return requestid;
    }


    public void setRequestid(String requestid) {
        this.requestid = requestid;
    }


    public String getCityId() {
        return cityId;
    }


    public void setCityId(String cityId) {
        this.cityId = cityId;
    }


    public String getCityName() {
        return cityName;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }


    public String getBlockId() {
        return blockId;
    }


    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }


    public String getBlockName() {
        return blockName;
    }


    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }


    public String getBookingId() {
        return bookingId;
    }


    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }


    public String getBookingType() {
        return bookingType;
    }


    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }


    public String getFrmDt() {
        return frmDt;
    }


    public void setFrmDt(String frmDt) {
        this.frmDt = frmDt;
    }


    public String getToDt() {
        return toDt;
    }


    public void setToDt(String toDt) {
        this.toDt = toDt;
    }


    public String getNoOfRooms() {
        return noOfRooms;
    }


    public void setNoOfRooms(String noOfRooms) {
        this.noOfRooms = noOfRooms;
    }


    public String getNoOfBeds() {
        return noOfBeds;
    }


    public void setNoOfBeds(String noOfBeds) {
        this.noOfBeds = noOfBeds;
    }


    public String getNoOfBedsDormitory() {
        return noOfBedsDormitory;
    }


    public void setNoOfBedsDormitory(String noOfBedsDormitory) {
        this.noOfBedsDormitory = noOfBedsDormitory;
    }


    public String getMob() {
        return mob;
    }


    public void setMob(String mob) {
        this.mob = mob;
    }


    public String getEmailId() {
        return emailId;
    }


    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public String getCatg() {
        return catg;
    }


    public void setCatg(String catg) {
        this.catg = catg;
    }


    public String getCatgName() {
        return catgName;
    }


    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }


    public String getPurp() {
        return purp;
    }


    public void setPurp(String purp) {
        this.purp = purp;
    }


    public String getPurpName() {
        return purpName;
    }


    public void setPurpName(String purpName) {
        this.purpName = purpName;
    }


    public String getDesg() {
        return desg;
    }


    public void setDesg(String desg) {
        this.desg = desg;
    }


    public String getDesgName() {
        return desgName;
    }


    public void setDesgName(String desgName) {
        this.desgName = desgName;
    }


    public String getRoomType() {
        return roomType;
    }


    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }


    public String getOffAddr() {
        return offAddr;
    }


    public void setOffAddr(String offAddr) {
        this.offAddr = offAddr;
    }


    public String getaVchId() {
        return aVchId;
    }


    public void setaVchId(String aVchId) {
        this.aVchId = aVchId;
    }


    public String getTravelModeFr() {
        return travelModeFr;
    }


    public void setTravelModeFr(String travelModeFr) {
        this.travelModeFr = travelModeFr;
    }


    public String getFlNoFr() {
        return flNoFr;
    }


    public void setFlNoFr(String flNoFr) {
        this.flNoFr = flNoFr;
    }


    public String getArrDt() {
        return arrDt;
    }


    public void setArrDt(String arrDt) {
        this.arrDt = arrDt;
    }


    public String getArrTm() {
        return arrTm;
    }


    public void setArrTm(String arrTm) {
        this.arrTm = arrTm;
    }


    public String getArrPla() {
        return arrPla;
    }


    public void setArrPla(String arrPla) {
        this.arrPla = arrPla;
    }


    public String getdVchId() {
        return dVchId;
    }


    public void setdVchId(String dVchId) {
        this.dVchId = dVchId;
    }


    public String getTravelModeTo() {
        return travelModeTo;
    }


    public void setTravelModeTo(String travelModeTo) {
        this.travelModeTo = travelModeTo;
    }


    public String getFlNoTo() {
        return flNoTo;
    }


    public void setFlNoTo(String flNoTo) {
        this.flNoTo = flNoTo;
    }


    public String getDepDt() {
        return depDt;
    }


    public void setDepDt(String depDt) {
        this.depDt = depDt;
    }


    public String getDepTm() {
        return depTm;
    }


    public void setDepTm(String depTm) {
        this.depTm = depTm;
    }


    public String getDepPla() {
        return depPla;
    }


    public void setDepPla(String depPla) {
        this.depPla = depPla;
    }


    public String getGuestDtlsStr() {
        return guestDtlsStr;
    }


    public void setGuestDtlsStr(String guestDtlsStr) {
        this.guestDtlsStr = guestDtlsStr;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatusDesc() {
        return statusDesc;
    }


    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }


    public String getModeOfReq() {
        return modeOfReq;
    }


    public void setModeOfReq(String modeOfReq) {
        this.modeOfReq = modeOfReq;
    }


    public Boolean getIsExtension() {
        return isExtension;
    }


    public void setIsExtension(Boolean isExtension) {
        this.isExtension = isExtension;
    }


    public String getExtensionId() {
        return extensionId;
    }


    public void setExtensionId(String extensionId) {
        this.extensionId = extensionId;
    }


    public Boolean getIsArrivalConv() {
        return isArrivalConv;
    }


    public void setIsArrivalConv(Boolean isArrivalConv) {
        this.isArrivalConv = isArrivalConv;
    }


    public Boolean getIsDepartueConv() {
        return IsDepartueConv;
    }


    public void setIsDepartueConv(Boolean isDepartueConv) {
        IsDepartueConv = isDepartueConv;
    }


    public List<GuestDetailsPojo> getList() {
        return list;
    }


    public void setList(List<GuestDetailsPojo> list) {
        this.list = list;
    }


    public MultipartFile getSuppDoc() {
        return suppDoc;
    }


    public void setSuppDoc(MultipartFile suppDoc) {
        this.suppDoc = suppDoc;
    }


    public String getSuppDocName() {
        return suppDocName;
    }


    public void setSuppDocName(String suppDocName) {
        this.suppDocName = suppDocName;
    }


    public MultipartFile getCatgDoc() {
        return catgDoc;
    }


    public void setCatgDoc(MultipartFile catgDoc) {
        this.catgDoc = catgDoc;
    }


    public String getCatgDocName() {
        return catgDocName;
    }


    public void setCatgDocName(String catgDocName) {
        this.catgDocName = catgDocName;
    }


    public String getModId() {
        return modId;
    }


    public void setModId(String modId) {
        this.modId = modId;
    }


    public String getQuotatype() {
        return quotatype;
    }


    public void setQuotatype(String quotatype) {
        this.quotatype = quotatype;
    }


    public String getQuotadesc() {
        return quotadesc;
    }


    public void setQuotadesc(String quotadesc) {
        this.quotadesc = quotadesc;
    }

    public String getGuestdetails() {
        return guestdetails;
    }

    public void setGuestdetails(String guestdetails) {
        this.guestdetails = guestdetails;
    }

    public String getArrivaldetails() {
        return arrivaldetails;
    }

    public void setArrivaldetails(String arrivaldetails) {
        this.arrivaldetails = arrivaldetails;
    }

    public String getDepaturedetails() {
        return depaturedetails;
    }

    public void setDepaturedetails(String depaturedetails) {
        this.depaturedetails = depaturedetails;
    }


    public String getResponseSupDoc() {
        return responseSupDoc;
    }


    public void setResponseSupDoc(String responseSupDoc) {
        this.responseSupDoc = responseSupDoc;
    }


    public GuestDetailsPojo getSingle() {
        return single;
    }


    public void setSingle(GuestDetailsPojo single) {
        this.single = single;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getConvayance() {
        return convayance;
    }


    public void setConvayance(String convayance) {
        this.convayance = convayance;
    }

	public Boolean getExtension() {
		return isExtension;
	}

	public void setExtension(Boolean extension) {
		isExtension = extension;
	}

	public Boolean getArrivalConv() {
		return isArrivalConv;
	}

	public void setArrivalConv(Boolean arrivalConv) {
		isArrivalConv = arrivalConv;
	}

	public Boolean getDepartueConv() {
		return IsDepartueConv;
	}

	public void setDepartueConv(Boolean departueConv) {
		IsDepartueConv = departueConv;
	}

	public String getBookingFor() {
		return bookingFor;
	}

	public void setBookingFor(String bookingFor) {
		this.bookingFor = bookingFor;
	}

	public Integer getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(Integer noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public Integer getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }

    public String getgMobile() {
        return gMobile;
    }

    public void setgMobile(String gMobile) {
        this.gMobile = gMobile;
    }

    public MultipartFile getgDoc() {
        return gDoc;
    }

    public void setgDoc(MultipartFile gDoc) {
        this.gDoc = gDoc;
    }

    public String getgDocName() {
        return gDocName;
    }

    public void setgDocName(String gDocName) {
        this.gDocName = gDocName;
    }

    public String getgDocType() {
        return gDocType;
    }

    public void setgDocType(String gDocType) {
        this.gDocType = gDocType;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getExistingBookingId() {
        return existingBookingId;
    }

    public void setExistingBookingId(String existingBookingId) {
        this.existingBookingId = existingBookingId;
    }

    public String getSplitGHouse() {
        return splitGHouse;
    }

    public void setSplitGHouse(String splitGHouse) {
        this.splitGHouse = splitGHouse;
    }

    public List<CommonComboBoxResponseModel> getGhList() {
		return ghList;
	}

	public void setGhList(List<CommonComboBoxResponseModel> ghList) {
		this.ghList = ghList;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

    public List<RoomDetails> getRoomDetails() {
        return roomDetails;
    }

    public void setRoomDetails(List<RoomDetails> roomDetails) {
        this.roomDetails = roomDetails;
    }
    
    public String getSuppDocFile() {
		return suppDocFile;
	}


	public void setSuppDocFile(String suppDocFile) {
		this.suppDocFile = suppDocFile;
	}


	public String getCatgDocFile() {
		return catgDocFile;
	}


	public void setCatgDocFile(String catgDocFile) {
		this.catgDocFile = catgDocFile;
	}


	public String getgDocFile() {
		return gDocFile;
	}


	public void setgDocFile(String gDocFile) {
		this.gDocFile = gDocFile;
	}
    

	public String getReqType() {
		return reqType;
	}


	public void setReqType(String reqType) {
		this.reqType = reqType;
	}


	public String getParamType() {
		return paramType;
	}


	public void setParamType(String paramType) {
		this.paramType = paramType;
	}


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	
	public List<RoomDetails> getPartialCancelRoomDetails() {
		return partialCancelRoomDetails;
	}


	public void setPartialCancelRoomDetails(List<RoomDetails> partialCancelRoomDetails) {
		this.partialCancelRoomDetails = partialCancelRoomDetails;
	}


	public String getArrTravel() {
		return arrTravel;
	}


	public void setArrTravel(String arrTravel) {
		this.arrTravel = arrTravel;
	}


	public String getDeptTravel() {
		return deptTravel;
	}


	public void setDeptTravel(String deptTravel) {
		this.deptTravel = deptTravel;
	}


	@Override
	public String toString() {
		return "RoomBookingPojo [type=" + type + ", cityId=" + cityId + ", cityName=" + cityName + ", blockId="
				+ blockId + ", blockName=" + blockName + ", bookingId=" + bookingId + ", bookingType=" + bookingType
				+ ", frmDt=" + frmDt + ", toDt=" + toDt + ", noOfRooms=" + noOfRooms + ", noOfBeds=" + noOfBeds
				+ ", noOfBedsDormitory=" + noOfBedsDormitory + ", mob=" + mob + ", emailId=" + emailId + ", catg="
				+ catg + ", catgName=" + catgName + ", purp=" + purp + ", purpName=" + purpName + ", desg=" + desg
				+ ", desgName=" + desgName + ", roomType=" + roomType + ", offAddr=" + offAddr + ", aVchId=" + aVchId
				+ ", travelModeFr=" + travelModeFr + ", flNoFr=" + flNoFr + ", arrDt=" + arrDt + ", arrTm=" + arrTm
				+ ", arrPla=" + arrPla + ", dVchId=" + dVchId + ", travelModeTo=" + travelModeTo + ", flNoTo=" + flNoTo
				+ ", depDt=" + depDt + ", depTm=" + depTm + ", depPla=" + depPla + ", guestDtlsStr=" + guestDtlsStr
				+ ", status=" + status + ", statusDesc=" + statusDesc + ", modeOfReq=" + modeOfReq + ", isExtension="
				+ isExtension + ", extensionId=" + extensionId + ", isArrivalConv=" + isArrivalConv
				+ ", IsDepartueConv=" + IsDepartueConv + ", list=" + list + ", ghList=" + ghList + ", suppDoc="
				+ suppDoc + ", suppDocFile=" + suppDocFile + ", suppDocName=" + suppDocName + ", catgType=" + catgType
				+ ", catgDoc=" + catgDoc + ", catgDocFile=" + catgDocFile + ", catgDocName=" + catgDocName + ", modId="
				+ modId + ", quotatype=" + quotatype + ", quotadesc=" + quotadesc + ", responseSupDoc=" + responseSupDoc
				+ ", single=" + single + ", guestdetails=" + guestdetails + ", arrivaldetails=" + arrivaldetails
				+ ", depaturedetails=" + depaturedetails + ", requestid=" + requestid + ", duringstay=" + duringstay
				+ ", convayance=" + convayance + ", bookingFor=" + bookingFor + ", noOfAdults=" + noOfAdults
				+ ", noOfChildren=" + noOfChildren + ", noOfDays=" + noOfDays + ", gMobile=" + gMobile + ", gDoc="
				+ gDoc + ", gDocFile=" + gDocFile + ", gDocName=" + gDocName + ", gDocType=" + gDocType + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn
				+ ", userId=" + userId + ", existingBookingId=" + existingBookingId + ", splitGHouse=" + splitGHouse
				+ ", roomDetails=" + roomDetails + ", modifiedDetails=" + modifiedDetails
				+ ", partialCancelRoomDetails=" + partialCancelRoomDetails + ", count=" + count + ", reqType=" + reqType
				+ ", paramType=" + paramType + ", requestId=" + requestId + ", arrTravel=" + arrTravel + ", deptTravel="
				+ deptTravel + "]";
	}

}
