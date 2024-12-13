package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.Artist;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Song {
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;

    @ManyToMany(mappedBy = "songs")
    private List<Artist> performers=new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Album album;
    public Song(String trackId, String title, String genre, int releaseYear,  Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>();;
        this.album=album;
    }
}
