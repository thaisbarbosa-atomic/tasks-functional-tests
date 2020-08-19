package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Tarefa via Selenium [2]");
		driver.findElement(By.id("dueDate")).sendKeys("21/08/2020");
		driver.findElement(By.id("saveButton")).click();
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);
		}
		finally {
		driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Tarefa via Selenium [3]");
		driver.findElement(By.id("dueDate")).sendKeys("21/08/2010");
		driver.findElement(By.id("saveButton")).click();
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
		}
		finally {
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefasSemDescricao() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("dueDate")).sendKeys("21/08/2020");
		driver.findElement(By.id("saveButton")).click();
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);
		}
		finally {
		driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefasSemData() {
		
		WebDriver driver = acessarAplicacao();
		
		try {
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Tarefa via Selenium [4]");
		driver.findElement(By.id("saveButton")).click();
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
		}
		finally {
		driver.quit();
		}
	}
}
