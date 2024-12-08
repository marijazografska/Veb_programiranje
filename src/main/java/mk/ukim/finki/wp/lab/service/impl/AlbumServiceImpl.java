package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.AlbumNotFoundException;
import mk.ukim.finki.wp.lab.repository.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final InMemoryAlbumRepository albumRepository;

    public AlbumServiceImpl(InMemoryAlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return this.albumRepository.findAll();
    }

    @Override
    public Optional<Album> findById(Long albumId) {
        return albumRepository.findById(albumId);
    }


    @Override
    public Optional<Album> save(String name,
                               String genre,
                               String releaseYear) {



        return this.albumRepository.save(name,genre,releaseYear);

    }
    @Override
    public void deleteById(Long id)
    {
        this.albumRepository.deleteById(id);
    }

}
