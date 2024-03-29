import com.nxastudios.acetato.core.action.ListArtist;
import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Single;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListArtisTest {
    private Artist artist;
    private Artists artistsRepository;
    private ArtistId artistId = new ArtistId("1-sdf");
    private ListArtist action;
    private Single<List<Artist>> result;

    @Test
    public void listArtistSuccessfully() {
        //given
        givenNewArtistFrom(artistId);
        givenArtistRepository();
        givenListArtistAction();

        //when
        whenListArtistExecute();

        //then
        thenListArtistShouldReturnOneWithIdArtist();
    }

    private void givenListArtistAction() {
        action = new ListArtist(artistsRepository);
    }

    private void givenArtistRepository() {
        artistsRepository = mock(Artists.class);
        when(artistsRepository.list()).thenReturn(Single.just(List.of(artist)));
    }

    private void thenListArtistShouldReturnOneWithIdArtist() {
        result.test().assertValue(it -> it.get(0).getArtistId() == artistId.toString());
    }

    private void whenListArtistExecute() {
        result = action.execute();
    }

    private void givenNewArtistFrom(ArtistId artistId) {
        artist = new Artist.Builder().withId(artistId.toString()).build();
    }

}
