package cn.meorain.translators.baidu.models;

import java.util.List;

public class BaiduTranslationResult<TransResult> {
      private String from;
      private String to;
      private List<TransResult>trans_result;
	
	
	public BaiduTranslationResult() {
		
		@SuppressWarnings({ "unused", "hiding" })
		class TransResult{
			private String src;
			private String dst;
			
			public TransResult() {}
			public TransResult(String src,String dst) {
				this.src=src;
				this.dst=dst;
			}
			public String getSrc() {
				return src;
			}
		
			public String getDst() {
				return dst;
			}
			public String toString() {
				return "TransResult [src="+src+",dst="+dst+"]";
			}
		
		}
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public List<TransResult> getTrans_result() {
		return trans_result;
	}


	public void setTran_result(List<TransResult> trans_result) {
		this.trans_result = trans_result;
	}
	
	public String toString() {
		return "百度翻译结果[源语言="+from+",翻译语言="+to+",翻译结果="+trans_result+"]";
	}
	
}
