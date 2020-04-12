package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.*;
import org.example.service.Service;
import org.example.validation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

public class TestAddStudent {

    private Validator<Student> studentValidator;
    private Validator<Tema> temaValidator;
    private Validator<Nota> notaValidator;

    private StudentXMLRepository studRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository noteRepo;

    private Service service;

    public TestAddStudent() {
        this.studentValidator = new StudentValidator();
        this.temaValidator = new TemaValidator();
        this.notaValidator = new NotaValidator();

        this.studRepo = new StudentXMLRepository(studentValidator, "studenti.xml");
        this.temaRepo = new TemaXMLRepository(temaValidator, "teme.xml");
        this.noteRepo = new NotaXMLRepository(notaValidator, "note.xml");

        this.service = new Service(studRepo, temaRepo, noteRepo);
    }

    @Before
    public void setup() {
        this.service = new Service(studRepo, temaRepo, noteRepo);
    }

    @Test
    public void testSuccesAddStudent() {

        try {
            int ret = service.saveStudent("5", "succesful", 200);
            assert ret == 1;
            int ret2 = service.saveStudent("6", "succesful2", 300);
            assert ret2 == 1;
        } catch (ValidationException vex) {
            assert false;
        }

        //BVA
        try {
            service.saveStudent("20", "success", 111);
            assert true;
        } catch (ValidationException e) {
            assert false;
        }
        try {
            service.saveStudent("21", "fail", 937);
            assert true;
        } catch (ValidationException e) {
            assert false;
        }
    }


    @Test
    public void testFailAddStudent() {
        /*
        try{
            int ret = service.saveStudent(1, "id is not string", 400);
            assert ret==1;
        } catch (ValidationException e){
            assert true;
        }
        try{
            int ret = service.saveStudent("10", 200, 400); // name not string
            assert ret==1;
        } catch (ValidationException e){
            assert true;
        }
        */
        try {
            int ret = service.saveStudent("", "id is empty string", 300);
            assert ret == 1;
        } catch (ValidationException e) {
            assert true;
        }
        try {
            int ret = service.saveStudent(null, "id is null", 400);
            assert ret == 1;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveStudent("7", "", 500);
            assert ret == 1;
        } catch (ValidationException e) {
            assert true;
        }
        try {
            int ret = service.saveStudent("8", null, 600);
            assert ret == 1;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            service.saveStudent("12", null, 300);
            assert false;
        } catch (ValidationException e) {
            assert true;

        }
//        try {
//            service.saveStudent("13", "fail", 300.0);
//            assert false;
//        } catch (Exception e) {
//            assert true;
//        }
        try {
            service.saveStudent("14", "fail", 110);
            assert false;
        } catch (ValidationException e) {
            assert true;
        }


        try {
            service.saveStudent("16", "fail", 938);
            assert false;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            service.saveStudent("17", "fail", -2);
            assert false;
        } catch (ValidationException e) {
            assert true;
        }
    }
    @Test
    public void TestSuccesAddAssignment(){

    }

    @Test
    public void TestSuccesAddAssignment(){

    }

}
