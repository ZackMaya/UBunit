package edu.uacm.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.operation.DeleteAllOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.uacm.Application;
import edu.uacm.domain.Vehiculo;
import edu.uacm.domain.VehiculoRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
public class VehiculoTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
	@Before
	public void configurar() throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		
		Connection conn = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(conn);
		DatabaseConfig dbConfig = connection.getConfig();
	    dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
	    
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet dataSetVehiculo = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "vehiculos-dataset/vehiculo.xml"));
		IDataSet dataSetAccesorio = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
		+ "vehiculos-dataset/accesorio.xml"));
		DeleteAllOperation.DELETE_ALL.execute(connection, dataSetAccesorio);
		DeleteAllOperation.DELETE_ALL.execute(connection, dataSetVehiculo);
		DatabaseOperation.INSERT.execute(connection, dataSetVehiculo);

	}
	
    @After
	public void finalizar() throws SQLException, DatabaseUnitException, MalformedURLException {
		Connection conn = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(conn);
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet dataSetVehiculo = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "vehiculos-dataset/vehiculo.xml"));
		IDataSet dataSetAccesorio = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
		    + "vehiculos-dataset/accesorio.xml"));
		DeleteAllOperation.DELETE_ALL.execute(connection, dataSetAccesorio);
		DeleteAllOperation.DELETE_ALL.execute(connection, dataSetVehiculo);
		
		
	}
	
	@Test
	@Rollback(false)
	public void probarActualizacionVehiculo() {
		log.debug("Entrando a probarActualizacionVehiculo...");
		String datoPruebaModelo = "2027";
		
		Vehiculo vehiculo = vehiculoRepository.findOne(new Long(1));
		
		vehiculo.setModelo(datoPruebaModelo);
		
		vehiculoRepository.save(vehiculo);
		
		Assert.assertEquals(vehiculo.getModelo(), datoPruebaModelo);
	
	}
	
	
	@Test
	public void probarTotalRegistros() {
		
		List<Vehiculo> listaVehiculos = (List<Vehiculo>) vehiculoRepository.findAll();
		Assert.assertTrue(listaVehiculos.size() == 6);
		
		
	}
	
}
