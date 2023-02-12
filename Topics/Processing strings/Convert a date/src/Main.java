import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] dateParts = input.split("-");
        System.out.printf("%s/%s/%s",dateParts[1],dateParts[2],dateParts[0]);

    }
}
