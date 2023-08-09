<template>
<div class="container content">
<div class="background">
        <img :src="img" width="100%" height="100%" alt="" />
</div>
<div class="text main-text">欢迎使用 {{this.$project.projectName}}</div>

</div>
</template>
<script>
import router from '@/router/router-static'
export default {
  data() {
      return {
        img: require('../assets/img/bg3.jpg'),
      };
  },
  mounted(){
    this.init();
  },
  methods:{
    init(){
        if(this.$storage.get('Token')){
        this.$http({
            url: `${this.$storage.get('sessionTable')}/session`,
            method: "get"
        }).then(({ data }) => {
            if (data && data.code != 0) {
            router.push({ name: 'login' })
            }
        });
        }else{
            router.push({ name: 'login' })
        }
    }
  }
};
</script>

<style lang="scss" scoped>
.content {
  background-repeat: no-repeat;
  background-position: center center;
  background-size: cover;
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  min-height: 500px;
  text-align: center;
  .background {
        z-index:0;
        position: absolute;
    }
  .main-text{
    font-size: 38px;
    font-weight: bold;
    margin-top: 25%;
    z-index: 1;
  }
  .text {
    font-size: 38px;
    font-weight: bold;
    color: #FFFFFF;
  }
}
</style>