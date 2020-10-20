import com.nxastudios.acetato.core.action.GetAlbum;
import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Single;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetAlbumTest {
    private Album album;
    private Albums albumsRepository;
    private GetAlbum action;
    private String idAlbum = "1";
    private Single<Album> result;

    @Test
    public void getExistsAlbumThenShouldReturnAlbumSuccessfully() {
        //given
        givenNewAlbumFrom(idAlbum);
        givenAnMockAlbumRepository();
        givenGetAlbumAction();

        //when
        whenGetAlbumExecute();

        //then
        thenGetAnAlbumSuccefully();
    }

    private void thenGetAnAlbumSuccefully() {
        result.test().assertValue(it -> it.getIdAlbum().equals(idAlbum));
    }

    private void whenGetAlbumExecute() {
        result = action.execute(idAlbum);
    }

    private void givenGetAlbumAction() {
        action = new GetAlbum(albumsRepository);
    }

    private void givenAnMockAlbumRepository() {
        albumsRepository = mock(Albums.class);
        when(albumsRepository.getOne(idAlbum)).thenReturn(Single.just(album));
    }

    private void givenNewAlbumFrom(String idAlbum) {
        album = new Album.Builder().withId(idAlbum).builder();
    }
}



