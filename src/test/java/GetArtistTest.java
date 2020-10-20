import com.nxastudios.acetato.core.action.GetArtist;
import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Single;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetArtistTest {
    private Artist artist;
    private Artists artistsRepository;
    private GetArtist action;
    private String idArtist = "1";
    private Single<Artist> result;

    @Test
    public void getExistsArtistThenShouldReturnArtistSuccessfully() {
        //given
        givenNewArtistFrom(idArtist);
        givenAnMockArtistRepository();
        givenGetArtistAction();

        //when
        whenGetArtistExecute();

        //then
        thenGetAnArtistSuccessfully();
    }

    private void thenGetAnArtistSuccessfully() {
        result.test().assertValue(it -> it.getIdArtist().equals(idArtist));
    }

    private void whenGetArtistExecute() {
        result = action.execute(idArtist);
    }

    private void givenGetArtistAction() {
        action = new GetArtist(artistsRepository);
    }

    private void givenAnMockArtistRepository() {
        artistsRepository = mock(Artists.class);
        when(artistsRepository.getOne(idArtist)).thenReturn(Single.just(artist));
    }

    private void givenNewArtistFrom(String idArtist) {
        artist = new Artist.Builder().withId(idArtist).build();
    }
}



