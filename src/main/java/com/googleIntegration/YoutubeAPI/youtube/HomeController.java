package com.googleIntegration.YoutubeAPI.youtube;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final YouTube youTube;

    public HomeController(YouTube youTube) {
        this.youTube = youTube;
    }

    @GetMapping
    String index(Model model) {
        model.addAttribute("channelVideos", //
                youTube.channelVideos("UC3usSeqCMYw2rNhMl2kmEMA", //
                        10, //
                        YouTube.Sort.VIEW_COUNT));
        return "index";
    }
}
