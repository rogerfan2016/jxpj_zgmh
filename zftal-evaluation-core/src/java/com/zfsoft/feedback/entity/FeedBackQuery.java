package com.zfsoft.feedback.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
* @ClassName: FeedBackQuery
* @Description: TODO(信息反馈查询实体类)
* @author rogerfan
* @date 2016-6-13 下午12:21:31
*
 */
public class FeedBackQuery extends BaseQuery{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

	// 学年
	private String xn;
	// 学期
	private String xq;
	// 学号
	private String xh;
	// 学生姓名
	private String xm;
	// 学院
	private String xy;
	// 专业
	private String zymc;
	// 行政班
	private String xzb;
	// 状态
	private String zt;
	// 过滤条件
	private String condition;
	// 信息ID
	private String xxid;
	// 信息分类(0学生类1课程类2教师类3教学环境保障类)
	private String xxfl;
	// 信息类型 (0普通1紧急)
	private String xxlx;
	// 信息内容类型(0表扬1意见/建议2紧急事件)
	private String xxnrlx;
	// 处理环节(0单位管理员筛选1责任人处理2责任人反馈3单位管理员反馈4信息员评价)
	private String clhj;
	// 处理人
	private String clr;
	//教师所在单位
	private String jsszdw; 
	//操作人所在单位
	private String czrszdw;
	//操作人所在单位编码
	private String czrszdwbm;
	
	
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getXxid() {
		return xxid;
	}
	public void setXxid(String xxid) {
		this.xxid = xxid;
	}
	public String getXxfl() {
		return xxfl;
	}
	public void setXxfl(String xxfl) {
		this.xxfl = xxfl;
	}
	public String getXxlx() {
		return xxlx;
	}
	public void setXxlx(String xxlx) {
		this.xxlx = xxlx;
	}
	public String getXxnrlx() {
		return xxnrlx;
	}
	public void setXxnrlx(String xxnrlx) {
		this.xxnrlx = xxnrlx;
	}
	public String getClhj() {
		return clhj;
	}
	public void setClhj(String clhj) {
		this.clhj = clhj;
	}
	public String getClr() {
		return clr;
	}
	public void setClr(String clr) {
		this.clr = clr;
	}
	public String getJsszdw() {
		return jsszdw;
	}
	public void setJsszdw(String jsszdw) {
		this.jsszdw = jsszdw;
	}
	public String getCzrszdw() {
		return czrszdw;
	}
	public void setCzrszdw(String czrszdw) {
		this.czrszdw = czrszdw;
	}
	public String getCzrszdwbm() {
		return czrszdwbm;
	}
	public void setCzrszdwbm(String czrszdwbm) {
		this.czrszdwbm = czrszdwbm;
	}
}
