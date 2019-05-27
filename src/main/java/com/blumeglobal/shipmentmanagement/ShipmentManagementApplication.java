package com.blumeglobal.shipmentmanagement;


import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
/*
 * 1.All the packags have to be subpackage of this
 * 2. Add the Class level and Method level Java Doc.
 * 3. Logger INFO
 * 
 * */


/**ShipmentManagementApplication contains the main method 
 * from where the Springboot application is starting
 * @author aleesha.john
 *
 */
@SpringBootApplication
@EnableAsync
@ComponentScan("com.blumeglobal")
@EnableScheduling
public class ShipmentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShipmentManagementApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate()
	              throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		
		SSLContextBuilder sslcontext = new SSLContextBuilder();
        sslcontext.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        HttpClient httpClient = HttpClients.custom().setSSLContext(sslcontext.build()).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
            .build();

  	  HttpComponentsClientHttpRequestFactory requestFactory =
  	                  new HttpComponentsClientHttpRequestFactory();

  	  requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);		
		
	  return restTemplate;
	     
	}
	
	
	static {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
        new javax.net.ssl.HostnameVerifier(){

            public boolean verify(String hostname,
                    javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        });
    }
}
