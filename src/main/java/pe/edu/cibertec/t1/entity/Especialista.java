package pe.edu.cibertec.t1.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the especialista database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Especialista.findAll", query="SELECT e FROM Especialista e")
public class Especialista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_especialista")
	private int idEspecialista;

	private String correo;

	private String dni;

	private String especialidad;

	@Column(name="nombre_completo")
	private String nombreCompleto;

	//bi-directional many-to-one association to Certificacion
	@OneToMany(mappedBy="especialista")
	private List<Certificacion> certificacions;

	public Especialista() {
	}

	public Certificacion addCertificacion(Certificacion certificacion) {
		getCertificacions().add(certificacion);
		certificacion.setEspecialista(this);

		return certificacion;
	}

	public Certificacion removeCertificacion(Certificacion certificacion) {
		getCertificacions().remove(certificacion);
		certificacion.setEspecialista(null);

		return certificacion;
	}

}