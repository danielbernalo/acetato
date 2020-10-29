import com.nxastudios.acetato.core.action.GetTrack;
import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.TrackId;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Single;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTrackTest {
    private Track track;
    private Tracks tracksRepository;
    private GetTrack action;
    private TrackId trackId = new TrackId("1-123");
    private Single<Track> result;

    @Test
    public void getExistsTrackThenShouldReturnTrackSuccessfully() {
        //given
        givenNewTrackFrom(trackId);
        givenAnMockTrackRepository();
        givenGetTrackAction();

        //when
        whenGetTrackExecute();

        //then
        thenGetAnTrackSuccessfully();
    }

    private void thenGetAnTrackSuccessfully() {
        result.test().assertValue(it -> it.getTrackId().equals(trackId.toString()));
    }

    private void whenGetTrackExecute() {
        result = action.execute(trackId.toString());
    }

    private void givenGetTrackAction() {
        action = new GetTrack(tracksRepository);

    }

    private void givenAnMockTrackRepository() {
        tracksRepository = mock(Tracks.class);
        when(tracksRepository.getOne(trackId)).thenReturn(Single.just(track));
    }

    private void givenNewTrackFrom(TrackId trackId) {
        track = new Track.Builder().withIdTrack(trackId.toString()).build();
    }
}



