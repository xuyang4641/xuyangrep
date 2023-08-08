package com.entity.vo;

import com.entity.AlarmlistEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 故障列表
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public class AlarmlistVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 故障类型
	 */
	
	private String alarmtype;
		
	/**
	 * 故障等级
	 */
	
	private String alarmlevel;
		
	/**
	 * 部位
	 */
	
	private String position;
		
	/**
	 * 区域
	 */
	
	private String region;
		
	/**
	 * 车辆号
	 */
	
	private String trainid;
		
	/**
	 * 车厢号
	 */
	
	private String carriageno;

	/**
	 * 图片路径
	 */

	private String src;

	/**
	 * 设置：故障类型
	 */
	 
	public void setalarmtype(String alarmtype) {
		this.alarmtype = alarmtype;
	}
	
	/**
	 * 获取：故障类型
	 */
	public String getalarmtype() {
		return alarmtype;
	}
				
	
	/**
	 * 设置：故障等级
	 */
	 
	public void setalarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}
	
	/**
	 * 获取：故障等级
	 */
	public String getalarmlevel() {
		return alarmlevel;
	}
				
	
	/**
	 * 设置：部位
	 */
	 
	public void setposition(String position) {
		this.position = position;
	}
	
	/**
	 * 获取：部位
	 */
	public String getposition() {
		return position;
	}
				
	
	/**
	 * 设置：区域
	 */
	 
	public void setregion(String region) {
		this.region = region;
	}
	
	/**
	 * 获取：区域
	 */
	public String getregion() {
		return region;
	}

	/**
	 * 设置：图片路径
	 */

	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * 获取：图片路径
	 */
	public String getSrc() {
		return src;
	}
				
	
	/**
	 * 设置：车辆号
	 */
	 
	public void settrainid(String trainid) {
		this.trainid = trainid;
	}
	
	/**
	 * 获取：车辆号
	 */
	public String gettrainid() {
		return trainid;
	}
				
	
	/**
	 * 设置：车厢号
	 */
	 
	public void setcarriageno(String carriageno) {
		this.carriageno = carriageno;
	}
	
	/**
	 * 获取：车厢号
	 */
	public String getcarriageno() {
		return carriageno;
	}
			
}
