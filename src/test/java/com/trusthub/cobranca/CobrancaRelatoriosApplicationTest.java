package com.trusthub.cobranca;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CobrancaRelatoriosApplicationTest.class)
@TestPropertySource(locations="classpath:test.properties")
public class CobrancaRelatoriosApplicationTest {
	@Test
	public void contextLoads() {
	}
}

