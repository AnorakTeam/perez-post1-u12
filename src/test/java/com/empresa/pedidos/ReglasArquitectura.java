package com.empresa.pedidos;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.empresa.pedidos")
public class ReglasArquitectura {

    @ArchTest
    static final ArchRule dominioAislado = noClasses()
            .that().resideInAPackage("..dominio..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("..infraestructura..", "..adaptadores..", "javax.persistence..", "org.springframework.mail..");

    @ArchTest
    static final ArchRule controladorSoloFacade = classes()
            .that().resideInAPackage("..adaptadores.rest..")
            .should().onlyAccessClassesThat()
            .resideInAnyPackage("..adaptadores.rest..", "..adaptadores.facade..", "..dominio..", "org.springframework.http..", "org.springframework.web..", "java..");

    @ArchTest
    static final ArchRule puertosComoInterfaces = classes()
            .that().resideInAPackage("..dominio.puertos..")
            .should().beInterfaces();

    @ArchTest
    static final ArchRule procesadoresImplementanPuerto = classes()
            .that().resideInAPackage("..adaptadores.procesadores..")
            .and().haveSimpleNameNotEndingWith("Test")
            .and().haveSimpleNameNotEndingWith("Tests")
            .should().implement(ProcesadorPedido.class);

    @ArchTest
    static final ArchRule infraNoAccedeRest = noClasses()
            .that().resideInAPackage("..infraestructura..")
            .should().accessClassesThat()
            .resideInAnyPackage("..adaptadores.rest..");
}
