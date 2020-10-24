import com.nxastudios.acetato.core.action.UpdateTrack;
import com.nxastudios.acetato.core.domain.*;
import io.reactivex.Completable;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateTrackTest {
    private static final String TITLE = "Nice job bob!";
    private static final Long DURATION = 1L;
    private static final Integer DISC_NUMBER = 1;
    private static final Integer TRACK_NUMBER = 1;
    private static final Artist ARTIST = giveNewArtist();
    private static final List<Artist> ARTISTS = asList(ARTIST);
    private static final Album ALBUM = givenNewAlbum();
    private static final TrackId ID_TRACK = new TrackId("11-1");
    private Track track;
    private UpdateTrack action;
    private Tracks repository;
    private Completable result;

    @Test
    public void trackIsAddedThenShouldBeSuccessfully() {
        //given
        giveAnTrack(ID_TRACK, ARTISTS, ALBUM, DISC_NUMBER, TITLE, TRACK_NUMBER, DURATION);
        givenAnRepository();
        givenAnAction();

        //when
        whenNewTrackAdded();

        //then
        thenShouldBeCompleted();
    }

    private static Album givenNewAlbum() {
        return new Album.Builder()
                .withTitle("anAlbum")
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

    private void whenNewTrackAdded() {
        result = action.execute(track);
    }

    private void givenAnAction() {
        action = new UpdateTrack(repository);
    }

    private void givenAnRepository() {
        repository = mock(Tracks.class);
        when(repository.put(track)).thenReturn(Completable.complete());
    }

    private void giveAnTrack(TrackId trackId, List<Artist> artists, Album album, Integer discNumber, String title, Integer trackNumber, Long duration) {
        track = new Track.Builder()
                .withArtists(artists)
                .withAlbum(album)
                .withDiscNumber(discNumber)
                .withTitle(title)
                .withDuration(duration)
                .withTrackNumber(trackNumber)
                .withIdTrack(trackId)
                .build();
    }

}
