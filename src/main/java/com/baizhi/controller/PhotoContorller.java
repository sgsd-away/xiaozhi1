package com.baizhi.controller;

import com.baizhi.entity.Photo;
import com.baizhi.entity.User;
import com.baizhi.service.PhotoService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.io.IOUtils;

/**
 * @ClassName PhotoContorller
 * @Discription
 * @Acthor
 * @Date 2019/12/1  17:14
 */

@Controller
@RequestMapping("/photo")
public class PhotoContorller {
    @Autowired
    private PhotoService photoService;
    public PhotoService getPhotoService() { return photoService; }
    public void setPhotoService(PhotoService photoService) { this.photoService = photoService; }

    //图片展示
    @RequestMapping("/show")
    public String showAllPhoto(HttpServletRequest request,HttpSession session){
        System.out.println("展示内容");
        User user =(User)session.getAttribute("user");
        List<Photo> list = photoService.showAllPhoto(user.getU_id());
        int i=list.size()%4==0?list.size()/4:list.size()/4+1;
            List<Integer> hangs = new ArrayList<>();
            for(int n=1;n<=i;n++){ hangs.add(n); }
        request.setAttribute("hangs",hangs);
        System.out.println(hangs+"个");
        request.setAttribute("list",list);
        System.out.println(list);
        return "/myPhoto.jsp";
    }
    //图片上传
    @RequestMapping("/insertPhoto")
    @ResponseBody
    public List<Photo> insertPhoto(MultipartFile upload,
                                   HttpServletRequest request,HttpSession session) throws IOException {
        System.out.println("upload:"+upload);
        Photo photo = new Photo();
        if(upload!=null){
            ServletContext context = request.getServletContext();
            String realPath = context.getRealPath("upload");
            System.out.println("realPath"+realPath);
            String originalFilename = upload.getOriginalFilename();
            if(!originalFilename.equals("")){
                System.out.println("原始文件名" + originalFilename);
                upload.transferTo(new File( realPath+ "/" + originalFilename));
                photo.setP_name(originalFilename);
            }
        }
        User user =(User)session.getAttribute("user");
        photo.setP_count(0);
        photo.setU_id(user.getU_id());
        List<Photo> list = photoService.insertPhoto(user.getU_id(), photo);
        return list;
    }
    //图片删除
    @RequestMapping("/deletePhoto")
    public String deletePhoto(String p_id){
        photoService.deletePhoto(p_id);
        return "forward:/photo/show";
    }
    //图片下载
    @RequestMapping("/downPhoto")
    public void downPhoto(HttpSession session, HttpServletResponse response,
                                 String filename) throws IOException {
        String realPath = session.getServletContext().getRealPath("/upload");
        System.out.println(realPath);
        // 获取待下载文件
        FileInputStream in = new FileInputStream(realPath +"/"+ filename);
        // 设置响应头
        response.setHeader("content-type","image/png");
        // attachment 代表以附件的方式保存到本地   inline在浏览器窗口打开
        response.setHeader("content-disposition","attachment;filename="+filename);
        response.setCharacterEncoding("utf-8");
        // 获取输出流
        OutputStream out = response.getOutputStream();
        // 下载到客户端
        IOUtils.copy(in, out);
    }
    //更改次数
    @RequestMapping("/gengGaiCiShu")
    @ResponseBody
    public Integer gengGaiCiShu(@RequestBody String p_id){
        System.out.println("更改 p_id:"+p_id);
        photoService.updatePhoto(p_id);
        Photo photo = photoService.getPhoto(p_id);
        return photo.getP_count();
    }
}
