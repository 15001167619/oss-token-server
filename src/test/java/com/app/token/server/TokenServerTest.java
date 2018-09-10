package com.app.token.server;

import com.app.token.server.mapper.ZnkfAliCallbackMapper;
import com.app.token.server.entity.ZnkfAliCallback;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 武海升
 * @date 2018/9/6 9:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TokenServerTest {

    @Resource
    private ZnkfAliCallbackMapper aliCallbackMapper;

    @Test
    public void insertTest() {
//        String ss = "bucket=\"zkhc-report\"&object=\"04bb938cd502450b89811831cc9c1850/1536222719882\"&etag=\"FF021236C517C7762FA7EACDC51B39CF\"&size=242930&mimeType=\"application/octet-stream\"";
//        Map<String, String> map = Stream.of(ss.split("&"))
//                .map(str -> str.split("="))
//                .collect(Collectors.toMap(s -> s[0], s -> s[1].replace("\"", "")));
//        System.out.println(map);
        aliCallbackMapper.insertAliCallback(new ZnkfAliCallback());
    }

}
