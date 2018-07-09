package com.botham.controllers;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.botham.aws.AwsHelper;
import com.botham.base.BaseHelper;
import com.botham.base.Info;
import com.botham.base.Storage;
import com.botham.db.resource.ResourceRepository;
import com.botham.domain.resource.Resource;
import com.botham.domain.resource.ResourceAsset;
import com.botham.domain.resource.ResourceAssetPK;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class MyRestController {

	@Autowired
	JdbcTemplate jdbc;

	@Autowired
	ResourceRepository repository;

	@Value("${spring.datasource.url}")
	public String springDatasourceUrl;

	private final String cName = "Server ";

	private static final Logger log = LoggerFactory.getLogger(MyRestController.class);

	@Consumes({ "application/xml", "application/json", "text/plain,text/html" })
	@Produces({ "application/xml", "application/json", "text/plain,text/html" })
	@RequestMapping("/api/hi")
	@ResponseBody
	public String hi(String request) {

		AwsHelper.s3ListBuckets(AwsHelper.region_us_east_1);

		archiveFile();

		try {
			doDb();
		} catch (MetaDataAccessException e) {
			e.printStackTrace();
		}

		return "hi";
	}

	public static void archiveFile() {
		String mName = "archiveFile";
		log.info(mName + " Starts");
		String localFilePath = "c:/pubs/320/20170602/NaplesDailyNews_20170602-PAGE001.pdf";
		File file = new File(localFilePath);
		String key = localFilePath.replaceAll("c:/", "");
		key = "pubs/320/20170602/NaplesDailyNews_20170602-PAGE001.pdf";
		

		AwsHelper.s3AddToBucket("florida-newsclips", key, file, AwsHelper.region_us_east_1);
		log.info(mName + " Ends");

	}

	public void doDbStuff() throws SQLException, MetaDataAccessException {
		String mName = "dpDbStuff";
		// repository.
		jdbc.execute("Select * from dbo.questions");

		DatabaseMetaData meta = jdbc.getDataSource().getConnection().getMetaData();

		// GetTableNames();

		log.info("done");

	}

	private boolean isPopulatedDatabase(DataSource dataSource) {
		if (dataSource == null) {
			log.warn("No datasource found");
			return false;
		}
		try {
			log.info("before 1");
			return (boolean) JdbcUtils.extractDatabaseMetaData(dataSource, new DatabaseMetaDataCallback() {

				@Override
				public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
					ResultSet tables = dbmd.getTables(null, "dbo.questions", null, null);
					log.info("before 2");
					boolean resultRow = tables.first();
					log.info("before 3");
					log.info("   Table MolgenisUser {}found.", resultRow ? "" : "not ");
					return resultRow;
				}
			});
		} catch (MetaDataAccessException e) {
			return false;
		}
	}

	public static List<String> getColumnNames(final javax.sql.DataSource dataSource, final String tableName)
			throws MetaDataAccessException {
		final List<String> columnNames = new ArrayList<String>();
		JdbcUtils.extractDatabaseMetaData(dataSource, new DatabaseMetaDataCallback() {
			@Override
			public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
				ResultSet rs = dbmd.getColumns(null, null, tableName, null);
				while (rs.next()) {
					String columnName = rs.getString("COLUMN_NAME");
					columnNames.add(columnName);
					String columnType = rs.getString("TYPE_NAME");
					int columnSize = rs.getInt("COLUMN_SIZE");
					int columnNullable = rs.getInt("NULLABLE");
					log.info("" + columnName + " " + columnType + " " + columnSize);
				}
				rs.close();
				return null;
			}
		});
		return columnNames;
	}

	public static List<String> getTableNames(final javax.sql.DataSource dataSource, final String tableName)
			throws MetaDataAccessException {
		final List<String> tableNames = new ArrayList<String>();
		JdbcUtils.extractDatabaseMetaData(dataSource, new DatabaseMetaDataCallback() {
			@Override
			public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
				ResultSet rs = dbmd.getTables(null, null, null, null);
				// new String[] { "TABLES"});
				while (rs.next()) {
					String name = rs.getString("TABLE_NAME");

					// log.info(""+rs);

					tableNames.add(name);
				}
				rs.close();
				return null;
			}
		});
		return tableNames;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doDb() throws MetaDataAccessException {
		String mName = "doDb";
		// log.info(mName+" "+isPopulatedDatabase(jdbc.getDataSource()));
		log.info(mName + " " + getTableNames(jdbc.getDataSource(), "questions"));
		log.info(mName + " " + getColumnNames(jdbc.getDataSource(), "questions"));
	}

	@Consumes({ "application/xml", "application/json", "text/plain,text/html" })
	@Produces({ "application/xml", "application/json", "text/plain,text/html" })
	@RequestMapping("/api/checkin")
	@ResponseBody
	public ResponseEntity<String> processCheckIn(RequestEntity<Info> request)
			throws JsonParseException, JsonMappingException, IOException {

		String mName = "processCheckin -------------------";
		if (log.isInfoEnabled()) {
			log.info(cName + mName + " Starts, request=" + request.getBody());
		}

		String build = BaseHelper.getBuildInfo();

		log.info(mName + " ");
		log.info(mName + " springDatasourceUrl=" + springDatasourceUrl);

		processCheckin(request.getBody());

		log.info(mName + " ");
		log.info(mName + " ");

		if (log.isInfoEnabled()) {
			log.info(mName + " Ends");
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@Transactional
	public void processCheckin(Info info) {

		String mName = "Server-processCheckin(Info)";

		if (log.isInfoEnabled()) {
			log.info(mName + " Starts, info=" + info.toString());
		}

		String thisSystemName = System.getenv("B_SYSTEM_NAME");
		log.debug(mName + " B_SYSTEM_NAME=" + thisSystemName);

		Date nowSqlDate = new Date(System.currentTimeMillis());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		java.util.Date nowDate = new java.util.Date(System.currentTimeMillis());

		log.info(mName + " " + info.getSystemName() + " checking in at " + nowSqlDate + " " + nowDate);

		Resource exists = new Resource();

		exists = repository.findOne(info.getSystemName());

		if (exists != null) {
			exists.setLastCheckin(timestamp);
			exists.setCheckinCount(exists.getCheckinCount().add(new BigDecimal("1.00")));
			exists.setInfo("Java=" + info.getJavaVersion() + " Build=" + info.getBuild() + " Location="
					+ info.getGeoLocation().getCity() + " " + info.getGeoLocation().getIp());

			updateResourceAssets(info.getSystemName(), info.getStorageList());

			exists.setCheckinServer(thisSystemName);
			
			exists.setTimeAtRemote(info.getLocalTime());
			
			exists.setClientVersion(info.getClientVersion());
			repository.save(exists);
		} else {
			Resource new1 = new Resource();
			new1.setId(info.getSystemName());
			new1.setLastCheckin(timestamp);
			new1.setCheckinCount(new BigDecimal("1.00"));
			new1.setInfo("Java=" + info.getJavaVersion() + " Build=" + info.getBuild() + " Location="
					+ info.getGeoLocation().getCity() + " " + info.getGeoLocation().getIp());

			updateResourceAssets(info.getSystemName(), info.getStorageList());

			new1.setCheckinServer(thisSystemName);
			
			new1.setTimeAtRemote(info.getLocalTime());
			
			new1.setClientVersion(info.getClientVersion());
			repository.save(new1);
		}

	}

	public void updateResourceAssets(String infoKey, List<Storage> storageList) {
		
		List<ResourceAsset> ra = new ArrayList<>();
		
		for (Storage s : storageList) {
			ResourceAssetPK rapk = new ResourceAssetPK(infoKey, s.getName());
			ra.add(new ResourceAsset(rapk));
		}

	}

	@PostConstruct
	public void postConstruct() {
		String mName = "postContruct";
		if (log.isDebugEnabled()) {
			log.debug(mName + " Controller is up");
		}
	}

}
