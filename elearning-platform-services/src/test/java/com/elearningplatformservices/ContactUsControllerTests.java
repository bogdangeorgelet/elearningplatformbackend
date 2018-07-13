package com.elearningplatformservices;

import com.elearningplatformservices.dto.ContactUsCustomerDto;
import com.elearningplatformservices.entity.ContactUsCustomerEntity;
import com.elearningplatformservices.repository.IContactUsCustomerRepository;
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

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElearningPlatformServicesApplication.class, H2TestProfileJPAConfig.class})
@WebAppConfiguration
@Profile("test")
public class ContactUsControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<ContactUsCustomerEntity> list = new ArrayList<>();

    @Autowired
    private IContactUsCustomerRepository contactUsCustomerRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private void setConverters(HttpMessageConverter<?>[] converters) {

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

        this.contactUsCustomerRepository.deleteAll();

        ContactUsCustomerDto first = new ContactUsCustomerDto();
        first.setFirstName("Let");
        first.setLastName("Bogdan");
        first.setEmail("bgl@gmail.com");
        first.setCountry("Romania");
        first.setPhoneNumber("0755231451");
        first.setMessage("Hello");

        ContactUsCustomerDto second = new ContactUsCustomerDto();
        second.setFirstName("Patrocle");
        second.setLastName("Lizuca");
        second.setEmail("lizuca@gmail.com");
        second.setCountry("UK");
        second.setPhoneNumber("0723854321");
        second.setMessage("Hello");

        this.list.add(this.contactUsCustomerRepository.save(first.toEntity()));
        this.list.add(this.contactUsCustomerRepository.save(second.toEntity()));

    }

    @Test
    public void userNotFound() throws Exception {
        this.mockMvc.perform(get("/blabla/contactUs")
            .content(json(new ContactUsCustomerEntity()))
            .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/contactUs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(this.list.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(this.list.get(0).getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(this.list.get(0).getLastName())))
                .andExpect(jsonPath("$[0].email", is(this.list.get(0).getEmail())))
                .andExpect(jsonPath("$[0].country", is(this.list.get(0).getCountry())))
                .andExpect(jsonPath("$[0].phoneNumber", is(this.list.get(0).getPhoneNumber())))
                .andExpect(jsonPath("$[0].message", is(this.list.get(0).getMessage())))
                .andExpect(jsonPath("$[1].id", is(this.list.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].firstName", is(this.list.get(1).getFirstName())))
                .andExpect(jsonPath("$[1].lastName", is(this.list.get(1).getLastName())))
                .andExpect(jsonPath("$[1].email", is(this.list.get(1).getEmail())))
                .andExpect(jsonPath("$[1].country", is(this.list.get(1).getCountry())))
                .andExpect(jsonPath("$[1].phoneNumber", is(this.list.get(1).getPhoneNumber())))
                .andExpect(jsonPath("$[1].message", is(this.list.get(1).getMessage())));
    }

    @Test
    public void create() throws Exception {
        ContactUsCustomerDto first = new ContactUsCustomerDto();
        first.setFirstName("Let");
        first.setLastName("Bogdan");
        first.setEmail("bgl@gmail.com");
        first.setCountry("Romania");
        first.setPhoneNumber("0755231451");
        first.setMessage("Hello");

        this.mockMvc.perform(post("/contactUs/add")
            .contentType(contentType)
            .content(json(first)))
                .andExpect(status().isCreated());
    }

    private String json(Object o) throws Exception {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
