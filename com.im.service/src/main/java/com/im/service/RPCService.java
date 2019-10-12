package com.im.service;

import com.passport.rpc.AdminRPCService;
import com.passport.rpc.UserRPCService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class RPCService {
    @Reference
    private UserRPCService userRPCService;

    @Reference
    private AdminRPCService adminRPCService;

    public UserRPCService getUserRPCService() {
        return userRPCService;
    }

    public AdminRPCService getAdminRPCService() {
        return adminRPCService;
    }
}
