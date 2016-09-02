package com.zfsoft.wjdc_xc.dao;

import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public interface IInspectionConfigDao {
	
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
	* @Title: getPagingInfoCount 
	* @Description: TODO(获取评价配置列表数量) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int getPagingInfoCount(InspectionConfigQuery query);
}