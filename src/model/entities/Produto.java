package model.entities;

public class Produto {
    private String codigoDeId;
    private String nome;
    private Double preco;
    private Integer qntd;

    public Produto(String codigoDeId,String nome, Double preco, Integer qntd){
        this.codigoDeId = codigoDeId;
        this.nome = nome;
        this.preco = preco;
        this.qntd = qntd;
    }

    @Override
    public String toString() {
        return "DADOS DO PRODUTO:\n\n" +
                "Código De Identificação: " + this.getCodigoDeId() + "\n" +
                "Nome: " + this.nome + "\n" +
                "Preço Unitário: R$" + String.format("%.2f", this.preco) + "\n" +
                "Quantidade: " + this.qntd + "\n\n" +
                "Total Em Estoque: R$" + String.format("%.2f", montanteEmEstoque());
    }

    public String getCodigoDeId() {
        return codigoDeId;
    }

    public void setCodigoDeId(String codigoDeId) {
        this.codigoDeId = codigoDeId;
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
