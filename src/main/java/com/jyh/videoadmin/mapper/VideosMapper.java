package com.jyh.videoadmin.mapper;

import com.jyh.videoadmin.pojo.Videos;
import com.jyh.videoadmin.pojo.vo.Reports;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VideosMapper extends Mapper<Videos> {
    public List<Reports> selectAllVideoReport();
    public void update(Videos videos);
}