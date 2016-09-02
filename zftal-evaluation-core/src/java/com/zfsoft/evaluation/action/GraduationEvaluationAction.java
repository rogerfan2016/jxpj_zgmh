package com.zfsoft.evaluation.action;

import java.util.List;
import java.util.Map;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dataprivilege.util.DeptFilterUtil;
import com.zfsoft.evaluation.entity.GraduationEvaluationEntity;
import com.zfsoft.evaluation.entity.GraduationEvaluationIndex;
import com.zfsoft.evaluation.entity.GraduationEvaluationQuery;
import com.zfsoft.evaluation.entity.GraduationEvaluationResultEntity;
import com.zfsoft.evaluation.service.IGraduationEvaluationService;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;

/**
 * 
* @ClassName: FeedBackAction
* @Description: TODO(毕业评价ACTION)
* @author rogerfan
* @date 2016-5-20 上午03:20:51
*
 */
public class GraduationEvaluationAction extends HrmAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private IGraduationEvaluationService graduationEvaluationService;
	private GraduationEvaluationQuery query;
	private GraduationEvaluationEntity model;
	private GraduationEvaluationEntity model2;
	private GraduationEvaluationResultEntity resultModel;
	private PageList<GraduationEvaluationEntity> pageList;
	private List<GraduationEvaluationResultEntity> resultList;
	private List<Map<String, Object>> summaryResult;
	private String id;
	private String[] ids;
	
	/**
	 * 
	* @Title: editSetting 
	* @Description: TODO(查询毕业评价设置) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getSetting() {
		model = graduationEvaluationService.getGraduationSetting("4");
		model2 = graduationEvaluationService.getGraduationSetting("3");
		return "getSetting";
	}
	
	/**
	 * 
	* @Title: editSetting 
	* @Description: TODO(保存毕业评价设置) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String editSetting() {
		if(model != null){
			graduationEvaluationService.modifyGraduationSetting(model);
			getValueStack().set(DATA, getMessage());
		}
		if(model2 != null){
			graduationEvaluationService.modifyGraduationSetting(model2);
			getValueStack().set(DATA, getMessage());
		}
		return DATA;
	}

	/**
	 * 
	* @Title: searchList 
	* @Description: TODO(毕业评价查询列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String searchList() {
		if (query == null) {
			query = new GraduationEvaluationQuery();
		}
		query.setCondition(this.getCondition(null));
		pageList = graduationEvaluationService.getGraduationEvaluationList(query);
		return "searchList";
	}
	
	/**
	 * 
	* @Title: getResultByPjid 
	* @Description: TODO(通过评价ID或许评价结果集合) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String getResultByPjid() {
		resultList = graduationEvaluationService.getGraduationEvaluationResultByPjid(id);
		return "getResultByPjid";
	}
	
	/**
	 * 
	* @Title: count 
	* @Description: TODO(毕业前评价统计) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String summary(){
		if(query == null){
			query = new GraduationEvaluationQuery();
			query.setNj(DateTimeUtil.getFormatDate(DateTimeUtil.getCurrDate(), "yyyy"));
			query.setZbx(GraduationEvaluationIndex.ZBX_TEACHER.getKey());
		}
		query.setEndRow(20);
		summaryResult = graduationEvaluationService.summartyGraduationEvaluationResult(query);
		StringBuffer data = new StringBuffer();
		String str1 = "{name : '",str2 = "',value : ",str3 = ",color:'",str4 = "'},";
		String[] colorArray = new String[]{"#4572a7","#aa4643","#89a54e","#80699b","#3d96ae","#3895BF","#2A962A","#9F2626","#4F7DE7","#7A3C9C","#B97944","#782A56"};
		int i = 0;
		if(summaryResult != null && summaryResult.size() > 0){
			for(Map<String, Object> map : summaryResult){
				data.append(str1).append(map.get("DA")).append(str2).append(map.get("ZB")).append(str3).append(colorArray[i]).append(str4);
				i++;
				if(i == 5){
					break;
				}
			}
			// 组装键值对
			getValueStack().set("data", data.toString().substring(0, data.length()-1));
		}else{
			for(int j=0;j<5;j++){
				data.append(str1).append("无").append(str2).append("0").append(str3).append(colorArray[i]).append(str4);
				if(i == 5){
					break;
				}
			}
			// 组装键值对
			getValueStack().set("data", data.toString().substring(0, data.length()-1));
		}
		if(GraduationEvaluationIndex.ZBX_TEACHER.getKey().equals(query.getZbx())){
			getValueStack().set("titleText", "【"+query.getNj()+"年毕业学生最喜欢的五位教师】");
		}
		if(GraduationEvaluationIndex.ZBX_HARVESTLESSON.getKey().equals(query.getZbx())){
			getValueStack().set("titleText", "【"+query.getNj()+"年毕业学生收获最大的五门课程】");
		}
		if(GraduationEvaluationIndex.ZBX_HARDLESSON.getKey().equals(query.getZbx())){
			getValueStack().set("titleText", "【"+query.getNj()+"年毕业学生认为最难掌握的五门课程】");
		}
		if(GraduationEvaluationIndex.ZBX_MAJOR.getKey().equals(query.getZbx())){
			getValueStack().set("titleText", "【"+query.getNj()+"年毕业学生对专业的认可程度】");
		}
		if(GraduationEvaluationIndex.ZBX_STUDY.getKey().equals(query.getZbx())){
			getValueStack().set("titleText", "【"+query.getNj()+"年毕业学生对大学学习的满意程度 】");
		}
		getValueStack().set("fillText", "按评价数量比例排名");
		getValueStack().set("fillText2", "评价数量/评价总数");
		getValueStack().set("indexEnums", GraduationEvaluationIndex.values());
		return "summary";
	}
	
// ============================================================================================================================

	/**
     * 查询条件
     * @return
     */
    private String getCondition(String status) {
        String express = "";
        String deptFilter = DeptFilterUtil.getCondition("x", "xy");
        if(!StringUtil.isEmpty(deptFilter)){
            express += " and (" + deptFilter + ")";
        }
        // 状态条件
        if(StringUtil.isNotEmpty(status)){
        	express += status;
        }
        return express;
    }

	public IGraduationEvaluationService getGraduationEvaluationService() {
		return graduationEvaluationService;
	}

	public void setGraduationEvaluationService(
			IGraduationEvaluationService graduationEvaluationService) {
		this.graduationEvaluationService = graduationEvaluationService;
	}

	public GraduationEvaluationQuery getQuery() {
		return query;
	}

	public void setQuery(GraduationEvaluationQuery query) {
		this.query = query;
	}

	public GraduationEvaluationEntity getModel() {
		return model;
	}

	public void setModel(GraduationEvaluationEntity model) {
		this.model = model;
	}
	
	public PageList<GraduationEvaluationEntity> getPageList() {
		return pageList;
	}

	public void setPageList(PageList<GraduationEvaluationEntity> pageList) {
		this.pageList = pageList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public GraduationEvaluationResultEntity getResultModel() {
		return resultModel;
	}

	public void setResultModel(GraduationEvaluationResultEntity resultModel) {
		this.resultModel = resultModel;
	}

	public List<GraduationEvaluationResultEntity> getResultList() {
		return resultList;
	}

	public void setResultList(List<GraduationEvaluationResultEntity> resultList) {
		this.resultList = resultList;
	}

	public GraduationEvaluationEntity getModel2() {
		return model2;
	}

	public void setModel2(GraduationEvaluationEntity model2) {
		this.model2 = model2;
	}

	public List<Map<String, Object>> getSummaryResult() {
		return summaryResult;
	}

	public void setSummaryResult(List<Map<String, Object>> summaryResult) {
		this.summaryResult = summaryResult;
	}
    
}
