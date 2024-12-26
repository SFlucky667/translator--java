package cn.meorain;
import java.util.Scanner;

import cn.meorain.inter.Translator;
import cn.meorain.translators.baidu.BaiduTranslator;
import cn.meorain.utils.Confinguration;
public class Main {
		public static void main(String[] args) {
			// TODO Auto-generated method stub
		       Confinguration.getInstance();
		        Confinguration.load("src/main/java/resources/config/config.properties");

		        Scanner scanner = new Scanner(System.in);
		        Translator translator = new BaiduTranslator(); // 确保此处创建 Translator 对象

		        System.out.println("欢迎使用翻译器！");
		        while (true) {
		            System.out.print("请输入要翻译的文本（输入 'exit' 退出）：");
		            String query = scanner.nextLine();
		            if (query.equalsIgnoreCase("exit")) {
		                break;
		            }

		            System.out.print("请输入源语言（例如 'en'、'zh' 等）：");
		            String fromLang = scanner.nextLine();
		            System.out.print("请输入目标语言（例如 'zh'、'en' 等）：");
		            String toLang = scanner.nextLine();

		            try {

		                String response = translator.translate(query, fromLang, toLang);
		                System.out.println("翻译结果：" + response);
		            } catch (Exception e) {
		                System.err.println("翻译失败：" + e.getMessage());
		            }
		        }

		        scanner.close();
		}

	}



	       
	       

