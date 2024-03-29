import com.nxastudios.acetato.core.action.AddTrack;
import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.infrastructure.services.converter.TrackDTO;
import io.reactivex.Completable;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddTrackTest {
    private static final String TITLE = "Nice job bob!";
    private static final Long DURATION = 1L;
    private static final Integer DISC_NUMBER = 1;
    private static final Integer TRACK_NUMBER = 1;
    private static final Artist ARTIST = giveNewArtist();
    private static final List<Artist> ARTISTS = asList(ARTIST);
    private static final Album ALBUM = givenNewAlbum();
    private static final TrackId ID_TRACK = new TrackId("11-11");
    private Track track;
    private TrackDTO trackDTO;
    private AddTrack action;
    private Tracks repository;
    private Completable result;

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

    @Test
    public void trackIsAddedThenShouldBeSuccessfully() {
        //given
        giveAnTrack(ID_TRACK.toString(), ARTISTS, ALBUM, DISC_NUMBER, TITLE, TRACK_NUMBER, DURATION);
        givenAnRepository();
        givenAnAction();

        //when
        whenNewTrackAdded();

        //then
        thenShouldBeCompleted();
    }

    private void thenShouldBeCompleted() {
        result.test().assertComplete();
    }

    private void whenNewTrackAdded() {
        result = action.execute(trackDTO);
    }

    private void givenAnAction() {
        action = new AddTrack(repository);
    }

    private void givenAnRepository() {
        repository = mock(Tracks.class);
        when(repository.put(track)).thenReturn(Completable.complete());
    }

    private void giveAnTrack(String trackId, List<Artist> artists, Album album, Integer discNumber, String title, Integer trackNumber, Long duration) {
        track = new Track.Builder()
                .WithDiscNumber(discNumber)
                .withTitle(title)
                .withDuration(duration)
                .withTrackNumber(trackNumber)
                .withIdTrack(trackId)
                .build();
        trackDTO = TrackDTO.buildFrom(track);
    }
}
