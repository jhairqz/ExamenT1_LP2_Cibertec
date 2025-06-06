package pe.edu.cibertec.t1.service.impl;

import pe.edu.cibertec.t1.dao.CertificacionDAO;
import pe.edu.cibertec.t1.entity.Certificacion;
import pe.edu.cibertec.t1.service.CertificacionService;

public class CertificacionServiceImpl extends GenericServiceImpl<Certificacion, Integer> implements CertificacionService {

    public CertificacionServiceImpl(CertificacionDAO certificacionDAO) {
        super(certificacionDAO);
    }
}
