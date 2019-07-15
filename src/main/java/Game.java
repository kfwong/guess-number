import java.io.InputStream;
import java.io.OutputStream;
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
        Random random = new Random();

        Set<Integer> buckets = new HashSet<>();

        do{
            buckets.add(random.nextInt(9));
        }while(buckets.size() < 4);

        String result = "";

        for (int integer: buckets){
            result += integer;
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
