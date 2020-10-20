import com.nxastudios.acetato.core.action.DeleteTrack;
import com.nxastudios.acetato.core.domain.Tracks;
import io.reactivex.Completable;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteTrackTest {
    private Tracks tracks;
    private DeleteTrack action;
    private String idTrack = "111";
    private Completable result;

    @Test
    public void getExistsTrackThenShouldReturnTrackSuccessfully() {
        //given
        givenAnMockTrackRepository();
        givenGetTrackAction();

        //when
        whenGetTrackExecute();

        //then
        thenGetAnTrackSuccessfully();
    }

    private void thenGetAnTrackSuccessfully() {
        result.test().assertComplete();
    }

    private void whenGetTrackExecute() {
        result = action.execute(idTrack);
    }

    private void givenGetTrackAction() {
        action = new DeleteTrack(tracks);
    }

    private void givenAnMockTrackRepository() {
        tracks = mock(Tracks.class);
        when(tracks.remove(idTrack)).thenReturn(Completable.complete());
    }
}
