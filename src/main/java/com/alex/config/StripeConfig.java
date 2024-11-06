package com.alex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stripe")
public class StripeConfig {

  private String apiKey;
  private String url;

  public String getApiKey() {
    return apiKey;
  }

  public String getUrl() {
    return url;
  }

  public void setApiKey(String apiKey) {
    this.apiKey = apiKey;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "StripeConfig [apiKey=" + apiKey + ", url=" + url + "]";
  }

}
