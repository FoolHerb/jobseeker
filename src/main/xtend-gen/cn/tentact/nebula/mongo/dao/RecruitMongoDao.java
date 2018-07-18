package cn.tentact.nebula.mongo.dao;

import cn.tentact.nebula.mongo.dao.I_RecruitMongoDao;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public class RecruitMongoDao implements I_RecruitMongoDao {
  @Autowired
  private MongoTemplate mongo;
  
  @Override
  public String getDescription(final String id) {
    Criteria _is = Criteria.where("recruit_id").is(id);
    Query query = new Query(_is);
    Map map = this.mongo.<Map>findOne(query, Map.class, "recruit");
    Object _get = map.get("job_description");
    return ((String) _get);
  }
}
