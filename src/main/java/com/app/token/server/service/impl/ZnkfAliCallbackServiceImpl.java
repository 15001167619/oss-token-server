package com.app.token.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.app.token.server.mapper.ZnkfAliCallbackMapper;
import com.app.token.server.entity.ZnkfAliCallback;
import com.app.token.server.service.IZnkfAliCallbackService;
import com.app.token.server.utils.CallbackUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 武海升
 * @date 2018/9/6 9:20
 */
@Service
@Slf4j
public class ZnkfAliCallbackServiceImpl implements IZnkfAliCallbackService {

    @Resource
    private ZnkfAliCallbackMapper aliCallbackMapper;

    @Override
    public String callbackUrl(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",false);
        try {
            String ossCallbackBody = CallbackUtil.getPostBody(request.getInputStream(), Integer.parseInt(request.getHeader("content-length")));
            log.info("=========>  ossCallbackBody  "+ossCallbackBody);
            Map<String, String> map = Stream.of(ossCallbackBody.split("&"))
                    .map(str -> str.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1].replace("\"", "")));
            boolean ret = CallbackUtil.verifyOSSCallbackRequest(request, ossCallbackBody);
            if (ret) {
                response.setStatus(HttpServletResponse.SC_OK);
                jsonObject.put("success",true);
                //保存数据
                aliCallbackMapper.insertAliCallback(new ZnkfAliCallback(map));
                return jsonObject.toString();
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return jsonObject.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
