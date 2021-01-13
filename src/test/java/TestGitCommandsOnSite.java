import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Test to check the teacher Vadim.:
 */
public class TestGitCommandsOnSite {
    WebDriver driver;
    String Add, Status, Diff, Commit;

    @BeforeTest
    public void Before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


    }

    @Test
    public void Test() {
        driver.get("https://git-scm.com/book/ru/v2/%D0%9F%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5-C%3A-%D0%9A%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D1%8B-Git-%D0%9E%D1%81%D0%BD%D0%BE%D0%B2%D0%BD%D1%8B%D0%B5-%D0%BA%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D1%8B");

        Add = GitAdd().getText();
        Assert.assertEquals("git add", Add);
        System.out.println(Add + "-команда git add добавляет содержимое рабочей директории в индекс (staging area) для последующего коммита. ");
        Status = GitStatus().getText();
        Assert.assertEquals("git status", Status);
        System.out.println(Status + "-команда git status показывает состояния файлов в рабочей директории и индексе: какие файлы изменены, но не добавлены в индекс; какие ожидают коммита в индексе.");
        Diff = GitDiff().getText();
        Assert.assertEquals("git diff", Diff);
        System.out.println(Diff + "-команда git diff используется для вычисления разницы между любыми двумя Git деревьями.");
        Commit = GitCommit().getText();
        Assert.assertEquals("git commit", Commit);
        System.out.println(Commit + "-команда git commit берёт все данные, добавленные в индекс с помощью git add, и сохраняет их слепок во внутренней базе данных, а затем сдвигает указатель текущей ветки на этот слепок.");
    }


    private WebElement GitAdd() {
        return driver.findElement(By.xpath("//h3[@id='_git_add']"));
    }

    private WebElement GitStatus() {
        return driver.findElement(By.xpath("//h3[@id='_git_status']"));
    }

    private WebElement GitDiff() {
        return driver.findElement(By.xpath("//h3[@id='_git_diff']"));
    }

    private WebElement GitCommit() {
        return driver.findElement(By.xpath("  //h3[@id='_git_commit']"));
    }

}



