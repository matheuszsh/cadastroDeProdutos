package model.entities;

import model.enuns.editMenu;
import model.enuns.entradasSaidas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Produto<T>{
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private String codigoDeId;
    private String descricao;
    private Double preco;
    private Integer qntd;
    private Date dataDeCadastro;

    private List<LogProdutoAbstract> logProduto = new ArrayList<>();

    public Produto(){
        this.preco = 0.0;
        this.qntd = 0;
        this.dataDeCadastro = new Date();
    }

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

    public void setQntd(Integer qntd){
        this.qntd = qntd;
    }

    public LogProdutoMov getLastLog(){
       return (LogProdutoMov) this.logProduto.get(logProduto.size() - 1);
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

    public void gerarLogEdicoes(int selector, T arg) {
        this.dataDeCadastro = new Date();

        switch (selector) {
            case 1:
                String oldCodigo = this.codigoDeId;
                setCodigoDeId((String) arg);
                this.logProduto.add(new LogProdutoEdit(codigoDeId, oldCodigo, editMenu.CODIGO));
                break;
            case 2:
                String oldDescricao = this.descricao;
                setDescricao((String) arg);
                this.logProduto.add(new LogProdutoEdit(codigoDeId, descricao, oldDescricao, editMenu.DESCRICAO));
                break;
            case 3:
                Double oldPreco = this.preco;
                setPreco((Double) arg);
                this.logProduto.add(new LogProdutoEdit(codigoDeId, preco, oldPreco, editMenu.PRECO));
                break;
        }
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