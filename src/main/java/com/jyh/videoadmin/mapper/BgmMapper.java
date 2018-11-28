package com.jyh.videoadmin.mapper;

import com.jyh.videoadmin.pojo.Bgm;
import tk.mybatis.mapper.common.Mapper;

public interface BgmMapper extends Mapper<Bgm> {
    /**
     * 保存用户，返回主键
     */
    int saveReturnPK(Bgm bgm);
}