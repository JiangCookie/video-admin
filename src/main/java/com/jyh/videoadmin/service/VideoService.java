package com.jyh.videoadmin.service;


import com.jyh.videoadmin.common.util.PagedResult;
import com.jyh.videoadmin.pojo.Bgm;

public interface VideoService {

	public void addBgm(Bgm bgm);

	public PagedResult queryBgmList(Integer page, Integer pageSize);

	public void deleteBgm(String id);

	public PagedResult queryReportList(Integer page, Integer pageSize);

	public void updateVideoStatus(String videoId, Integer status);
}
