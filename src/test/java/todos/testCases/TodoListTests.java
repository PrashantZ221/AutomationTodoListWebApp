package todos.testCases;

import base.BaseTest;
import constants.Constants;
import org.testng.annotations.*;
import pages.AddTodoPage;
import pages.ListedTodosPage;

public class TodoListTests extends BaseTest {

    AddTodoPage addTodoPage;
    ListedTodosPage listedTodosPage;

    @BeforeMethod
    public void setup() {
        initialization();
        addTodoPage = new AddTodoPage(driver);
        listedTodosPage = new ListedTodosPage(driver);
    }

    @Test(testName = "Verify the todo gets completed when user clicks on radio button")
    public void verifyTodoIsCompleted() {
        addTodoPage.addTodo(Constants.todoGoToGym);
        listedTodosPage.completeTodo(Constants.todoGoToGym);
        listedTodosPage.verifyTodoIsCompleted(Constants.todoGoToGym);
    }

    @Test(testName = "Verify that all todos are displayed (Active and completed) when user selects 'All' option")
    public void verifyAllTodos() {
        addTodoPage.addTodo(Constants.todo30MinutesMeditation);
        addTodoPage.addTodo(Constants.todo30MinutesYoga);
        listedTodosPage.completeTodo(Constants.todo30MinutesMeditation);
        listedTodosPage.selectAllTodo(Constants.todo30MinutesMeditation, Constants.todo30MinutesYoga);
    }

    @Test(testName = "Verify all todos radio button are selected when user click on select all icon")
    public void verifyAllTodosAreSelected() {
        addTodoPage.addTodo(Constants.todoReading);
        addTodoPage.addTodo(Constants.todoWalking);
        listedTodosPage.selectAllCheckbox();
        listedTodosPage.verifyTodoIsCompleted(Constants.todoReading);
        listedTodosPage.verifyTodoIsCompleted(Constants.todoWalking);
    }

    @Test(testName = "Verify that by clicking on Clear completed button, only the completed todos should be cleared and active todos should not get cleared")
    public void verifyClearCompletedButton() {
        addTodoPage.addTodo(Constants.todoReading);
        addTodoPage.addTodo(Constants.todoWalking);
        listedTodosPage.completeTodo(Constants.todoReading);
        listedTodosPage.clickListAllTodosButton();
        listedTodosPage.clickClearCompletedButton();
        addTodoPage.verifyTodoPresent(Constants.todoWalking);
        addTodoPage.verifyTodoNotPresent(Constants.todoReading);
    }

    @Test(testName = "Verify that the items left count should not include the completed todos count")
    public void verifyItemsLeftCountDoesNotIncludeCompletedTodos() {
        addTodoPage.addTodo(Constants.todo30MinutesYoga);
        addTodoPage.addTodo(Constants.todo30MinutesMeditation);
        listedTodosPage.verifyItemsLeftCount(Constants.two);
        listedTodosPage.completeTodo(Constants.todo30MinutesYoga);
        listedTodosPage.verifyItemsLeftCount(Constants.one);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
