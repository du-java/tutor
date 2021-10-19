package com.example.tutor.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tutor.models.Course;
import com.example.tutor.models.Group;
import com.example.tutor.models.Lesson;
import com.example.tutor.repositories.CourseRepository;
import com.example.tutor.repositories.GroupRepository;
import com.example.tutor.repositories.LessonRepository;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    int port;

    @Autowired
    private GroupRepository groupRepository;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private LessonRepository lessonRepository;

    @Test
    void create() throws Exception {

        final Lesson lesson1 = Lesson.builder().id(1L).build();
        final Lesson lesson2 = Lesson.builder().id(2L).build();
        final Course course = Course.builder()
                .id(2L)
                .periodStart(LocalDate.parse("2021-09-01"))
                .periodEnd(LocalDate.parse("2021-09-15"))
                .lessons(List.of(lesson1, lesson2))
                .group(Group.builder().id(1L).build())
                .build();
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        when(lessonRepository.save(any(Lesson.class)))
                .thenReturn(lesson1)
                .thenReturn(lesson2);

        String json = "{" +
                "  \"start\": \"2021-09-01\"," +
                "  \"end\": \"2021-09-15\"," +
                "  \"lessonStartTime\": \"11:30:00\"," +
                "  \"lessonDuration\": 30," +
                "  \"dayOfWeek\": \"monday\"," +
                "  \"groupId\": 1" +
                "}";

        mockMvc
                .perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.periodStart").value("2021-09-01"))
                .andExpect(jsonPath("$.periodEnd").value("2021-09-15"))
                .andExpect(jsonPath("$.lessons").isArray())
                .andExpect(jsonPath("$.lessons").isNotEmpty())
                .andExpect(jsonPath("$.lessons.length()").value(2))
                .andExpect(jsonPath("$.lessons[0]").value(1))
                .andExpect(jsonPath("$.lessons[1]").value(2))
                .andExpect(jsonPath("$.groupId").value(1));
    }

    @Test
    void create_isBusy() throws Exception {

        final Lesson lesson1 = Lesson.builder().id(1L).build();
        final Lesson lesson2 = Lesson.builder().id(2L).build();
        final Course course = Course.builder()
                .id(1L)
                .periodStart(LocalDate.parse("2021-09-01"))
                .periodEnd(LocalDate.parse("2021-09-15"))
                .lessons(List.of(lesson1, lesson2))
                .group(Group.builder().id(1L).build())
                .build();
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        when(lessonRepository.save(any(Lesson.class)))
                .thenReturn(lesson1)
                .thenReturn(lesson2);

        String json = "{" +
                "  \"start\": \"2021-09-01\"," +
                "  \"end\": \"2021-09-15\"," +
                "  \"lessonStartTime\": \"12:00:00\"," +
                "  \"lessonDuration\": 30," +
                "  \"dayOfWeek\": \"tuesday\"," +
                "  \"groupId\": 1" +
                "}";

        mockMvc
                .perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

//    @Test
//    void findAll() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void deleteById() {
//    }
//
//    @Test
//    void update() {
//    }
}
