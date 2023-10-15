package com.tutget.tutgetmain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class Advert {
    @Value("${enableAd}")
    private boolean enableAd;

    @RequestMapping("/api/ad")
    public boolean getAdStatus(){
        return enableAd;
    }
}
