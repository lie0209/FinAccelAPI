package GithubTest.Steps;

import Common.commonConstants;
import Common.setup.DriverScript;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class addRepoSteps extends DriverScript {

    private Response response;
    private String reqBody;

    Integer id;
    String node_id;
    String name;

    private void constructdefaultRequestBody (){
        reqBody = addRepoRequest.toJSONString();
    }
    public void addRepo() {
        constructdefaultRequestBody();

        response = given()
                .auth().oauth2(commonConstants.authorization)
                .header("Content-Type", commonConstants.contentType)
                .body(reqBody)
                .when()
                .post(addNewRepo());
        test.info("Create template repo ");
        id = response.then().extract().path("id");
        node_id = response.then().extract().path("node_id");
        name = response.then().extract().path("name");
    }

    public void isCreated(){
        response.then().statusCode(201);
        test.pass("API Executed Successfully and Repo Created");
    }

    public void getListRepo() {
        constructdefaultRequestBody();

        response = given()
                .auth().oauth2(commonConstants.authorization)
                .header("Content-Type", commonConstants.contentType)
                .when()
                .get(addNewRepo());
        test.info("Get list repo ");
    }

    public void isRepoCreated(){
        response.then().statusCode(200);
        Assert.assertTrue(response.getBody().asString().contains(String.valueOf(id)));
        Assert.assertTrue(response.getBody().asString().contains(node_id));
        Assert.assertTrue(response.getBody().asString().contains(name));
        test.pass("Repo Successfully created");
    }
}
