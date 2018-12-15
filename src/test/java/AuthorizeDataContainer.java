public class AuthorizeDataContainer {

    private String loginTag = "a";
    private String loginValue = "log in";
    private String loginUserFieldValueByDefault = "Username";

    private String username = "testUser";
    private String password = "1111";
    private String userEmail = "testUser@user.com";
    private String mainURL = "http://localhost:8080";

    private String userNameField_ID = "j_username";
    private String passwordField_XPath = "/html/body/div/div/form/div[2]/input";

    private String submitKey_XPath = "/html/body/div/div/form/div[3]/input";

    private String loggedUserName_XPath = "//*[@id=\"header\"]/div[2]/span/a[1]/b";








    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getMainURL() {
        return mainURL;
    }

    public String getUserNameField_ID() {
        return userNameField_ID;
    }


    public String getSubmitKey_XPath() {
        return submitKey_XPath;
    }

    public String getPasswordField_XPath() {
        return passwordField_XPath;
    }

    public String getLoggedUserName_XPath() {
        return loggedUserName_XPath;
    }

    public String getLoginTag() {
        return loginTag;
    }

    public String getLoginValue() {
        return loginValue;
    }

    public String getLoginUserFieldValueByDefault() {
        return loginUserFieldValueByDefault;
    }

}
