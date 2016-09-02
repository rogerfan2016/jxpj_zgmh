package com.zfsoft.wjdc_xc.service;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.normal.info.entity.OverallInfo;
import com.zfsoft.wjdc_xc.entites.InspectionTask;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.query.InspectionSummerQuery;
import com.zfsoft.wjdc_xc.query.InspectionTaskQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-10
 * @version V1.0.0
 */
public interface IInspectionTaskService {
	
	public PageList<InspectionTask> getPagingList(InspectionTaskQuery query);
	
	public void insert(InspectionTask inspectionTask);

	public void save(InspectionTask inspectionTask,List<OverallInfo> overallInfoList);
	
	/**
	 * 
	* @Title: saveMemberOfJkpj 
	* @Description: TODO(保存结课评价人员信息) 
	* @param @param param    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void saveMemberOfJkpj(Map<String, String> param);
	
	/**
	 * 
	* @Title: update 
	* @Description: TODO(更新评价任务信息) 
	* @param @param inspectionTask    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void update(InspectionTask inspectionTask);

	/**
	 * 
	* @Title: findById 
	* @Description: TODO(通过ID查询评价任务信息) 
	* @param @param key
	* @param @return    设定文件 
	* @return InspectionTask    返回类型 
	* @throws
	 */
	public InspectionTask findById(String key);

	/**
	 * 
	* @Title: remove 
	* @Description: TODO(删除评价任务) 
	* @param @param inspectionTask    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void remove(InspectionTask inspectionTask);

	/**
	 * 
	* @Title: getMemberList 
	* @Description: TODO(获取评价人员列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskMember>    返回类型 
	* @throws
	 */
	public List<InspectionTaskMember> getMemberList(InspectionTaskMember query);

	/**
	 * 
	* @Title: getTaskSummerPage 
	* @Description: TODO(获取任务统计汇总结果) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<Map<String,Object>>    返回类型 
	* @throws
	 */
	PageList<Map<String, Object>> getTaskSummerPage(InspectionSummerQuery query);

	/**
	 * 
	* @Title: getResultPagingList 
	* @Description: TODO(获取评价结果记录列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<Map<String,Object>>    返回类型 
	* @throws
	 */
	PageList<Map<String, Object>> getResultPagingList(InspectionSummerQuery query);
	
	/**
	 * 
	* @Title: getTaskMemberList 
	* @Description: TODO(获取评价成员 列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<InspectionTaskMember>    返回类型 
	* @throws
	 */
	public PageList<InspectionTaskMember> getTaskMemberList(InspectionTaskQuery query);
	
	/**
	 * 
	* @Title: getLessonList 
	* @Description: TODO(获取课程列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<Map<String,Object>>    返回类型 
	* @throws
	 */
	public PageList<Map<String, Object>> getLessonList(InspectionTaskQuery query);
	
	/**
	 * 
	* @Title: getEndingClassSumResult 
	* @Description: TODO(查询汇总结课评价信息) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskMember>    返回类型 
	* @throws
	 */
	public PageList<InspectionTaskMember> getEndingClassSumResult(InspectionTaskQuery query);

}
