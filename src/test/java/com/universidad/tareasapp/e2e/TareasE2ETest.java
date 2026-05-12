package com.universidad.tareasapp.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TareasE2ETest {

    private WebDriver driver;
    private TareasPage tareasPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(opts);
        driver.get("http://localhost:8080/tareas");
        tareasPage = new TareasPage(driver);
    }

    @Test
    void paginaTareas_cargaCorrectamente() {
        assertThat(driver.getTitle()).contains("Tareas");
    }

    @Test
    void paginaTareas_listaTareasVisible() {
        assertThat(tareasPage.contarTareas()).isGreaterThanOrEqualTo(0);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}