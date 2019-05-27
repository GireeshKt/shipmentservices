package com.blumeglobal.shipmentmanagement.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blumeglobal.shipmentmanagement.external.hub.adapter.IOrganizationAdapter;
import com.blumeglobal.shipmentmanagement.external.hub.adapter.OrganizationAdapterImpl;
import com.blumeglobal.shipmentmanagement.external.hub.endpoint.IDispatchManagerFacade;

/**
 * Configuration class for Hub client
 *
 */
@Configuration
public class HubClientConfig {
	
	@Value("${client.hub.address}")
	private String address;
	
	
	/**
	 * Method for proxy
	 * @return IDispatchManagerFacade
	 */
	@Bean(name = "dispatchManagerFacadeClient")
	  public IDispatchManagerFacade helloWorldProxy() {
	    JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
	    jaxWsProxyFactoryBean.setServiceClass(IDispatchManagerFacade.class);
	    jaxWsProxyFactoryBean.setAddress(address);

	    return (IDispatchManagerFacade) jaxWsProxyFactoryBean.create();
	  }
	
    @Bean
    public IOrganizationAdapter organiationAdapter() {
          return new OrganizationAdapterImpl();
        }

	
	
}
