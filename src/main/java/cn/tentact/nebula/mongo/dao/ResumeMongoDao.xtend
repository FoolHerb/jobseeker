package cn.tentact.nebula.mongo.dao

import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import java.util.Map
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Update
import java.lang.reflect.Array

@Repository
class ResumeMongoDao implements I_ResumeMongoDao{
	
	
	@Autowired
	MongoTemplate mongo;

	
	override getResumeDetails(int resume_id) {
		var query = new Query(Criteria.where("resume_id").is(resume_id));
		return mongo.findOne(query , Map , "resume");
	}
	
	override updateResumeDetails(Map map) {
		var resume_id = map.get("resume_id");
		var s =  Integer.parseInt(resume_id.toString);
		var query = new Query(Criteria.where("resume_id").is(s.intValue));
		var update = Update.update("eduexp" , map.get("eduexp"));
		mongo.updateMulti(query , update , "resume");	
		update = Update.update("workexp" , map.get("workexp"));
		mongo.updateMulti(query , update , "resume");
		update = Update.update("selfintro" , map.get("selfintro"));
		mongo.updateMulti(query , update , "resume");
		update = Update.update("tech" , map.get("tech"));
		mongo.updateMulti(query , update , "resume");
		update = Update.update("techexp" , map.get("techexp"));
		mongo.updateMulti(query , update , "resume");
		update = Update.update("eduexpnum" , map.get("eduexpnum"));
		mongo.updateMulti(query , update , "resume");
		update = Update.update("workexpnum" , map.get("workexpnum"));
		mongo.updateMulti(query , update , "resume");
		return 1;
	}
	
	
}