package com.app.token.server.api;

import com.app.token.server.service.IAuthorizationService;
import com.app.token.server.service.IZnkfAliCallbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 武海升
 * @date 2018/9/5 17:43
 */
@RestController
@RequestMapping(value = "ali")
@Slf4j
public class AuthorizationController {

    @Autowired
    private IAuthorizationService authorizationService;

    @Autowired
    private IZnkfAliCallbackService aliCallbackService;

    @RequestMapping(value={"getToken"}, method=RequestMethod.GET)
    public Object getToken() {
        log.info("================> 执行获取 Token");
        return authorizationService.getToken();
    }

    @RequestMapping(value = "callback", method = {RequestMethod.POST,RequestMethod.GET})
    public Object callback(HttpServletRequest request,
                           HttpServletResponse response) {
        return aliCallbackService.callbackUrl(request,response);
    }
}
