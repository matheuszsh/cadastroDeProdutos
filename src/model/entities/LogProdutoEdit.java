package model.entities;

import model.enuns.editMenu;

import java.util.Date;

public class LogProdutoEdit extends LogProdutoAbstract {
    private editMenu opcoesMenu;
    private String descricao;
    private Double preco;
    private String oldString;
    private Double oldDouble;

    // Edit: codigo
    public LogProdutoEdit(String codigoId,String codIdOld, editMenu opcao) {
        super();
        this.codigoId = codigoId;
        this.dataProduto = new Date();
        this.idLog = 2;
        this.opcoesMenu = opcao;
        this.oldString = codIdOld;
    }

    // Edit: Descrição
    public LogProdutoEdit(String codigoId, String descricao, String oldString, editMenu opcoesMenu) {
        super();
        this.codigoId = codigoId;
        this.dataProduto = new Date();
        this.idLog = 2;
        this.opcoesMenu = opcoesMenu;
        this.descricao = descricao;
        this.oldString = oldString;
    }

    // Edit: Preço

    public LogProdutoEdit(String codigoId, Double preco, Double oldDouble, editMenu opcoesMenu) {
        super();
        this.codigoId = codigoId;
        this.dataProduto = new Date();
        this.idLog = 2;
        this.preco = preco;
        this.opcoesMenu = opcoesMenu;
        this.oldDouble = oldDouble;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (opcoesMenu == editMenu.CODIGO) {
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
        } else if (opcoesMenu == editMenu.DESCRICAO) {
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
        } else if (opcoesMenu == editMenu.PRECO) {
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
}
