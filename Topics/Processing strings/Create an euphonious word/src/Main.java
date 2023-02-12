import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        char[] vowels = {'a','e','i','o','u','y'};
        int lettersNeeded = 0;
        int consonantsInaRow = 0;
        int vowelsInaRow = 0;
        for(int i = 0; i<input.length();i++){
            boolean isvowel = false;
            for(int j = 0; j<vowels.length;j++){
                if(input.charAt(i)==vowels[j]){
                    vowelsInaRow++;
                    isvowel = true;
                    if(consonantsInaRow>=3){
                        while(consonantsInaRow>=3){
                            lettersNeeded++;
                            consonantsInaRow-=2;
                        }
                    }
                    consonantsInaRow=0;
                    if(i==input.length()-1){

                        if(vowelsInaRow>=3){
                            while (vowelsInaRow>=3) {
                                lettersNeeded++;
                                vowelsInaRow-=2;
                            }
                        }
                    }
                    break;
                }

            }
            if(!isvowel){
                consonantsInaRow++;
                if(vowelsInaRow>=3){
                    while (vowelsInaRow>=3) {
                        lettersNeeded++;
                        vowelsInaRow-=2;
                    }
                }
                vowelsInaRow=0;
                if(i==input.length()-1){
                    if(consonantsInaRow>=3){
                        while(consonantsInaRow>=3){
                            lettersNeeded++;
                            consonantsInaRow-=2;
                        }
                    }
                }
            }
        }
        System.out.println(lettersNeeded);
    }
}
