package edu.uacm.test.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.uacm.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
//es aplication edu.uacm
public class ExtraccionTest {
		private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	DataSource dataSource;
	
	
	@Before
	//setUp
	public void inicial() throws FileNotFoundException, SQLException, DatabaseUnitException, IOException{
		//el dataSource es el que reegresara una conexion a la base de datos
		log.debug("entrada a inicial");
		DBUnitData.extraerDatasetsUnoxUno(dataSource.getConnection());
	
		
		
	}
	
	@Test
	public void prueba1(){
		
		
		
	}

}
