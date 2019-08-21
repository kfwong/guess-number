import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class GameTest {

    static Game game;
    static Utils utils;
    static ByteArrayOutputStream out;
    static PrintStream ps;
    static PrintStream stdout;

    @BeforeClass
    public static void setup(){
        utils = spy(new Utils());
        game = spy(new Game(utils));
        stdout = System.out;
    }

    @Before
    public void setupEach(){
        out = new ByteArrayOutputStream();
        ps = new PrintStream(out);
        System.setOut(ps);
    }

    @After
    public void afterEach() {
        System.setOut(stdout);
        reset(game);
        reset(utils);
    }


    @Test
    public void should_have_one_digit_is_correct_number(){
        String guess = "1234";
        String answer = "5167";

        int correctNumberCount = game.correctNumberCount(guess, answer);


        assertEquals(correctNumberCount, 1);
    }

    @Test
    public void should_have_four_correct_number_and_position(){
        String guess = "1234";
        String answer = "1234";

        int correctNumberAndPostion = game.correctNumberAndPosition(guess, answer);

        assertEquals(4, correctNumberAndPostion);
    }

    @Test
    public void should_have_two_correct_number_and_position() {
        String guess = "1234";
        String answer = "1290";

        int correctNumberAndPosition = game.correctNumberAndPosition(guess, answer);

        assertEquals(2, correctNumberAndPosition);
    }

    @Test
    public void should_prompt_welcome_message() throws Exception {

        game.displayWelcomeMessage();

        assertEquals("Welcome!", out.toString("UTF-8").trim());
    }

    @Test
    public void should_fail_for_non_numberic_input() {

        doNothing().when(game).displayWelcomeMessage();
        doNothing().when(game).displayWinningMessage();

        doReturn("abcd").when(game).promptForUserGuess();
        doReturn(true).when(game).hasGuessCorrectly();

        game.start();

        assertEquals("abcd is not in numeric format, incorrect length or has repeated digits.", out.toString().trim());
    }

    @Test
    public void should_fail_for_non_unique_input() {

        doNothing().when(game).displayWelcomeMessage();
        doNothing().when(game).displayWinningMessage();

        doReturn("1111").when(game).promptForUserGuess();
        doReturn(true).when(game).hasGuessCorrectly();

        game.start();

        assertEquals("1111 is not in numeric format, incorrect length or has repeated digits.", out.toString().trim());
    }

    @Test
    public void should_give_hints_when_guess_is_incorrect() {

        doNothing().when(game).displayWelcomeMessage();
        doNothing().when(game).displayWinningMessage();

        doReturn("2345").when(utils).generateRandomFourNumbers();
        doReturn("1234").when(game).promptForUserGuess();
        doReturn(true).when(game).hasGuessCorrectly();

        game.start();

        assertEquals("0A3B", out.toString().trim());
    }

    @Test
    public void should_win_the_game_when_guess_correctly(){
        doNothing().when(game).displayWelcomeMessage();

        doReturn("1234").when(utils).generateRandomFourNumbers();
        doReturn("1234").when(game).promptForUserGuess();

        game.start();

        assertTrue(game.hasGuessCorrectly());

        assertEquals("4A4B\nYou won!", out.toString().trim());
    }

}


