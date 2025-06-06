package pe.edu.cibertec.t1.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.List;


/**
 * The persistent class for the tipoauditoria database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Tipoauditoria.findAll", query="SELECT t FROM Tipoauditoria t")
public class Tipoauditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo_auditoria")
	private int idTipoAuditoria;

	private String descripcion;

	//bi-directional many-to-one association to Certificacion
	@OneToMany(mappedBy="tipoauditoria")
	private List<Certificacion> certificacions;

	public Tipoauditoria() {
	}

	public Certificacion addCertificacion(Certificacion certificacion) {
		getCertificacions().add(certificacion);
		certificacion.setTipoauditoria(this);

		return certificacion;
	}

	public Certificacion removeCertificacion(Certificacion certificacion) {
		getCertificacions().remove(certificacion);
		certificacion.setTipoauditoria(null);

		return certificacion;
	}

}