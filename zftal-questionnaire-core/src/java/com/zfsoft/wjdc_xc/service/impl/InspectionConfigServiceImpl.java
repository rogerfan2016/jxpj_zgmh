package com.zfsoft.wjdc_xc.service.impl;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc_xc.dao.IInspectionConfigDao;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;
import com.zfsoft.wjdc_xc.service.IInspectionConfigService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-5
 * @version V1.0.0
 */
public class InspectionConfigServiceImpl implements IInspectionConfigService {
	
	private IInspectionConfigDao inspectionConfigDao;
	/**
	 * 设置
	 * @param inspectionConfigDao 
	 */
	public void setInspectionConfigDao(IInspectionConfigDao inspectionConfigDao) {
		this.inspectionConfigDao = inspectionConfigDao;
	}

	@Override
	public InspectionConfig findConfig(InspectionConfig inspectionConfig) {
		return inspectionConfigDao.findConfig(inspectionConfig);
	}

	@Override
	public void addConfig(InspectionConfig inspectionConfig) {
		inspectionConfigDao.addConfig(inspectionConfig);
	}

	@Override
	public void updateConfig(InspectionConfig inspectionConfig) {
		inspectionConfigDao.updateConfig(inspectionConfig);
	}

	@Override
	public PageList<Map<String, Object>> getPagingInfoList(
			InspectionConfigQuery query) {
		PageList<Map<String, Object>> pageList = new PageList<Map<String, Object>>();
		Paginator paginator = new Paginator();
		if(query!=null){
			paginator.setItemsPerPage(query.getPerPageSize());
			paginator.setPage((Integer)query.getToPage());
			
			paginator.setItems(inspectionConfigDao.getPagingInfoCount(query));
			pageList.setPaginator(paginator);
			
			if(paginator.getBeginIndex() <= paginator.getItems()){
				query.setStartRow(paginator.getBeginIndex());
				query.setEndRow(paginator.getEndIndex());
				List<Map<String, Object>> list = inspectionConfigDao.getPagingInfoList(query);
				pageList.addAll(list);
			}
		}
		return pageList;
	}

	@Override
	public PageList<Map<String, Object>> getPagingPersonList(
			InspectionConfigQuery query) {
		query.setTable("overall");
		String param = "";
		if(StringUtil.isNotEmpty(query.getParams().get("gh"))){
			param+=" and gh =#{params.gh}";
		}
		if(StringUtil.isNotEmpty(query.getParams().get("xm"))){
			param+=" and xm like '%'||#{params.xm}||'%'";
		}
		// 个人身份（teacher教师；student学生）
		if(StringUtil.isNotEmpty(query.getParams().get("grsf"))){
			param+=" and grsf =#{params.grsf}";
		}
		query.setExpress(" (select '%' || nvl(person,'0') || '%' from XC_CONFIG where ywjb=#{params.ywjb} and ywbm=#{params.ywbm} and type=#{params.type}) NOT LIKE '%' || gh || '%' "+param);
		return getPagingInfoList(query);
	}
	
	@Override
	public PageList<Map<String, Object>> getPagingObjectList(
			InspectionConfigQuery query) {
		query.setTable("overall");
		String param = "";
		if(StringUtil.isNotEmpty(query.getParams().get("gh"))){
			param+=" and gh =#{params.gh}";
		}
		if(StringUtil.isNotEmpty(query.getParams().get("xm"))){
			param+=" and xm like '%'||#{params.xm}||'%'";
		}
		query.setExpress(" (select '%' || nvl(object,'0') || '%' from XC_CONFIG where ywjb=#{params.ywjb} and ywbm=#{params.ywbm} and type=#{params.type}) NOT LIKE '%' || gh || '%' "+param);
		return getPagingInfoList(query);
	}

	@Override
	public PageList<Map<String, Object>> getPagingDcwjList(
			InspectionConfigQuery query) {
		query.setTable("wjdc_wjxxb");
		String param = "";
		if(StringUtil.isNotEmpty(query.getParams().get("wjlx"))){
			param+=" and wjlx =#{params.wjlx}";
		}
		if(StringUtil.isNotEmpty(query.getParams().get("wjzt"))){
			param+=" and wjzt =#{params.wjzt}";
		}
		if(StringUtil.isNotEmpty(query.getParams().get("wjmc"))){
			param+=" and wjmc like '%'||#{params.wjmc}||'%'";
		}
		query.setExpress(" wjzt ='草稿' and (select '%' || nvl(wjid,'0') || '%' from XC_CONFIG where ywjb=#{params.ywjb} and ywbm=#{params.ywbm} and type=#{params.type}) NOT LIKE '%' || wjid || '%' "+param);
		return getPagingInfoList(query);
	}

	@Override
	public List<Map<String, Object>> getCheckedPersonList(String type, String ywjb, String ywbm) {
		InspectionConfigQuery query = new InspectionConfigQuery();
		query.setTotalItem(1000);
		query.setEndRow(1000);
		query.setTable("overall");
		query.getParams().put("type", type);
		query.getParams().put("ywjb", ywjb);
		query.getParams().put("ywbm", ywbm);
		query.setExpress(" dqztm = '11' and (select nvl(person,'0') from XC_CONFIG where ywjb=#{params.ywjb} and ywbm=#{params.ywbm} and type=#{params.type}) like '%' || gh || '%'");
		return getPagingInfoList(query);
	}

	@Override
	public List<Map<String, Object>> getCheckedDcwjList(String type, String ywjb, String ywbm) {
		InspectionConfigQuery query = new InspectionConfigQuery();
		query.setTotalItem(1000);
		query.setEndRow(1000);
		query.setTable("wjdc_wjxxb");
		query.getParams().put("type", type);
		query.getParams().put("ywjb", ywjb);
		query.getParams().put("ywbm", ywbm);
		query.setExpress(" (select nvl(wjid,'0') from XC_CONFIG where ywjb=#{params.ywjb} and ywbm=#{params.ywbm} and type=#{params.type}) like '%' || wjid || '%'");
		return inspectionConfigDao.getPagingInfoList(query);
	}

	@Override
	public List<Map<String, Object>> getCheckedObjectList(String type, String ywjb, String ywbm) {
		InspectionConfigQuery query = new InspectionConfigQuery();
		query.setTotalItem(1000);
		query.setEndRow(1000);
		query.setTable("overall");
		query.getParams().put("type", type);
		query.getParams().put("ywjb", ywjb);
		query.getParams().put("ywbm", ywbm);
		query.setExpress(" dqztm = '11' and (select nvl(object,'0') from XC_CONFIG where ywjb=#{params.ywjb} and ywbm=#{params.ywbm} and type=#{params.type}) like '%' || gh || '%'");
		return getPagingInfoList(query);
	}
	
}
