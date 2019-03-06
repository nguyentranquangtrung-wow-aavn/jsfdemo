package com.axonactive.jsfdemo.employee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsumeWS {

	public static void main(String[] args) {
		
		try {
			URL url = new URL("http://localhost:8080/training/api/employee");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String str;
			StringBuffer stringBuffer = new StringBuffer();
			while ((str = bufferReader.readLine()) != null) {
				stringBuffer.append(str);
				stringBuffer.append("\n");
				System.out.println(stringBuffer.toString());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
