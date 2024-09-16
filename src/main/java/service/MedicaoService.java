package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ItemRepository;
import repository.MedicaoItemRepository;
import repository.MedicaoRepository;

@Service
public class MedicaoService {

    @Autowired
    private MedicaoRepository medicaoRepository;

    @Autowired
    private MedicaoItemRepository medicaoItemRepository;

    @Autowired
    private ItemRepository itemRepository;

}
