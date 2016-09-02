package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
 * @author Administrator
 *
 */
public class CurriculumScheduleQuery extends BaseQuery{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

    // 课程ID
    private String kcid;
    // 课程名称
    private String kcmc;
    // 课程时间
    private String kcsj;
    // 课程节次
    private String kcjc;
    // 任课老师工号
    private String rklsgh;
    // 上课地点
    private String skdd;
    // 开课学院
    private String kkxy;
    // 用户ID
    private String userid;
    // 用户类型
    private String userlx;
    // 条件表达式
    private String condition;
    // 开始时间
    private String firstDay;
    // 结束时间
    private String lastDay;
    // 节次数
    private String jcs;
    
	public String getKcid() {
		return kcid;
	}
	public void setKcid(String kcid) {
		this.kcid = kcid;
	}
	public String getKcmc() {
		return kcmc;
	}
	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}
	public String getKcsj() {
		return kcsj;
	}
	public void setKcsj(String kcsj) {
		this.kcsj = kcsj;
	}
	public String getKcjc() {
		return kcjc;
	}
	public void setKcjc(String kcjc) {
		this.kcjc = kcjc;
	}
	public String getRklsgh() {
		return rklsgh;
	}
	public void setRklsgh(String rklsgh) {
		this.rklsgh = rklsgh;
	}
	public String getSkdd() {
		return skdd;
	}
	public void setSkdd(String skdd) {
		this.skdd = skdd;
	}
	public String getKkxy() {
		return kkxy;
	}
	public void setKkxy(String kkxy) {
		this.kkxy = kkxy;
	}
	public String getUserlx() {
		return userlx;
	}
	public void setUserlx(String userlx) {
		this.userlx = userlx;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getFirstDay() {
		return firstDay;
	}
	public void setFirstDay(String firstDay) {
		this.firstDay = firstDay;
	}
	public String getLastDay() {
		return lastDay;
	}
	public void setLastDay(String lastDay) {
		this.lastDay = lastDay;
	}
	public String getJcs() {
		return jcs;
	}
	public void setJcs(String jcs) {
		this.jcs = jcs;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}
