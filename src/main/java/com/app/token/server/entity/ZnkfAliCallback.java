package com.app.token.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author 武海升
 * @date 2018/9/6 8:58
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ZnkfAliCallback{

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String userId;

    private String bucketName;

    private Long reportTime;

    private String remark;

    private Date insertTime = new Date();


    public ZnkfAliCallback(){

    }

    public ZnkfAliCallback(Map<String, String> map){
        this.bucketName = map.get("bucket");
        String object = map.get("object");
        if(StringUtils.isNotBlank(object)){
            String[] split = object.split("/");
            if(split.length==2){
                this.userId = split[0];
                this.reportTime = Long.parseLong(split[1]);
            }
        }
    }


}
