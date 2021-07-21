package User;

import com.allan.amca.user.UserFactory;
import com.allan.amca.user.UserFactoryGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserTest {
    UserFactory userFactory = new UserFactoryGenerator();


    @Given("User opens the ATM application")
    public void user_opens_the_atm_application() {
        System.err.println("Not implemented, yet. user_opens_atm_app");
    }
    @Given("User opens create account")
    public void user_opens_create_account() {
        System.err.println("Not implemented, yet. user_opens_create_account");
    }
    @When("I enter client ID as {string} and I enter password as {string}")
    public void i_enter_an_invalid_client_id_as(final String arg1, final String arg2) {
//        User user = userFactory.CreateUser(UserType.USER, arg1, arg1, arg2);
    }

    @Then("Registration should be successful")
    public void registration_should_be_successful() {
        assert true;
    }
}
