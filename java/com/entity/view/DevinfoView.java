package com.entity.view;

import com.entity.DevinfoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 员工
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@TableName("devinfo")
public class DevinfoView  extends DevinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DevinfoView(){
	}
 
 	public DevinfoView(DevinfoEntity devinfoEntity){
 	try {
			BeanUtils.copyProperties(this, devinfoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
