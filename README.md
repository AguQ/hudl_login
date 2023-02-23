# Automation UI flow with Selenium, Java and TestNG.

Test Suite covering mostly critical scenarios.
- Verify User Login
- Verify UserLogin With Invalid Credentials (with several data inputs)
- Verify Org Login With Invalid Credentials
- Verify the 'Login With Email and Password' button
- Verify Need Help Option
- Verify Login Button redirect to /login in HudlPage
- Organization login scenario was skipped due to the lag of credentials.

This implementation is based in **Page Object Model (POM).**

## Dependencies
- Java 1.8
- Selenium
- TestNG
- Tinylog
- Maven
- Commons

## Run a Test
To prepare and run the test suite use the following commands in the terminal. Pass user's email and password as properties
```agsl
mvn install -DskipTests
mvn test -Demail=<user-email> -Dpassword=<user-password>
```