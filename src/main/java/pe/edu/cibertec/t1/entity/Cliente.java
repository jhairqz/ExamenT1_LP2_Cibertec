package pe.edu.cibertec.t1.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente")
	private int idCliente;

	private String correo;

	@Column(name="nombre_empresa")
	private String nombreEmpresa;

	private String ruc;

	//bi-directional many-to-one association to Certificacion
	@OneToMany(mappedBy="cliente")
	private List<Certificacion> certificacions;

	public Cliente() {
	}

}