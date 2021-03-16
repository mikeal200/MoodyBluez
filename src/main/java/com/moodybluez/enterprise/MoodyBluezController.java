package com.moodybluez.enterprise;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MoodyBluezController {

    /**
     * Handles the root endpoint and returns index.html
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
