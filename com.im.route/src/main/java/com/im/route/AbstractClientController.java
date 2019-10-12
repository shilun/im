package com.im.route;

import com.common.exception.BizException;
import com.common.util.RPCResult;
import com.common.util.StringUtils;
import com.common.web.AbstractController;
import com.im.service.RPCService;
import com.passport.rpc.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

/**
 * Created by shilun on 2017/5/12.
 */
@Slf4j
public abstract class AbstractClientController extends AbstractController {

    @Resource
    private RPCService rpcService;

    /**
     * 获取用户token
     * @return
     */
    @SuppressWarnings("Duplicates")
    protected UserDTO getUser() {
        String token = getToken();
        RPCResult<UserDTO> userDTOResult = rpcService.getUserRPCService().verificationToken(token);
        if (userDTOResult.getSuccess()) {
            return userDTOResult.getData();
        }
        log.error("token.error:" + token);
        throw new BizException("token.error", "获取token失败");
    }

    protected String getToken() {
        String token = getRequest().getHeader("token");
        if (StringUtils.isBlank(token)) {
            Cookie tokenCookie = null;
            for (Cookie item : getRequest().getCookies()) {
                if (StringUtils.equals(item.getName(), "token")) {
                    tokenCookie = item;
                    break;
                }
            }
            token = tokenCookie.getValue();
        }
        return token;
    }
}
