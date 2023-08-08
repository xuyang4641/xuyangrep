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


import com.dao.TrainlistDao;
import com.entity.TrainlistEntity;
import com.service.TrainlistService;
import com.entity.vo.TrainlistVO;
import com.entity.view.TrainlistView;

@Service("trainlistService")
public class TrainlistServiceImpl extends ServiceImpl<TrainlistDao, TrainlistEntity> implements TrainlistService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TrainlistEntity> page = this.selectPage(
                new Query<TrainlistEntity>(params).getPage(),
                new EntityWrapper<TrainlistEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TrainlistEntity> wrapper) {
		  Page<TrainlistView> page =new Query<TrainlistView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<TrainlistVO> selectListVO(Wrapper<TrainlistEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public TrainlistVO selectVO(Wrapper<TrainlistEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<TrainlistView> selectListView(Wrapper<TrainlistEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public TrainlistView selectView(Wrapper<TrainlistEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
