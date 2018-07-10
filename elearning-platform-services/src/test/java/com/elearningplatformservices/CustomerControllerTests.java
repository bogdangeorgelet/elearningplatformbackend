package com.elearningplatformservices;

import com.elearningplatformservices.dto.CustomerDto;
import com.elearningplatformservices.entity.CustomerEntity;
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
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ElearningPlatformServicesApplication.class, H2TestProfileJPAConfig.class})
@WebAppConfiguration
@Profile("test")
public class CustomerControllerTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<CustomerEntity> customers = new ArrayList<>();

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

        this.customerRepository.deleteAll();

        CustomerDto first =new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        CustomerDto second =new CustomerDto();
        second.setFullName("Lucas");
        second.setUsername("lucas");
        second.setPassword("pass2");
        second.setEmail("second@gmail.com");
        second.setAddress("Str. Nufarului");
        second.setPhoneNumber("0744353234");

        this.customers.add(customerRepository.save(first.toEntity()));
        this.customers.add(customerRepository.save(second.toEntity()));
    }

    @Test
    public void customerNotFoud() throws Exception{

        mockMvc.perform(get("george/customer/")
                .content(this.json(new CustomerEntity()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getOneCustomer() throws Exception {
        mockMvc.perform(get("/customer/{id}", customers.get(0).getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.customers.get(0).getId().intValue())))
                .andExpect(jsonPath("$.fullName", is(this.customers.get(0).getFullName())))
                .andExpect(jsonPath("$.username", is(this.customers.get(0).getUsername())))
                .andExpect(jsonPath("$.password", is(this.customers.get(0).getPassword())))
                .andExpect(jsonPath("$.email", is(this.customers.get(0).getEmail())))
                .andExpect(jsonPath("$.address", is(this.customers.get(0).getAddress())))
                .andExpect(jsonPath("$.phoneNumber", is(this.customers.get(0).getPhoneNumber())));
    }

    @Test
    public void getAllCustomers() throws Exception {
        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(this.customers.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].fullName", is(this.customers.get(0).getFullName())))
                .andExpect(jsonPath("$[0].username", is(this.customers.get(0).getUsername())))
                .andExpect(jsonPath("$[0].password", is(this.customers.get(0).getPassword())))
                .andExpect(jsonPath("$[0].email", is(this.customers.get(0).getEmail())))
                .andExpect(jsonPath("$[0].address", is(this.customers.get(0).getAddress())))
                .andExpect(jsonPath("$[0].phoneNumber", is(this.customers.get(0).getPhoneNumber())))
                .andExpect(jsonPath("$[1].id", is(this.customers.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].fullName", is(this.customers.get(1).getFullName())))
                .andExpect(jsonPath("$[1].username", is(this.customers.get(1).getUsername())))
                .andExpect(jsonPath("$[1].password", is(this.customers.get(1).getPassword())))
                .andExpect(jsonPath("$[1].email", is(this.customers.get(1).getEmail())))
                .andExpect(jsonPath("$[1].address", is(this.customers.get(1).getAddress())))
                .andExpect(jsonPath("$[1].phoneNumber", is(this.customers.get(1).getPhoneNumber())));
    }

    @Test
    public void getCustomerNotFound() throws Exception {
        mockMvc.perform(get("/customer/{id}", 100))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(delete("/customer/{id}", customers.get(0).getId())
            .contentType(contentType).content(json(customers.get(0))))
                .andExpect(status().isOk());
    }

    @Test
    public void couldNotDeleteCustomer() throws Exception {
        CustomerDto first =new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");
        customerRepository.save(first.toEntity());

        this.mockMvc.perform(delete("/customer/{id}", first.getId())
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isMethodNotAllowed());

    }

    @Test
    public void createCustomer() throws Exception {
        CustomerDto first =new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        this.mockMvc.perform(post("/customer/add")
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isCreated());
    }

    @Test
    public void couldNotCreateCustomer() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setId(null);
        first.setFullName(null);
        first.setUsername(null);
        first.setPassword(null);
        first.setEmail(null);
        first.setAddress(null);
        first.setPhoneNumber(null);

        this.mockMvc.perform(post("/customers/add")
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustomer() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        this.mockMvc.perform(put("/customer/{id}", customers.get(0).getId())
            .contentType(contentType)
            .content(json(first)))
            .andExpect(status().isOk());
    }

    @Test
    public void updateWithoutUsername() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setFullName("Name");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        this.mockMvc.perform(put("/customer/{id}", first.getId())
            .contentType(contentType)
            .content(json(first)))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void checkIfSameUsername() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        if(customerRepository.findByUsername(customers.get(0).getUsername()) != null) {
            if (first.getUsername().equals(customerRepository.findByUsername(customers.get(0).getUsername()))) {
                this.mockMvc.perform(put("/customer/{id}", customers.get(0).getId())
                    .content(json(first))
                    .contentType(contentType))
                        .andExpect(status().isOk());
            }
        }
    }

    @Test
    public void checkIfSameUsernameExists() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        if (customerRepository.findByUsername(customers.get(0).getUsername()).equals(true)) {
            if (first.getUsername().equals(customerRepository.findByUsername(customers.get(0).getUsername()))) {
                List<CustomerEntity> all = (List<CustomerEntity>) customerRepository.findAll();
                if (all.stream().anyMatch(hmc -> hmc.getUsername().equals(first.getUsername()))) {
                    this.mockMvc.perform(put("/customer/{id}", customers.get(0).getUsername())
                        .contentType(contentType)
                        .content(json(first)))
                            .andExpect(status().isMethodNotAllowed());
                }
            }
        }
    }

    @Test
    public void checkIfDifferentUsername() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        if (customerRepository.findByUsername(customers.get(0).getUsername()).equals(true)) {
            if (first.getUsername().equals(customerRepository.findByUsername(customers.get(0).getUsername()))) {
                this.mockMvc.perform(put("/customer/{id}", customers.get(0).getUsername())
                    .content(json(first))
                    .contentType(contentType))
                        .andExpect(status().isOk());
            }
        }
    }

    @Test
    public void checkIfusernameNotFound() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        if (customerRepository.findByUsername(customers.get(0).getUsername()).equals(false)) {
            this.mockMvc.perform(put("/customer/{id}", customers.get(0).getUsername())
                    .content(json(first))
                    .contentType(contentType))
                    .andExpect(status().isNotFound());
        }
    }

    @Test
    public void wrongPathUpdate() throws Exception {
        CustomerDto first = new CustomerDto();
        first.setFullName("Name");
        first.setUsername("username");
        first.setPassword("pass");
        first.setEmail("first@gmail.com");
        first.setAddress("Str. Universitatii");
        first.setPhoneNumber("0744353234");

        this.mockMvc.perform(put("/george/customer/{id}", first.getId())
            .contentType(contentType)
            .content(json(first)))
                .andExpect(status().isNotFound());
    }

    private String json(Object o) throws Exception {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
