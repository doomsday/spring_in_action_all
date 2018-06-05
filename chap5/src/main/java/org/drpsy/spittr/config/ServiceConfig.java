package org.drpsy.spittr.config;

import org.drpsy.spittr.service.SpittrService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * Created by drpsy on 02-Jun-18 (21:51).
 */
@Configuration
public class ServiceConfig {

  @Bean
  public HessianServiceExporter hessianExportedSpittrService(SpittrService service) {
    HessianServiceExporter exporter = new HessianServiceExporter();
    exporter.setService(service);
    exporter.setServiceInterface(SpittrService.class);
    return exporter;
  }

//  @Bean(name = "SpittrTestService")
//  public HessianProxyFactoryBean spittrTestService() {
//    HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
//    proxy.setServiceUrl("http://localhost:8080/Spittr/spittr.service");
//    proxy.setServiceInterface(SpittrService.class);
//    return proxy;
//  }

}
