package com.tarea7.dgmt07e03.Domain;

import com.tarea7.dgmt07e03.Modelos.Genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sortDTO {
    private String sortname;
    private Genero sortgender;
    private Long sortdepto;
}
