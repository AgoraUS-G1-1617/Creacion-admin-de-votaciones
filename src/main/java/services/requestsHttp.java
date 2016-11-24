package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class requestsHttp {

	private String USER_AGENT = "Mozilla/5.0";

	@SuppressWarnings("deprecation")
	public Integer generaPeticionCenso(int i, Date date, Date date2, String string, String string2) throws IOException {
		String SERVLETURL = "http://localhost:80/ADMCensus/census/create.do?idVotacion=" + Integer.toString(i)
				+ "&fechaInicio=" + date.getDate() + "/" + date.getMonth() + "/" + (date.getYear() + 1900)
				+ "&fechaFin=" + date2.getDate() + "/" + date2.getMonth() + "/" + (date2.getYear() + 1900)
				+ "&tituloVotacion=" + string + "&tipoVotacion=" + string2;
		System.out.println(SERVLETURL);
		Integer res = 1;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		System.out.println("Creo el httpclient");
		HttpGet httpGet = new HttpGet(SERVLETURL);
		System.out.println("Creo el httpclient");
		httpGet.addHeader("User-Agent", USER_AGENT);
		System.out.println("Voy a ejecutar");
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println("Ejecutado");
		System.out.println("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine.toString());
		}
		reader.close();

		// print result
		System.out.println("Respuesta: " + response.toString());
		httpClient.close();
		SERVLETURL = "http://localhost:80/ADMCensus/census/create.do?";
		return res;

	}

	public void generaPeticionDeliberations(int id, String title) throws IOException {
		//TODO Cambiar URL por URL de despliegue.
		String SERVLETURL = "localhost:80//Deliberations/customer/createThreadFromVotacion.do?title="+title+"&id="+id;
		System.out.println(SERVLETURL);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		System.out.println("Creo el httpclient");
		HttpGet httpGet = new HttpGet(SERVLETURL);
		System.out.println("Creo el httpclient");
		httpGet.addHeader("User-Agent", USER_AGENT);
		System.out.println("Voy a ejecutar");
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println("Ejecutado");
		System.out.println("GET Response Status:: " + httpResponse.getStatusLine().getStatusCode());

		BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine.toString());
		}
		reader.close();

		httpClient.close();
		SERVLETURL = "localhost:80//Deliberations/customer/createThreadFromVotacion.do?";
		
	}
}
