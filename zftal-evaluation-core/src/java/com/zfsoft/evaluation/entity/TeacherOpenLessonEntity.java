package com.zfsoft.evaluation.entity;

import java.util.Date;


/**
 * 
 * @author Administrator
 *
 */
public class TeacherOpenLessonEntity {

    // 星期
    private String dayofweek;
    // 年度
    private String schoolyear;
    // 学期
    private String semester;
    // 课程信息
    private CurriculumScheduleEntity curriculum;
    // 审核状态（0未提交、1待审核、2审核通过、3审核不通过）
    private String shzt;
    // 备注说明
    private String bzsm;
    // 评教ID
    private String pjid;
    // 全局ID
    private String globalid;
    // 上课时间
    private String sksj;
    // 课程ID
    private String kcid;
    // 听课教师ID
    private String tkjsid;
    // 听课老师姓名
    private String tkjsxm;
    // 授课教师所在部门
    private String dept;
    // 评教情况
    private String pjqk;
    // 新问卷ID
    private String xwjid;
    
    // 听课类型（dept院级；school校级）
    private String tklx;
    // 审核人
    private String shr;
    // 开课学院
    private String kkxy;
    // 审核时间
    private Date shsj;
    
    /**
     * @return the dayofweek
     */
    public String getDayofweek() {
        return dayofweek;
    }

    /**
     * @param dayofweek the dayofweek to set
     */
    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    /**
     * @return the curriculum
     */
    public CurriculumScheduleEntity getCurriculum() {
        return curriculum;
    }

    /**
     * @param curriculum the curriculum to set
     */
    public void setCurriculum(CurriculumScheduleEntity curriculum) {
        this.curriculum = curriculum;
    }

    /**
     * @return the shzt
     */
    public String getShzt() {
        return shzt;
    }

    /**
     * @param shzt the shzt to set
     */
    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    /**
     * @return the bzsm
     */
    public String getBzsm() {
        return bzsm;
    }

    /**
     * @param bzsm the bzsm to set
     */
    public void setBzsm(String bzsm) {
        this.bzsm = bzsm;
    }

    /**
     * @return the pjid
     */
    public String getPjid() {
        return pjid;
    }

    /**
     * @param pjid the pjid to set
     */
    public void setPjid(String pjid) {
        this.pjid = pjid;
    }

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
     * @return the sksj
     */
    public String getSksj() {
        return sksj;
    }

    /**
     * @param sksj the sksj to set
     */
    public void setSksj(String sksj) {
        this.sksj = sksj;
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
     * @return the tkjsid
     */
    public String getTkjsid() {
        return tkjsid;
    }

    /**
     * @param tkjsid the tkjsid to set
     */
    public void setTkjsid(String tkjsid) {
        this.tkjsid = tkjsid;
    }

    /**
     * @return the schoolyear
     */
    public String getSchoolyear() {
        return schoolyear;
    }

    /**
     * @param schoolyear the schoolyear to set
     */
    public void setSchoolyear(String schoolyear) {
        this.schoolyear = schoolyear;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * @return the tkjsxm
     */
    public String getTkjsxm() {
        return tkjsxm;
    }

    /**
     * @param tkjsxm the tkjsxm to set
     */
    public void setTkjsxm(String tkjsxm) {
        this.tkjsxm = tkjsxm;
    }

    /**
     * @return the dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * @return the pjqk
     */
    public String getPjqk() {
        return pjqk;
    }

    /**
     * @param pjqk the pjqk to set
     */
    public void setPjqk(String pjqk) {
        this.pjqk = pjqk;
    }

	public String getXwjid() {
		return xwjid;
	}

	public void setXwjid(String xwjid) {
		this.xwjid = xwjid;
	}

	public String getKkxy() {
		return kkxy;
	}

	public void setKkxy(String kkxy) {
		this.kkxy = kkxy;
	}

	public String getTklx() {
		return tklx;
	}

	public void setTklx(String tklx) {
		this.tklx = tklx;
	}

	public String getShr() {
		return shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public Date getShsj() {
		return shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}
}
