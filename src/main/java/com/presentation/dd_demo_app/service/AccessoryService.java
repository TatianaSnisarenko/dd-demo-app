package com.presentation.dd_demo_app.service;

import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_ALL;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_ALL_LATENCY;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_BY_ATTRIBUTES;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_BY_ATTRIBUTES_LATENCY;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_BY_CATEGORY;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_BY_CATEGORY_LATENCY;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_BY_ID;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_BY_ID_LATENCY;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricName.ACCESSORIES_SAVE;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricTag.ATTRIBUTE;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricTag.CATEGORY;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricTag.ID;
import static com.presentation.dd_demo_app.repository.entity.metrics.MetricTag.VALUE;
import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.dto.AccessoryDto;
import com.presentation.dd_demo_app.dto.mappers.AccessoryMapper;
import com.presentation.dd_demo_app.enums.AccessoryAttribute;
import com.presentation.dd_demo_app.enums.Category;
import com.presentation.dd_demo_app.repository.AccessoryRepository;
import com.presentation.dd_demo_app.repository.entity.AccessoryEntity;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional
public class AccessoryService {

    AccessoryRepository accessoryRepository;
    AccessoryMapper accessoryMapper;
    MeterRegistry meterRegistry;

    public AccessoryDto getById(Long id) {
        long startTime = System.currentTimeMillis();
        meterRegistry.counter(ACCESSORIES_BY_ID, ID, id.toString()).increment();
        AccessoryDto dto = accessoryRepository.findById(id).map(accessoryMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException(String.format("Accessory with id %s is not present in DB", id)));
        meterRegistry.timer(ACCESSORIES_BY_ID_LATENCY, ID, id.toString())
                .record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
        return dto;
    }

    public Set<AccessoryDto> getAll() {
        long startTime = System.currentTimeMillis();
        delay();
        meterRegistry.counter(ACCESSORIES_ALL).increment();
        Set<AccessoryDto> accessories = accessoryRepository.findAll().stream().map(accessoryMapper::toDto).collect(Collectors.toSet());
        meterRegistry.timer(ACCESSORIES_ALL_LATENCY).record(System.currentTimeMillis() - startTime,
                TimeUnit.MILLISECONDS);
        return accessories;
    }

    public Set<AccessoryDto> getByCategory(Category category) {
        long startTime = System.currentTimeMillis();
        delay();
        meterRegistry.counter(ACCESSORIES_BY_CATEGORY, CATEGORY, category.name()).increment();
        Set<AccessoryDto> accessories = accessoryRepository.findByCategory_Name(category).stream()
                .map(accessoryMapper::toDto)
                .collect(Collectors.toSet());
        meterRegistry.timer(ACCESSORIES_BY_CATEGORY_LATENCY, CATEGORY, category.name()).record(System.currentTimeMillis() - startTime,
                TimeUnit.MILLISECONDS);
        return accessories;
    }

    public Set<AccessoryDto> getByAttributes(AccessoryAttribute attribute, String value) {
        long startTime = System.currentTimeMillis();
        delay();
        meterRegistry.counter(ACCESSORIES_BY_ATTRIBUTES, ATTRIBUTE, attribute.name(), VALUE, value).increment();
        Set<AccessoryDto> accessories = accessoryRepository.findByAttributes_TypeAndAttributes_ValueIgnoreCase(attribute, value).stream()
                .map(accessoryMapper::toDto)
                .collect(Collectors.toSet());
        meterRegistry.timer(ACCESSORIES_BY_ATTRIBUTES_LATENCY, ATTRIBUTE, attribute.name(), VALUE, value)
                .record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
        return accessories;
    }

    public AccessoryDto save(AccessoryEntity accessory) {
        long startTime = System.currentTimeMillis();
        meterRegistry.counter(ACCESSORIES, CATEGORY, accessory.getCategory().getName().name()).increment();
        AccessoryDto dto = accessoryMapper.toDto(accessoryRepository.save(accessory));
        meterRegistry.timer(ACCESSORIES_SAVE, CATEGORY, accessory.getCategory().getName().name())
                .record(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
        return dto;
    }

    private void delay() {
        try {
            Random random = new Random();
            int delay = random.nextInt(201);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
