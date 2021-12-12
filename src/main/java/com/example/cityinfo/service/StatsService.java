package com.example.cityinfo.service;

import com.example.cityinfo.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
