package com.zfsoft.evaluation.entity;


/**
 * 
 * @author Administrator
 *
 */
public enum EvaluationType {
	SSPJ("学生实时评价"), // 学生实时评价
	JSHP("教师听课互评"), // 教师听课互评
    XSJKPJ("学生结课评价"), // 学生结课评价
    XJZJPJ("校级专家评价"), // 校级专家评价
    XJDDPJ("校级督导评价"), // 校级督导评价
    XWDDPJ("校外督导评价"), // 校外督导评价
    YJZJPJ("院级专家评价"), // 院级专家评价
    YJDDPJ("院级督导评价"); // 院级督导评价

    private String text;
    
    private EvaluationType(String text){
        this.text=text;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getKeyStr() {
        return toString();
    }
    
}
