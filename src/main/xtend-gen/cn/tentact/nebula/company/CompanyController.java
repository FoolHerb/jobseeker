package cn.tentact.nebula.company;

import cn.tentact.nebula.company.I_CompanyController;
import cn.tentact.nebula.company.I_CompanyService;
import cn.tentact.nebula.web.ResponseBean;
import java.util.HashMap;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@SuppressWarnings("all")
public class CompanyController implements I_CompanyController {
  @Autowired
  private I_CompanyService companyService;
  
  @RequiresRoles("求职者")
  @RequestMapping("/searchCompany")
  @Override
  public ResponseBean searchCompany(@RequestHeader("Authorization") final String token, final String companyId) {
    Map map = this.companyService.searchCompany(companyId);
    return new ResponseBean(200, "查询结果", true, map);
  }
  
  @RequestMapping("/insertCompany")
  @Override
  public ResponseBean insertCompany(final String companyId, final String itro) {
    Pair<String, String> _mappedTo = Pair.<String, String>of("companyId", companyId);
    Pair<String, String> _mappedTo_1 = Pair.<String, String>of("itro", itro);
    HashMap<String, String> map = CollectionLiterals.<String, String>newHashMap(_mappedTo, _mappedTo_1);
    this.companyService.insert(map);
    return new ResponseBean(200, "查询", true, null);
  }
}
