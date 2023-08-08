package com.dao;

import com.entity.EmpregisterEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.EmpregisterVO;
import com.entity.view.EmpregisterView;


/**
 * 人员登记
 * 
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface EmpregisterDao extends BaseMapper<EmpregisterEntity> {
	
	List<EmpregisterVO> selectListVO(@Param("ew") Wrapper<EmpregisterEntity> wrapper);

	EmpregisterVO selectVO(@Param("ew") Wrapper<EmpregisterEntity> wrapper);
	
	List<EmpregisterView> selectListView(@Param("ew") Wrapper<EmpregisterEntity> wrapper);

	List<EmpregisterView> selectListView(Pagination page,@Param("ew") Wrapper<EmpregisterEntity> wrapper);

	EmpregisterView selectView(@Param("ew") Wrapper<EmpregisterEntity> wrapper);
	
}
