package com.zfsoft.feedback.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.common.log.User;
import com.zfsoft.dao.page.PageList;
import com.zfsoft.evaluation.entity.CheckInSurveyEntity;
import com.zfsoft.evaluation.entity.CurriculumScheduleEntity;
import com.zfsoft.evaluation.entity.CurriculumScheduleQuery;
import com.zfsoft.evaluation.entity.TeachingEntity;
import com.zfsoft.evaluation.entity.ViewQuestionnaireEntity;
import com.zfsoft.evaluation.service.IEvaluationService;
import com.zfsoft.feedback.entity.CourseQuery;
import com.zfsoft.feedback.entity.FeedBackEntity;
import com.zfsoft.feedback.entity.FeedBackLogEntity;
import com.zfsoft.feedback.entity.FeedBackQuery;
import com.zfsoft.feedback.entity.FeedBackStaffEntity;
import com.zfsoft.feedback.entity.FeedBackStaffQuery;
import com.zfsoft.feedback.service.IFeedBackService;
import com.zfsoft.hrm.baseinfo.code.query.ItemQuery;
import com.zfsoft.hrm.baseinfo.code.service.svcinterface.IItemService;
import com.zfsoft.hrm.baseinfo.data.entity.InfoType;
import com.zfsoft.hrm.baseinfo.data.util.DataProcessInfoUtil;
import com.zfsoft.hrm.common.HrmAction;
import com.zfsoft.hrm.config.ICodeConstants;
import com.zfsoft.hrm.core.exception.RuleException;
import com.zfsoft.hrm.core.util.DownloadFilenameUtil;
import com.zfsoft.util.base.StringUtil;
import com.zfsoft.util.date.DateTimeUtil;

/**
 * 
 * @ClassName: FeedBackAction
 * @Description: TODO(信息反馈ACTION类)
 * @author rogerfan
 * @date 2016-6-13 下午03:05:06
 * 
 */
public class FeedBackAction extends HrmAction {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private IFeedBackService feedBackService;
	private FeedBackQuery query;
	private FeedBackEntity model;
	private File file;
	private String fileContentType;
	/**信息员管理*/
	private FeedBackStaffQuery staffQuery;
	private FeedBackStaffEntity staffEntity;
	private PageList<FeedBackStaffEntity> staffPageList;
	private IItemService itemService;
	private String staffIds;
	private String xxid;
	private PageList<FeedBackEntity> pageList;
	
	private String handle;
	private int totalSize;
    private int nowPage;
    private int perSize;

	/**========================================信息员管理========================================*/
	
	
	/**
	 * 信息员管理
	 */
	public String staffSearchList(){
		if(null == staffQuery){
			staffQuery = new FeedBackStaffQuery();
		}
		staffQuery.setSsbm(ICodeConstants.DM_DEF_XXYSSZ);
		staffPageList = feedBackService.getFeedBackStaffList(staffQuery);
		ItemQuery itemQuery = new ItemQuery();
		itemQuery.setCatalogId(ICodeConstants.DM_DEF_XXYSSZ);
		getValueStack().set("xxySszList", itemService.getList(itemQuery));
		return "staffSearchList";
	}
	
	/**
	 * 检查文件
	 * 
	 * @throws Exception
	 */
	private void checkFile() throws Exception {
		if (file == null) {
			throw new RuleException("文件没有接收成功");
		}
		if (file.length() == 0) {
			throw new RuleException("数据文件内容为空");
		}
		if (!fileContentType.equals("application/vnd.ms-excel")) {
			throw new RuleException("请确保数据文件格式为excel文件");
		}
	}

	/**
	 * 信息员导入
	 * 
	 * @return
	 */
	@ResponseBody
	public String dataImport() {
		try {
			DataProcessInfoUtil.setInfo(" 导入开始，请耐心等待", null);
			checkFile();
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			int rows = sheet.getRows();
			if (rows == 1) {
				DataProcessInfoUtil.setInfo("数据内容行数为0行", InfoType.WARN);
			}
			int step = 0;
			List<FeedBackStaffEntity> list = new ArrayList<FeedBackStaffEntity>();
			for (int m = 1; m < rows; m++) {
				DataProcessInfoUtil.setStep("数据入库:", ++step, rows-1);
				FeedBackStaffEntity entity = new FeedBackStaffEntity();
				//数据转换
				Cell cell[] = sheet.getRow(m);  
				if(cell.length == 3){
					entity.setXh(cell[0].getContents());
					entity.setSsz(cell[1].getContents());
					entity.setSfzz(cell[2].getContents());
					list.add(entity);
				}
			}
			feedBackService.saveStaffList(list);
		} catch (Exception e) {
			e.printStackTrace();
			DataProcessInfoUtil.setInfo(" " + e.getMessage(), InfoType.ERROR);
			DataProcessInfoUtil.setInfo(" 导入终止", null);
		} finally {
			DataProcessInfoUtil.setInfo("-1", null);
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DataProcessInfoUtil.clear();
		}
		return null;
	}

	/**
	 * 信息员导入excel模板
	 * 
	 * @return
	 * @throws Exception
	 */
	public void downloadTemplate() throws Exception {
		String excelFileName = "信息员导入.xls";
		getResponse().reset();
		getResponse().setCharacterEncoding("utf-8");
		getResponse().setContentType("application/vnd.ms-excel");
		String useragent = getRequest().getHeader("user-agent");
		String disposition = DownloadFilenameUtil.fileDisposition(useragent,
				excelFileName);
		getResponse().setHeader("Content-Disposition", disposition);
		WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
		WritableSheet sheet1 = wwb.createSheet(excelFileName, 0);
	    WritableCellFormat wcfF = new WritableCellFormat(NumberFormats.TEXT); //定义一个单元格样式
	    CellView cv = new CellView(); //定义一个列显示样式 
	    cv.setFormat(wcfF);//把定义的单元格格式初始化进去
	    cv.setSize(10*600);
		int i = 0;
		sheet1.addCell(generateTheadLabel(i, 0, "学号", "xh"));
		sheet1.setColumnView(i, cv);
		i++;
		sheet1.addCell(generateTheadLabel(i, 0, "所属组", "ssz"));
		sheet1.setColumnView(i, cv);
		i++;
		sheet1.addCell(generateTheadLabel(i, 0, "是否组长", "sfzz"));
		sheet1.setColumnView(i, cv);
		i++;
		sheet1.addCell(generateTheadLabel(i, 0, "学年", "xn"));
		sheet1.setColumnView(i, cv);
		i++;
		sheet1.addCell(generateTheadLabel(i, 0, "学期", "xq"));
		sheet1.setColumnView(i, cv);
		wwb.write();
		wwb.close();
	}

	/**
	 * 上传excel页面
	 * 
	 * @return
	 */
	public String uploadTemplate() {
		return "upload";
	}
	
	/**
	 * 删除信息员
	 * @param ids
	 * @return
	 */
	@ResponseBody
	public boolean delete(){
		try{
			if(StringUtils.isNotBlank(staffIds)){
				String[] ids = staffIds.split(",");
				feedBackService.deleteFeedBackStaffByIds(ids);
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 信息员编辑页面
	 * @return
	 */
	public String staffEdit(){
		if(StringUtils.isNotBlank(staffIds)){
			FeedBackStaffEntity entity = feedBackService.getFeedBackStaffById(staffIds);
			getValueStack().set("entity", entity);
		}
		return "staffEdit";
	}
	
	/**
	 * 信息员新增页面
	 * @return
	 */
	public String staffAddView(){
		return "staffAddView";
	}
	
	/**
	 * 保存信息员
	 * @return
	 */
	@ResponseBody
	public boolean saveStaff(){
		try{
			List<FeedBackStaffEntity> list = new ArrayList<FeedBackStaffEntity>();
			list.add(staffEntity);
			feedBackService.saveStaffList(list);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 更新信息员
	 * @return
	 */
	@ResponseBody
	public boolean staffUpdate(){
		try{
			feedBackService.modifyFeedBackStaff(staffEntity);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**========================================信息员管理 以上======================================*/
	
	/**========================================信息员反馈以下======================================*/
	private IEvaluationService evaluationService; 
	private CurriculumScheduleQuery csquery;
	private PageList<FeedBackEntity> feedBackPageList;
	
	private String kcid;
	private String globalid;
	
	/**
	 * 当日课程表
	 */
	public String todayCkList(){
		CurriculumScheduleQuery query = new CurriculumScheduleQuery();
    	String userId = getUser().getYhm();
    	query.setUserlx(getUser().getYhlx());
    	query.setUserid(userId);
        List<CurriculumScheduleEntity> curriculumSchedule = evaluationService.getTodayCurriculum(query);
        List<TeachingEntity> teachingEntities = new ArrayList<TeachingEntity>();
        ViewQuestionnaireEntity view = null;
        for (CurriculumScheduleEntity e: curriculumSchedule) {
        	TeachingEntity te = new TeachingEntity();
        	te.setGlobalid(e.getGlobalid());
        	te.setRklsgh(e.getRklsgh());
        	te.setKcmc(e.getKcmc());
        	te.setSkdd(e.getSkdd());
        	te.setRkls(e.getRkls());
            te.setKcsj(e.getKssj());
            te.setKcjc(e.getKcjc());
            te.setKcid(e.getKcid());
            view = evaluationService.getXsEvaluation(e.getGlobalid()+userId);
            // 判断是否存在评价记录
            if(view != null){
            	te.setXwjid(view.getXwjid());
            	te.setPjryid(view.getPjryid());
            	te.setPjid(view.getPjid());
            	// 如果已评价
            	if("1".equals(view.getPjzt())){
            		te.setZt("1");
            	}
            	// 如果未评价
            	else{
            		te.setZt("0");
            	}
            }else{
            	// 生成评价 
            	evaluationService.sendOneStudentEvaluation(te, userId);
            	view = evaluationService.getXsEvaluation(e.getGlobalid()+userId);
            	if(view != null){
                	te.setXwjid(view.getXwjid());
                	te.setPjryid(view.getPjryid());
                	te.setPjid(view.getPjid());
            	}
            	te.setZt("0");
            }
            teachingEntities.add(te);
        }
        getValueStack().set("teachingEntities", teachingEntities);
		return "todayCkList";
	}
	
	/**
	 * 信息反馈页面
	 * @return
	 */
	public String addFeedbackIndex(){
		getValueStack().set("kcid",kcid);
		getValueStack().set("globalid",globalid);
		return "addFeekbackIndex";
	}
	
	/**
	 * 保存信息反馈
	 * @return
	 */
	@ResponseBody
	public boolean saveFeedbackInfo(){
		try{
			if(StringUtils.isNotBlank(globalid)){
				CourseQuery query = new CourseQuery();
				query.setKcid(kcid);
				query.setGlobalid(globalid);
				CourseQuery course = feedBackService.getCource(query);
				User user = getUser();
				model.setCjr(user == null?"":user.getYhm());
				if(null != course){
					model.setXn(this.DEFULT_XN);
					model.setXq(this.DEFULT_XQ);
					model.setXxid(globalid+user.getYhm());//信息ID=选课课号+学号
					model.setKcmc(course.getKcmc());
					model.setKcdm(course.getKcdm());
					model.setKkxy(course.getKkxy());
					model.setJsszdw(course.getJsszdw());
					model.setSkjsgh(course.getSkjsgh());
					model.setSkjsxm(course.getSkjsxm());
					model.setXsszxy(user.getBmdm_id());
				}
				feedBackService.saveFeedbackInfo(model);
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 反馈信息列表
	 */
	public String feedBackList(){
		if(null == query){
			query = new FeedBackQuery();
		}
		feedBackPageList = feedBackService.getFeedBackList(query);
		return "feedBackList";
	}
	
	/**
	 * 反馈信息页面详情
	 * @return
	 */
	public String feedBackDetail(){
		FeedBackEntity model = feedBackService.getFeedbackByXxid(xxid);
		List<FeedBackLogEntity> feedBackLogEntitys = feedBackService.getFeedBackLogEntityByXxid(xxid);
		getValueStack().set("xxid",xxid);
		getValueStack().set("model",model);
		getValueStack().set("feedBackLogEntitys",feedBackLogEntitys);
		return "feedBackDetail"; 
	}
	
	/**========================================信息员反馈以上======================================*/
	
	/**========================================移动端信息反馈功能======================================*/
	
	/**
	 * 手机端-信息反馈页面
	 * @return
	 */
	public String addFeedback(){
		getValueStack().set("kcid",kcid);
		getValueStack().set("globalid",globalid);
		return "addFeedback";
	}
	
	/**
	 * 手机端-查看信息反馈详情页面
	 * @return
	 */
	public String veiwFeedback(){
		model = feedBackService.getFeedbackByXxid(globalid);
		getValueStack().set("model",model);
		return "veiwFeedback";
	}
	
	/**
	 * 手机端-反馈信息列表
	 */
	public String searchfeedBackList(){
		String userId = this.getUser().getYhm();
		if(null == query){
			query = new FeedBackQuery();
		}
		// 判断是否是信息员
		boolean isMessenger = feedBackService.isFeedBackStaff(userId);
		if(isMessenger){
			// 只能查自己的反馈信息
			query.setXh(userId);
			feedBackPageList = feedBackService.getFeedBackList(query);
		}
		getValueStack().set("sfxxy", isMessenger);
		if(feedBackPageList != null){
			getValueStack().set("feedBackPageList", getPageList(feedBackPageList));
		}		
		return "searchfeedBackList";
	}
	/**=============================================================================================*/
	
	/**========================================信息反馈处理功能======================================*/
	
	/**
	 * 单位管理员处理反馈信息列表
	 */
	public String unitHandleFeedBackList(){
		if(null == query){
			query = new FeedBackQuery();
		}
		query.setXxlx("0");        //信息类型为普通
		String bmdmId = getUser().getBmdm_id();
		String czrszdw = getUser().getBmmc();
		if(StringUtils.isNotBlank(bmdmId)){
			czrszdw = feedBackService.getXyMC(bmdmId);
		}
		query.setCzrszdw(czrszdw);    //操作人所在单位名称
		feedBackPageList = feedBackService.getUnitHandleFeedBackList(query);
		return "unitHandleFeedBackList";
	}
	
	/**
	 * 单位管理员处理反馈信息页面
	 * @return
	 */
	public String unitHandleFeedbackView(){
		FeedBackEntity model = feedBackService.getFeedbackByXxid(xxid);
		List<FeedBackLogEntity> feedBackLogEntitys = feedBackService.getFeedBackLogEntityByXxid(xxid);
		getValueStack().set("xxid",xxid);
		getValueStack().set("model",model);
		getValueStack().set("feedBackLogEntitys",feedBackLogEntitys);
		return "unitHandleFeedbackView"; 
	}
	
	/**
	 * 单位管理员反馈
	 * @return
	 */
	public String unitHandleAddFeedbackView(){
		FeedBackEntity model = feedBackService.getFeedbackByXxid(xxid);
		List<FeedBackLogEntity> feedBackLogEntitys = feedBackService.getFeedBackLogEntityByXxid(xxid);
		getValueStack().set("xxid",xxid);
		getValueStack().set("model",model);
		getValueStack().set("feedBackLogEntitys",feedBackLogEntitys);
		return "unitHandleAddFeedbackView";
	}
	
	/**
	 * 单位管理员处理
	 * @return
	 */
	@ResponseBody
	public String unitHandleFeedback(){
		model.setClhj("1");
		model.setZt("1");
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setClhj("0");
		//feedBackLogEntity.setSfcs("");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
	 * 单位管理员处理退回操作
	 * @return
	 */
	@ResponseBody
	public String backUnitHandleFeedback(){
		model.setClhj("4");
		model.setZt("4");
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setSfcs("");
		feedBackLogEntity.setClhj("0");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
	 * 单位管理员反馈
	 * @return
	 */
	@ResponseBody
	public String unitHandleAddFeedback(){
		model.setClhj("3");
		model.setZt("2");
		model.setClr(getUser().getYhm());
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setSfcs("");
		feedBackLogEntity.setClhj("2");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
	 * 单位管理员反馈退回操作
	 * @return
	 */
	@ResponseBody
	public String backUnitHandleAddFeedback(){
		model.setClhj("1");
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setSfcs("");
		feedBackLogEntity.setClhj("2");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
     * 单位管理员导出
     * @return
     * @throws Exception
     */
    public String unitExportFeedBack() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "单位管理员导出反馈信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("单位管理员导出反馈信息", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        query.setXxlx("0");        //信息类型为普通
		query.setCzrszdw(getUser().getBmmc());    //操作人所在单位名称
		query.setCzrszdwbm(getUser().getBmdm_id());////操作人所在单位编码
    	pageList = feedBackService.getUnitHandleFeedBackList(query);
        
        //产生表头
    	sheet.addCell(generateTheadLabel(0, 0, "学年"));
        sheet.addCell(generateTheadLabel(1, 0, "学期"));
        sheet.addCell(generateTheadLabel(2, 0, "信息分类"));
        sheet.addCell(generateTheadLabel(3, 0, "信息类型"));
        sheet.addCell(generateTheadLabel(4, 0, "内容类型"));
        sheet.addCell(generateTheadLabel(5, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(6, 0, "开课学院"));
        sheet.addCell(generateTheadLabel(7, 0, "教师名称"));
        sheet.addCell(generateTheadLabel(8, 0, "教师所在单位"));
        sheet.addCell(generateTheadLabel(9, 0, "处理环节"));
        sheet.addCell(generateTheadLabel(10, 0, "状态"));

        //产生内容
        int y = 0;
        for(FeedBackEntity h : pageList){
            y++;
            //学年
            String xn = h.getXn();
            //学期
            String xq = h.getXq();
            if("1".equals(xq)){
            	xq = "第1学期";
        	}else if("2".equals(xq)){
        		xq = "第2学期";
        	}
            //信息分类
            String xxfl = h.getXxfl();
            if(StringUtils.isNotBlank(xxfl)){
            	if("0".equals(xxfl)){
            		xxfl = "学生类";
            	}else if("1".equals(xxfl)){
            		xxfl = "课程类";
            	}else if("2".equals(xxfl)){
            		xxfl = "教师类";
            	}else if("3".equals(xxfl)){
            		xxfl = "教学环境保障类";
            	}
            }
            //信息类型
            String xxlx = h.getXxlx();
            if(StringUtils.isNotBlank(xxlx)){
            	if("0".equals(xxlx)){
            		xxlx = "普通";
            	}else if("1".equals(xxlx)){
            		xxlx = "紧急";
            	}
            }
            //信息内容类型
            String xxnrlx = h.getXxnrlx();
            if(StringUtils.isNotBlank(xxnrlx)){
            	if("0".equals(xxnrlx)){
            		xxnrlx = "表扬";
            	}else if("1".equals(xxnrlx)){
            		xxnrlx = "意见/建议";
            	}else if("2".equals(xxnrlx)){
            		xxnrlx = "紧急事件";
            	}
            }
            //开课学院
            String kkxy = h.getKkxy();
            kkxy = feedBackService.getXyMC(kkxy);
            //处理环节
            String clhj = h.getClhj();
            if(StringUtils.isNotBlank(clhj)){
            	if("0".equals(clhj)){
            		clhj = "单位管理员筛选";
            	}else if("1".equals(clhj)){
            		clhj = "责任人/单位处理";
            	}else if("2".equals(clhj)){
            		clhj = "单位管理员反馈";
            	}else if("3".equals(clhj)){
            		clhj = "信息员评价";
            	}else if("4".equals(clhj)){
            		clhj = "完成";
            	}
            }
            //状态
            String zt = h.getZt();
            if(StringUtils.isNotBlank(zt)){
            	if("0".equals(zt)){
            		zt = "已提交";
            	}else if("1".equals(zt)){
            		zt = "处理中";
            	}else if("2".equals(zt)){
            		zt = "已反馈";
            	}else if("3".equals(zt)){
            		zt = "已评价";
            	}else if("4".equals(zt)){
            		zt = "已退回";
            	}
            }
            sheet.addCell(generateValueLabel(0, y, xn));
            sheet.addCell(generateValueLabel(1, y, xq));
            sheet.addCell(generateValueLabel(2, y, xxfl));
            sheet.addCell(generateValueLabel(3, y, xxlx));
            sheet.addCell(generateValueLabel(4, y, xxnrlx));
            sheet.addCell(generateValueLabel(5, y, h.getKcmc()));
            sheet.addCell(generateValueLabel(6, y, kkxy));
            sheet.addCell(generateValueLabel(7, y, h.getSkjsxm()));
            sheet.addCell(generateValueLabel(8, y, h.getJsszdw()));
            sheet.addCell(generateValueLabel(9, y, clhj));
            sheet.addCell(generateValueLabel(10, y, zt));
        }
        wwb.write();
        wwb.close();
        return null;
    }
	
	
	/**
	 * 责任人/单位处理反馈信息列表
	 */
	public String personHandleFeedBackList(){
		if(null == query){
			query = new FeedBackQuery();
		}
		//反馈信息处理人是当前操作人
		query.setClr(getUser().getYhm());
		feedBackPageList = feedBackService.getPersonHandleFeedBackList(query);
		return "personHandleFeedBackList";
	}
	
	/**
	 * 责任人/单位处理反馈信息页面
	 * @return
	 */
	public String personHandleFeedbackView(){
		FeedBackEntity model = feedBackService.getFeedbackByXxid(xxid);
		List<FeedBackLogEntity> feedBackLogEntitys = feedBackService.getFeedBackLogEntityByXxid(xxid);
		getValueStack().set("xxid",xxid);
		getValueStack().set("model",model);
		getValueStack().set("feedBackLogEntitys",feedBackLogEntitys);
		return "personHandleFeedbackView";
	}
	
	/**
	 * 责任人/单位处理
	 * @return
	 */
	@ResponseBody
	public String personHandleFeedback(){
		model.setClhj("2");
		model.setZt("1");
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setSfcs("");
		feedBackLogEntity.setClhj("1");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
	 * 责任人/单位处理退回操作
	 * @return
	 */
	@ResponseBody
	public String backPersonHandleFeedback(){
		model.setClhj("2");
		model.setZt("1");
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setSfcs("");
		feedBackLogEntity.setClhj("1");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
     * 责任人/单位 反馈信息导出
     * @return
     * @throws Exception
     */
    public String personExportFeedBack() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "责任人&单位导出反馈信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("责任人&单位导出反馈信息", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        //反馈信息处理人是当前操作人
		query.setClr(getUser().getYhm());
    	pageList = feedBackService.getPersonHandleFeedBackList(query);
        
        //产生表头
    	sheet.addCell(generateTheadLabel(0, 0, "学年"));
        sheet.addCell(generateTheadLabel(1, 0, "学期"));
        sheet.addCell(generateTheadLabel(2, 0, "信息分类"));
        sheet.addCell(generateTheadLabel(3, 0, "信息类型"));
        sheet.addCell(generateTheadLabel(4, 0, "内容类型"));
        sheet.addCell(generateTheadLabel(5, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(6, 0, "开课学院"));
        sheet.addCell(generateTheadLabel(7, 0, "教师名称"));
        sheet.addCell(generateTheadLabel(8, 0, "教师所在单位"));
        sheet.addCell(generateTheadLabel(9, 0, "处理环节"));
        sheet.addCell(generateTheadLabel(10, 0, "状态"));
        //产生内容
        int y = 0;
        for(FeedBackEntity h : pageList){
            y++;
            //学年
            String xn = h.getXn();
            //学期
            String xq = h.getXq();
            if("1".equals(xq)){
            	xq = "第1学期";
        	}else if("2".equals(xq)){
        		xq = "第2学期";
        	}
            //信息分类
            String xxfl = h.getXxfl();
            if(StringUtils.isNotBlank(xxfl)){
            	if("0".equals(xxfl)){
            		xxfl = "学生类";
            	}else if("1".equals(xxfl)){
            		xxfl = "课程类";
            	}else if("2".equals(xxfl)){
            		xxfl = "教师类";
            	}else if("3".equals(xxfl)){
            		xxfl = "教学环境保障类";
            	}
            }
            //信息类型
            String xxlx = h.getXxlx();
            if(StringUtils.isNotBlank(xxlx)){
            	if("0".equals(xxlx)){
            		xxlx = "普通";
            	}else if("1".equals(xxlx)){
            		xxlx = "紧急";
            	}
            }
            //信息内容类型
            String xxnrlx = h.getXxnrlx();
            if(StringUtils.isNotBlank(xxnrlx)){
            	if("0".equals(xxnrlx)){
            		xxnrlx = "表扬";
            	}else if("1".equals(xxnrlx)){
            		xxnrlx = "意见/建议";
            	}else if("2".equals(xxnrlx)){
            		xxnrlx = "紧急事件";
            	}
            }
            //开课学院
            String kkxy = h.getKkxy();
            kkxy = feedBackService.getXyMC(kkxy);
            //处理环节
            String clhj = h.getClhj();
            if(StringUtils.isNotBlank(clhj)){
            	if("0".equals(clhj)){
            		clhj = "单位管理员筛选";
            	}else if("1".equals(clhj)){
            		clhj = "责任人/单位处理";
            	}else if("2".equals(clhj)){
            		clhj = "单位管理员反馈";
            	}else if("3".equals(clhj)){
            		clhj = "信息员评价";
            	}else if("4".equals(clhj)){
            		clhj = "完成";
            	}
            }
            //状态
            String zt = h.getZt();
            if(StringUtils.isNotBlank(zt)){
            	if("0".equals(zt)){
            		zt = "已提交";
            	}else if("1".equals(zt)){
            		zt = "处理中";
            	}else if("2".equals(zt)){
            		zt = "已反馈";
            	}else if("3".equals(zt)){
            		zt = "已评价";
            	}else if("4".equals(zt)){
            		zt = "已退回";
            	}
            }
            sheet.addCell(generateValueLabel(0, y, xn));
            sheet.addCell(generateValueLabel(1, y, xq));
            sheet.addCell(generateValueLabel(2, y, xxfl));
            sheet.addCell(generateValueLabel(3, y, xxlx));
            sheet.addCell(generateValueLabel(4, y, xxnrlx));
            sheet.addCell(generateValueLabel(5, y, h.getKcmc()));
            sheet.addCell(generateValueLabel(6, y, kkxy));
            sheet.addCell(generateValueLabel(7, y, h.getSkjsxm()));
            sheet.addCell(generateValueLabel(8, y, h.getJsszdw()));
            sheet.addCell(generateValueLabel(9, y, clhj));
            sheet.addCell(generateValueLabel(10, y, zt));
        }
        wwb.write();
        wwb.close();
        return null;
    }
	
	/**
	 * 校级管理员处理反馈信息列表
	 */
	public String schoolHandleFeedBackList(){
		if(null == query){
			query = new FeedBackQuery();
		}
		query.setXxlx("1");        //信息类型为紧急类型
		feedBackPageList = feedBackService.getFeedBackList(query);
		return "schoolHandleFeedBackList";
	}
	
	/**
	 * 校级管理员处理反馈信息页面
	 * @return
	 */
	public String schoolHandleFeedbackView(){
		FeedBackEntity model = feedBackService.getFeedbackByXxid(xxid);
		List<FeedBackLogEntity> feedBackLogEntitys = feedBackService.getFeedBackLogEntityByXxid(xxid);
		getValueStack().set("xxid",xxid);
		getValueStack().set("model",model);
		getValueStack().set("feedBackLogEntitys",feedBackLogEntitys);
		return "schoolHandleFeedbackView";
	}
	
	/**
	 * 校级管理员处理
	 * @return
	 */
	@ResponseBody
	public String schoolHandleFeedback(){
		model.setClhj("2");
		model.setZt("1");
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setSfcs("");
		feedBackLogEntity.setClhj("1");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
	 * 校级管理员处理退回操作
	 * @return
	 */
	@ResponseBody
	public String backSchoolHandleFeedback(){
		model.setClhj("2");
		model.setZt("1");
		feedBackService.modifyFeedback(model);
		//记录处理日志
		FeedBackLogEntity feedBackLogEntity = new FeedBackLogEntity();
		feedBackLogEntity.setXxid(model.getXxid());
		feedBackLogEntity.setRznr(model.getFkjg());
		feedBackLogEntity.setCzr(getUser().getYhm());
		feedBackLogEntity.setSfcs("");
		feedBackLogEntity.setClhj("1");
		feedBackService.saveFeedBackLog(feedBackLogEntity);
		getValueStack().set(DATA, getMessage());
        return DATA;
	}
	
	/**
     * 校级管理员 反馈信息导出
     * @return
     * @throws Exception
     */
    public String schoolExportFeedBack() throws Exception{
        getResponse().reset();
        getResponse().setCharacterEncoding("utf-8");
        getResponse().setContentType("application/vnd.ms-excel");
        String useragent = getRequest().getHeader("user-agent");
        String disposition = DownloadFilenameUtil.fileDisposition(useragent, "校级管理员导出反馈信息.xls");
        getResponse().setHeader("Content-Disposition", disposition);
        
        WritableWorkbook wwb = Workbook.createWorkbook(getResponse().getOutputStream());
        
        WritableSheet sheet = wwb.createSheet("校级管理员导出反馈信息", 1);
        query.setPerPageSize(Integer.MAX_VALUE);
        query.setXxlx("1");        //信息类型为紧急类型
    	pageList = feedBackService.getPersonHandleFeedBackList(query);
        
        //产生表头
    	sheet.addCell(generateTheadLabel(0, 0, "学年"));
        sheet.addCell(generateTheadLabel(1, 0, "学期"));
        sheet.addCell(generateTheadLabel(2, 0, "信息分类"));
        sheet.addCell(generateTheadLabel(3, 0, "信息类型"));
        sheet.addCell(generateTheadLabel(4, 0, "内容类型"));
        sheet.addCell(generateTheadLabel(5, 0, "课程名称"));
        sheet.addCell(generateTheadLabel(6, 0, "开课学院"));
        sheet.addCell(generateTheadLabel(7, 0, "教师名称"));
        sheet.addCell(generateTheadLabel(8, 0, "教师所在单位"));
        sheet.addCell(generateTheadLabel(9, 0, "处理环节"));
        sheet.addCell(generateTheadLabel(10, 0, "状态"));

        //产生内容
        int y = 0;
        for(FeedBackEntity h : pageList){
            y++;
            //学年
            String xn = h.getXn();
            //学期
            String xq = h.getXq();
            if("1".equals(xq)){
            	xq = "第1学期";
        	}else if("2".equals(xq)){
        		xq = "第2学期";
        	}
            //信息分类
            String xxfl = h.getXxfl();
            if(StringUtils.isNotBlank(xxfl)){
            	if("0".equals(xxfl)){
            		xxfl = "学生类";
            	}else if("1".equals(xxfl)){
            		xxfl = "课程类";
            	}else if("2".equals(xxfl)){
            		xxfl = "教师类";
            	}else if("3".equals(xxfl)){
            		xxfl = "教学环境保障类";
            	}
            }
            //信息类型
            String xxlx = h.getXxlx();
            if(StringUtils.isNotBlank(xxlx)){
            	if("0".equals(xxlx)){
            		xxlx = "普通";
            	}else if("1".equals(xxlx)){
            		xxlx = "紧急";
            	}
            }
            //信息内容类型
            String xxnrlx = h.getXxnrlx();
            if(StringUtils.isNotBlank(xxnrlx)){
            	if("0".equals(xxnrlx)){
            		xxnrlx = "表扬";
            	}else if("1".equals(xxnrlx)){
            		xxnrlx = "意见/建议";
            	}else if("2".equals(xxnrlx)){
            		xxnrlx = "紧急事件";
            	}
            }
            //开课学院
            String kkxy = h.getKkxy();
            kkxy = feedBackService.getXyMC(kkxy);
            //处理环节
            String clhj = h.getClhj();
            if(StringUtils.isNotBlank(clhj)){
            	if("0".equals(clhj)){
            		clhj = "单位管理员筛选";
            	}else if("1".equals(clhj)){
            		clhj = "责任人/单位处理";
            	}else if("2".equals(clhj)){
            		clhj = "单位管理员反馈";
            	}else if("3".equals(clhj)){
            		clhj = "信息员评价";
            	}else if("4".equals(clhj)){
            		clhj = "完成";
            	}
            }
            //状态
            String zt = h.getZt();
            if(StringUtils.isNotBlank(zt)){
            	if("0".equals(zt)){
            		zt = "已提交";
            	}else if("1".equals(zt)){
            		zt = "处理中";
            	}else if("2".equals(zt)){
            		zt = "已反馈";
            	}else if("3".equals(zt)){
            		zt = "已评价";
            	}else if("4".equals(zt)){
            		zt = "已退回";
            	}
            }
            sheet.addCell(generateValueLabel(0, y, xn));
            sheet.addCell(generateValueLabel(1, y, xq));
            sheet.addCell(generateValueLabel(2, y, xxfl));
            sheet.addCell(generateValueLabel(3, y, xxlx));
            sheet.addCell(generateValueLabel(4, y, xxnrlx));
            sheet.addCell(generateValueLabel(5, y, h.getKcmc()));
            sheet.addCell(generateValueLabel(6, y, kkxy));
            sheet.addCell(generateValueLabel(7, y, h.getSkjsxm()));
            sheet.addCell(generateValueLabel(8, y, h.getJsszdw()));
            sheet.addCell(generateValueLabel(9, y, clhj));
            sheet.addCell(generateValueLabel(10, y, zt));
        }
        wwb.write();
        wwb.close();
        return null;
    }

	
	/**=============================================================================================*/

	public FeedBackQuery getQuery() {
		return query;
	}

	public IFeedBackService getFeedBackService() {
		return feedBackService;
	}

	public PageList<FeedBackEntity> getFeedBackPageList() {
		return feedBackPageList;
	}

	public void setFeedBackPageList(PageList<FeedBackEntity> feedBackPageList) {
		this.feedBackPageList = feedBackPageList;
	}

	public String getGlobalid() {
		return globalid;
	}

	public void setGlobalid(String globalid) {
		this.globalid = globalid;
	}

	public String getKcid() {
		return kcid;
	}

	public void setKcid(String kcid) {
		this.kcid = kcid;
	}

	public void setQuery(FeedBackQuery query) {
		this.query = query;
	}

	public FeedBackEntity getModel() {
		return model;
	}

	public void setModel(FeedBackEntity model) {
		this.model = model;
	}

	public void setFeedBackService(IFeedBackService feedBackService) {
		this.feedBackService = feedBackService;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public FeedBackStaffQuery getStaffQuery() {
		return staffQuery;
	}

	public void setStaffQuery(FeedBackStaffQuery staffQuery) {
		this.staffQuery = staffQuery;
	}

	public PageList<FeedBackStaffEntity> getStaffPageList() {
		return staffPageList;
	}

	public void setStaffPageList(PageList<FeedBackStaffEntity> staffPageList) {
		this.staffPageList = staffPageList;
	}

	public IItemService getItemService() {
		return itemService;
	}

	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	public String getStaffIds() {
		return staffIds;
	}

	public void setStaffIds(String staffIds) {
		this.staffIds = staffIds;
	}

	public FeedBackStaffEntity getStaffEntity() {
		return staffEntity;
	}

	public void setStaffEntity(FeedBackStaffEntity staffEntity) {
		this.staffEntity = staffEntity;
	}
	
	public IEvaluationService getEvaluationService() {
		return evaluationService;
	}

	public void setEvaluationService(IEvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

	public CurriculumScheduleQuery getCsquery() {
		return csquery;
	}

	public void setCsquery(CurriculumScheduleQuery csquery) {
		this.csquery = csquery;
	}

	public String getXxid() {
		return xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPerSize() {
		return perSize;
	}

	public void setPerSize(int perSize) {
		this.perSize = perSize;
	}

	public PageList<FeedBackEntity> getPageList() {
		return pageList;
	}

	public void setPageList(PageList<FeedBackEntity> pageList) {
		this.pageList = pageList;
	}

	/**
	 * 组装表头，标注
	 * 
	 * @param column
	 * @param row
	 * @param cellContext
	 * @param cellComment
	 * @return
	 */
	private Label generateTheadLabel(int column, int row, String cellContext,
			String cellComment) {
		WritableCellFeatures f = new WritableCellFeatures();
		f.setComment(cellComment);
		Label label = new Label(column, row, cellContext);
		label.setCellFeatures(f);
		return label;
	}
	
	 /**
     * 分页
     * @param temp
     * @return
     */
    private List<FeedBackEntity> getPageList(List<FeedBackEntity> temp) {
        List<FeedBackEntity> ret = new ArrayList<FeedBackEntity>();
        if (perSize == 0) {
            perSize = 10;
        }
        // 获取总页数
        if (temp.size() % perSize == 0) {
            totalSize = temp.size() / perSize;
        } else {
            totalSize = temp.size() / perSize + 1;
        }
        
        // 上一页
        if ("previous".equals(handle)) {
            nowPage = nowPage - 1;
        // 下一页
        } else if ("next".equals(handle)) {
            nowPage = nowPage + 1;
        } else {
            nowPage = 1;
        }
        int start = (nowPage - 1) * perSize + 1;
        int end = nowPage * perSize;
        
        for (int i = 0; i < temp.size(); i++) {
            if (i >= (start - 1) && i <= (end - 1)) {
                ret.add(temp.get(i));
            }
        }
        return ret;
    }

}
