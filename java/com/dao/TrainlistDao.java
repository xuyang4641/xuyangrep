package com.dao;

import com.entity.TrainlistEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.TrainlistVO;
import com.entity.view.TrainlistView;


/**
 * 车辆列表
 * 
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface TrainlistDao extends BaseMapper<TrainlistEntity> {
	
	List<TrainlistVO> selectListVO(@Param("ew") Wrapper<TrainlistEntity> wrapper);

	TrainlistVO selectVO(@Param("ew") Wrapper<TrainlistEntity> wrapper);
	
	List<TrainlistView> selectListView(@Param("ew") Wrapper<TrainlistEntity> wrapper);

	List<TrainlistView> selectListView(Pagination page,@Param("ew") Wrapper<TrainlistEntity> wrapper);

	TrainlistView selectView(@Param("ew") Wrapper<TrainlistEntity> wrapper);
	
}
