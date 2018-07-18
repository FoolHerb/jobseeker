package cn.tentact.nebula.mongo.dao

import java.util.Map
import org.bson.Document
import java.util.List

interface I_CompanyMongoDao {
	def void insert(Document document);
	
	def Map getCompanyItro(String id);
}