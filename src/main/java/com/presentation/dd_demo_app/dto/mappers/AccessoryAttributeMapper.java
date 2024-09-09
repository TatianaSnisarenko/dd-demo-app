package com.presentation.dd_demo_app.dto.mappers;

import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.dto.AccessoryAttributeDto;
import com.presentation.dd_demo_app.repository.entity.AccessoryAttributeEntity;
import java.util.List;
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
public class AccessoryAttributeMapper {

    public AccessoryAttributeDto toDto(AccessoryAttributeEntity entity) {
        return AccessoryAttributeDto.builder()
                .id(entity.getId())
                .attribute(entity.getType())
                .value(entity.getValue())
                .build();
    }

    public Set<AccessoryAttributeDto> toDto(List<AccessoryAttributeEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
