package com.dao;

import com.entity.AlarmstatEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.AlarmstatVO;
import com.entity.view.AlarmstatView;


/**
 * 故障统计
 * 
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface AlarmstatDao extends BaseMapper<AlarmstatEntity> {
	
	List<AlarmstatVO> selectListVO(@Param("ew") Wrapper<AlarmstatEntity> wrapper);
	
	AlarmstatVO selectVO(@Param("ew") Wrapper<AlarmstatEntity> wrapper);
	
	List<AlarmstatView> selectListView(@Param("ew") Wrapper<AlarmstatEntity> wrapper);

	List<AlarmstatView> selectListView(Pagination page,@Param("ew") Wrapper<AlarmstatEntity> wrapper);
	
	AlarmstatView selectView(@Param("ew") Wrapper<AlarmstatEntity> wrapper);
	
}
