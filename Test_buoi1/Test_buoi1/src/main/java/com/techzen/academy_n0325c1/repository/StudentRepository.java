package com.techzen.academy_n0325c1.repository;

import com.techzen.academy_n0325c1.model.Student;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentRepository implements IStudentRepository {

    public List<Student> finAll() {
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("select id, name, score from student");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .score(resultSet.getDouble("score"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public Student findById(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("select id, name, score from student where id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return (Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .score(resultSet.getDouble("score"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Student save(Student student) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("insert into student (name, score) values (?, ?)");

            preparedStatement.setString(1, student.getName());
            preparedStatement.setDouble(2, student.getScore());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }


}
