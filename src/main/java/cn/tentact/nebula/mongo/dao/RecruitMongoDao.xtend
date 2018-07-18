package cn.tentact.nebula.mongo.dao

import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.util.Map

@Repository
class RecruitMongoDao implements I_RecruitMongoDao{
	
	@Autowired
	MongoTemplate mongo;
	
	override getDescription(String id) {
		
		var query = new Query(Criteria.where("recruit_id").is(id));
		var map = mongo.findOne(query , Map , "recruit");
		return map.get("job_description") as String;
	}
	
}