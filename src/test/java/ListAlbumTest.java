import com.nxastudios.acetato.core.action.ListAlbum;
import com.nxastudios.acetato.core.domain.Album;
import com.nxastudios.acetato.core.domain.AlbumId;
import com.nxastudios.acetato.core.domain.Albums;
import io.reactivex.Single;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListAlbumTest {
    private Album album;
    private Albums albumsRepository;
    private AlbumId idAlbum = new AlbumId("1-b-1");
    private ListAlbum action;
    private Single<List<Album>> result;

    @Test
    public void listAlbumSuccessfully() {
        //given
        givenNewAlbumFrom(idAlbum);
        givenAlbumRepository();
        givenListAlbumAction();

        //when
        whenListAlbumExecute();

        //then
        thenListAlbumShouldReturnOneWithIdAlbum();
    }

    private void givenListAlbumAction() {
        action = new ListAlbum(albumsRepository);
    }

    private void givenAlbumRepository() {
        albumsRepository = mock(Albums.class);
        when(albumsRepository.list()).thenReturn(Single.just(List.of(album)));
    }

    private void thenListAlbumShouldReturnOneWithIdAlbum() {
        result.test().assertValue(it -> it.get(0).getAlbumId().equals(idAlbum));
    }

    private void whenListAlbumExecute() {
        result = action.execute();
    }

    private void givenNewAlbumFrom(AlbumId idAlbum) {
        album = new Album.Builder().withId(idAlbum.toString()).build();
    }

}
