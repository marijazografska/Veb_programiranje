package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.ArtistNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemorySongRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    private final AlbumRepository albumRepository;

    public SongServiceImpl(SongRepository songRepository,ArtistRepository artistRepository,AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.artistRepository=artistRepository;
        this.albumRepository=albumRepository;

    }

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }
    @Override
    public Optional<Song> findByTrackId(String trackId) {
        return this.songRepository.findByTrackId(trackId);
    }



    @Override
    public void deleteById(Long id)
    {
        this.songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> save(String trackId, String title, String genre, Integer releaseYear, Long albumID) {
        Album album = this.albumRepository.findById(albumID)
                .orElseThrow(() -> new AlbumNotFoundException(albumID));

        Song song;
        if (this.songRepository.existsByTrackId(trackId)) {
            // Find the existing song by trackId
            song = this.songRepository.findByTrackId(trackId).orElseThrow(() -> new IllegalArgumentException("Song not found"));
            song.setTitle(title);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
            song.setAlbum(album);
        } else {
            // Create a new song
            song = new Song(trackId, title, genre, releaseYear, album);
        }

        return Optional.of(this.songRepository.save(song));
    }


    @Override
    public Optional<Song> findById(Long id) {
        return this.songRepository.findById(id);
    }

    @Override
    public Artist addArtistToSong(Long artistId, String trackId) {
        Song song = this.songRepository
                .findByTrackId(trackId)
                .orElseThrow(() -> new AlbumNotFoundException("Track ID not found: " + trackId));

        Artist artist = this.artistRepository
                .findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException("Artist ID not found: " + artistId));

        if (song.getPerformers().contains(artist)) {
            return artist;
        }

        song.getPerformers().add(artist);
        this.songRepository.save(song);

        return artist;
    }

    @Override
    public List<Song> listByAlbum(Long album_id)
    {
        return this.songRepository.findSongsByAlbum_Id(album_id);
    }



}
