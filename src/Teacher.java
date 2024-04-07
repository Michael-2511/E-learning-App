public class Teacher extends User {
    private int experience;

    public Teacher(String name, String email, int experience) {
        super(name, email);
        this.experience = experience;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Experienta: " + experience);
    }
}
