import com.nxastudios.acetato.core.action.AddAlbum;
import com.nxastudios.acetato.core.action.UpdateAlbum;
import com.nxastudios.acetato.core.domain.*;
import io.reactivex.Completable;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateAlbumTest {
    private static final String ID_ALBUM = "123";
    private static final String TITLE = "anAlbum";
    private static final Long RELEASE_DATE = 1L;
    private static final Artist ARTIST = giveNewArtist();
    private static final List<Artist> ARTISTS = asList(ARTIST);
    private static final Track TRACK = giveNewTrack();
    private static final List<Track> TRACKS = asList(TRACK);
    private static final AlbumType TYPE = AlbumType.ALBUM;
    private Album album;
    private UpdateAlbum action;
    private Albums repository;
    private Completable result;

    @Test
    public void albumIsAddedThenShouldBeSuccessfully() {
        //given
        giveAnAlbumFrom(ID_ALBUM, TITLE, RELEASE_DATE, ARTISTS, TRACKS, TYPE);
        givenAnRepository();
        givenAnAction();

        //when
        whenNewAlbumAdded();

        //then
        thenShouldBeCompleted();
    }

    private static Track giveNewTrack() {
        return new Track.Builder()
                .withTitle("Nice job bob!")
                .build();
    }

    private static Artist giveNewArtist() {
        return new Artist.Builder()
                .withName("bob")
                .build();
    }

    private void thenShouldBeCompleted() {
        result.test().assertComplete();
    }

    private void whenNewAlbumAdded() {
        result = action.execute(album);
    }

    private void givenAnAction() {
        action = new UpdateAlbum(repository);
    }

    private void givenAnRepository() {
        repository = mock(Albums.class);
        when(repository.put(album)).thenReturn(Completable.complete());
    }

    private void giveAnAlbumFrom(String idAlbum, String title, Long releaseDate, List<Artist> artists, List<Track> tracks, AlbumType type) {
        album = new Album.Builder()
                .withId(idAlbum)
                .withTitle(title)
                .withArtists(artists)
                .withReleaseDate(releaseDate)
                .withTracks(tracks)
                .withType(type)
                .builder();
    }

}
