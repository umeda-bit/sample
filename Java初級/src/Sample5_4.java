public class Sample5_4 {
    public static void main(String[] args) throws Exception {
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.println("■");
            }
            System.out.println("●");
        }
        boolean b=true;
        int i=0;
        while(b){
            if(i==6){
                break;
            }
            System.out.println("iの値は"+i);
            i++;
        }
        int[] x={1,2,3,4,5,6,7,8,9,10};
        for(int y : x){
            if(y%2==0){
                System.out.println("配列の値は"+y);
            }
        }
    }
}
