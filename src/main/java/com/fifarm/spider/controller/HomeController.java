package com.fifarm.spider.controller;

import com.fifarm.spider.net.Result;
import com.fifarm.spider.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "seonghun");
        return "home";
    }

    @GetMapping("/api")
    public String api(Model model) throws Exception {
        HomeService homeService = new HomeService();
        Result result = homeService.getAPI();
        model.addAttribute("result", result);
        return "api";
    }

    @GetMapping("/map")
    public String map(Model model) throws Exception {
        HomeService homeService = new HomeService();
        Map<String, Object> map = homeService.jsonToMap();
        model.addAttribute("map", map);
        return "map";
    }

}
