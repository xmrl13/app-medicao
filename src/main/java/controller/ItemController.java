package controller;

import dto.ItemDTO;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ItemService;

import java.util.List;

@RestController
@RequestMapping("api/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create/one")
    public ResponseEntity<ItemDTO> createOne(@Valid @RequestBody ItemDTO itemDTO) {

        itemService.createItem(itemDTO);
        return ResponseEntity.ok().body(itemDTO);
    }

    @PostMapping("/create/list")
    public ResponseEntity<?> createList(@Valid @RequestBody List<ItemDTO> itensDTO) {

        itemService.createItens(itensDTO);
        return ResponseEntity.ok("Lista enviada para processamento");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());   }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> BadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> Exception(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
