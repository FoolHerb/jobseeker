package cn.tentact.nebula.company;

import cn.tentact.nebula.company.I_CompanyService;
import cn.tentact.nebula.db.dao.I_CompanyDao;
import cn.tentact.nebula.mongo.dao.I_CompanyMongoDao;
import java.util.Map;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("all")
public class CompanyService implements I_CompanyService {
  @Autowired
  private I_CompanyDao companyDao;
  
  @Autowired
  private I_CompanyMongoDao companyMongoDao;
  
  @Override
  public Map searchCompany(final String companyId) {
    Map map = this.companyDao.searchCompany(companyId);
    Map temp = this.companyMongoDao.getCompanyItro(companyId);
    Object _get = temp.get("com_itro");
    map.put("itroduction", ((String) _get));
    return map;
  }
  
  @Transactional
  @Override
  public void insert(final Map map) {
    Document document = new Document();
    Object _get = map.get("companyId");
    String id = ((String) _get);
    Object _get_1 = map.get("itro");
    String itro = ((String) _get_1);
    document.put("company_id", id);
    document.put("com_itro", itro);
    this.companyMongoDao.insert(document);
  }
}
