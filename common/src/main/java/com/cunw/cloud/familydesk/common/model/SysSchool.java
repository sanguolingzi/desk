package com.cunw.cloud.familydesk.common.model;

import com.cunw.cloud.framework.model.IBasePO;
import java.util.Date;

public class SysSchool implements IBasePO<String> {
    private String schoolId;

    private String schoolCode;

    private String schoolName;

    /**
     * 维护学校对应的服务地址
     */
    private String url;

    /**
     * 1-启用 0-停用(删除)
     */
    private Integer status;

    private Date crtDate;

    /**
     * 学校地址
     */
    private String officeAddr;

    /**
     * 校长
     */
    private String master;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 学校分类：1-小学 2- 初中 3-高中 4-职高 5-大学
     */
    private String schoolType;

    /**
     * 省份
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    private static final long serialVersionUID = 1L;

    @Override
    public String getId() {
        return this.schoolId;
    }

    @Override
    public void setId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId == null ? null : schoolId.trim();
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode == null ? null : schoolCode.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public String getOfficeAddr() {
        return officeAddr;
    }

    public void setOfficeAddr(String officeAddr) {
        this.officeAddr = officeAddr == null ? null : officeAddr.trim();
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master == null ? null : master.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType == null ? null : schoolType.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", schoolId=").append(schoolId);
        sb.append(", schoolCode=").append(schoolCode);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", url=").append(url);
        sb.append(", status=").append(status);
        sb.append(", crtDate=").append(crtDate);
        sb.append(", officeAddr=").append(officeAddr);
        sb.append(", master=").append(master);
        sb.append(", contact=").append(contact);
        sb.append(", schoolType=").append(schoolType);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}