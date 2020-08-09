package com.example.applicationcontextutil_demo.commonMapper.impl;

import com.example.applicationcontextutil_demo.commonMapper.CoreClient;
import com.example.applicationcontextutil_demo.util.HttpClientUtil;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;

/**
 * Created by sun on 2020/1/7.
 */
@Component("coreClient")
public class CoreClientImpl implements CoreClient {

    /**
     * @Author LJH
     * @Description //发送请求报文
     * @Date 17:50 2020/1/7
     * @Param [request, endpoint]
     * @return java.lang.String
     */
    @Override public String callLis(String request, String endpoint) {
        //用于请求带有wsdl
        try {
            if (endpoint.endsWith("wsdl")){
                //endsWith() 方法用于测试字符串是否以指定的后缀结束。
                String resultXml = call(request, endpoint);
                return resultXml;
            } else {
                String resultXml = HttpClientUtil.postMsg((String) request, endpoint);
                return resultXml;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private String call2(String requestXml, String endpoint){
        try {
            // 使用RPC方式调用WebService
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();

            // 指定调用WebService的URLSS
            // 确定目标服务地址
            options.setTo(new EndpointReference(endpoint));

//            options.setProperty(HTTPConstants.CHUNKED, "false");// 把chunk关掉后，会自动加上Content-Length
//            //解决高并发链接超时问题
//            options.setManageSession(true);
//            options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT,true);


            //设置响应超时，默认5s
            options.setProperty(HTTPConstants.SO_TIMEOUT, 6000);
            //设置连接超时，默认5s
            options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, 6000);
            //超时时间默认
            options.setTimeOutInMilliSeconds(6000L);

            // 指定入参方法的参数值
            Object[] args = new Object[]{ requestXml };
            // 指定sayHelloToPerson方法返回值的数据类型的Class对象
            Class[] classes = new Class[]{String[].class};
            // 指定要调用的sayHelloToPerson方法及WSDL文件的命名空间
            QName name = new QName("http://services.webservice.sinosoft.com", "service");
            // 调用方法并输出该方法的返回值
            String[] responseXml = (String[]) serviceClient.invokeBlocking(name, args, classes)[0];
            serviceClient.cleanupTransport();

            if (responseXml == null) {
                return null;
            }

            return responseXml[0];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    private String call(String requestXml,String endpoint) {
        try {
            // 使用RPC方式调用WebService
            RPCServiceClient serviceClient = new RPCServiceClient();
            Options options = serviceClient.getOptions();

            // 指定调用WebService的URLSS
            options.setTo(new EndpointReference(endpoint));
            options.setProperty(HTTPConstants.SO_TIMEOUT, 6000);
            options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, 6000);
            options.setTimeOutInMilliSeconds(6000L);

            // 指定sayHelloToPerson方法的参数值
            Object[] args = new Object[]{ requestXml };
            // 指定sayHelloToPerson方法返回值的数据类型的Class对象
            Class[] classes = new Class[]{String[].class};
            // 指定要调用的sayHelloToPerson方法及WSDL文件的命名空间
            QName name = new QName("http://services.webservice.sinosoft.com", "service");
            // 调用sayHelloToPerson方法并输出该方法的返回值
            String[] responseXml = (String[]) serviceClient.invokeBlocking(name, args, classes)[0];
            serviceClient.cleanupTransport();

            if (responseXml == null) {
               // log.info("调用无响应！");
                return null;
            }

            return responseXml[0];

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
