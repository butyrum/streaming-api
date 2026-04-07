package com.ebac.streaming_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/streaming")
public class StreamingController {

    @Autowired
    private StreamingService streamingService;

    @PostMapping("/{usuarioId}/assistir/{filmeId}")
    public String assistir(@PathVariable Long usuarioId, @PathVariable Long filmeId) {
        return streamingService.assistir(usuarioId, filmeId);
    }

    @GetMapping("/{usuarioId}/historico")
    public List<Historico> verHistorico(@PathVariable Long usuarioId) {
        return streamingService.buscarHistorico(usuarioId);
    }
}