import java.util.Scanner;

public class Sample4_2 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        int x=scan.nextInt();
        if (x >= 100) {
            System.out.println("100以上です");
        } else if (x >= 50 && x < 100) {
            System.out.println("50以上、100未満です");
        }else{
            System.out.println("それ以外です");
        }
        scan.close();

    }
}