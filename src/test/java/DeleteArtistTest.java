import com.nxastudios.acetato.core.action.DeleteArtist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Completable;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteArtistTest {
    private Artists artistsRepository;
    private DeleteArtist action;
    private ArtistId artistId = new ArtistId("1-324");
    private Completable result;

    @Test
    public void getExistsArtistThenShouldReturnArtistSuccessfully() {
        //given
        givenAnMockArtistRepository();
        givenGetArtistAction();

        //when
        whenGetArtistExecute();

        //then
        thenGetAnArtistSuccessfully();
    }

    private void thenGetAnArtistSuccessfully() {
        result.test().assertComplete();
    }

    private void whenGetArtistExecute() {
        result = action.execute(artistId);
    }

    private void givenGetArtistAction() {
        action = new DeleteArtist(artistsRepository);
    }

    private void givenAnMockArtistRepository() {
        artistsRepository = mock(Artists.class);
        when(artistsRepository.remove(artistId)).thenReturn(Completable.complete());

    }
}



