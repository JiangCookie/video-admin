package com.jyh.videoadmin.controller;

import com.jyh.videoadmin.common.enums.VideoStatusEnum;
import com.jyh.videoadmin.common.util.JSONResult;
import com.jyh.videoadmin.common.util.PagedResult;
import com.jyh.videoadmin.common.util.ZKCurator;
import com.jyh.videoadmin.pojo.Bgm;
import com.jyh.videoadmin.service.VideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author JYH
 * 2018/11/14 16:25
 */
@Controller
@RequestMapping("/manage/video")
public class VideoController extends BasicController{

    @Autowired
    private VideoService videoService;



    @GetMapping("/showBgmList")
    public String showBgmList() {
        return "video/bgmList";
    }

    @PostMapping("/queryBgmList")
    @ResponseBody
    public PagedResult queryBgmList(Integer page) {
        return videoService.queryBgmList(page, 8);
    }

    @GetMapping("/showAddBgm")
    public String showAddBgm() {
        return "video/addBgm";
    }

    @PostMapping("/addBgm")
    @ResponseBody
    public JSONResult addBgm(Bgm bgm) {

        videoService.addBgm(bgm);
        return JSONResult.ok();
    }

    @PostMapping("/deleteBgm")
    @ResponseBody
    public JSONResult deleteBgm(String id) {

        videoService.deleteBgm(id);
        return JSONResult.ok();
    }

    @PostMapping("/bgmUpload")
    @ResponseBody
    public JSONResult bgmUpload(@RequestParam("file") MultipartFile file) throws Exception {

        //保存到数据库中的相对路径
        String fileSpace = "D:" + File.separator + "video_dev" + File.separator + "admin-bgm";
        // 保存到数据库中的相对路径
        String uploadPathDB = File.separator + "bgm";

        String finalBgmPath = null;
        if(file != null){
            String fileName = file.getOriginalFilename();
            if(StringUtils.isNotEmpty(fileName)){
                finalBgmPath = fileSpace + uploadPathDB + File.separator + fileName;
                // 设置数据库保存的路径
                uploadPathDB += (File.separator + fileName);

                File outFile = new File(finalBgmPath);
                if(outFile.getParentFile() != null || outFile.isDirectory() ){
                    // 创建父文件夹
                    outFile.getParentFile().mkdirs();
                }

                file.transferTo(outFile);
            }else {
                return JSONResult.errorMsg("上传出错...");
            }
        }else {
            return JSONResult.errorMsg("上传视频为空...");
        }


        return JSONResult.ok(uploadPathDB);
    }

    @GetMapping("/showReportList")
    public String showReportList() {
        return "video/reportList";
    }

    @PostMapping("/reportList")
    @ResponseBody
    public PagedResult reportList(Integer page) {

        PagedResult result = videoService.queryReportList(page, 8);
        return result;
    }

    @PostMapping("/updateVideoStatus")
    @ResponseBody
    public JSONResult forbidVideo(String videoId) {

        videoService.updateVideoStatus(videoId, VideoStatusEnum.FORBID.value);
        return JSONResult.ok();
    }
}
