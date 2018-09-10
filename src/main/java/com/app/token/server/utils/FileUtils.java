package com.app.token.server.utils;

import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @author 武海升
 * @date 2018/9/5 17:58
 */
public class FileUtils {



    public static String getContent(){
        JSONObject jsonObject = new JSONObject();

        JSONObject jsonObj = new JSONObject();



        String[] action = new String[1];
        action[0] = "oss:*";
        String[] resource = new String[1];
        resource[0] = "acs:oss:*:*:*";

        jsonObj.put("Action",action);
        jsonObj.put("Effect","Allow");
        jsonObj.put("Resource",resource);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObj);


        jsonObject.put("Version","1");
        jsonObject.put("Statement",jsonArray);


        return jsonObject.toString();
    }

    public static void main(String[] args) {
        System.out.println(getContent());
    }

}
