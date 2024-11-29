package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService=albumService;
    }


    @GetMapping()
    public String getSongsPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null&&!error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Song> songs=this.songService.listSongs();
        model.addAttribute("songs",songs);
        return "listSongs";
    }
    @PostMapping()
    public String selectSong(@RequestParam("trackId") String trackId, HttpServletRequest request) {

        request.getSession().setAttribute("trackId", trackId);

        return "redirect:/artist";
    }
    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {

        this.songService.deleteById(id);

        return "redirect:/listSongs";
    }
    @GetMapping("/add-song")
    public String addSongPage(Model model)
    {
        List<Album> albums=this.albumService.findAll();

        model.addAttribute("albums",albums);
        return "add-song";
    }
    @PostMapping("/add")
    public String saveSong(@RequestParam String trackId,
                              @RequestParam String title,
                              @RequestParam String genre,
                              @RequestParam Integer releaseYear,
                              @RequestParam Long album)
    {
        this.songService.save(trackId,title,genre,releaseYear,album);
        return "redirect:/songs";

    }
    @GetMapping("edit-song/{id}")
    public String getEditPage(@PathVariable Long id, Model model)
    {
        if(this.songService.findById(id).isPresent())
        {
            Song song=this.songService.findById(id).get();
            List<Album> albums = this.albumService.findAll();

            model.addAttribute("albums", albums);
            model.addAttribute("song", song);
            return "add-song";
        }
        return "redirect:/songs?error=ProductNotFound";
    }




}
