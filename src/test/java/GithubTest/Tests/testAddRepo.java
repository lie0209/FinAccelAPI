package GithubTest.Tests;

import Common.setup.DriverScript;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Common.utilities.TestListener.class)
public class testAddRepo extends DriverScript {


    @Test
    public void tryAddAccomodationRoomDefault()  throws Exception {
        addAccomodationRommSteps.addRepo();
        addAccomodationRommSteps.isCreated();
        addAccomodationRommSteps.getListRepo();
        addAccomodationRommSteps.isRepoCreated();
    }

}
