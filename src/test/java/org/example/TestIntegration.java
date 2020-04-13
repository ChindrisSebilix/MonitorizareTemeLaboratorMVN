package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.*;
import org.junit.Before;
import org.junit.Test;

public class TestIntegration {
    private Validator<Student> studentValidator;
    private Validator<Tema> temaValidator;
    private Validator<Nota> notaValidator;

    private StudentXMLRepository studRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository noteRepo;

    private Service service;

    public TestIntegration() {
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
        int ret = this.service.saveStudent("2", "suc", 345);
        assert ret == 1;
    }

    @Test
    public void testSuccesAddAssignment() {
        int ret = service.saveTema("7", "hw", 5, 3);
        assert ret == 1;
    }

    @Test
    public void TestSuccesAddGrade() {
        int ret = service.saveNota("1", "6", 10.0, 5, "excelent");
        assert ret == 1;
    }

    @Test
    public void bigBangTest() {
        service.saveStudent("5", "BANG", 345);
        service.saveTema("8", "BIG", 5, 3);
        int ret = service.saveNota("5", "8", 10, 5, "Congrats");
        assert ret == 1;
    }

}
