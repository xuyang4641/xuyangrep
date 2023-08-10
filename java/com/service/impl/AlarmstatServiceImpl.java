package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.AlarmstatDao;
import com.entity.AlarmstatEntity;
import com.service.AlarmstatService;
import com.entity.vo.AlarmstatVO;
import com.entity.view.AlarmstatView;

@Service("alarmstatService")
public class AlarmstatServiceImpl extends ServiceImpl<AlarmstatDao, AlarmstatEntity> implements AlarmstatService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AlarmstatEntity> page = this.selectPage(
                new Query<AlarmstatEntity>(params).getPage(),
                new EntityWrapper<AlarmstatEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<AlarmstatEntity> wrapper) {
		  Page<AlarmstatView> page =new Query<AlarmstatView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<AlarmstatVO> selectListVO(Wrapper<AlarmstatEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public AlarmstatVO selectVO(Wrapper<AlarmstatEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<AlarmstatView> selectListView(Wrapper<AlarmstatEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public AlarmstatView selectView(Wrapper<AlarmstatEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
