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


import com.dao.EmpregisterDao;
import com.entity.EmpregisterEntity;
import com.service.EmpregisterService;
import com.entity.vo.EmpregisterVO;
import com.entity.view.EmpregisterView;

@Service("empregisterService")
public class EmpregisterServiceImpl extends ServiceImpl<EmpregisterDao, EmpregisterEntity> implements EmpregisterService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<EmpregisterEntity> page = this.selectPage(
                new Query<EmpregisterEntity>(params).getPage(),
                new EntityWrapper<EmpregisterEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<EmpregisterEntity> wrapper) {
		  Page<EmpregisterView> page =new Query<EmpregisterView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<EmpregisterVO> selectListVO(Wrapper<EmpregisterEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public EmpregisterVO selectVO(Wrapper<EmpregisterEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<EmpregisterView> selectListView(Wrapper<EmpregisterEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public EmpregisterView selectView(Wrapper<EmpregisterEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
