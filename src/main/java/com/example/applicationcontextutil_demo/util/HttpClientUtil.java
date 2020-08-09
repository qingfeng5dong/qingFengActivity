/**
 * 
 */
package com.example.applicationcontextutil_demo.util;
/**
 * 
 */
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

/**
 * @author wusr 2015-8-6
 * @version 1.0
 */
@Slf4j
public class HttpClientUtil {
	/**
	 * http通信客户端，直接发送报文
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String postMsg(String content, String endpoint) throws Exception {
		// DEV环境: http://10.1.16.110:8080/tip/httpfep/website.action
		// DAT环境: http://10.50.0.27:7001/tip/httpfep/website.action
		// UAT环境: http://10.50.0.136:7001/tip/httpfep/website.action
		// PRD环境: http://10.50.0.236:7001/tip/httpfep/website.action
//		PostMethod post = new PostMethod("http://192.168.11.119:8080/lis/services/EcommerceService?wsdl");
		PostMethod post = new PostMethod(endpoint);
//		log.info("调用地址为：{}", endpoint);
//		log.info("请求报文：{}", content);
		RequestEntity requestEntity = new StringRequestEntity(content, "application/xml", "GBK");
		post.setRequestEntity(requestEntity);
		HttpClient httpClient = new HttpClient();
		int statusCode = httpClient.executeMethod(post);
		if (HttpStatus.SC_OK == statusCode) {
			// 备份响应报文
			return post.getResponseBodyAsString();
		} else {
			throw new Exception("通信异常，返回码为：" + statusCode);
		}
	}
}
