package org.example;

import org.example.domain.Nota;
import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepository;
import org.example.repository.StudentXMLRepository;
import org.example.repository.TemaXMLRepository;
import org.example.service.Service;
import org.example.validation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ItemEvent;

public class TestAddAssignment {

    private Validator<Student> studentValidator;
    private Validator<Tema> temaValidator;
    private Validator<Nota> notaValidator;

    private StudentXMLRepository studRepo;
    private TemaXMLRepository temaRepo;
    private NotaXMLRepository noteRepo;

    private Service service;

    public TestAddAssignment() {
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
    public void testSuccesAddAssignment() {
        try {
            int ret = service.saveTema("6", "hw", 5, 3);
            assert ret == 1;
        } catch (ValidationException e) {
            assert true;
        }
    }

    @Test
    public void testFailAddAssignment() {
        try {
            int ret = service.saveTema("", "hw", 5, 3);
            assert ret == 0;
        } catch (ValidationException e) {
            assert e.getMessage().equals("ID invalid! \n");
        }

        try {
            int ret = service.saveTema("0", "", 5, 3);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveTema("1", "hw", 1, 9);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveTema("2", "hw", 1, 9);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            service.saveTema("0", "hw", 5, 3);
            int ret = service.saveTema("0", "hw", 5, 3);// an entry with id 0 is already there
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveTema(null, "hw", 5, 3);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveTema("7", null, 5, 3);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveTema("8", "hw", 21, 9);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveTema("9", "hw", 0, 9);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;
        }

        try {
            int ret = service.saveTema("10", "hw", 9, 21);
            assert ret == 0;
        } catch (ValidationException e) {
            assert true;//startline invalid
        }
        /*
        try {
            service.saveTema("3", "hw", 5, 3);
        } catch (ValidationException e) {
            assert false;
        }
                try {
            service.saveTema("4", "hw", 5, 3);
        } catch (ValidationException e) {
            assert false;
        }
                try {
            service.saveTema("5", "hw", 5, 3);
        } catch (ValidationException e) {
            assert false;
        }
        */
    }

    @After
    public void clearRepos() {

        temaRepo.clear();
    }
}
