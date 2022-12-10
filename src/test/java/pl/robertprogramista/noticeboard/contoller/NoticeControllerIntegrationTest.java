package pl.robertprogramista.noticeboard.contoller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class NoticeControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void showNotices() throws Exception {
        var result = mvc.perform(get("/notices?isSort=true")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Don't Sort"));
    }

    @Test
    void update() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("theme", "orange");
        params.add("description", "");

        mvc.perform(post("/notices/create").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(params)).andReturn();
        params.add("id", "1");
        params.add("description", "Hello World!");
        mvc.perform(put("/notices/update").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)).andReturn();

        var result = mvc.perform(get("/notices")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Hello World!"));
    }

    @Test
    void create() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("theme", "orange");
        params.add("description", "Hello World!");
        mvc.perform(post("/notices/create").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)).andReturn();

        var result = mvc.perform(get("/notices")).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Hello World!"));
    }

    @Test
    void remove() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("theme", "orange");
        params.add("description", "Hello World!");

        mvc.perform(post("/notices/create").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)).andReturn();

        var resultAfterCreate = mvc.perform(get("/notices")).andReturn();

        params.add("id", "1");
        mvc.perform(delete("/notices/remove").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .params(params)).andReturn();

        var resultAfterRemove = mvc.perform(get("/notices")).andReturn();

        assertTrue(resultAfterCreate.getResponse().getContentAsString().contains("<textarea onkeyup=\"update(1)\">Hello World!</textarea>"));
        assertFalse(resultAfterRemove.getResponse().getContentAsString().contains("<textarea onkeyup=\"update(1)\">Hello World!</textarea>"));
    }
}