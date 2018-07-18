package cn.tentact.nebula.company

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_CompanyDao
import java.util.Map
import org.springframework.transaction.annotation.Transactional
import org.bson.Document
import cn.tentact.nebula.mongo.dao.I_CompanyMongoDao

@Service
class CompanyService implements I_CompanyService{
	@Autowired
	I_CompanyDao companyDao;
	
	@Autowired
	I_CompanyMongoDao companyMongoDao;
	
	override searchCompany(String companyId) {
		var map = companyDao.searchCompany(companyId);
		var temp = companyMongoDao.getCompanyItro(companyId);
		map.put("itroduction" , temp.get("com_itro") as String);
		return map;	
	}
	
	@Transactional
	override insert(Map map) {
		
		var document = new Document;
		var id = map.get("companyId") as String;
		var itro = map.get("itro") as String;
		document.put("company_id" , id);
		document.put("com_itro" , itro);
		companyMongoDao.insert(document);

	}
}