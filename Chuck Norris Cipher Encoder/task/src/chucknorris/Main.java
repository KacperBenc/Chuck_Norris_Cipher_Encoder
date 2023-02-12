package chucknorris;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String option = "";
        while(!option.equals("exit")){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please input operation (encode/decode/exit):");
            option=scanner.nextLine();
            if(option.equals("encode")){
                System.out.println("Input string:");
                String input = scanner.nextLine();
                char[]symbols = new char[input.length()];
                String sentence ="";
                for(int i = 0;i<input.length();i++){
                    symbols[i]=input.charAt(i);
                }
                System.out.println("Encoded string:");
                for (int i = 0; i <input.length() ; i++) {
                    sentence += toBinary(symbols[i]);
                    //System.out.printf("%c = %s\n", symbols[i], toBinary(symbols[i]));
                }
                System.out.println(toChuckNorrisCode(sentence));
            }
            else if (option.equals("decode")) {
                try{
                    System.out.println("Input encoded string:");
                    String input = scanner.nextLine();
                    String[] codeParts = input.split(" ");
                    if(codeParts.length%2!=0||!(codeParts[0].equals("0")||codeParts[0].equals("00"))||(input.length()%7==0)){

                        throw new Exception();
                    }
                    for(int i = 0;i<codeParts.length;i++){
                        for(int j=0;j<codeParts[i].length();j++){
                            if(codeParts[i].charAt(j)!='0'){
                                throw new Exception();
                            }
                        }
                    }
                    System.out.println("Decoded string:");
                    System.out.println(fromBinaryToLetters(fromChuckToBinary(codeParts)));
                }
                catch (Exception e){
                    System.out.println("Encoded string is not valid.\n");
                }
            }
            else if (option.equals("exit")) {
                System.out.println("Bye!");
                continue;
            }
            else{
                System.out.printf("There is no '%s' operation\n",option);
            }
        }
      
    }
    public static String fromBinaryToLetters(String binary){
        String[] letters=binary.split("(?<=\\G.{" + 7 + "})");
        String codedMassage ="";
        for(String letter:letters){
            int asciiNumber = Integer.parseInt(letter,2);
            char singleLetter = (char)asciiNumber;
            codedMassage+=singleLetter;
        }
        return codedMassage;
    }
    public static String fromChuckToBinary(String[] codeParts){
        String BinaryCode = "";
        for(int i = 0; i<codeParts.length;i=i+2){
            if(codeParts[i].equals("0")){
                for(int j = 0;j< codeParts[i+1].length();j++){
                    BinaryCode+="1";
                }
            }
            else {
                for(int j = 0;j< codeParts[i+1].length();j++){
                    BinaryCode+="0";
                }}
        }
        return BinaryCode;
    }
    public static String toBinary(char symbol){
        String binary = Integer.toBinaryString(symbol);
               return ("0000000" + binary).substring(binary.length());
    }
    public static String toChuckNorrisCode(String binary){
        String chuckNorrisCode="";
        int indexNow=0;
        while(indexNow!=binary.length()-1){
            int counter = 1;
            if(binary.charAt(indexNow)=='1'){
                chuckNorrisCode+="0 ";
                for(int i =indexNow+1;i<binary.length();i++){
                    if(binary.charAt(i-1)==binary.charAt(i)){
                        counter++;
                        if(i==binary.length()-1){
                            indexNow=i;
                            for(int j = 0;j<counter;j++){
                                chuckNorrisCode+="0";
                            }
                            break;
                        }
                    }
                    else {
                        indexNow=i;
                        for(int j = 0;j<counter;j++){
                            chuckNorrisCode+="0";
                        }
                        chuckNorrisCode+=" ";
                        if(indexNow==binary.length()-1 && binary.charAt(indexNow)!=binary.charAt(indexNow-1)){
                            chuckNorrisCode+="00 0";
                        }
                        break;
                    }
                }
                continue;
            }
            if(binary.charAt(indexNow)=='0'){
                chuckNorrisCode+="00 ";
                for(int i =indexNow+1;i<binary.length();i++){
                    if(binary.charAt(i-1)==binary.charAt(i)){
                        counter++;
                        if(i==binary.length()-1){
                            indexNow=i;
                            for(int j = 0;j<counter;j++){
                                chuckNorrisCode+="0";
                            }
                            break;
                        }
                    }
                    else {
                        indexNow=i;
                        for(int j = 0;j<counter;j++){
                            chuckNorrisCode+="0";
                        }
                        chuckNorrisCode+=" ";
                        if(indexNow==binary.length()-1 && binary.charAt(indexNow)!=binary.charAt(indexNow-1)){
                            chuckNorrisCode+="0 0";
                        }
                        break;
                    }
                }
            continue;
            }
        }
        return chuckNorrisCode;
    }
}