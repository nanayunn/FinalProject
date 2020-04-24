package server;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class clientgetData {
	
	static URL url = null;
	
	public static void sendPostJson(String ip,String id) {
		System.out.println("ip="+ip+" id="+id);
		
		try {
			String data = "http://70.12.231.175:8088/finalpj/clientgetdata?ip="+ip+"&id="+id;
			url = new URL(data);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.getInputStream();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}
	public static void disconnectJson(String ip) {
		System.out.println("ip="+ip);
		
		try {
			String data = "http://70.12.231.175:8088/finalpj/disconnect?ip="+ip;
			url = new URL(data);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.getInputStream();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}
	
}