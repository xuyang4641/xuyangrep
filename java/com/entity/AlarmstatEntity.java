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
 * 故障统计
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@TableName("alarmstat")
public class AlarmstatEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AlarmstatEntity() {
		
	}
	
	public AlarmstatEntity(T t) {
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
	 * 故障类型
	 */
					
	private String alarmtype;

	
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


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：故障类型
	 */
	public void setAlarmtype(String alarmtype) {
		this.alarmtype = alarmtype;
	}
	/**
	 * 获取：故障类型
	 */
	public String getAlarmtype() {
		return alarmtype;
	}

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
