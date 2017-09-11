package com.example.demo.persistence.dao;

import com.example.demo.persistence.model.Lecture;

import java.util.List;

/**
 * Created by Kuba on 06.09.2017.
 */
public interface ILectureDAO {
    List<Lecture> getAllLectures();
    Lecture getLectureById(int lectureId);
    void addLecture(Lecture lecture);
    void deleteLecture(int lectureId);
    public void updateLecture(Lecture lecture);
    boolean lectureExists(String name);
}
