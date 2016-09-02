package com.zfsoft.feedback.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 课程表
 * @author honghui
 *
 */
public class CourseQuery extends BaseQuery{
	
	private static final long serialVersionUID = 1L;
	
	private String globalid; //全局ID
	private String kcid;   //课程ID
	private String kcmc;   //课程名称
	private String kcdm;   //课程代码
	private String kkxy;   //开课学院
	private String skjsgh; //教师工号
	private String jsszdw; //教师所在单位
	private String skjsxm; //教师姓名
	
	public String getSkjsxm() {
		return skjsxm;
	}
	public void setSkjsxm(String skjsxm) {
		this.skjsxm = skjsxm;
	}
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
	public String getKcdm() {
		return kcdm;
	}
	public void setKcdm(String kcdm) {
		this.kcdm = kcdm;
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
	public String getJsszdw() {
		return jsszdw;
	}
	public void setJsszdw(String jsszdw) {
		this.jsszdw = jsszdw;
	}
	public String getGlobalid() {
		return globalid;
	}
	public void setGlobalid(String globalid) {
		this.globalid = globalid;
	}
	
}
