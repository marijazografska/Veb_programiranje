package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAlbumRepository {

    public List<Album> findAll()
    {
        return DataHolder.albums;
    }

    public Optional<Album> findById(Long man_id)
    {
        return DataHolder.albums.stream().filter(i->i.getId().equals(man_id)).findFirst();
    }

}
