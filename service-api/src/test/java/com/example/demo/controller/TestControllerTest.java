package com.example.demo.controller;

import com.example.demo.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(SecurityConfig.class)
@WebMvcTest(TestController.class)
public class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublicEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a public endpoint"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUserEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a USER role endpoint"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUserEndpoint_WithAdminRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is a USER role endpoint"));
    }

    @Test
    public void testUserEndpoint_WithoutAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/test"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAdminEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is an ADMIN role endpoint"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testAdminEndpoint_WithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testAdminEndpoint_WithoutAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andExpect(status().isUnauthorized());
    }
} 