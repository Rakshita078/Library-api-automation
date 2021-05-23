import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Actions {
    public Response addBook(String url, String postRequestBody) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(postRequestBody)
                .when()
                .post(url)
                .then().statusCode(200).log().all().extract().response();
        return response;

    }

    public Response getBookById(String getUrl, String id) {
        Response response = given()
                .get(getUrl + id)
                .then()
                .statusCode(200)
                .log()
                .all()
                .extract().response();
        return response;
    }


    public Response deleteBook(String deleteUrl, String id) {
        String deleteId = "{\n" +
                "    \"ID\":" + id + "\n" +
                "}";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(deleteId)
                .when()
                .delete(deleteUrl)
                .then()
                .statusCode(200).log().all()
                .extract()
                .response();
        return response;
    }


}
