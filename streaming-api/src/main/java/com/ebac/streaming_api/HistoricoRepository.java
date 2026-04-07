package com.ebac.streaming_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    List<Historico> findByUsuarioIdOrderByDataVisualizacaoDesc(Long usuarioId);
}