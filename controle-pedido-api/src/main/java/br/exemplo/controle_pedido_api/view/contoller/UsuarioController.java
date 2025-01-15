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
import org.springframework.web.bind.annotation.RequestMapping;

import br.exemplo.controle_pedido_api.control.dto.UsuarioDTO;
import br.exemplo.controle_pedido_api.control.service.UsuarioService;
import br.exemplo.controle_pedido_api.view.request.UsuarioRequest;
import br.exemplo.controle_pedido_api.view.response.UsuarioResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> findAll() {
        List<UsuarioDTO> dtos = service.findAll();
        ModelMapper mapper = new ModelMapper();
        List<UsuarioResponse> responses = dtos.stream()
                .map(dto -> mapper.map(dto, UsuarioResponse.class)).toList();
        return new ResponseEntity<List<UsuarioResponse>>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<UsuarioDTO> optional = service.findById(id);
        if (optional.isPresent()) {
            UsuarioResponse response = new ModelMapper().map(optional.get(), UsuarioResponse.class);
            return new ResponseEntity<UsuarioResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    public ResponseEntity<?> insert(@RequestBody UsuarioRequest request) {
        ModelMapper mapper = new ModelMapper();
        UsuarioDTO dto = mapper.map(request, UsuarioDTO.class);
        Optional<UsuarioDTO> optional = service.insert(dto);
        if (optional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        UsuarioResponse response = mapper.map(optional.get(), UsuarioResponse.class);
        return new ResponseEntity<UsuarioResponse>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UsuarioRequest request) {
        ModelMapper mapper = new ModelMapper();
        UsuarioDTO dto = mapper.map(request, UsuarioDTO.class);
        dto.setId(id);
        Optional<UsuarioDTO> optional = service.update(dto);
        if (optional.isPresent()) {
            UsuarioResponse response = mapper.map(optional.get(), UsuarioResponse.class);
            if (response.getId().equals(id)) {
                return new ResponseEntity<UsuarioResponse>(response, HttpStatus.OK);
            }
            return new ResponseEntity<UsuarioResponse>(response, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
