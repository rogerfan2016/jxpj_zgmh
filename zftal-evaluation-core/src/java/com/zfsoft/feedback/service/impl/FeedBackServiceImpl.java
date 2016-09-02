package com.zfsoft.feedback.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zfsoft.dao.page.PageList;
import com.zfsoft.dao.page.Paginator;
import com.zfsoft.feedback.dao.daointerface.IFeedBackDao;
import com.zfsoft.feedback.entity.CourseQuery;
import com.zfsoft.feedback.entity.FeedBackEntity;
import com.zfsoft.feedback.entity.FeedBackLogEntity;
import com.zfsoft.feedback.entity.FeedBackQuery;
import com.zfsoft.feedback.entity.FeedBackStaffEntity;
import com.zfsoft.feedback.entity.FeedBackStaffQuery;
import com.zfsoft.feedback.service.IFeedBackService;
import com.zfsoft.util.base.StringUtil;

/**
 * 
* @ClassName: FeedBackServiceImpl
* @Description: TODO(信息反馈service实现类)
* @author rogerfan
* @date 2016-6-13 下午03:02:12
*
 */
public class FeedBackServiceImpl implements IFeedBackService {
	private IFeedBackDao feedBackDao;

	//====================set注入===============================
	public void setFeedBackDao(IFeedBackDao feedBackDao) {
		this.feedBackDao = feedBackDao;
	}
	
	/**==============================信息员管理==============================*/
	@Override
	public void doImportData(List<FeedBackStaffEntity> beans, String xh, boolean compel) {
		
	}

	@Override
	public PageList<FeedBackStaffEntity> getFeedBackStaffList(FeedBackStaffQuery query) {
    	PageList<FeedBackStaffEntity> pageList = new PageList<FeedBackStaffEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(feedBackDao.getFeedBackStaffCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(feedBackDao.getFeedBackStaffList(query));
            }
        }
        return pageList;
	}

	@Override
	public void saveStaffList(List<FeedBackStaffEntity> entities) {
		for (FeedBackStaffEntity entity : entities) {
			feedBackDao.insertFeedBackStaff(entity);
		}
	}

	@Override
	public void deleteFeedBackStaffByIds(@Param("ids")String[] ids) {
		feedBackDao.deleteFeedBackStaffByIds(ids);
	}

	@Override
	public FeedBackStaffEntity getFeedBackStaffById(@Param("id")String id) {
		return feedBackDao.getFeedBackStaffById(id);
	}

	@Override
	public void modifyFeedBackStaff(FeedBackStaffEntity entity) {
		feedBackDao.modifyFeedBackStaff(entity);
	}
	
	@Override
	public boolean isFeedBackStaff(String xh) {
		int count = 0;
		FeedBackStaffQuery query = new FeedBackStaffQuery();
		if(StringUtil.isNotEmpty(xh)){
			query.setXh(xh);
			count = feedBackDao.getFeedBackStaffCount(query);
			if(count > 0){
				return true;
			}
		}
		return false;
	}

	/**==============================信息员管理以上==============================*/

	/**==============================信息反馈==============================*/
	
	@Override
	public void saveFeedbackInfo(FeedBackEntity entity) {
		feedBackDao.insertFeedBack(entity);
	}

	@Override
	public CourseQuery getCource(CourseQuery query) {
		return feedBackDao.getCourse(query);
	}

	@Override
	public FeedBackEntity getFeedbackByXxid(String xxid) {
		return feedBackDao.getFeedbackByXxid(xxid);
	}

	@Override
	public PageList<FeedBackEntity> getFeedBackList(FeedBackQuery query) {
		PageList<FeedBackEntity> pageList = new PageList<FeedBackEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(feedBackDao.getFeedBackCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(feedBackDao.getFeedBackList(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<FeedBackEntity> getUnitHandleFeedBackList(
			FeedBackQuery query) {
		PageList<FeedBackEntity> pageList = new PageList<FeedBackEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(feedBackDao.getUnitHandleFeedBackCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(feedBackDao.getUnitHandleFeedBackList(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<FeedBackEntity> getPersonHandleFeedBackList(
			FeedBackQuery query) {
		PageList<FeedBackEntity> pageList = new PageList<FeedBackEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(feedBackDao.getPersonHandleFeedBackCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(feedBackDao.getPersonHandleFeedBackList(query));
            }
        }
        return pageList;
	}

	@Override
	public PageList<FeedBackEntity> getSchoolHandleFeedBackList(
			FeedBackQuery query) {
		PageList<FeedBackEntity> pageList = new PageList<FeedBackEntity>();
        Paginator paginator = new Paginator();
        if(query != null){
            paginator.setItemsPerPage(query.getPerPageSize());
            paginator.setPage((Integer)query.getToPage());
            
            paginator.setItems(feedBackDao.getSchoolHandleFeedBackCount(query));
            pageList.setPaginator(paginator);
            if(paginator.getBeginIndex() <= paginator.getItems()){
                query.setStartRow(paginator.getBeginIndex());
                query.setEndRow(paginator.getEndIndex());
                pageList.addAll(feedBackDao.getSchoolHandleFeedBackList(query));
            }
        }
        return pageList;
	}
	

	@Override
	public void modifyFeedback(FeedBackEntity model) {
		feedBackDao.modifyFeedBack(model);
	}

	@Override
	public boolean isFeedBack(String xh, String globalid) {
		FeedBackQuery query = new FeedBackQuery();
		query.setXxid(globalid+xh);
		int count = feedBackDao.getFeedBackCount(query);
		if(count > 0){
			return true;
		}
		return false;
	}

	/**==============================信息反馈以上==============================*/
	
	/**==============================信息反馈处理日志==============================*/
	
	@Override
	public void saveFeedBackLog(FeedBackLogEntity entity) {
		feedBackDao.insertFeedBackLog(entity);
	}

	@Override
	public List<FeedBackLogEntity> getFeedBackLogEntityByXxid(String xxid) {
		return feedBackDao.getFeedBackLogEntityByXxid(xxid);
	}
	@Override
	public String getXyMC(String xy) {
		return feedBackDao.getXyMC(xy);
	}
	
	/**==============================信息反馈处理日志以上==============================*/
}
