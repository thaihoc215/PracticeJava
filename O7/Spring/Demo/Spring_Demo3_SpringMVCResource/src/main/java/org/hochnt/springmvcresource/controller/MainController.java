package org.hochnt.springmvcresource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping(value = "/staticResourceTest")
    public String staticResource(Model model) {
        return "staticResourceTest";
    }
}
