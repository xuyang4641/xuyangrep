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
 * 故障列表
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@TableName("alarmlist")
public class AlarmlistEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AlarmlistEntity() {
		
	}
	
	public AlarmlistEntity(T t) {
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
	 * 故障id
	 */
					
	private String alarmid;
	
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
	 * 检测设备id
	 */

	private String devid;
	
	
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
	 * 设置：故障ID
	 */
	public void setalarmid(String alarmid) {
		this.alarmid = alarmid;
	}
	/**
	 * 获取：故障ID
	 */
	public String getalarmid() {
		return alarmid;
	}
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
	 * 设置：检测设备
	 */
	public void setDevid(String devid) {
		this.devid = devid;
	}
	/**
	 * 获取：检测设备
	 */
	public String getDevid() {
		return devid;
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
