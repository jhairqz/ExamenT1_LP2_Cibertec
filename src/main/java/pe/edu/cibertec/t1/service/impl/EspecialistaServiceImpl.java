package pe.edu.cibertec.t1.service.impl;

import pe.edu.cibertec.t1.dao.EspecialistaDAO;
import pe.edu.cibertec.t1.entity.Especialista;
import pe.edu.cibertec.t1.service.EspecialistaService;

public class EspecialistaServiceImpl extends GenericServiceImpl<Especialista, Integer> implements EspecialistaService {

    public EspecialistaServiceImpl(EspecialistaDAO especialistaDAO) {
        super(especialistaDAO);
    }
}
