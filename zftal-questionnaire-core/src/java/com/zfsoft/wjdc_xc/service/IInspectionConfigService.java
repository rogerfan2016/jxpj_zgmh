package com.zfsoft.wjdc_xc.service;
import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-5
 * @version V1.0.0
 */
public interface IInspectionConfigService {
	/**
	 * 
	* @Title: findConfig 
	* @Description: TODO(查询评价配置) 
	* @param @param inspectionConfig    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public InspectionConfig findConfig(InspectionConfig inspectionConfig);
	/**
	 * 
	* @Title: addConfig 
	* @Description: TODO(增加评价配置) 
	* @param @param inspectionConfig    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void addConfig(InspectionConfig inspectionConfig);
	/**
	 * 
	* @Title: updateConfig 
	* @Description: TODO(修改评价配置) 
	* @param @param inspectionConfig    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void updateConfig(InspectionConfig inspectionConfig);
	/**
	 * 
	* @Title: getPagingInfoList 
	* @Description: TODO(获取评价配置列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<Map<String,Object>>    返回类型 
	* @throws
	 */
	public PageList<Map<String, Object>> getPagingInfoList(InspectionConfigQuery query);
	/**
	 * 
	* @Title: getPagingPersonList 
	* @Description: TODO(获取评价人员列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<Map<String,Object>>    返回类型 
	* @throws
	 */
	public PageList<Map<String, Object>> getPagingPersonList(InspectionConfigQuery query);
	/**
	 * 
	* @Title: getPagingObjectList 
	* @Description: TODO(获取评价对象列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<Map<String,Object>>    返回类型 
	* @throws
	 */
	public PageList<Map<String, Object>> getPagingObjectList(InspectionConfigQuery query);
	/**
	 * 
	* @Title: getPagingDcwjList 
	* @Description: TODO(获取草稿状态的评价模板列表) 
	* @param @param query
	* @param @return    设定文件 
	* @return PageList<Map<String,Object>>    返回类型 
	* @throws
	 */
	public PageList<Map<String, Object>> getPagingDcwjList(InspectionConfigQuery query);
	/**
	 * 
	* @Title: getCheckedPersonList 
	* @Description: TODO(已选择的评价人员列表) 
	* @param @param type
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> getCheckedPersonList(String type, String ywjb, String ywbm);
	/**
	 * 
	* @Title: getCheckedObjectList 
	* @Description: TODO(已选择的评价对象列表) 
	* @param @param type
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> getCheckedObjectList(String type, String ywjb, String ywbm);
	/**
	 * 
	* @Title: getCheckedDcwjList 
	* @Description: TODO(已选择的评价模板列表) 
	* @param @param type
	* @param @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> getCheckedDcwjList(String type, String ywjb, String ywbm);

}
