import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Delayed;

import static org.testng.Assert.fail;

public class primaryTaskBundle_Test {

    boolean authorizationSucess = false;
    AuthorizeDataContainer authorizeDataContainer = new AuthorizeDataContainer();
    constantTestConditions testConditions = new constantTestConditions();
    fieldsDataValues fieldsData = new fieldsDataValues();
    ArrayList<WebElement>fields = new ArrayList<WebElement>();
    private WebDriver driver;

    /* SERVICE METHODS */

    public boolean detectByTagName_Info(String tagName, String searchInfo, WebDriver driver) {
        int p=0;
        List<WebElement>itemsList = driver.findElements(By.tagName(tagName));

        for (int i=0;i<itemsList.size();++i) {
            System.out.println(itemsList.get(i).getText());
            if (itemsList.get(i).getText().equals(searchInfo)) {
             p++;
            }
        }
        if (p>0) {return true;} else {
            return false;
        }
    }

    public void clickThis(String tagName, String info, WebDriver driver) {
        List<WebElement>itemsList = driver.findElements(By.tagName(tagName));
        for (int i=0;i<itemsList.size();++i) {
            if (itemsList.get(i).getText().equals(info)) {
                itemsList.get(i).click(); break;
            }
        }
    }

    public boolean isFieldEmpty (WebElement item) {
        if (item.getText().equals("")) {
            return true;
        }
        return false;
    }

    @BeforeTest
    public void startAuthorizationProcess(){
        driver = new FirefoxDriver();
        driver.get(authorizeDataContainer.getMainURL());
        System.out.println("AUTHORIZATION STARTED");

        // AUTHORIZATION PROCESS

        String usernameValue = driver.findElement(By.id(authorizeDataContainer.getUserNameField_ID())).getText();

        if (usernameValue.equals(authorizeDataContainer.getLoginUserFieldValueByDefault())) // we have authorization page
        {
            driver.findElement(By.id(authorizeDataContainer.getUserNameField_ID())).sendKeys(authorizeDataContainer.getUsername());
            driver.findElement(By.xpath(authorizeDataContainer.getPasswordField_XPath())).sendKeys(authorizeDataContainer.getPassword());
            driver.findElement(By.xpath(authorizeDataContainer.getSubmitKey_XPath())).click();
        } else {
            clickThis(authorizeDataContainer.getLoginTag(),authorizeDataContainer.getLoginValue(),driver);
            driver.findElement(By.id(authorizeDataContainer.getUserNameField_ID())).sendKeys(authorizeDataContainer.getUsername());
            driver.findElement(By.xpath(authorizeDataContainer.getPasswordField_XPath())).sendKeys(authorizeDataContainer.getPassword());
            driver.findElement(By.xpath(authorizeDataContainer.getSubmitKey_XPath())).click();
        }
         String result = driver.findElement(By.xpath(authorizeDataContainer.getLoggedUserName_XPath())).getText();

         if(result.equals(authorizeDataContainer.getUsername())) {
             authorizationSucess = true;
         } else {
             authorizationSucess = false;
         }
        Assert.assertTrue(authorizationSucess);
    }

    @Test (priority = 1)
    /*
    1.	После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом
        «Manage Users» и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
    */
    public void firstTestTask() {
        if (authorizationSucess) {
            clickThis(testConditions.getTest1_tag0(),testConditions.getTest1_info0(),driver);
            Assert.assertEquals(true,detectByTagName_Info(testConditions.getTest1_tag1(),testConditions.getTest1_info1(),driver));
            Assert.assertEquals(true,detectByTagName_Info(testConditions.getTest1_tag2(),testConditions.getTest1_info2(),driver));
        } else {
            fail("Test 1 reports - Authorization process has been unsuccessful...");
        }
    }

    @Test (priority = 2)
    /*
    2.	После клика по ссылке, внутри которой содержится элемент dt
     с текстом «Manage Users», становится доступна ссылка «Create User».
     */
    public void secondTestTask() {
        if (authorizationSucess) {
            List<WebElement>itemsList = driver.findElements(By.tagName(testConditions.getTest2_tag1()));
            for (int i=0;i<itemsList.size();++i) {
                if (itemsList.get(i).getText().equals(testConditions.getTest2_info1())) {
                   itemsList.get(i).click(); break;
                }
            }
            Assert.assertEquals(true,detectByTagName_Info(testConditions.getTest2_tag2(),testConditions.getTest2_info2(),driver));

        } else {
            fail("Test 2 reports -Authorization process has been unsuccessful...");
        }
    }

    @Test (priority = 3)
    /*
    3.	После клика по ссылке «Create User» появляется форма с тремя полями
     типа text и двумя полями типа password, причём все поля должны быть пустыми.
     */
    public void thirdTestTask() {
        if (authorizationSucess) {
            int inputTextFieldsAmount = 0;
            int inputTextPasswordFieldsAmount = 0;
            int emptyFieldsAmount =0;
            boolean mainTest3Condition = false;

            clickThis(testConditions.getTest3_tag1(),testConditions.getTest3_info1(),driver);

            List<WebElement>inputTextElements = driver.findElements(By.tagName(testConditions.getTest3_tag2()));

            for (WebElement item : inputTextElements) {
                if (item.getAttribute(testConditions.getTest3_attribute1()).equals(testConditions.getTest3_fieldName1())
                        ||item.getAttribute(testConditions.getTest3_attribute1()).equals(testConditions.getTest3_fieldName2())
                        ||item.getAttribute(testConditions.getTest3_attribute1()).equals(testConditions.getTest3_fieldName3())
                        ||item.getAttribute(testConditions.getTest3_attribute1()).equals(testConditions.getTest3_fieldName4())
                        ||item.getAttribute(testConditions.getTest3_attribute1()).equals(testConditions.getTest3_fieldName5())
                        ) {
                    if (item.getAttribute(testConditions.getTest3_info0()).equals(testConditions.getTest3_info2())) {
                        inputTextFieldsAmount++;
                        if(!isFieldEmpty(item)){
                        emptyFieldsAmount++;
                        }
                        fields.add(item);
                    }
                    if (item.getAttribute(testConditions.getTest3_info0()).equals(testConditions.getTest3_info3())) {
                        inputTextPasswordFieldsAmount++;
                        if(!isFieldEmpty(item)){
                            emptyFieldsAmount++;
                        }
                        fields.add(item);
                    }
                }
            }
            if (inputTextFieldsAmount==testConditions.getTest3_summ1()
                    &&inputTextPasswordFieldsAmount==testConditions.getTest3_summ2()
                    &&emptyFieldsAmount==0) {
                mainTest3Condition = true;
            }
            Assert.assertEquals(true,mainTest3Condition);
        } else {
            fail("Test 3 reports - Authorization process has been unsuccessful...");
        }
    }

    /*
    4.	После заполнения полей формы
     («Username» = «someuser»,
      «Password» = «somepassword»,
      «Confirm password» = «somepassword»,
       «Full name» = «Some Full Name»,
        «E-mail address» = «some@addr.dom»)
         и клика по кнопке с надписью «Create User»
          на странице появляется строка таблицы (элемент tr), в которой есть ячейка (элемент td) с текстом «someuser».
     */
    @Test(priority = 4)
    public void fourTestTask() {
        if (authorizationSucess) {
                for(int i=0;i<fields.size();++i) {

                    if (fields.get(i).getAttribute("name").equals(fieldsData.getUsernameFieldName())) {
                        fields.get(i).sendKeys(fieldsData.getUsernameValue());
                    }
                    if (fields.get(i).getAttribute("name").equals(fieldsData.getPasswordFieldName())) {
                        fields.get(i).sendKeys(fieldsData.getPasswordFieldValue());
                    }
                    if (fields.get(i).getAttribute("name").equals(fieldsData.getConfirmPasswordFiledName())) {
                        fields.get(i).sendKeys(fieldsData.getConfirmPasswordFiledValue());
                    }
                    if (fields.get(i).getAttribute("name").equals(fieldsData.getFullnameFieldname())) {
                        fields.get(i).sendKeys(fieldsData.getFullnameFieldValue());
                    }
                    if (fields.get(i).getAttribute("name").equals(fieldsData.getEmailFieldname())) {
                        fields.get(i).sendKeys(fieldsData.getEmailFieldnameValue());
                    }
        }
            clickThis(testConditions.getTest4_tag1(),testConditions.getTest4_value1(),driver);

            List<WebElement>tableElementsList = driver.findElements(By.tagName(testConditions.getTest4_tag2()));

            boolean test4mainCondition = false;

            for (int i=0;i<tableElementsList.size();++i) {
                List<WebElement>tdElements = tableElementsList.get(i).findElements(By.tagName(testConditions.getTest4_tag3()));
                 for (int j=0;j<tdElements.size();++j) {
                     if (tdElements.get(j).getText().equals(testConditions.getTest4_value2())) {
                         test4mainCondition=true;
                     }
                 }
            }
            Assert.assertEquals(true,test4mainCondition);
        } else {
            fail("Test 4 reports - Authorization process has been unsuccessful...");
        }
    }

    /*
    5.	После клика по ссылке с атрибутом href равным «user/someuser/delete»
    появляется текст «Are you sure about deleting the user from Jenkins?».
     */
    @Test(priority = 5)
    public void fiveTestTask() {
        if (authorizationSucess) {
            driver.findElement(By.xpath("//a[@" + testConditions.getTest5_attr1() + "='" + testConditions.getTest5_value1() + "']")).click();
            Assert.assertEquals(true, driver.getPageSource().contains(testConditions.getTest5_value2()));
        } else {
            fail("Test 5 reports - Authorization process has been unsuccessful...");
        }
    }

    /*
    6.	После клика по кнопке с надписью «Yes» на странице отсутствует
     строка таблицы (элемент tr), с ячейкой (элемент td) с текстом «someuser».
      На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete»
     */
    @Test(priority=6)
    public void sixTestTask(){

        if (authorizationSucess) {

            clickThis("button","Yes",driver);

            List<WebElement>tableElementsList = driver.findElements(By.tagName(testConditions.getTest4_tag2()));
            boolean test6mainCondition = true;

            for (int i=0;i<tableElementsList.size();++i) {
                List<WebElement>tdElements = tableElementsList.get(i).findElements(By.tagName(testConditions.getTest4_tag3()));
                for (int j=0;j<tdElements.size();++j) {
                    if (tdElements.get(j).getText().equals(testConditions.getTest4_value2())) {
                        test6mainCondition=false;
                    }
                }
            }
            Assert.assertEquals(true,test6mainCondition);

            Assert.assertEquals(false, driver.getPageSource().contains(testConditions.getTest5_value2()));

        } else {
            fail("Test 6 reports - Authorization process has been unsuccessful...");
        }
    }

    /*
    7.	{На той же странице, без выполнения каких бы то ни было действий}.
        На странице отсутствует ссылка с атрибутом href равным «user/admin/delete».
     */
    @Test(priority=7)
    public void sevenTestTask() {
        if (authorizationSucess) {
            List<WebElement> links = driver.findElements(By.linkText(testConditions.getTest7_value1()));
            Assert.assertEquals(true, links.size() < 1);
        } else {
            fail("Test 7 reports - Authorization process has been unsuccessful...");
        }
    }

 @AfterTest
    public void shutDown() {
    driver.quit();
 }

} // end of class
