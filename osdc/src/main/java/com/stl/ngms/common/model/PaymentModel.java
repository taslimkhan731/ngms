package com.stl.ngms.common.model;

public class PaymentModel {
    private String id;
    private String blockId;
    private Double amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "id='" + id + '\'' +
                ", blockId='" + blockId + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
