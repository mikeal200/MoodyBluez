package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dto.Mood;
import com.moodybluez.enterprise.service.IMoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MoodyBluezController {

    @Autowired
    IMoodService moodService;
    /**
     * Handles the root endpoint and returns index.html
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        Mood mood = new Mood();
        mood.setMoodID(1001);
        mood.setMoodDesc("Happy");
        model.addAttribute(mood);
        return "index";
    }

    @GetMapping("/mood")
    public ResponseEntity fetchAllMoods() { return moodService.fetchAll(); }
}
