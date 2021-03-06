package com.jyh.videoadmin.controller;



import com.jyh.videoadmin.common.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class BasicController {
	
	@Autowired
	public RedisOperator redis;
	
	public static final String USER_REDIS_SESSION = "user-redis-session";
	
	// 文件保存的命名空间
	public static final String FILE_SPACE = "D:/video_dev";
	
	// ffmpeg所在目录
	public static final String FFMPEG_EXE = "D:\\Software\\ffmpeg\\bin\\ffmpeg.exe";
	
	// 每页分页的记录数
	public static final Integer PAGE_SIZE = 5;

	public static final Integer TIMEOUT = 60*60*24*30;
	
}
