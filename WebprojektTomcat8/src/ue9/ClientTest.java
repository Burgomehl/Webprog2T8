package ue9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ClientTest {
	public static void main(String[] args) {
		try {
			new ClientTest().put();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void put() throws MalformedURLException, IOException{
		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8080/WebprojektTomcat8/rest/Test")
				.openConnection();
		con.setRequestProperty("Content-Type", "text/plain");
		con.setRequestMethod("DELETE");
		con.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		out.write("Pu");

		out.close();

		System.out.println(con.getResponseCode()+" ");

		con.disconnect();
	}
}
