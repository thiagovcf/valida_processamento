
package br.com.marketpay.validaapp.conf;

import org.junit.Ignore;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "spring", ignoreUnknownFields = true)
@Ignore
public class ValidaappProperties{

     
     @Getter
     private final Datasource datasource = new Datasource();

     public static class Datasource{

          @Getter
          @Setter
          private String url;

          @Getter
          @Setter
          private String username;

          @Getter
          @Setter
          private String password;

          @Getter
          @Setter
          private String driverClassName;

          @Getter
          @Setter
          private String connectionTestQuery;

     }

}
