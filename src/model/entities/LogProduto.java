package model.entities;

import model.enuns.editMenu;
import model.enuns.entradasSaidas;

import java.util.Date;

public class LogProduto{
    private final Date dataProduto;
    private final int idLog;
    private final String codigoId;

    private entradasSaidas movimentacoes;
    private int qntd;

    private editMenu opcoesEdicao;
    private String descricao;
    private Double preco;

    private String oldString;
    private Double oldDouble;

    // Construtores de EDIÇÃO

    //String construtor - Edição:CODIGO
    public LogProduto(String codigoId,String codigoIdOld, editMenu atributoMenu){
        this.dataProduto = new Date();
        this.idLog = 2;
        this.codigoId = codigoId;
        this.opcoesEdicao = atributoMenu;
        this.oldString = codigoIdOld;
    }
    //String construtor - Edição:DESCRIÇÂO
    public LogProduto(String codigoId,String descricao,String descricaoOld, editMenu atributoMenu){
        this.dataProduto = new Date();
        this.idLog = 2;
        this.codigoId = codigoId;
        this.descricao = descricao;
        this.oldString = descricaoOld;
        this.opcoesEdicao = atributoMenu;
    }


    //Double Construtor - Edição:PREÇO
    public LogProduto(String codigoId,Double preco, Double precoOld, editMenu atributoMenu){
        this.dataProduto = new Date();
        this.idLog = 2;
        this.codigoId = codigoId;
        this.preco = preco;
        this.oldDouble = precoOld;
        this.opcoesEdicao = atributoMenu;
    }

    // Construtores de Movimentação

    public LogProduto(String codigoId, Integer qntd, entradasSaidas mov) {
        this.dataProduto = new Date();
        this.idLog = 1;
        this.codigoId = codigoId;
        this.qntd = qntd;
        this.movimentacoes = mov;
    }

    public int getIdLog() {
        return idLog;
    }

    public String logEdicoes() {
        StringBuilder result = new StringBuilder();
        if (opcoesEdicao == editMenu.CODIGO) {
            result.append("Produto '")
                    .append(this.codigoId)
                    .append("' editado: ")
                    .append(editMenu.CODIGO)
                    .append(" '")
                    .append(oldString)
                    .append("' --> '")
                    .append(this.codigoId)
                    .append("' ")
                    .append(this.dataProduto);
        } else if (opcoesEdicao == editMenu.DESCRICAO) {
            result.append("Produto '")
                    .append(this.codigoId)
                    .append("' editado: ")
                    .append(editMenu.DESCRICAO)
                    .append(" '")
                    .append(oldString)
                    .append("' --> '")
                    .append(this.descricao)
                    .append("' ")
                    .append(this.dataProduto);
        } else if (opcoesEdicao == editMenu.PRECO) {
            result.append("Produto '")
                    .append(this.codigoId)
                    .append("' editado: ")
                    .append(editMenu.PRECO)
                    .append(" '")
                    .append(oldDouble)
                    .append("' --> '")
                    .append(this.preco)
                    .append("' ")
                    .append(this.dataProduto);
        }
        return result.toString();
    }

    public String logMovmentacoes(){
        StringBuilder result = new StringBuilder();
        if (movimentacoes == entradasSaidas.ENTRADAS){
            result.append("Entrada ");
        } else if (movimentacoes == entradasSaidas.SAIDAS) {
            result.append("Saídas ");
        }
        result.append(this.qntd)
                .append(" Unidades do produto cod.'")
                .append(this.codigoId)
                .append("' ")
                .append(this.dataProduto);

        return result.toString();
    }
}
