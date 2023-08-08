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


import com.dao.DevinfoDao;
import com.entity.DevinfoEntity;
import com.service.DevinfoService;
import com.entity.vo.DevinfoVO;
import com.entity.view.DevinfoView;

@Service("devinfoService")
public class DevinfoServiceImpl extends ServiceImpl<DevinfoDao, DevinfoEntity> implements DevinfoService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DevinfoEntity> page = this.selectPage(
                new Query<DevinfoEntity>(params).getPage(),
                new EntityWrapper<DevinfoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DevinfoEntity> wrapper) {
		  Page<DevinfoView> page =new Query<DevinfoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<DevinfoVO> selectListVO(Wrapper<DevinfoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DevinfoVO selectVO(Wrapper<DevinfoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DevinfoView> selectListView(Wrapper<DevinfoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DevinfoView selectView(Wrapper<DevinfoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
