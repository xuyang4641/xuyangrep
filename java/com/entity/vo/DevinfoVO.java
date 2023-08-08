package com.entity.vo;

import com.entity.DevinfoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 员工
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public class DevinfoVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 序列号
	 */
	
	private String devsnum;
		
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
	 
	public void setDevsnum(String devsnum) {
		this.devsnum = devsnum;
	}
	
	/**
	 * 获取：序列号
	 */
	public String getDevsnum() {
		return devsnum;
	}
				
	
	/**
	 * 设置：软件版本
	 */
	 
	public void setSwver(String swver) {
		this.swver = swver;
	}
	
	/**
	 * 获取：软件本吧
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
	 * 设置：部门
	 */
	 
	public void setRegname(String regname) {
		this.regname = regname;
	}
	
	/**
	 * 获取：部门
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
