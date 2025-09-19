package org.shop.beautyportal.saleschannels.architecture;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import java.lang.annotation.Annotation;

class HexagonAccessTest {

    JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.shop.beautyportal.saleschannels");

    @ParameterizedTest(name = "{2}")
    @MethodSource("placementRules")
    @DisplayName("Architectural placement rules")
    void annotatedClassesShouldResideInCorrectPackage(
            Class<? extends Annotation> annotation,
            String expectedPackage,
            String displayName
    ) {
        ArchRule rule = classes()
                .that().areAnnotatedWith(annotation)
                .should().resideInAPackage(expectedPackage);

        rule.check(importedClasses);
    }

    static Stream<Arguments> placementRules() {
        return Stream.of(
                Arguments.of(jakarta.persistence.Entity.class, "..domain.entities..", "@Entity classes should reside in domain.entities")
        );
    }
}
