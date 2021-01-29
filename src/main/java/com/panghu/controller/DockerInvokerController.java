package com.panghu.controller;

import com.panghu.common.ResultDTO;
import com.panghu.controller.param.docker.ContainerCreateAndStartParam;
import com.panghu.controller.param.docker.ContainerCreateParam;
import com.panghu.service.docker.DockerInvokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 9:33 2021/1/29
 * @Modified By:
 */
@RequestMapping("/docker")
@RestController
public class DockerInvokerController {

    @Autowired
    private DockerInvokerService dockerInvokerService;

    @RequestMapping("/container/create")
    public ResultDTO<String> createContainer(@RequestBody ContainerCreateParam param) {
        String host = param.getHost();
        String containerName = param.getContainerName();
        String imageName = param.getImageName();
        return dockerInvokerService.createContainer(host,containerName,imageName);
    }

    @RequestMapping("/container/createAndStart")
    public ResultDTO<Void> createAndStartContainer(@RequestBody ContainerCreateAndStartParam param) {
        String host = param.getHost();
        String containerName = param.getContainerName();
        String imageName = param.getImageName();
        return dockerInvokerService.createAndStartContainer(host,containerName,imageName);
    }

}
