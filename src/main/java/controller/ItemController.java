package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ItemService;

@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;




}
