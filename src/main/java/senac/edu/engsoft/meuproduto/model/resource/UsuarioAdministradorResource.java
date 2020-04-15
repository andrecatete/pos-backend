package senac.edu.engsoft.meuproduto.model.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;
import senac.edu.engsoft.meuproduto.model.UsuarioAdministrador;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude
public class UsuarioAdministradorResource extends RepresentationModel<UsuarioAdministradorResource> {

	private Long id;
	private Long cpf;
	private String nome;
	private Long telefoneContato;
	private String username;

	private String ruaEnderecoPessoal;
	private String numeroEnderecoPessoal;
	private String bairroEnderecoPessoal;
	private String cidadeEnderecoPessoal;
	private String estadoEnderecoPessoal;
	private String cepEnderecoPessoal;

	private boolean enabled;
	private boolean emailConfirmado;

	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDate dataAniversario;
	
	public UsuarioAdministradorResource() {
		super();
	}

	public UsuarioAdministradorResource(UsuarioAdministrador usuarioAdministrador) {
		super();
		this.id = usuarioAdministrador.getId();
		this.cpf = usuarioAdministrador.getCpf();
		this.nome = usuarioAdministrador.getNome();
		this.telefoneContato = usuarioAdministrador.getTelefoneContato();
		this.username = usuarioAdministrador.getUsername();
		this.ruaEnderecoPessoal = usuarioAdministrador.getRuaEnderecoPessoal();
		this.numeroEnderecoPessoal = usuarioAdministrador.getNumeroEnderecoPessoal();
		this.bairroEnderecoPessoal = usuarioAdministrador.getBairroEnderecoPessoal();
		this.cidadeEnderecoPessoal = usuarioAdministrador.getCidadeEnderecoPessoal();
		this.estadoEnderecoPessoal = usuarioAdministrador.getEstadoEnderecoPessoal();
		this.cepEnderecoPessoal = usuarioAdministrador.getCepEnderecoPessoal();
		this.dataAniversario = usuarioAdministrador.getDataAniversario();
		this.enabled = usuarioAdministrador.isEnabled();
		this.emailConfirmado = usuarioAdministrador.isEmailConfirmado();
	}

}
