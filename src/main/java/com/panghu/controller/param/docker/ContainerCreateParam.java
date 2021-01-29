package com.panghu.controller.param.docker;

import lombok.Data;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 10:36 2021/1/29
 * @Modified By:
 */
@Data
public class ContainerCreateParam {

    private String host;

    private String containerName;

    private String imageName;

}
