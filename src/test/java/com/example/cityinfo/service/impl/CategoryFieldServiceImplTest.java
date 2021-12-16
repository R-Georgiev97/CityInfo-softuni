package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.CategoryBindingModel;
import com.example.cityinfo.model.binding.CategoryFieldBindingModel;
import com.example.cityinfo.model.entity.Category;
import com.example.cityinfo.repository.CategoryFieldRepository;
import com.example.cityinfo.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryFieldServiceImplTest {

    @Mock
    private CategoryFieldRepository categoryFieldRepository;
    @Mock
    private Category category;
    @Mock
    private ValidationUtil validationUtil;
    @Spy
    private ModelMapper modelMapper;
    @Spy
    private CategoryFieldBindingModel categoryFieldBindingModel;

    @BeforeEach
    void init(){
        validationUtil = mock(ValidationUtil.class);
    }

    @Test
    @MockitoSettings(strictness = Strictness.WARN)
    void testStoresCategoryFieldWithCorrectData() {

        CategoryFieldBindingModel categoryFieldBindingModel = new CategoryFieldBindingModel();
        categoryFieldBindingModel.setName("testName");
        categoryFieldBindingModel.setSlug("test");

        when(validationUtil.isValid(any(CategoryFieldBindingModel.class))).thenReturn(true);

        CategoryFieldServiceImpl categoryFieldService = new CategoryFieldServiceImpl(categoryFieldRepository,validationUtil,modelMapper);
        categoryFieldService.store("testName","test", category);


    }
}
