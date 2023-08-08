package com.entity.vo;

import com.entity.EmpregisterEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 人员登记
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public class EmpregisterVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 员工姓名
	 */
	
	private String yuangongxingming;
		
	/**
	 * 部门
	 */
	
	private String bumen;
		
	/**
	 * 密码
	 */
	
	private String mima;
		
	/**
	 * 请假开始时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date qingjiakaishishijian;
		
	/**
	 * 请假结束时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date qingjiajieshushijian;
		
	/**
	 * 登记人
	 */
	
	private String regname;
		
	/**
	 * 邮箱
	 */
	
	private String youxiang;
		
	/**
	 * 手机
	 */
	
	private String phonenumber;
				
	
	/**
	 * 设置：员工姓名
	 */
	 
	public void setYuangongxingming(String yuangongxingming) {
		this.yuangongxingming = yuangongxingming;
	}
	
	/**
	 * 获取：员工姓名
	 */
	public String getYuangongxingming() {
		return yuangongxingming;
	}
				
	
	/**
	 * 设置：部门
	 */
	 
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	
	/**
	 * 获取：部门
	 */
	public String getBumen() {
		return bumen;
	}
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setMima(String mima) {
		this.mima = mima;
	}
	
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
				
	
	/**
	 * 设置：请假开始时间
	 */
	 
	public void setQingjiakaishishijian(Date qingjiakaishishijian) {
		this.qingjiakaishishijian = qingjiakaishishijian;
	}
	
	/**
	 * 获取：请假开始时间
	 */
	public Date getQingjiakaishishijian() {
		return qingjiakaishishijian;
	}
				
	
	/**
	 * 设置：请假结束时间
	 */
	 
	public void setQingjiajieshushijian(Date qingjiajieshushijian) {
		this.qingjiajieshushijian = qingjiajieshushijian;
	}
	
	/**
	 * 获取：请假结束时间
	 */
	public Date getQingjiajieshushijian() {
		return qingjiajieshushijian;
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
			
}
