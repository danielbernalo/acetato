import com.nxastudios.acetato.core.action.AddArtist;
import com.nxastudios.acetato.core.domain.*;
import com.nxastudios.acetato.core.infrastructure.services.converter.ArtistDTO;
import io.reactivex.Completable;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddArtistTest {
    private static final String NAME = "anArtist";
    private static final ArtistId ID_ARTIST = new ArtistId("1A");
    private Artist artist;
    private AddArtist action;
    private Artists repository;
    private Completable result;
    private ArtistDTO artistDTO;

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
        when(repository.put(artist)).thenReturn(Completable.complete());
    }

    private void giveAnArtist(ArtistId idArtist, String name) {
        artist = new Artist.Builder()
                .withId(idArtist.toString())
                .withName(name)
                .build();

        artistDTO =  ArtistDTO.buildFrom(artist);
    }

}
