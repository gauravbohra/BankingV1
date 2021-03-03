package com.banking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	public static Properties properties;

	static {
		File file = new File("./Config/config.properties");
		properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(file);
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getChromeDriverPath() {
		return ReadConfig.getPropertyValue("chromeDriverPath");
	}

	public static String getGeckoDriverPath() {
		return ReadConfig.getPropertyValue("geckoDriverPath");
	}

	public static String getIEDriverPath() {
		return ReadConfig.getPropertyValue("ieDriverPath");
	}

	public static String getPropertyValue(String propertyKey) {
		return properties.getProperty(propertyKey);
	}

}
