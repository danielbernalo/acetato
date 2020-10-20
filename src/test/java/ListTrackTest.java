import com.nxastudios.acetato.core.action.ListTrack;
import com.nxastudios.acetato.core.domain.Track;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Single;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTrackTest {
    private Track track;
    private Tracks tracksRepository;
    private String idTrack = "1";
    private ListTrack action;
    private Single<Map<String, Track>> result;

    @Test
    public void listTrackSuccessfully() {
        //given
        givenNewTrackFrom(idTrack);
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
        when(tracksRepository.list()).thenReturn(Single.just(Map.of(idTrack, track)));
    }

    private void thenListTrackShouldReturnOneWithIdTrack() {
        result.test().assertValue(it -> it.containsKey(idTrack));
    }

    private void whenListTrackExecute() {
        result = action.execute();
    }

    private void givenNewTrackFrom(String idTrack) {
        track = new Track.Builder().withIdTrack(idTrack).build();
    }

}

