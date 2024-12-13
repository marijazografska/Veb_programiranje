package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song,Long> {

    Optional <Song> findByTrackId(String trackId);
    List<Song> findSongsByAlbum_Id(Long album_id);
    boolean existsByTrackId(String trackId);


}
