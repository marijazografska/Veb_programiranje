package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping()
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Song> songs = this.songService.listSongs();
        model.addAttribute("songs", songs);
        return "listSongs";
    }

    @GetMapping("/add-song")
    public String addSongPage(Model model) {
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        model.addAttribute("song", new Song()); // Empty object for adding a new song
        return "add-song";
    }

    @PostMapping("/add")
    public String addSong(
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Integer releaseYear,
            @RequestParam Long albumId
    ) {
        Album album = this.albumService.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));
        this.songService.save(trackId, title, genre, releaseYear, albumId);
        return "redirect:/songs";
    }

    @GetMapping("/edit-song/{id}")
    public String getEditPage(@PathVariable Long id, Model model) {
        Optional<Song> songOptional = this.songService.findById(id);
        if (songOptional.isPresent()) {
            Song song = songOptional.get();
            List<Album> albums = this.albumService.findAll();
            model.addAttribute("albums", albums);
            model.addAttribute("song", song); // Data for editing
            return "add-song";
        }
        return "redirect:/songs?error=SongNotFound";
    }

    @PostMapping("/update/{id}")
    public String updateSong(
            @PathVariable Long id,
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Integer releaseYear,
            @RequestParam Long albumId
    ) {
        Album album = this.albumService.findById(albumId)
                .orElseThrow(() -> new IllegalArgumentException("Album not found"));

        Song song = this.songService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Song not found"));

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);
        song.setAlbum(album);

        this.songService.save(song.getTrackId(), song.getTitle(), song.getGenre(), song.getReleaseYear(), albumId);
        return "redirect:/songs";
    }

    @PostMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        this.songService.deleteById(id);
        return "redirect:/songs";
    }

    @GetMapping("/select-album")
    public String selectAlbumPage(Model model) {
        List<Album> albums = this.albumService.findAll();
        model.addAttribute("albums", albums);
        return "selectAlbum";
    }

    @PostMapping("/songs-by-album")
    public String getSongsByAlbum(@RequestParam Long albumId, Model model) {
        List<Song> songs = this.songService.listByAlbum(albumId);
        Optional<Album> album = albumService.findById(albumId);
        model.addAttribute("songs", songs);
        model.addAttribute("album", album.orElse(null));

        return "songsByAlbum";
    }


}
