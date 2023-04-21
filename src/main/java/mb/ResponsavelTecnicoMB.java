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
import bo.ResponsavelTecnicoBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import model.ResponsavelTecnico;
import model.Loja;
import model.Usuario;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.ManagedBeanHelper;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class ResponsavelTecnicoMB implements Serializable {

	private static final long serialVersionUID = 8197346996386546555L;
	private final String CAD = "/private/cadastro/cadastrarResponsavelTecnico.xhtml?faces-redirect=true";
	private final String EDIT = "/private/cadastro/cadastrarResponsavelTecnico.xhtml";
	private final String LIST = "/private/lista/listarResponsavelTecnico.xhtml?faces-redirect=true";

	private ResponsavelTecnico responsavelTecnico;
	private ResponsavelTecnico responsavelTecnicoFilter;
	private List<ResponsavelTecnico> responsavelTecnicos;
	private Usuario usuarioLogado;

	private Loja lojaSelecionada;

	@PostConstruct
	public void init() {

		usuarioLogado = ManagedBeanHelper.recuperaBean("escopoSessaoBean", EscopoSessaoBean.class).getUsuarioLogado();
		ResponsavelTecnico aux = RecuperarObjetoViaRequisicao.getObjeto(ResponsavelTecnico.class, "id");
		responsavelTecnico = aux != null ? aux : new ResponsavelTecnico();

		lojaSelecionada = new Loja();
		responsavelTecnicoFilter = new ResponsavelTecnico();
		responsavelTecnicos = new ArrayList<ResponsavelTecnico>();
		
		
		if(usuarioLogado.getLoja() != null) {
			responsavelTecnicoFilter.setLoja(usuarioLogado.getLoja());
		}
		
		list();
		
	}

	public String save() {
		try {
			ResponsavelTecnicoBO.save(responsavelTecnico);
			setInfoMessage("Cadastrado com sucesso!", "ResponsavelTecnico " + responsavelTecnico.getNome());
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
		responsavelTecnico = new ResponsavelTecnico();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public String update() {

		try {
			ResponsavelTecnicoBO.update(responsavelTecnico);
			setInfoMessage("Editado com sucesso!", "ResponsavelTecnico " + responsavelTecnico.getNome());
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
		responsavelTecnico = new ResponsavelTecnico();
		AfterRedirect.manterMensagem();
		return LIST;
	}

	public List<ResponsavelTecnico> getResponsavelTecnicoAutoComplete(String nome) {

		List<ResponsavelTecnico> lista = new ArrayList<ResponsavelTecnico>();
		try {
			lista = ResponsavelTecnicoBO.responsavelTecnicoesComplete(nome);
		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public void gerarPDF(ActionEvent ev) {
		try {
			Map<String, Object> mapa = new HashMap<String, Object>();

			List<ResponsavelTecnico> responsavelTecnicos = ResponsavelTecnicoBO.responsavelTecnicoes(responsavelTecnicoFilter);

			responsavelTecnicoFilter = new ResponsavelTecnico();
//			mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//			mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//			mapa.put("filtro", ResponsavelTecnicoBO.dadosFiltro());

			GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, responsavelTecnicos, "responsavelTecnicoes",
					"Relatório de responsavelTecnicoes " + DataUtil.formatarData(new Date()), true);

		} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
			e.printStackTrace();
		}
	}

	public void list() {

		try {
			responsavelTecnicos = ResponsavelTecnicoBO.responsavelTecnicoes(responsavelTecnicoFilter);
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
		responsavelTecnico = new ResponsavelTecnico();
		return CAD;
	}

	public String updateStatus() {
		try {

			if (responsavelTecnico.getStatus()) {
				responsavelTecnico.setStatus(false);
				ResponsavelTecnicoBO.update(responsavelTecnico);
			} else {
				responsavelTecnico.setStatus(true);
				ResponsavelTecnicoBO.update(responsavelTecnico);
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

		String status = responsavelTecnico.getStatus() ? "Ativo" : "Inativo";

		setInfoMessage("Status alterado com sucesso!", "O responsavelTecnico está " + status);

		return null;
	}
	
	public boolean isVazia() {
		return this.responsavelTecnicos.isEmpty();
	}
	public boolean notVazia() {
		return !isVazia();
	}

	public ResponsavelTecnico getResponsavelTecnico() {
		return responsavelTecnico;
	}

	public void setResponsavelTecnico(ResponsavelTecnico responsavelTecnico) {
		this.responsavelTecnico = responsavelTecnico;
	}

	public List<ResponsavelTecnico> getResponsavelTecnicos() {
		return responsavelTecnicos;
	}

	public void setResponsavelTecnicos(List<ResponsavelTecnico> responsavelTecnicos) {
		this.responsavelTecnicos = responsavelTecnicos;
	}

	public ResponsavelTecnico getResponsavelTecnicoFilter() {
		return responsavelTecnicoFilter;
	}

	public void setResponsavelTecnicoFilter(ResponsavelTecnico responsavelTecnicoFilter) {
		this.responsavelTecnicoFilter = responsavelTecnicoFilter;
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
