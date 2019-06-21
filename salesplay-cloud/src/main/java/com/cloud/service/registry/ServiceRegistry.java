package com.cloud.service.registry;

public interface ServiceRegistry {
    <T> AdapterService<T> getService(String serviceName);
}
