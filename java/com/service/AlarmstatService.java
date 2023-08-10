package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.AlarmstatEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.AlarmstatVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.AlarmstatView;


/**
 * 故障统计
 *
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
public interface AlarmstatService extends IService<AlarmstatEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<AlarmstatVO> selectListVO(Wrapper<AlarmstatEntity> wrapper);
   	
   	AlarmstatVO selectVO(@Param("ew") Wrapper<AlarmstatEntity> wrapper);
   	
   	List<AlarmstatView> selectListView(Wrapper<AlarmstatEntity> wrapper);
   	
   	AlarmstatView selectView(@Param("ew") Wrapper<AlarmstatEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<AlarmstatEntity> wrapper);
   	
}

