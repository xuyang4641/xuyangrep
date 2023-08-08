package com.dao;

import com.entity.AlarmlistEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.AlarmlistVO;
import com.entity.view.AlarmlistView;


/**
 * 故障列表
 * 
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface AlarmlistDao extends BaseMapper<AlarmlistEntity> {
	
	List<AlarmlistVO> selectListVO(@Param("ew") Wrapper<AlarmlistEntity> wrapper);
	
	AlarmlistVO selectVO(@Param("ew") Wrapper<AlarmlistEntity> wrapper);
	
	List<AlarmlistView> selectListView(@Param("ew") Wrapper<AlarmlistEntity> wrapper);

	List<AlarmlistView> selectListView(Pagination page,@Param("ew") Wrapper<AlarmlistEntity> wrapper);
	
	AlarmlistView selectView(@Param("ew") Wrapper<AlarmlistEntity> wrapper);
	
}
