package com.example.demo.hibernate.service.impl;

import com.example.demo.hibernate.service.ILectureService;
import com.example.demo.persistence.dao.ILectureDAO;
import com.example.demo.persistence.model.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService implements ILectureService {

    @Autowired
    private ILectureDAO lectureDAO;

    @Override
    public List<Lecture> getAllLectures() {
        return lectureDAO.getAllLectures();
    }

    @Override
    public Lecture getLectureById(int lectureId) {
        return lectureDAO.getLectureById(lectureId);
    }

    @Override
    public boolean addLecture(Lecture lecture) {
        if(lectureDAO.lectureExists(lecture.getName())) {
            return false;
        }
        else {
            lectureDAO.addLecture(lecture);
            return true;
        }
    }

    @Override
    public void updateLecture(Lecture lecture) {
        lectureDAO.updateLecture(lecture);
    }

    @Override
    public void deleteLecture(int lectureId) {
        lectureDAO.deleteLecture(lectureId);
    }
}
