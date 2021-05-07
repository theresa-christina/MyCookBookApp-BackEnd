package com.passionproject.cookbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.passionproject.cookbook.services.PhotoStorageService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class PhotoStorageController {

    private final PhotoStorageService svc;

    @Autowired
    public PhotoStorageController(PhotoStorageService svc) {
        this.svc = svc;
    }

}
