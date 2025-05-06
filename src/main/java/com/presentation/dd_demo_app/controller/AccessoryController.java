package com.presentation.dd_demo_app.controller;

import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.dto.AccessoryDto;
import com.presentation.dd_demo_app.dto.mappers.AccessoryMapper;
import com.presentation.dd_demo_app.enums.AccessoryAttribute;
import com.presentation.dd_demo_app.enums.Category;
import com.presentation.dd_demo_app.repository.entity.AccessoryEntity;
import com.presentation.dd_demo_app.service.AccessoryService;
import jakarta.validation.constraints.Min;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RequestMapping("/accessories")
@RestController
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccessoryController {

    AccessoryService accessoryService;

    @GetMapping(value = "/{accessoryId}")
    public AccessoryDto getAccessoryById(@Min(value = 1, message = "Accessory id must be greater than 0") @PathVariable Long accessoryId) {
        log.info("Get accessory by id: {}", accessoryId);
        return accessoryService.getById(accessoryId);
    }

    @GetMapping
    public Set<AccessoryDto> getAll() {
        log.info("Get all accessories");
        return accessoryService.getAll();
    }

    @GetMapping(params = {"category"})
    public Set<AccessoryDto> getByCategoryName(@RequestParam Category category) {
        log.info("Get accessories by category: {}", category);
        return accessoryService.getByCategory(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccessoryDto createAccessory(@RequestBody AccessoryEntity accessory) {
        log.info("Create accessory: {}", accessory);
        return accessoryService.save(accessory);
    }

    @GetMapping(params = {"attribute", "value"})
    public Set<AccessoryDto> getByAttribute(@RequestParam  AccessoryAttribute attribute, @RequestParam  String value) {
        log.info("Get accessories by attribute: {} and value: {}", attribute, value);
        return accessoryService.getByAttributes(attribute, value);
    }
}
