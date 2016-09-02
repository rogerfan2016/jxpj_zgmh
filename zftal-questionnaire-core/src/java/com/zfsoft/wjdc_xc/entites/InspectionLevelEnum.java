package com.zfsoft.wjdc_xc.entites;

/**
 * 
 * 类描述：级别枚举类
 * 
 * @version: 1.0
 * @author: yingjie.fan
 * @version: 2015-12-23 下午01:43:43
 */
public enum InspectionLevelEnum {
	LEVEL_DEPT("学院", "dept"), // 学院
	LEVEL_SCHOOL("学校", "school"); // 学校

	/**
	 * 定义枚举类型自己的属性
	 */
	private final String text;
	private final String key;

	private InspectionLevelEnum(String text, String key) {
		this.text = text;
		this.key = key;
	}

	/**
	 * 展示文本
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 代码编号
	 * 
	 * @return
	 */
	public String getKey() {
		return key;
	}

}
