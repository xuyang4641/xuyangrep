package com.entity.vo;

import com.entity.GongsigonggaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
 

/**
 * 公司公告
 * 手机端接口返回实体辅助类 
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public class GongsigonggaoVO  implements Serializable {
	private static final long serialVersionUID = 1L;


		
	/**
	 * 发布日期
	 */
		

	private String faburiqi;
		
	/**
	 * 人事账号
	 */
	
	private String renshizhanghao;
		
	/**
	 * 姓名
	 */
	
	private String xingming;
				
	
	/**
	 * 设置：发布日期
	 */
	 
	public void setFaburiqi(String faburiqi) {
		this.faburiqi = faburiqi;
	}
	
	/**
	 * 获取：发布日期
	 */
	public String getFaburiqi() {
		return faburiqi;
	}
				
	
	/**
	 * 设置：人事账号
	 */
	 
	public void setRenshizhanghao(String renshizhanghao) {
		this.renshizhanghao = renshizhanghao;
	}
	
	/**
	 * 获取：人事账号
	 */
	public String getRenshizhanghao() {
		return renshizhanghao;
	}
				
	
	/**
	 * 设置：姓名
	 */
	 
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
			
}
