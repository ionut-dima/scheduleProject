package com.schedule.schedule.api;

import com.schedule.schedule.DataTransferObject.ResolveContraintsResponse;
import com.schedule.schedule.logic.AiLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallResolveContraintsController {

    private static final String API_NAME = "/admin";

    @Autowired
    AiLogic aiLogic;

    @GetMapping(API_NAME + "/callResolveConstraints")
    public ResolveContraintsResponse callResolveContraints() {

        ResolveContraintsResponse resolveContraintsResponse = new ResolveContraintsResponse();

        resolveContraintsResponse.setResolveWish(aiLogic.resolveConstraints());
        return resolveContraintsResponse;
    }
}
