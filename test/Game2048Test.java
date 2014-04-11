import com.indywiz.game.library.Game2048;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by viswanathanr on 4/11/14.
 */
public class Game2048Test extends TestCase {

    Game2048 game2048;

    @Before
    public void setUp() {
        game2048 = new Game2048();
    }

    @Test
    public void testCanary() {
        assertTrue(true);
    }

}
