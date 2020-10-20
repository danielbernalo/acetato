import com.nxastudios.acetato.core.action.AddAlbum;
import com.nxastudios.acetato.core.domain.*;
import io.reactivex.Completable;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class AddAlbumTest {
        private static final String TITLE = "anAlbum";
        private static final Long RELEASE_DATE = 1L;
        private static final Artist ARTIST = giveNewArtist();
        private static final List<Artist> ARTISTS = asList(ARTIST);
        private static final Track TRACK = giveNewTrack();
        private static final List<Track> TRACKS = asList(TRACK);
        private static final AlbumType TYPE = AlbumType.ALBUM;
        private Album album;
        private AddAlbum action;
        private Albums repository;
        private Completable result;

        @Test
        public void albumIsAddedThenShouldBeSuccessfully() {
            //given
            giveAnAlbum();
            givenAnRepository();
            givenAnAction();

            //when
            whenNewAlbumAdded();

            //then
            thenShouldBeCompleted();
        }

        private static Track giveNewTrack() {
            return new Track.Builder()
                    .withTitle("Nice job bob!")
                    .build();
        }

        private static Artist giveNewArtist() {
            return new Artist.Builder()
                    .withName("bob")
                    .build();
        }

        private void thenShouldBeCompleted() {
            result.test().assertComplete();
        }

        private void whenNewAlbumAdded() {
            result = action.execute(album);
        }

        private void givenAnAction() {
            action = new AddAlbum(repository);
        }

        private void givenAnRepository() {
            repository = mock(Albums.class);
            when(repository.put(album)).thenReturn(Completable.complete());
        }

        private void giveAnAlbum() {
            album = new Album.Builder()
                    .withArtists(ARTISTS)
                    .withReleaseDate(RELEASE_DATE)
                    .withTitle(TITLE)
                    .withTracks(TRACKS)
                    .withType(TYPE)
                    .build();
        }

}
