package com.example.demo.spring.controller;

import com.example.demo.hibernate.service.ILectureService;
import com.example.demo.persistence.model.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class LectureController {

    @Autowired
    private ILectureService lectureService;

    @GetMapping("lecture/{id}")
    public ResponseEntity<Lecture> getLectureById(@PathVariable("id") Integer id) {
        Lecture lecture = lectureService.getLectureById(id);
        return new ResponseEntity<Lecture>(lecture, HttpStatus.OK);
    }

    @GetMapping("lecture")
    public String lectureForm(Model model) {
        model.addAttribute("lecture", new Lecture());
        return "lecture";
    }

    @GetMapping("lectures")
    public ResponseEntity<List<Lecture>> getAllLectures() {
        List<Lecture> list = lectureService.getAllLectures();
        return new ResponseEntity<List<Lecture>>(list, HttpStatus.OK);
    }

    @PostMapping("lecture")
    public String addLecture(@ModelAttribute Lecture lecture) {
        boolean flag = lectureService.addLecture(lecture);
        if(!flag) {
            return "index";
        }
        return "index";
    }

    @PutMapping("lecture")
    public ResponseEntity<Lecture> updateLecture(@RequestBody Lecture lecture) {
        lectureService.updateLecture(lecture);
        return new ResponseEntity<Lecture>(lecture, HttpStatus.OK);
    }

    @DeleteMapping("lecture/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        lectureService.deleteLecture(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
