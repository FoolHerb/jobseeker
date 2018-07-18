package cn.tentact.nebula.mongo.dao;

import cn.tentact.nebula.mongo.dao.I_ResumeMongoDao;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@SuppressWarnings("all")
public class ResumeMongoDao implements I_ResumeMongoDao {
  @Autowired
  private MongoTemplate mongo;
  
  @Override
  public Map getResumeDetails(final int resume_id) {
    Criteria _is = Criteria.where("resume_id").is(Integer.valueOf(resume_id));
    Query query = new Query(_is);
    return this.mongo.<Map>findOne(query, Map.class, "resume");
  }
  
  @Override
  public int updateResumeDetails(final Map map) {
    Object resume_id = map.get("resume_id");
    int s = Integer.parseInt(resume_id.toString());
    Criteria _is = Criteria.where("resume_id").is(Integer.valueOf(Integer.valueOf(s).intValue()));
    Query query = new Query(_is);
    Update update = Update.update("eduexp", map.get("eduexp"));
    this.mongo.updateMulti(query, update, "resume");
    update = Update.update("workexp", map.get("workexp"));
    this.mongo.updateMulti(query, update, "resume");
    update = Update.update("selfintro", map.get("selfintro"));
    this.mongo.updateMulti(query, update, "resume");
    update = Update.update("tech", map.get("tech"));
    this.mongo.updateMulti(query, update, "resume");
    update = Update.update("techexp", map.get("techexp"));
    this.mongo.updateMulti(query, update, "resume");
    update = Update.update("eduexpnum", map.get("eduexpnum"));
    this.mongo.updateMulti(query, update, "resume");
    update = Update.update("workexpnum", map.get("workexpnum"));
    this.mongo.updateMulti(query, update, "resume");
    return 1;
  }
}
