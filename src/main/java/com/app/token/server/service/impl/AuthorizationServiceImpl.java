package com.app.token.server.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.app.token.server.constants.TokenConstants;
import com.app.token.server.service.IAuthorizationService;
import com.app.token.server.utils.FileUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author 武海升
 * @date 2018/9/5 17:33
 */
@Service
@Slf4j
public class AuthorizationServiceImpl implements IAuthorizationService {

    protected volatile String accessKeyID;
    protected volatile String accessKeySecret;
    protected volatile String roleArn;
    protected volatile Long tokenExpireTime;

    @Override
    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    @Override
    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Override
    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    @Override
    public void setTokenExpireTime(Long tokenExpireTime) {
        this.tokenExpireTime = tokenExpireTime;
    }

    @Override
    public String getAccessKeyID() {
        return this.accessKeyID;
    }

    @Override
    public String getAccessKeySecret() {
        return this.accessKeySecret;
    }

    @Override
    public String getRoleArn() {
        return this.roleArn;
    }

    @Override
    public Long getTokenExpireTime() {
        return this.tokenExpireTime;
    }

    @Override
    public JSONObject getToken() {
        log.info("【获取Token】=========>，accessKeyID = {}", this.accessKeyID);
        log.info("【获取Token】=========>，accessKeySecret = {}", this.accessKeySecret);
        log.info("【获取Token】=========>，roleArn = {}", this.roleArn);
        log.info("【获取Token】=========>，tokenExpireTime = {}", this.tokenExpireTime);
        long durationSeconds = this.tokenExpireTime;
        String policy = FileUtils.getContent();
        ProtocolType protocolType = ProtocolType.HTTPS;
        JSONObject json;
        try {
            final AssumeRoleResponse stsResponse = assumeRole(this.accessKeyID, this.accessKeySecret, this.roleArn, TokenConstants.ROLE_SESSION_NAME,
                    policy, protocolType, durationSeconds);
            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("StatusCode", "200");
            respMap.put("AccessKeyId", stsResponse.getCredentials().getAccessKeyId());
            respMap.put("AccessKeySecret", stsResponse.getCredentials().getAccessKeySecret());
            respMap.put("SecurityToken", stsResponse.getCredentials().getSecurityToken());
            respMap.put("Expiration", stsResponse.getCredentials().getExpiration());
            json = JSONObject.fromObject(respMap);
        } catch (ClientException e) {
            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("StatusCode", "500");
            respMap.put("ErrorCode", e.getErrCode());
            respMap.put("ErrorMessage", e.getErrMsg());
            json = JSONObject.fromObject(respMap);
        }
        log.info("【获取OSS-Token】=========>，json = {}", json);
        return json;
    }


    protected AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
                                            String roleSessionName, String policy, ProtocolType protocolType, long durationSeconds) throws ClientException
    {
        try {
            IClientProfile profile = DefaultProfile.getProfile(TokenConstants.REGION_CN_SHANGHAI, accessKeyId, accessKeySecret);
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setVersion(TokenConstants.STS_API_VERSION);
            request.setMethod(MethodType.POST);
            request.setProtocol(protocolType);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy);
            request.setDurationSeconds(durationSeconds);
            final AssumeRoleResponse response = client.getAcsResponse(request);
            return response;
        } catch (ClientException e) {
            throw e;
        }
    }
}
