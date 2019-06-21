package com.cloud.service.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix="aws")
public class AwsProperties {
    @NotNull
    private String region;

    @NotNull
    private String accessKeyId;

    @NotNull
    private String secretKey;
}
