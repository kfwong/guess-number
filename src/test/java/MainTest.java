import org.junit.BeforeClass;
import org.junit.Test;

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
    public void should_have_one_digit_in_correct_position(){
        String data = "1234";
        String answer = "5167";

        int correctNumberCount = game.correctNumberCount(data, answer);


        assertEquals(correctNumberCount, 1);
    }
}


