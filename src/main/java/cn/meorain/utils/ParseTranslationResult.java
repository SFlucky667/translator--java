package cn.meorain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParseTranslationResult {

	public static String parse(String response) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode root = mapper.readTree(response);
			JsonNode transResult=root.path("trans_result").get(0);
			return transResult.path("dst").asText();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  "解析失败";
		}
		
		
		
	}
}
