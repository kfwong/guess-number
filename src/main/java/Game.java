import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {

    String answer;

    public void start(InputStream in, OutputStream out){
        /*

        1. System generate a random number
        2. User input a guess number
        3. System check the guess number against the answer
        4. System output XAXB format to the user
        5. repeat steps 2-4 until 4A4B.
         */

        answer = generateRandomFourNumbers();

    }

    public boolean isNumber(String data){
        try {
            Integer.parseInt(data);
            return data.length() == 4;
        }catch(Exception e){
            return false;
        }
    }

    public int correctNumberCount(String data, String answer){
        int count = 0;


        for( char a: answer.toCharArray()){

            if(data.indexOf(a) >= 0){
                count ++;
            };
        }

        return count;
    }

    public String generateRandomFourNumbers() {
        String result = "";
        int[] numbers = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();

        for(int i =0; i < 4; i++ ){
            final int randomlyPickedNumber = numbers[random.nextInt(numbers.length)];
            numbers = Arrays.stream(numbers)
                        .filter( number -> number != randomlyPickedNumber)
                        .toArray();
            result += randomlyPickedNumber;
        }

        return result;
    }

    public int correctNumberAndPosition(String guess, String answer) {
        int count = 0;

        for(int i=0; i<answer.length(); i++){
            if(guess.toCharArray()[i] == answer.toCharArray()[i]){
                count++;
            }
        }

        return count;
    }
}
