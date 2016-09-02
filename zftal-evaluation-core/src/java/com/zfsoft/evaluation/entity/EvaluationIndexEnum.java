package com.zfsoft.evaluation.entity;


/**
 * 
* @ClassName: EvaluationIndexEnum
* @Description: TODO(实时评价指标)
* @author rogerfan
* @date 2016-8-26 上午10:21:55
*
 */
public enum EvaluationIndexEnum {
	PJZB_JXXG("教学效果","jxxg"),
    PJZB_JXNR("教学内容","jxnr"),
    PJZB_YJJY("意见建议","yjjy");

	/**
	 * 定义枚举类型自己的属性
	 */
	private final String text;
	private final String key;

	private EvaluationIndexEnum(String text, String key) {
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
