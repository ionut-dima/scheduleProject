package com.schedule.schedule.DataTransferObject;

import com.schedule.schedule.Entity.Wish;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResolveContraintsResponse {

    private List<Wish> resolveWish = new ArrayList<>();
    private String message;
}
