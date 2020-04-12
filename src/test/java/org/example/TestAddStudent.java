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

        this.service = new Service(studRepo, temaRepo, noteRepo); }

    @Before
    public void setup() { this.service = new Service(studRepo, temaRepo, noteRepo); }

    @Test
    public void testSuccesAddStudent(){

        try {
            int ret = service.saveStudent("5", "succesful", 200);
            assert ret==0;
            int ret2 = service.saveStudent("6", "succesful2", 300);
            assert ret2==0;
        }
        catch (ValidationException vex)
        {
            assert false;
        }
    }

    /*
    @Test
    public void testFailAddStudent(){

    }*/
}
