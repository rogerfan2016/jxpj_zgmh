package com.zfsoft.wjdc_xc.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.zfsoft.common.spring.SpringHolder;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.normal.info.entity.OverallInfo;
import com.zfsoft.hrm.normal.info.service.svcinterface.IOverallInfoService;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.entites.InspectionLevelEnum;
import com.zfsoft.wjdc_xc.query.InspectionConfigQuery;
import com.zfsoft.wjdc_xc.service.IInspectionConfigService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-2
 * @version V1.0.0
 */
public class InspectionConfigAction extends HrmAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7091496556422483536L;
	private InspectionConfig config = new InspectionConfig();
	private String[] valueList;
	private InspectionConfigQuery query = new InspectionConfigQuery();
	private IInspectionConfigService inspectionConfigService;
	private String type = "XNXC";
	private String ywjb = "dept";
	private String ywbm = "";
	
	/**
	 * 
	* @Title: setup 
	* @Description: TODO(评价设置) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String setup(){
		ywbm = this.getUser().getBmdm_id();
		config.setType(type);
		config.setYwjb(ywjb);
		// 如果是校级则使用同一个配置
		if(InspectionLevelEnum.LEVEL_DEPT.getKey().equals(ywjb)){
			config.setYwbm(ywbm);
		}else{
			config.setYwbm("0");
		}
		InspectionConfig entity = inspectionConfigService.findConfig(config);
		// 如果不存在部门的配置记录
		if(entity == null){
			config.setYwbm("");
			entity = inspectionConfigService.findConfig(config);
			if(entity != null){
				entity.setYwbm(this.getUser().getBmdm_id());
				// 如果部门配置没有记录则先插入一条
				inspectionConfigService.addConfig(entity);
			}
		}else{
			// 如果登录用的部门为空或者级别为校级，则默认为0
			if(StringUtil.isEmpty(ywbm) || InspectionLevelEnum.LEVEL_SCHOOL.getKey().equals(ywjb)){
				ywbm = "0";
			}
			getValueStack().set("personList", inspectionConfigService.getCheckedPersonList(type,ywjb,ywbm));
			getValueStack().set("objectList", inspectionConfigService.getCheckedObjectList(type,ywjb,ywbm));
			getValueStack().set("dcwjList", inspectionConfigService.getCheckedDcwjList(type,ywjb,ywbm));
		}
		getValueStack().set("config", entity);
		return "setup";
	} 
	
	/**
	* <p>Title: pj_setup</p>
	* <p>Description: 业务评价设置</p>
	* @return
	 */
	public String pj_setup(){
		return "pj_setup";
	}
	/**
	 * 
	* @Title: dcwjList 
	* @Description: TODO(获取问卷列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String dcwjList(){
		getValueStack().set("list", inspectionConfigService.getPagingDcwjList(query));
		return "dcwjList";
	}
	/**
	 * 
	* @Title: personList 
	* @Description: TODO(获取评价人员列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String personList(){
		getValueStack().set("list", inspectionConfigService.getPagingPersonList(query));
		return "personList";
	}
	/**
	 * 
	* @Title: objectList 
	* @Description: TODO(获取评价对象列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String objectList(){
		getValueStack().set("list", inspectionConfigService.getPagingObjectList(query));
		return "objectList";
	}
	/**
	 * 
	* @Title: save 
	* @Description: TODO(保存人员或问卷模板) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String save(){
		if(valueList!=null){
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < valueList.length; i++){
				sb.append(valueList[i]);
				sb.append(",");
			}
			InspectionConfig bean = inspectionConfigService.findConfig(config);
			if(bean != null){
				String str = "";
				// 选择评价人员
				if("personList".equals(config.getKey())){
					str = bean.getPerson() == null ? "" : bean.getPerson();
					bean.setPerson(str.concat(sb.toString()));
				}
				// 选择评价对象
				if("objectList".equals(config.getKey())){
					str = bean.getObject() == null ? "" : bean.getObject();
					bean.setObject(str.concat(sb.toString()));
				}
				// 选择评价模板
				if("dcwjList".equals(config.getKey())){
					str = bean.getWjid() == null ? "" : bean.getWjid();
					bean.setWjid(str.concat(sb.toString()));
				}
				inspectionConfigService.updateConfig(bean);
			}
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}
	/**
	 * 
	* @Title: remove 
	* @Description: TODO(删除人员或问卷模板) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String remove(){
		if(StringUtil.isNotEmpty(config.getType()) 
				&& StringUtil.isNotEmpty(config.getValue())){
			InspectionConfig bean = inspectionConfigService.findConfig(config);
			if(bean != null){
				String str = "";
				// 删除人员工号字符串
				if("personList".equals(config.getKey())){
					str = bean.getPerson().replace(config.getValue().concat(","), "");
					bean.setPerson(str);
				}
				// 删除对象工号字符串
				if("objectList".equals(config.getKey())){
					str = bean.getObject().replace(config.getValue().concat(","), "");
					bean.setObject(str);
				}
				// 删除问卷模块字符串
				if("dcwjList".equals(config.getKey())){
					str = bean.getWjid().replace(config.getValue().concat(","), "");
					bean.setWjid(str);
				}
				inspectionConfigService.updateConfig(bean);
			}
		}
		getValueStack().set(DATA, getMessage());
		return DATA;
	}

	/**
	 * 
	* @Title: userListScopeThink 
	* @Description: TODO(根据人姓名、教室、课程、节次查询被评价对象) 
	* @param @return
	* @param @throws UnsupportedEncodingException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String userListScopeThink() throws UnsupportedEncodingException {
		InspectionConfig bean = new InspectionConfig();
		bean.setType(type);
		InspectionConfig config = inspectionConfigService.findConfig(bean);
		String sql = "1=1";
		if (config != null && StringUtil.isNotEmpty(config.getValue())) {
			sql = config.getValue().replaceAll("\\$\\{[^\\}]*\\}",
					"'" + getUser().getYhm() + "'");
			IOverallInfoService overallInfoService = SpringHolder.getBean(
					"overallInfoService", IOverallInfoService.class);
			String term = new String(getRequest().getParameter("term").getBytes(
					getRequest().getCharacterEncoding()), "utf-8");
			// String dept =getRequest().getParameter("dept");
			int num = 10;
			try {
				num = Integer.valueOf(getRequest().getParameter("maxItemSize"));
			} catch (Exception e) {
			}
			// String depSql="OFF".equals(dept)?"":" and "+DeptFilterUtil.getCondition("orl", "dwm");
			List<OverallInfo> overallInfos = overallInfoService.userListThink(term,
					" rownum<=" + num + " and " + sql);
			this.getValueStack().set(DATA, overallInfos);
		}
		return DATA;
	}

	/**
	 * 设置
	 * @param inspectionConfigService 
	 */
	public void setInspectionConfigService(
			IInspectionConfigService inspectionConfigService) {
		this.inspectionConfigService = inspectionConfigService;
	}

	/**
	 * 返回
	 */
	public InspectionConfigQuery getQuery() {
		return query;
	}

	/**
	 * 设置
	 * @param query 
	 */
	public void setQuery(InspectionConfigQuery query) {
		this.query = query;
	}

	public InspectionConfig getConfig() {
		return config;
	}

	public void setConfig(InspectionConfig config) {
		this.config = config;
	}
	
	/**
	 * 返回
	 */
	public String[] getValueList() {
		return valueList;
	}

	/**
	 * 设置
	 * @param valueList 
	 */
	public void setValueList(String[] valueList) {
		this.valueList = valueList;
	}

	/**
	 * 设置
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回
	 */
	public String getType() {
		return type;
	}

	public String getYwjb() {
		return ywjb;
	}

	public void setYwjb(String ywjb) {
		this.ywjb = ywjb;
	}

	public String getYwbm() {
		return ywbm;
	}

	public void setYwbm(String ywbm) {
		this.ywbm = ywbm;
	}
	
}