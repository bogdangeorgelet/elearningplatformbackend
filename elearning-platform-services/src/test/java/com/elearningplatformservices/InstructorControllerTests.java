package com.elearningplatformservices;

import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.dto.InstructorDto;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.repository.IInstructorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElearningPlatformServicesApplication.class, H2TestProfileJPAConfig.class})
@WebAppConfiguration
@Profile("test")
public class InstructorControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2MessageConverter;

    private List<InstructorEntity> instructors = new ArrayList<>();

    @Autowired
    private IInstructorRepository instructorRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2MessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message must not be null",
                this.mappingJackson2MessageConverter);
    }

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.instructorRepository.deleteAll();

        InstructorDto first = new InstructorDto();
        first.setFirstName("Dan");
        first.setLastName("Bogdan");
        first.setPassword("password");
        first.setEmail("example@gmail.com");

        InstructorDto second = new InstructorDto();
        second.setFirstName("John");
        second.setEmail("Doe");
        second.setPassword("password");
        second.setEmail("doe@gmail.com");

        this.instructors.add(instructorRepository.save(first.toEntity()));
        this.instructors.add(instructorRepository.save(second.toEntity()));
    }

    @Test
    public void instructorNotFound() throws Exception {

        mockMvc.perform(get("george/instructor/")
            .content(this.json(new InstructorEntity()))
            .contentType(contentType))
            .andExpect(status().isNotFound());

    }

    @Test
    public void getOneInstructor() throws Exception {
        mockMvc.perform(get("/instructors/{id}", instructors.get(0).getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.instructors.get(0).getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(this.instructors.get(0).getFirstName())))
                .andExpect(jsonPath("$.lastName", is(this.instructors.get(0).getLastName())))
                .andExpect(jsonPath("$.password", is(this.instructors.get(0).getPassword())))
                .andExpect(jsonPath("$.email", is(this.instructors.get(0).getEmail())));
    }


    @Test
    public void getAllInstructors() throws Exception {
        mockMvc.perform(get("/instructors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].id", is(this.instructors.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(this.instructors.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(this.instructors.get(0).getLastName())))
                .andExpect(jsonPath("$[0].password", is(this.instructors.get(0).getPassword())))
                .andExpect(jsonPath("$[0].email", is(this.instructors.get(0).getEmail())))
                .andExpect(jsonPath("$[1].id", is(this.instructors.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].firstName", is(this.instructors.get(1).getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(this.instructors.get(1).getLastName())))
                .andExpect(jsonPath("$[1].password", is(this.instructors.get(1).getPassword())))
                .andExpect(jsonPath("$[1].email", is(this.instructors.get(1).getEmail())));
    }

    @Test
    public void deleteInstructor() throws Exception {
        mockMvc.perform(delete("/instructors/{id}", instructors.get(0).getId())
            .contentType(contentType).content(json(instructors.get(0))))
                .andExpect(status().isOk());
    }

    @Test
    public void couldNotDeleteInstructor() throws Exception {
        InstructorDto first = new InstructorDto();
        first.setFirstName("Dan");
        first.setLastName("Bogdan");
        first.setPassword("password");
        first.setEmail("example@gmail.com");
        instructorRepository.save(first.toEntity());

        this.mockMvc.perform(delete("/instructors/{id}", first.getId())
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void createInstructor() throws Exception {
        InstructorDto first = new InstructorDto();
        first.setFirstName("Dan");
        first.setLastName("Bogdan");
        first.setPassword("password");
        first.setEmail("example@gmail.com");

        this.mockMvc.perform(post("/instructors")
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isCreated());
    }

    @Test
    public void couldNotCreateInstructor() throws Exception {
        InstructorDto first = new InstructorDto();
        first.setFirstName(null);
        first.setLastName(null);
        first.setPassword(null);
        first.setEmail(null);

        this.mockMvc.perform(post("/instructor/add")
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateInstructor() throws Exception {
        InstructorDto first = new InstructorDto();
        first.setFirstName("Dan");
        first.setLastName("Bogdan");
        first.setPassword("password");
        first.setEmail("example@gmail.com");

        mockMvc.perform(put("/instructors/{id}", instructors.get(0).getId())
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isOk());
    }

    private String json(Object o) throws Exception {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2MessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
