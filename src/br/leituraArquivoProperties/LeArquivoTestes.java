package br.leituraArquivoProperties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class LeArquivoTestes {

	protected static Properties properties = new Properties();
	private static HashMap<String, String> atributos = new HashMap<String, String>();

	public static void lePropriedades(String propFileName) {
		InputStream inputStream = LeArquivoTestes.class.getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null) {
			try {
				properties.load(inputStream);
				Enumeration<?> keys = properties.keys();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					String value = (String) properties.get(key);
					atributos.put(StringUtils.stripAccents(key.toLowerCase()), value);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(String key) {
		return atributos.get(StringUtils.stripAccents(key.toLowerCase()));
	}

	public static void main(String[] args) {
		lePropriedades("teste.properties");
		if (!atributos.isEmpty()) {
			System.out.println(getProperty("chave 10"));
		} else {
			System.out.println("nao contem");
		}
	}

}
