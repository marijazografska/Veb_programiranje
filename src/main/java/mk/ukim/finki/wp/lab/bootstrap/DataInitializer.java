package mk.ukim.finki.wp.lab.bootstrap;



import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.jpa.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.jpa.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.jpa.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public DataInitializer(ArtistRepository artistRepository, AlbumRepository albumRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @PostConstruct
    public void init() {
        // Create Artists
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Pharrell", "Williams", "American rapper, producer, and singer."));
        artists.add(new Artist("David", "Guetta", "French house DJ."));
        artists.add(new Artist("Amy", "Winehouse", "English late singer."));
        artists.add(new Artist("Kelly", "Rowland", "American singer."));
        artists.add(new Artist("Lenny", "Kravitz", "American singer and the KING tbh."));
        artistRepository.saveAll(artists);

        // Create Albums
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("Girl", "pop", "2013"));
        albums.add(new Album("One Love", "house/techno", "2011"));
        albums.add(new Album("Back to Black", "soul", "2006"));
        albums.add(new Album("Are You Gonna Go My Way", "rock", "1993"));
        albums.add(new Album("In My Mind", "R&B", "2004"));
        albumRepository.saveAll(albums);

        // Create Songs
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("A", "When Love Takes Over", "house", 2011, albums.get(1)));
        songs.add(new Song("B", "Happy", "pop", 2013, albums.get(0)));
        songs.add(new Song("C", "Back to Black", "soul", 2006, albums.get(2)));
        songs.add(new Song("D", "Are You Gonna Go My Way", "rock", 1993, albums.get(3)));
        songs.add(new Song("E", "Frontin", "R&B", 2004, albums.get(4)));
        songRepository.saveAll(songs);

        // Associate songs with artists
        artists.get(0).getSongs().add(songs.get(1)); // Pharrell -> Happy
        artists.get(1).getSongs().add(songs.get(0)); // Guetta -> When Love Takes Over
        artists.get(2).getSongs().add(songs.get(2)); // Winehouse -> Back to Black
        artists.get(3).getSongs().add(songs.get(0)); // Rowland -> When Love Takes Over
        artists.get(4).getSongs().add(songs.get(3)); // Kravitz -> Are You Gonna Go My Way
        artistRepository.saveAll(artists); // Update associations in DB
    }
}

