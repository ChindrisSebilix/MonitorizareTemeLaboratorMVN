package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTopDown {

    private Validator<Student> studentValidator;
    private Validator<Tema> temaValidator;
    private Validator<Nota> notaValidator;

    private StudentXMLRepository studRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository noteRepo;

    private Service service;

    public TestTopDown() {
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
    public void testSuccessAddStudent() {
        int ret = this.service.saveStudent("1", "suc", 345);
        assert ret == 1;
    }

    @Test
    public void testSuccessAddAssignment() {
        int ret = service.saveStudent("2", "perf", 456);
        assert ret == 1;

        int ret2 = service.saveTema("1", "hw", 5, 3);
        assert ret2 == 1;
    }

    @Test
    public void testSuccessAddGrade() {

        int ret = service.saveStudent("3", "gr", 456);
        assert ret == 1;

        int ret2 = service.saveTema("2", "ade", 5, 3);
        assert ret2 == 1;
        int ret3 = service.saveNota("3", "2", 10.0, 5, "excelent");
        assert ret3 == 1;
    }

    @After
    public void clearRepos() {

        studRepo.clear();
        temaRepo.clear();
        noteRepo.clear();
    }
}
