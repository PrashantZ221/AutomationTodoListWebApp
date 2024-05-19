package pages;

import base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ListedTodosPage extends AddTodoPage{

    public WebDriver driver;

    public ListedTodosPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Completed")
    WebElement completedLink;

    @FindBy(linkText = "All")
    WebElement allLink;

    @FindBy(className = "toggle-all")
    WebElement selectAllCheckbox;

    @FindBy(className = "clear-completed")
    WebElement clearCompletedButton;

    @FindBy(className = "todo-count")
    WebElement itemLeftCount;

    private WebElement completeTodoRadioButton(String todoName){
        return driver.findElement(By.xpath("//label[contains(text(), '" + todoName + "')]/preceding-sibling::input"));
    }

    private WebElement completedTodo(String todoName) {
        return driver.findElement(By.xpath("//label[contains(text(), '" + todoName + "')]/ancestor::li[@class='completed']"));
    }

    @Step("Complete a todo with name {0}")
    public void completeTodo(String todoName) {
        completeTodoRadioButton(todoName).click();
        completedLink.click();
    }

    @Step("Verify that the todo {0} is completed")
    public void verifyTodoIsCompleted(String todoName) {
        Assert.assertTrue(completedTodo(todoName).isDisplayed());
    }

    @Step("Select and verify that all todos are displayed")
    public void selectAllTodo(String firstTodo, String secondTodo) {
        completeTodoRadioButton(firstTodo).click();
        allLink.click();
        Assert.assertTrue(todoItem(firstTodo).isDisplayed(), "Todo is not present");
        Assert.assertTrue(todoItem(secondTodo).isDisplayed(), "Todo is not present");
    }

    @Step("Select all todos checkbox")
    public void selectAllCheckbox() {
        selectAllCheckbox.click();
    }

    @Step("Click clear completed button")
    public void clickClearCompletedButton() {
        clearCompletedButton.click();
    }

    @Step("Click on List all todos link")
    public void clickListAllTodosButton() {
        allLink.click();
    }

    @Step("Verify that the count of items left is {0}")
    public void verifyItemsLeftCount(String count) {
        if(Integer.parseInt(count)==1)
            Assert.assertTrue(itemLeftCount.getText().contains(count + " item left!"), "Items left count is not correct");
        else
            Assert.assertTrue(itemLeftCount.getText().contains(count + " items left!"), "Items left count is not correct");
    }
}
