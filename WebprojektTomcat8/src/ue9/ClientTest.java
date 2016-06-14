package ue9;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class ClientTest {

	public static void main(String[] args) {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client c = Client.create(clientConfig);

		delete(c);
	}

	public static void delete(Client c) {
		WebResource resource = c.resource("http://localhost:8080/WebprojektTomcat8/rest/Test/Delete");
		resource.path("/Pu").delete();
	}


}
