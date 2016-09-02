package com.zfsoft.wjdc_xc.service;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc_xc.entites.InspectionTaskResult;
import com.zfsoft.wjdc_xc.query.InspectionTaskResultQuery;

/**
 * 
* @ClassName: IInspectionTaskResultService
* @Description: TODO(评价结果服务层接口类)
* @author rogerfan
* @date 2016-7-2 上午10:38:05
*
 */
public interface IInspectionTaskResultService {
	
	/**
	 * 
	* @Title: findTaskMemberByParam 
	* @Description: TODO(获取评价任务的人员信息) 
	* @param @param query
	* @param @return    设定文件 
	* @return List<Map<String,String>>    返回类型 
	* @throws
	 */
	public List<Map<String,String>> findTaskMemberByParam(InspectionTaskResultQuery query);
	
	/**
	 * 
	* @Title: getPagingList 
	* @Description: TODO(获取评价结果列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<InspectionTaskResult>    返回类型 
	* @throws
	 */
	public PageList<InspectionTaskResult> getPagingList(InspectionTaskResultQuery query);

	/**
	 * 
	* @Title: insert 
	* @Description: TODO(新增评价结构记录) 
	* @param @param inspectionTask    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void insert(InspectionTaskResult inspectionTask);
	
	/**
	 * 
	* @Title: insertEndingClassResult 
	* @Description: TODO(批量新增结课评价结果记录) 
	* @param @param param    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void insertEndingClassResult(Map<String,String> param);
	
	/**
	 * 
	* @Title: updateStatus 
	* @Description: TODO(修改评价结果记录状态) 
	* @param @param inspectionTask    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateStatus(InspectionTaskResult inspectionTask);

	/**
	 * 
	* @Title: findById 
	* @Description: TODO(根本ID获取评价结果记录) 
	* @param @param key
	* @param @return    设定文件 
	* @return InspectionTaskResult    返回类型 
	* @throws
	 */
	public InspectionTaskResult findById(String key);

	/**
	 * 
	* @Title: remove 
	* @Description: TODO(删除评价结果) 
	* @param @param inspectionTask
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void remove(InspectionTaskResult inspectionTask) throws Exception;
	
	/**
	 * 
	* @Title: findCount 
	* @Description: TODO(根本条件统计数量) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int findCount(InspectionTaskResultQuery query);
	
	/**
	 * 
	* @Title: getFzSum 
	* @Description: TODO(获取评价结果总得分) 
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
	* @Description: TODO(取得课程分类内容) 
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
