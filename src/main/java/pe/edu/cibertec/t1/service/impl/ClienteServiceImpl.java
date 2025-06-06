package pe.edu.cibertec.t1.service.impl;

import pe.edu.cibertec.t1.dao.ClienteDAO;
import pe.edu.cibertec.t1.entity.Cliente;
import pe.edu.cibertec.t1.service.ClienteService;

public class ClienteServiceImpl extends GenericServiceImpl<Cliente, Integer> implements ClienteService {

    public ClienteServiceImpl(ClienteDAO clienteDAO) {
        super(clienteDAO);
    }
}
