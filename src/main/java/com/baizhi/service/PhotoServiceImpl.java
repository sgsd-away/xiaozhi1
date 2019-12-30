package com.baizhi.service;

import com.baizhi.dao.PhotoDAO;
import com.baizhi.entity.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName PhotoServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/1  17:10
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoDAO photoDAO;
    public PhotoDAO getPhotoDAO() { return photoDAO; }
    public void setPhotoDAO(PhotoDAO photoDAO) { this.photoDAO = photoDAO; }

    //图片展示
    public List<Photo> showAllPhoto(String u_id){

        List<Photo> list = photoDAO.showAllPhoto(u_id);
        return list;
    }
    //图片上传
    public List<Photo> insertPhoto(String u_id,Photo photo){
        photo.setP_id(UUID.randomUUID().toString());
        photoDAO.insertPhoto(photo);
        List<Photo> list = photoDAO.showAllPhoto(u_id);
        return list;
    }
    //图片删除
    public void deletePhoto(String p_id){
        photoDAO.deletePhoto(p_id);
    }
    //图片下载加1
    public void updatePhoto(String p_id){
        Photo photo = photoDAO.getPhoto(p_id);
        photo.setP_count(photo.getP_count()+1);
        photoDAO.updatePhoto(photo);
    }
    //获取图片
    public Photo getPhoto(String p_id){
        Photo photo = photoDAO.getPhoto(p_id);
        return photo;
    }
}
