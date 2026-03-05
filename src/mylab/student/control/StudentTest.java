package mylab.student.control;

import mylab.student.entity.Student;
import mylab.student.exception.InvalidGradeException;

public class StudentTest {
    public static void main(String[] args) {
        try {
            Student k = new Student("202401", "김민수", "컴퓨터공학", 3);
            System.out.println(k.getName() + " / " + k.getMajor() + " / " + k.getGrade() + "학년");

            System.out.println("5학년으로 변경");
            
            k.setGrade(5);

        } catch (InvalidGradeException e) {
            System.out.println(e.getMessage());
        }
    }
}