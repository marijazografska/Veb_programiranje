package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryAlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
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

        Album album=new Album(name,genre,releaseYear);
        return Optional.of(this.albumRepository.save(album));
    }
    @Override
    public void deleteById(Long id)
    {
        this.albumRepository.deleteById(id);
    }

}
