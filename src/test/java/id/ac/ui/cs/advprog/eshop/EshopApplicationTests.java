package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


@SpringBootTest
class EshopApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void mainMethodExecuted() {
        EshopApplication.main(new String[] {});
    }
}
