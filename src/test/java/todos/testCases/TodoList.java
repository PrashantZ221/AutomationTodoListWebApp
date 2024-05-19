package todos.testCases;

import base.BaseTest;
import org.testng.annotations.*;
import pages.AddTodoPage;
import pages.ListedTodosPage;

public class TodoList extends BaseTest {

    AddTodoPage addTodoPage;
    ListedTodosPage listedTodosPage;

    @BeforeMethod
    public void setup() {
        initialization();
        addTodoPage = new AddTodoPage();
        listedTodosPage = new ListedTodosPage();
    }

    @Test(testName = "Verify the todo gets completed when user clicks on radio button")
    public void verifyTodoIsCompleted() {
        addTodoPage.addTodo("Go to Gym");
        listedTodosPage.completeTodo("Go to Gym");
        listedTodosPage.verifyTodoIsCompleted("Go to Gym");
    }

    @Test(testName = "Verify that all todos are displayed (Active and completed) when user selects 'All' option")
    public void verifyAllTodos() {
        addTodoPage.addTodo("Breakfast");
        addTodoPage.addTodo("Lunch");
        listedTodosPage.completeTodo("Breakfast");
        listedTodosPage.selectAllTodo("Breakfast", "Lunch");
    }

    @Test(testName = "Verify all todos radio button are selected when user click on select all icon")
    public void verifyAllTodosAreSelected() {
        addTodoPage.addTodo("Breakfast");
        addTodoPage.addTodo("Lunch");
        listedTodosPage.selectAllCheckbox();
        listedTodosPage.verifyTodoIsCompleted("Breakfast");
        listedTodosPage.verifyTodoIsCompleted("Lunch");
    }

    @Test(testName = "Verify that by clicking on Clear completed button, only the completed todos should be cleared and active todos should not get cleared")
    public void verifyClearCompletedButton() {
        addTodoPage.addTodo("Breakfast");
        addTodoPage.addTodo("Lunch");
        listedTodosPage.completeTodo("Breakfast");
        listedTodosPage.clickListAllTodosButton();
        listedTodosPage.clickClearCompletedButton();
        addTodoPage.verifyTodoPresent("Lunch");
        addTodoPage.verifyTodoNotPresent("Breakfast");
    }

    @Test(testName = "Verify that the items left count should not include the completed todos count")
    public void verifyItemsLeftCountDoesNotIncludeCompletedTodos() {
        addTodoPage.addTodo("Breakfast");
        addTodoPage.addTodo("Lunch");
        listedTodosPage.verifyItemsLeftCount("2");
        listedTodosPage.completeTodo("Breakfast");
        listedTodosPage.verifyItemsLeftCount("1");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.close();
    }
}
