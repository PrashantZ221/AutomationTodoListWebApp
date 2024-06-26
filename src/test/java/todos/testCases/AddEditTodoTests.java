package todos.testCases;

import base.BaseTest;
import constants.Constants;
import listener.ScreenshotOnFailure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AddTodoPage;
import pages.ListedTodosPage;

@Listeners(ScreenshotOnFailure.class)

public class AddEditTodoTests extends BaseTest {
    AddTodoPage addTodoPage;
    ListedTodosPage listedTodosPage;

    @BeforeMethod
    public void setup() {
        initialization();
        addTodoPage = new AddTodoPage(driver);
        listedTodosPage = new ListedTodosPage(driver);
    }

    @Test(testName = "Verify the todo gets added when we enter todo in the box and press enter")
    public void verifyTodoIsAdded() {
        addTodoPage.addTodo(Constants.todoGoToMarket);
        addTodoPage.verifyTodoPresent(Constants.todoGoToMarket);
    }

    @Test(testName = "Verify that todo should not be editable unless user double clicks on it")
    public void verifyTodoIsNotEditableUnlessUserDoubleClicks() {
        addTodoPage.addTodo(Constants.todo30MinutesYoga);
        addTodoPage.singleClickAndVerifyNotEditable(Constants.todo30MinutesYoga);
    }

    @Test(testName = "Verify todo name should not accept blank value or space.")
    public void verifyTodoNameCannotBeBlank() {
        addTodoPage.addTodo("   ");
        addTodoPage.verifyTodoNotPresent("   ");
        addTodoPage.addTodo("");
        addTodoPage.verifyTodoNotPresent("");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
