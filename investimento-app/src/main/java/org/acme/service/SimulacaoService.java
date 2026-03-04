package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Simulacao;

import java.util.List;

@ApplicationScoped
public class SimulacaoService {


    public List<Simulacao> retornaTodasSimulacoes(){
        return Simulacao.getAll();
    }

    public List<Simulacao> retornaSimulacaoPorClientId(Integer clienteId){
        return Simulacao.findByClienteId(clienteId);
    }

}
