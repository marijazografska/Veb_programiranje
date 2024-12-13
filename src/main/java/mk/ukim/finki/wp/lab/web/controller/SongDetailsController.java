package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songDetails")
public class SongDetailsController {

    private final SongService songService;

    public SongDetailsController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public String getSongDetails(HttpServletRequest request, Model model) {
        String trackId = (String) request.getSession().getAttribute("trackId");

        if (trackId == null || trackId.isEmpty()) {
            return "redirect:/songs?error=NoSongSelected";
        }

        Song song = this.songService.findByTrackId(trackId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));

        model.addAttribute("listSongs", songService.listSongs());
        model.addAttribute("song", song);

        return "songDetails";
    }
}
