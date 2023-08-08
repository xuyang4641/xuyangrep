package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.TrainlistEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.TrainlistVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.TrainlistView;


/**
 * 体温上报
 *
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface TrainlistService extends IService<TrainlistEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<TrainlistVO> selectListVO(Wrapper<TrainlistEntity> wrapper);

	TrainlistVO selectVO(@Param("ew") Wrapper<TrainlistEntity> wrapper);
   	
   	List<TrainlistView> selectListView(Wrapper<TrainlistEntity> wrapper);

	TrainlistView selectView(@Param("ew") Wrapper<TrainlistEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<TrainlistEntity> wrapper);
   	
}

