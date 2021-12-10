package com.example.cityinfo.service;

import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.model.view.ObjectViewModel;

import java.util.List;
import java.util.Map;

public interface ObjectService {

    void store(ObjectBindingModel objectBindingModel, Map<String,String> requestParams);

    List<ObjectViewModel> getLastApprovedObjects();

    List<ObjectViewModel>  getAllNotApproved();

    void approveObject(Long id) throws Exception;
}
