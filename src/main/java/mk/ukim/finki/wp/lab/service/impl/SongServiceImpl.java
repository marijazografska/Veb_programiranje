package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumNotFoundException;
import mk.ukim.finki.wp.lab.repository.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.repository.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.repository.InMemorySongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final InMemorySongRepository songRepository;
    private final InMemoryArtistRepository artistRepository;

    private final InMemoryAlbumRepository albumRepository;

    public SongServiceImpl(InMemorySongRepository songRepository,InMemoryArtistRepository artistRepository,InMemoryAlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository=artistRepository;
        this.albumRepository=albumRepository;

    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist,song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public void deleteById(Long id)
    {
        this.songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> save(String trackId,
                               String title,
                               String genre,
                               Integer releaseYear,
                               Long albumID) {


        Album album =this.albumRepository.findById(albumID).orElseThrow(()->new AlbumNotFoundException(albumID));
        return this.songRepository.save(trackId,title,genre,releaseYear,album);

    }
    @Override
    public Optional<Song> findById(Long id) {
        return this.songRepository.findById(id);
    }


}
