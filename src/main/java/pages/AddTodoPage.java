package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import javax.swing.*;
import java.util.List;

public class AddTodoPage extends BaseTest {

    @FindBy(id = "todo-input")
    WebElement todoTextBox;

    public WebElement todoItem(String todoName) {
        return driver.findElement(By.xpath("//label[@data-testid='todo-item-label'][contains(text(), '" + todoName + "')]"));
    }

    public WebElement editTodo(String todoName) {
        return driver.findElement(By.xpath("//div[@class='view']//input[@id='todo-input'][@value='" + todoName + "']"));
    }

    public AddTodoPage() {
        PageFactory.initElements(driver, this);
    }

    public void addTodo(String todoName) {
        todoTextBox.sendKeys(todoName);
        todoTextBox.sendKeys(Keys.ENTER);
    }

    public void verifyTodoPresent(String todoName) {
        Assert.assertTrue(todoItem(todoName).isDisplayed());
    }

    public void verifyTodoNotPresent(String todoName) {
        try {
            Assert.assertFalse(todoItem(todoName).isDisplayed());
        }
        catch (Exception e) {
        }
    }

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
