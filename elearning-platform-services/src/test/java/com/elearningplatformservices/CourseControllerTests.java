package com.elearningplatformservices;

import com.elearningplatformservices.dto.CourseDto;
import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CourseEntity;
import com.elearningplatformservices.entity.CustomerEntity;
import com.elearningplatformservices.entity.InstructorEntity;
import com.elearningplatformservices.enums.CourseCategories;
import com.elearningplatformservices.repository.ICourseRepository;
import com.elearningplatformservices.repository.ICustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.elearningplatformservices.enums.CourseCategories.JAVA;
import static com.elearningplatformservices.enums.CourseCategories.JAVASCRIPT;
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElearningPlatformServicesApplication.class, H2TestProfileJPAConfig.class})
@WebAppConfiguration
@Profile("test")
public class CourseControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<CourseEntity> courses = new ArrayList<>();

    private List<CustomerEntity> customers = new ArrayList<>();

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);

    }

    @Before
    public void setUp() throws Exception {

        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.courseRepository.deleteAll();
        this.customerRepository.deleteAll();

        CustomerDto customer1 = new CustomerDto();
        customer1.setId(1L);
        customer1.setFullName("Let Bogdan");
        customer1.setUsername("lbogdan");

        CustomerDto customer2 = new CustomerDto();
        customer2.setId(2L);
        customer2.setFullName("Pavel Dan");
        customer2.setUsername("pavelDan");

        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);
//        first.setInstructor(new InstructorEntity().getFirstName());
//        first.setCustomer(Arrays.asList(customer));

        CourseDto second = new CourseDto();
        second.setName("Filip");
        second.setCourse_type("JAVASCRIPT");
        second.setPrice(99.99);
        second.setCategory(JAVASCRIPT);
        second.setInstructor(new InstructorEntity());

        this.customers.add(this.customerRepository.save(customer1.toEntity()));
        this.customers.add(this.customerRepository.save(customer2.toEntity()));

        this.courses.add(this.courseRepository.save(first.toEntity()));
        this.courses.add(this.courseRepository.save(second.toEntity()));
    }

    @Test
    public void courseNotFound() throws Exception {
        this.mockMvc.perform(get("george/course")
            .content(this.json(new CourseEntity()))
            .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getOneCourse() throws Exception {
        this.mockMvc.perform(get("/course/{id}", courses.get(0).getId()))
                .andExpect(content().contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.courses.get(0).getId().intValue())))
                .andExpect(jsonPath("$.name", is(this.courses.get(0).getName())))
                .andExpect(jsonPath("$.course_type", is(this.courses.get(0).getCourse_type())))
                .andExpect(jsonPath("$.price", is(this.courses.get(0).getPrice())))
                .andExpect(jsonPath("$.category", is( "JAVA")));
    }

    @Test
    public void getAllCourses() throws Exception {
        this.mockMvc.perform(get("/course"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(this.courses.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(this.courses.get(0).getName())))
                .andExpect(jsonPath("$[0].course_type", is(this.courses.get(0).getCourse_type())))
                .andExpect(jsonPath("$[0].price", is(this.courses.get(0).getPrice())))
                .andExpect(jsonPath("$[0].category", is("JAVA")))
                .andExpect(jsonPath("$[1].id", is(this.courses.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(this.courses.get(1).getName())))
                .andExpect(jsonPath("$[1].course_type", is(this.courses.get(1).getCourse_type())))
                .andExpect(jsonPath("$[1].price", is(this.courses.get(1).getPrice())))
                .andExpect(jsonPath("$[1].category", is("JAVASCRIPT")));
    }

    @Test
    public void getCourseNotFound() throws Exception{
        this.mockMvc.perform(get("/course/{id}", 100))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCourse() throws Exception {
        this.mockMvc.perform(delete("/course/{id}", courses.get(0).getId())
            .contentType(contentType)
            .content(json(courses.get(0))))
            .andExpect(status().isOk());
    }

    @Test
    public void createCourse() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        this.mockMvc.perform(post("/course/add")
            .contentType(contentType)
            .content(json(first)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCourse() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        this.mockMvc.perform(put("/course/{id}", courses.get(0).getId())
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isOk());
    }

    @Test
    public void updateWithoutId() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        this.mockMvc.perform(put("/course/{id}", first.getId())
            .contentType(contentType)
            .content(json(first)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void updateWithoutName() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        this.mockMvc.perform(put("/course/{id}", first.getId())
            .content(json(first))
            .contentType(contentType))
            .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void checkIfNameExists() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        if (courseRepository.findByName(courses.get(0).getName()) != null) {
            if (first.getName().equals(courseRepository.findByName(courses.get(0).getName()))) {
                List<CourseEntity> all = courseRepository.findAll();
                if (all.stream().anyMatch(one -> one.getName().equals(first.getName()))) {
                    this.mockMvc.perform(put("/course/{id}", courses.get(0).getId())
                        .contentType(contentType)
                        .content(json(first)))
                        .andExpect(status().isMethodNotAllowed());
                }
            }
        }
    }

    @Test
    public void checkIfDifferentName() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        if (courseRepository.findByName(courses.get(0).getName()) != null) {
            if (!first.getName().equals(courseRepository.findByName(courses.get(0).getName()))) {
                this.mockMvc.perform(put("/course/{id}", courses.get(0).getId())
                        .contentType(contentType)
                        .content(json(first)))
                        .andExpect(status().isOk());
            }
        }
    }

    @Test
    public void checkIfNameNotFound() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        if (courseRepository.findByName(courses.get(0).getName()) == null) {
            this.mockMvc.perform(put("/course/{id}", courses.get(0).getId())
                    .contentType(contentType)
                    .content(json(first)))
                    .andExpect(status().isMethodNotAllowed());
        }
    }

    @Test
    public void couldNotCreateCourse() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        this.mockMvc.perform(post("/courses/add")
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void couldNotUpdateCourse() throws Exception {
        CourseDto first = new CourseDto();
        first.setName(null);
        first.setCourse_type(null);
        first.setPrice(null);
        first.setCategory(null);

        this.mockMvc.perform(put("/courses/{id}", courses.get(0).getId().intValue())
            .content(json(first))
            .contentType(contentType))
            .andExpect(status().isNotFound());
    }

    @Test
    public void wrongPathUpdate() throws Exception {
        CourseDto first = new CourseDto();
        first.setName("Dan");
        first.setCourse_type("JAVA");
        first.setPrice(10.00);
        first.setCategory(JAVA);

        this.mockMvc.perform(put("example/course/{id}", courses.get(0).getId())
            .content(json(first))
            .contentType(contentType))
            .andExpect(status().isNotFound());
    }

    @Test
    public void getCourseByCustomerUsername() throws Exception {
        this.mockMvc.perform(get("/course/byCustomer/{username}", customers.get(0).getUsername()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getCourseByCategory() throws Exception {
        this.mockMvc.perform(get("/course/getAllByCategory/{category}", courses.get(0).getCategory()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(this.courses.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(this.courses.get(0).getName())))
                .andExpect(jsonPath("$[0].course_type", is(this.courses.get(0).getCourse_type())))
                .andExpect(jsonPath("$[0].price", is(this.courses.get(0).getPrice())))
                .andExpect(jsonPath("$[0].category", is("JAVA")));
    }

    @Test
    public void getCourseByInstructor() throws Exception {
        this.mockMvc.perform(get("/course/allBy/{firstName}", courses.get(0).getInstructor()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.courses.get(0).getId().intValue())))
                .andExpect(jsonPath("$.name", is(this.courses.get(0).getName())))
                .andExpect(jsonPath("$.course_type", is(this.courses.get(0).getCourse_type())))
                .andExpect(jsonPath("$.price", is(this.courses.get(0).getPrice())))
                .andExpect(jsonPath("$.category", is("JAVA")));
    }

    private String json(Object o) throws Exception {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
