package model.entities;

import model.entities.LogProduto;
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

    private List<LogProduto> logProduto = new ArrayList<>();

    public Produto(String codigoDeId, String descricao, Double preco, Integer qntd) {
        this.codigoDeId = codigoDeId;
        this.descricao = descricao;
        this.preco = preco;
        this.qntd = qntd;
        this.dataDeCadastro = new Date();
    }

    // Contructor logEntradasSaidas
    public Produto(String codigoDeId, Integer qntd) {
        this.codigoDeId = codigoDeId;
        this.qntd = qntd;
    }

    // Contructor logEditMenu
    public Produto(String codigoDeId, String descricao, Double preco) {
        this.codigoDeId = codigoDeId;
        this.descricao = descricao;
        this.preco = preco;
    }

    // Estudar sobrecarga de métodos !!!
    public Produto(String atributoId,String atributoS, String atributoOldS, editMenu atributoMenu) {
        this.codigoDeId = atributoId;
        this.descricao = atributoS;
    }

    public Produto(String atributoId, String atributoOldS, editMenu atributoMenu){
        this.codigoDeId = atributoId;
    }

    public Produto(String atributoId, Double atributoPreco, Double atributoOldPreco, editMenu atributoMenu) {
        this.codigoDeId = atributoId;
        this.preco = atributoPreco;
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
        this.logProduto.add(new LogProduto(codigoDeId, oldCodigo, editMenu.CODIGO));
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        this.dataDeCadastro = new Date();
        this.logProduto.add(new LogProduto(codigoDeId,descricao, oldDescricao, editMenu.DESCRICAO));

    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        Double oldPreco = this.preco;
        this.preco = preco;
        this.dataDeCadastro = new Date();
        this.logProduto.add(new LogProduto(codigoDeId,preco, oldPreco, editMenu.PRECO));

    }

    public Integer getQntd() {
        return qntd;
    }

    public double montanteEmEstoque() {
        return this.preco * this.qntd;
    }

    public void saidaDeEstoque(int qntd) {
        this.qntd -= qntd;
        this.logProduto.add(new LogProduto(this.codigoDeId, this.qntd, entradasSaidas.SAIDAS));
    }

    public void entradaDeEstoque(int qntd) {
        this.qntd += qntd;
        this.logProduto.add(new LogProduto(this.codigoDeId, this.qntd, entradasSaidas.ENTRADAS));
    }

    public void mostrarLogProduto(int option){
        for (LogProduto logProdutos : logProduto){
            if (logProdutos.getIdLog() == 1 && logProdutos.getIdLog() == option) {
                System.out.println(logProdutos.logMovmentacoes());
            } else if (logProdutos.getIdLog() == 2 && logProdutos.getIdLog() == option) {
                System.out.println(logProdutos.logEdicoes());
            }
        }
    }
}