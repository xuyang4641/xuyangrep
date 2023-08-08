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
 * 车辆列表
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@TableName("trainlist")
public class TrainlistEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TrainlistEntity() {
		
	}
	
	public TrainlistEntity(T t) {
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
	 * 列车号
	 */
					
	private String trainid;
	
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
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
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
	 * 设置：列车号
	 */
	public void setTrainid(String trainid) {
		this.trainid = trainid;
	}
	/**
	 * 获取：列车号
	 */
	public String getTrainid() {
		return trainid;
	}
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
