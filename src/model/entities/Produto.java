package model.entities;

public class Produto {
    private static Integer idProduto = 0;

    private String nome;
    private Double preco;
    private Integer qntd;

    public Produto(String nome, Double preco, Integer qntd){
        this.nome = nome;
        this.preco = preco;
        this.qntd = qntd;
        Produto.idProduto += 1;
    }

    @Override
    public String toString() {
        return "DADOS DO PRODUTO:\n\n" +
                "Nome: " + this.nome + "\n" +
                "Preço Unitário: " + this.preco + "\n" +
                "Quantidade: " + this.qntd + "\n\n" +
                "Total Em Estoque: " + String.format("%.2f", montanteEmEstoque());
    }

    public static Integer getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setQntd(Integer qntd) {
        this.qntd = qntd;
    }

    public double montanteEmEstoque() {
        return this.preco * this.qntd;
    }
}
