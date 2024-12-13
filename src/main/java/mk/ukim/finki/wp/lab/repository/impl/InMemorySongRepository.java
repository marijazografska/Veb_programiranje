package mk.ukim.finki.wp.lab.repository.impl;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class InMemorySongRepository {
    public List<Song> findAll()
    {
        return DataHolder.songs;
    }
    public Song findByTrackId(String trackId)
    {
        return DataHolder.songs.stream().filter(song -> song.getTrackId().equals(trackId)).
                findFirst().orElse(null);
    }
    public Artist addArtistToSong(Artist artist, Song song)
    {
        if (song.getPerformers() == null) {
            song.setPerformers(new ArrayList<>()); // Ensure performers is initialized
        }
        if (!song.getPerformers().contains(artist)) {
            song.getPerformers().add(artist); // Add the artist to performers
        }
        return artist;
    }
    public void deleteById(Long id) {
        DataHolder.songs.removeIf(i->i.getId().equals(id));
    }
    public Optional<Song> save(String trackId,
                               String title,
                               String genre,
                               Integer releaseYear,
                               Album album)
    {
        if (album == null) {
            throw new IllegalArgumentException();
        }
        Song song=new Song(trackId,title,genre,releaseYear,album);
        DataHolder.songs.removeIf(i->i.getTitle().equals(song.getTitle())||i.getTrackId().equals(song.getTrackId()));

        DataHolder.songs.add(song);
        return Optional.of(song);

    }
    public Optional<Song> findById(long prod_id)
    {
        return DataHolder.songs.stream().filter(i->i.getId().equals(prod_id)).findFirst();
    }




}
