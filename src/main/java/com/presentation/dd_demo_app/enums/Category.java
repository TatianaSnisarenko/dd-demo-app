package com.presentation.dd_demo_app.enums;

import java.beans.PropertyEditorSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public enum Category {
    BAG,
    GLASSES,
    BELT,
    SCARF,
    HAT;

//    public static Category fromString(String category) {
//        try {
//            return Category.valueOf(category.toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("Invalid category: " + category);
//        }
//    }
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Category.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                setValue(Category.fromString(text));
//            }
//        });
//    }

}
