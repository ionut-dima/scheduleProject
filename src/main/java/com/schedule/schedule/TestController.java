package com.schedule.schedule;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/")
    public String getTest() {
        return "test message";
    }

    @GetMapping("/test1")
    public String getTest1(@RequestParam(value = "dima") String text) {
        return text;
    }


    @PostMapping("/testPost")
    public void testM(@RequestBody String text){
        System.out.println(text);
    }

    @PutMapping("/put")
    public void testPut(@RequestBody String text) {
        System.out.println(text);
    }

//    @DeleteMapping
}
