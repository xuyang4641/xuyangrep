package com.entity.view;

import com.entity.EmpregisterEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 人员登记
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@TableName("empregister")
public class EmpregisterView  extends EmpregisterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public EmpregisterView(){
	}
 
 	public EmpregisterView(EmpregisterEntity empregisterEntity){
 	try {
			BeanUtils.copyProperties(this, empregisterEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
