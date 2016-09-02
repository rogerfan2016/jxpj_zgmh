package com.zfsoft.wjdc_xc.dao;

import java.util.List;
import java.util.Map;

import com.zfsoft.wjdc_xc.entites.InspectionTask;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.query.InspectionSummerQuery;
import com.zfsoft.wjdc_xc.query.InspectionTaskQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public interface IInspectionTaskDao {

	public void insert(InspectionTask inspectionTask);
	/**
	 * 
	* @Title: saveMemberOfJkpj 
	* @Description: TODO(保存结课评价人员信息) 
	* @param @param param    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void saveMemberOfJkpj(Map<String, String> param);
	
	public void remove(String id);
	public void update(InspectionTask inspectionTask);
	public InspectionTask findById(String id);
	public List<InspectionTask> getPagingInfoList(InspectionTaskQuery inspectionTaskQuery);
	public int getPagingInfoCount(InspectionTaskQuery inspectionTaskQuery);
	
	public List<InspectionTaskMember> getMemberList(InspectionTaskMember member);
	public void removeMember(InspectionTaskMember member);
	public void insertMember(InspectionTaskMember member);
	
	public List<Map<String, Object>> getTaskSummerPage(InspectionSummerQuery query);
	public int getTaskSummerPageCount(InspectionSummerQuery query);
	public List<Map<String, Object>> getXcdxSummerPage(InspectionSummerQuery query);
	public int getXcdxSummerPageCount(InspectionSummerQuery query);
	/**
	 * 
	* @Title: getTaskMemberCount 
	* @Description: TODO(获取评价成员 列表数量) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getTaskMemberCount(InspectionTaskQuery query);
	
	/**
	 * 
	* @Title: getTaskMemberList 
	* @Description: TODO(获取评价成员 列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskMember>    返回类型 
	* @throws
	 */
	public List<InspectionTaskMember> getTaskMemberList(InspectionTaskQuery query);
	
	/**
	 * 
	* @Title: getLessonList 
	* @Description: TODO(获取课程列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> getLessonList(InspectionTaskQuery query);
	
	/**
	 * 
	* @Title: getLessonCount 
	* @Description: TODO(获得课程列表数量) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getLessonCount(InspectionTaskQuery query);
	
	/**
	 * 
	* @Title: getLessonByKcid 
	* @Description: TODO(根据课程ID获取课程信息) 
	* @param @param globalid
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public Map<String, String> getLessonByKcid(String globalid);
	
	/**
	 * 
	* @Title: getEndingClassSumResultCount 
	* @Description: TODO(查询汇总结课评价信息列表数量) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getEndingClassSumResultCount(InspectionTaskQuery query);
	/**
	 * 
	* @Title: getEndingClassSumResult 
	* @Description: TODO(查询汇总结课评价信息) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<InspectionTaskMember>    返回类型 
	* @throws
	 */
	public List<InspectionTaskMember> getEndingClassSumResult(InspectionTaskQuery query);
	
}