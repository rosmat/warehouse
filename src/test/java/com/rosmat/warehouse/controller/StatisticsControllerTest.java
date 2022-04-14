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

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report?aggregationFunctionEnum=SUM&groupingParamEnum=CAMPAIGN&aggregationParamEnum=IMPRESSIONS"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report?aggregationFunctionEnum=AVG&groupingParamEnum=DATE&aggregationParamEnum=IMPRESSIONS"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report?aggregationFunctionEnum=MAX&groupingParamEnum=DATASOURCE&aggregationParamEnum=CLICKS"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report?aggregationFunctionEnum=MIN&groupingParamEnum=CAMPAIGN&aggregationParamEnum=CLICKS"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldReportReturn400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("groupingParamEnum")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("aggregationFunctionEnum")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("aggregationParamEnum")));

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report?groupingParamEnum=DATASOURCE"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("aggregationFunctionEnum")))
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("aggregationParamEnum")));

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics/report?groupingParamEnum=DATASOURCE&aggregationFunctionEnum=COUNT"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString("aggregationParamEnum")));
    }
}
