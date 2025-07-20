package com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

/**
 * DOM4J XML 처리 유틸리티 클래스
 * 
 * 프로그램명: Dom4jUtil.java
 * 설명: DOM4J 라이브러리를 사용한 XML 문서 처리 유틸리티 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - XML 문서 파싱 및 생성
 * - XML 노드 및 엘리먼트 조작
 * - XML 속성 맵 처리
 * - XML에서 데이터 추출
 * - XML 속성 설정 및 조회
 * 
 * @version 1.0
 */
public class Dom4jUtil {

	/**
	 * 문자열 XML을 Document 객체로 변환한다.
	 * 
	 * @param xml XML 문자열
	 * @return Document 객체
	 */
	public static Document getDocument(String xml) {
		try {
			return DocumentHelper.parseText(new String(xml));
		} catch (DocumentException e) {
			return null;
		}
	}

	/**
	 * 바이트 배열 XML을 Document 객체로 변환한다.
	 * 
	 * @param xml XML 바이트 배열
	 * @return Document 객체
	 */
	public static Document getDocument(byte[] xml) {
		try {
			return DocumentHelper.parseText(new String(xml));
		} catch (DocumentException e) {
			return null;
		}
	}

	/**
	 * XPath로 노드를 조회한다.
	 * 
	 * @param doc  Document 객체
	 * @param path XPath 경로
	 * @return Node 객체
	 */
	public static Node getNode(Document doc, String path) {
		return doc.selectSingleNode(path);
	}

	/**
	 * XPath로 엘리먼트를 조회한다.
	 * 
	 * @param doc  Document 객체
	 * @param path XPath 경로
	 * @return Element 객체
	 */
	public static Element getElement(Document doc, String path) {
		return getElement(doc, path, false);
	}

	/**
	 * XPath로 엘리먼트를 조회하고, 없으면 생성한다.
	 * 
	 * @param doc     Document 객체
	 * @param path    XPath 경로
	 * @param bCreate 생성 여부
	 * @return Element 객체
	 */
	public static Element getElement(Document doc, String path, boolean bCreate) {
		Element elmt = (Element) doc.selectSingleNode(path);

		if (bCreate && elmt == null) {
			String[] nodeArray = null;

			if (path.startsWith("/")) {
				nodeArray = path.substring(1).split("/");
			} else {
				nodeArray = path.split("/");
			}
			Element pElmt = null;
			Element tmpElmt = null;
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("/");

			// 경로를 따라 노드를 생성한다.
			for (int i = 0; i < nodeArray.length; i++) {
				strBuf.append(nodeArray[i]);

				tmpElmt = getElement(doc, strBuf.toString());

				if (tmpElmt == null) {
					if (i == 0) {
						tmpElmt = doc.addElement(nodeArray[i]);
					} else {
						tmpElmt = pElmt.addElement(nodeArray[i]);
					}
				}

				pElmt = tmpElmt;
				strBuf.append("/");
			}

			elmt = pElmt;
		}

		return elmt;
	}

	/**
	 * 노드의 속성 맵을 반환한다.
	 * 
	 * @param node Node 객체
	 * @return 속성 맵
	 */
	public static Map getAttributeMap(Node node) {

		Map attrMap = null;

		if (node instanceof Element) {
			Element elmt = (Element) node;

			if (elmt != null) {
				List attrList = elmt.attributes();
				attrMap = new LinkedHashMap(attrList.size());

				Attribute attr = null;
				for (Iterator iterator = snapshotIterator(attrList); iterator.hasNext();) {
					attr = (Attribute) iterator.next();
					attrMap.put(attr.getName(), attr.getStringValue());
				}
			}
		}

		return attrMap;
	}

	/**
	 * 엘리먼트의 속성 맵을 반환한다.
	 * 
	 * @param elmt Element 객체
	 * @return 속성 맵
	 */
	public static Map getAttributeMap(Element elmt) {

		Map attrMap = null;

		if (elmt != null) {
			List attrList = elmt.attributes();
			attrMap = new LinkedHashMap(attrList.size());

			Attribute attr = null;
			for (Iterator iterator = snapshotIterator(attrList); iterator.hasNext();) {
				attr = (Attribute) iterator.next();
				attrMap.put(attr.getName(), attr.getStringValue());
			}
		}

		return attrMap;
	}

	/**
	 * XPath로 노드를 조회하고 속성 맵을 반환한다.
	 * 
	 * @param doc  Document 객체
	 * @param path XPath 경로
	 * @return 속성 맵
	 */
	public static Map getAttributeMap(Document doc, String path) {
		Node node = (Node) doc.selectSingleNode(path);
		return getAttributeMap(node);
	}

	/**
	 * XPath로 엘리먼트 리스트의 속성 맵 리스트를 반환한다.
	 * 
	 * @param doc  Document 객체
	 * @param path XPath 경로
	 * @return 속성 맵 리스트
	 */
	public static List getAttributeMapList(Document doc, String path) {
		return getAttributeMapList(doc, path, null);
	}

	/**
	 * 해당 경로의 하위 Element들의 List
	 * 
	 * @param doc            Document 객체
	 * @param path           XPath 경로
	 * @param elmtNameFilter 엘리먼트 이름 필터
	 * @return 속성 맵 리스트
	 */
	public static List getAttributeMapList(Document doc, String path, String elmtNameFilter) {
		// List nodeList = doc.selectNodes(path);
		Node selNode = doc.selectSingleNode(path);
		if (selNode == null) {
			return null;
		}

		List elmtList = ((Element) selNode).elements();
		List mapList = new Vector();

		Element elmt = null;
		Map map = null;

		if (elmtNameFilter != null) {
			for (Iterator itr = snapshotIterator(elmtList); itr.hasNext();) {
				elmt = (Element) itr.next();
				if (elmt != null && elmt.getName().equals(elmtNameFilter)) {
					map = getAttributeMap(elmt);
					mapList.add(map);
				}
			}
		} else {
			for (Iterator itr = snapshotIterator(elmtList); itr.hasNext();) {
				elmt = (Element) itr.next();
				map = getAttributeMap(elmt);
				mapList.add(map);
			}
		}

		return mapList;
	}

	/**
	 * XML을 결과 맵으로 변환한다.
	 * 
	 * @param xml XML 문자열
	 * @return 결과 맵
	 */
	public static HashMap xml2ResultMap(String xml) {
		Document doc = getDocument(xml);

		if (doc == null) {
			return null;
		}

		String rootPath = "/Individual/OutData";

		HashMap resultMap = new HashMap();

		List elmtList = ((Element) doc.selectSingleNode(rootPath)).elements();

		Element elmt = null;
		List groupList = null;
		String elmtName = null;

		for (Iterator itr = snapshotIterator(elmtList); itr.hasNext();) {
			elmt = (Element) itr.next();
			elmtName = elmt.getName();

			groupList = getAttributeMapList(doc, rootPath + "/" + elmtName);

			resultMap.put(elmtName, groupList);
		}

		return resultMap;
	}

	public static Map getFirstChildMap(Document doc, String path) {
		// Node node = (Node)doc.selectNodes(path).get(0);
		Element elmt = (Element) ((Element) doc.selectSingleNode(path)).elements().get(0);

		if (elmt == null) {
			return (Map) (new HashMap());
		} else {
			return getAttributeMap(elmt);
		}

	}

	public static List getNodes(Document doc, String path) {
		return doc.selectNodes(path);
	}

	public static String extractXml(Document doc, String path) {

		if (doc == null) {
			return "";
		}

		doc.setXMLEncoding("euc-kr");
		Node selNode = (Node) doc.selectSingleNode(path);
		return selNode.asXML();
	}

	public static String extractXml(String path, String xml) {

		Document doc = getDocument(xml);

		if (doc == null) {
			return "";
		}

		doc.setXMLEncoding("euc-kr");
		Node selNode = (Node) doc.selectSingleNode(path);
		// selNode.setName("Root");
		return selNode.asXML();
	}

	public static String getHeaderAttribute(Document doc, String attrName) {
		return getAttribute(doc, "/KB-Message/Individual/DataHeader", attrName);
	}

	public static String getAttribute(Document doc, String elmtPath, String attrName) {

		Node node = getNode(doc, elmtPath);

		if (node instanceof Element) {
			Element elmt = (Element) node;
			Attribute attr = elmt.attribute(attrName);
			if (attr != null) {
				return attr.getValue();
			} else {
				return "";
			}
		} else {
			return "";
		}

	}

	public static void setHeaderAttribute(Document doc, String attrName, String attrValue) {
		setAttribute(doc, "/KB-Message/Individual/DataHeader", attrName, attrValue);
	}

	public static void setAttribute(Document doc, String elmtPath, String attrName, String attrValue) {

		Node node = getNode(doc, elmtPath);

		if (node instanceof Element) {
			Element elmt = (Element) node;
			Attribute attr = elmt.attribute(attrName);
			if (attr != null) {
				attr.setValue(attrValue);
			}
		}
	}

	public static Iterator snapshotIterator(Collection collection) {
		return new ArrayList(collection).iterator();
	}

	public static byte[] getFileXml(String fileStr) {
		File f;
		byte readByte[];
		BufferedInputStream bin;
		f = new File(fileStr);
		readByte = new byte[(int) f.length()];
		bin = null;
		try {
			bin = new BufferedInputStream(new FileInputStream(f), readByte.length);
			bin.read(readByte, 0, readByte.length);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return readByte;
	}

}
