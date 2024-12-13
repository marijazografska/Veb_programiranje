package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
    List<Artist> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);
    Optional<Artist> findById(Long artistId);

}
