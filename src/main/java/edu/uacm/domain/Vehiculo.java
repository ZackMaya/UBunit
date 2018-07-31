package edu.uacm.domain;

//import org.hibernate.annotations.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="vehiculo")

public class Vehiculo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@NotNull
	private String modelo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	

}
