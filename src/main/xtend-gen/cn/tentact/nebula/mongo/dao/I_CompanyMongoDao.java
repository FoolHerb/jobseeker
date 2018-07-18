package cn.tentact.nebula.mongo.dao;

import java.util.Map;
import org.bson.Document;

@SuppressWarnings("all")
public interface I_CompanyMongoDao {
  public abstract void insert(final Document document);
  
  public abstract Map getCompanyItro(final String id);
}
