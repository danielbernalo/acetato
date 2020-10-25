import com.nxastudios.acetato.core.action.GetArtist;
import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Single;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetArtistTest {
    private Artist artist;
    private Artists artistsRepository;
    private GetArtist action;
    private ArtistId artistId = new ArtistId("1/a");
    private Single<Artist> result;

    @Test
    public void getExistsArtistThenShouldReturnArtistSuccessfully() {
        //given
        givenNewArtistFrom(artistId.toString());
        givenAnMockArtistRepository();
        givenGetArtistAction();

        //when
        whenGetArtistExecute();

        //then
        thenGetAnArtistSuccessfully();
    }

    private void thenGetAnArtistSuccessfully() {
        result.test().assertValue(it -> it.getArtistId().equals(artistId.toString()));
    }

    private void whenGetArtistExecute() {
        result = action.execute(artistId);
    }

    private void givenGetArtistAction() {
        action = new GetArtist(artistsRepository);
    }

    private void givenAnMockArtistRepository() {
        artistsRepository = mock(Artists.class);
        when(artistsRepository.getOne(artistId)).thenReturn(Single.just(artist));
    }

    private void givenNewArtistFrom(String artistId) {
        artist = new Artist.Builder().withId(artistId).build();
    }
}



