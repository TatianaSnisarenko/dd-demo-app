package com.presentation.dd_demo_app.repository;

import com.presentation.dd_demo_app.repository.entity.Accessory;
import com.presentation.dd_demo_app.repository.entity.AccessoryAttribute;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoryRepository extends JpaRepository<Accessory, Long> {

    Set<Accessory> findByCategory_NameIgnoreCase(String categoryName);

    Set<Accessory> findByAttributes(Set<AccessoryAttribute> attributes);

}
