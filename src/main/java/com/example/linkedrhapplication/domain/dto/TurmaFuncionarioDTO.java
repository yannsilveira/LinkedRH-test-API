package com.example.linkedrhapplication.domain.dto;

import com.example.linkedrhapplication.domain.Turma;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TurmaFuncionarioDTO {

    private Turma turma;
    private int funcionarioQuantidade;
}
