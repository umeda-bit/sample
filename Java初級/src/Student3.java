public class Student3 {
    private String name = "スクー太郎";
    private int age = 20;
    private int grade = 2;
    private int rank = 1;

    public Student3(String name, int age, int grade, int rank) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    void introduction() {
        System.out.println("私の名前は" + name + "です");
        System.out.println("私の年齢は" + age + "歳です");
        System.out.println("私の学年は"+grade+"です");
        System.out.println("私の順位は"+rank+"です");
    }

    void ageCalculation() {
        age++;
        System.out.println("私は" + age + "歳になりました");
    }
}
