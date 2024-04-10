public class Professor extends User {
    private int experience;
    private Course course;

    public Professor(String name, String email, int experience) {
        super(name, email);
        this.experience = experience;
        this.course = null;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Experience: " + experience);
    }
}
