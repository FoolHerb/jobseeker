package cn.tentact.nebula.company

import java.util.Map

interface I_CompanyService {
	def Map searchCompany(String companyId);
	
	def void insert(Map map);
}