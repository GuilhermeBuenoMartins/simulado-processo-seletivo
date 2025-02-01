package br.exemplo.controle_pedido_api.view.contoller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.exemplo.controle_pedido_api.control.dto.PedidoDTO;
import br.exemplo.controle_pedido_api.control.service.PedidoService;
import br.exemplo.controle_pedido_api.view.request.PedidoRequest;
import br.exemplo.controle_pedido_api.view.response.PedidoDetalhadoResponse;
import br.exemplo.controle_pedido_api.view.response.PedidoResponse;

@Controller
@RequestMapping("/api/v1/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    private ResponseEntity<List<PedidoResponse>> findAll() {
        List<PedidoDTO> dtos = service.findAll();
        ModelMapper mapper = new ModelMapper();
        List<PedidoResponse> responses = dtos.stream().map(
                dto -> mapper.map(dto, PedidoResponse.class)).toList();
        return new ResponseEntity<List<PedidoResponse>>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<PedidoDTO> optional = service.findById(id);
        if (optional.isPresent()) {
            PedidoDetalhadoResponse response = new ModelMapper().map(optional.get(), PedidoDetalhadoResponse.class);
            return new ResponseEntity<PedidoDetalhadoResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> insert(@RequestBody PedidoRequest request) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        PedidoDTO dto = mapper.map(request, PedidoDTO.class);;
        Optional<PedidoDTO> optional = service.insert(dto);
        if (optional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PedidoDetalhadoResponse response = mapper.map(dto, PedidoDetalhadoResponse.class);;
        return new ResponseEntity<PedidoDetalhadoResponse>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
        public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PedidoRequest request) {
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            PedidoDTO dto = mapper.map(request, PedidoDTO.class);
            dto.setId(id);
            Optional<PedidoDTO> optional = service.update(dto);
            if (optional.isPresent()) {
                PedidoDetalhadoResponse response = mapper.map(optional.get(), 
                    PedidoDetalhadoResponse.class);
                return new ResponseEntity<PedidoDetalhadoResponse>(response, HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
     
    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
