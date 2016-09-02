package com.zfsoft.feedback.dao.daointerface;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zfsoft.feedback.entity.CourseQuery;
import com.zfsoft.feedback.entity.FeedBackEntity;
import com.zfsoft.feedback.entity.FeedBackLogEntity;
import com.zfsoft.feedback.entity.FeedBackQuery;
import com.zfsoft.feedback.entity.FeedBackStaffEntity;
import com.zfsoft.feedback.entity.FeedBackStaffQuery;


/**
 * 
* @ClassName: IFeedBackDao
* @Description: TODO(信息员DAO类)
* @author rogerfan
* @date 2016-5-19 下午05:44:55
*
 */
public interface IFeedBackDao {
	
	/**===============================信息员管理==============================*/
	/**
	 * 根据条件查询信息员列表
	 * @param query
	 * @return
	 */
	public List<FeedBackStaffEntity> getFeedBackStaffList(FeedBackStaffQuery query);
	
	 /**
     * 根据条件查询信息员列表数量
     * @param query
     * @return
     */
    public int getFeedBackStaffCount(FeedBackStaffQuery query);
	
	/**
	 * 保存信息员
	 * @param feedBackStaffEntity
	 */
	public void insertFeedBackStaff(FeedBackStaffEntity feedBackStaffEntity);
	
	/**
	 * 修改信息员
	 * @param feedBackStaffEntity
	 */
	public void modifyFeedBackStaff(FeedBackStaffEntity feedBackStaffEntity);
	
	/**
	 * 删除信息员
	 * @param feedBackStaffEntity
	 */
	public void deleteFeedBackStaff(FeedBackStaffEntity feedBackStaffEntity);
	
	/**
	 * 批量删除信息员
	 * @param ids
	 */
	public void deleteFeedBackStaffByIds(@Param("ids")String[] ids);
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public FeedBackStaffEntity getFeedBackStaffById(@Param("id")String id);
	
	/**===============================信息员管理以上==============================*/
	
	/**===============================信息反馈==============================*/
	
	/**
	 * 保存反馈信息
	 */
	public void insertFeedBack(FeedBackEntity entity);
	
	/**
	 * 查询课程
	 * @param query
	 * @return
	 */
	public CourseQuery getCourse(CourseQuery query);
	
	/**
     * 根据信息ID获取信息反馈
     */
    public FeedBackEntity getFeedbackByXxid(String xxid);
	
	/**
	 * 根据条件查询信息反馈列表
	 * @param query
	 * @return
	 */
	public List<FeedBackEntity> getFeedBackList(FeedBackQuery query);
	
	 /**
     * 根据条件查询信息反馈列表数量
     * @param query
     * @return
     */
    public int getFeedBackCount(FeedBackQuery query);
    
    /**
	 * 根据条件查询信息反馈列表
	 * @param query
	 * @return
	 */
	public List<FeedBackEntity> getUnitHandleFeedBackList(FeedBackQuery query);
	
	 /**
     * 根据条件查询信息反馈列表数量
     * @param query
     * @return
     */
    public int getUnitHandleFeedBackCount(FeedBackQuery query);
    
    /**
	 * 根据条件查询信息反馈列表
	 * @param query
	 * @return
	 */
	public List<FeedBackEntity> getPersonHandleFeedBackList(FeedBackQuery query);
	
	 /**
     * 根据条件查询信息反馈列表数量
     * @param query
     * @return
     */
    public int getPersonHandleFeedBackCount(FeedBackQuery query);
    
    /**
	 * 根据条件查询信息反馈列表
	 * @param query
	 * @return
	 */
	public List<FeedBackEntity> getSchoolHandleFeedBackList(FeedBackQuery query);
	
	 /**
     * 根据条件查询信息反馈列表数量
     * @param query
     * @return
     */
    public int getSchoolHandleFeedBackCount(FeedBackQuery query);
    
    /**
     * 更新反馈信息
     * @param model
     */
    public void modifyFeedBack(FeedBackEntity model);
    
	
	/**===============================信息反馈以上==============================*/
    
    /**===============================信息反馈处理日志==========================*/

	/**
	 * 保存反馈信息处理日志
	 */
	public void insertFeedBackLog(FeedBackLogEntity entity);
	
	/**
	 * 获取日志
	 */
	public List<FeedBackLogEntity> getFeedBackLogEntityByXxid(String xxid);
	
	/**
	 * 学院编码获取学院名称
	 * @param xy
	 * @return
	 */
	public String getXyMC(String xy);
    
    /**===============================信息反馈处理日志以上======================*/
}
