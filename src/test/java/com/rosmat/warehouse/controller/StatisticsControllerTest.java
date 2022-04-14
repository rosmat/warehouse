package com.rosmat.warehouse.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class StatisticsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldStatisticsReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statistics"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReportReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report?aggregationFunctionEnum=COUNT&groupingParamEnum=DATASOURCE&aggregationParamEnum=IMPRESSIONS"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
