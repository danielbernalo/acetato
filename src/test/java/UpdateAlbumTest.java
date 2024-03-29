import com.nxastudios.acetato.core.action.UpdateAlbum;
import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.infrastructure.services.converter.AlbumDTO;
import io.reactivex.Completable;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateAlbumTest {
    private static final AlbumId ID_ALBUM = new AlbumId("123-abc-456");
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
    private AlbumDTO albumDTO;

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

    private void thenShouldBeCompleted() {
        result.test().assertComplete();
    }

    private void whenNewAlbumAdded() {
        result = action.execute(albumDTO);
    }

    private void givenAnAction() {
        action = new UpdateAlbum(repository);
    }

    private void givenAnRepository() {
        repository = mock(Albums.class);
        when(repository.put(album)).thenReturn(Completable.complete());
    }

    private void giveAnAlbumFrom(AlbumId idAlbum, String title, Long releaseDate, List<Artist> artists, List<Track> tracks, AlbumType type) {
        album = new Album.Builder()
                .withId(idAlbum.toString())
                .withTitle(title)
                .withReleaseDate(releaseDate)
                .withType(type)
                .build();
        albumDTO = AlbumDTO.buildFrom(album);
    }

}
