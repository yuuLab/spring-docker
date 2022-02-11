package com.yuuLab.springLab.db.testtool;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.dataset.stream.IDataSetProducer;
import org.dbunit.operation.DatabaseOperation;

public class DBTestWorker {
	
	private static final String PRE_DATASET_DIR = "src/test/resources/dataset/premise/";
	
	protected IDataSet dataset;
	
	protected  IDataSetProducer producer;
	
	protected IDatabaseConnection dbconn;

	
	/**
	 * コンストラクタ。
	 * 
	 * @param preDatasetFileDir テストに用いる事前データセットファイル名
	 * @throws Exception
	 */
	public DBTestWorker(String preDatasetFileDir) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/develop","root","password");
		this.dbconn = new DatabaseConnection(conn);
		this.dataset = new CsvDataSet(new File(PRE_DATASET_DIR + preDatasetFileDir));
	}
	
	public void deleteAllData() throws Exception {
		DatabaseOperation.DELETE_ALL.execute(dbconn, this.dataset);
	}
	
	public void insertPreDataset()  throws Exception {
		DatabaseOperation.INSERT.execute(dbconn, this.dataset);
	}
}
