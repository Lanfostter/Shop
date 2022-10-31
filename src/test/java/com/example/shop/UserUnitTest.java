package com.example.shop;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.VerificationCollector;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

import com.example.shop.dto.UserDTO;
import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.LoginService;
import com.example.shop.service.impl.UserServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserUnitTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Autowired
    UserDetailsService userDetailsService;
    @Mock
    LoginService loginService;

    @Test
    void getAll_userReturnList() {
        List<UserEntity> mockbooks = new ArrayList<UserEntity>();
        for (int i = 0; i < userRepository.findAll().size(); i++) {
            mockbooks.addAll(userRepository.findAll());
        }
        when(userRepository.findAll()).thenReturn((ArrayList<UserEntity>) mockbooks);
    }

    @Test
    void whenAddOneUser() throws ParseException {
        UserEntity entity = new UserEntity();
        entity.setName("DucAnh");
        entity.setEmail("fostter2gmail.com");
        String date = "12/12/2001";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        entity.setBirthday(dateFormat.parse(date));
        entity.setUsername("DucAnh162001");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entity.setPassword(encoder.encode("12345"));
        entity.setGender("Nam");
        List<String> roles = new ArrayList<String>();
        roles.add("ROLE_ADMIN");
        entity.setRoles(roles);
        when(userRepository.save(entity)).thenReturn(entity);
        UserEntity testEntity = mock(UserEntity.class);
        testEntity = entity;
        assertNotNull(userRepository.save(entity));
        verify(userRepository).save(testEntity);
        assertNotNull(testEntity.getId());
        assertNotNull(testEntity.getName());
        assertNotNull(testEntity.getGender());
        assertNotNull(testEntity.getEmail());
        assertNotNull(testEntity.getUsername());
        assertNotNull(testEntity.getPassword());
        assertNotNull(testEntity.getRoles());
        assertEquals(dateFormat.parse("12/12/2001"), entity.getBirthday());
    }

    @Test
    void findByName() {
        List<UserEntity> mockbooks = new ArrayList<UserEntity>();
        String name = "DucAnh";
        for (int i = 0; i < userRepository.findByName(name).size(); i++) {
            mockbooks.addAll(userRepository.findByName(name));
        }
        when(userRepository.findByName(name)).thenReturn((ArrayList<UserEntity>) mockbooks);
    }

    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectResponse() throws Exception {
        String user = "{\"name\": \"\", \"email\" : \"bob@domain.com\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/Shop/register")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("Name is mandatory")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
