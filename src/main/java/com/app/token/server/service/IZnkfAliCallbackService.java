package com.app.token.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 武海升
 * @date 2018/9/6 9:19
 */
public interface IZnkfAliCallbackService{

    String callbackUrl(HttpServletRequest request,HttpServletResponse response);

}
