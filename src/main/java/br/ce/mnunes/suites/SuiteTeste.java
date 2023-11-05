package br.ce.mnunes.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.mnunes.core.DriverFactory;
import br.ce.mnunes.test.TesteCadastro;
import br.ce.mnunes.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class
})

public class SuiteTeste {

	@AfterClass
	public static void finalizaTudo(){
		DriverFactory.killDriver();
	}
}
