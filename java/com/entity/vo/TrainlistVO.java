package com.entity.vo;

import com.entity.TrainlistEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 车辆列表
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public class TrainlistVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 车组号
	 */
	
	private String groupid;
		
	/**
	 * 列车类型
	 */
	
	private String traintype;
		
	/**
	 * 日期
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date detectdate;
		
	/**
	 * 检测设备id
	 */
	
	private String devid;
		
	/**
	 * 备注
	 */
	
	private String beizhu;
				
	
	/**
	 * 设置：车组号
	 */
	 
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	/**
	 * 获取：车组号
	 */
	public String getGroupid() {
		return groupid;
	}
				
	
	/**
	 * 设置：列车类型
	 */
	 
	public void setTraintype(String traintype) {
		this.traintype = traintype;
	}
	
	/**
	 * 获取：列车类型
	 */
	public String getTraintype() {
		return traintype;
	}
				
	
	/**
	 * 设置：日期
	 */
	 
	public void setDetectdate(Date detectdate) {
		this.detectdate = detectdate;
	}
	
	/**
	 * 获取：日期
	 */
	public Date getDetectdate() {
		return detectdate;
	}
				
	
	/**
	 * 设置：检测设备id
	 */
	 
	public void setDevid(String devid) {
		this.devid = devid;
	}
	
	/**
	 * 获取：检测设备id
	 */
	public String getDevid() {
		return devid;
	}
				
	
	/**
	 * 设置：备注
	 */
	 
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	/**
	 * 获取：备注
	 */
	public String getBeizhu() {
		return beizhu;
	}
			
}
