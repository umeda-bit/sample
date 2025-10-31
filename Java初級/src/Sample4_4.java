public class Sample4_4 {
    public static void main(String[] args) throws Exception {
        int x,y,z;
        x=3+7;
        if(x>=10){
            System.out.println("10以上です");
        }
        y=27/3;
        if(y>=10){
            System.out.println("10以上です");
        }else if(y<10){
            System.out.println("10未満です");
        }
        z=10%3;
        if(z==0){
            System.out.println("割り算のあまりは0です");
        }else{
            System.out.println("割り算のあまりは"+z+"です");
        }
    }        
}
