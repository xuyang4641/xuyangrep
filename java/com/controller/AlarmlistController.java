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

import com.entity.AlarmlistEntity;
import com.entity.view.AlarmlistView;

import com.service.AlarmlistService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.CommonUtil;


/**
 * 故障列表
 * 后端接口
 * @author 
 * @email 
 * @date 2021-01-08 19:02:11
 */
@RestController
@RequestMapping("/alarmlist")
public class AlarmlistController {
    @Autowired
    private AlarmlistService alarmlistService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,AlarmlistEntity alarmlist, HttpServletRequest request){

		/*String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yuangong")) {
            alarmlist.setGonghao((String)request.getSession().getAttribute("username"));
		}*/
        EntityWrapper<AlarmlistEntity> ew = new EntityWrapper<AlarmlistEntity>();
		PageUtils page = alarmlistService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, alarmlist), params), params));
        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,AlarmlistEntity alarmlist, HttpServletRequest request){
        EntityWrapper<AlarmlistEntity> ew = new EntityWrapper<AlarmlistEntity>();
		PageUtils page = alarmlistService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, alarmlist), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( AlarmlistEntity alarmlist){
       	EntityWrapper<AlarmlistEntity> ew = new EntityWrapper<AlarmlistEntity>();
      	ew.allEq(MPUtil.allEQMapPre( alarmlist, "alarmlist"));
        return R.ok().put("data", alarmlistService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(AlarmlistEntity alarmlist){
        EntityWrapper< AlarmlistEntity> ew = new EntityWrapper< AlarmlistEntity>();
 		ew.allEq(MPUtil.allEQMapPre( alarmlist, "alarmlist"));
        AlarmlistView alarmlistView =  alarmlistService.selectView(ew);
		return R.ok("查询成功").put("data", alarmlistView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        AlarmlistEntity alarmlist = alarmlistService.selectById(id);
        return R.ok().put("data", alarmlist);
    }

    /**
     * 前端详情
     */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") String id){
        AlarmlistEntity alarmlist = alarmlistService.selectById(id);
        return R.ok().put("data", alarmlist);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AlarmlistEntity alarmlist, HttpServletRequest request){
        alarmlist.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(alarmlist);

        alarmlistService.insert(alarmlist);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody AlarmlistEntity alarmlist, HttpServletRequest request){
        alarmlist.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(alarmlist);

        alarmlistService.insert(alarmlist);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AlarmlistEntity alarmlist, HttpServletRequest request){
        //ValidatorUtils.validateEntity(alarmlist);
        alarmlistService.updateById(alarmlist);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        alarmlistService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<AlarmlistEntity> wrapper = new EntityWrapper<AlarmlistEntity>();
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

		int count = alarmlistService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	


}
