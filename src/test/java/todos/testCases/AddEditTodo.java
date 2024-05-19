package todos.testCases;

import base.BaseTest;
import listener.ScreenshotOnFailure;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AddTodoPage;
import pages.ListedTodosPage;

@Listeners(ScreenshotOnFailure.class)

public class AddEditTodo extends BaseTest {
    AddTodoPage addTodoPage;
    ListedTodosPage listedTodosPage;

    @BeforeMethod
    public void setup() {
        initialization();
        addTodoPage = new AddTodoPage();
        listedTodosPage = new ListedTodosPage();
    }

    @Test(testName = "Verify the todo gets added when we enter todo in the box and press enter")
    public void verifyTodoIsAdded() {
        addTodoPage.addTodo("Go to market");
        addTodoPage.verifyTodoPresent("Go to market");
    }

    @Test(testName = "Verify that todo should not be editable unless user double clicks on it")
    public void verifyTodoIsNotEditableUnlessUserDoubleClicks() {
        addTodoPage.addTodo("Breakfast");
        addTodoPage.singleClickAndVerifyNotEditable("Breakfast");
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
