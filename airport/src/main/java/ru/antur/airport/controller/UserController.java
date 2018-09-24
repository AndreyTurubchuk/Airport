package ru.antur.airport.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antur.airport.model.User;
import ru.antur.airport.service.UserMessageSender;

import java.util.List;

@Controller
public class UserController {

    private final List<User> messageList;

    @Autowired
    UserMessageSender userMessageSender;

    public UserController(List<User> messageList) {
        this.messageList = messageList;
    }

    @GetMapping("/demo")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/demo")
    public String userSubmit(@ModelAttribute User user, Model model) {
        userMessageSender.sendMessage(user);
        model.addAttribute("message", "Сообщение отправлено в очередь");
        return "result";
    }

    @RequestMapping("/getMessage")
    public String myMessage(Model model) {
        model.addAttribute("messageList", messageList);
        return "resultMessage";
    }
}
