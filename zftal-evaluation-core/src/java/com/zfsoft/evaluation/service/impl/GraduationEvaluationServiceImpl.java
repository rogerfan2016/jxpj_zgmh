package com.zfsoft.evaluation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.evaluation.dao.daointerface.IGraduationEvaluationDao;
import com.zfsoft.evaluation.entity.GraduationEvaluationEntity;
import com.zfsoft.evaluation.entity.GraduationEvaluationIndex;
import com.zfsoft.evaluation.entity.GraduationEvaluationQuery;
import com.zfsoft.evaluation.entity.GraduationEvaluationResultEntity;
import com.zfsoft.evaluation.service.IGraduationEvaluationService;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;

/**
 * 
* @ClassName: FeedBackServiceImpl
* @Description: TODO(毕业评价Service实现类)
* @author rogerfan
* @date 2016-5-20 上午03:01:45
*
 */
public class GraduationEvaluationServiceImpl implements IGraduationEvaluationService {

    private IGraduationEvaluationDao graduationEvaluationDao;

	@Override
	public void saveGraduationEvaluation(GraduationEvaluationEntity entity) {
		graduationEvaluationDao.saveGraduationEvaluation(entity);
	}

	@Override
	public void modifyGraduationEvaluation(GraduationEvaluationEntity entity) {
		graduationEvaluationDao.modifyGraduationEvaluation(entity);
	}

	@Override
	public PageList<GraduationEvaluationEntity> getGraduationEvaluationList(
			GraduationEvaluationQuery query) {
		PageList<GraduationEvaluationEntity> pageList = new PageList<GraduationEvaluationEntity>();
        Paginator paginator = new Paginator();
        if (query != null) {
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(graduationEvaluationDao.getGraduationEvaluationCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(graduationEvaluationDao.getGraduationEvaluationList(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<Map<String, Object>> summartyGraduationEvaluationResult(
			GraduationEvaluationQuery query) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 如果评价指标内容是课程
		if(GraduationEvaluationIndex.ZBX_HARDLESSON.getKey().equals(query.getZbx())
				|| GraduationEvaluationIndex.ZBX_HARVESTLESSON.getKey().equals(query.getZbx())){
			list = graduationEvaluationDao.summaryLessonResult(query);
		}
		// 如果评价指标内容是教师
		if(GraduationEvaluationIndex.ZBX_TEACHER.getKey().equals(query.getZbx())){
			list = graduationEvaluationDao.summaryTeacherResult(query);
		}
		// 如果评价指标是单选题
		if(GraduationEvaluationIndex.ZBX_MAJOR.getKey().equals(query.getZbx())
				|| GraduationEvaluationIndex.ZBX_STUDY.getKey().equals(query.getZbx())){
			list = graduationEvaluationDao.summaryRadioResult(query);
		}
		PageList<Map<String, Object>> pageList = new PageList<Map<String, Object>>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(list.size());
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				pageList.addAll(list);
			}
		}
		return pageList;
	}

	@Override
	public GraduationEvaluationEntity getGraduationEvaluationByXh(String xh) {
		return graduationEvaluationDao.getGraduationEvaluationByXh(xh);
	}

	@Override
	public List<GraduationEvaluationResultEntity> getGraduationEvaluationResultByPjid(
			String pjid) {
		return graduationEvaluationDao.getGraduationEvaluationResultByPjid(pjid);
	}

	@Override
	public GraduationEvaluationEntity getGraduationSetting(String xz) {
		return graduationEvaluationDao.getGraduationSetting(xz);
	}

	@Override
	public void modifyGraduationSetting(GraduationEvaluationEntity entity) {
		graduationEvaluationDao.deleteGraduationSetting(entity.getXz());
		graduationEvaluationDao.saveGraduationSetting(entity);
	}
	
	@Override
	public void modifyGraduationEvaluationResult(GraduationEvaluationResultEntity result) {
		// 先删除所有的评价结果记录
		graduationEvaluationDao.deleteGraduationEvaluationResultByPjid(result.getPjid());
		// 再插入所有提交的评价记录
		GraduationEvaluationResultEntity entity = new GraduationEvaluationResultEntity();
		entity.setPjid(result.getPjid());
		// 添加意见和建议评价结果
		if(StringUtil.isNotEmpty(result.getOpinion())){
			entity.setDa(result.getOpinion());
			entity.setZbx(GraduationEvaluationIndex.ZBX_OPINION.getKey());
			graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
		// 添加专业认可度评价结果
		if(StringUtil.isNotEmpty(result.getMajor())){
			entity.setDa(result.getMajor());
			entity.setZbx(GraduationEvaluationIndex.ZBX_MAJOR.getKey());
			graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
		// 添加学习满意度评价结果
		if(StringUtil.isNotEmpty(result.getStudy())){
			entity.setDa(result.getStudy());
			entity.setZbx(GraduationEvaluationIndex.ZBX_STUDY.getKey());
			graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
		// 添加最喜欢的老师评价结果
		for(String teacher : result.getTeacher()){
			entity.setDa(teacher);
			entity.setZbx(GraduationEvaluationIndex.ZBX_TEACHER.getKey());
			graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
		// 添加收获最大的课程评价结果
		for(String hl : result.getHarvestLesson()){
			entity.setDa(hl);
			entity.setZbx(GraduationEvaluationIndex.ZBX_HARVESTLESSON.getKey());
			graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
		// 添加难度最大的课程评价结果
		for(String hard : result.getHardLesson()){
			entity.setDa(hard);
			entity.setZbx(GraduationEvaluationIndex.ZBX_HARDLESSON.getKey());
			graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
	}

	@Override
	public boolean isGraduate(String xh) {
		boolean result = false;
		int count = graduationEvaluationDao.isGraduate(xh);
		if(count > 0){
			result = true;
		}
		return result;
	}

	@Override
	public String isEvaluation(String userId) {
		String message = "";
		int count = graduationEvaluationDao.isGraduate(userId);
		// 是否是毕业生
		if(count <= 0){
			message = "您还没有毕业，请再等等吧！";
			return message;
		}
		GraduationEvaluationEntity entity = graduationEvaluationDao.getGraduationSetting(graduationEvaluationDao.getXzByXh(userId));
		if(entity == null){
			message = "评价未开始！";
			return message;
		}else {
			String kssj = entity.getKssj();
			String jssj = entity.getJssj();
			if(DateTimeUtil.compare_date(kssj, DateTimeUtil.getCurrDateStr()) > 0){
				message = "评价未开始，开始时间：" + kssj;
				return message;
			}
			if(DateTimeUtil.compare_date(jssj, DateTimeUtil.getCurrDateStr()) < 0){
				message = "评价已结束，结束时间：" + jssj;
				return message;
			}
			GraduationEvaluationEntity model = graduationEvaluationDao.getGraduationEvaluationByXh(userId);
			if(model != null && "1".equals(model.getZt())){
				message = "您已提交评价！";
				return message;
			}
		}
		return message;
	}
	
	@Override
	public void addGraduationEvaluationResult(Map<String,String> param) {
		String pjid = param.get("pjid");
		String teachers = param.get("teachers");
		String harvestLessons = param.get("harvestLessons");
		String hardLessons = param.get("hardLessons");
		String study = param.get("study");
		String major = param.get("major");
		String opinion = param.get("opinion");
		String opinion1 = param.get("opinion1");
		String opinion2 = param.get("opinion2");
		String opinion3 = param.get("opinion3");
		//大学最喜欢的五位老师
    	for (String teacher : teachers.split(",")) {
    		GraduationEvaluationResultEntity entity = new GraduationEvaluationResultEntity();
    		entity.setPjid(pjid);
        	entity.setZbx(GraduationEvaluationIndex.ZBX_TEACHER.getKey());
        	entity.setDa(teacher);
        	graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
    	//收获最大的五门课
    	for (String harvestLession : harvestLessons.split(",")) {
    		GraduationEvaluationResultEntity entity = new GraduationEvaluationResultEntity();
    		entity.setPjid(pjid);
        	entity.setZbx(GraduationEvaluationIndex.ZBX_HARVESTLESSON.getKey());
        	entity.setDa(harvestLession);
        	graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
    	//最难掌握的五门课
    	for (String hardLession : hardLessons.split(",")) {
    		GraduationEvaluationResultEntity entity = new GraduationEvaluationResultEntity();
    		entity.setPjid(pjid);
        	entity.setZbx(GraduationEvaluationIndex.ZBX_HARDLESSON.getKey());
        	entity.setDa(hardLession);
        	graduationEvaluationDao.addGraduationEvaluationResult(entity);
		}
    	//对大学学习的满意程度
    	GraduationEvaluationResultEntity studyResultEntity = new GraduationEvaluationResultEntity();
    	studyResultEntity.setPjid(pjid);
    	studyResultEntity.setZbx(GraduationEvaluationIndex.ZBX_STUDY.getKey());
    	studyResultEntity.setDa(study);
    	graduationEvaluationDao.addGraduationEvaluationResult(studyResultEntity);
    	//对大学专业认可程度
    	GraduationEvaluationResultEntity marjorResultEntity = new GraduationEvaluationResultEntity();
    	marjorResultEntity.setPjid(pjid);
    	marjorResultEntity.setZbx(GraduationEvaluationIndex.ZBX_MAJOR.getKey());
    	marjorResultEntity.setDa(major);
    	graduationEvaluationDao.addGraduationEvaluationResult(marjorResultEntity);
    	//对母校说的话
    	GraduationEvaluationResultEntity opinionResultEntity = new GraduationEvaluationResultEntity();
    	opinionResultEntity.setPjid(pjid);
    	opinionResultEntity.setZbx(GraduationEvaluationIndex.ZBX_OPINION.getKey());
    	opinionResultEntity.setDa(opinion);
		graduationEvaluationDao.addGraduationEvaluationResult(opinionResultEntity);
		//对学院说的话
    	opinionResultEntity.setZbx(GraduationEvaluationIndex.ZBX_OPINION_1.getKey());
    	opinionResultEntity.setDa(opinion1);
		graduationEvaluationDao.addGraduationEvaluationResult(opinionResultEntity);
		//对专业说的话
    	opinionResultEntity.setZbx(GraduationEvaluationIndex.ZBX_OPINION_2.getKey());
    	opinionResultEntity.setDa(opinion2);
		graduationEvaluationDao.addGraduationEvaluationResult(opinionResultEntity);
		//对教师说的话
    	opinionResultEntity.setZbx(GraduationEvaluationIndex.ZBX_OPINION_3.getKey());
    	opinionResultEntity.setDa(opinion3);
		graduationEvaluationDao.addGraduationEvaluationResult(opinionResultEntity);
	}

	@Override
	public List<GraduationEvaluationEntity> getLessonListByXh(String xh) {
		return graduationEvaluationDao.getLessonListByXh(xh);
	}

	@Override
	public List<GraduationEvaluationEntity> getTeacherByXh(String xh) {
		return graduationEvaluationDao.getTeacherByXh(xh);
	}

	//=====================================================================================================
	public void setGraduationEvaluationDao(
			IGraduationEvaluationDao graduationEvaluationDao) {
		this.graduationEvaluationDao = graduationEvaluationDao;
	}

}
