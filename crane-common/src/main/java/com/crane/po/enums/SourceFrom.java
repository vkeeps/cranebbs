package com.crane.po.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
* @author  Crane:
* @version 5.0
* @time 2017年4月3日 下午11:02:09
* 
*/
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum SourceFrom {
	PC("P","PC"),A("A","Android"),I("I","Iphone");
	private String type;
	private String desc;
	SourceFrom(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static SourceFrom getSourceFromByTypeValue(String type){
		if(null==type){
			return null;
		}
		for (SourceFrom s : SourceFrom.values()) {
            if (s.getType().equals(type)) {
                return s;
            }
        }
        return null;
	}
}
