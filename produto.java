public class produto {
   private String codigo;
   private String nome;
   private int preco;
   private int quantidadetotal;
    public produto(String codigo, String nome, int preco, int quantidadetotal) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadetotal = quantidadetotal;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getQuantidadetotal() {
        return quantidadetotal;
    }
    public int getPreco() {
        return preco;
    }
    public void setPreco(int preco) {
        this.preco = preco;
    }
    public void setQuantidadetotal(int quantidadetotal) {
        this.quantidadetotal = quantidadetotal;
    }
    
}
