package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dao.MoodDAO;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MoodyBluezController {

    /**
     * Handles the root endpoint and returns index.html
     * @return Returns a thymeleaf template
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Returns the json representation of a Mood by id
     * @param id integer id of a mood
     * @return Mood in JSON format
     */
    @GetMapping("/api/Mood/{id}")
    public @ResponseBody Mood moodFetchById(@PathVariable int id){
        MoodDAO dao = new MoodDAO();
        return dao.fetchByMoodID(id);
    }

}
