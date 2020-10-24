import com.nxastudios.acetato.core.action.AddArtist;
import com.nxastudios.acetato.core.action.UpdateArtist;
import com.nxastudios.acetato.core.domain.Artist;
import com.nxastudios.acetato.core.domain.ArtistId;
import com.nxastudios.acetato.core.domain.Artists;
import io.reactivex.Completable;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UpdateArtistTest {
    private static final String NAME = "anArtist";
    private static final ArtistId ID_ARTIST = new ArtistId("1A");
    private Artist artist;
    private UpdateArtist action;
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
        result = action.execute(artist);
    }

    private void givenAnAction() {
        action = new UpdateArtist(repository);
    }

    private void givenAnRepository() {
        repository = mock(Artists.class);
        when(repository.put(artist)).thenReturn(Completable.complete());
    }

    private void giveAnArtist(ArtistId artistId, String name) {
        artist = new Artist.Builder()
                .withId(artistId.get())
                .withName(name)
                .build();
    }

}
