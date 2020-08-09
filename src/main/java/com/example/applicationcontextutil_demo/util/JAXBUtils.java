package com.example.applicationcontextutil_demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.applicationcontextutil_demo.Dto.CommonResponseRoot;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Slf4j
public class JAXBUtils {
	public static String toXml(Class transDataClass, Class bodyClass, Object transData) throws JAXBException {
		return toXml(transDataClass, bodyClass, transData, "gbk");
	}

	public static String toXml(Class transDataClass, Class bodyClass, Object transData, String encoding) throws JAXBException {
		JAXBContext jaxbContext;
		if (bodyClass == null) {
			jaxbContext = JAXBContext.newInstance(transDataClass);
		} else {
			jaxbContext = JAXBContext.newInstance(transDataClass, bodyClass);
		}
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		Writer writer = new StringWriter();

		log.info("request transData is : {}", JSONObject.toJSONString(transData));
		marshaller.marshal(transData, writer);

		String xml = writer.toString();
		if (!xml.contains("</MAIN>")) {
			return xml.replaceAll("<MAIN.*?>", "<MAIN></MAIN>");
		} else {
			return xml.replaceAll("<MAIN.*?>", "<MAIN>");
		}
	}

	public static <T> CommonResponseRoot<T> toJavaObject(String xml, Class<T> bodyType) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(CommonResponseRoot.class, bodyType);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		String simpleName = bodyType.getSimpleName();
		simpleName = formatName(simpleName);

		xml = xml.replace("<MAIN/>","<MAIN></MAIN>");
		xml = xml.replaceAll("<MAIN>", "<MAIN xsi:type=\"" + simpleName + "\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		Reader reader = new StringReader(xml);

		return (CommonResponseRoot<T>) unmarshaller.unmarshal(reader);
	}

	public static Object getJavaObject(String xml, Class bodyType) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(bodyType);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		String simpleName = bodyType.getSimpleName();
		simpleName = formatName(simpleName);

		xml = xml.replace("<MAIN/>","<MAIN></MAIN>");
		xml = xml.replaceAll("<MAIN>", "<MAIN xsi:type=\"" + simpleName + "\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		Reader reader = new StringReader(xml);

		return unmarshaller.unmarshal(reader);
	}

	/**
	 * no body
	 *
	 * @param xml
	 * @return
	 * @throws JAXBException
	 */
	public static CommonResponseRoot toJavaObject(String xml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(CommonResponseRoot.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Reader reader = new StringReader(xml);
		CommonResponseRoot unmarshal = (CommonResponseRoot) unmarshaller.unmarshal(reader);
		unmarshal.setBody(null);

		return unmarshal;
	}

	private static String formatName(String name) {

		char[] methodName = name.toCharArray();
		for (int i = 0; i < methodName.length; i++) {
			if (i == 0) {
				if (97 <= methodName[i] && methodName[i] <= 122) {
					return name;
				} else {
					methodName[i] += 32;
				}

			} else if (i == methodName.length - 1) {
				if (97 <= methodName[i] && methodName[i] <= 122) {
					return String.valueOf(methodName);
				} else {
					methodName[i] += 32;
				}

			} else {
				if (97 <= methodName[i] && methodName[i] <= 122) {
					return String.valueOf(methodName);
				} else if (97 <= methodName[i + 1] && methodName[i + 1] <= 122) {
					return String.valueOf(methodName);
				} else {
					methodName[i] += 32;
				}
			}
		}
		return String.valueOf(methodName);
	}


	/**
	 * XML 转对象
	 *
	 * @param metadata
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static <T> T parse(String metadata, Class<T> clazz) {
		T module = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller um = jaxbContext.createUnmarshaller();
			module = (T) um.unmarshal(new ByteArrayInputStream(metadata.getBytes()));
		} catch (JAXBException e) {
//            log.warn("JAXB castor failed to convert the metadata to module instance by {}", e.getMessage());
		}
		return module;
	}

	/**
	 * xml转对象
	 */
	public static <T> T fromXML(Class<T> clazz, String xml) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
//            log.error("对象转换失败:{}", e);
		}

		return null;
	}

	public static String doc2String(Document document) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			OutputFormat format = new OutputFormat("  ", true, "GBK");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			return out.toString("GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
