package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService=songService;
    }

    @GetMapping()
    public String ArtistGetPage(@SessionAttribute(value = "trackId", required = false) String trackId, Model model)
    {
        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("trackId", trackId);
        return "artistsList";
    }
    @PostMapping()
    public String addArtistToTrack(
            @RequestParam("artistId") String artistId,
            @SessionAttribute(value = "trackId", required = false) String trackId) {

        songService.addArtistToSong(
                artistService.ArtistfindById(artistId),
                songService.findByTrackId(trackId)
        );

        return "redirect:/songDetails";
    }

}
