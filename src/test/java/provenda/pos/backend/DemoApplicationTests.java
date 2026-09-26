package provenda.pos.backend;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Full application context test. Requires a running MySQL instance (see README),
 * so it is tagged as an integration test and excluded from the default CI build.
 */
@Tag("integration")
@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
