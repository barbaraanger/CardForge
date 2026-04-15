package com.barbaraanger.cardforge;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModulithTest {

    @Test
    void verifyModules() {
        ApplicationModules.of(CardforgeApplication.class).verify();
    }
}