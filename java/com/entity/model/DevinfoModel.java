package com.entity.model;

import com.entity.DevinfoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
 

/**
 * 设备信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public class DevinfoModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 序列号
	 */
	
	private String devid;
		
	/**
	 * 软件版本
	 */
	
	private String swver;
		
	/**
	 * 硬件版本
	 */
	
	private String hwver;
		
	/**
	 * 登记人
	 */
	
	private String regname;
		
	/**
	 * 手机
	 */
	
	private String phonenumber;
		
	/**
	 * 邮箱
	 */
	
	private String youxiang;
				
	
	/**
	 * 设置：序列号
	 */
	 
	public void setDevid(String devid) {
		this.devid = devid;
	}
	
	/**
	 * 获取：序列号
	 */
	public String getDevid() {
		return devid;
	}
				
	
	/**
	 * 设置：软件版本
	 */
	 
	public void setSwver(String swver) {
		this.swver = swver;
	}
	
	/**
	 * 获取：软件版本
	 */
	public String getSwver() {
		return swver;
	}
				
	
	/**
	 * 设置：硬件版本
	 */
	 
	public void setHwver(String hwver) {
		this.hwver = hwver;
	}
	
	/**
	 * 获取：硬件版本
	 */
	public String getHwver() {
		return hwver;
	}
				
	
	/**
	 * 设置：登记人
	 */
	 
	public void setRegname(String regname) {
		this.regname = regname;
	}
	
	/**
	 * 获取：登记人
	 */
	public String getRegname() {
		return regname;
	}
				
	
	/**
	 * 设置：手机
	 */
	 
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	/**
	 * 获取：手机
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
				
	
	/**
	 * 设置：邮箱
	 */
	 
	public void setYouxiang(String youxiang) {
		this.youxiang = youxiang;
	}
	
	/**
	 * 获取：邮箱
	 */
	public String getYouxiang() {
		return youxiang;
	}
			
}
