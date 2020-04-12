package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.*;
import org.example.service.Service;
import org.example.validation.*;
import org.junit.Before;
import org.junit.Test;

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
            ret = service.saveStudent("6", "succesfulAgain", 300);
            assert ret == 1;
        } catch (ValidationException vex) {
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
        try{
            int ret = service.saveStudent("", "id is empty string", 300);
            assert ret==1;
        } catch (ValidationException e){
            assert true;
        }
        try{
            int ret = service.saveStudent(null, "id is null", 400);
            assert ret==1;
        } catch (ValidationException e){
            assert true;
        }

        try{
            int ret = service.saveStudent("7", "", 500);
            assert ret==1;
        } catch (ValidationException e){
            assert true;
        }
        try{
            int ret = service.saveStudent("8", null, 600);
            assert ret==1;
        } catch (ValidationException e){
            assert true;
        }

    }
}
