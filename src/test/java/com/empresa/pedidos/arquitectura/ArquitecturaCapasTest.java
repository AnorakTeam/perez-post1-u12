package com.empresa.pedidos.arquitectura;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.empresa.pedidos", importOptions = ImportOption.DoNotIncludeTests.class)
class ArquitecturaCapasTest {

    @ArchTest
    static final ArchRule aplicacionNoDependeDeInfraestructuraNiAdaptadores = noClasses()
            .that().resideInAPackage("..aplicacion..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("..infraestructura..", "..adaptadores..");

    @ArchTest
    static final ArchRule dominioNoDependeDeSpring = noClasses()
            .that().resideInAPackage("..dominio..")
            .should().dependOnClassesThat()
            .resideInAPackage("org.springframework..");
}
