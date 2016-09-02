package com.zfsoft.evaluation.entity;

import com.zfsoft.dao.query.BaseQuery;

/**
 * 
* @ClassName: FeedBackQuery
* @Description: TODO(毕业评价查询实体类)
* @author rogerfan
* @date 2016-5-18 下午11:30:44
*
 */
public class GraduationEvaluationQuery extends BaseQuery{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

	// 学号
	private String xh;
	// 学生姓名
	private String xm;
	// 年级
	private String nj;
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
	
	// 指标项
	private String zbx;
	
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
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String getZbx() {
		return zbx;
	}
	public void setZbx(String zbx) {
		this.zbx = zbx;
	}
	
}
