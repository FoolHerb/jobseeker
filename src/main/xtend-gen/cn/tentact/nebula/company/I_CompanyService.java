package cn.tentact.nebula.company;

import java.util.Map;

@SuppressWarnings("all")
public interface I_CompanyService {
  public abstract Map searchCompany(final String companyId);
  
  public abstract void insert(final Map map);
}
