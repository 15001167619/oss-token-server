package com.app.token.server.mapper;

import com.app.token.server.entity.ZnkfAliCallback;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 武海升
 * @date 2018/9/6 8:58
 */
@Mapper
public interface ZnkfAliCallbackMapper{

    int insertAliCallback(ZnkfAliCallback aliCallback);

}
