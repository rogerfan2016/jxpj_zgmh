package com.zfsoft.wjdc_xc.entites;

import java.util.Date;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public class InspectionConfig {
	private String key;
	private String value;
	private String append;
	private String type;
	private String wjid;
	private String person;
	private String object;
	private Date startTime;
	private Date endTime;
	private String status;
	private String remark;
	private String ywjb;
	private String ywbm;
	
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 返回
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置
	 * @param key 
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 返回
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置
	 * @param value 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 返回
	 */
	public String getAppend() {
		return append;
	}
	/**
	 * 设置
	 * @param append 
	 */
	public void setAppend(String append) {
		this.append = append;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWjid() {
		return wjid;
	}
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getYwjb() {
		return ywjb;
	}
	public void setYwjb(String ywjb) {
		this.ywjb = ywjb;
	}
	public String getYwbm() {
		return ywbm;
	}
	public void setYwbm(String ywbm) {
		this.ywbm = ywbm;
	}
	
}
