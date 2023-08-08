package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.EmpregisterEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.EmpregisterVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.EmpregisterView;


/**
 * 人员登记
 *
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface EmpregisterService extends IService<EmpregisterEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<EmpregisterVO> selectListVO(Wrapper<EmpregisterEntity> wrapper);

	EmpregisterVO selectVO(@Param("ew") Wrapper<EmpregisterEntity> wrapper);
   	
   	List<EmpregisterView> selectListView(Wrapper<EmpregisterEntity> wrapper);

	EmpregisterView selectView(@Param("ew") Wrapper<EmpregisterEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<EmpregisterEntity> wrapper);
   	
}

