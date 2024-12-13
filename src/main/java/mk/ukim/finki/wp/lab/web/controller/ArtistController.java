package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping()
    public String ArtistGetPage(@SessionAttribute(value = "trackId", required = false) String trackId, Model model) {
        model.addAttribute("artists", artistService.listAll());
        model.addAttribute("trackId", trackId);
        return "artistsList";
    }

    @PostMapping()
    public String addArtistToTrack(
            @RequestParam("artistId") Long artistId,
            @SessionAttribute(value = "trackId", required = false) String trackId
    ) {
        this.songService.addArtistToSong(artistId, trackId);
        return "redirect:/songDetails";
    }
}
