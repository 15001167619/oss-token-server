package com.app.token.server.service;

import net.sf.json.JSONObject;

/**
 * @author 武海升
 * @date 2018/9/5 17:32
 */
public interface IAuthorizationService {

    void setAccessKeyID(String accessKeyID);

    void setAccessKeySecret(String accessKeySecret);

    void setRoleArn(String roleArn);

    void setTokenExpireTime(Long tokenExpireTime);

    String getAccessKeyID();

    String getAccessKeySecret();

    String getRoleArn();

    Long getTokenExpireTime();

    JSONObject getToken();
}
