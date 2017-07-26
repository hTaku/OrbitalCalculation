package com.kansate.collect;

import java.io.File;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import com.iciql.Db;
import com.kansate.common.exception.ApplicationException;
import com.kansate.db.model.ApisSatelliteinfo;
import com.kansate.orbital.tle.element.OrbitalElementEntity;
import com.kansate.orbital.tle.file.TleReader;
import com.kansate.type.IsDel;

/**
 * TLEを収集
 */
public class Collector {

	/**
	 * コンストラクタ
	 */
	public Collector() {
	}

	/**
	 * 収集を実行する
	 * @throws SQLException 
	 */
	public void execute(String dirPath) {
		// TLEを読み込む
		TleReader reader = new TleReader();
		FileUtils.listFiles(new File(dirPath), null, false).forEach(file -> reader.load(file.getPath()));

		Db db = Db.open("jdbc:mysql://localhost:3306/kanpai?useSSL=false&useUnicode=true&characterEncoding=UTF-8", "kanpai", "passwd");
		try {
			db.setAutoSavePoint(false);
			db.getConnection().setAutoCommit(false);
			while (reader.hasNext()) {
				ApisSatelliteinfo entity = createApisSatelliteinfo(reader.next());
				// DBへ登録
				db.insert(entity);
			}
			db.getConnection().commit();
		} catch (SQLException e) {
			throw new ApplicationException(e);
		} finally {
			db.close();
		}
	}

	/**
	 * 衛星情報のエンティティを作成する。
	 * @param orbitalElement TLE情報
	 * @return 衛星情報のエンティティ
	 */
	private ApisSatelliteinfo createApisSatelliteinfo(OrbitalElementEntity orbitalElement) {
		ApisSatelliteinfo entity = new ApisSatelliteinfo();
		entity.satName = orbitalElement.getSatName();
		entity.epochYear = orbitalElement.getEpochYear();
		entity.epochTime = orbitalElement.getEpochTime();
		entity.argumentOfPerigee = orbitalElement.getArgumentOfPerigee().doubleValue();
		entity.inclinationAngle = orbitalElement.getInclinationAngle().doubleValue();
		entity.rightAscensionOfAscendingNode = orbitalElement.getRightAscensionOfAscendingNode().doubleValue();
		entity.eccentricity = orbitalElement.getEccentricity().doubleValue();
		entity.meanAnomaly = orbitalElement.getMeanAnomaly().doubleValue();
		entity.meanMotion = orbitalElement.getMeanMotion().doubleValue();
		entity.meanMotionCoefficientChange = orbitalElement.getMeanMotionCoefficientChange().doubleValue();
		entity.isDel = IsDel.Anable.ordinal();
		return entity;
	}
}
