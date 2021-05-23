import com.github.javafaker.Faker;

public class Setup {
    private String uniqueIsbn;
    private String uniqueAisle;

    public void generateUniqueID() {
        Faker faker = new Faker();
        uniqueIsbn = faker.number().digits(6);
        uniqueAisle = faker.number().digits(6);
    }

    public String getIsbn() {
        return uniqueIsbn;
    }

    public String getAisle() {
        return uniqueAisle;
    }


}
