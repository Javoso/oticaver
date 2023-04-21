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
import bo.ProdutoBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import model.Produto;
import model.Loja;
import model.Usuario;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.ManagedBeanHelper;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;
	private final String CAD = "/private/cadastro/cadastrarProduto.xhtml?faces-redirect=true";
	private final String EDIT = "/private/cadastro/cadastrarProduto.xhtml";
	private final String LIST = "/private/lista/listarProduto.xhtml?faces-redirect=true";

	private Produto produto;
	private Produto produtoFilter;
	private List<Produto> produtos;
	private Usuario usuarioLogado;

	private Loja lojaSelecionada;

	@PostConstruct
	public void init() {

		usuarioLogado = ManagedBeanHelper.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class).getUsuarioLogado();
		Produto aux = RecuperarObjetoViaRequisicao.getObjeto(Produto.class, "id");
		produto = aux != null ? aux : new Produto();

		lojaSelecionada = new Loja();
		produtoFilter = new Produto();
		produtos = new ArrayList<Produto>();
		
		list();
		
	}

	public String save() {
		try {
			ProdutoBO.save(produto);
			setInfoMessage("Cadastrado com sucesso!", "Produto " + produto.getNome());
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
		produto = new Produto();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String update() {

		try {
			ProdutoBO.update(produto);
			setInfoMessage("Editado com sucesso!", "Produto " + produto.getNome());
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
		produto = new Produto();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public List<Produto> getProdutoAutoComplete(String nome) {

		List<Produto> lista = new ArrayList<Produto>();
		try {
			lista = ProdutoBO.produtoesComplete(nome);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void gerarPDF(ActionEvent ev) {
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();

			List<Produto> produtos = ProdutoBO.produtoes(produtoFilter);

			produtoFilter = new Produto();
//			mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//			mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//			mapa.put("filtro", ProdutoBO.dadosFiltro());

			GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, produtos, "produtoes",
					"Relatório de produtoes " + DataUtil.formatarData(new Date()), true);

		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

	public void list() {

		try {
			produtos = ProdutoBO.produtoes(produtoFilter);
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
		produto = new Produto();
		return CAD;
	}

	public String updateStatus() {
		try {

			if (produto.getStatus()) {
				produto.setStatus(false);
				ProdutoBO.update(produto);
			} else {
				produto.setStatus(true);
				ProdutoBO.update(produto);
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

		String status = produto.getStatus() ? "Ativo" : "Inativo";

		setInfoMessage("Status alterado com sucesso!", "O produto está " + status);

		return null;
	}
	
	public boolean isVazia() {
		return this.produtos.isEmpty();
	}
	public boolean notVazia() {
		return !isVazia();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(Produto produtoFilter) {
		this.produtoFilter = produtoFilter;
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
