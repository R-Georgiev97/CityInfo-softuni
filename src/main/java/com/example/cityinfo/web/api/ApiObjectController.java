package com.example.cityinfo.web.api;

import com.example.cityinfo.service.ObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/api")
public class ApiObjectController {

    private final ObjectService objectService;

    public ApiObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @PostMapping("/objects/approve")
    public ResponseEntity<String> approveObject(@RequestParam("object_id") Long objectId) throws Exception {
        objectService.approveObject(objectId);
        return new ResponseEntity<>(
                HttpStatus.OK);
    }

    @PostMapping("/rating")
    public ResponseEntity<String> rateObject(@RequestParam("object_id") Long objectId,
                                             @RequestParam("rate") Integer rate) throws Exception {
        objectService.rate(objectId, rate);
        return new ResponseEntity<>(
                HttpStatus.OK);
    }

}
