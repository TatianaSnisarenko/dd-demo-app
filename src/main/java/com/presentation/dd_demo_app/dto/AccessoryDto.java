package com.presentation.dd_demo_app.dto;

import static lombok.AccessLevel.PRIVATE;

import com.presentation.dd_demo_app.enums.Category;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Builder
public class AccessoryDto {

    Long id;
    String name;
    String description;
    Double price;
    Double discount;
    Category category;
    Set<AccessoryAttributeDto> accessories;
}
