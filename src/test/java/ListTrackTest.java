import com.nxastudios.acetato.core.action.ListTrack;
import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.TrackId;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Single;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTrackTest {
    private Track track;
    private Tracks tracksRepository;
    private TrackId trackId = new TrackId("1-123-13");
    private ListTrack action;
    private Single<List<Track>> result;

    @Test
    public void listTrackSuccessfully() {
        //given
        givenNewTrackFrom(trackId);
        givenTrackRepository();
        givenListTrackAction();

        //when
        whenListTrackExecute();

        //then
        thenListTrackShouldReturnOneWithIdTrack();
    }

    private void givenListTrackAction() {
        action = new ListTrack(tracksRepository);
    }

    private void givenTrackRepository() {
        tracksRepository = mock(Tracks.class);
        when(tracksRepository.list()).thenReturn(Single.just(List.of(track)));
    }

    private void thenListTrackShouldReturnOneWithIdTrack() {
        result.test().assertValue(it -> it.get(0).equals(trackId));
    }

    private void whenListTrackExecute() {
        result = action.execute();
    }

    private void givenNewTrackFrom(TrackId trackId) {
        track = new Track.Builder().withIdTrack(trackId).build();
    }

}

