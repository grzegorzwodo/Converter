package com.billennium.currency.converter.nbp;

import com.billennium.currency.converter.converter.ConverterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.billennium.currency.converter.converter.ConverterService.courses;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
@Component
@Slf4j
public class NbpLoader {

    private final RestTemplate restTemplate;
    @Value("${nbp.url}")
    private String url;
    @Value("#{'${nbp.currency.tables}'.split(',')}")
    private List<String> tables;

    @Scheduled(cron = "${nbp.cron.expression}")
    public  void scheduleNbpLoader(){
        setMapCourses();
    }
    @PostConstruct
    public void init() {
        setMapCourses();
    }

    public ActualCourse getCourse(String tableId) {
        return requireNonNull(restTemplate.getForEntity(url + tableId + "?format=json", ActualCourse[].class).getBody())[0];
    }

    public void setMapCourses() {
        log.info("Start loading courses");
        courses = new HashMap<>();
        tables.stream().forEach(tableId -> courses.putAll(requireNonNull(
                getCourse(tableId).getRates().stream().collect(toMap(Rate::getCode, Rate::getMid)))
        ));
        log.info("Finished loading courses");
    }
}
