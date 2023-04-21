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
import bo.ColaboradorBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import model.Colaborador;
import model.Loja;
import model.Usuario;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.ManagedBeanHelper;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class ColaboradorMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;
	private final String CAD = "/private/cadastro/cadastrarColaborador.xhtml?faces-redirect=true";
	private final String EDIT = "/private/cadastro/cadastrarColaborador.xhtml";
	private final String LIST = "/private/lista/listarColaborador.xhtml?faces-redirect=true";

	private Colaborador colaborador;
	private Colaborador colaboradorFilter;
	private List<Colaborador> colaboradors;
	private Usuario usuarioLogado;

	private Loja lojaSelecionada;

	@PostConstruct
	public void init() {

		usuarioLogado = ManagedBeanHelper.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class).getUsuarioLogado();
		Colaborador aux = RecuperarObjetoViaRequisicao.getObjeto(Colaborador.class, "id");
		colaborador = aux != null ? aux : new Colaborador();

		lojaSelecionada = new Loja();
		colaboradorFilter = new Colaborador();
		colaboradors = new ArrayList<Colaborador>();
		
		
		if(usuarioLogado.getLoja() != null) {
			colaboradorFilter.setLoja(usuarioLogado.getLoja());
		}
		
		list();
		
	}

	public String save() {
		try {
			ColaboradorBO.save(colaborador);
			setInfoMessage("Cadastrado com sucesso!", "Colaborador " + colaborador.getNome());
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
		colaborador = new Colaborador();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String update() {

		try {
			ColaboradorBO.update(colaborador);
			setInfoMessage("Editado com sucesso!", "Colaborador " + colaborador.getNome());
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
		colaborador = new Colaborador();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public List<Colaborador> getColaboradorAutoComplete(String nome) {

		List<Colaborador> lista = new ArrayList<Colaborador>();
		try {
			lista = ColaboradorBO.colaboradoresComplete(nome);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void gerarPDF(ActionEvent ev) {
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();

			List<Colaborador> colaboradors = ColaboradorBO.colaboradores(colaboradorFilter);

			colaboradorFilter = new Colaborador();
//			mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//			mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//			mapa.put("filtro", ColaboradorBO.dadosFiltro());

			GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, colaboradors, "colaboradores",
					"Relatório de colaboradores " + DataUtil.formatarData(new Date()), true);

		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

	public void list() {

		try {
			colaboradors = ColaboradorBO.colaboradores(colaboradorFilter);
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
		colaborador = new Colaborador();
		return CAD;
	}

	public String updateStatus() {
		try {

			if (colaborador.getStatus()) {
				colaborador.setStatus(false);
				ColaboradorBO.update(colaborador);
			} else {
				colaborador.setStatus(true);
				ColaboradorBO.update(colaborador);
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

		String status = colaborador.getStatus() ? "Ativo" : "Inativo";

		setInfoMessage("Status alterado com sucesso!", "O colaborador está " + status);

		return null;
	}
	
	public boolean isVazia() {
		return this.colaboradors.isEmpty();
	}
	public boolean notVazia() {
		return !isVazia();
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradors() {
		return colaboradors;
	}

	public void setColaboradors(List<Colaborador> colaboradors) {
		this.colaboradors = colaboradors;
	}

	public Colaborador getColaboradorFilter() {
		return colaboradorFilter;
	}

	public void setColaboradorFilter(Colaborador colaboradorFilter) {
		this.colaboradorFilter = colaboradorFilter;
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
