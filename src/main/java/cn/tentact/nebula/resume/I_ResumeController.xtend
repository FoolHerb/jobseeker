package cn.tentact.nebula.resume

import cn.tentact.nebula.web.ResponseBean
import java.util.Arrays
import org.springframework.web.multipart.MultipartFile

interface I_ResumeController {
	/**
	 * 投递简历
	 * */
	 def ResponseBean sendMyResume(String token , int recruitId);
	 
	 /**
	  * 查询简历
	  * */
	  def ResponseBean searchResume(String token);
	  
	 /**
	  * 上传图像
	  * */
	 def ResponseBean upload(String token ,String file)
	    
	 /**
	  * 更新用户简历
	  * */
	 def ResponseBean updateResume(String token , String name, String sex , String Date , String height , String weight , String marry , String city , String education , String tel , String email , String position , String place , String money , String worktime ,String eduexp, String workexp,String selfintro, String tech,String techexp,String eduexpnum,String workexpnum);
	  
	}
