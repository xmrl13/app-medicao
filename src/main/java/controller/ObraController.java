package controller;

import dto.ObraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ObraService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/obras")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @PostMapping("/create")
    public ResponseEntity<ObraDTO> criarObra(@RequestBody ObraDTO obraDTO) {

        obraService.criarObra(obraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(obraDTO);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ObraDTO>> getAllObras(){
        List<ObraDTO> obras = obraService.getAllObras();
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/search/{contrato}")
    public ResponseEntity<ObraDTO> buscarObraPorId(@PathVariable("contrato") String contrato){
        try{
            ObraDTO obraDTO = obraService.findByContrato(contrato);
            return ResponseEntity.ok(obraDTO);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/exists/{contrato}")
    public ResponseEntity<Boolean> obraExiste(@PathVariable String contrato) {
        System.out.println("cheguei");
        boolean existe = obraService.obraExistePorContrato(contrato);
        return ResponseEntity.ok(existe);
    }



}
