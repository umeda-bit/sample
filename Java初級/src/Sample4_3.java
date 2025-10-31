import java.util.Scanner;

public class Sample4_3 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String str=scan.next();
        if(str.equals("ABC")){
            System.out.println("値はABCです");
        }else if(str.equals("DEF")){
            System.out.println("値はDEFです");
        }else{
            System.out.println("それ以外です");
        }
        scan.close();
    }
}