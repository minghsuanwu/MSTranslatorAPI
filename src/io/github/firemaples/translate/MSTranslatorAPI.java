package io.github.firemaples.translate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import io.github.firemaples.language.Language;

public class MSTranslatorAPI {

	private static String readConfig() {
		StringBuilder sb = new StringBuilder();
		String filePath = "config.txt";
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "UTF-8")); // read document format in "UTF-8"
			String str = null;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception e) {
			System.err.println("Please create your config.txt with your server key in JSON format:");
			System.err.println("{\"serverkey\": \"your server key\"}");
//			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	class ConfigInfo {
		public String serverkey = "";
	}
	public static String getServerKey() {
		String JSON = readConfig();
		Gson gson = new Gson();		
		ConfigInfo ci = gson.fromJson(JSON, ConfigInfo.class);
		
		return ci.serverkey;
	}
	public static void main(String[] args) {
		// Translate an english string to spanish
		String englishString = "i love you";
		String translatedText = "";
	    // Set your Azure Portal Subscription Key - See https://www.microsoft.com/cognitive-services/en-us/translator-api/documentation/TranslatorInfo/overview
	    String serverkey = getServerKey();
		Translate.setSubscriptionKey(serverkey);
	    
	    try {
			translatedText = Translate.execute(englishString, Language.ENGLISH, Language.CHINESE_TRADITIONAL);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Original english phrase: " + englishString);
		System.out.println("Translated spanish phrase: " + translatedText);
	}

}
