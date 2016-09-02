package com.zfsoft.wjdc_xc.query;

import java.util.Date;

import com.zfsoft.dao.query.BaseQuery;
import com.zfsoft.orcus.lang.TimeUtil;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-10
 * @version V1.0.0
 */
public class InspectionTaskQuery extends BaseQuery{
	
	private static final long serialVersionUID = -3590952749231554472L;
	
	private String id;
	private String wjText;
	private String configType;
	private String rwjb;
	private String rwbm;
	private String rwmc;
	private String taskDate;
	
	private Date start;
	
	private Date end;
	
	private String zt;
	
	private String xy;
	private String zy;
	private String xm;
	private String xzb;
	
	private String kcmc;
	private String kcjc;
	private String kkxy;
	
	private String xn;
	private String xq;
	
	private String condition;

	/**
	 * 返回
	 */
	public String getWjText() {
		return wjText;
	}

	/**
	 * 设置
	 * @param wjText 
	 */
	public void setWjText(String wjText) {
		this.wjText = wjText;
	}

	/**
	 * 返回
	 */
	public Date getStart() {
		return start;
	}

	public String getStartText() {
		if(start==null) return "";
		return TimeUtil.format(start, TimeUtil.yyyy_MM_dd);
	}
	/**
	 * 设置
	 * @param start 
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * 返回
	 */
	public Date getEnd() {
		return end;
	}
	
	public String getEndText() {
		if(end==null) return "";
		return TimeUtil.format(end, TimeUtil.yyyy_MM_dd);
	}

	/**
	 * 设置
	 * @param end 
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * 返回
	 */
	public String getConfigType() {
		return configType;
	}

	/**
	 * 设置
	 * @param configType 
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXzb() {
		return xzb;
	}

	public void setXzb(String xzb) {
		this.xzb = xzb;
	}

	public String getRwjb() {
		return rwjb;
	}

	public void setRwjb(String rwjb) {
		this.rwjb = rwjb;
	}

	public String getRwbm() {
		return rwbm;
	}

	public void setRwbm(String rwbm) {
		this.rwbm = rwbm;
	}

	public String getRwmc() {
		return rwmc;
	}

	public void setRwmc(String rwmc) {
		this.rwmc = rwmc;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public String getKcmc() {
		return kcmc;
	}

	public void setKcmc(String kcmc) {
		this.kcmc = kcmc;
	}

	public String getKcjc() {
		return kcjc;
	}

	public void setKcjc(String kcjc) {
		this.kcjc = kcjc;
	}

	public String getKkxy() {
		return kkxy;
	}

	public void setKkxy(String kkxy) {
		this.kkxy = kkxy;
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

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
