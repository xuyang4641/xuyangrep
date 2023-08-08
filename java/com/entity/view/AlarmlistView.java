package com.entity.view;

import com.entity.AlarmlistEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 故障列表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@TableName("alarmlist")
public class AlarmlistView  extends AlarmlistEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public AlarmlistView(){
	}
 
 	public AlarmlistView(AlarmlistEntity alarmlistEntity){
 	try {
			BeanUtils.copyProperties(this, alarmlistEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}
}
