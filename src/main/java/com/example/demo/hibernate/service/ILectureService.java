package com.example.demo.hibernate.service;

import com.example.demo.persistence.model.Lecture;

import java.util.List;

public interface ILectureService {
    List<Lecture> getAllLectures();
    Lecture getLectureById(int lectureId);
    boolean addLecture(Lecture lecture);
    void updateLecture(Lecture lecture);
    void deleteLecture(int lectureId);
}
