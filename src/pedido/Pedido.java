package pedido;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import cliente.Cliente;
import pessoa.Pessoa;

public class Pedido {
	public int IdPedido;
	Cliente CPFCliente = new Cliente();
	Pessoa IdPessoa = new Pessoa();
	
	LocalDateTime hoje = LocalDateTime.now();
	DateTimeFormatter formatadorTraco = 
	DateTimeFormatter.ofPattern("dd-MM-yyyy hh-mm");
	public String  DataVenda = hoje.format(formatadorTraco);
	
	public float ValorVenda;
}
