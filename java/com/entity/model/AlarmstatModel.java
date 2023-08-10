package com.entity.model;

import com.entity.AlarmstatEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
 

/**
 * 故障统计
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public class AlarmstatModel  implements Serializable {
	private static final long serialVersionUID = 1L;

		
	/**
	 * 故障等级
	 */
		

	private String alarmlevel;
		
	/**
	 * 数量
	 */
	
	private String cnt;
		
	/**
	 * 比例
	 */
	
	private String ratio;
				
	
	/**
	 * 设置：故障等级
	 */
	 
	public void setAlarmlevel(String alarmlevel) {
		this.alarmlevel = alarmlevel;
	}
	
	/**
	 * 获取：故障等级
	 */
	public String getAlarmlevel() {
		return alarmlevel;
	}
				
	
	/**
	 * 设置：数量
	 */
	 
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	
	/**
	 * 获取：数量
	 */
	public String getCnt() {
		return cnt;
	}
				
	
	/**
	 * 设置：比例
	 */
	 
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	
	/**
	 * 获取：比例
	 */
	public String getRatio() {
		return ratio;
	}
			
}
