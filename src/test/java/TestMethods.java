import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestMethods extends Actions {
    public String requestBodyToAddBook;
    public String isbn;
    public String aisle;

    public void setup() {
        RestAssured.baseURI = "http://216.10.245.166";
        Setup uniqueID = new Setup();
        uniqueID.generateUniqueID();
        isbn = uniqueID.getIsbn();
        aisle = uniqueID.getAisle();
        requestBodyToAddBook = "{\n" +
                "\n" +
                "\"name\":\"Python Programming language\",\n" +
                "\"isbn\":\"" + isbn + "\",\n" +
                "\"aisle\":\"" + aisle + "\",\n" +
                "\"author\":\"VanRossum\"\n" +
                "}";
    }

    @Test
    public void testAddBook() {
        setup();
        Response response = addBook("/Library/Addbook.php", requestBodyToAddBook);
        Assert.assertEquals("successfully added", response.jsonPath().getString("Msg"));
    }

    @Test
    public void testGetBookById() {
        setup();
        Response response = addBook("/Library/Addbook.php", requestBodyToAddBook);
        Response output = getBookById("/Library/GetBook.php?ID=", response.jsonPath().getString("ID"));
        Assert.assertEquals(isbn, output.jsonPath().getString("isbn[0]"));
    }

    @Test
    public void testGetBookByAuthorName() {
        setup();
        Response output = getBookById("/Library/GetBook.php?AuthorName=", "Van Rossum");
        Assert.assertEquals("Python Programming language", output.jsonPath().getString("book_name[2]"));
    }

    @Test
    public void testDeleteBookId() {
        setup();
        Response response = addBook("/Library/Addbook.php", requestBodyToAddBook);
        Response output = deleteBook("/Library/DeleteBook.php", response.jsonPath().getString("ID"));
        Assert.assertEquals("book is successfully deleted", output.jsonPath().getString("msg"));

    }

}
