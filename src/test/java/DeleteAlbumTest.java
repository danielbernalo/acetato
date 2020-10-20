import com.nxastudios.acetato.core.action.DeleteAlbum;
import com.nxastudios.acetato.core.action.GetAlbum;
import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteAlbumTest {
    private Albums albumsRepository;
    private DeleteAlbum action;
    private String idAlbum = "1";
    private Completable result;

    @Test
    public void getExistsAlbumThenShouldReturnAlbumSuccessfully() {
        //given
        givenAnMockAlbumRepository();
        givenGetAlbumAction();

        //when
        whenGetAlbumExecute();

        //then
        thenGetAnAlbumSuccessfully();
    }

    private void thenGetAnAlbumSuccessfully() {
        result.test().assertComplete();
    }

    private void whenGetAlbumExecute() {
        result = action.execute(idAlbum);
    }

    private void givenGetAlbumAction() {
        action = new DeleteAlbum(albumsRepository);
    }

    private void givenAnMockAlbumRepository() {
        albumsRepository = mock(Albums.class);
        when(albumsRepository.remove(idAlbum)).thenReturn(Completable.complete());
    }
}



