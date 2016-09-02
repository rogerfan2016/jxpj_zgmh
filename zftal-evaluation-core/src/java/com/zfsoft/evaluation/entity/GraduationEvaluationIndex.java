package com.zfsoft.evaluation.entity;

/**
 * 
* @ClassName: ClassTypeEnum
* @Description: TODO(毕业评价指标项枚举类)
* @author rogerfan
* @date 2016-5-24 上午10:46:43
*
 */
public enum GraduationEvaluationIndex {
	ZBX_TEACHER("喜欢的教师","teacher"),
    ZBX_HARVESTLESSON("收获最大课程","harvestLesson"),
    ZBX_HARDLESSON("最难的课程","hardLesson"),
    ZBX_MAJOR("专业认可度","major"),
    ZBX_STUDY("学习满意度","study"),
    ZBX_OPINION("对母校说的话","opinion"),
    ZBX_OPINION_1("对学院说的话","opinion1"),
    ZBX_OPINION_2("对专业说的话","opinion2"),
    ZBX_OPINION_3("对教师说的话","opinion3");

	/**
	 * 定义枚举类型自己的属性
	 */
	private final String text;
	private final String key;

	private GraduationEvaluationIndex(String text, String key) {
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
