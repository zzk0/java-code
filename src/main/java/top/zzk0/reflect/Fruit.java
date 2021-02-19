package top.zzk0.reflect;

@Nullable
public class Fruit {
    private String title;

    public Fruit() {

    }

    public Fruit(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "title='" + title + '\'' +
                '}';
    }
}

@Eatable
class Apple extends Fruit {

}
