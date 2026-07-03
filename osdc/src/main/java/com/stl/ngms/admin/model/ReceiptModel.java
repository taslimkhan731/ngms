package com.stl.ngms.admin.model;

import java.util.List;
import java.util.Map;

public class ReceiptModel {
	private String cityName;
	private String guestHouseName;
    private String recId;
    private String billId;
    private String billNo;
    private String createdBy;
    private String date;
    private String rectDate;
    private String cityId;
    private String ghId;
    private String paidAmount;
    private String total;
    private String bookingId;
    private String fromDate;
    private String toDate;
    private String name;
    private List<String> guest;
    private String roomNo;
    private String mobileNo;
    private Map<String, Double> mode;
    private String transactionNo;
    private String category;
    List<Map<String, Object>> paymentModes;
    private String status;
    private String dueAmount;
    private String onlyName;
    private String modeOfPayment;
    private Integer amounts;
    private String guestHouseAddress;
    private String guestHouseMail;
    private String guestHouseContactNum;
    private String advancedAmount;
    private String purpose;
    private String totalAmount;
    private String guestContact;
    private String guestEmail;
    private String guestAddress;
    private String noOfGuest;
    private String amountPaid;
    

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getNoOfGuest() {
		return noOfGuest;
	}

	public void setNoOfGuest(String noOfGuest) {
		this.noOfGuest = noOfGuest;
	}

	public String getGuestContact() {
		return guestContact;
	}

	public void setGuestContact(String guestContact) {
		this.guestContact = guestContact;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public String getGuestAddress() {
		return guestAddress;
	}

	public void setGuestAddress(String guestAddress) {
		this.guestAddress = guestAddress;
	}

	public String getGuestHouseAddress() {
		return guestHouseAddress;
	}

	public void setGuestHouseAddress(String guestHouseAddress) {
		this.guestHouseAddress = guestHouseAddress;
	}

	public String getGuestHouseMail() {
		return guestHouseMail;
	}

	public void setGuestHouseMail(String guestHouseMail) {
		this.guestHouseMail = guestHouseMail;
	}

	public String getGuestHouseContactNum() {
		return guestHouseContactNum;
	}

	public void setGuestHouseContactNum(String guestHouseContactNum) {
		this.guestHouseContactNum = guestHouseContactNum;
	}

	public String getAdvancedAmount() {
		return advancedAmount;
	}

	public void setAdvancedAmount(String advancedAmount) {
		this.advancedAmount = advancedAmount;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Integer getAmounts() {
		return amounts;
	}

	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGuestHouseName() {
		return guestHouseName;
	}

	public void setGuestHouseName(String guestHouseName) {
		this.guestHouseName = guestHouseName;
	}

	public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getRectDate() {
        return rectDate;
    }

    public void setRectDate(String rectDate) {
        this.rectDate = rectDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getGhId() {
        return ghId;
    }

    public void setGhId(String ghId) {
        this.ghId = ghId;
    }

   

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<String> getGuest() {
        return guest;
    }

    public void setGuest(List<String> guest) {
        this.guest = guest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public Map<String, Double> getMode() {
        return mode;
    }

    public void setMode(Map<String, Double> mode) {
        this.mode = mode;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }        

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    public List<Map<String, Object>> getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(List<Map<String, Object>> paymentModes) {
        this.paymentModes = paymentModes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getOnlyName() {
		return onlyName;
	}

	public void setOnlyName(String onlyName) {
		this.onlyName = onlyName;
	}

	@Override
	public String toString() {
		return "ReceiptModel [cityName=" + cityName + ", guestHouseName=" + guestHouseName + ", recId=" + recId
				+ ", billId=" + billId + ", billNo=" + billNo + ", createdBy=" + createdBy + ", date=" + date
				+ ", rectDate=" + rectDate + ", cityId=" + cityId + ", ghId=" + ghId + ", paidAmount=" + paidAmount
				+ ", total=" + total + ", bookingId=" + bookingId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", name=" + name + ", guest=" + guest + ", roomNo=" + roomNo + ", mobileNo=" + mobileNo + ", mode="
				+ mode + ", transactionNo=" + transactionNo + ", category=" + category + ", paymentModes="
				+ paymentModes + ", status=" + status + ", dueAmount=" + dueAmount + ", onlyName=" + onlyName
				+ ", modeOfPayment=" + modeOfPayment + ", amounts=" + amounts + ", guestHouseAddress="
				+ guestHouseAddress + ", guestHouseMail=" + guestHouseMail + ", guestHouseContactNum="
				+ guestHouseContactNum + ", advancedAmount=" + advancedAmount + ", purpose=" + purpose
				+ ", totalAmount=" + totalAmount + ", guestContact=" + guestContact + ", guestEmail=" + guestEmail
				+ ", guestAddress=" + guestAddress + "]";
	}

}
