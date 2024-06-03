package model.entities;

import model.enuns.editMenu;
import model.enuns.entradasSaidas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Produto {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String codigoDeId;
    private String descricao;
    private Double preco;
    private Integer qntd;
    private Date dataDeCadastro;

    private List<LogProdutoAbstract> logProduto = new ArrayList<>();

    public Produto(String codigoDeId, String descricao, Double preco, Integer qntd) {
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
                "Descrição: " + this.descricao + "\n" +
                "Preço Unitário: R$" + String.format("%.2f", this.preco) + "\n" +
                "Quantidade: " + this.qntd + "\n" +
                "Update: " + dateFormat.format(dataDeCadastro) + "\n" +
                "Total Em Estoque: R$" + String.format("%.2f", montanteEmEstoque());

    }

    public String getCodigoDeId() {
        return codigoDeId;
    }

    public void setCodigoDeId(String codigoDeId) {
        String oldCodigo = this.codigoDeId;
        this.codigoDeId = codigoDeId;
        this.dataDeCadastro = new Date();
        this.logProduto.add(new LogProdutoEdit(codigoDeId, oldCodigo, editMenu.CODIGO));
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        this.dataDeCadastro = new Date();
        this.logProduto.add(new LogProdutoEdit(codigoDeId,descricao, oldDescricao, editMenu.DESCRICAO));

    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        Double oldPreco = this.preco;
        this.preco = preco;
        this.dataDeCadastro = new Date();
        this.logProduto.add(new LogProdutoEdit(codigoDeId,preco, oldPreco, editMenu.PRECO));

    }

    public Integer getQntd() {
        return qntd;
    }

    public double montanteEmEstoque() {
        return this.preco * this.qntd;
    }

    public void saidaDeEstoque(int qntd) {
        this.qntd -= qntd;
        this.logProduto.add(new LogProdutoMov(this.codigoDeId, qntd, entradasSaidas.SAIDAS));
    }

    public void entradaDeEstoque(int qntd) {
        this.qntd += qntd;
        this.logProduto.add(new LogProdutoMov(this.codigoDeId, qntd, entradasSaidas.ENTRADAS));
    }

    public void mostrarLogEdicoes(){
        for (LogProdutoAbstract logDoProduto : logProduto){
            if (logDoProduto instanceof LogProdutoEdit) {
                System.out.println(logDoProduto);
            }
        }
    }

    public void mostrarLogMov(){
        for (LogProdutoAbstract logDoProduto : logProduto){
            if (logDoProduto instanceof LogProdutoMov) {
                System.out.println(logDoProduto);
            }
        }
    }
}