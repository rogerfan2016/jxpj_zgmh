package com.zfsoft.feedback.entity;

import com.zfsoft.dao.query.BaseQuery;


/**
 * 
* @ClassName: FeedBackStaffEntity
* @Description: TODO(信息员查询实体类)
* @author rogerfan
* @date 2016-6-13 下午12:03:11
*
 */
public class FeedBackStaffQuery extends BaseQuery{
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;
	// ID
	private String id;
	// 学号
	private String xh;
	// 所属组
	private String ssz;
	// 是否组长
	private String sfzz;
	// 状态(0删除；1正常)
	private String zt;
	
    // 姓名
	private String xm;
	// 性别
	private String xb;
	// 学院
	private String xy;
	// 专业
	private String zymc;
	// 行政班
	private String xzb;
	//编目编号
	private String ssbm;
	// 学年
	private String xn;
	// 学期
	private String xq;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSsz() {
		return ssz;
	}
	public void setSsz(String ssz) {
		this.ssz = ssz;
	}
	public String getSfzz() {
		return sfzz;
	}
	public void setSfzz(String sfzz) {
		this.sfzz = sfzz;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public String getXzb() {
		return xzb;
	}
	public void setXzb(String xzb) {
		this.xzb = xzb;
	}
	public String getSsbm() {
		return ssbm;
	}
	public void setSsbm(String ssbm) {
		this.ssbm = ssbm;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
}
