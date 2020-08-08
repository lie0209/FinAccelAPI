package Common.setup;

import GithubTest.Steps.*;
import Common.utilities.TestListener;
import com.aventstack.extentreports.ExtentTest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class DriverScript {

    public static JSONObject addRepoRequest=null;

    //Init PageObject
    protected static TestListener test = new TestListener();
    protected static addRepoSteps addAccomodationRommSteps = new addRepoSteps();

    public static JSONParser jparser=null;
    public static ExtentTest report = null;


    //load api structure DataTest//BodyRequestTemplate
    static {
        jparser = new JSONParser();

        try {
            addRepoRequest = (JSONObject) jparser.parse(new FileReader(System.getProperty("user.dir") + "//src//test//java//GithubTest//DataTest//addRepoRequest.json"));
            } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    //load api server location
    public static String GitHubEndPoint= "https://api.github.com";

    //load api endpoint
    public static String addNewRepo(){
        return GitHubEndPoint+"/user/repos";
    }


}
