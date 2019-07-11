public class Game {

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
}
