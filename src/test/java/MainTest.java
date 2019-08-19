import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

public class MainTest {

    static Game game;
    static ByteArrayOutputStream out;
    static PrintStream ps;
    static PrintStream stdout;

    @BeforeClass
    public static void setup(){
        game = spy(new Game());
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
    }

    @Test
    public void must_be_four_numbers(){
        String[] data = {"1234", "0284"};

        for( String d : data){
            boolean isNumber = game.isFourDigits(d);
            assertTrue(isNumber);
        }
    }

    @Test
    public void should_fail_if_invalid_numbers() {
        String[] data = {"abcd", ";[];", "12367923659"};

        for( String d : data){
            boolean isNumber = game.isFourDigits(d);
            assertFalse(isNumber);
        }
    }

    @Test
    public void should_have_one_digit_is_correct_number(){
        String guess = "1234";
        String answer = "5167";

        int correctNumberCount = game.correctNumberCount(guess, answer);


        assertEquals(correctNumberCount, 1);
    }

    @Test
    public void should_return_four_digits_that_is_not_repeated(){
        String data = game.generateRandomFourNumbers();

        assertEquals(data.length(), 4);

        Set<Character> buckets = new HashSet<>();

        for( char d: data.toCharArray()){
            buckets.add(d);
        }

        System.out.println(data);

        assertEquals(buckets.size(), 4);

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

        assertEquals("abcd is not in numeric format or incorrect length.", out.toString().trim());
    }

    @Test
    public void should_give_hints_when_guess_is_incorrect() {

        doNothing().when(game).displayWelcomeMessage();
        doNothing().when(game).displayWinningMessage();

        doReturn("2345").when(game).generateRandomFourNumbers();
        doReturn("1234").when(game).promptForUserGuess();
        doReturn(true).when(game).hasGuessCorrectly();

        game.start();

        assertEquals("0A3B", out.toString().trim());
    }

    @Test
    public void should_win_the_game_when_guess_correctly(){
        doNothing().when(game).displayWelcomeMessage();

        doReturn("1234").when(game).generateRandomFourNumbers();
        doReturn("1234").when(game).promptForUserGuess();

        game.start();

        verify(game.hasGuessCorrectly());

        assertEquals("4A4B\nYou won!", out.toString().trim());
    }

}


