package com.universidad.tareasapp.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NuevaTareaPage {

    private final WebDriver driver;

    private static final By CAMPO_TITULO    = By.id("titulo");
    private static final By CAMPO_DESC      = By.id("descripcion");
    private static final By BTN_GUARDAR     = By.id("btn-guardar");
    private static final By MENSAJE_EXITO   = By.id("mensaje-exito");

    public NuevaTareaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void llenarFormulario(String titulo, String descripcion) {
        driver.findElement(CAMPO_TITULO).sendKeys(titulo);
        driver.findElement(CAMPO_DESC).sendKeys(descripcion);
    }

    public void guardar() {
        driver.findElement(BTN_GUARDAR).click();
    }

    public boolean mensajeExitoVisible() {
        return !driver.findElements(MENSAJE_EXITO).isEmpty();
    }
}