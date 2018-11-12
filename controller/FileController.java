package com.penglang.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("file")
public class FileController {

    @RequestMapping(value = "/tofile",method = RequestMethod.GET)
    public ModelAndView toFile(){
        ModelAndView modelAndView = new ModelAndView("file");
        return modelAndView;
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ModelAndView upload(MultipartFile file, HttpServletRequest request){

        try{
            //上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
            //如果目录不不存在，自动创建
            File dir = new File(uploadDir);
            if (!dir.exists()){
                dir.mkdir();
            }
            //文件后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //上传文件名
            String filename = System.nanoTime()+suffix;
            //服务器保存的文件对象
            File serverFile = new File(uploadDir+filename);
            file.transferTo(serverFile);
        }catch (Exception e){
            e.printStackTrace();
            ModelAndView modelAndView1 = new ModelAndView("file");
            return modelAndView1;
        }

        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
}
