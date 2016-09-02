package com.zfsoft.evaluation.service;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.GraduationEvaluationEntity;
import com.zfsoft.evaluation.entity.GraduationEvaluationQuery;
import com.zfsoft.evaluation.entity.GraduationEvaluationResultEntity;

/**
 * 
* @ClassName: IFeedBackService
* @Description: TODO(毕业评价Service接口类)
* @author rogerfan
* @date 2016-5-19 下午06:30:46
*
 */
public interface IGraduationEvaluationService {

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
    * @Title: getGraduationEvaluationList 
    * @Description: TODO(查询毕业评价列表) 
    * @param @param query
    * @param @return    设定文件 
    * @return PageList<FeedBackEntity>    返回类型 
    * @throws
     */
    public PageList<GraduationEvaluationEntity> getGraduationEvaluationList(GraduationEvaluationQuery query);
    
    /**
     * 
    * @Title: summartyGraduationEvaluationResult 
    * @Description: TODO(统计评价结果列表) 
    * @param @param query
    * @param @return    设定文件 
    * @return PageList<Map<String,Object>>    返回类型 
    * @throws
     */
    public PageList<Map<String, Object>> summartyGraduationEvaluationResult(GraduationEvaluationQuery query);

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
    * @Title: getGraduationSetting 
    * @Description: TODO(获取毕业评价设置) 
    * @param @param xz
    * @param @return    设定文件 
    * @return FeedBackEntity    返回类型 
    * @throws
     */
    public GraduationEvaluationEntity getGraduationSetting(String xz);

    /**
     * 
    * @Title: modifyGraduationSetting 
    * @Description: TODO(修改毕业评价设置) 
    * @param @param entity    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void modifyGraduationSetting(GraduationEvaluationEntity entity);
    
    /**
     * 
    * @Title: modifyGraduationEvaluationResult 
    * @Description: TODO(新增或修改评价结果) 
    * @param @param result    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void modifyGraduationEvaluationResult(GraduationEvaluationResultEntity result);
    
    /**
     * 
    * @Title: isGraduate 
    * @Description: TODO(通过用户ID判断是不是毕业生) 
    * @param @param xh
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public boolean isGraduate(String xh);
    
    /**
     * 
    * @Title: isEvaluation 
    * @Description: TODO(判断用户是否可以评价) 
    * @param @param userId
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws
     */
    public String isEvaluation(String userId);
    
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
    
    /**
     * 
    * @Title: addGraduationEvaluationResult 
    * @Description: TODO(新增评价结果) 
    * @param @param param    设定文件 
    * @return void    返回类型 
    * @throws
     */
    public void addGraduationEvaluationResult(Map<String,String> param);
    
}
