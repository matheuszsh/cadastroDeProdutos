package model.entities;

import model.enuns.entradasSaidas;

import java.util.Date;

public class LogProdutoMov extends LogProdutoAbstract{

    private entradasSaidas movimentacoes;
    private int qntd;

    public LogProdutoMov(String codigoId, Integer qntd,entradasSaidas movimentacoes) {
        super();
        this.codigoId = codigoId;
        this.dataProduto = new Date();
        this.idLog = 1;
        this.movimentacoes = movimentacoes;
        this.qntd = qntd;
    }

    @Override
    public String toString() {
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
