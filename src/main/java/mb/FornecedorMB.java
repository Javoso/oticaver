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
import bo.FornecedorBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import model.Fornecedor;
import model.Loja;
import model.Usuario;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.ManagedBeanHelper;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class FornecedorMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;
	private final String CAD = "/private/cadastro/cadastrarFornecedor.xhtml?faces-redirect=true";
	private final String EDIT = "/private/cadastro/cadastrarFornecedor.xhtml";
	private final String LIST = "/private/lista/listarFornecedor.xhtml?faces-redirect=true";

	private Fornecedor fornecedor;
	private Fornecedor fornecedorFilter;
	private List<Fornecedor> fornecedors;
	private Usuario usuarioLogado;

	private Loja lojaSelecionada;

	@PostConstruct
	public void init() {

		usuarioLogado = ManagedBeanHelper.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class).getUsuarioLogado();
		Fornecedor aux = RecuperarObjetoViaRequisicao.getObjeto(Fornecedor.class, "id");
		fornecedor = aux != null ? aux : new Fornecedor();

		lojaSelecionada = new Loja();
		fornecedorFilter = new Fornecedor();
		fornecedors = new ArrayList<Fornecedor>();
		
		list();
		
	}

	public String save() {
		try {
			FornecedorBO.save(fornecedor);
			setInfoMessage("Cadastrado com sucesso!", "Fornecedor " + fornecedor.getNome());
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
		fornecedor = new Fornecedor();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String update() {

		try {
			FornecedorBO.update(fornecedor);
			setInfoMessage("Editado com sucesso!", "Fornecedor " + fornecedor.getNome());
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
		fornecedor = new Fornecedor();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public List<Fornecedor> getFornecedorAutoComplete(String nome) {

		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		try {
			lista = FornecedorBO.fornecedoresComplete(nome);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void gerarPDF(ActionEvent ev) {
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();

			List<Fornecedor> fornecedors = FornecedorBO.fornecedores(fornecedorFilter);

			fornecedorFilter = new Fornecedor();
//			mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//			mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//			mapa.put("filtro", FornecedorBO.dadosFiltro());

			GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, fornecedors, "fornecedores",
					"Relatório de fornecedores " + DataUtil.formatarData(new Date()), true);

		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

	public void list() {

		try {
			fornecedors = FornecedorBO.fornecedores(fornecedorFilter);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String goToList() {
		return LIST;
	}

	public String prepareUpdate() {
		return EDIT;
	}

	public String prepareSave() {
		fornecedor = new Fornecedor();
		return CAD;
	}

	public String updateStatus() {
		try {

			if (fornecedor.getStatus()) {
				fornecedor.setStatus(false);
				FornecedorBO.update(fornecedor);
			} else {
				fornecedor.setStatus(true);
				FornecedorBO.update(fornecedor);
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

		String status = fornecedor.getStatus() ? "Ativo" : "Inativo";

		setInfoMessage("Status alterado com sucesso!", "O fornecedor está " + status);

		return null;
	}
	
	public boolean isVazia() {
		return this.fornecedors.isEmpty();
	}
	public boolean notVazia() {
		return !isVazia();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedors() {
		return fornecedors;
	}

	public void setFornecedors(List<Fornecedor> fornecedors) {
		this.fornecedors = fornecedors;
	}

	public Fornecedor getFornecedorFilter() {
		return fornecedorFilter;
	}

	public void setFornecedorFilter(Fornecedor fornecedorFilter) {
		this.fornecedorFilter = fornecedorFilter;
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
