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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.exemplo.controle_pedido_api.control.dto.ProdutoDTO;
import br.exemplo.controle_pedido_api.control.service.ProdutoService;
import br.exemplo.controle_pedido_api.view.request.ProdutoRequest;
import br.exemplo.controle_pedido_api.view.response.ProdutoResponse;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    private ResponseEntity<List<ProdutoResponse>> findAll() {
        List<ProdutoDTO> dtos = service.findAll();
        ModelMapper mapper = new ModelMapper();
        List<ProdutoResponse> responses = dtos.stream().map(
                dto -> mapper.map(dto, ProdutoResponse.class)).toList();
        return new ResponseEntity<List<ProdutoResponse>>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<ProdutoDTO> optional = service.findById(id);
        if (optional.isPresent()) {
            ProdutoResponse response = new ModelMapper().map(optional.get(), ProdutoResponse.class);
            return new ResponseEntity<ProdutoResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<ProdutoResponse> insert(@RequestBody ProdutoRequest request) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO dto = service.insert(mapper.map(request, ProdutoDTO.class));
        ProdutoResponse response = mapper.map(dto, ProdutoResponse.class);
        return new ResponseEntity<ProdutoResponse>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ProdutoRequest request) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO dto = mapper.map(request, ProdutoDTO.class);
        dto.setId(id);
        Optional<ProdutoDTO> optional = service.update(dto);
        if (optional.isPresent()) {
            ProdutoResponse response = mapper.map(optional.get(), ProdutoResponse.class);
            return new ResponseEntity<ProdutoResponse>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
