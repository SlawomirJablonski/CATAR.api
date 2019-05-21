package com.restapp.catar.controller;

import com.restapp.catar.domain.auto.Mark;
import com.restapp.catar.domain.auto.Model;
import com.restapp.catar.domain.auto.client.AutoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/auto")
public class AutoController {

    private AutoClient autoClient;

    @Autowired
    public AutoController(AutoClient autoClient) {
        this.autoClient = autoClient;
    }

    @GetMapping(value = "/models/{model}")
    public List<Model> getModels(@RequestParam String code) {
        return autoClient.getModels(code);
    }

    @GetMapping(value = "/brands")
    public List<Mark> getMarks() {
        return autoClient.getMarks();
    }
}
