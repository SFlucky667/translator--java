package cn.meorain.translators.baidu;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.meorain.inter.Translator;
import cn.meorain.translators.baidu.models.BaiduTranslationResult;
import cn.meorain.utils.Confinguration;
import cn.meorain.utils.HttpUtils;
import cn.meorain.utils.UrlBuilder;

public class BaiduTranslator implements Translator {
	private static Confinguration confinguration = Confinguration.getInstance();
    private static final String API_URL = confinguration.getBaidu_api_url();
    private static final String APP_ID = confinguration.getBaidu_appid();
    @SuppressWarnings("unused")
	private static final String SECURITY_KEY = confinguration.getBaidu_security_key();
	
	@Override
	public String translate(String query, String from, String to) {
		
		String salt =String.valueOf(System.currentTimeMillis());
		String sign=generateSign(query,salt);

		String response="";
		String urlStr;
		try {
			urlStr = new UrlBuilder(API_URL)
					.addParam("q",query)
					.addParam("from",from)
					.addParam("to",to)
					.addParam("appid",APP_ID)
					.addParam("salt",salt)
					.addParam("sign",sign)
					.build();
			
				response=HttpUtils.Get(urlStr);
		}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		@SuppressWarnings("rawtypes")
		BaiduTranslationResult result = parse(response);
		return result.toString();				
	}
private String generateSign(String query,String salt) {
	String sign=APP_ID+ query + salt +SECURITY_KEY;
	
	try {
		MessageDigest md = MessageDigest.getInstance("MD5");//生成MD5哈希值
		byte[]  messageDigest = md.digest(sign.getBytes("UTF-8"));//使用UTF-8编码
		StringBuilder hexString=new StringBuilder();
		for(byte b : messageDigest) {
			 String hex=Integer.toHexString(0xff & b);
			 if(hex.length()==1)
				 hexString.append('0');
			 hexString.append(hex);
		}
		return hexString.toString();
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
	}
		
//解析API响应
@SuppressWarnings("rawtypes")
private BaiduTranslationResult parse(String response) {
	ObjectMapper objectMapper = new ObjectMapper();
	BaiduTranslationResult translationResult;
	try {
		translationResult = objectMapper.readValue(response,BaiduTranslationResult.class);
		return translationResult;
	} catch (Exception  e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	return null;
	}
	
}
		
		
		
		
		
		
		
	


