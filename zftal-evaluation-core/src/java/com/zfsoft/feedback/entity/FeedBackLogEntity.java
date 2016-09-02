package com.zfsoft.feedback.entity;

import java.util.Date;

/**
 * 
* @ClassName: FeedBackLogEntity
* @Description: TODO(反馈处理日志实体)
* @author rogerfan
* @date 2016-6-13 下午12:17:30
*
 */
public class FeedBackLogEntity {
	// 日志ID
	private String rzid;
	// 信息ID
	private String xxid;
	// 日志内容
	private String rznr;
	// 操作人
	private String czr;
	// 操作时间
	private Date czsj;
	// 处理是否超时
	private String sfcs;
	//处理环节(0单位管理员处理1责任人/单位处理2单位管理员反馈3信息员评价4完成)
	private String clhj;
	
	
	public String getRzid() {
		return rzid;
	}
	public void setRzid(String rzid) {
		this.rzid = rzid;
	}
	public String getXxid() {
		return xxid;
	}
	public void setXxid(String xxid) {
		this.xxid = xxid;
	}
	public String getRznr() {
		return rznr;
	}
	public void setRznr(String rznr) {
		this.rznr = rznr;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public Date getCzsj() {
		return czsj;
	}
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	public String getSfcs() {
		return sfcs;
	}
	public void setSfcs(String sfcs) {
		this.sfcs = sfcs;
	}
	public String getClhj() {
		return clhj;
	}
	public void setClhj(String clhj) {
		this.clhj = clhj;
	}
}
