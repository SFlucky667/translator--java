package cn.meorain.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

   public class UrlBuilder {
   private final String baseUrl;
   private final Map<String ,String>queryParams=new LinkedHashMap<>();
  
   public  UrlBuilder(String baseUrl) {
	   this.baseUrl=baseUrl;
   }
   public UrlBuilder addParam(String key,String value) {//将键值对形式的查询参数添加到 queryParams 映射中
	   queryParams.put(key, value);
	   return this;//返回当前对象实例，支持链式调用
   }
   
   public String build() throws UnsupportedEncodingException{
	   StringBuilder url=new StringBuilder(baseUrl);
	   if(!queryParams.isEmpty()) {
		   url.append("?");
		   for(Map.Entry<String, String>entry:queryParams.entrySet()) {
			  url.append(URLEncoder.encode(entry.getKey(), "UTF-8"))
			  .append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8"))
			  .append("&");
		   }
		   url.setLength(url.length()-1);
	   }
	   return url.toString();
   }
}
