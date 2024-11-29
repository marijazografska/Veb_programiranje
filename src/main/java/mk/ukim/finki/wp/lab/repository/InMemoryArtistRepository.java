package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.wp.lab.model.Artist;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryArtistRepository {
    public List<Artist> findAll()
    {
        return DataHolder.artists;
    }
    public Optional<Artist> findById(String id)
    {
        return DataHolder.artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }

}
