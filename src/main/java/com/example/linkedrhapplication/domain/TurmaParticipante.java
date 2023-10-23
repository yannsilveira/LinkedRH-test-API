package com.example.linkedrhapplication.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TurmaParticipante {

    private int codigo;
    private Turma turma;
    private List<Funcionario> funcionarios;
}
