import com.nxastudios.acetato.core.action.GetTrack;
import com.nxastudios.acetato.core.domain.Tracks;
import com.nxastudios.acetato.core.domain.Track;
import io.reactivex.Single;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetTrackTest {
    private Track track;
    private Tracks tracksRepository;
    private GetTrack action;
    private String idTrack = "1";
    private Single<Track> result;

    @Test
    public void getExistsTrackThenShouldReturnTrackSuccessfully() {
        //given
        givenNewTrackFrom(idTrack);
        givenAnMockTrackRepository();
        givenGetTrackAction();

        //when
        whenGetTrackExecute();

        //then
        thenGetAnTrackSuccefully();
    }

    private void thenGetAnTrackSuccefully() {
        result.test().assertValue(it -> it.getIdTrack().equals(idTrack));
    }

    private void whenGetTrackExecute() {
        result = action.execute(idTrack);
    }

    private void givenGetTrackAction() {
        action = new GetTrack(tracksRepository);

    }

    private void givenAnMockTrackRepository() {
        tracksRepository = mock(Tracks.class);
        when(tracksRepository.getOne(idTrack)).thenReturn(Single.just(track));
    }

    private void givenNewTrackFrom(String idTrack) {
        track = new Track.Builder().withIdTrack(idTrack).build();
    }
}



