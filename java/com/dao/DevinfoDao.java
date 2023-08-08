package com.dao;

import com.entity.DevinfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DevinfoVO;
import com.entity.view.DevinfoView;


/**
 * 员工
 * 
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface DevinfoDao extends BaseMapper<DevinfoEntity> {
	
	List<DevinfoVO> selectListVO(@Param("ew") Wrapper<DevinfoEntity> wrapper);

	DevinfoVO selectVO(@Param("ew") Wrapper<DevinfoEntity> wrapper);
	
	List<DevinfoView> selectListView(@Param("ew") Wrapper<DevinfoEntity> wrapper);

	List<DevinfoView> selectListView(Pagination page,@Param("ew") Wrapper<DevinfoEntity> wrapper);

	DevinfoView selectView(@Param("ew") Wrapper<DevinfoEntity> wrapper);
	
}
