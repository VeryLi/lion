package com.yeadun.old;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import com.lion.testutil.util.EncryptUtil;




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
	
	//生产
	static final String KEY = "1f46796e-ac1a-4b6a-8dc4-65a8bb006b3a";
//	static final String KEY = "5413133d-87eb-497b-aab0-91ba35f155be";
	
	//测试
//	static final String KEY = "1f46796e-ac1a-4b6a-8dc4-65a8bb006b3a";
//	static final String KEY = "46f21d97-78f0-4ada-a9bf-12a9b06c63ab";
	
	static final String MER = "M100000001";
	
	static List<String> dataList = new ArrayList<String>();
	
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final String MOBILE = "178,134,135,136,137,138,139,150,151,152,154,157,158,159,184,187,188,147,182,183";
	private static final String UNICOM = "130,131,132,155,156,185,186,176,175";
	private static final String TELECOM = "133,153,189,180,177,181,173";
	
	public static void main(String[] args) throws URISyntaxException, IOException, NoSuchAlgorithmException {
//		long a = System.currentTimeMillis();
		Map<String, Object> map = new TreeMap<String, Object>();
//		
//		map.put("certName", "王新峰");
//		map.put("certId", "330127198710013112");
//		map.put("province", "浙江");
//		map.put("city", "杭州");
//		map.put("cardId", "6258091644227410");
		
		
		map.put("certName", "王超");
		map.put("certId", "130225198910308412");
		map.put("mp", "13582531662");
		map.put("cardId", "6217000180008749909");

//		map.put("mp","13587718863");
//		map.put("cycle","24");
//		map.put("platform","0");

//		map.put("certName", "温学平");
//		map.put("certId", "511112199101010914");
//		map.put("mp", "18426496061");
//		map.put("password","246813"); 
		
//		map.put("certName", "陈维力");
//		map.put("certId", "610112199107280512");
//		map.put("mp", "13810796512");
//		map.put("password","369328"); 
		
		
//		map.put("token","e30fb99f30844cf0888de408eca15f34"); 
//		map.put("captcha","396750");
		
////
//		map.put("certId", "330127198710013112");
//		map.put("certName", "王新峰");
//		map.put("queryReason", "99");
		//{"retCode":"0000","retDesc":"查询处理成功","merchPrivate":null,"orderId":"orderId1483606597461","retInfo":{"total_count":2,"pid_overdue":2,"pid_fraud":0,"pid_gfsx":0,"pid_tax":0,"pid_law":0,"pid_gray":0,"mobile_overdue":0,"mobile_fraud":0,"mobile_gray":0}}
		//{"retCode":"0000","retDesc":"查询处理成功","merchPrivate":null,"orderId":"orderId1483606691868","retInfo":{"total_count":3,"pid_overdue":3,"pid_fraud":0,"pid_gfsx":0,"pid_tax":0,"pid_law":0,"pid_gray":0,"mobile_overdue":0,"mobile_fraud":0,"mobile_gray":0}}
//		map.put("certId", "330127198710013112");
//		map.put("certName", "王新峰");
//		map.put("mp", "13486161566");
//		map.put("cardId", "6212261202022828447");
		//4d153acb-c4c5-4f43-9700-4cd5ad7f3e0d   M100000050
		
		
//--------------------王新峰  京东------------------------------------------------			
//		map.put("certId", "330127198710013112");
//		map.put("certName", "王新峰");
//		map.put("mp", "13486161566");
//		map.put("account", "13486161566");
//		map.put("password", "wxfhxy871001");
//		
//		map.put("token", "22b467b2a9ce452a8946aa7a349bcf98");
//		map.put("account", "13486161566");
//		map.put("captcha", "586977");
//--------------------------------------------------------------------		
	
//----------------"温学平" 京东----------------------------------------------------			
//		map.put("certId", "511112199101010914");
//		map.put("certName", "温学平");
//		map.put("mp", "18426496061");
//		map.put("account", "601871350@qq.com");
//		map.put("password", "sin60cos60");
		
		
//		map.put("token", "65951889e17f48f19cd14e59ba9e3112");
//		map.put("account", "601871350@qq.com");
//		map.put("captcha", "768511");
//-------------------------------------------------------------
//----------------"苏少烽" 京东----------------------------------------------------			
//		map.put("certId", "330683199111302812");
//		map.put("certName", "苏少烽");
//		map.put("mp", "18969015741");
//		map.put("account", "18969015741");
//		map.put("password", "ssf1329310021");
		
		
//		map.put("token", "ec7dbe39836344d58a6766194d6c16c0");
//		map.put("account", "ssf1329310021");
//		map.put("captcha", "901898");
//----------------"帅斌" 京东----------------------------------------------------			
//		map.put("certId", "330124199203073511");
//		map.put("certName", "帅斌");
//		map.put("mp", "18868944400");
//		map.put("account", "18868944400");
//		map.put("password", "sb1114036526");
		
		
//		map.put("token", "22b467b2a9ce452a8946aa7a349bcf98");
//		map.put("account", "18868944400");
//		map.put("captcha", "586977");
//-------------------------------------------------------------
//----------------"蔡斌伟" 京东----------------------------------------------------			
//		map.put("certId", "330124199203073511");
//		map.put("certName", "帅斌");
//		map.put("mp", "18868944400");
//		map.put("account", "18274782992");
//		map.put("password", "lsh950107");
		
		
//		map.put("token", "e0f49271d1174cc7bbb2b47fba2819cf");
//		map.put("account", "18868944400");
//		map.put("captcha", "586977");
//-------------------------------------------------------------
//----------------"潘玉龙" 京东----------------------------------------------------			
//		map.put("certId", "511023198901179071");
//		map.put("certName", "潘玉龙");
//		map.put("mp", "13408086138");
//		map.put("account", "13408086138");
//		map.put("password", "Pan@1171227");
		
		
//		map.put("token", "8d6d5a24ff2c46de9d6be878ea71c7f0");
//		map.put("account", "13408086138");
//		map.put("captcha", "639012");
//----------------"陈维利" 京东----------------------------------------------------			
//		map.put("certId", "511023198901179071");
//		map.put("certName", "潘玉龙");
//		map.put("mp", "13408086138");
//		map.put("account", "very_li");
//		map.put("password", "chen199196");
		
		
//		map.put("token", "89b55a9df5a943d5a9da221ee42430f6");
//		map.put("account", "very_li");
//		map.put("captcha", "176334");
//-------------------------------------------------------------
		//---------------------金鑫 京东-----------------------------------------------	
//		map.put("certId", "230321199111205538");
//		map.put("certName", "金鑫");
//		map.put("mp", "13122091857");
//		map.put("account", "479133301@qq.com");
//		map.put("password", "jinxin138048");
		//--------------------------------------------------------------------	
		
//-------------------王新峰 淘宝---------------------------------------------		
//		map.put("certId", "330127198710013112");
//		map.put("certName", "王新峰");
//		map.put("mp", "13486161566");
//		map.put("account", "947146966@qq.com");
//		map.put("password", "wangxinfeng1001");
		
		
//		map.put("token", "fed1f9a72e534e21990a8817bba8edde");
//		map.put("account", "947146966@qq.com");
//--------------王新峰 淘宝--------------------------------------		
//-------------------吴进辉 淘宝---------------------------------------------		
//		map.put("certId", "34290119901219425X");
//		map.put("certName", "吴进辉");
//		map.put("mp", "18042300678");
//		map.put("account", "summerlove66@163.com");
//		map.put("password", "summer,789");
		
		
//		map.put("token", "fed1f9a72e534e21990a8817bba8edde");
//		map.put("account", "summerlove66@163.com");
//---------------------------------------------------		
//-------------------温学平 淘宝---------------------------------------------		
//		map.put("certId", "511112199101010914");
//		map.put("certName", "温学平");
//		map.put("mp", "18426496061");
//		map.put("account", "温学平98");
//		map.put("password", "sin30cos30");
		
		
//		map.put("token", "e2479defb087466b8b45c8cef903f609");
//		map.put("account", "温学平98");
//---------------------------------------------------	
		/**
		 * P100061 场景
		 * P100048 意图
		 * P100049 意图
		 * 
		 * 榕数访问ip受限
		 */
		//6212261202022828447
//		map.put("certName", "王新峰");
//		map.put("certId", "330127198710013112");
//		map.put("certName", "潘忠勇 ");
//		map.put("certId", "452723198410020077");
//		map.put("cardId", "6212261202022828447");
//		map.put("mp", "13086161566");
//		map.put("ocr_mode", "1");
//		map.put("ocr_type", "1");
//		
//		map.put("image_content", "BhmmtRRQgYqnNOoooBC0wvjtRRQBVnutgIH51l3FwZG56UUUhorZzS5oopjP//Z");
		
//		map.put("mp", "18235159661");
//		map.put("cycle","24");
//		map.put("platform","0");
		
//		map.put("image_content_1", "/9j/4AAQSkZJRgABAgAAAQABAAD/");
//		map.put("image_type_1", 101);	//图片类型：101-自动区分网文照
//		map.put("image_content_2", "/9j/4AAQSkZJRgABAQEAYABgAAD/");
//		map.put("image_type_2", 301);	//图片类型：301-类证件照

//		map.put("month","12");
//		map.put("certId","230321199111205538");
//		map.put("certName","金名");


		invoke("P100062",map);
//		M100000001orderId1484734146739P100001{"account":"13486161566","certId":"330127198710013112","certName":"王新峰","mp":"18980765359","password":"112358wangxf"}20170118180906f13ac289-12b6-4a67-a767-1fefcc4e3452
//		M100000001orderId1484734146739P100001{"account":"13486161566","certId":"330127198710013112","certName":"王新峰","mp":"18980765359","password":"112358wangxf"}20170118180906f13ac289-12b6-4a67-a767-1fefcc4e3452
		//System.out.println(System.currentTimeMillis()-a);
		//test2();
//		test1();
		
//		M100000051orderId1487215557128P100042{"certId":"330127198710013112","certName":"王新峰","queryReason":"99"}2017021611255746f21d97-78f0-4ada-a9bf-12a9b06c63ab
//		M100000051orderId1487215557128P100042{"certId":"330127198710013112","certName":"王新峰","queryReason":"99"}2017021611255746f21d97-78f0-4ada-a9bf-12a9b06c63ab

//		
//		//D:\\wxf\\文档\\温学平\\手机三要素验证\\测试数据.xlsx"
		//"D:\wxf\文档\温学平\驾驶证验证\羿盾20170117.xlsx"
//		String basepath = "D:\\wxf\\文档\\小球\\20170301\\";
////		//测试数据 - 副本
//		saveResult(basepath+"list2.xlsx", basepath, new String[]{"queryData"}, "P100058", false);
		
	}

	public static String invoke(String productId, Map<String, Object> paramMap) throws URISyntaxException, IOException, NoSuchAlgorithmException{
		Map<String, Object> param = new TreeMap<String, Object>();
		param.put("merchCode", MER);
		String orderId = "orderId"+System.currentTimeMillis();
		param.put("orderId", orderId);
		param.put("jsonStr", JSONObject.toJSONString(paramMap));
		String tranTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		param.put("tranTime", tranTime);
		param.put("productCode", productId);
		String json = JSONObject.toJSONString(paramMap);
		
		System.out.println(MER+orderId+productId+json+tranTime+KEY);
		String sign = null;
		System.out.println(MER+orderId+productId+json+tranTime+KEY);
		sign = EncryptUtil.MD5EncryptReturnLowerCase(MER+orderId+productId+json+tranTime+KEY);
		param.put("sign", sign);
		Set<Entry<String, Object>> entry = param.entrySet();
		Iterator<Entry<String, Object>> it = entry.iterator();
		StringBuffer kvStr = new StringBuffer();
		while(it.hasNext()){
			Entry<String, Object> en = it.next();
			kvStr.append(en.getKey()+"="+en.getValue().toString()+"&");
		}
		String parameStr = kvStr.substring(0, kvStr.length()-1);
 		System.out.println(parameStr);
		URL url = new URL(URL);
		URLConnection conn = url.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setConnectTimeout(10000);
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		OutputStream os = conn.getOutputStream();
		os.write(parameStr.toString().getBytes());
		os.flush();
		InputStream in = conn.getInputStream();
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[1024];
		int a = 0;
		while((a = in.read(b))>0){
			sb.append(new String(b,0,a));
		}
		in.close();
		os.close();
		System.out.println(sb.toString());
		return sb.toString();
	}
}