package pages;

import base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddTodoPage {

    public WebDriver driver;
    public AddTodoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "todo-input")
    WebElement todoTextBox;

    protected WebElement todoItem(String todoName) {
        return driver.findElement(By.xpath("//label[@data-testid='todo-item-label'][contains(text(), '" + todoName + "')]"));
    }

    private WebElement editTodo(String todoName) {
        return driver.findElement(By.xpath("//div[@class='view']//input[@id='todo-input'][@value='" + todoName + "']"));
    }

    @Step("Add a todo with name {0}")
    public void addTodo(String todoName) {
        todoTextBox.sendKeys(todoName);
        todoTextBox.sendKeys(Keys.ENTER);
    }

    @Step("Verify that a todo {0} is present")
    public void verifyTodoPresent(String todoName) {
        Assert.assertTrue(todoItem(todoName).isDisplayed());
    }

    @Step("Verify that a todo {0} is not present")
    public void verifyTodoNotPresent(String todoName) {
        try {
            Assert.assertFalse(todoItem(todoName).isDisplayed());
        }
        catch (Exception e) {
        }
    }

    @Step("Verify that todo is not editable with single click for todo {0}")
    public void singleClickAndVerifyNotEditable(String todoName) {
        todoItem(todoName).click();
        try {
            Assert.assertFalse(editTodo(todoName).isDisplayed());
        }
        catch (Exception e){

        }
        Actions act = new Actions(driver);
        act.doubleClick(todoItem(todoName)).build().perform();
        Assert.assertTrue(editTodo(todoName).isDisplayed());
    }

}
