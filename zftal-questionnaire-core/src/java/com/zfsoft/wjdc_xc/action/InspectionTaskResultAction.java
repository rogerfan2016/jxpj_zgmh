package com.zfsoft.wjdc_xc.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.common.system.SubSystemHolder;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc.dao.entites.WjglModel;
import com.zfsoft.wjdc.service.svcinterface.IQuestionNaireService;
import com.zfsoft.wjdc.service.svcinterface.IStglService;
import com.zfsoft.wjdc.service.svcinterface.IWjglService;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.entites.InspectionTaskResult;
import com.zfsoft.wjdc_xc.entites.InspectionTypeEnum;
import com.zfsoft.wjdc_xc.query.InspectionTaskResultQuery;
import com.zfsoft.wjdc_xc.service.IInspectionTaskResultService;
import com.zfsoft.wjdc_xc.service.IInspectionTaskService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-12
 * @version V1.0.0
 */
public class InspectionTaskResultAction extends HrmAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5691816845344518185L;
	private InspectionTaskResultQuery query = new InspectionTaskResultQuery();
	private InspectionTaskResult inspectionTaskResult;
	private IInspectionTaskService inspectionTaskService;
	private IInspectionTaskResultService inspectionTaskResultService;
	private PageList<InspectionTaskResult> pageList = new PageList<InspectionTaskResult>();
	private List<InspectionTaskResult> list = new ArrayList<InspectionTaskResult>();
	
	private IStglService stglService;
	private IWjglService wjglService;
	private IQuestionNaireService questionNaireService;
	private String type="XNXC";
	private String rwjb = "dept";
	
	/**
	 * 手机端录入的评价列表展示
	 * @return
	 */
	public String list(){
		query.setConfigType(type);
		query.setGh(getUser().getYhm());
		if(query.getStatus()==null){
			query.setStatus("0");
		}
		query.setPerPageSize(6);
		pageList=inspectionTaskResultService.getPagingList(query);
		getValueStack().set("pageInfo",pageList.getPaginator());
		getValueStack().set("type",type);
		return "list";
	}
	
	/**
	 * 
	* @Title: evaluationChoose 
	* @Description: TODO(显示要评价的授课列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String evaluationChoose(){
		// 取得教学楼信息
		getValueStack().set("jxlList", inspectionTaskResultService.getJxlList(query));
		InspectionTaskMember memberQuery = new InspectionTaskMember();
		memberQuery.setGh(getUser().getYhm());
		// 取任务天数配置
		String lectures_audit = SubSystemHolder.getPropertiesValue("task_day_no");
		if(StringUtil.isNotEmpty(lectures_audit)){
			memberQuery.setQueryDayNum(Integer.valueOf(lectures_audit).intValue()); // 取多少天的任务
		}
		memberQuery.setConfigType(type);
		getValueStack().set("taskList", inspectionTaskService.getMemberList(memberQuery));
		return "choose";
	}
	
	/**
	 * 
	* @Title: skddList 
	* @Description: TODO(获取上课地点列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String skddList() {
		getValueStack().set("data", inspectionTaskResultService.getSkddListByJxl(query));
		return DATA;
	}
	
	/**
	 * 添加评价记录
	 * @return
	 */
	public String choose(){
		InspectionTaskMember memberQuery = new InspectionTaskMember();
		memberQuery.setGh(getUser().getYhm());
		// 取任务天数配置
		String lectures_audit = SubSystemHolder.getPropertiesValue("task_day_no");
		if(StringUtil.isNotEmpty(lectures_audit)){
			memberQuery.setQueryDayNum(Integer.valueOf(lectures_audit).intValue()); // 取多少天的任务
		}
		memberQuery.setConfigType(type);
		getValueStack().set("taskList", inspectionTaskService.getMemberList(memberQuery));
		return "choose";
	}
	
	/**
	 * 
	* @Title: create 
	* @Description: TODO(保存评价记录) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String create(){
		InspectionTaskMember memberQuery = new InspectionTaskMember();
		memberQuery.setId(inspectionTaskResult.getMemberId());
		memberQuery.setConfigType(type);
		List<InspectionTaskMember> mList = inspectionTaskService.getMemberList(memberQuery);
		if(mList==null||mList.size()<1||!getUser().getYhm().equals(mList.get(0).getGh())){
			setErrorMessage("指定巡查任务不存在或您没有被分配到该任务！");
			getValueStack().set(DATA, getMessage());
			return DATA;
		}
		query.setDcdx(inspectionTaskResult.getDcdx());
		query.setConfigType(type);
		query.setMemberId(inspectionTaskResult.getMemberId());
		if(inspectionTaskResultService.findCount(query)>0){
			setErrorMessage("该对象已存在相应巡查任务的记录！");
			getValueStack().set(DATA, getMessage());
			return DATA;
		}
		inspectionTaskResult.setWjid(mList.get(0).getWjid());
		inspectionTaskResult.setDcr(this.getUser().getYhm());
		inspectionTaskResultService.insert(inspectionTaskResult); 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		map.put("text", getMessage().getText());
		map.put("html", getMessage().getHtml());
		map.put("resultId", inspectionTaskResult.getId());
		getValueStack().set(DATA, map);
		return DATA;
	}
	
	/**
	 * 
	* @Title: detail 
	* @Description: TODO(显示评价问卷详细内容) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String detail(){
		try {
			inspectionTaskResult = inspectionTaskResultService.findById(inspectionTaskResult.getId());
			WjglModel wjm=wjglService.getModel(inspectionTaskResult.getWjid());
			if(wjm.getDjrid()==null||"".equals(wjm.getDjrid())){
				wjm.setDjrid(getUser().getYhm());//设置答卷人id
			}
			wjm.setDjrid(inspectionTaskResult.getId());
			WjglModel yhdjxx=stglService.getYhdjxx(wjm);
			wjm.setDjzt(yhdjxx.getDjzt());
			getValueStack().set("wjModel", wjm);
			
		} catch (Exception e) {
			logException(e);
		}		
		return "detail";
	}
	/**
	 * 
	* @Title: saveWjDa 
	* @Description: TODO(保存问卷答案) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String saveWjDa(){	
		HttpServletRequest request = getRequest();
		try {
			inspectionTaskResult = inspectionTaskResultService.findById(inspectionTaskResult.getId());
			WjglModel wjModel=wjglService.getModel(inspectionTaskResult.getWjid());
			wjModel.setDjrid(inspectionTaskResult.getId());
			WjglModel wjglModel=stglService.getYhdjxx(wjModel);
			
			if(wjglModel!=null&&!"1".equals(inspectionTaskResult.getRwzt())){
				getValueStack().set("result", "该问卷任务已停止，不能修改或提交答卷！");
			}else{
				query.setGh(getUser().getYhm());
				query.setConfigType(type);
				query.setMemberId(inspectionTaskResult.getMemberId());
				if(inspectionTaskResultService.findCount(query)<1){
					getValueStack().set("result", "您无权提交他人的问卷！");
				}else{
					// 保存问卷答案
					String result = stglService.saveWjDa(request, wjModel);
					// 计算总分并更新到答卷记录中
					int zf = inspectionTaskResultService.getFzSum(inspectionTaskResult.getId());
					// 修改评价记录信息
					inspectionTaskResult.setStatus("1");
					inspectionTaskResult.setZf(String.valueOf(zf));
					inspectionTaskResultService.updateStatus(inspectionTaskResult);
					if("I99001".equals(result) || "I99002".equals(result)){
						result = getText(result);
					}
					getValueStack().set("result",result );
				}
			}
		} catch (Exception e) {
			logException(e);
		}
		return detail();
	}
	
	public String remove() throws Exception{
		inspectionTaskResultService.remove(inspectionTaskResult);
		getValueStack().set(DATA, getMessage()); 
		return DATA;
	}
	
//==============================================================使用了通用评价功能==============================================================
	
	/**
	 * 手机端-通用评价列表
	 * @return
	 */
	public String endingClassList(){
		query.setConfigType(type);
		query.setGh(getUser().getYhm());
		if(query.getStatus()==null){
			query.setStatus("0");
		}
		query.setPerPageSize(6);
		
		pageList=inspectionTaskResultService.getPagingList(query);
		// 如果获取未评价状态数据
		if("0".equals(query.getStatus())){
			// 获取评价任务和人员信息
			List<Map<String,String>> taskMember = inspectionTaskResultService.findTaskMemberByParam(query);
			if((pageList == null || pageList.size() == 0) && "0".equals(query.getStatus()) && taskMember != null){
				for(Map<String,String> task : taskMember){
					Map<String,String> map = new HashMap<String,String>();
					map.put("memberid", task.get("ID"));
					map.put("kcid", task.get("PJDX"));
					map.put("xn", this.DEFULT_XN);
					map.put("xq", this.DEFULT_XQ);
					map.put("gh", getUser().getYhm());
					map.put("type", type);
					inspectionTaskResultService.insertEndingClassResult(map);
				}				
				// 插入后重新查询列表
				pageList=inspectionTaskResultService.getPagingList(query);
			}
		}
		
		getValueStack().set("pageInfo",pageList.getPaginator());
		if(InspectionTypeEnum.TYPE_XSJKPJ.getKey().equals(type)){
			getValueStack().set("title","结课评价");
		}else{
			getValueStack().set("title","听课评价");
		}
		return "endingClassList";
	}
	
	/**
	 * 
	* @Title: endingClassDetail 
	* @Description: TODO(显示结课评价问卷详细内容) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String endingClassDetail(){
		String yhm = this.getUser().getYhm();
		try {
			// 通过评价人和问卷ID查询问卷
			WjglModel wjm = wjglService.getModel(inspectionTaskResult.getId());
			if(wjm == null){
				// 获取课程分类
				Map<String,String> kcfl = inspectionTaskResultService.getKcfl(inspectionTaskResult.getKcdm());
                // 通过课程分类获取问卷模板
                Map<String,String> map = new HashMap<String,String>();
                // 如果是结课评价
                String title = "",djrylx = "";
                if(InspectionTypeEnum.TYPE_XSJKPJ.getKey().equals(type)){
                	map.put("kcfl", kcfl.get("FLDM"));
                    map.put("djrylx", "0");
                    map.put("pjlx", InspectionTypeEnum.TYPE_XSJKPJ.getKey());
                    djrylx = "student";
                    title = InspectionTypeEnum.TYPE_XSJKPJ.getText();
                }else{
                	// 如果是院级专家评价
                	if(InspectionTypeEnum.TYPE_YJZJPJ.getKey().equals(type)){
                		map.put("kcfl", kcfl.get("ZJPJFL"));
                        map.put("pjlx", InspectionTypeEnum.TYPE_YJZJPJ.getKey());
                        title = InspectionTypeEnum.TYPE_YJZJPJ.getText();
                	}
                	// 如果是校级专家评价
                	if(InspectionTypeEnum.TYPE_XJZJPJ.getKey().equals(type)){
                		map.put("kcfl", kcfl.get("ZJPJFL"));
                        map.put("pjlx", InspectionTypeEnum.TYPE_XJZJPJ.getKey());
                        title = InspectionTypeEnum.TYPE_XJZJPJ.getText();
                	}
                	// 如果是院级督导评价
                	if(InspectionTypeEnum.TYPE_YJDDPJ.getKey().equals(type)){
                		map.put("kcfl", kcfl.get("DDPJFL"));
                        map.put("pjlx", InspectionTypeEnum.TYPE_YJDDPJ.getKey());
                        title = InspectionTypeEnum.TYPE_YJDDPJ.getText();
                	}
                	// 如果是校级督导评价
                	if(InspectionTypeEnum.TYPE_XJDDPJ.getKey().equals(type)){
                		map.put("kcfl", kcfl.get("DDPJFL"));
                        map.put("pjlx", InspectionTypeEnum.TYPE_XJDDPJ.getKey());
                        title = InspectionTypeEnum.TYPE_XJDDPJ.getText();
                	}
                	// 如果是校外督导评价
                	if(InspectionTypeEnum.TYPE_XWDDPJ.getKey().equals(type)){
                		map.put("kcfl", kcfl.get("DDPJFL"));
                        map.put("pjlx", InspectionTypeEnum.TYPE_XWDDPJ.getKey());
                        title = InspectionTypeEnum.TYPE_XWDDPJ.getText();
                	}
                	map.put("djrylx", "1");
                	djrylx = "teacher";
                }
                Map<String,String> wjpz = inspectionTaskResultService.getWjpz(map);
                if(wjpz != null){
                	String[] member = new String[1];
                	member[0] = yhm;
                	questionNaireService.doDistribute(wjpz.get("WJID"), inspectionTaskResult.getId(), title, member, djrylx, "", "");
                	wjm = wjglService.getModel(inspectionTaskResult.getId());
                }				
			}
			if(wjm.getDjrid()==null||"".equals(wjm.getDjrid())){
				wjm.setDjrid(yhm);//设置答卷人id
			}
			WjglModel yhdjxx=stglService.getYhdjxx(wjm);
			wjm.setDjzt(yhdjxx.getDjzt());
			getValueStack().set("wjModel", wjm);
			getValueStack().set("type", type);
		} catch (Exception e) {
			logException(e);
		}		
		return "endingClassDetail";
	}
	
	/**
	 * 
	* @Title: saveEndingClassWjDa 
	* @Description: TODO(保存结课评价问卷答案) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String saveEndingClassWjDa(){	
		HttpServletRequest request = getRequest();
		try {
			inspectionTaskResult = inspectionTaskResultService.findById(inspectionTaskResult.getId());
			WjglModel wjModel=wjglService.getModel(inspectionTaskResult.getId());
			wjModel.setDjrid(getUser().getYhm());
			WjglModel wjglModel=stglService.getYhdjxx(wjModel);
			
			if(wjglModel!=null&&!"1".equals(inspectionTaskResult.getRwzt())){
				getValueStack().set("result", "该问卷任务已停止，不能修改或提交答卷！");
			}else{
				// 保存问卷答案
				String result = stglService.saveWjDa(request, wjModel);
				// 计算总分并更新到答卷记录中
				int zf = inspectionTaskResultService.getFzSum(inspectionTaskResult.getId());
				// 修改评价记录信息
				inspectionTaskResult.setStatus("1");
				inspectionTaskResult.setZf(String.valueOf(zf));
				inspectionTaskResultService.updateStatus(inspectionTaskResult);
				if("I99001".equals(result) || "I99002".equals(result)){
					result = getText(result);
				}
				getValueStack().set("result",result );
				getValueStack().set("type",type );
			}
		} catch (Exception e) {
			logException(e);
		}
		return endingClassDetail();
	}
//=================================================================================================================================	
	
	/**
	 * 返回
	 */
	public InspectionTaskResultQuery getQuery() {
		return query;
	}

	/**
	 * 设置
	 * @param query 
	 */
	public void setQuery(InspectionTaskResultQuery query) {
		this.query = query;
	}

	/**
	 * 返回
	 */
	public InspectionTaskResult getInspectionTaskResult() {
		return inspectionTaskResult;
	}

	/**
	 * 设置
	 * @param inspectionTaskResult 
	 */
	public void setInspectionTaskResult(InspectionTaskResult inspectionTaskResult) {
		this.inspectionTaskResult = inspectionTaskResult;
	}

	/**
	 * 返回
	 */
	public PageList<InspectionTaskResult> getPageList() {
		return pageList;
	}

	/**
	 * 设置
	 * @param pageList 
	 */
	public void setPageList(PageList<InspectionTaskResult> pageList) {
		this.pageList = pageList;
	}

	/**
	 * 设置
	 * @param inspectionTaskService 
	 */
	public void setInspectionTaskService(
			IInspectionTaskService inspectionTaskService) {
		this.inspectionTaskService = inspectionTaskService;
	}

	/**
	 * 设置
	 * @param inspectionTaskResultService 
	 */
	public void setInspectionTaskResultService(
			IInspectionTaskResultService inspectionTaskResultService) {
		this.inspectionTaskResultService = inspectionTaskResultService;
	}

	/**
	 * 设置
	 * @param service 
	 */
	public void setStglService(IStglService stglService) {
		this.stglService = stglService;
	}

	/**
	 * 设置
	 * @param wjglService 
	 */
	public void setWjglService(IWjglService wjglService) {
		this.wjglService = wjglService;
	}

	public void setQuestionNaireService(IQuestionNaireService questionNaireService) {
		this.questionNaireService = questionNaireService;
	}

	/**
	 * 返回
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置
	 * @param type 
	 */
	public void setType(String type) {
		this.type = type;
	}

	public List<InspectionTaskResult> getList() {
		return list;
	}

	public void setList(List<InspectionTaskResult> list) {
		this.list = list;
	}

	public String getRwjb() {
		return rwjb;
	}

	public void setRwjb(String rwjb) {
		this.rwjb = rwjb;
	}
}
