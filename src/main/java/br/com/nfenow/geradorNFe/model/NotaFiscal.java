package br.com.nfenow.geradorNFe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notas_fiscais", uniqueConstraints = {@UniqueConstraint(columnNames = {"numeroNotaFiscal", "numeroSerie"})})
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 9, nullable = false)
    private String numeroNotaFiscal;

    @Column(length = 3, nullable = false)
    @NotBlank
    @Size(max = 3)
    private String numeroSerie;

    @Column(length = 80, nullable = false)
    @NotBlank
    @Size(max = 80)
    private String razaoSocial;

    @Column(length = 14, nullable = false)
    @NotBlank
    @Size(min = 14)
    private String cnpj;

    @Column(length = 125, nullable = false)
    @NotBlank
    @Size(max = 125)
    private String endereco;

    @Column(precision = 9, nullable = false)
    @NotNull
    @Min(0)
    private double valor;

    public NotaFiscal() {
    }

    public NotaFiscal(int id, String cnpj, String endereco, String razaoSocial,
                      double valor, String numeroNotaFiscal, String numeroSerie) {
        this.id = id;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.razaoSocial = razaoSocial;
        this.valor = valor;
        this.numeroNotaFiscal = numeroNotaFiscal;
        this.numeroSerie = numeroSerie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNumeroNotaFiscal() {
        return numeroNotaFiscal;
    }

    public void setNumeroNotaFiscal(String numeroNotaFiscal) {
        this.numeroNotaFiscal = numeroNotaFiscal;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
}
