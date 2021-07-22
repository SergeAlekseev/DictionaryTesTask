package DictionarySpring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class DictionariesController {

    @GetMapping
    public String getHello(Model model){
        int i = 1;
        return "hello";
    }

}
