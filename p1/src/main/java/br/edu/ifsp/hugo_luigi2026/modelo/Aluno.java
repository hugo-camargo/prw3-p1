package br.edu.ifsp.hugo_luigi2026.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.math.RoundingMode;

// FEITO POR:
// LUIGI ANTONIO LODDI VANZELLA
// HUGO CAMARGO
@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String ra;
    private String email;
    @DecimalMin(value = "0.0", message = "A nota não pode ser menor que 0")
    @DecimalMax(value = "10.0", message = "A nota não pode ser maior que 10")
    private BigDecimal nota1;

    @DecimalMin(value = "0.0", message = "A nota não pode ser menor que 0")
    @DecimalMax(value = "10.0", message = "A nota não pode ser maior que 10")
    private BigDecimal nota2;

    @DecimalMin(value = "0.0", message = "A nota não pode ser menor que 0")
    @DecimalMax(value = "10.0", message = "A nota não pode ser maior que 10")
    private BigDecimal nota3;

    public Aluno(){}

    public Aluno(Long id, String nome, String ra, String email, BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {
        this.id = id;
        this.nome = nome;
        this.ra = ra;
        this.email = email;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getRa() {
        return ra;
    }
    public void setRa(String ra) {
        this.ra = ra;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public BigDecimal getNota1() {
        return nota1;
    }
    public void setNota1(BigDecimal nota1) {
        this.nota1 = nota1;
    }


    public BigDecimal getNota2() {
        return nota2;
    }
    public void setNota2(BigDecimal nota2) {
        this.nota2 = nota2;
    }


    public BigDecimal getNota3() {
        return nota3;
    }
    public void setNota3(BigDecimal nota3) {
        this.nota3 = nota3;
    }

    public BigDecimal getMedia(){
        if (nota1 == null || nota2 == null || nota3 == null) return BigDecimal.ZERO;
        BigDecimal soma = nota1.add(nota2).add(nota3);
        return soma.divide(new BigDecimal("3"), 2, RoundingMode.HALF_UP);
    }
    public String status(){
        if(this.getMedia().doubleValue() >= 6)
            return "APROVADO";
        if(this.getMedia().doubleValue() < 6 && this.getMedia().doubleValue() >= 4 ){
            return "RECUPERAÇÃO";
        }
        else{
            return "REPROVADO";
        }
    }

}