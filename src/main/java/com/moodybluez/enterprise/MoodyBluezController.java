package com.moodybluez.enterprise;

import com.moodybluez.enterprise.dao.MoodDAO;
import com.moodybluez.enterprise.dto.Mood;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MoodyBluezController implements ErrorController {

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
     * @author Stephen Meckstroth
     */
    @GetMapping("/api/Mood/{id}")
    public @ResponseBody Mood moodFetchById(@PathVariable int id){
        MoodDAO dao = new MoodDAO();
        return dao.fetchByMoodID(id);
    }

    /**
     * Returns the error template whenever an error occurs
     * @param request The request that caused the error
     * @return the error.html Thymeleaf template
     * @author Stephen Meckstroth
     */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
