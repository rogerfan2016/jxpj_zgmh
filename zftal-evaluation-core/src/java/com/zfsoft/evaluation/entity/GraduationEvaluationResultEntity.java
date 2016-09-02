package com.zfsoft.evaluation.entity;


/**
 * 
* @ClassName: FeedBackEntity
* @Description: TODO(毕业评价结果实体类)
* @author rogerfan
* @date 2016-5-18 下午11:31:15
*
 */
public class GraduationEvaluationResultEntity {	
	// ID
	private String id;
	// 指标项
	private String zbx;
	// 评价ID
	private String pjid;
	// 答案
	private String da;
	
	// 最喜欢的教师
	private String[] teacher;
	// 收获最大课程
	private String[] harvestLesson;
	// 难度最大课程
	private String[] hardLesson;
	// 学习满意度
	private String study;
	// 专业认可度
	private String major;
	// 请写出你想对母校说的话
	private String opinion;
	// 请写出你想对学院说的话
	private String opinion1;
	// 请写出你想对专业说的话
	private String opinion2;
	// 请写出你想对教师说的话
	private String opinion3;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZbx() {
		return zbx;
	}
	public void setZbx(String zbx) {
		this.zbx = zbx;
	}
	public String getPjid() {
		return pjid;
	}
	public void setPjid(String pjid) {
		this.pjid = pjid;
	}
	public String getDa() {
		return da;
	}
	public void setDa(String da) {
		this.da = da;
	}
	public String[] getTeacher() {
		return teacher;
	}
	public void setTeacher(String[] teacher) {
		this.teacher = teacher;
	}
	public String[] getHarvestLesson() {
		return harvestLesson;
	}
	public void setHarvestLesson(String[] harvestLesson) {
		this.harvestLesson = harvestLesson;
	}
	public String[] getHardLesson() {
		return hardLesson;
	}
	public void setHardLesson(String[] hardLesson) {
		this.hardLesson = hardLesson;
	}
	public String getStudy() {
		return study;
	}
	public void setStudy(String study) {
		this.study = study;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getOpinion1() {
		return opinion1;
	}
	public void setOpinion1(String opinion1) {
		this.opinion1 = opinion1;
	}
	public String getOpinion2() {
		return opinion2;
	}
	public void setOpinion2(String opinion2) {
		this.opinion2 = opinion2;
	}
	public String getOpinion3() {
		return opinion3;
	}
	public void setOpinion3(String opinion3) {
		this.opinion3 = opinion3;
	}
	
}
