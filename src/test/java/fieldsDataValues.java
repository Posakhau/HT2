public class fieldsDataValues {

    private String usernameFieldName = "username";
    private String passwordFieldName = "password1";
    private String confirmPasswordFiledName = "password2";
    private String fullnameFieldname = "fullname";
    private String emailFieldname = "email";

    /*
    («Username» = «someuser»,
     «Password» = «somepassword»,
     «Confirm password» = «somepassword»,
     «Full name» = «Some Full Name»,
     «E-mail address» = «some@addr.dom»)
     и клика по кнопке с надписью «Create User»
     на странице появляется строка таблицы (элемент tr),
     в которой есть ячейка (элемент td) с текстом «someuser».
     */


    private String usernameValue = "someuser";
    private String passwordFieldValue ="somepassword";
    private String confirmPasswordFiledValue ="somepassword";
    private String fullnameFieldValue ="Some Full Name";
    private String emailFieldnameValue ="some@addr.dom";



    public String getUsernameFieldName() {
        return usernameFieldName;
    }

    public String getPasswordFieldName() {
        return passwordFieldName;
    }

    public String getConfirmPasswordFiledName() {
        return confirmPasswordFiledName;
    }

    public String getFullnameFieldname() {
        return fullnameFieldname;
    }

    public String getEmailFieldname() {
        return emailFieldname;
    }
    public String getUsernameValue() {
        return usernameValue;
    }

    public String getPasswordFieldValue() {
        return passwordFieldValue;
    }

    public String getConfirmPasswordFiledValue() {
        return confirmPasswordFiledValue;
    }

    public String getFullnameFieldValue() {
        return fullnameFieldValue;
    }

    public String getEmailFieldnameValue() {
        return emailFieldnameValue;
    }

}
