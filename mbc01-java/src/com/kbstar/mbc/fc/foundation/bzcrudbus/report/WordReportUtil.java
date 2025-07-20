package com.kbstar.mbc.fc.foundation.bzcrudbus.report;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.rtf.IRTFDocumentTransformer;
import net.sourceforge.rtf.RTFTemplate;
import net.sourceforge.rtf.handler.RTFDocumentHandler;
import net.sourceforge.rtf.template.velocity.RTFVelocityTransformerImpl;
import net.sourceforge.rtf.template.velocity.VelocityTemplateEngineImpl;

import org.apache.velocity.app.VelocityEngine;

import com.kbstar.ksa.das.ibatis.KesaSqlMapClient;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility.Utils;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IIfrsLogger;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IfrsLogHelper;

/**
 * Word 리포트 유틸리티 클래스
 * 
 * 프로그램명: WordReportUtil.java
 * 설명: Word 문서 생성을 위한 RTF 템플릿 처리 유틸리티 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - RTF 템플릿 기반 Word 문서 생성
 * - Velocity 템플릿 엔진을 통한 데이터 바인딩
 * - 그리드 데이터 및 변수 처리
 * - SQL 데이터 조회 및 매핑
 * - 로깅 및 에러 처리
 * 
 * @version 1.0
 */
public class WordReportUtil {

	/** 그리드 데이터 타입 구분 */
	private static final String TYPE_GRIDDATA = "GD";

	/** RTF 템플릿 객체 */
	private RTFTemplate rtfTemplate = null;

	/** RTF 템플릿 경로 */
	private String rtfTemplatePath = null;

	/** RTF 리포트 경로 */
	private String rtfReportPath = null;

	/**
	 * WordReportUtil 생성자
	 * 
	 * @param rtfTemplatePath RTF 템플릿 경로
	 * @param rtfReportPath   RTF 리포트 경로
	 */
	public WordReportUtil(String rtfTemplatePath, String rtfReportPath) {

		// 템플릿이 저장된 위치 설정
		this.rtfTemplatePath = rtfTemplatePath;

		// 리포트가 저장될 위치 설정
		this.rtfReportPath = rtfReportPath;

		rtfTemplate = new RTFTemplate();

		// Parser 설정
		RTFDocumentHandler parser = new RTFDocumentHandler();
		rtfTemplate.setParser(parser);

		// Transformer 설정
		IRTFDocumentTransformer transformer = new RTFVelocityTransformerImpl();
		rtfTemplate.setTransformer(transformer);

		// Template engine 설정
		VelocityTemplateEngineImpl templateEngine = new VelocityTemplateEngineImpl();

		// velocity engine 초기화
		VelocityEngine velocityEngine = new VelocityEngine();
		templateEngine.setVelocityEngine(velocityEngine);
		rtfTemplate.setTemplateEngine(templateEngine);

	}

	/**
	 * 텍스트 변수를 설정한다.
	 * 
	 * @param key   변수 키
	 * @param value 변수 값
	 */
	private void setText(String key, String value) {
		rtfTemplate.put(key, value);
	}

	/**
	 * 맵 변수를 설정한다.
	 * 
	 * @param key 변수 키
	 * @param map 맵 객체
	 */
	private void setMap(String key, Map map) {
		rtfTemplate.put(key, map);
	}

	/**
	 * 리스트 변수를 설정한다.
	 * 
	 * @param key  변수 키
	 * @param list 리스트 객체
	 */
	private void setList(String key, List list) {
		rtfTemplate.put(key, list);
	}

	/**
	 * 템플릿 경로를 반환한다.
	 * 
	 * @return 템플릿 경로
	 */
	private String getTemplatePath() {
		return rtfTemplatePath;
	}

	/**
	 * 리포트 경로를 반환한다.
	 * 
	 * @return 리포트 경로
	 */
	private String getReportPath() {
		return rtfReportPath;
	}

	/**
	 * 리포트를 생성한다.
	 * 
	 * @param resultList   리포트 메타 데이터 리스트
	 * @param sqlMapClient SQL 맵 클라이언트
	 */
	public void makeReport(List<ReportMeta> resultList, KesaSqlMapClient sqlMapClient) {

		IIfrsLogger logger = IfrsLogHelper.getServer();

		logger.info("### Word Report ### : Generation Begin...");

		if (resultList != null && resultList.size() > 0) {

			try {
				ReportMeta rptMeta = null;
				Map resultMap = null;
				Map tempMap = null;
				Map objectMap = null;
				Set keySet = null;
				String mapKey = null;
				String mapValue = null;
				String rtfTarget = null;
				String baseYm = null;
				String rptdocId = null;
				String rptdocName = null;
				String fxdfmType = null;
				String varName = null;
				String varValue = null;

				int i = 1;
				String prefix = "### Word Report ###";

				for (Iterator itr = snapshotIterator(resultList); itr.hasNext();) {

					try {

						rptMeta = (ReportMeta) itr.next();

						baseYm = rptMeta.getBaseYm().trim();
						rptdocId = rptMeta.getRptdocId().trim();
						rptdocName = rptMeta.getRptdocName().trim();
						fxdfmType = rptMeta.getFxdfmType().trim();
						varName = rptMeta.getVarName().trim();
						varValue = rptMeta.getVarValue().trim();

						// 첫번째 데이터의 리포트ID를 사용하여 템플릿을 설정한다.
						if (i == 1) {

							// 템플릿 파일 경로 (템플릿파일명은 {리포트ID}.rtf 형태)
							String rtfSource = getTemplatePath() + rptdocId + ".rtf";

							rtfTemplate.setTemplate(new File(rtfSource));

							logger.info(prefix + " : Word Template Found : " + rtfSource);

							// 리포트 파일명
							rtfTarget = getReportPath() + rptdocName + ".doc";
						}

						// 타입에 따른 데이터처리 (그리드 데이터)
						if (fxdfmType.equals("GD")) {

							logger.info(prefix + " : GridData Set : (SQLID : '" + varValue + "') : Begin...");

							// 조건 맵 생성
							Map conditionMap = new HashMap();
							// 기준년월
							conditionMap.put("BaseYm", baseYm);
							// 리포트ID
							conditionMap.put("RptdocId", rptdocId.trim());

							// SQLID == rptMeta.getVarValue()

							List<Map> objectList = sqlMapClient.queryForList(varValue, conditionMap);
							/*
							 * List<Map> rawObjectList = sqlMapClient.queryForList(varValue, conditionMap);
							 * List<Map> objectList = new ArrayList();
							 * for (Iterator itr2 = snapshotIterator(rawObjectList); itr2.hasNext();) {
							 * tempMap = (Map)itr2.next();
							 * 
							 * objectMap = new HashMap();
							 * 
							 * keySet = tempMap.keySet();
							 * 
							 * for (Iterator itr3 = snapshotIterator(keySet); itr3.hasNext();) {
							 * mapKey = (String)itr3.next();
							 * //mapValue = encValue((String)tempMap.get(mapKey));
							 * mapValue = (String)tempMap.get(mapKey);
							 * objectMap.put(mapKey, mapValue);
							 * logger.info(prefix + " : GridColumn Set : (ColName : '" + mapKey +
							 * "' / ColValue : " + (String)tempMap.get(mapKey) + ")");
							 * }
							 * 
							 * objectList.add(objectMap);
							 * }
							 */
							logger.info(prefix + " : GridData Set : (VarName : '" + varName + "' / Object List : "
									+ objectList.toString() + ")");

							setList(varName, objectList);

							logger.info(prefix + " : GridData Set : (SQLID : '" + varValue + "') : End...");
						} else {

							logger.info(prefix + " : Variable Set : (VarName : '" + varName + "' / VarValue : '"
									+ varValue + "')");

							setText(varName, varValue);

						}
					} catch (Exception e) {

						logger.info(prefix + " : Error Occured : " + e.toString());
						continue;

					}

					i++;
				}

				merge(rtfTarget);

				logger.info(prefix + " : File Created : " + rtfTarget);
				logger.info(prefix + " : Generation End...");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 문자열 인코딩을 변환한다.
	 * 
	 * @param orgStr 원본 문자열
	 * @return 인코딩 변환된 문자열
	 */
	private String encValue(String orgStr) {

		// String encStr = Utils.toEnc(orgStr, "utf-8", "euc-kr");
		// String encStr = Utils.toEnc(orgStr, "8859_1", "euc-kr");
		// String encStr = Utils.toEnc(orgStr, "euc-kr", "utf-8");
		// String encStr = Utils.toEnc(orgStr, "euc-kr", "cp949");
		String encStr = Utils.toEnc(orgStr, "euc-kr", "8859_1");
		return encStr;
	}

	/**
	 * RTF 템플릿을 병합하여 최종 파일을 생성한다.
	 * 
	 * @param rtfTarget 대상 파일 경로
	 * @throws Exception 예외
	 */
	public void merge(String rtfTarget) throws Exception {
		rtfTemplate.merge(rtfTarget);
	}

	/**
	 * 컬렉션의 스냅샷 이터레이터를 반환한다.
	 * 
	 * @param collection 컬렉션
	 * @return 스냅샷 이터레이터
	 */
	public static Iterator snapshotIterator(Collection collection) {
		return new ArrayList(collection).iterator();
	}
}
