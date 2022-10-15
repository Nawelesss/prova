import java.time.LocalDate;
import java.time.LocalDateTime;


public class venda  {
    
    private LocalDate data;
    private String nome;
    private int preco;
    private int quantidadeVendida;
    private int valorVenda;
    
    public venda(LocalDate localDateTime, String nome, int preco, int quantidadeVendida) {
        this.data = localDateTime.now();
        this.nome = nome;
        this.preco = preco;
        this.quantidadeVendida = quantidadeVendida;
        this.valorVenda = quantidadeVendida*preco;
    }
    public String toString() {
        return String.format("Data: %s - Motorista: %s - Passageiro: %s - Valor: %s", 
            this.data, this.nome, this.preco, this.quantidadeVendida);
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getquantidadeVendida() {
        return quantidadeVendida;
    }

    public void setquantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
    public int getValorVenda() {
        return valorVenda;
    }
    
    
}
