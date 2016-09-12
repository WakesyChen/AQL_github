package www.aql.com.utils;

import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.x;

import java.io.File;

public class DbManagerHelper {
	/**
	 * 获取DbManager
	 * 
	 * @param dbName
	 *            数据库名称
	 * @param dbDir
	 *            数据库路径
	 * @param allowTransaction
	 *            是否允许事务，一般为false
	 * @param dbVersion
	 *            数据库版本
	 * @return
	 */
	public static DbManager getDbManager(String dbName, File dbDir, boolean allowTransaction, int dbVersion) {
		DaoConfig daoConfig = new DaoConfig().setAllowTransaction(allowTransaction).setDbName(dbName)
				.setDbDir(dbDir).setDbVersion(dbVersion);
		return x.getDb(daoConfig);
	}

	/**
	 * 获取DbManager,默认不允许transaction
	 *
	 * @param dbName
	 *            数据库名称
	 * @param dbDir
	 *            数据库路径
	 * @param dbVersion
	 *            数据库版本
	 * @return
	 */
	public static DbManager getDbManager(String dbName, File dbDir, int dbVersion) {
		DaoConfig daoConfig = new DaoConfig().setAllowTransaction(false).setDbName(dbName).setDbDir(dbDir)
				.setDbVersion(dbVersion);
		return x.getDb(daoConfig);
	}

	/**
	 * 获取DbManager,默认不允许transaction,默认路径为context.getExternalFilesDir("")
	 *
	 * @param context
	 * @param dbName
	 *            数据库名称
	 * @param dbVersion
	 *            数据库版本
	 * @return
	 */
	public static DbManager getDbManager(String dbName, int dbVersion) {
		DaoConfig daoConfig = new DaoConfig().setAllowTransaction(false).setDbName(dbName)
				.setDbVersion(dbVersion);
		return x.getDb(daoConfig);
	}

	/**
	 * 获取DbManager,默认不允许transaction,默认路径为context.getExternalFilesDir("")，默认版本为1
	 *
	 * @param context
	 * @param dbName
	 *            数据库名称
	 * @return
	 */
	public static DbManager getDbManager(String dbName) {
		DaoConfig daoConfig = new DaoConfig().setAllowTransaction(false).setDbName(dbName);
		return x.getDb(daoConfig);
	}
}
