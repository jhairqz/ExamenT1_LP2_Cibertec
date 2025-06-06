package pe.edu.cibertec.t1.entity;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Date;


/**
 * The persistent class for the certificacion database table.
 * 
 */
@Data
@Entity
@NamedQuery(name="Certificacion.findAll", query="SELECT c FROM Certificacion c")
public class Certificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_certificacion")
	private int idCertificacion;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_emision")
	private Date fechaEmision;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	//bi-directional many-to-one association to Especialista
	@ManyToOne
	@JoinColumn(name="id_especialista")
	private Especialista especialista;

	//bi-directional many-to-one association to Tipoauditoria
	@ManyToOne
	@JoinColumn(name="id_tipo_auditoria")
	private Tipoauditoria tipoauditoria;

	public Certificacion() {
	}

}