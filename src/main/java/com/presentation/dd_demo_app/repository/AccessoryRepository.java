package com.presentation.dd_demo_app.repository;

import com.presentation.dd_demo_app.enums.AccessoryAttribute;
import com.presentation.dd_demo_app.enums.Category;
import com.presentation.dd_demo_app.repository.entity.AccessoryEntity;
import com.presentation.dd_demo_app.repository.entity.AccessoryAttributeEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryRepository extends JpaRepository<AccessoryEntity, Long> {

    Set<AccessoryEntity> findByCategory_Name(Category category);
    Set<AccessoryEntity> findByAttributes_TypeAndAttributes_ValueIgnoreCase(AccessoryAttribute type, String value);

}
