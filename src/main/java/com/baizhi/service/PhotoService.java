package com.baizhi.service;

import com.baizhi.entity.Photo;

import java.util.List;

public interface PhotoService {
    //图片展示
    public List<Photo> showAllPhoto(String u_id);
    //图片上传
    public List<Photo> insertPhoto(String u_id,Photo photo);
    //图片删除
    public void deletePhoto(String p_id);
    //图片下载加1
    public void updatePhoto(String p_id);
    //获取图片
    public Photo getPhoto(String p_id);
}
