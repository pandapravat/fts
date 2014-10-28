package com.pravatpanda.apps.ats.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.pravatpanda.apps.ats.bi.AtsException;

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
			
			// Insert the default logo image
			String sql = "insert into fts_meta values ('logo', ?)";
			ClassPathResource res = new ClassPathResource("fts_logo_default.jpg");
			try {
				final InputStream imageStream = res.getInputStream();
				namedJdbcTemplate.update(sql, new PreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement arg0) throws SQLException {
						arg0.setBinaryStream(1, imageStream);
						
					}
				});
			} catch (Exception e) {
				throw new AtsException("Error persisting logo to DB", e);
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
