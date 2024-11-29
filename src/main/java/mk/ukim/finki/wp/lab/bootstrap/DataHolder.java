package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artists=null;
    public static List<Song> songs=null;
    public static List<Album> albums=null;

    @PostConstruct
    public void init() {
        artists=new ArrayList<>();
        artists.add(new Artist("1","Pharrell","Williams","American raper,producer and singer."));
        artists.add(new Artist("2","David","Guetta","French house DJ."));
        artists.add(new Artist("3","Amy","Winehouse","English late singer"));
        artists.add(new Artist("4","Kelly","Rowland","American singer."));
        artists.add(new Artist("5","Lenny","Kravitz","American singer and the KING tbh."));

        albums=new ArrayList<>();
        albums.add(new Album("Girl","pop","2013"));
        albums.add(new Album("One Love","house/techno","2011"));
        albums.add(new Album("Back to black","soul","2006"));
        albums.add(new Album("Are You Gonna Go My Way","rock","1993"));
        albums.add(new Album("In My Mind","R&B","2004"));


        songs=new ArrayList<>();
        songs.add(new Song("A", "When love takes over", "house", 2011 ,albums.get(1)));
        songs.add(new Song("B", "Happy", "pop", 2013 ,albums.get(0)));
        songs.add(new Song("C", "Back to Black", "soul", 2006,albums.get(2)));
        songs.add(new Song("D", "Are You Gonna Go My Way", "rock", 1993, albums.get(3)));
        songs.add(new Song("E", "Frontin", "R&B", 2004,albums.get(4)));
    }

}
