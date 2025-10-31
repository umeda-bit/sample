public class Sample5_2 {
    public static void main(String[] args) throws Exception {
        int i=0;
        boolean b=true;
        while(i<5){
            System.out.println(i);
            i++;
        }
        while(i<50){
            System.out.println(i);
            i=i*2;
        }
        int j=0;
        while(b){
            System.out.println("Hello");
            j++;
            if(j==2){
                b=false;
            }
        }
    }
}
