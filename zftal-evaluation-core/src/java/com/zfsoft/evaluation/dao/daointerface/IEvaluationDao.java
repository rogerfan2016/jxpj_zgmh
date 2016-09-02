package com.zfsoft.evaluation.dao.daointerface;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.zfsoft.evaluation.entity.CheckInEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyEntity;
import com.zfsoft.evaluation.entity.CheckInSurveyQuery;
import com.zfsoft.evaluation.entity.CurriculumScheduleEntity;
import com.zfsoft.evaluation.entity.CurriculumScheduleQuery;
import com.zfsoft.evaluation.entity.EvaluationConnEntity;
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
public interface IEvaluationDao {
	
	/**
     * 获取教学周
     * @param dateStr
     * @return
     */
    public int getTeachingWeek(String dateStr);
	
	/**
     * 取得课程分类
     * @param pjid
     * @return
     */
    public String getKcfl(String kcid);

    /**
     * 查询课表 
     * @param param
     * @return
     */
    public List<CurriculumScheduleEntity> getCurriculumSchedule(Map<String, Object> param);
    
    /**
     * 根据条件查询课表列表数量
     * @param param
     * @return
     */
    public int getCurriculumScheduleCount(CurriculumScheduleQuery query);
    
    /**
     * 根据条件查询课表列表 
     * @param param
     * @return
     */
    public List<CurriculumScheduleEntity> getCurriculumScheduleList(CurriculumScheduleQuery query);
    
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
     * 最大节次
     * @param param
     * @return
     */
    public int getMaxJc(Map<String, Object> param);

    /**
     * 查询课表
     * @param globalid
     * @return
     */
    public TeachingEntity getTeachingById(String globalid);

    /**
     * 取得学生
     * @param teachingEntity
     * @return
     */
    public List<CheckInEntity> getStudents(TeachingEntity teachingEntity);

    /**
     * 保存教学日志
     * @param teachingEntity
     */
    public void insertTeachingLog(TeachingEntity teachingEntity);

    /**
     * 考勤录入
     * @param xs
     */
    public void insertCheckIn(CheckInEntity xs);

    /**
     * 取得教学日志
     * @param globalid
     * @return
     */
    public int getTeachingLogById(String globalid);

    /**
     * 修改教学日志
     * @param teachingEntity
     */
    public void modifyTeachingLog(TeachingEntity teachingEntity);

    /**
     * 考勤取得
     * @param xs
     * @return
     */
    public int getCheckIn(CheckInEntity xs);

    /**
     * 修改考勤
     * @param xs
     */
    public void modifyCheckIn(CheckInEntity xs);

    /**
     * 评教记录数
     * @param param
     * @return
     */
    public int getXsEvaluationCnt(Map<String, String> param);

    /**
     * 评教记录数
     * @param param
     * @return
     */
    public int getJsEvaluationCnt(Map<String, String> param);
    
    /**
     * 插入学生评教记录
     * @param globalid
     */
    public void addXsEvaluation(ViewQuestionnaireEntity entity);
    
    /**
     * 插入教师评教记录
     * @param globalid
     */
    public void addJsEvaluation(ViewQuestionnaireEntity entity);
    
    /**
     * 通过考勤记录插入评教记录
     * @param globalid
     */
    public void insertXsEvaluation(String globalid);

    /**
     * 通过听课记录插入评教记录
     * @param globalid
     */
    public void insertJsEvaluation(String globalid);
    
    /**
     * 查询课表
     * @param query
     * @return
     */
    public List<CurriculumScheduleEntity> getCheckInSurveyEntities(CheckInSurveyQuery query);

    /**
     * 查询评教数目
     * @param globalid
     * @return
     */
    public Map<String, BigDecimal> getEvaluationCount(String globalid);
    
    /**
     * 按学院统计考勤
     * @param globalid
     * @return
     */
    public List<CheckInSurveyEntity> getEvaluationCountByCollege(CheckInSurveyQuery query);
    
    /**
     * 查询学生考勤数量
     * @param query
     * @return
     */
    public int getStudentAttendanceCount(CheckInSurveyQuery query);
    
    /**
     * 按学生统计考勤列表
     * @param query
     * @return
     */
    public List<CheckInSurveyEntity> getStudentAttendanceList(CheckInSurveyQuery query);
    
    /**
     * 按班级课程统计缺勤学生数量
     * @param query
     * @return
     */
    public int getSummaryAbsentCountByClass(CheckInSurveyQuery query);
    
    /**
     * 按班级课程统计缺勤学生
     * @param query
     * @return
     */
    public List<CheckInSurveyEntity> getSummaryAbsentListByClass(CheckInSurveyQuery query);
    
    /**
     * 按班级统计学生考勤数量
     * @param query
     * @return
     */
    public int getCheckSummaryCountByClass(CheckInSurveyQuery query);
    
    /**
     * 按班级统计学生考勤
     * @param query
     * @return
     */
    public List<CheckInSurveyEntity> getCheckSummaryListByClass(CheckInSurveyQuery query);
    
    /**
     * 按年级统计学生考勤数量
     * @param query
     * @return
     */
    public int getCheckSummaryCountByGrade(CheckInSurveyQuery query);
    
    /**
     * 按年级统计学生考勤
     * @param query
     * @return
     */
    public List<CheckInSurveyEntity> getCheckSummaryListByGrade(CheckInSurveyQuery query);
    
    /**
     * 学院名称列表
     */
    public List<String> getCollegeList();

    /**
     * 查询考勤和评教
     */
    public List<CheckInEntity> getEvaluationDetail(Map<String, String> param);

    /**
     * 教学日志查询
     * @param query
     * @return
     */
    public List<CurriculumScheduleEntity> getTeachingLog(CheckInSurveyQuery query);

    /**
     * 评教
     * @param param
     * @return
     */
    public List<CheckInEntity> getStudentEvaluations(Map<String, String> param);

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
    public int getOpenLessonSettingCount(OpenLessonSettingEntity query);

    /**
     * 查询申请听课设置
     * @param query
     * @return
     */
    public List<OpenLessonSettingEntity> getOpenLessonSettingAll(OpenLessonSettingEntity query);

    /**
     * 刷新状态
     */
    public void modifyStatusBySave();
    
    /**
     * 刷新状态
     */
    public void modifyStatusByRefresh();
    
    /**
     * 查询申请听课设置
     * @param query
     * @return
     */
    public OpenLessonSettingEntity getOpenLessonSetting();

    /**
     * 预约听课列表
     * @param query
     * @return
     */
    public int getDeclareOpenLessonCount(TeacherOpenLessonQuery query);

    /**
     * 预约听课列表
     * @param query
     * @return
     */
    public List<TeacherOpenLessonEntity> getDeclareOpenLesson(TeacherOpenLessonQuery query);
    
    /**
     * 查询课表
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
     * 开放预约听课列表
     * @param query
     * @return
     */
    public int getOpenLessonCount(CheckInSurveyQuery query);

    /**
     * 开放预约听课列表
     * @param query
     * @return
     */
    public List<CurriculumScheduleEntity> getOpenLesson(CheckInSurveyQuery query);

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
     * @param query
     * @return
     */
    public int getAuditOpenLessonCount(TeacherOpenLessonQuery query);

    /**
     * 听课管理
     * @param query
     * @return
     */
    public List<TeacherOpenLessonEntity> getAuditOpenLesson(TeacherOpenLessonQuery query);

    /**
     * 听课查询
     * @param query
     * @return
     */
    public int getSearchOpenLessonCount(TeacherOpenLessonQuery query);
    
    /**
     * 听课查询
     * @param query
     * @return
     */
    public List<TeacherOpenLessonEntity> getSearchOpenLesson(TeacherOpenLessonQuery query);
    
    /**
     * 听课评价查询
     * @param query
     * @return
     */
    public int getEvaluationOpenLessonCount(TeacherOpenLessonQuery query);
    
    /**
     * 听课评价查询
     * @param query
     * @return
     */
    public List<TeacherOpenLessonEntity> getEvaluationOpenLesson(TeacherOpenLessonQuery query);

    /**
     * 
    * @Title: getQuestionnairesCount 
    * @Description: TODO(取得业务问卷配置列表数量) 
    * @param @param query
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws
     */
    public int getQuestionnairesCount(SettingQuery query);

    /**
     * 
    * @Title: getQuestionnaires 
    * @Description: TODO(取得业务问卷配置列表) 
    * @param @param query
    * @param @return    设定文件 
    * @return List<SettingEntity>    返回类型 
    * @throws
     */
    public List<SettingEntity> getQuestionnaires(SettingQuery query);

    /**
     * 
    * @Title: getQuestionnaireById 
    * @Description: TODO(取得业务问卷配置) 
    * @param @param globalid
    * @param @return    设定文件 
    * @return SettingEntity    返回类型 
    * @throws
     */
    public SettingEntity getQuestionnaireById(String globalid);

    /**
     * 
    * @Title: saveQuestionnaire 
    * @Description: TODO(增加业务问卷配置) 
    * @param @param settingEntity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void saveQuestionnaire(SettingEntity settingEntity);

    /**
     * 
    * @Title: modifyQuestionnaire 
    * @Description: TODO(修改业务问卷配置) 
    * @param @param settingEntity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void modifyQuestionnaire(SettingEntity settingEntity);

    /**
     * 
    * @Title: isExistSetting 
    * @Description: TODO(业务问卷配置存在判断) 
    * @param @param m
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws
     */
    public int isExistSetting(SettingEntity m);

    /**
     * 查询实时评教信息
     * @param globalid
     * @return
     */
    public List<String> getSspjInfo(String globalid);

    /**
     * 查询教师互评信息
     * @param globalid
     * @return
     */
    public List<String> getJshpInfo(String globalid);

    /**
     * 通过考勤记录增加评教关系
     * @param ec
     */
    public void addPjgx(EvaluationConnEntity ec);
    
    /**
     * 增加评教关系
     * @param ec
     */
    public void savePjgx(EvaluationConnEntity ec);
    
    /**
     * 取得GUID
     * @param globalid
     * @return
     */
    public String getGuid();
    
    /**
     * 取得评教人员类型
     * @param pjid
     * @return
     */
    public String getPjrylx(String pjid);
    
    /**
     * 取得所有问卷
     */
    public List<ViewQuestionnaireEntity> getQuestionnaireByPjid(ViewQuestionnaireEntity entity);

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
     * 改变问卷回答状态
     * @param m
     */
    public void updateWjzt(ViewQuestionnaireEntity m);

    /**
     * 保存意见
     * @param param
     */
    public void saveOpinion(Map<String, String> paraOm);
    
    /**
     * 获取意见反馈列表记录数量
     * @param param
     * @return
     */
    public int getOpinionListCount(OpinionQuery query);

    /**
     * 获取意见反馈列表
     * @param param
     * @return
     */
    public List<OpinionEntity> getOpinionList(OpinionQuery query);
    
    /**
     * 根据ID查询反馈意见
     * @param globalid
     * @return
     */
    public OpinionEntity getOpinionById(String globalid);
}
