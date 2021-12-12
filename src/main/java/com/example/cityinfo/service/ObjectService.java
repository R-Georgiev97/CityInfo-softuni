package com.example.cityinfo.service;

import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.model.binding.ObjectEditBindingModel;
import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.view.ObjectViewModel;

import java.util.List;
import java.util.Map;

public interface ObjectService {

    void store(ObjectBindingModel objectBindingModel, Map<String,String> requestParams);

    void destroy(Long id);

    List<ObjectViewModel> getLastApprovedObjects();

    List<ObjectViewModel>  getAllNotApproved();

    List<ObjectViewModel> getAllApproved();

    void approveObject(Long id) throws Exception;

    ObjectViewModel getById(Long id) throws Exception;

    void update(ObjectEditBindingModel objetEditBindingModel, Map<String,String> requestParams) throws Exception;

    void rate(Long objectId,Integer rate) throws Exception;

    List<Object> getAllApprovedRaw();
}
