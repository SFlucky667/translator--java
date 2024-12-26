package cn.meorain.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
	
	public static String Get(String urlStr) throws Exception{
		StringBuilder response =new StringBuilder();
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		int responseCode = connection.getResponseCode();
		if(responseCode == HttpURLConnection.HTTP_OK) {
			try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
				String inputLine;
				while((inputLine = in.readLine())!=null) {
					response.append(inputLine);
				}
			}
		}
			else {
				throw new RuntimeException("Failed : Http error code : " + responseCode);
			}
		return response.toString();
	}

}
