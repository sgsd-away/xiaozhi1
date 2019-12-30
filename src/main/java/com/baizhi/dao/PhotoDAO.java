package com.baizhi.dao;

import com.baizhi.entity.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoDAO {
    //图片展示
    public List<Photo> showAllPhoto(String u_id);
    //图片上传
    public void insertPhoto(Photo photo);
    //图片删除
    public void deletePhoto(String p_id);
    //获取图片
    public Photo getPhoto(String p_id);
    //图片下载加1
    public void updatePhoto(Photo photo);
}
