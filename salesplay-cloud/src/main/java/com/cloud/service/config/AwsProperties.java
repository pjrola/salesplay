package com.cloud.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix="aws")
public class AwsProperties {

    @NotNull
    private String region;

    @NotNull
    private String accessKeyId;

    @NotNull
    private String secretKey;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "AwsProperties{" +
                "region='" + region + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
