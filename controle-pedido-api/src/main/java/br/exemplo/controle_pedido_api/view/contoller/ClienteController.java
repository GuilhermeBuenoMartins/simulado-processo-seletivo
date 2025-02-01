package br.exemplo.controle_pedido_api.view.contoller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import br.exemplo.controle_pedido_api.control.dto.ClienteDTO;
import br.exemplo.controle_pedido_api.control.service.ClienteService;
import br.exemplo.controle_pedido_api.view.request.ClienteRequest;
import br.exemplo.controle_pedido_api.view.response.ClienteResponse;
import br.exemplo.controle_pedido_api.view.response.ClienteDetalhadoResponse;

@Controller
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    private ResponseEntity<List<ClienteResponse>> findAll() {
        List<ClienteDTO> dtos = service.findAll();
        ModelMapper mapper = new ModelMapper();
        List<ClienteResponse> responses = dtos.stream().map(
                dto -> mapper.map(dto, ClienteResponse.class)).toList();
        return new ResponseEntity<List<ClienteResponse>>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<ClienteDTO> optional = service.findById(id);
        if (optional.isPresent()) {
            ClienteDetalhadoResponse response = new ModelMapper().map(optional.get(), ClienteDetalhadoResponse.class);
            return new ResponseEntity<ClienteDetalhadoResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<ClienteDetalhadoResponse> insert(@RequestBody ClienteRequest request) {
        ModelMapper mapper = new ModelMapper();
        ClienteDTO dto = service.insert(mapper.map(request, ClienteDTO.class));
        ClienteDetalhadoResponse response = mapper.map(dto, ClienteDetalhadoResponse.class);
        return new ResponseEntity<ClienteDetalhadoResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ClienteRequest request) {
        ModelMapper mapper = new ModelMapper();
        ClienteDTO dto = mapper.map(request, ClienteDTO.class);
        dto.setId(id);
        Optional<ClienteDTO> optional = service.update(dto);
        if (optional.isPresent()) {
            ClienteDetalhadoResponse response = mapper.map(optional.get(), ClienteDetalhadoResponse.class);
            return new ResponseEntity<ClienteDetalhadoResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
