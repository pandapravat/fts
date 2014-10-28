package com.pravatpanda.apps.ats.dao;

import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class SetUpDatabaseBean {
	@Autowired
	private JdbcTemplate namedJdbcTemplate;


	@Resource(name="initQueryMap")
	Map<String, String> initQueryMap;

	/**
	 * Initializes the databasesif not done already
	 */
	@PostConstruct
	public void createDatabases() {
		if(!isCreated()) {
			Collection<String> keySet = initQueryMap.values();
			for (String tableDDL : keySet) {
				//log.info("Executing query table :" + tableDDL);
				namedJdbcTemplate.execute(tableDDL);
			}
		}
	}

	private boolean isCreated()  {
		try {
			String sql = "select 1 from associate fetch first row only";
			System.out.println("#####################ATTEMPTING TO CHECK TABLES**************");
			namedJdbcTemplate.queryForInt(sql);
			return true;
		} catch (IncorrectResultSizeDataAccessException e) {
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
