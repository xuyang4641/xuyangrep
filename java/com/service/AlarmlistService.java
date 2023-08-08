package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.AlarmlistEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.AlarmlistVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.AlarmlistView;


/**
 * 故障列表
 *
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface AlarmlistService extends IService<AlarmlistEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<AlarmlistVO> selectListVO(Wrapper<AlarmlistEntity> wrapper);
   	
   	AlarmlistVO selectVO(@Param("ew") Wrapper<AlarmlistEntity> wrapper);
   	
   	List<AlarmlistView> selectListView(Wrapper<AlarmlistEntity> wrapper);
   	
   	AlarmlistView selectView(@Param("ew") Wrapper<AlarmlistEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<AlarmlistEntity> wrapper);
   	
}

