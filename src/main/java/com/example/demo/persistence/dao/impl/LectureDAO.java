package com.example.demo.persistence.dao.impl;

import com.example.demo.persistence.dao.ILectureDAO;
import com.example.demo.persistence.model.Lecture;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class LectureDAO implements ILectureDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Lecture> getAllLectures() {
        String hql = "FROM Lecture as lctr ORDER BY lctr.lecturerId";
        return (List<Lecture>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Lecture getLectureById(int lectureId) {
        return entityManager.find(Lecture.class, lectureId);
    }

    @Override
    public void addLecture(Lecture lecture) {
        entityManager.persist(lecture);
    }

    @Override
    public void updateLecture(Lecture pLecture) {
        Lecture lecture = getLectureById(pLecture.getLecturerId());
        lecture.setNumberOfHours(pLecture.getNumberOfHours());
        lecture.setName(pLecture.getName());
        lecture.setLecturerId(pLecture.getLecturerId());
        lecture.setGroupId(pLecture.getGroupId());
        entityManager.flush();
    }

    @Override
    public void deleteLecture(int lectureId) {
        entityManager.remove(getLectureById(lectureId));
    }

    @Override
    public boolean lectureExists(String name) {
        System.out.println(name);
        String hql = "FROM Lecture as lctr WHERE lctr.name = :name";
        boolean count = entityManager.createQuery(hql).setParameter("name", name)
                .getResultList().isEmpty();
        return !count;
    }
}
