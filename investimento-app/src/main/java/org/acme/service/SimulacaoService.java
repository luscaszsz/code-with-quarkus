package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Simulacao;
import org.jboss.logging.Logger;

import java.util.List;

@ApplicationScoped
public class SimulacaoService {

    private static final Logger LOG = Logger.getLogger(SimulacaoService.class);

    public List<Simulacao> retornaTodasSimulacoes(){
        return Simulacao.getAll();
    }

    public List<Simulacao> retornaSimulacaoPorClientId(Integer clienteId){

        LOG.info("Camada de serviço para busca por cliente id = " + clienteId);

        return Simulacao.findByClienteId(clienteId);
    }

}
