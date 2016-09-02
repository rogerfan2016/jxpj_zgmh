package com.zfsoft.feedback.entity;

import java.util.Date;
import java.util.List;

/**
 * 
* @ClassName: FeedBackEntity
* @Description: TODO(信息反馈实体类)
* @author rogerfan
* @date 2016-6-13 下午12:06:53
*
 */
public class FeedBackEntity {
	
	// 学年
	private String xn;
	// 学期
	private String xq;
	// 信息处理日志对象集合
	private List<FeedBackLogEntity> feedBackLogList;
	// 信息ID
	private String xxid;
	// 信息分类(0学生类1课程类2教师类3教学环境保障类)
	private String xxfl;
	// 信息类型 (0普通1紧急)
	private String xxlx;
	// 信息内容类型(0表扬1意见/建议2紧急事件)
	private String xxnrlx;
	// 信息内容
	private String xxnr;
	// 课程代码
	private String kcdm;
	// 课程名称
	private String kcmc;
	// 开课学院
	private String kkxy;
	// 授课教师工号
	private String skjsgh;
	// 授课教师姓名
	private String skjsxm;
	// 教师所在单位
	private String jsszdw;
	// 状态(0已提交1处理中2已反馈3已评价)
	private String zt;
	// 创建人
	private String cjr;
	// 创建时间
	private Date cjsj;
	// 处理环节(0单位管理员筛选1责任人处理2责任人反馈3单位管理员反馈4信息员评价)
	private String clhj;
	// 处理人
	private String clr;
	// 反馈结果
	private String fkjg;
	// 评价结果
	private String pjjg;
	// 评价时间
	private Date pjsj;
	// 学生所在学院
	private String xsszxy;
	

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
	public List<FeedBackLogEntity> getFeedBackLogList() {
		return feedBackLogList;
	}
	public void setFeedBackLogList(List<FeedBackLogEntity> feedBackLogList) {
		this.feedBackLogList = feedBackLogList;
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
	public String getXxnr() {
		return xxnr;
	}
	public void setXxnr(String xxnr) {
		this.xxnr = xxnr;
	}
	public String getKcdm() {
		return kcdm;
	}
	public void setKcdm(String kcdm) {
		this.kcdm = kcdm;
	}
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public String getKkxy() {
		return kkxy;
	}
	public void setKkxy(String kkxy) {
		this.kkxy = kkxy;
	}
	public String getSkjsgh() {
		return skjsgh;
	}
	public void setSkjsgh(String skjsgh) {
		this.skjsgh = skjsgh;
	}
	public String getSkjsxm() {
		return skjsxm;
	}
	public void setSkjsxm(String skjsxm) {
		this.skjsxm = skjsxm;
	}
	public String getJsszdw() {
		return jsszdw;
	}
	public void setJsszdw(String jsszdw) {
		this.jsszdw = jsszdw;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
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
	public String getFkjg() {
		return fkjg;
	}
	public void setFkjg(String fkjg) {
		this.fkjg = fkjg;
	}
	public String getPjjg() {
		return pjjg;
	}
	public void setPjjg(String pjjg) {
		this.pjjg = pjjg;
	}
	public Date getPjsj() {
		return pjsj;
	}
	public void setPjsj(Date pjsj) {
		this.pjsj = pjsj;
	}
	public String getXsszxy() {
		return xsszxy;
	}
	public void setXsszxy(String xsszxy) {
		this.xsszxy = xsszxy;
	}
	
}
