package com.zfsoft.wjdc_xc.action;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.zfsoft.common.spring.SpringHolder;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.core.util.DownloadFilenameUtil;
import com.zfsoft.hrm.normal.info.entity.OverallInfo;
import com.zfsoft.hrm.normal.info.service.svcinterface.IOverallInfoService;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.wjdc_xc.entites.InspectionConfig;
import com.zfsoft.wjdc_xc.entites.InspectionLevelEnum;
import com.zfsoft.wjdc_xc.entites.InspectionTask;
import com.zfsoft.wjdc_xc.entites.InspectionTaskMember;
import com.zfsoft.wjdc_xc.entites.InspectionTypeEnum;
import com.zfsoft.wjdc_xc.query.InspectionSummerQuery;
import com.zfsoft.wjdc_xc.query.InspectionTaskQuery;
import com.zfsoft.wjdc_xc.service.IInspectionConfigService;
import com.zfsoft.wjdc_xc.service.IInspectionTaskService;

/**
 * 
 * @author ChenMinming
 * @date 2015-6-9
 * @version V1.0.0
 */
public class InspectionTaskAction extends HrmAction{

	private static final long serialVersionUID = 6219910320036556383L;
	private InspectionTask inspectionTask = new InspectionTask();
	private IInspectionTaskService inspectionTaskService;
	private IInspectionConfigService inspectionConfigService;
	private InspectionTaskQuery query= new InspectionTaskQuery();
	private PageList<InspectionTask> pageList;
	private PageList<Map<String, Object>> taskSummerList;
	private PageList<InspectionTaskMember> taskMemberList = new PageList<InspectionTaskMember>();
	private InspectionTaskMember takeMember;
	private String taskId;
	private String type="XNXC";
	private String rwjb = "dept";
	private String rwbm = "";
	
	private InspectionSummerQuery summerQuery = new InspectionSummerQuery();
	
	/**
	 * 
	* @Title: list 
	* @Description: TODO(评价任务列表) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String list(){
		rwbm = this.getUser().getBmdm_id();
		query.setConfigType(type);
		query.setRwjb(rwjb);
		query.setOrderStr("taskDate desc");
		
		// 获取配置信息对象
		InspectionConfig config = new InspectionConfig();
		config.setType(type);
		config.setYwjb(rwjb);
		// 如果是校级则使用同一个配置
		if(InspectionLevelEnum.LEVEL_DEPT.getKey().equals(rwjb)){
			query.setRwbm(rwbm);
			config.setYwbm(rwbm);
		}
		pageList = inspectionTaskService.getPagingList(query);
		getValueStack().set("config",inspectionConfigService.findConfig(config));
		return "page";
	}
	
	
	public String taskSummer(){
		getValueStack().set("taskSummerList",inspectionTaskService.getTaskSummerPage(summerQuery));
		return "taskSummer";
	}
	
	public String resultSummer(){
		getValueStack().set("resultSummerList",inspectionTaskService.getResultPagingList(summerQuery));
		return "resultSummer";
	}
	
	/**
     * 查询评价成员列表
     * @return
     * @throws Exception
     */
	public String searchMember(){
		getValueStack().set("memberList",inspectionTaskService.getTaskMemberList(query));
		return "searchMember";
	}
	
	/**
     * 导出被评价结果记录列表
     * @return
     * @throws Exception
     */
    public String export() throws Exception{
    	Map<String,String> map = new HashMap<String,String>();
    	if(StringUtil.isNotEmpty(taskId)){
        	inspectionTask = inspectionTaskService.findById(taskId);
        	map.put("taskId", taskId);
        }
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, inspectionTask.getTaskDateStr()+ "-" + inspectionTask.getWjText()+".xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet(inspectionTask.getTaskDateStr()+ "-" + inspectionTask.getWjText(), 1);
        summerQuery.setPerPageSize(Integer.MAX_VALUE);
        summerQuery.setParams(map);
        taskSummerList = inspectionTaskService.getTaskSummerPage(summerQuery);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "职工号/部门编号"));
        sheet.addCell(generateTheadLabel(1, 0, "被评价对象"));
        sheet.addCell(generateTheadLabel(2, 0, "评价人数"));
        sheet.addCell(generateTheadLabel(3, 0, "评价得分"));	

        //产生内容
        int y = 0;
        for(Map<String, Object> m : taskSummerList){
            y++;
            sheet.addCell(generateValueLabel(0, y, m.get("DCDX")));
            sheet.addCell(generateValueLabel(1, y, m.get("DCDXMC")));
            sheet.addCell(generateValueLabel(2, y, m.get("NUM")));
            Object fz = m.get("FZ");
            Object num = m.get("NUM");
            DecimalFormat df = new DecimalFormat("#.00");   
            sheet.addCell(generateValueLabel(3, y, df.format(Double.valueOf(fz.toString())/Double.valueOf(num.toString()))));
        }
        wwb.write();
        wwb.close();
        return null;
    }
    
    /**
     * 导出评价人员记录列表
     * @return
     * @throws Exception
     */
    public String exportMember() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "评价人员列表.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet(inspectionTask.getTaskDateStr()+ "-" + inspectionTask.getWjText(), 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        taskMemberList = inspectionTaskService.getTaskMemberList(query);
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "职工号/学号"));
        sheet.addCell(generateTheadLabel(1, 0, "姓名"));
        sheet.addCell(generateTheadLabel(2, 0, "学院"));
        sheet.addCell(generateTheadLabel(3, 0, "专业"));	
        sheet.addCell(generateTheadLabel(4, 0, "行政班"));	

        //产生内容
        int y = 0;
        for(InspectionTaskMember m : taskMemberList){
            y++;
            sheet.addCell(generateValueLabel(0, y, m.getGh()));
            sheet.addCell(generateValueLabel(1, y, m.getXm()));
            sheet.addCell(generateValueLabel(2, y, m.getXy()));
            sheet.addCell(generateValueLabel(3, y, m.getZy()));
            sheet.addCell(generateValueLabel(4, y, m.getXzb()));
        }
        wwb.write();
        wwb.close();
        return null;
    }
	
    /**
     * 
    * @Title: save 
    * @Description: TODO(保存评价任务) 
    * @param @return
    * @param @throws UnsupportedEncodingException    设定文件 
    * @return String    返回类型 
    * @throws
     */
	public String save() throws UnsupportedEncodingException{
		if(inspectionTask != null && StringUtil.isNotEmpty(inspectionTask.getConfigType())){
			List<OverallInfo> overallInfoList = null;
			InspectionConfig bean = new InspectionConfig();
			bean.setType(inspectionTask.getConfigType());
			bean.setYwjb(inspectionTask.getRwjb());
			bean.setYwbm(inspectionTask.getRwbm());
			InspectionConfig inspectionConfig = inspectionConfigService.findConfig(bean);
			// 学生结课评价情况写死判断
			if(inspectionConfig != null && InspectionTypeEnum.TYPE_XSJKPJ.getKey().equals(inspectionConfig.getType())){
				// 插入任务记录
				inspectionTaskService.insert(inspectionTask);
				// 保存评价学生记录
				Map<String, String> param = new HashMap<String, String>();
				param.put("xn", this.DEFULT_XN);
				param.put("xq", this.DEFULT_XQ);
				param.put("taskId", inspectionTask.getId());
				inspectionTaskService.saveMemberOfJkpj(param);
			}else{
				// 如果是靠关系来找评价人和被评价对象
				if(inspectionConfig != null && "sql_append".equals(inspectionConfig.getKey())){
					String sql = "1=1";
					sql=inspectionConfig.getAppend();
					sql=sql.replaceAll("\\$\\{[^\\}]*\\}", "'"+getUser().getYhm()+"'");
					IOverallInfoService overallInfoService = SpringHolder.getBean("overallInfoService", IOverallInfoService.class);
					overallInfoList=overallInfoService.userListThink(null, sql);
				}
				//指定了评价对象
				inspectionTaskService.save(inspectionTask,overallInfoList);
			}
		}
		getValueStack().set(DATA,getMessage());
		return DATA;
	}
	
	public String update(){
		// 修改和问卷ID状态
		inspectionTaskService.update(inspectionTask);
		// 生成评价结果记录
		
		getValueStack().set(DATA,getMessage());
		return DATA;
	}
	
	public String remove(){
		inspectionTask=inspectionTaskService.findById(inspectionTask.getId());
		if(inspectionTask.getDxNum()>0){
			setErrorMessage("不允许删除已经存在提交记录的信息！");
			getValueStack().set(DATA,getMessage());
			return DATA;
		}
		inspectionTaskService.remove(inspectionTask);
		getValueStack().set(DATA,getMessage());
		return DATA;
	}
	
	/**
	 * 
	* @Title: detail 
	* @Description: TODO(新增评价任务) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String detail(){
		if(StringUtil.isNotEmpty(inspectionTask.getId())){
			inspectionTask=inspectionTaskService.findById(inspectionTask.getId());
		}else{
			inspectionTask.setTaskDate(new Date());
			inspectionTask.setConfigType(type);
			inspectionTask.setRwjb(rwjb);
			// 如果不是院级任务则不需要部门作为查询条件
			if(InspectionLevelEnum.LEVEL_DEPT.getKey().equals(rwjb)){
				inspectionTask.setRwbm(this.getUser().getBmdm_id());
			}else{
				inspectionTask.setRwbm("0");
			}
			inspectionTask.setPjdxlx("lesson");
		}
		InspectionConfig config = new InspectionConfig();
		config.setType(inspectionTask.getConfigType());
		// 如果不是院级任务则不需要部门作为查询条件
		if(InspectionLevelEnum.LEVEL_DEPT.getKey().equals(rwjb)){
			config.setYwbm(this.getUser().getBmdm_id());
		}else{
			config.setYwbm("0");
		}
		config.setYwjb(inspectionTask.getRwjb());
		getValueStack().set("personList", inspectionConfigService.getCheckedPersonList(inspectionTask.getConfigType(), inspectionTask.getRwjb(), inspectionTask.getRwbm()));
		getValueStack().set("dcwjList", inspectionConfigService.getCheckedDcwjList(inspectionTask.getConfigType(), inspectionTask.getRwjb(), inspectionTask.getRwbm()));
		getValueStack().set("config", inspectionConfigService.findConfig(config));
		return "detail";
	}
	
	/**
	 * 设置评价对象
	 * @return
	 */
	public String chooseLesson(){
		if(StringUtil.isNotEmpty(taskId)){
			inspectionTask = inspectionTaskService.findById(taskId);
			query.setTaskDate(inspectionTask.getTaskDateStr());
			// 如果是学院任务，则只取开课学院是本学院的课程
			if(InspectionLevelEnum.LEVEL_DEPT.getKey().equals(inspectionTask.getRwjb())){
				query.setRwbm(inspectionTask.getRwbm());
			}
			getValueStack().set("lessonList", inspectionTaskService.getLessonList(query));
		}
		getValueStack().set("inspectionTask", inspectionTask);
		return "chooseLesson";
	}
	
	/**
	 * 查询汇总结课评价信息
	 * @return
	 */
	public String sumMemberResult(){
		query.setXn(this.DEFULT_XN);
		query.setXq(this.DEFULT_XQ);
		// 默认查询从未评价的人员列表
		if(StringUtil.isEmpty(query.getZt())){
			query.setZt("0");
		}
		this.getResultList();
		return "sumMemberResult";
	}

    /**
     * 导出评价汇总查询结果
     * @return
     * @throws Exception
     */
	public String exportSumResult() throws Exception{
		getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "评价汇总查询结果.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("评价汇总查询结果", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        this.getResultList();
        
        //产生表头
        sheet.addCell(generateTheadLabel(0, 0, "学年"));
        sheet.addCell(generateTheadLabel(1, 0, "学期"));
        sheet.addCell(generateTheadLabel(2, 0, "职工号/学号"));
        sheet.addCell(generateTheadLabel(3, 0, "姓名"));
        sheet.addCell(generateTheadLabel(4, 0, "学院"));
        sheet.addCell(generateTheadLabel(5, 0, "专业"));	
        sheet.addCell(generateTheadLabel(6, 0, "行政班"));	
        sheet.addCell(generateTheadLabel(7, 0, "评价总数量"));
        sheet.addCell(generateTheadLabel(8, 0, "已评价数量"));
        sheet.addCell(generateTheadLabel(9, 0, "未评价数量"));

        //产生内容
        int y = 0;
        for(InspectionTaskMember m : taskMemberList){
            y++;
            sheet.addCell(generateValueLabel(0, y, m.getXn()));
            sheet.addCell(generateValueLabel(1, y, m.getXq()));
            sheet.addCell(generateValueLabel(2, y, m.getGh()));
            sheet.addCell(generateValueLabel(3, y, m.getXm()));
            sheet.addCell(generateValueLabel(4, y, m.getXy()));
            sheet.addCell(generateValueLabel(5, y, m.getZy()));
            sheet.addCell(generateValueLabel(6, y, m.getXzb()));
            sheet.addCell(generateValueLabel(7, y, m.getPjzsl()));
            sheet.addCell(generateValueLabel(8, y, m.getYpjsl()));
            sheet.addCell(generateValueLabel(9, y, m.getWpjsl()));
        }
        wwb.write();
        wwb.close();
        return null;
	}
	
	/*
	 * 获取评价结果列表
	 */
	private void getResultList() {
		// 如果是学生结课评价
		if("XSJKPJ".equals(type)){
			// 如果是从未评价
			if("0".equals(query.getZt())){
				query.setCondition(" and t.pjzsl=t.wpjsl");
			}
			// 如果是部分评价
			if("1".equals(query.getZt())){
				query.setCondition(" and t.wpjsl > 0 and t.ypjsl > 0");
			}
			// 如果是全部评价
			if("2".equals(query.getZt())){
				query.setCondition(" and t.pjzsl = t.ypjsl and t.pjzsl > 0");
			}
			taskMemberList = inspectionTaskService.getEndingClassSumResult(query);
		}
	}
	
	/**
	 * 返回
	 */
	public InspectionTask getInspectionTask() {
		return inspectionTask;
	}
	/**
	 * 设置
	 * @param inspectionTask 
	 */
	public void setInspectionTask(InspectionTask inspectionTask) {
		this.inspectionTask = inspectionTask;
	}
	/**
	 * 返回
	 */
	public PageList<InspectionTask> getPageList() {
		return pageList;
	}
	/**
	 * 设置
	 * @param pageList 
	 */
	public void setPageList(PageList<InspectionTask> pageList) {
		this.pageList = pageList;
	}
	/**
	 * 返回
	 */
	public InspectionTaskQuery getQuery() {
		return query;
	}
	/**
	 * 设置
	 * @param query 
	 */
	public void setQuery(InspectionTaskQuery query) {
		this.query = query;
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
	 * @param inspectionConfigService 
	 */
	public void setInspectionConfigService(
			IInspectionConfigService inspectionConfigService) {
		this.inspectionConfigService = inspectionConfigService;
	}

	/**
	 * 返回
	 */
	public InspectionSummerQuery getSummerQuery() {
		return summerQuery;
	}

	/**
	 * 设置
	 * @param summerQuery 
	 */
	public void setSummerQuery(InspectionSummerQuery summerQuery) {
		this.summerQuery = summerQuery;
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

	public PageList<Map<String, Object>> getTaskSummerList() {
		return taskSummerList;
	}

	public void setTaskSummerList(PageList<Map<String, Object>> taskSummerList) {
		this.taskSummerList = taskSummerList;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public PageList<InspectionTaskMember> getTaskMemberList() {
		return taskMemberList;
	}

	public void setTaskMemberList(PageList<InspectionTaskMember> taskMemberList) {
		this.taskMemberList = taskMemberList;
	}

	public String getRwjb() {
		return rwjb;
	}

	public void setRwjb(String rwjb) {
		this.rwjb = rwjb;
	}

	public String getRwbm() {
		return rwbm;
	}

	public void setRwbm(String rwbm) {
		this.rwbm = rwbm;
	}


	public InspectionTaskMember getTakeMember() {
		return takeMember;
	}


	public void setTakeMember(InspectionTaskMember takeMember) {
		this.takeMember = takeMember;
	}

}
