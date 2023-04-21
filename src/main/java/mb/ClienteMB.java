package mb;

import static util.Message.addErrorMessage;
import static util.Message.setErrorMessage;
import static util.Message.setInfoMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import acesso.EscopoSessaoBean;
import bo.ClienteBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import constants.TipoPessoa;
import model.Cliente;
import model.Loja;
import model.Usuario;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.ManagedBeanHelper;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;
	private final String CAD = "/private/cadastro/cadastrarCliente.xhtml?faces-redirect=true";
	private final String EDIT = "/private/cadastro/cadastrarCliente.xhtml";
	private final String LIST = "/private/lista/listarCliente.xhtml?faces-redirect=true";

	private Cliente cliente;
	private Cliente clienteFilter;
	private List<Cliente> clientes;
	private Usuario usuarioLogado;

	private Loja lojaSelecionada;

	@PostConstruct
	public void init() {

		usuarioLogado = ManagedBeanHelper.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class).getUsuarioLogado();
		Cliente aux = RecuperarObjetoViaRequisicao.getObjeto(Cliente.class, "id");
		cliente = aux != null ? aux : new Cliente();

		lojaSelecionada = new Loja();
		clienteFilter = new Cliente();
		clientes = new ArrayList<Cliente>();
		
		
		if(usuarioLogado.getLoja() != null) {
			clienteFilter.setLoja(usuarioLogado.getLoja());
		}
		
		list();
		
	}

	public String save() {
		try {
			ClienteBO.save(cliente);
			setInfoMessage("Cadastrado com sucesso!", "Cliente " + cliente.getNome());
		} catch (ViolacaoChaveHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}
		cliente = new Cliente();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String update() {

		try {
			ClienteBO.update(cliente);
			setInfoMessage("Editado com sucesso!", "Cliente " + cliente.getNome());
		} catch (ViolacaoChaveHibernateException e) {
			addErrorMessage("Formulario", "erro.salvar.entidade.campo.existente", "");
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}
		cliente = new Cliente();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public List<Cliente> getClienteAutoComplete(String nome) {

		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			lista = ClienteBO.clienteesComplete(nome);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void gerarPDF(ActionEvent ev) {
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();

			List<Cliente> clientes = ClienteBO.clientees(clienteFilter);

			clienteFilter = new Cliente();
//			mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//			mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//			mapa.put("filtro", ClienteBO.dadosFiltro());

			GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, clientes, "clientees",
					"Relatório de clientees " + DataUtil.formatarData(new Date()), true);

		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

	public void list() {

		try {
			clientes = ClienteBO.clientees(clienteFilter);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TipoPessoa[] getTiposPessoa() {
		return TipoPessoa.values();
	}

	public String goToList() {
		return LIST;
	}

	public String prepareUpdate() {
		return EDIT;
	}

	public String prepareSave() {
		cliente = new Cliente();
		return CAD;
	}

	public String updateStatus() {
		try {

			if (cliente.getStatus()) {
				cliente.setStatus(false);
				ClienteBO.update(cliente);
			} else {
				cliente.setStatus(true);
				ClienteBO.update(cliente);
			}

		} catch (ViolacaoChaveHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (ValidacaoHibernateException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			setErrorMessage("Erro!", e.getMessage());
			e.printStackTrace();
		}

		String status = cliente.getStatus() ? "Ativo" : "Inativo";

		setInfoMessage("Status alterado com sucesso!", "O cliente está " + status);

		return null;
	}
	
	public boolean isVazia() {
		return this.clientes.isEmpty();
	}
	public boolean notVazia() {
		return !isVazia();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getClienteFilter() {
		return clienteFilter;
	}

	public void setClienteFilter(Cliente clienteFilter) {
		this.clienteFilter = clienteFilter;
	}

	public String getCAD() {
		return CAD;
	}

	public String getEDIT() {
		return EDIT;
	}

	public String getLIST() {
		return LIST;
	}

	public Loja getLojaSelecionada() {
		return lojaSelecionada;
	}

	public void setLojaSelecionada(Loja lojaSelecionada) {
		this.lojaSelecionada = lojaSelecionada;
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
