package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class Controller {

    private final EventRepository eventRepository;

    @PostMapping
    public HttpEntity<Object> save (@RequestBody @Valid EventDTO eventDTO, Errors errors) {


        if (errors.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(errors.getAllErrors().get(0).getDefaultMessage());
        }

        var event = new Event(null, eventDTO.getTitle(), eventDTO.getContent());

        return ResponseEntity.ok(eventRepository.save(event));
    }

    @GetMapping
    public HttpEntity<List<Event>> getList () {
        return ResponseEntity.ok(eventRepository.findAll());
    }


}
