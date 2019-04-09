package org.csu.bookstore.domain;

import java.util.Date;
import java.util.List;

public class Order {
    private Integer orderId;

    private Integer userId;

    private Date date;

    private Float carriage;

    private Float totalCost;

    private String recipientName;

    private String recipientPhone;

    private String zip;

    private String recipientAddress;

    private Short isPaid;

    private Date paidTime;

    private Short isSent;

    private Date sentTime;

    private Short isRecived;

    private Date recivedTime;

    private Short isDone;

    private Date doneTime;

    private List<OrderDetail> orderDetailList;

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getCarriage() {
        return carriage;
    }

    public void setCarriage(Float carriage) {
        this.carriage = carriage;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName == null ? null : recipientName.trim();
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone == null ? null : recipientPhone.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress == null ? null : recipientAddress.trim();
    }

    public Short getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Short isPaid) {
        this.isPaid = isPaid;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Short getIsSent() {
        return isSent;
    }

    public void setIsSent(Short isSent) {
        this.isSent = isSent;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }

    public Short getIsRecived() {
        return isRecived;
    }

    public void setIsRecived(Short isRecived) {
        this.isRecived = isRecived;
    }

    public Date getRecivedTime() {
        return recivedTime;
    }

    public void setRecivedTime(Date recivedTime) {
        this.recivedTime = recivedTime;
    }

    public Short getIsDone() {
        return isDone;
    }

    public void setIsDone(Short isDone) {
        this.isDone = isDone;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }
}