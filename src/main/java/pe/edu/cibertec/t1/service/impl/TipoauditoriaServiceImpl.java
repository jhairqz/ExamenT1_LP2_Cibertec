package pe.edu.cibertec.t1.service.impl;

import pe.edu.cibertec.t1.dao.TipoauditoriaDAO;
import pe.edu.cibertec.t1.entity.Tipoauditoria;
import pe.edu.cibertec.t1.service.TipoauditoriaService;

public class TipoauditoriaServiceImpl extends GenericServiceImpl<Tipoauditoria, Integer> implements TipoauditoriaService {

    public TipoauditoriaServiceImpl(TipoauditoriaDAO tipoauditoriaDAO) {
        super(tipoauditoriaDAO);
    }
}
