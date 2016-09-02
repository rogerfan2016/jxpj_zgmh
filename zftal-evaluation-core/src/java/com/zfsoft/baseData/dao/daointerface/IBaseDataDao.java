package com.zfsoft.baseData.dao.daointerface;

import java.util.List;
import java.util.Map;

import com.zfsoft.baseData.entity.ClassTypeEntity;
import com.zfsoft.baseData.entity.ProcedureEntity;
import com.zfsoft.baseData.entity.ViewPropertyEntity;
import com.zfsoft.hrm.baseinfo.dyna.entities.DynaBeanQuery;

/**
 * 
 * @author Administrator
 *
 */
public interface IBaseDataDao {
	
	/**
	 * 
	* @Title: getClassTypeByKcdm 
	* @Description: TODO(根据课程代码查询课程分类数据) 
	* @param @param kcdm
	* @param @return    设定文件 
	* @return ClassTypeEntity    返回类型 
	* @throws
	 */
	public ClassTypeEntity getClassTypeByKcdm(String kcdm);
	
	/**
	 * 
	* @Title: modifyClassType 
	* @Description: TODO(更新课程分类) 
	* @param @param entity    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void modifyClassType(ClassTypeEntity entity);
	
	/**
	 * 
	* @Title: getSynchronizedBaseDataCount 
	* @Description: TODO(查询课程分类数据条数) 
	* @param @param query
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
    public int getClassTypeCount(ClassTypeEntity query);

    /**
     * 
    * @Title: getClassTypeList 
    * @Description: TODO(查询课程分类数据记录) 
    * @param @param query
    * @param @return    设定文件 
    * @return List<ClassTypeEntity>    返回类型 
    * @throws
     */
    public List<ClassTypeEntity> getClassTypeList(ClassTypeEntity query);

    /**
     * 获取同步数据条数
     * @param query
     * @return
     */
    public int getSynchronizedBaseDataCount(DynaBeanQuery query);

    /**
     * 获取同步数据
     * @param query
     * @return
     */
    public List<Map<String, Object>> getSynchronizedBaseDataList(DynaBeanQuery query);

    /**
     * 执行数据存储
     * @param param
     * @return
     */
    public String executeProcedure(Map<String, String> param);

    /**
     * 获取存储过程
     * @param param
     * @return
     */
    public List<ProcedureEntity> getProcedures(Map<String, String> param);
    
    /**
     * 获取存储过程
     * @param param
     * @return
     */
    public ProcedureEntity getProceduresById(Map<String, String> param);
    
    /**
     * 插入存储过程
     * @param param
     * @return
     */
    public void insertProcedure(ProcedureEntity entity);
    
    /**
     * 修改存储过程
     * @param param
     * @return
     */
    public void modifyProcedure(ProcedureEntity entity);

    /**
     * 取得DB中存储过程
     * @return
     */
    public List<String> getDBProcedures();

    /**
     * 增加显示属性
     * @param vpModel
     */
    public void addViewPro(ViewPropertyEntity vpModel);

    /**
     * 移除显示属性
     * @param vpModel
     */
    public void removeViewPro(ViewPropertyEntity vpModel);

    /**
     * 修改显示属性
     * @param vpModel
     */
    public void modifyViewPro(ViewPropertyEntity vpModel);

    /**
     * 获取显示属性
     * @param classId
     */
    public List<ViewPropertyEntity> findViewPro(String classId);

    /**
     * 获取显示属性
     * @param vpModel
     * @return
     */
    public List<ViewPropertyEntity> findViewProByPIds(Map<String, Object> param);
}
