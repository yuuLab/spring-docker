package com.yuuLab.springLab.db.testtool;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.stream.IDataSetProducer;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;

public class DBTestWorker {
	
	private static final String PRE_DATASET_DIR = "src/test/resources/dataset/";
	
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/develop";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "password";
	
	protected IDataSet beforeDataset;
	
	protected IDataSet expectedDataset;
	
	protected  IDataSetProducer producer;
	
	protected IDatabaseConnection dbconn;

	private Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DBTestWorker.class);
	
	
	/**
	 * コンストラクタ。
	 * 
	 * @param preDatasetFileDir テストに用いる事前データセットファイル名
	 * @throws Exception
	 */
	public DBTestWorker(String preDatasetFileDir) throws Exception {
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		conn.setAutoCommit(false);
		this.dbconn = new DatabaseConnection(conn);
		DatabaseConfig config = dbconn.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
		
		this.beforeDataset = new CsvDataSet(new File(PRE_DATASET_DIR + preDatasetFileDir + "/before"));
		this.expectedDataset = new CsvDataSet(new File(PRE_DATASET_DIR + preDatasetFileDir + "/expected"));
	}
	
	public void setUpData()  throws Exception {
		try {
			this.deleteAllData();
			this.insertPreDataset();
		} catch (Exception e) {
			LOGGER.info("failed to set up data", e);
			throw e;
		}
	}
	
	
	private void deleteAllData() throws Exception {
		LOGGER.info("DELETE ALL DATA");
		DatabaseOperation.DELETE_ALL.execute(dbconn, this.beforeDataset);
	}
	
	private void insertPreDataset()  throws Exception {
		LOGGER.info("INSERT TEST DATA");
		DatabaseOperation.INSERT.execute(dbconn, this.beforeDataset);
	}
	
	public ITable getExpectedData(String tableName) throws Exception {
		// 期待されるデータを取得
        ITable expectedTable = this.expectedDataset.getTable(tableName);
        return expectedTable;
	}
	
	public ITable getActualData(String tableName) throws Exception {
		ITable actualTable = this.dbconn.createDataSet().getTable(tableName);
		return actualTable;
	}
	
	/**
	* DBコネクションクローズ
	*
	* @param dbConn DBコネクション
	* @throws SQLException 例外
	*/
	public void closeDbConn() throws SQLException {
		if (this.dbconn != null) {
			this.dbconn.close();
		}
	}
}
