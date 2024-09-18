package controller;

import dto.ItemDTO;
import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ItemService;

@RestController
@RequestMapping("api/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public ItemDTO criarItem(ItemDTO itemDTO){
        itemService.createItem(itemDTO);
        //TODO terminar

        return itemDTO;
    }






}
