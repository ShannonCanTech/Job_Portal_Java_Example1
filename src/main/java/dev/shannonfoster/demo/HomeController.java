package dev.shannonfoster.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    JobRepository jobRepo;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("jobs", jobRepo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addJob(Model model) {
        model.addAttribute("job", new Job());
        return "jobform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Job job, BindingResult result) {
        if (result.hasErrors()) {
            return "jobform";
        }
        jobRepo.save(job);
        return "redirect:/";
    }

    @RequestMapping("/details/{id}")
    public String jobDetails(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", jobRepo.findById(id));
        return "details";
    }

    @RequestMapping("/update/{id}")
    public String updateJob(@PathVariable("id") long id, Model model) {
        model.addAttribute("job", jobRepo.findById(id));
        return "jobform";
    }

    @RequestMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") long id) {
        jobRepo.deleteById(id);
        return "redirect:/";
    }
}
