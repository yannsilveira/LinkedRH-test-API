package com.example.linkedrhapplication.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Turma {

    private int codigo;
    private Date inicio;
    private Date fim;
    private String local;
    private Curso curso;
}
