import com.nxastudios.acetato.core.action.AddAlbum;
import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import io.reactivex.Completable;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddAlbumTest {
    private static final String TITLE = "anAlbum";
    private static final Long RELEASE_DATE = 1L;
    private static final Artist ARTIST = giveNewArtist();
    private static final List<Artist> ARTISTS = asList(ARTIST);
    private static final Track TRACK = giveNewTrack();
    private static final List<Track> TRACKS = asList(TRACK);
    private static final AlbumType TYPE = AlbumType.ALBUM;
    private Album album;
    private AlbumId idAlbum = new AlbumId("123-bar1-123");
    private AddAlbum action;
    private Albums repository;
    private Completable result;
    private AlbumDTO albumDTO;

    private static Track giveNewTrack() {
        return new Track.Builder()
                .withTitle("Nice job bob!")
                .withIdTrack("1")
                .build();
    }

    private static Artist giveNewArtist() {
        return new Artist.Builder()
                .withName("bob")
                .withId("1")
                .build();
    }

    @Test
    public void albumIsAddedThenShouldBeSuccessfully() {
        //given
        giveAnAlbum();
        givenAnRepository();
        givenAnAction();

        //when
        whenNewAlbumAdded();

        //then
        thenShouldBeCompleted();
    }

    private void thenShouldBeCompleted() {
        result
                .test().assertComplete();

    }

    private void whenNewAlbumAdded() {
        result = action.execute(albumDTO);
    }

    private void givenAnAction() {
        action = new AddAlbum(repository);
    }

    private void givenAnRepository() {
        repository = mock(Albums.class);
        when(repository.put(album)).thenReturn(Completable.complete());
    }

    private void giveAnAlbum() {
        album = new Album.Builder()
                .withId(idAlbum.toString())
                .withArtists(ARTISTS)
                .withReleaseDate(RELEASE_DATE)
                .withTitle(TITLE)
                .withTracks(TRACKS)
                .withType(TYPE)
                .build();
        albumDTO = AlbumDTO.buildFrom(album);
    }

}
