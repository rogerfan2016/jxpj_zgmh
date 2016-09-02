package com.zfsoft.wjdc_xc.entites;

/**
 * 
 * 类描述：类别枚举类
 * 
 * @version: 1.0
 * @author: yingjie.fan
 * @version: 2015-12-23 下午01:43:43
 */
public enum InspectionTypeEnum {
	TYPE_XSJKPJ("学生结课评价", "XSJKPJ"), // 学生结课评价
	TYPE_XJZJPJ("校级专家评价", "XJZJPJ"), // 校级专家评价
	TYPE_XJDDPJ("校级督导评价", "XJDDPJ"), // 校级督导评价
	TYPE_XWDDPJ("校外督导评价", "XWDDPJ"), // 校外督导评价
	TYPE_YJZJPJ("院级专家评价", "YJZJPJ"), // 院级专家评价
	TYPE_YJDDPJ("院级督导评价", "YJDDPJ"); // 院级督导评价

	/**
	 * 定义枚举类型自己的属性
	 */
	private final String text;
	private final String key;

	private InspectionTypeEnum(String text, String key) {
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
