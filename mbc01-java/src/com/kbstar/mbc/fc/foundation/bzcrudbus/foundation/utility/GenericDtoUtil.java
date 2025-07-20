package com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.mbc.fc.foundation.bzcrudbus.config.Env;

/**
 * Generic DTO 유틸리티 클래스
 * 
 * 프로그램명: GenericDtoUtil.java
 * 설명: GenericDto 객체를 조작하고 XML 엘리먼트를 관리하는 유틸리티 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - GenericDto 엘리먼트 조작
 * - Map 리스트를 XML로 변환
 * - 헤더 속성 관리
 * - XML 엘리먼트 생성 및 관리
 */
public class GenericDtoUtil {

	/**
	 * 컬렉션의 스냅샷 이터레이터를 반환한다.
	 * 
	 * @param collection 컬렉션
	 * @return 스냅샷 이터레이터
	 */
	public static Iterator snapshotIterator(Collection collection) {
		return new ArrayList(collection).iterator();
	}

	/**
	 * OutData 엘리먼트를 반환한다.
	 * 
	 * @param gDto GenericDto 객체
	 * @return OutData 엘리먼트
	 */
	public static Element getOutDataElement(GenericDto gDto) {
		return getGenericDtoElement(gDto, "/KB-Message/Individual", "OutData");
	}

	/**
	 * Map을 리스트로 추가한다.
	 * 
	 * @param gDto        GenericDto 객체
	 * @param groupName   그룹 이름
	 * @param elementName 엘리먼트 이름
	 * @param map         맵 객체
	 * @param addType     추가 타입
	 */
	public static void addMapList(GenericDto gDto, String groupName, String elementName, HashMap map, String addType) {
		addMapList(gDto, groupName, elementName, map, addType, null);
	}

	/**
	 * Map을 리스트로 추가한다. (메타데이터 포함)
	 * 
	 * @param gDto        GenericDto 객체
	 * @param groupName   그룹 이름
	 * @param elementName 엘리먼트 이름
	 * @param map         맵 객체
	 * @param addType     추가 타입
	 * @param metaDataMap 메타데이터 맵
	 */
	public static void addMapList(GenericDto gDto, String groupName, String elementName, HashMap map, String addType,
			HashMap metaDataMap) {
		List<HashMap> mapList = new Vector();
		mapList.add(map);
		addMapList(gDto, groupName, elementName, mapList, addType, metaDataMap);
	}

	/**
	 * Map 리스트를 추가한다.
	 * 
	 * @param gDto      GenericDto 객체
	 * @param groupName 그룹 이름
	 * @param elmtName  엘리먼트 이름
	 * @param mapList   맵 리스트
	 * @param addType   추가 타입
	 */
	public static void addMapList(GenericDto gDto, String groupName, String elmtName, List<HashMap> mapList,
			String addType) {
		addMapList(gDto, groupName, elmtName, mapList, addType, null);
	}

	/**
	 * Map 리스트를 추가한다. (메타데이터 포함)
	 * 
	 * @param gDto        GenericDto 객체
	 * @param groupName   그룹 이름
	 * @param elmtName    엘리먼트 이름
	 * @param mapList     맵 리스트
	 * @param addType     추가 타입
	 * @param metaDataMap 메타데이터 맵
	 */
	public static void addMapList(GenericDto gDto, String groupName, String elmtName, List<HashMap> mapList,
			String addType, HashMap metaDataMap) {

		String elementName = elmtName;
		if (elementName == null || elementName.equals("")) {
			elementName = Env.getProperty("GenericDtoUtil.DATA_ELEMENT_NAME");
		}

		List<HashMap> dupMapList = mapList;

		Element outDataElmt = getOutDataElement(gDto);

		Element grpElmt = outDataElmt.addElement(groupName);

		if (mapList == null || mapList.size() < 1) {
			return;
		}

		HashMap<String, String> sampleMap = (HashMap) dupMapList.get(0);

		Object[] keyArray = sampleMap.keySet().toArray();

		HashMap rowMap = null;
		Element rowElmt = null;
		String keyStr = null;

		// Element 타입으로 추가
		if (addType != null && addType.equals("Element")) {

			Element keyElmt = null;

			for (Iterator itr = snapshotIterator(dupMapList); itr.hasNext();) {
				rowMap = (HashMap) itr.next();

				rowElmt = grpElmt.addElement(elementName);

				for (int i = 0; i < keyArray.length; i++) {

					keyStr = (String) keyArray[i];
					// keyElmt = rowElmt.addElement(keyStr.toLowerCase());
					if (metaDataMap != null && metaDataMap.containsKey(keyStr)) {
						keyElmt = rowElmt.addElement((String) metaDataMap.get(keyStr));
					} else {
						keyElmt = rowElmt.addElement(keyStr);
					}
					keyElmt.setText(rowMap.get(keyStr).toString().trim());

				}

			}

		} else {

			for (Iterator itr = snapshotIterator(dupMapList); itr.hasNext();) {
				rowMap = (HashMap) itr.next();

				rowElmt = grpElmt.addElement(elementName);

				for (int i = 0; i < keyArray.length; i++) {
					keyStr = (String) keyArray[i];
					// rowElmt.addAttribute(keyStr.toLowerCase(), rowMap.get(keyStr).toString());
					if (metaDataMap != null && metaDataMap.containsKey(keyStr)) {
						rowElmt.addAttribute((String) metaDataMap.get(keyStr), rowMap.get(keyStr).toString().trim());
					} else {
						rowElmt.addAttribute(keyStr, rowMap.get(keyStr).toString().trim());
					}
				}

			}
		}
	}

	/**
	 * GenericDto에서 특정 엘리먼트를 반환한다.
	 * 
	 * @param gDto        GenericDto 객체
	 * @param path        경로
	 * @param elementName 엘리먼트 이름
	 * @return XML 엘리먼트
	 */
	public static Element getGenericDtoElement(GenericDto gDto, String path, String elementName) {
		List elmtList = gDto.getElements(path);

		Element xmlElmt = null;

		for (Iterator itr = snapshotIterator(elmtList); itr.hasNext();) {
			xmlElmt = (Element) itr.next();

			if (xmlElmt.getName().equals(elementName)) {
				return xmlElmt;
			}
		}

		return xmlElmt;
	}

	/**
	 * 헤더 속성을 반환한다.
	 * 
	 * @param gDto     GenericDto 객체
	 * @param attrName 속성 이름
	 * @return 속성 값
	 */
	public static String getHeaderAttribute(GenericDto gDto, String attrName) {

		Element headerElmt = getGenericDtoElement(gDto, "/KB-Message/Individual", "DataHeader");

		if (headerElmt != null) {

			Attribute attr = headerElmt.attribute(attrName);
			if (attr != null) {
				return attr.getValue();
			}

		}

		return "";
	}

	/**
	 * 헤더 속성을 설정한다.
	 * 
	 * @param gDto      GenericDto 객체
	 * @param attrName  속성 이름
	 * @param attrValue 속성 값
	 */
	public static void setHeaderAttribute(GenericDto gDto, String attrName, String attrValue) {

		Element headerElmt = getGenericDtoElement(gDto, "/KB-Message/Individual", "DataHeader");

		if (headerElmt != null) {

			Attribute attr = headerElmt.attribute(attrName);
			if (attr != null) {
				attr.setValue(attrValue);
			}

		}
	}

}
