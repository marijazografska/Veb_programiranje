package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping()
    public String getAlbumsPage(@RequestParam(required = false) String error, Model model)
    {
        if(error!=null&&!error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Album> albums=this.albumService.findAll();
        model.addAttribute("albums",albums);
        return "albums";
    }
    @PostMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable Long id) {

        this.albumService.deleteById(id);

        return "redirect:/albums";
    }
    @GetMapping("/add-album")
    public String addAlbumPage(Model model)
    {

        return "add-album";
    }
    @PostMapping("/add")
    public String saveSong(@RequestParam String name,
                           @RequestParam String genre,
                           @RequestParam String releaseYear)
    {
        this.albumService.save(name,genre,releaseYear);
        return "redirect:/albums";

    }
    @GetMapping("edit-album/{id}")
    public String getEditPage(@PathVariable Long id, Model model)
    {
        if(this.albumService.findById(id).isPresent())
        {
            Album album=this.albumService.findById(id).get();

            model.addAttribute("album", album);
            return "add-album";
        }
        return "redirect:/songs?error=ProductNotFound";
    }



}
