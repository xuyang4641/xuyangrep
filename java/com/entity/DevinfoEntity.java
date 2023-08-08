package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 设备信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@TableName("devinfo")
public class DevinfoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DevinfoEntity() {
		
	}
	
	public DevinfoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 设备ID
	 */
					
	private String devid;
	
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
	 * 部门
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
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：设备ID
	 */
	public void setDevid(String devid) {
		this.devid = devid;
	}
	/**
	 * 获取：设备ID
	 */
	public String getDevid() {
		return devid;
	}
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
