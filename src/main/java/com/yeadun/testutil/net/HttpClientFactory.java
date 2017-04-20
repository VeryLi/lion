package com.yeadun.testutil.net;

import org.apache.http.Header;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * HttpClientFactory可以实例化一组属性不同的HttpClientBuilder对象，
 * 并可以使用不同的HttpClientBuilder构建出相应属性_HttpClient对象，
 * _HttpClient是HttpClientFactory的内部类
 * _HttpClient对象封装了ClosableHttpClient对象，简化HttpClient操作。
 */
public class HttpClientFactory {

    public static String DEFAULT_BUILDER = "DefaultBuilder";
    public static String HTTP_HEADER = "HTTP_HEADER";
    public static String HTTP_BODY = "HTTP_BODY";

    private static final Logger LOG = LoggerFactory.getLogger(HttpClientFactory.class);
    private static final HashMap<String, HttpClientBuilder> FACTORY_BUILDERS
            = new HashMap<>();
    private static HttpClientFactory factory;

    public synchronized static HttpClientFactory getFactory() {
        if (HttpClientFactory.factory == null) {
            factory = new HttpClientFactory();
        }
        return factory;
    }

    private HttpClientFactory() {
        FACTORY_BUILDERS.put(DEFAULT_BUILDER, HttpClientBuilder.create());
        LOG.info("HttpClientFactory初始化成功。");
    }

    public void createBuilder(String builderName) {
        FACTORY_BUILDERS.put(builderName, HttpClientBuilder.create());
    }

    public HttpClientBuilder getBuilder(String builderName, boolean ifCreate) {
        boolean ifContain = FACTORY_BUILDERS.containsKey(builderName);
        HttpClientBuilder builder;
        if ((!ifContain) && ifCreate) {
            createBuilder(builderName);
            LOG.warn("需要的\"{}\" Builder 不存在，将创建该Builder(\"{}\")。", builderName, builderName);
            builder = FACTORY_BUILDERS.get(builderName);
        } else if (!ifContain) {
            LOG.warn("需要的\"{}\" Builder 不存在，将使用默认HttpClientBuidler(\"{}\")", builderName, DEFAULT_BUILDER);
            builder = FACTORY_BUILDERS.get(DEFAULT_BUILDER);
        } else {
            builder = FACTORY_BUILDERS.get(builderName);
        }
        return builder;
    }

    public _HttpClient createHttpClient(String builderName, String uriStr) {
        return new _HttpClient(builderName, uriStr);
    }

    /**
     * 将InputStream对象转为String对象并返回。
     */
    private String convertToString(InputStream input) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        }
        StringBuilder strBuilder = new StringBuilder();
        String tmp;
        try {
            if (reader == null) {
                return "";
            }
            while ((tmp = reader.readLine()) != null) {
                strBuilder.append(tmp);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return strBuilder.toString();
    }

    /**
     * 工厂种指定的Buidler，若为指定则用"Default" Builder建出的Http客户端
     */
    public class _HttpClient {

        private String builderName = DEFAULT_BUILDER;
        private ArrayList<BasicNameValuePair> parameters = new ArrayList<>();
        private ArrayList<Header> headerBuilder = new ArrayList<>();
        private Header[] headers;
        private URI uri;
        private HttpGet get;
        private HttpPost post;
        private String uriStr;

        /**
         * 构造函数：
         *
         * @param builderName 指定使用哪个builder进行构建CloseableHttpClient对象，不指定则为Default。
         * @param uriStr      请求地址
         */
        private _HttpClient(String builderName, String uriStr) {
            if (builderName != null && "".equalsIgnoreCase(builderName)) {
                this.builderName = builderName;
            }
            try {
                this.uri = new URI(uriStr);
            } catch (URISyntaxException e) {
                LOG.error(e.getMessage(), e);
            }
            this.get = new HttpGet(uri);
            this.post = new HttpPost(uri);
            this.uriStr = uriStr;
        }

        public HashMap<String, Object> sendGETReturnStringBody() {
            return sendRequest(HttpMethod.GET, HttpDataType.STRING);
        }

        public HashMap<String, Object> sendGETReturnStreamBody() {
            return sendRequest(HttpMethod.GET, HttpDataType.STREAM);
        }

        public HashMap<String, Object> sendPOSTReturnStringBody() {
            return sendRequest(HttpMethod.POST, HttpDataType.STRING);
        }

        public HashMap<String, Object> sendPOSTReturnStreamBody() {
            return sendRequest(HttpMethod.POST, HttpDataType.STREAM);
        }

        public _HttpClient addPostParameter(BasicNameValuePair pair) {
            this.parameters.add(pair);
            return this;
        }

        public _HttpClient setPostParameters(ArrayList<BasicNameValuePair> parameters) {
            this.parameters = parameters;
            return this;
        }

        public _HttpClient addHeader(String key, String value) {
            this.headerBuilder.add(new BasicHeader(key, value));
            return this;
        }

        public _HttpClient addHeader(Header header) {
            this.headerBuilder.add(header);
            return this;
        }

        public _HttpClient setHeaders(ArrayList<Header> headers) {
            this.headerBuilder = headers;
            return this;
        }

        public String getUriStr() {
            return this.uriStr;
        }

        /**
         * 发送Http请求
         *
         * @param dataType Response响应中Body的格式，String类型或者InputStream类型
         * @return Response响应，有两个key，一个是"header"，一个是"body"
         */
        private HashMap<String, Object> sendRequest(HttpMethod method, HttpDataType dataType) {
            // 发送Get请求，并返回结果响应
            if (method == HttpMethod.GET) {
                return execute(method, dataType);
            }
            // 发送Post请求，并返回结果响应
            else if (method == HttpMethod.POST) {
                if (parameters == null) {    // 添加POST请求参数
                    return execute(method, dataType);
                } else {
                    try {
                        post.setEntity(new UrlEncodedFormEntity(this.parameters));
                    } catch (UnsupportedEncodingException e) {
                        LOG.error(e.getMessage(), e);
                    }
                    return execute(method, dataType);
                }
            } else {
                LOG.warn("未知请求类型，返回值为空。");
                return null;
            }
        }

        /**
         * 执行Http请求操作
         *
         * @param method   Get 或 Post请求类型
         * @param dataType Response响应中Body的格式，String类型或者InputStream类型
         * @return Response响应，有两个key，一个是"header"，一个是"body"Ò
         */
        private HashMap<String, Object> execute(HttpMethod method, HttpDataType dataType) {

            // 开始执行请求。
            // 创建Client 和 响应对象。
            CloseableHttpResponse response;
            CloseableHttpClient client =
                    HttpClientFactory.getFactory()
                            .getBuilder(this.builderName, false)
                            .build();
            try {
                // Get 请求
                if (HttpMethod.GET == method) {
                    // 添加请求头
                    if (this.headerBuilder != null) {
                        this.headers = new Header[this.headerBuilder.size()];
                        this.headerBuilder.toArray(this.headers);
                        this.get.setHeaders(this.headers);
                    }
                    LOG.debug(getRequestInfo());
                    response = client.execute(this.get);
                }
                // Post 请求
                else {
                    // 添加请求头
                    if (this.headerBuilder != null) {
                        this.headers = new Header[this.headerBuilder.size()];
                        this.headerBuilder.toArray(this.headers);
                        this.post.setHeaders(this.headers);
                    }
                    LOG.debug(getRequestInfo());
                    response = client.execute(this.post);
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
                LOG.error("HTTP请求发生错误，响应返回空值。");
                try {
                    client.close();
                } catch (IOException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
                return null;
            }

            HashMap<String, Object> result = new HashMap<>();
            // 获取返回Header值
            Header[] headers = response.getAllHeaders();
            HashMap<String, String> allHeader = new HashMap<>();
            for (Header header : headers) {
                allHeader.put(header.getName(), header.getValue());
            }

            // 获取返回Body值
            // -- 获取String类型
            if (dataType == HttpDataType.STRING) {
                String body = null;
                try {
                    body = convertToString(response.getEntity().getContent());
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
                result.put(HTTP_HEADER, allHeader);
                result.put(HTTP_BODY, body);
            }
            // -- 获取InputStream类型
            if (dataType == HttpDataType.STREAM) {
                InputStream body = null;
                try {
                    body = response.getEntity().getContent();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
                result.put(HTTP_HEADER, allHeader);
                result.put(HTTP_BODY, body);

            }

            try {
                response.close();
                client.close();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
            return result;
        }

        private String getRequestInfo() {
            StringBuilder info = new StringBuilder();
            info.append("\n");
            info.append("BuilderName: ").append(this.builderName).append("\n");
            info.append("Get URL: ").append(this.get.getURI()).append("\n");
            info.append("Get Header: {").append("\n");
            for (Header header : this.get.getAllHeaders()) {
                info.append("  ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
            }
            info.append("}").append("\n");
            info.append("Post URL: ").append(this.post.getURI()).append("\n");
            info.append("Post Header: {").append("\n");
            for (Header header : this.post.getAllHeaders()) {
                info.append("  ").append(header.getName()).append(": ").append(header.getValue()).append("\n");
            }
            info.append("}").append("\n");
            return info.toString();
        }
    }
}
