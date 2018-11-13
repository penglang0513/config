package com.penglang.config.controller;

import com.penglang.config.bean.UserEntity;
import com.penglang.config.service.UserService;
import com.penglang.config.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView index(){

        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = "/toregister",method = RequestMethod.GET)
    public ModelAndView toregister(){
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView register(UserEntity userEntity,HttpServletRequest request) throws Exception{
        UserEntity userEntity1=userService.findByMobile(userEntity.getMobilephone());
        if (null!=userEntity1){
            throw new Exception("手机号码已存在！");
        }else {
            redisTemplate.delete("myuser::"+userEntity.getMobilephone());
        }
        UserEntity userEntity2=userService.findByEmail(userEntity.getEmail());
        if (null!=userEntity2){
            throw new Exception("邮箱已存在！");
        }
        String maxid = userService.findMaxId();
        if (maxid==null){
            maxid="10384";
        }else {
            maxid=(Integer.parseInt(maxid.substring(0,5))+1)+"";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String dates = sdf.format(date);
        Map map = new HashMap();
        map.put("userid",maxid+dates);
        map.put("username",userEntity.getUsername());
        map.put("pwd",MD5.getInstance().getMD5ofStr(maxid+dates+userEntity.getPwd()));
        map.put("mobile",userEntity.getMobilephone());
        map.put("email",userEntity.getEmail());
        map.put("createtime",date);
        map.put("state","N");
        if (null!=userEntity.getAge()){
            map.put("age",userEntity.getAge());
        }
        if(null!=userEntity.getAddress()){
            map.put("address",userEntity.getAddress());
        }

        int insert = userService.insertUser(map);

        logger.info("**********************insert**********"+insert);
        String result = null;
        if (insert>0){
            result = "index";
        }else {
            result = "register";
        }
        ModelAndView mv = new ModelAndView(result);
        return mv;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView toindex(@RequestParam String mobile,@RequestParam String pwd, HttpServletRequest request) throws Exception{

        UserEntity userEntity = userService.findByMobile(mobile);
        if (null==userEntity){
            redisTemplate.delete("myuser::"+mobile);
            throw new Exception("该用户不存在！");
        }else {
            pwd = MD5.getInstance().getMD5ofStr(userEntity.getUserid()+pwd);
            Map map = new HashMap();
            map.put("mobile",mobile);
            map.put("pwd",pwd);

            UserEntity userEntity1 = userService.findByMoblieAndPwd(map);
            logger.info("*********"+userEntity1);
            if (userEntity1==null){
                redisTemplate.delete("myuser::"+map.get("mobile")+map.get("pwd"));
                throw new Exception("用户密码错误！");
            }else {
                request.getSession().setAttribute("user",userEntity1);
            }
        }
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ModelAndView deleteUser(String mobile,HttpServletRequest request){
        UserEntity userEntity = userService.findByMobile(mobile);
        int a=userService.deleteUser(mobile);
        String result = null;
        if (a>0){
            redisTemplate.delete("myuser::"+userEntity.getMobilephone()+userEntity.getPwd());
            result="login";
            request.getSession().removeAttribute("user");
        }else {
            result="index";
        }
        ModelAndView modelAndView = new ModelAndView(result);
        return modelAndView;
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request){

        HttpSession session = request.getSession();

        logger.info("*****session***"+session.getAttribute("user"));

        ModelAndView modelAndView = new ModelAndView("test");
        return modelAndView;
    }
}
