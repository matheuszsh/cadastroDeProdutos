package model.entities;

import model.enuns.editMenu;
import model.enuns.entradasSaidas;

import java.util.Date;

public class LogProduto extends Produto{
    private Date dataProduto;
    private int idLog;
    private entradasSaidas movimentacoes;
    private editMenu opcoesEdicao;
    private String oldString;
    private Double oldDouble;

    // Construtores de EDIÇÃO

    //String construtor
    public LogProduto(String atributoS,String atributoOldS, editMenu atributoMenu){
        super(atributoS, atributoOldS, atributoMenu);
        this.dataProduto = new Date();
        this.idLog = 2;
        this.opcoesEdicao = atributoMenu;
        this.oldString = atributoOldS;
    }

    //Double Construtor
    public LogProduto(Double atributoPreco, Double atributoOldPreco, editMenu atributoMenu){
        super(atributoPreco,atributoOldPreco,atributoMenu);
        this.dataProduto = new Date();
        this.idLog = 2;
        this.opcoesEdicao = atributoMenu;
        this.oldDouble = atributoOldPreco;
    }


    // Construtores de Movimentação
    public LogProduto(String codigoDeId, Integer qntd, entradasSaidas mov) {
        super(codigoDeId, qntd);
        this.dataProduto = new Date();
        this.idLog = 1;
        this.movimentacoes = mov;
    }

    public int getIdLog() {
        return idLog;
    }

    public String logEdicoes() {
        String result = null;
        if (opcoesEdicao == editMenu.CODIGO) {
            result = "Produto '" + this.getCodigoDeId() + "' editado: " + editMenu.CODIGO + " '" + oldString +
                    "' --> '" + this.getCodigoDeId() + "' " + this.dataProduto;
        } else if (opcoesEdicao == editMenu.DESCRICAO) {
            result = "Produto '" + this.getCodigoDeId() + "' editado: " + editMenu.DESCRICAO + " '" + oldString +
                    "' --> '" + this.getDescricao() + "' " + this.dataProduto;
        } else if (opcoesEdicao == editMenu.PRECO) {
            result = "Produto '" + this.getCodigoDeId() + "' editado: " + editMenu.PRECO + " '" + oldDouble +
                    "' --> '" + this.getCodigoDeId() + this.dataProduto;
        }
        return result;
    }

    public String logMovmentacoes(){
        String result = null;
        if (movimentacoes == entradasSaidas.ENTRADAS){
            result = "Entrada " + getQntd() + "Unidades do produto cod.'" + getCodigoDeId() + "' " + this.dataProduto;
        } else if (movimentacoes == entradasSaidas.SAIDAS) {
            result = "Saídas " + getQntd() + "Unidades do produto cod.'" + getCodigoDeId() + "' " + this.dataProduto;

        }
        return result;
    }
}
