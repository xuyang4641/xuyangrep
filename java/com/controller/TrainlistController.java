package com.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.TrainlistEntity;
import com.entity.view.TrainlistView;

import com.service.TrainlistService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 车辆列表
 * 后端接口
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@RestController
@RequestMapping("/trainlist")
public class TrainlistController {
    @Autowired
    private TrainlistService trainlistService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TrainlistEntity trainlist, HttpServletRequest request){

		/*String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yuangong")) {
            trainlist.setTrainid((String)request.getSession().getAttribute("username"));
		}*/
        EntityWrapper<TrainlistEntity> ew = new EntityWrapper<TrainlistEntity>();
		PageUtils page = trainlistService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, trainlist), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TrainlistEntity trainlist, HttpServletRequest request){
        EntityWrapper<TrainlistEntity> ew = new EntityWrapper<TrainlistEntity>();
		PageUtils page = trainlistService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, trainlist), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TrainlistEntity trainlist){
       	EntityWrapper<TrainlistEntity> ew = new EntityWrapper<TrainlistEntity>();
      	ew.allEq(MPUtil.allEQMapPre( trainlist, "trainlist"));
        return R.ok().put("data", trainlistService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TrainlistEntity trainlist){
        EntityWrapper< TrainlistEntity> ew = new EntityWrapper< TrainlistEntity>();
 		ew.allEq(MPUtil.allEQMapPre( trainlist, "trainlist"));
        TrainlistView trainlistView =  trainlistService.selectView(ew);
		return R.ok("查询成功").put("data", trainlistView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        TrainlistEntity trainlist = trainlistService.selectById(id);
        return R.ok().put("data", trainlist);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        TrainlistEntity trainlist = trainlistService.selectById(id);
        return R.ok().put("data", trainlist);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TrainlistEntity trainlist, HttpServletRequest request){
        trainlist.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(trainlist);

        trainlistService.insert(trainlist);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TrainlistEntity trainlist, HttpServletRequest request){
        trainlist.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());

        trainlistService.insert(trainlist);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TrainlistEntity trainlist, HttpServletRequest request){

        trainlistService.updateById(trainlist);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        trainlistService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<TrainlistEntity> wrapper = new EntityWrapper<TrainlistEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		/*String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yuangong")) {
			wrapper.eq("gonghao", (String)request.getSession().getAttribute("username"));
		}*/

		int count = trainlistService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	


}
