package com.zfsoft.changeclass.entity;

import java.io.Serializable;

/**
 * 
* @ClassName: ChangeClassEntity
* @Description: TODO(临时调课实体类)
* @author rogerfan
* @date 2016-8-13 上午05:37:42
*
 */
public class ChangeClassEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	// 全局ID
    private String globalid;
    // 课程ID
    private String kcid;
    // 课程名称
    private String kcmc;
    // 课程时间
    private String kcsj;
    // 开始时间
    private String kssj;
    // 结束时间
    private String jssj;
    // 课程节次
    private String kcjc;
    // 任课老师工号
    private String rklsgh;
    // 任课老师
    private String rkls;
    // 上课地点
    private String skdd;
    // 单双周
    private String dsz;
    // 开课学院
    private String kkxy;

    /**
     * @return the globalid
     */
    public String getGlobalid() {
        return globalid;
    }

    /**
     * @param globalid the globalid to set
     */
    public void setGlobalid(String globalid) {
        this.globalid = globalid;
    }

    /**
     * @return the kcid
     */
    public String getKcid() {
        return kcid;
    }

    /**
     * @param kcid the kcid to set
     */
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }

    /**
     * @return the kcmc
     */
    public String getKcmc() {
        return kcmc;
    }

    /**
     * @param kcmc the kcmc to set
     */
    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    /**
     * @return the kcsj
     */
    public String getKcsj() {
        return kcsj;
    }

    /**
     * @param kcsj the kcsj to set
     */
    public void setKcsj(String kcsj) {
        this.kcsj = kcsj;
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

	/**
     * @return the kcjc
     */
    public String getKcjc() {
        return kcjc;
    }

    /**
     * @param kcjc the kcjc to set
     */
    public void setKcjc(String kcjc) {
        this.kcjc = kcjc;
    }

    /**
     * @return the rklsgh
     */
    public String getRklsgh() {
        return rklsgh;
    }

    /**
     * @param rklsgh the rklsgh to set
     */
    public void setRklsgh(String rklsgh) {
        this.rklsgh = rklsgh;
    }

    /**
     * @return the rkls
     */
    public String getRkls() {
        return rkls;
    }

    /**
     * @param rkls the rkls to set
     */
    public void setRkls(String rkls) {
        this.rkls = rkls;
    }

    /**
     * @return the skdd
     */
    public String getSkdd() {
        return skdd;
    }

    /**
     * @param skdd the skdd to set
     */
    public void setSkdd(String skdd) {
        this.skdd = skdd;
    }

	public String getDsz() {
		return dsz;
	}

	public void setDsz(String dsz) {
		this.dsz = dsz;
	}

	public String getKkxy() {
		return kkxy;
	}

	public void setKkxy(String kkxy) {
		this.kkxy = kkxy;
	}
    
}
