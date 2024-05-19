package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ListedTodosPage extends BaseTest {

    AddTodoPage addTodoPage = new AddTodoPage();

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

    public WebElement completeTodoRadioButton(String todoName){
        return driver.findElement(By.xpath("//label[contains(text(), '" + todoName + "')]/preceding-sibling::input"));
    }

    public WebElement completedTodo(String todoName) {
        return driver.findElement(By.xpath("//label[contains(text(), '" + todoName + "')]/ancestor::li[@class='completed']"));
    }
    public ListedTodosPage() {
        PageFactory.initElements(driver, this);
    }

    public void completeTodo(String todoName) {
        completeTodoRadioButton(todoName).click();
        completedLink.click();
    }

    public void verifyTodoIsCompleted(String todoName) {
        Assert.assertTrue(completedTodo(todoName).isDisplayed());
    }

    public void selectAllTodo(String firstTodo, String secondTodo) {
        completeTodoRadioButton(firstTodo).click();
        allLink.click();
        Assert.assertTrue(addTodoPage.todoItem(firstTodo).isDisplayed(), "Todo is not present");
        Assert.assertTrue(addTodoPage.todoItem(secondTodo).isDisplayed(), "Todo is not present");
    }

    public void selectAllCheckbox() {
        selectAllCheckbox.click();
    }

    public void clickClearCompletedButton() {
        clearCompletedButton.click();
    }

    public void clickListAllTodosButton() {
        allLink.click();
    }

    public void verifyItemsLeftCount(String count) {
        if(Integer.parseInt(count)==1)
            Assert.assertTrue(itemLeftCount.getText().contains(count + " item left!"), "Items left count is not correct");
        else
            Assert.assertTrue(itemLeftCount.getText().contains(count + " items left!"), "Items left count is not correct");
    }
}
