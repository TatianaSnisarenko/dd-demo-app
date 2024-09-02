package com.presentation.dd_demo_app.service;

import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.repository.AccessoryRepository;
import com.presentation.dd_demo_app.repository.entity.Accessory;
import com.presentation.dd_demo_app.repository.entity.AccessoryAttribute;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccessoryService {

    AccessoryRepository accessoryRepository;

    public Accessory getById(Long id) {
        return accessoryRepository.findById(id).orElse(null);
    }

    public Set<Accessory> getAll(){
        return new HashSet<>(accessoryRepository.findAll());
    }

    public Set<Accessory> getByCategory(String categoryName) {
        return accessoryRepository.findByCategory_NameIgnoreCase(categoryName);
    }

    public Set<Accessory> getByAttributes(Set<AccessoryAttribute> attributes) {
        return accessoryRepository.findByAttributes(attributes);
    }

    public Accessory save(Accessory accessory) {
        return accessoryRepository.save(accessory);
    }
}
