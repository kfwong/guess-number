import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.*;

public class MainTest {

    static Game game;

    @BeforeClass
    public static void setup(){
        game = new Game();
    }

    @Test
    public void must_be_four_numbers(){
        String[] data = {"1234", "0284"};

        for( String d : data){
            boolean isNumber = game.isNumber(d);
            assertTrue(isNumber);
        }
    }

    @Test
    public void should_fail_if_invalid_numbers() {
        String[] data = {"abcd", ";[];", "12367923659"};

        for( String d : data){
            boolean isNumber = game.isNumber(d);
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

}


