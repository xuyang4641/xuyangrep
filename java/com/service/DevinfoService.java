package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DevinfoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DevinfoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DevinfoView;


/**
 * 员工
 *
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface DevinfoService extends IService<DevinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DevinfoVO> selectListVO(Wrapper<DevinfoEntity> wrapper);

	DevinfoVO selectVO(@Param("ew") Wrapper<DevinfoEntity> wrapper);
   	
   	List<DevinfoView> selectListView(Wrapper<DevinfoEntity> wrapper);

	DevinfoView selectView(@Param("ew") Wrapper<DevinfoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DevinfoEntity> wrapper);
   	
}

