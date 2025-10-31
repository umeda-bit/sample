public class School4 {
    public static void main(String[] args) throws Exception {
        Student3 s = new Student3("スクー太郎", 18, 3, 2);
        s.introduction();
        s.ageCalculation();
        s.setGrade(2);
        s.setRank(1);
        s.introduction();
    }
}