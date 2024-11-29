package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List <Album> findAll();

    Optional<Album> findById(Long id);


}
