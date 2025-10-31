public class Sample6_1 {
    public static void main(String[] args) throws Exception {
        Student s1 = new Student();
        s1.introduction();
        Student s2 = new Student();
        s2.introduction();
        s2.ageCalculation();
        Student s3 = new Student();
        s3.introduction();
        s3.ageCalculation();
        s3.ageCalculation();
    }
}

class Student {
    String name = "スクー太郎";
    int age = 20, grade = 2, rank = 1;

    void introduction() {
        System.out.println("私の名前は" + name + "です");
        System.out.println("私の年齢は" + age + "歳です");
    }

    void ageCalculation() {
        age++;
        System.out.println("私は" + age + "歳になりました");
    }
}