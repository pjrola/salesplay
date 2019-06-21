package com.cloud.service.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.*;
import com.cloud.service.dto.InstanceDTO;
import com.cloud.service.entity.Instance;
import com.cloud.service.entity.MachineState;
import com.cloud.service.registry.AdapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("amazonWebServices")
public class AwsEc2Service implements AdapterService<Instance> {

    private AmazonEC2 ec2;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Region region;

    @Autowired
    public AwsEc2Service(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider) {
        this.region = awsRegion;
        this.ec2 = AmazonEC2ClientBuilder.standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(awsRegion.getName()).build();
    }

    public Instance create(Instance instance) {
        Placement placement = new Placement();
        RunInstancesResult result = null;
        RunInstancesRequest request = new RunInstancesRequest()
                .withImageId("ami-035be7bafff33b6b6")
                .withMaxCount(1)
                .withMinCount(1)
                .withInstanceType("t2.micro")
                .withPlacement(placement.withAvailabilityZone(this.region.getName() + "c"));
        try {
            result = ec2.runInstances(request);
        } catch (AmazonServiceException e) {
            log.error("Error creating instance: {}", e.getMessage());
        }

        com.amazonaws.services.ec2.model.Instance i = result.getReservation().getInstances().get(0);
        instance.setState(MachineState.getIfPresent(i.getState().getName()));
        instance.setRemoteId(i.getInstanceId());
        instance.setUrl(i.getPublicIpAddress());
        instance.setLastStartedTime(i.getLaunchTime());

        return instance;
    }

    public void delete(String id) {
        TerminateInstancesRequest request = new TerminateInstancesRequest()
                .withInstanceIds(id);
        try {
            ec2.terminateInstances(request);
        } catch (AmazonServiceException e) {
            log.error("Error terminating instance: {}", e.getMessage());
        }
    }

    /**
     * Delete a remote instance
     * change status to terminated
     *
     * @param instance
     * @return
     */
    public Instance terminate(Instance instance) {
        this.delete(instance.getRemoteId());
        instance.setState(MachineState.terminated);

        return instance;
    }

    public Instance update(Instance instance) {
        return null;
    }

    public Instance getById(String id) {
        return null;
    }

    public Page getAll(Pageable pageable) {
        return null;
    }

    public Instance getStatusById(String id) {
        DescribeInstanceStatusRequest request = new DescribeInstanceStatusRequest()
                .withInstanceIds(id);
        DescribeInstanceStatusResult result = null;

        try {
            result = ec2.describeInstanceStatus(request);
        } catch (AmazonServiceException e) {
            log.error("Error getting instance status: {}", e.getMessage());
        }

        return null;
    }

    public InstanceDTO restart(String id) {
        InstanceDTO dto = new InstanceDTO();
        RebootInstancesRequest request = new RebootInstancesRequest()
                .withInstanceIds(id);
        RebootInstancesResult result = null;

        try {
            result = ec2.rebootInstances(request);
        } catch (AmazonServiceException e) {

        }
        return dto;
    }

    public InstanceDTO stop(String id) {
        InstanceDTO dto = new InstanceDTO();
        StopInstancesRequest request = new StopInstancesRequest()
                .withInstanceIds(id);
        StopInstancesResult result = null;

        try {
            result =  ec2.stopInstances(request);
        } catch (AmazonServiceException e) {
            log.error("Error stopping instance: {}", e.getMessage());
        }

        String state = result.getStoppingInstances().get(0).getCurrentState().getName();
        dto.setState(MachineState.getIfPresent(state));

        return dto;
    }

    public InstanceDTO start(String id) {
        InstanceDTO dto = new InstanceDTO();
        StartInstancesRequest request = new StartInstancesRequest()
                .withInstanceIds(id);
        StartInstancesResult result = null;

        try {
            result = ec2.startInstances(request);
        } catch (AmazonServiceException e) {
            log.error("Error starting instance: {}", e.getMessage());
        }

        String state = result.getStartingInstances().get(0).getCurrentState().getName();
        dto.setState(MachineState.getIfPresent(state));

        return dto;
    }
}
