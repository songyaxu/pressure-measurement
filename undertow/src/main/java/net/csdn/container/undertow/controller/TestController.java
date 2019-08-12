package net.csdn.container.undertow.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 * @Author： yaxuSong
 * @Description：
 * @Date： 19-8-8 上午11:41
 * @MOdified by:
 **/
@RestController
@RequestMapping("test")
public class TestController {


    @Value("${server.name}")
    private String SERVER_NAME;

    /**
     * 未使用HTTP异步的接口
     */
    @GetMapping("/block")
    public String block() {
        return SERVER_NAME + " block!!!";
    }

    /**
     * 使用HTTP异步的接口
     */
    @GetMapping("/async")
    public WebAsyncTask<String> async() {
        return new WebAsyncTask(() -> SERVER_NAME + " async!!!");
    }
}
