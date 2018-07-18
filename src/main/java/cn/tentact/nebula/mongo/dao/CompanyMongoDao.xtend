package cn.tentact.nebula.mongo.dao

import java.util.Map
import org.springframework.stereotype.Repository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.bson.Document
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria
import java.util.HashMap
import java.util.List

@Repository
class CompanyMongoDao implements I_CompanyMongoDao{
	
	@Autowired
	MongoTemplate mongo;
	
	override insert(Document document) {
		mongo.insert(document , "company");
	}
	
	override getCompanyItro(String id) {
		var query=new Query(Criteria.where("company_id").is(id));
		var map = mongo.findOne(query,Map , "company");
		println(map.get("com_itro")as String);
		return map;
	}

}

