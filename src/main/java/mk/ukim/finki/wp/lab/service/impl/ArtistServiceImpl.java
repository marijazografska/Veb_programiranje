package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> listAll()
    {
        return this.artistRepository
                .findAll();
    }

    @Override
    public Optional<Artist> findById(long id)
    {
        return this.artistRepository
                .findById(id);
    }
}
