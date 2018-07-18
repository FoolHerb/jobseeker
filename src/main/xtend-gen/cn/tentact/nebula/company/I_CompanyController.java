package cn.tentact.nebula.company;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_CompanyController {
  public abstract ResponseBean searchCompany(final String token, final String companyId);
  
  public abstract ResponseBean insertCompany(final String companyId, final String itro);
}
