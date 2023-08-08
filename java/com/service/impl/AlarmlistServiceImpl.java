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


import com.dao.AlarmlistDao;
import com.entity.AlarmlistEntity;
import com.service.AlarmlistService;
import com.entity.vo.AlarmlistVO;
import com.entity.view.AlarmlistView;

@Service("alarmlistService")
public class AlarmlistServiceImpl extends ServiceImpl<AlarmlistDao, AlarmlistEntity> implements AlarmlistService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AlarmlistEntity> page = this.selectPage(
                new Query<AlarmlistEntity>(params).getPage(),
                new EntityWrapper<AlarmlistEntity>()
        );
        return new PageUtils(page);
    }

    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<AlarmlistEntity> wrapper) {
		  Page<AlarmlistView> page =new Query<AlarmlistView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    @Override
	public List<AlarmlistVO> selectListVO(Wrapper<AlarmlistEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}

	@Override
	public AlarmlistVO selectVO(Wrapper<AlarmlistEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}

	@Override
	public List<AlarmlistView> selectListView(Wrapper<AlarmlistEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public AlarmlistView selectView(Wrapper<AlarmlistEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
