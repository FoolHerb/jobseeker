package cn.tentact.nebula.mongo.dao;

import cn.tentact.nebula.mongo.dao.I_CompanyMongoDao;
import java.util.Map;
import org.bson.Document;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public class CompanyMongoDao implements I_CompanyMongoDao {
  @Autowired
  private MongoTemplate mongo;
  
  @Override
  public void insert(final Document document) {
    this.mongo.insert(document, "company");
  }
  
  @Override
  public Map getCompanyItro(final String id) {
    Criteria _is = Criteria.where("company_id").is(id);
    Query query = new Query(_is);
    Map map = this.mongo.<Map>findOne(query, Map.class, "company");
    Object _get = map.get("com_itro");
    InputOutput.<String>println(((String) _get));
    return map;
  }
}
