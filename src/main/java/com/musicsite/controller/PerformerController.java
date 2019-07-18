package com.musicsite.controller;


import com.musicsite.entity.*;
import com.musicsite.service.AlbumService;
import com.musicsite.service.PerformerService;
import com.musicsite.service.TrackService;
import com.musicsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/performer")
public class PerformerController {
    private PerformerService performerService;
    private UserService userService;
    private AlbumService albumService;
    private TrackService trackService;

    @Autowired
    public PerformerController(PerformerService performerService,
                               UserService userService,
                               AlbumService albumService,
                               TrackService trackService) {
        this.performerService = performerService;
        this.userService = userService;
        this.albumService = albumService;
        this.trackService = trackService;
    }


    @GetMapping("/{id}")
    public String showForm(@PathVariable String id, Model model, HttpSession session) {
        Performer performer = performerService.getPerformerById(Long.parseLong(id));
        if (performer == null || performer.isProposition())
            return "main/blank";

        Long userId = (Long) session.getAttribute("loggedUserId");
        if (userId != null)
            model.addAttribute("userPerformerRating", userService.getPerformerUserRating(userId, performer));


        performerService.orderData(performer);
        model.addAttribute("performer", performer);
        model.addAttribute("performerAlbums", albumService.getAlbumsByPerformerAndPropositionOrderByYear(performer, false));
        model.addAttribute("performerTracks", trackService.getTracksByPerformerAndPropositionsOrderByYear(performer, false));

        return "main/performer";
    }

    @GetMapping("/{performerId}/setRate/{rating}")
    public String ratePerformer(@PathVariable Long performerId, @PathVariable int rating, HttpSession session){

        Long userId = (Long) session.getAttribute("loggedUserId");

        if (userId == null)
            return "redirect:/login";

        performerService.saveRating(userId, performerId, rating);
        performerService.updatePerformerAverage(performerId);

        return "redirect:/performer/".concat(String.valueOf(performerId));
    }

}
