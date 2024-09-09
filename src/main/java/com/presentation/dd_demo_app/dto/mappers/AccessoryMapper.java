package com.presentation.dd_demo_app.dto.mappers;

import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.dto.AccessoryDto;
import com.presentation.dd_demo_app.repository.entity.AccessoryEntity;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@Data
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AccessoryMapper {

    AccessoryAttributeMapper attributeMapper;

    public AccessoryDto toDto(AccessoryEntity entity) {
        return AccessoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .category(entity.getCategory().getName())
                .accessories(attributeMapper.toDto(entity.getAttributes()))
                .price(entity.getPrice())
                .discount(entity.getDiscount())
                .build();
    }

    public Set<AccessoryDto> toDto(Set<AccessoryEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
