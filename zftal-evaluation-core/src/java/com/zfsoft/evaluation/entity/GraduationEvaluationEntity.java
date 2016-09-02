package com.zfsoft.evaluation.entity;

import java.util.Date;

/**
 * 
* @ClassName: FeedBackEntity
* @Description: TODO(毕业评价实体类)
* @author rogerfan
* @date 2016-5-18 下午11:31:15
*
 */
public class GraduationEvaluationEntity {
	
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
	// 层次
	private String cc;
    /**=========评价设置============*/
    // 课程编号
	private String kcdm;
	// 课程名称
	private String kcmc;
	// 教师职工号
	private String jszgh;
	// 教师姓名
	private String jsxm;
	/**=========评价设置============*/
	// 开始时间
	private String kssj;
	// 结束时间
	private String jssj;
	// 学制
	private String xz;
	/**=========评价管理============*/	
	// 评价ID
	private String id;
	// 状态(0未评价；1已评价；2无效)
	private String zt;
	// 学号
	private String xh;
	// 评价时间
	private Date pjsj;
	
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
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public Date getPjsj() {
		return pjsj;
	}
	public void setPjsj(Date pjsj) {
		this.pjsj = pjsj;
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
	public String getJszgh() {
		return jszgh;
	}
	public void setJszgh(String jszgh) {
		this.jszgh = jszgh;
	}
	public String getJsxm() {
		return jsxm;
	}
	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
	}

}
