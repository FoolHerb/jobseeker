package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_CompanyDao {
	def Map searchCompany(String companyId);
}