package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ItemRepository;
import repository.MedicaoBaciaItemRepository;
import repository.MedicaoRepository;

@Service
public class MedicaoService {

    @Autowired
    private MedicaoRepository medicaoRepository;

    @Autowired
    private MedicaoBaciaItemRepository medicaoItemRepository;

    @Autowired
    private ItemRepository itemRepository;

}
