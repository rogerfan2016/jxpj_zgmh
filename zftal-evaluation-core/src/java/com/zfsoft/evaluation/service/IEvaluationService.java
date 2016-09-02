package com.zfsoft.evaluation.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.CheckInEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyQuery;
import com.zfsoft.evaluation.entity.CurriculumScheduleEntity;
import com.zfsoft.evaluation.entity.CurriculumScheduleQuery;
import com.zfsoft.evaluation.entity.OpenLessonSettingEntity;
import com.zfsoft.evaluation.entity.OpinionEntity;
import com.zfsoft.evaluation.entity.OpinionQuery;
import com.zfsoft.evaluation.entity.SettingEntity;
import com.zfsoft.evaluation.entity.SettingQuery;
import com.zfsoft.evaluation.entity.TeacherOpenLessonEntity;
import com.zfsoft.evaluation.entity.TeacherOpenLessonQuery;
import com.zfsoft.evaluation.entity.TeachingEntity;
import com.zfsoft.evaluation.entity.ViewQuestionnaireEntity;

/**
 * 
 * @author Administrator
 *
 */
public interface IEvaluationService {
	
    /**
     * 根据条件查询课表列表 
     * @param param
     * @return
     */
    public PageList<CurriculumScheduleEntity> getCurriculumScheduleList(CurriculumScheduleQuery query);
    
    /**
     * 查询今日课表列表 
     * @param param
     * @return
     */
    public List<CurriculumScheduleEntity> getTodayCurriculum(CurriculumScheduleQuery query);
    
    /**
     * 查询教师今日听课课表列表 
     * @param param
     * @return
     */
    public List<CurriculumScheduleEntity> getTodayListenCurriculum(CurriculumScheduleQuery query);
    
    /**
     * 
    * @Title: getXsEvaluation 
    * @Description: TODO(通过 评价ID查询学生评价记录) 
    * @param @param pjid
    * @param @return    设定文件 
    * @return ViewQuestionnaireEntity    返回类型 
    * @throws
     */
    public ViewQuestionnaireEntity getXsEvaluation(String pjid);
    
    /**
     * 
    * @Title: getJsEvaluation 
    * @Description: TODO(通过 评价ID查询教师评价记录) 
    * @param @param pjid
    * @param @return    设定文件 
    * @return ViewQuestionnaireEntity    返回类型 
    * @throws
     */
    public ViewQuestionnaireEntity getJsEvaluation(String pjid);

	/**
     * 获取教学周
     * @param dateStr
     * @return
     */
    public int getTeachingWeek(String dateStr);
    /**
     * 查询课表
     * @param userlx
     * @param userid
     * @return
     */
    public CurriculumScheduleEntity[][] getCurriculumSchedule(Map<String, Object> param);
    
    /**
     * 查询课表
     * @param param
     * @return
     */
    public CurriculumScheduleEntity[] getCurriculumScheduleByDay(Map<String, Object> param);

    /**
     * 查询课表
     * @param globalid
     * @return
     */
    public TeachingEntity getTeachingById(String globalid);

    /**
     * 保存教学日志
     * @param teachingEntity
     */
    public void saveTeachingLog(TeachingEntity teachingEntity);

    /**
     * 考勤录入
     * @param teachingEntity
     */
    public void saveCheckIn(TeachingEntity teachingEntity, String type);

    /**
     * 发起评教-生成学生评价记录
     * @param globalid
     */
    public void sendStudentEvaluation(TeachingEntity teachingEntity);
    
    /**
     * 生成单条学生评价记录
     * @param globalid
     */
    public void sendOneStudentEvaluation(TeachingEntity teachingEntity, String userId);
    
    /**
     * 发起评教-生成听课教师评价记录
     * @param globalid
     */
    public void sendTeacherEvaluation(TeachingEntity teachingEntity);
    
    /**
     * 生成单条听课教师评价记录
     * @param globalid
     */
    public void sendOneTeacherEvaluation(TeachingEntity teachingEntity, String userId);
    
    /**
     * 上课考勤管理
     * @param query
     * @return
     */
    public PageList<CheckInSurveyEntity> getCheckInSurveyEntities(CheckInSurveyQuery query);

    /**
     * 查询考勤和评教
     * @param param
     * @return
     */
    public List<CheckInEntity> getEvaluationDetail(Map<String, String> param);
    
    /**
     * 按学院统计考勤
     * @param globalid
     * @return
     */
    public PageList<CheckInSurveyEntity> getCheckInByCollege(CheckInSurveyQuery query);

    /**
     * 查询学生考勤列表
     * @param query
     * @return
     */
    public PageList<CheckInSurveyEntity> getStudentAttendanceList(CheckInSurveyQuery query);
    
    /**
     * 按班级课程统计缺勤学生
     * @param query
     * @return
     */
    public PageList<CheckInSurveyEntity> getSummaryAbsentListByClass(CheckInSurveyQuery query);
    
    /**
     * 按班级统计学生考勤
     * @param query
     * @return
     */
    public PageList<CheckInSurveyEntity> getCheckSummaryListByClass(CheckInSurveyQuery query);
    
    /**
     * 按年级统计学生考勤
     * @param query
     * @return
     */
    public PageList<CheckInSurveyEntity> getCheckSummaryListByGrade(CheckInSurveyQuery query);
    
    /**
     * 学院名称列表
     */
    public List<String> getCollegeList();

    /**
     * 教学日志查询
     * @param query
     * @return
     */
    public PageList<CheckInSurveyEntity> getTeachingLog(CheckInSurveyQuery query);

    /**
     * 查询评教
     * @param query
     * @return
     */
    public PageList<CheckInSurveyEntity> getEvaluations(CheckInSurveyQuery query);

    /**
     * 评教记录数
     * @param param
     * @return
     */
    public int getEvaluationCnt(Map<String, String> param);

    /**
     * 保存申请听课设置
     * @param model
     */
    public void saveSetting(OpenLessonSettingEntity model);

    /**
     * 查询申请听课设置
     * @param query 
     * @return
     */
    public PageList<OpenLessonSettingEntity> getOpenLessonSettingAll(OpenLessonSettingEntity query);

    /**
     * 刷新
     * @param model
     */
    public void refreshSetting();

    /**
     * 查询有效申请听课设置
     * @return
     */
    public OpenLessonSettingEntity getOpenLessonSetting();

    /**
     * 预约听课列表
     * @param tQuery
     * @return
     */
    public PageList<TeacherOpenLessonEntity> getDeclareOpenLesson(TeacherOpenLessonQuery query);

    /**
     * 开放预约听课列表
     * @param pquery
     * @return
     */
    public PageList<CurriculumScheduleEntity> getOpenLessonList(CheckInSurveyQuery query);

    /**
     * 提交预约
     * @param globalid
     */
    public void doSubmit(Map<String, String> param);

    /**
     * 改变审核状态
     * @param param
     */
    public void changeStatus(Map<String, String> param);

    /**
     * 取消预约
     * @param param
     */
    public void cancelSubmit(Map<String, String> param);

    /**
     * 听课管理
     * @param tQuery
     * @return
     */
    public PageList<TeacherOpenLessonEntity> getAuditOpenLesson(TeacherOpenLessonQuery query);

    /**
     * 听课查询
     * @param tolQuery
     * @return
     */
    public PageList<TeacherOpenLessonEntity> getSearchOpenLesson(TeacherOpenLessonQuery query);

    /**
     * 听课评价
     * @param tolQuery
     * @return
     */
    public PageList<TeacherOpenLessonEntity> getEvaluationOpenLesson(TeacherOpenLessonQuery query);

    /**
     * 取得业务问卷配置列表
     * @param query
     * @return
     */
    public PageList<SettingEntity> getQuestionnaires(SettingQuery query);
    
    /**
     * 取得业务问卷配置
     * @param query
     * @return
     */
    public SettingEntity getQuestionnaireById(String globalid);
    
    /**
     * 增加业务问卷配置
     * @param settingEntity
     */
    public void saveQuestionnaire(SettingEntity settingEntity);
    
    /**
     * 修改业务问卷配置
     * @param settingEntity
     */
    public void modifyQuestionnaire(SettingEntity settingEntity);

    /**
     * 业务问卷配置存在判断
     * @param m
     * @return
     */
    public boolean isExistSetting(SettingEntity m);
   
    /**
     * 取得问卷
     * @param pjid
     * @return
     */
    public List<ViewQuestionnaireEntity> getViewQuestionnaires(String pjid);
    
    /**
     * 根据条件查询课表列表
     * @param param
     * @return
     */
    public List<CurriculumScheduleEntity> getCurriculumScheduleByKcid(Map<String, Object> param);
    
    /**
     * 根据条件查询课表列表
     * @param param
     * @return
     */
    public List<CurriculumScheduleEntity> getCurriculumScheduleByParam(Map<String, Object> param);
    
    /**
     * 根据ID查询课表记录
     * @param globalid
     * @return
     */
    public CurriculumScheduleEntity getCurriculumScheduleById(String globalid);
    
    /**
     * 根据条件查询课程表数量
     * @param globalid
     * @return
     */
    public int countCurriculumByParam(Map<String, String> param);

    /**
     * 我的评价
     * @param entity
     * @return
     */
    public List<ViewQuestionnaireEntity> getMyEvaluation(Map<String, String> param);

    /**
     * 问卷调查
     * @param entity
     * @return
     */
    public List<ViewQuestionnaireEntity> getMyResponse(Map<String, String> param);
    
    /**
     * 查询评教数目
     * @param globalid
     * @return
     */
    public Map<String, BigDecimal> getEvaluationCount(String globalid);

    /**
     * 保存意见
     * @param param
     */
    public void saveOpinion(Map<String, String> param);
    
    /**
     * 获取意见反馈列表
     * @param param
     * @return
     */
    public PageList<OpinionEntity> getOpinionList(OpinionQuery query);
    
    /**
     * 根据ID查询反馈意见
     * @param globalid
     * @return
     */
    public OpinionEntity getOpinionById(String globalid);
}
