package com.zfsoft.wjdc_xc.dao;

import java.util.List;
import java.util.Map;

import com.zfsoft.wjdc_xc.entites.InspectionTaskResult;
import com.zfsoft.wjdc_xc.query.InspectionTaskResultQuery;

/**
 * 
* @ClassName: IInspectionTaskResultDao
* @Description: TODO(评价结果DAO)
* @author rogerfan
* @date 2016-7-2 上午10:36:03
*
 */
public interface IInspectionTaskResultDao {
	
	/**
	 * 
	* @Title: insert 
	* @Description: TODO(新增评价结果记录信息) 
	* @param @param inspectionTaskResult    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void insert(InspectionTaskResult inspectionTaskResult);
	
	/**
	 * 
	* @Title: update 
	* @Description: TODO(修改评价结果记录) 
	* @param @param inspectionTaskResult    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void update(InspectionTaskResult inspectionTaskResult);
	
	/**
	 * 
	* @Title: findById 
	* @Description: TODO(通过ID查找评价结果 记录) 
	* @param @param id
	* @param @return    设定文件 
	* @return InspectionTaskResult    返回类型 
	* @throws
	 */
	public InspectionTaskResult findById(String id);
	
	/**
	 * 
	* @Title: findEndingClassList 
	* @Description: TODO(通过条件获取评价课程记录) 
	* @param @param param
	* @param @return    设定文件 
	* @return List<InspectionTaskResult>    返回类型 
	* @throws
	 */
	public List<InspectionTaskResult> findEndingClassList(Map<String,String> param);
	
	/**
	 * 
	* @Title: findLessonByKcId 
	* @Description: TODO(通过课程ID查询课程记录) 
	* @param @param param
	* @param @return    设定文件 
	* @return List<InspectionTaskResult>     返回类型 
	* @throws
	 */
	public List<InspectionTaskResult> findLessonByKcId(Map<String,String> param);
	
	/**
	 * 
	* @Title: getEndingCalssCountByParam 
	* @Description: TODO(通过条件判断结课评价结果记录是否存在) 
	* @param @param param
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getEndingCalssCountByParam(Map<String,String> param);
	
	/**
	 * 
	* @Title: findTaskMemberByParam 
	* @Description: TODO(获取评价任务的人员信息) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<Map<String,String>>   返回类型 
	* @throws
	 */
	public List<Map<String,String>> findTaskMemberByParam(InspectionTaskResultQuery query);
	
	/**
	 * 
	* @Title: remove 
	* @Description: TODO(删除评价结果记录) 
	* @param @param inspectionTaskResult    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void remove(InspectionTaskResult inspectionTaskResult);
	
	/**
	 * 
	* @Title: getPagingInfoList 
	* @Description: TODO(获取评价结果 列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskResult>    返回类型 
	* @throws
	 */
	public List<InspectionTaskResult> getPagingInfoList(InspectionTaskResultQuery query);
	
	/**
	 * 
	* @Title: getPagingInfoCount 
	* @Description: TODO(获取评价结果记录数) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getPagingInfoCount(InspectionTaskResultQuery query);

	/**
	 * 
	* @Title: getFzSum 
	* @Description: TODO(获取总得分) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getFzSum(String id);
	/**
	 * 
	* @Title: getJxlList 
	* @Description: TODO(获取上课的教学楼列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskResult>    返回类型 
	* @throws
	 */
	public List<InspectionTaskResult> getJxlList(InspectionTaskResultQuery query);
	
	/**
	 * 
	* @Title: getSkddListByJxl 
	* @Description: TODO(获取某教学楼的所有教室列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskResult>    返回类型 
	* @throws
	 */
	public List<InspectionTaskResult> getSkddListByJxl(InspectionTaskResultQuery query);
	
	/**
	 * 
	* @Title: getKcfl 
	* @Description: TODO(取得课程分类) 
	* @param @param kcdm
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws
	 */
    public Map<String,String> getKcfl(String kcdm);
    
    /**
     * 
    * @Title: getWjpz 
    * @Description: TODO(取得业务问卷配置信息) 
    * @param @param param
    * @param @return    设定文件 
    * @return Map<String,String>    返回类型 
    * @throws
     */
    public Map<String,String> getWjpz(Map<String,String> param);
}
