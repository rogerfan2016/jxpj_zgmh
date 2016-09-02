package com.zfsoft.evaluation.entity;


/**
 * 
* @ClassName: TeacherOpenLessonType
* @Description: TODO(听课类型枚举类)
* @author rogerfan
* @date 2016-4-14 下午06:05:19
*
 */
public enum TeacherOpenLessonType {
	TKLX_YJ("院级","dept"),
    TKLX_XJ("校级","school");

	/**
	 * 定义枚举类型自己的属性
	 */
	private final String text;
	private final String key;

	private TeacherOpenLessonType(String text, String key) {
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
