package com.jyh.videoadmin.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyh.videoadmin.common.enums.BGMOperatorTypeEnum;
import com.jyh.videoadmin.common.util.PagedResult;
import com.jyh.videoadmin.common.util.ZKCurator;
import com.jyh.videoadmin.mapper.BgmMapper;
import com.jyh.videoadmin.mapper.VideosMapper;
import com.jyh.videoadmin.pojo.Bgm;
import com.jyh.videoadmin.pojo.Videos;
import com.jyh.videoadmin.pojo.vo.Reports;
import com.jyh.videoadmin.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private BgmMapper bgmMapper;

	@Autowired
	private ZKCurator zkCurator;

	@Autowired
	private VideosMapper videosMapper;

	@Override
	public void addBgm(Bgm bgm) {
		bgmMapper.saveReturnPK(bgm);

		zkCurator.sendBgmOperator(Integer.toString(bgm.getId()), BGMOperatorTypeEnum.ADD.type);
	}

	@Override
	public PagedResult queryBgmList(Integer page, Integer pageSize) {
		PageHelper.startPage(page,pageSize);
		List<Bgm>  list= bgmMapper.selectAll();
		PageInfo pageInfo = new PageInfo(list);

		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setTotal(pageInfo.getPages());
		pagedResult.setRecords(pageInfo.getTotal());
		pagedResult.setRows(list);

		return pagedResult;
	}

	@Override
	public void deleteBgm(String id) {
		bgmMapper.deleteByPrimaryKey(id);
		zkCurator.sendBgmOperator(id, BGMOperatorTypeEnum.DELETE.type);
	}

	@Override
	public PagedResult queryReportList(Integer page, Integer pageSize) {
		PageHelper.startPage(page,pageSize);

		List<Reports> reportsList = videosMapper.selectAllVideoReport();

		PageInfo<Reports> pageList = new PageInfo<Reports>(reportsList);

		PagedResult grid = new PagedResult();
		grid.setTotal(pageList.getPages());
		grid.setRows(reportsList);
		grid.setPage(page);
		grid.setRecords(pageList.getTotal());

		return grid;
	}

	@Override
	public void updateVideoStatus(String videoId, Integer status) {
		Videos video = new Videos();
		video.setId(Integer.valueOf(videoId));
		video.setStatus(status);
//		videosMapper.updateByPrimaryKeySelective(video);
		videosMapper.update(video);
	}
}
