package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.model.binding.ObjectEditBindingModel;
import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.view.ObjectViewModel;
import com.example.cityinfo.repository.ObjectRepository;
import com.example.cityinfo.repository.RatingRepository;
import com.example.cityinfo.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ObjectServiceImpl implements ObjectService {

    private static final List<String> OBJECT_FIELDS = new ArrayList<>(Arrays.asList("name", "description", "status", "cityId", "categorySlug", "_csrf","_method"));

    private final ObjectRepository objectRepository;
    private final ModelMapper modelMapper;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ObjectDataService objectDataService;
    private final CommentService commentService;
    private final RatingService ratingService;

    public ObjectServiceImpl(ObjectRepository objectRepository, ModelMapper modelMapper, CityService cityService, CategoryService categoryService, UserService userService, ObjectDataService objectDataService, CommentService commentService, RatingService ratingService) {
        this.objectRepository = objectRepository;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.objectDataService = objectDataService;
        this.commentService = commentService;
        this.ratingService = ratingService;
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

    @Override
    public void destroy(Long id) {
        objectRepository.deleteById(id);
    }


    private void storeObjectData(Map<String, String> requestParams, Object object) {
        for (var entry : requestParams.entrySet()) {
            if (!OBJECT_FIELDS.contains(entry.getKey())) {
                String value = entry.getValue();
                if (entry.getValue().equals("") || entry.getValue() == null) {
                    value = "липсва информация";
                }

                objectDataService.store(entry.getKey(), value , object);

            }
        }
    }

    @Override
    @Transactional
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
    @Transactional
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

    @Override
    @Transactional
    public ObjectViewModel getById(Long id) throws Exception {
        Object object = objectRepository.findById(id)
                .orElseThrow(() -> new Exception("Object not found"));

        ObjectViewModel objectViewModel = modelMapper.map(object, ObjectViewModel.class);
        objectViewModel.setCityName(object.getCity().getName());
        objectViewModel.setCategoryName(object.getCategory().getName());
        objectViewModel.setFields(object.getObjectData());
        objectViewModel.setCanBeEdited(object.canBeEdited());
        objectViewModel.setComments(commentService.parseComments(object.getComments()));
        objectViewModel.setRated(ratingService.userAlreadyVotedForObject(object));
        return objectViewModel;
    }

    @Override
    public void update(ObjectEditBindingModel objetEditBindingModel, Map<String, String> requestParams) throws Exception {
        Object object = objectRepository.findById(objetEditBindingModel.getId())
                .orElseThrow(() -> new Exception("Object not found"));

        object.setName(objetEditBindingModel.getName());
        object.setDescription(objetEditBindingModel.getDescription());
        object.setStatus(objetEditBindingModel.getStatus());

        updateObjectData(requestParams,object);
        objectRepository.save(object);
    }


    private void updateObjectData(Map<String, String> requestParams, Object object) {
        for (var entry : requestParams.entrySet()) {
            if (!OBJECT_FIELDS.contains(entry.getKey())) {
                String value = entry.getValue();
                if (entry.getValue().equals("") || entry.getValue() == null) {
                    value = "липсва информация";
                }

                objectDataService.update(entry.getKey(), value , object);
            }
        }
    }

    @Override
    public void rate(Long objectId, Integer rate) throws Exception {
        Object object = objectRepository.findById(objectId)
                .orElseThrow(() -> new Exception("Object not found"));

        ratingService.store(rate,object);
    }

    @Override
    @Transactional
    public List<ObjectViewModel> getAllApproved() {
        return objectRepository.findAllByApproved(true)
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
    public List<Object> getAllApprovedRaw() {
        return objectRepository.findAllByApproved(true);
    }
}
