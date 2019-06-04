package com.cloud.service.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ AwsProperties.class })
public class AwsAutoConfiguration {

    @Autowired
    private AwsProperties awsProperties;

    @Bean(name = "awsRegion")
    @ConditionalOnProperty(name = "provider.name", havingValue = "aws", matchIfMissing = true)
    public Region getAWSPollyRegion() {
        return Region.getRegion(Regions.fromName(awsProperties.getRegion()));
    }

    @Bean(name = "awsCredentialsProvider")
    @ConditionalOnProperty(name = "provider.name", havingValue = "aws", matchIfMissing = true)
    public AWSCredentialsProvider getAWSCredentials() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsProperties.getAccessKeyId(), awsProperties.getSecretKey());
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
}
