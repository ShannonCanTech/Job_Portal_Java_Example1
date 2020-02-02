package dev.shannonfoster.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    JobRepository jobRepository;

    @RequestMapping
    public String index(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addJob(Model model) {
        model.addAttribute("job", new Job());
        return "jobform";
    }
}
