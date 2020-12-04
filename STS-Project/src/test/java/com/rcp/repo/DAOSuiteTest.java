package com.rcp.repo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	Insert.class,
	Read.class,
	Update.class,
	Delete.class
})
public class DAOSuiteTest {

}
