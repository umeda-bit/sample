public class Sample5_3 {
    public static void main(String[] args) throws Exception {
        int[] x ={1,2,3,4,5};
        String[] y={"あ","い","う","え","お"};
        for(int i=0;i<x.length;i++){
            System.out.println(x[i]);
        }
        for(int i=0;i<y.length;i++){
            System.out.println(y[i]);
        }
        for(int i : x){
            System.out.println(i);
        }
    }
}
