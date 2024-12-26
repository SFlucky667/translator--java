package cn.meorain.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Confinguration {
  static Confinguration instance;
  String baidu_api_url;
  String baidu_appid;
  String baidu_security_key;
  
  public Confinguration() {}
  public static Confinguration getInstance() {
	  if(instance==null) {
		  synchronized(Confinguration.class) {
			  if(instance==null) {
				  instance=new Confinguration();
			  }
		  }
	  }
	  return instance;
  }
  public static void load(String path) {
	  Properties properties =new Properties();
	  InputStream input;
	try {
		input = new FileInputStream(path);
		  properties.load(input);
		  instance.baidu_api_url=properties.getProperty("baidu_api_url");
		  instance.baidu_appid=properties.getProperty("baidu_appid");
		  instance.baidu_security_key=properties.getProperty("baidu_security_key");
		  input.close();
		  System.out.println("读取配置文件成功！");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
  }
  public String toString() {
	  return "Configuration [baidu_api_url="+baidu_api_url+",baidu_appid="+baidu_appid+",baidu_securyity_key="+baidu_security_key+"]";    
  }
  public String getBaidu_api_url() {
	return baidu_api_url;
  }
  public String getBaidu_appid() {
	return baidu_appid;
 }
  public String getBaidu_security_key() {
	return baidu_security_key;
}
	
	
	
}
