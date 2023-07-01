package com.example.demotest.controller;

import com.example.demotest.entity.Test;
import com.example.demotest.util.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/test")
public class TestController {

//    @Autowired
//    private TestMapper testMapper;
//
//    @PostMapping("/add")
//    public ResponseEntity add(@RequestBody Test test){
//        return ResponseEntity.ok().body(testMapper.insertSelective(test));
//    }

    @GetMapping("/testRequestBody")
    public void testRequestBody(HttpServletRequest request){
        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream in = null;
        try
        {
            in = request.getInputStream();
            in.read(buffer, 0, len);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != in)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(buffer.toString());
    }
}
