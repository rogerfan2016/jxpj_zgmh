package com.zfsoft.evaluation.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.evaluation.entity.GraduationEvaluationEntity;
import com.zfsoft.evaluation.entity.GraduationEvaluationIndex;
import com.zfsoft.evaluation.entity.GraduationEvaluationQuery;
import com.zfsoft.evaluation.entity.GraduationEvaluationResultEntity;
import com.zfsoft.evaluation.service.IGraduationEvaluationService;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.util.base.StringUtil;

/**
 * 
* @ClassName: MobileGraduationAction
* @Description: TODO(手机端-毕业评价ACTION类)
* @author rogerfan
* @date 2016-5-20 上午06:13:31
*
 */
public class MobileGraduationAction extends HrmAction {

    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 1L;

	private IGraduationEvaluationService graduationEvaluationService;
	private GraduationEvaluationQuery query;
	private GraduationEvaluationEntity model;
	private GraduationEvaluationResultEntity resultModel;
	private List<GraduationEvaluationResultEntity> resultList;
	private String msg;
	//毕业结果评论
	private String teachers = "";
    private String harvestLessons = "";
    private String hardLessons = "";
    private String study = "";
    private String major = "";
    private String opinion = "";
    private String opinion1 = "";
    private String opinion2 = "";
    private String opinion3 = "";
    private String pjid;
        
    /*****************************Action****************************************/
    
    /**
     * 我的评价
     * @return
     */
    public String myEvaluation() {
    	String userId = getUser().getYhm();
    	// 判断是不是毕业生
    	if(graduationEvaluationService.isGraduate(userId)){
    		model = graduationEvaluationService.getGraduationEvaluationByXh(userId);
            // 如果不存在评价记录就创建
            if(model == null){
            	model = new GraduationEvaluationEntity();
            	model.setXh(userId);
            	graduationEvaluationService.saveGraduationEvaluation(model);
            }else{
            	resultList = graduationEvaluationService.getGraduationEvaluationResultByPjid(model.getId());
            	if(resultList != null && resultList.size() > 0){
            		for(GraduationEvaluationResultEntity result : resultList){
                		// 喜欢的老师
                		if(GraduationEvaluationIndex.ZBX_TEACHER.getKey().equals(result.getZbx())){
                			teachers += result.getDa() + "、";
                			continue;
                		}
                		// 最难的课程
                		if(GraduationEvaluationIndex.ZBX_HARDLESSON.getKey().equals(result.getZbx())){
                			hardLessons += result.getDa() + "、";
                			continue;
                		}
                		// 最有收获的课程
                		if(GraduationEvaluationIndex.ZBX_HARVESTLESSON.getKey().equals(result.getZbx())){
                			harvestLessons += result.getDa() + "、";
                			continue;
                		}
                		// 学习满意度
                		if(GraduationEvaluationIndex.ZBX_STUDY.getKey().equals(result.getZbx())){
                			study += result.getDa();
                			continue;
                		}
                		// 专业认可度
                		if(GraduationEvaluationIndex.ZBX_MAJOR.getKey().equals(result.getZbx())){
                			major += result.getDa();
                			continue;
                		}
                		// 对母校说的话
                		if(GraduationEvaluationIndex.ZBX_OPINION.getKey().equals(result.getZbx())){
                			opinion += result.getDa();
                			continue;
                		}
                		// 对学院说的话
                		if(GraduationEvaluationIndex.ZBX_OPINION_1.getKey().equals(result.getZbx())){
                			opinion1 += result.getDa();
                			continue;
                		}
                		// 对专业说的话
                		if(GraduationEvaluationIndex.ZBX_OPINION_2.getKey().equals(result.getZbx())){
                			opinion2 += result.getDa();
                			continue;
                		}
                		// 对教师说的话
                		if(GraduationEvaluationIndex.ZBX_OPINION_3.getKey().equals(result.getZbx())){
                			opinion3 += result.getDa();
                			continue;
                		}
                	}
            	}
            }
    	}
    	
        getValueStack().set("massage", graduationEvaluationService.isEvaluation(userId));
        getValueStack().set("lessonList", graduationEvaluationService.getLessonListByXh(userId));
        getValueStack().set("teacherList", graduationEvaluationService.getTeacherByXh(userId));
        return "myEvaluation";
    }
    
    /**
     * 提交毕业评价结果
     * @return
     */
    public String saveMyEvaluation(){
    	Map<String,String> param = new HashMap<String,String>();
    	param.put("pjid", pjid);
    	param.put("teachers", teachers);
    	param.put("harvestLessons", harvestLessons);
    	param.put("hardLessons", hardLessons);
    	param.put("study", study);
    	param.put("major", major);
    	param.put("opinion", (StringUtil.isEmpty(opinion)?"无":opinion));
    	param.put("opinion1", (StringUtil.isEmpty(opinion1)?"无":opinion1));
    	param.put("opinion2", (StringUtil.isEmpty(opinion2)?"无":opinion2));
    	param.put("opinion3", (StringUtil.isEmpty(opinion3)?"无":opinion3));
    	// 保存评价结果
    	graduationEvaluationService.addGraduationEvaluationResult(param);
    	// 修改评价状态
    	model = new GraduationEvaluationEntity();  
    	model.setId(pjid);
    	model.setZt("1");
    	graduationEvaluationService.modifyGraduationEvaluation(model);
    	getValueStack().set(DATA, getMessage());
    	return DATA;
    }

    /************************************注入************************************/
    
	public IGraduationEvaluationService getGraduationEvaluationService() {
		return graduationEvaluationService;
	}

	public void setGraduationEvaluationService(
			IGraduationEvaluationService graduationEvaluationService) {
		this.graduationEvaluationService = graduationEvaluationService;
	}

	public GraduationEvaluationQuery getQuery() {
		return query;
	}

	public void setQuery(GraduationEvaluationQuery query) {
		this.query = query;
	}

	public GraduationEvaluationResultEntity getResultModel() {
		return resultModel;
	}

	public void setResultModel(GraduationEvaluationResultEntity resultModel) {
		this.resultModel = resultModel;
	}

	public List<GraduationEvaluationResultEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<GraduationEvaluationResultEntity> resultList) {
		this.resultList = resultList;
	}

	public GraduationEvaluationEntity getModel() {
		return model;
	}

	public void setModel(GraduationEvaluationEntity model) {
		this.model = model;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTeachers() {
		return teachers;
	}

	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}

	public String getHarvestLessons() {
		return harvestLessons;
	}

	public void setHarvestLessons(String harvestLessons) {
		this.harvestLessons = harvestLessons;
	}

	public String getHardLessons() {
		return hardLessons;
	}

	public void setHardLessons(String hardLessons) {
		this.hardLessons = hardLessons;
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

	public String getPjid() {
		return pjid;
	}

	public void setPjid(String pjid) {
		this.pjid = pjid;
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
