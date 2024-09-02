package com.presentation.dd_demo_app.controller;

import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.repository.AccessoryRepository;
import com.presentation.dd_demo_app.repository.entity.Accessory;
import com.presentation.dd_demo_app.repository.entity.AccessoryAttribute;
import com.presentation.dd_demo_app.service.AccessoryService;
import jakarta.validation.constraints.Min;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RequestMapping("/accessories")
@RestController
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccessoryController {

    AccessoryService accessoryService;

    @GetMapping(value = "/{accessoryId}")
    public Accessory getAccessoryById(@Min(value = 1, message = "Accessory id must be greater than 0") @PathVariable Long accessoryId) {
        return accessoryService.getById(accessoryId);
    }

    @GetMapping
    public Set<Accessory> getAll() {
        return accessoryService.getAll();
    }

    @GetMapping(params = {"categoryName"})
    public Set<Accessory> getByCategoryName(@RequestParam String categoryName) {
        return accessoryService.getByCategory(categoryName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Accessory createAccessory(@RequestBody Accessory accessory) {
        return accessoryService.save(accessory);
    }



}
