package com.zfsoft.baseData.entity;

import com.zfsoft.dao.query.BaseQuery;


/**
 * 
* @ClassName: ClassTypeEntity
* @Description: TODO(课程分类实体类)
* @author rogerfan
* @date 2016-6-18 上午08:23:14
*
 */
public class ClassTypeEntity extends BaseQuery{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;
	// 课程代码
    private String kcdm;
    // 课程中文名称
    private String kczwmc;
    // 分类代码
    private String fldm;
    // 分类代码2
    private String fldm2;
    // 三级单位
    private String sjdw;
    // 开课学院
    private String kkxy;
    
    
	public String getKcdm() {
		return kcdm;
	}
	public void setKcdm(String kcdm) {
		this.kcdm = kcdm;
	}
	public String getKczwmc() {
		return kczwmc;
	}
	public void setKczwmc(String kczwmc) {
		this.kczwmc = kczwmc;
	}
	public String getFldm() {
		return fldm;
	}
	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	public String getFldm2() {
		return fldm2;
	}
	public void setFldm2(String fldm2) {
		this.fldm2 = fldm2;
	}
	public String getSjdw() {
		return sjdw;
	}
	public void setSjdw(String sjdw) {
		this.sjdw = sjdw;
	}
	public String getKkxy() {
		return kkxy;
	}
	public void setKkxy(String kkxy) {
		this.kkxy = kkxy;
	}
	
}
