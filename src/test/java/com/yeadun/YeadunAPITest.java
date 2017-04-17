package com.yeadun;

import com.alibaba.fastjson.JSONObject;
import com.lion.testutil.Config;
import com.lion.testutil.util.EncryptUtil;
import com.lion.testutil.util.net.HttpClientFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

//import java.nio.file.Path;
//import java.nio.file.Paths;


public class YeadunAPITest {

    static final String URL = "http://api.yeadun.com/index";
//	static final String URL = "http://115.236.16.10/api/index";
//	static final String URL = "http://120.26.37.123/yeadunapi/index";
//	static final String URL = "http://120.26.37.123/yeadunapi/crawler/collectMobile";
//	static final String URL = "http://127.0.0.1:8080/yeadunapi/crawler/login/jingdong";
//	static final String URL = "http://139.224.208.92:8081/crawler/collectMobile";
//	static final String URL = "http://139.224.208.92:8081/index";
//	static final String URL = "http://127.0.0.1:8080/yeadunapi/index";
//	static final String URL = "http://192.168.100.214:8082/yeadun-api/crawler/collectMobile";
//	static final String URL = "http://192.168.100.214:8082/yeadun-api/index";

    //??
    static final String KEY = "1f46796e-ac1a-4b6a-8dc4-65a8bb006b3a";
//	static final String KEY = "5413133d-87eb-497b-aab0-91ba35f155be";

    //??
//	static final String KEY = "1f46796e-ac1a-4b6a-8dc4-65a8bb006b3a";
//	static final String KEY = "46f21d97-78f0-4ada-a9bf-12a9b06c63ab";

    static final String MER = "M100000001";

    static List<String> dataList = new ArrayList<String>();

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final String MOBILE = "178,134,135,136,137,138,139,150,151,152,154,157,158,159,184,187,188,147,182,183";
    private static final String UNICOM = "130,131,132,155,156,185,186,176,175";
    private static final String TELECOM = "133,153,189,180,177,181,173";

    public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException, InvalidFormatException {

        String productCode = "P100062";

        Map<String, Object> map = new TreeMap<>();
        map.put("cardId", "6258091644227410");
        map.put("certName", "王超");
        map.put("certId", "130225198910308412");

        HashMap<String, Object> param = new HashMap<>();
        param.put("merchCode", MER);

        String orderId = "orderId" + System.currentTimeMillis();
        param.put("orderId", orderId);

        String json = JSONObject.toJSONString(map);
        param.put("jsonStr", json);

        String tranTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        param.put("tranTime", tranTime);

        param.put("productCode", productCode);

        String data = MER + orderId + productCode + json + tranTime + KEY;
        System.out.println("加密前：" + data);
        String sign = EncryptUtil.MD5EncryptReturnLowerCase(data);
        System.out.println("加密后：" + sign);
        param.put("sign", sign);

        HttpClientFactory factory = HttpClientFactory.getFactory();
        factory.createBuilder("MyBuilder");
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(Config.CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000)
                .build();
        factory.getBuilder("MyBuilder").setDefaultRequestConfig(config);

        HttpClientFactory._HttpClient client = factory.createHttpClient("MyBuilder",URL);
//        client.addHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded"));
        client.addHeader(new BasicHeader("charset","UTF-8"));
        client.addHeader(new BasicHeader("Accept-Charset", "UTF-8"));

        ArrayList<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tranTime", tranTime));
        params.add(new BasicNameValuePair("productCode", productCode));
        params.add(new BasicNameValuePair("merchCode", MER));
        params.add(new BasicNameValuePair("orderId", orderId));
        params.add(new BasicNameValuePair("jsonStr", json));
        params.add(new BasicNameValuePair("sign", sign));
        client.setPostParameters(params);

        HashMap<String, Object> result = client.sendPOSTReturnStringBody();
        System.out.println(result.get(HttpClientFactory.HTTP_HEADER));
        System.out.println(result.get(HttpClientFactory.HTTP_BODY));
    }
}