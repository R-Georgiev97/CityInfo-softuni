package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.view.ObjectViewModel;
import com.example.cityinfo.repository.ObjectRepository;
import com.example.cityinfo.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ObjectServiceImpl implements ObjectService {

    private static final List<String> OBJECT_FIELDS = new ArrayList<>(Arrays.asList("name", "description", "status", "cityId", "categorySlug", "_csrf"));

    private final ObjectRepository objectRepository;
    private final ModelMapper modelMapper;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ObjectDataService objectDataService;

    public ObjectServiceImpl(ObjectRepository objectRepository, ModelMapper modelMapper, CityService cityService, CategoryService categoryService, UserService userService, ObjectDataService objectDataService) {
        this.objectRepository = objectRepository;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.objectDataService = objectDataService;
    }

    public void store(ObjectBindingModel objectBindingModel, Map<String, String> requestParams) {
        String username = "";
        java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        Object object = modelMapper.map(objectBindingModel, Object.class);
        object.setApproved(false);
        object.setAverageRating(0.0);
        object.setCity(cityService.getById(objectBindingModel.getCityId()));
        object.setCategory(categoryService.getBySlug(objectBindingModel.getCategorySlug()));
        object.setUser(userService.getByUsername(username));
        objectRepository.save(object);

        storeObjectData(requestParams, object);

    }


    private void storeObjectData(Map<String, String> requestParams, Object object) {
        for (var entry : requestParams.entrySet()) {
            if (!OBJECT_FIELDS.contains(entry.getKey())) {
                if (!entry.getValue().equals("") && entry.getValue() != null) {
                    objectDataService.store(entry.getKey(), entry.getValue(), object);
                }
            }
        }
    }

    @Override
    public List<ObjectViewModel> getLastApprovedObjects() {
        return objectRepository.findTop5ByApprovedOrderByIdDesc(true)
                .stream()
                .map(object -> {
                    ObjectViewModel objectViewModel = modelMapper.map(object, ObjectViewModel.class);
                    objectViewModel.setCityName(object.getCity().getName());
                    objectViewModel.setCategoryName(object.getCategory().getName());
                    return objectViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ObjectViewModel> getAllNotApproved() {
        return objectRepository.findAllByApprovedOrderById(false)
                .stream()
                .map(object -> {
                    ObjectViewModel objectViewModel = modelMapper.map(object, ObjectViewModel.class);
                    objectViewModel.setCityName(object.getCity().getName());
                    objectViewModel.setCategoryName(object.getCategory().getName());
                    return objectViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void approveObject(Long id) throws Exception {
        Object object = objectRepository.findById(id)
                .orElseThrow(() -> new Exception("Object not found"));
        object.setApproved(true);
        objectRepository.save(object);

    }
}
