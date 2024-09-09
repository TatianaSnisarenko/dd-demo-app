package com.presentation.dd_demo_app.dto;

import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.enums.AccessoryAttribute;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Builder
public class AccessoryAttributeDto {
    
    Long id;
    AccessoryAttribute attribute;
    String value;
}
