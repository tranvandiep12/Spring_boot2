package com.techzen.academy_n0325c1.repository;

import com.techzen.academy_n0325c1.model.Student;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository {

    public List<Student> findAll() {
        Session session = ConnectionUtil.sessionFactory.openSession(); // Bước 1: Mở phiên làm việc (Session) từ ConnectionUtil
        List<Student> students = null;
        try {
            students = session.createQuery("FROM Student").getResultList(); // Bước 2: Sử dụng HQL để lấy danh sách sinh viên
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // Bước 3: Đóng phiên làm việc sau khi lấy danh sách xong
        }
        return students;
    }


    public Student save(Student student) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) { // B1
            Transaction transaction = session.beginTransaction(); // B2

            try {

                session.saveOrUpdate(student); // B3

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback(); // Rollback nếu có lỗi
                }
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    public Student findById(int id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        String sql = "SELECT * FROM Student WHERE id = :id";
        Query<Student> query = session.createNativeQuery(sql, Student.class);

        query.setParameter("id", id); // Chuyển đổi UUID thành String

        return query.uniqueResultOptional().get();
    }
}
