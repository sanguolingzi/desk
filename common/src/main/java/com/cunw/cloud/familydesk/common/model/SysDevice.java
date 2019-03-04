package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysDevice implements IBasePO<String> {
    private String deviceId;

    /**
     * 设备号
     */
    private String deviceNo;

    /**
     * 设备类型id
     */
    private String typeId;

    /**
     * 设备类型：1-校牌 2-手表 3-一体机 4课桌
     */
    private String type;

    /**
     * 设备GIS_id
     */
    private String gisId;

    /**
     * IMEI号
     */
    private String imei;

    /**
     * Sn序列号
     */
    private String sn;

    /**
     * 二维码字符串
     */
    private String qrCode;

    /**
     * 设备特理码
     */
    private String mac;

    /**
     * 发放时间
     */
    private Date provDate;

    /**
     * 发放地址
     */
    private String provAddr;

    /**
     * 维护负责人
     */
    private String contact;

    /**
     * 安装位置
     */
    private String instAddr;

    /**
     * 设备状态：1-挂失 2-激活 2 故障 3 关闭
     */
    private String status;

    /**
     * 射频id
     */
    private String rfId;

    /**
     * 射频CH
     */
    private String ch;

    /**
     * 手机号
     */
    private String inMobile;

    /**
     * 创建时间
     */
    private Date crtDate;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.deviceId;
    }

    @Override
    public void setId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo == null ? null : deviceNo.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getGisId() {
        return gisId;
    }

    public void setGisId(String gisId) {
        this.gisId = gisId == null ? null : gisId.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode == null ? null : qrCode.trim();
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Date getProvDate() {
        return provDate;
    }

    public void setProvDate(Date provDate) {
        this.provDate = provDate;
    }

    public String getProvAddr() {
        return provAddr;
    }

    public void setProvAddr(String provAddr) {
        this.provAddr = provAddr == null ? null : provAddr.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getInstAddr() {
        return instAddr;
    }

    public void setInstAddr(String instAddr) {
        this.instAddr = instAddr == null ? null : instAddr.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId == null ? null : rfId.trim();
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch == null ? null : ch.trim();
    }

    public String getInMobile() {
        return inMobile;
    }

    public void setInMobile(String inMobile) {
        this.inMobile = inMobile == null ? null : inMobile.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceId=").append(deviceId);
        sb.append(", deviceNo=").append(deviceNo);
        sb.append(", typeId=").append(typeId);
        sb.append(", type=").append(type);
        sb.append(", gisId=").append(gisId);
        sb.append(", imei=").append(imei);
        sb.append(", sn=").append(sn);
        sb.append(", qrCode=").append(qrCode);
        sb.append(", mac=").append(mac);
        sb.append(", provDate=").append(provDate);
        sb.append(", provAddr=").append(provAddr);
        sb.append(", contact=").append(contact);
        sb.append(", instAddr=").append(instAddr);
        sb.append(", status=").append(status);
        sb.append(", rfId=").append(rfId);
        sb.append(", ch=").append(ch);
        sb.append(", inMobile=").append(inMobile);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}