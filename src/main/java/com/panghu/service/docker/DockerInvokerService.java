package com.panghu.service.docker;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 10:20 2021/1/29
 * @Modified By:
 */

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.panghu.common.ResultDTO;
import com.panghu.manager.sql.DockerClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DockerInvokerService {

    @Autowired
    private DockerClientManager dockerClientManager;


    /**
     *  创建Docker 容器
     * @param host 主机host
     * @param containerName 容器名称
     * @param imageName 镜像名称
     * @return
     */
    public ResultDTO<String> createContainer(String host,String containerName,String imageName) {
        DockerClient dockerClient = dockerClientManager.connectDocker(host);
        CreateContainerResponse createContainerResponse = dockerClientManager.
                createContainers(dockerClient, containerName, imageName);
        return ResultDTO.successData(createContainerResponse.getId());
    }

    public ResultDTO<Void> createAndStartContainer(String host,String containerName,String imageName) {
        DockerClient dockerClient = dockerClientManager.connectDocker(host);
        CreateContainerResponse createContainerResponse = dockerClientManager.
                createContainers(dockerClient, containerName, imageName);
        String id = createContainerResponse.getId();
        startContainer(dockerClient,id);

        return ResultDTO.successData(null);
    }


    private void startContainer(DockerClient client,String containerId) {
        dockerClientManager.startContainer(client, containerId);
    }

}
