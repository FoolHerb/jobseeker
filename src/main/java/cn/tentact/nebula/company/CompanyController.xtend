package cn.tentact.nebula.company

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.web.ResponseBean
import org.springframework.web.bind.annotation.RequestHeader

@RestController
@RequestMapping("/company")
class CompanyController implements I_CompanyController{
	
	@Autowired
	I_CompanyService companyService;
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchCompany")
	override searchCompany(@RequestHeader("Authorization") String token,String companyId) {
		var map = companyService.searchCompany(companyId);
		return new ResponseBean(200 , "查询结果" , true , map);
	}
	
	@RequestMapping("/insertCompany")
	override insertCompany(String companyId, String itro) {
		var map = newHashMap("companyId"->companyId , "itro"->itro);
		companyService.insert(map);
		return new ResponseBean(200 , "查询" , true , null);
	}
	
	
	
}