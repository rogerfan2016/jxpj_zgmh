package com.zfsoft.feedback.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.feedback.entity.CourseQuery;
import com.zfsoft.feedback.entity.FeedBackEntity;
import com.zfsoft.feedback.entity.FeedBackLogEntity;
import com.zfsoft.feedback.entity.FeedBackQuery;
import com.zfsoft.feedback.entity.FeedBackStaffEntity;
import com.zfsoft.feedback.entity.FeedBackStaffQuery;


/**
 * 
* @ClassName: IFeedBackService
* @Description: TODO(信息反馈service接口类)
* @author rogerfan
* @date 2016-6-13 下午03:02:18
*
 */
public interface IFeedBackService {

	/**============================信息员管理================================*/
	
	/**
	 * 信息员存储数据（单条时若已经存在信息则修改，其他情况统一为添加）
	 * @param beans
	 * @param xh
	 */
	public void doImportData(List<FeedBackStaffEntity> beans, String xh, boolean compel);
	
	/**
     * 根据条件查询信息员列表 
     * @param param
     * @return
     */
    public PageList<FeedBackStaffEntity> getFeedBackStaffList(FeedBackStaffQuery query);
    
    /**
     * 保存信息员
     * @param entities
     */
    public void saveStaffList(List<FeedBackStaffEntity> entities);
    
    /**
     * 批量删除信息员
     * @param ids
     */
    public void deleteFeedBackStaffByIds(@Param("ids")String[] ids);
    
    /**
     * 根据ID查询信息员
     * @param id
     * @return
     */
    public FeedBackStaffEntity getFeedBackStaffById(@Param("id")String id);
    
    /**
     * 更新信息员
     * @param query
     */
    public void modifyFeedBackStaff(FeedBackStaffEntity entity);
    
    /**
     * 
    * @Title: isFeedBackStaff 
    * @Description: TODO(判断某学生是不是信息员) 
    * @param @param xh
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public boolean isFeedBackStaff(String xh);
    
    /**============================信息员管理以上================================*/
    
    /**============================信息反馈================================*/
    
    /**
     * 保存信息反馈
     */
    public void saveFeedbackInfo(FeedBackEntity entity);
    
    /**
     * 获取课程信息
     */
    public CourseQuery getCource(CourseQuery query);
    
    /**
     * 根据信息ID获取信息反馈
     */
    public FeedBackEntity getFeedbackByXxid(String xxid);
    
    /**
     * 获取反馈信息列表
     * @param query
     * @return
     */
    public PageList<FeedBackEntity> getFeedBackList(FeedBackQuery query);
    
    /**
     * 单位管理员处理反馈信息列表
     * @param entity
     * @return
     */
    public PageList<FeedBackEntity> getUnitHandleFeedBackList(FeedBackQuery query);
    
    /**
     * 责任人/单位处理反馈信息列表
     * @param entity
     * @return
     */
    public PageList<FeedBackEntity> getPersonHandleFeedBackList(FeedBackQuery query);
    
    /**
     * 校级处理反馈信息列表
     * @param entity
     * @return
     */
    public PageList<FeedBackEntity> getSchoolHandleFeedBackList(FeedBackQuery query);
    
    /**
     * 更新反馈信息
     * @param model
     */
    public void modifyFeedback(FeedBackEntity model);
    
    /**
     * 
    * @Title: isFeedBack 
    * @Description: TODO(某学生某门课程是否反馈) 
    * @param @param xh
    * @param @param globalid
    * @param @return    设定文件 
    * @return boolean    返回类型 
    * @throws
     */
    public boolean isFeedBack(String xh, String globalid);
    
    /**============================信息反馈以上================================*/
    
    /**============================信息反馈处理日志================================*/
    
    /**
     * 保存信息反馈处理日志
     */
    public void saveFeedBackLog(FeedBackLogEntity entity);
    
    /**
     * 根据信息id获取日志
     * @param xxid
     * @return
     */
    public List<FeedBackLogEntity> getFeedBackLogEntityByXxid(String xxid);
    
    /**
     * 学院编码获取学院名称
     * @param xy
     * @return
     */
    public String getXyMC(String xy);
    
    /**============================信息反馈处理日志以上============================*/
	
}
