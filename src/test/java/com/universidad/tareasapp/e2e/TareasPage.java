package com.universidad.tareasapp.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TareasPage {

    private final WebDriver driver;

    private static final By BTN_NUEVA = By.id("btn-nueva");
    private static final By LIST_ITEMS = By.cssSelector(".tarea-item");

    public TareasPage(WebDriver driver) {
        this.driver = driver;
    }

    public int contarTareas() {
        return driver.findElements(LIST_ITEMS).size();
    }

    public NuevaTareaPage irANuevaTarea() {
        driver.findElement(BTN_NUEVA).click();
        return new NuevaTareaPage(driver);
    }
}