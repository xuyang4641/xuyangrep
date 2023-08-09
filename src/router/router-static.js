import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import devinfo from '@/views/modules/devinfo/list'
    import yuangonggongzi from '@/views/modules/yuangonggongzi/list'
    import shenqingjingfei from '@/views/modules/shenqingjingfei/list'
    import trainlist from '@/views/modules/trainlist/list'
    import gongsihuodong from '@/views/modules/gongsihuodong/list'
    import alarmstat from '@/views/modules/alarmstat/list'
    import alarmlist from '@/views/modules/alarmlist/list'
    import users from '@/views/modules/users/list'
    import shebeishenqing from '@/views/modules/shebeishenqing/list'
    import renshi from '@/views/modules/renshi/list'
    import empregister from '@/views/modules/empregister/list'
    import huodongbaoming from '@/views/modules/huodongbaoming/list'
    import chat from '@/views/modules/chat/list'
    import messages from '@/views/modules/messages/list'
    import config from '@/views/modules/config/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
          ,{
	path: '/devinfo',
        name: '设备',
        component: devinfo
      }
          ,{
	path: '/yuangonggongzi',
        name: '员工工资',
        component: yuangonggongzi
      }
          ,{
	path: '/shenqingjingfei',
        name: '申请经费',
        component: shenqingjingfei
      }
          ,{
	path: '/trainlist',
        name: '车辆列表',
        component: trainlist
      }
          ,{
	path: '/gongsihuodong',
        name: '公司活动',
        component: gongsihuodong
      }
          ,{
	path: '/alarmstat',
        name: '故障信息',
        component: alarmstat
      }
          ,{
	path: '/alarmlist',
        name: '故障记录',
        component: alarmlist
      }
          ,{
	path: '/users',
        name: '管理员',
        component: users
      }
          ,{
	path: '/shebeishenqing',
        name: '设备申请',
        component: shebeishenqing
      }
          ,{
	path: '/renshi',
        name: '设备维护',
        component: renshi
      }
          ,{
	path: '/empregister',
        name: '人员登记',
        component: empregister
      }
          ,{
	path: '/huodongbaoming',
        name: '活动报名',
        component: huodongbaoming
      }
          ,{
	path: '/chat',
        name: '客服管理',
        component: chat
      }
          ,{
	path: '/messages',
        name: '留言板管理',
        component: messages
      }
          ,{
	path: '/config',
        name: '轮播图管理',
        component: config
      }
        ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页1',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
