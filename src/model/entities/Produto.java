package model.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Produto {
    private static SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    private String codigoDeId;
    private String descricao;
    private Double preco;
    private Integer qntd;
    private Date dataDeCadastro;

    public Produto(String codigoDeId,String descricao, Double preco, Integer qntd){
        this.codigoDeId = codigoDeId;
        this.descricao = descricao;
        this.preco = preco;
        this.qntd = qntd;
        this.dataDeCadastro = new Date();
    }

    @Override
    public String toString() {
        return "DADOS DO PRODUTO:\n\n" +
                "Código De Identificação: " + this.getCodigoDeId() + "\n" +
                "Nome: " + this.descricao + "\n" +
                "Preço Unitário: R$" + String.format("%.2f", this.preco) + "\n" +
                "Quantidade: " + this.qntd + "\n" +
                "Data De Cadastro: " + dateFormat.format(dataDeCadastro) + "\n" +
                "Total Em Estoque: R$" + String.format("%.2f", montanteEmEstoque());

    }

    public String getCodigoDeId() {
        return codigoDeId;
    }

    public void setCodigoDeId(String codigoDeId) {
        this.codigoDeId = codigoDeId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQntd() {
        return qntd;
    }

    public double montanteEmEstoque() {
        return this.preco * this.qntd;
    }

    public void saidaDeEstoque(int qntd){
        this.qntd -= qntd;
    }

    public void entradaDeEstoque(int qntd){
        this.qntd += qntd;
    }
}
