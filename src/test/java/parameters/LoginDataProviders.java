package parameters;

import org.testng.annotations.DataProvider;

public class LoginDataProviders {

    private static final String USER_EMAIL = System.getProperty("email");
    private static final String USER_PASSWORD = System.getProperty("password");

    @DataProvider(name = "LoginCredentials")
    public static Object[][] loginDataProvider() {
        return new Object[][]{{USER_EMAIL, USER_PASSWORD}};
    }

    @DataProvider(name = "InvalidLoginCredentials")
    public static Object[][] invalidLoginDataProvider() {
        return new Object[][]{{"wrongemail@gmail.com", "qwerty12345"}, {"aguq1984@gmail.com", "invalidpassword"}, {"", ""}};
    }

    @DataProvider(name = "InvalidSSOLoginCredentials")
    public static Object[][] invalidSSOLoginDataProvider() {
        return new Object[][]{{"something@email.test"}};
    }

    @DataProvider(name = "ResetPasswordCredentials")
    public static Object[][] resetPasswordDataProvider() {
        return new Object[][]{{"email@domain.com"}};
    }

}
