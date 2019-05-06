package com.vlad.db.controller;

import com.vlad.db.domain.Group;
import com.vlad.db.domain.Student;
import com.vlad.db.repo.GroupRepo;
import com.vlad.db.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    GroupRepo groupRepo;

    private List<Group> groupsList;

    private void setModelList(Model model) {
        groupsList = (List) groupRepo.findAll();
        model.addAttribute("groupsList", groupsList);

    }

    @GetMapping("/addstudent")
    public String showSignUpForm(Student student, Model model) {
        setModelList(model);
        return "add-student";
    }

    @PostMapping("/newStudent")
    public String addNewUser(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentRepo.save(student);
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @GetMapping("/")
    public String initUsers(Model model) {

        // model.addAttribute("students", studentRepo.findAll());
        return "index";
    }

    @GetMapping("/students")
    public String getAllUsers(Model model) {

        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @GetMapping("/editstudent/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Student student = studentRepo.findById(id).get();
        setModelList(model);
        model.addAttribute("student", student);
        return "update-student";
    }

    @PostMapping("/updatestudent/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid Student student,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }

        studentRepo.save(student);
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @GetMapping("/deletestudent/{id}")
    public String deleteStudent(@PathVariable("id") long id, Model model) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //studentRepo.delete(student);
        student.getGroup().getStudents().remove(student);
        studentRepo.delete(student);

        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

}
