package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

@Controller
public class IndexControl {
    private final PostService posts;
    private final UserService userService;

    public IndexControl(PostService posts, UserService userService) {
        this.posts = posts;
        this.userService = userService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", posts.findAll());
        model.addAttribute("user", userService.findByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()).get());
        return "index";
    }
}
