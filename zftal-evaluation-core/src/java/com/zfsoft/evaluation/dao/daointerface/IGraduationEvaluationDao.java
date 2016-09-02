package com.zfsoft.evaluation.dao.daointerface;

import java.util.List;
import java.util.Map;

import com.zfsoft.evaluation.entity.GraduationEvaluationEntity;
import com.zfsoft.evaluation.entity.GraduationEvaluationQuery;
import com.zfsoft.evaluation.entity.GraduationEvaluationResultEntity;

/**
 * 
* @ClassName: IFeedBackDao
* @Description: TODO(毕业评价DAO类)
* @author rogerfan
* @date 2016-5-19 下午05:44:55
*
 */
public interface IGraduationEvaluationDao {
	
	/**
     * 
    * @Title: saveGraduationEvaluation 
    * @Description: TODO(保存毕业评价) 
    * @param @param entity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void saveGraduationEvaluation(GraduationEvaluationEntity entity);

    /**
     * 
    * @Title: modifyGraduationEvaluation 
    * @Description: TODO(修改毕业评价) 
    * @param @param entity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void modifyGraduationEvaluation(GraduationEvaluationEntity entity);

	/**
	 * 
	* @Title: getGraduationEvaluationCount 
	* @Description: TODO(查询毕业评价数量) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
    public int getGraduationEvaluationCount(GraduationEvaluationQuery query);

    /**
     * 
    * @Title: getGraduationEvaluationList 
    * @Description: TODO(查询毕业评价列表) 
    * @param @param query
    * @param @return    设定文件 
    * @return List<FeedBackEntity>    返回类型 
    * @throws
     */
    public List<GraduationEvaluationEntity> getGraduationEvaluationList(GraduationEvaluationQuery query);

    /**
     * 
    * @Title: getGraduationEvaluationByXh 
    * @Description: TODO(根据学号查询单条评价结果记录) 
    * @param @param xh
    * @param @return    设定文件 
    * @return FeedBackEntity    返回类型 
    * @throws
     */
    public GraduationEvaluationEntity getGraduationEvaluationByXh(String xh);
    
    /**
     * 
    * @Title: getGraduationEvaluationResultByPjid 
    * @Description: TODO(根据评价ID查询评价结果列表) 
    * @param @param pjid
    * @param @return    设定文件 
    * @return List<GraduationEvaluationResultEntity>    返回类型 
    * @throws
     */
    public List<GraduationEvaluationResultEntity> getGraduationEvaluationResultByPjid(String pjid);
    
    /**
     * 
    * @Title: summaryTeacherResult 
    * @Description: TODO(统计评价指标为教师的结果) 
    * @param @param query
    * @param @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws
     */
    public List<Map<String, Object>> summaryTeacherResult(GraduationEvaluationQuery query);
    
    /**
     * 
    * @Title: summaryLessonResult 
    * @Description: TODO(统计评价指标为课程的结果) 
    * @param @param query
    * @param @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws
     */
    public List<Map<String, Object>> summaryLessonResult(GraduationEvaluationQuery query);
    
    /**
     * 
    * @Title: summaryRadioResult 
    * @Description: TODO(统计评价指标为单选的结果) 
    * @param @param query
    * @param @return    设定文件 
    * @return List<Map<String,Object>>    返回类型 
    * @throws
     */
    public List<Map<String, Object>> summaryRadioResult(GraduationEvaluationQuery query);
    
    /**
     * 
    * @Title: getGraduationSetting 
    * @Description: TODO(查询毕业评价设置) 
    * @param @param xz
    * @param @return    设定文件 
    * @return FeedBackEntity    返回类型 
    * @throws
     */
    public GraduationEvaluationEntity getGraduationSetting(String xz);

    /**
     * 
    * @Title: saveGraduationSetting 
    * @Description: TODO(保存毕业评价设置) 
    * @param @param entity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void saveGraduationSetting(GraduationEvaluationEntity entity);

    /**
     * 
    * @Title: deleteGraduationSetting 
    * @Description: TODO(删除毕业评价设置) 
    * @param @param xz    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void deleteGraduationSetting(String xz);
    
    /**
     * 
    * @Title: addGraduationEvaluationResult 
    * @Description: TODO(添加评价结果记录) 
    * @param @param result    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void addGraduationEvaluationResult(GraduationEvaluationResultEntity result);
    
    /**
     * 
    * @Title: deleteGraduationEvaluationResultByPjid 
    * @Description: TODO(根据评价ID删除评价结果) 
    * @param @param pjid    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void deleteGraduationEvaluationResultByPjid(String pjid);
    
    /**
     * 
    * @Title: getXzByXh 
    * @Description: TODO(通过学号获取学制) 
    * @param @param xh
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public String getXzByXh(String xh);
    
    /**
     * 
    * @Title: isGraduate 
    * @Description: TODO(通过用户ID判断是不是毕业生) 
    * @param @param xh
    * @param @return    设定文件 
    * @return int    返回类型 
    * @throws
     */
    public int isGraduate(String xh);
    
    /**
     * 
    * @Title: getLessonListByXh 
    * @Description: TODO(通过用户ID获取在校期间所学所有课程) 
    * @param @param xh
    * @param @return    设定文件 
    * @return List<FeedBackEntity>    返回类型 
    * @throws
     */
    public List<GraduationEvaluationEntity> getLessonListByXh(String xh);
    
    /**
     * 
    * @Title: getTeacherByXh 
    * @Description: TODO(通过用户ID获取在校期间所学所有课程教师) 
    * @param @param xh
    * @param @return    设定文件 
    * @return List<FeedBackEntity>    返回类型 
    * @throws
     */
    public List<GraduationEvaluationEntity> getTeacherByXh(String xh);
}
