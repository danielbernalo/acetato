import com.nxastudios.acetato.core.action.AddArtist;
import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.infrastructure.repositories.services.converter.ArtistDTO;
import io.reactivex.Completable;
import org.junit.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddArtistTest {
    private static final String NAME = "anArtist";
    private static final String ID_ARTIST = "1A";
    private ArtistDTO artistDTO;
    private AddArtist action;
    private Artists repository;
    private Completable result;

    @Test
    public void artistIsAddedThenShouldBeSuccessfully() {
        //given
        giveAnArtist(ID_ARTIST, NAME);
        givenAnRepository();
        givenAnAction();

        //when
        whenNewArtistAdded();

        //then
        thenShouldBeCompleted();
    }

    private void thenShouldBeCompleted() {
        result.test().assertComplete();
    }

    private void whenNewArtistAdded() {
        result = action.execute(artistDTO);
    }

    private void givenAnAction() {
        action = new AddArtist(repository);
    }

    private void givenAnRepository() {
        repository = mock(Artists.class);

        Artist artist = new Artist.Builder().withName(artistDTO.name()).withId(artistDTO.artistId()).build();

        when(repository.put(artist)).thenReturn(Completable.complete());
    }

    private void giveAnArtist(String artistId, String name) {
        artistDTO = new ArtistDTO(artistId, name);
    }

}
