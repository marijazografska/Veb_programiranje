package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


public interface SongService {
    List<Song> listSongs();
    List<Song> listByAlbum(Long album_id);
    Artist addArtistToSong(Long artistId, String trackId);
    Optional<Song> findByTrackId(String trackId);
    void deleteById(Long id);
    Optional<Song> findById(Long id);
    Optional<Song> save (String trackId,
                         String title,
                         String genre,
                         Integer releaseYear,
                         Long albumID);

}
