package com.im.route.controller;

import com.common.web.IExecute;
import com.im.route.AbstractClientController;
import com.im.service.ServerInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api(description = "服务器路由")
@RestController
@RequestMapping(method = {RequestMethod.POST})
public class RouteController extends AbstractClientController {
    @Resource
    private ServerInfoService serverInfoService;

    /**
     * 查找可用im资源服务
     * @return
     */
    @RequestMapping(name="/route/available",method = {RequestMethod.POST})
    public Map<String, Object> findAvailable() {
        return buildMessage(new IExecute() {
            @Override
            public Object getData() {
                return serverInfoService.findAvailable();
            }
        });
    }
}
