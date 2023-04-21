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

import bo.UnidadeBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.Unidade;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class UnidadeMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarUnidade.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarUnidade.xhtml";
		private final String LIST= "/private/lista/listarUnidade.xhtml?faces-redirect=true";
		
		private Unidade unidade;
		private Unidade unidadeFilter;
		private LazyEntityDataModel<Unidade> lazy;
		private List<Unidade> unidades;
		
		@PostConstruct
		public void init(){
			
			Unidade aux = RecuperarObjetoViaRequisicao.getObjeto(Unidade.class, "id");
			unidade = aux != null ? aux : new Unidade();
			
			unidadeFilter = new Unidade();
			lazy = new LazyEntityDataModel<Unidade>(Unidade.class);
			unidades = new ArrayList<Unidade>();
		}
		
		
		public String save(){
			try {
				UnidadeBO.save(unidade);
				setInfoMessage("Cadastrado com sucesso!",  "Unidade " + unidade.getNome());
			} catch (ViolacaoChaveHibernateException e) {
				addErrorMessage("Formulario", "erro.salvar.entidade.campo.existente", "");
				e.printStackTrace();
			} catch (ValidacaoHibernateException e) {
				setErrorMessage("Erro!", e.getMessage());
				e.printStackTrace();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				setErrorMessage("Erro!", e.getMessage());
				e.printStackTrace();
			} catch (ObjetoExistenteException e) {
				setErrorMessage("Erro!", e.getMessage());
				e.printStackTrace();
			}
			unidade = new Unidade();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				UnidadeBO.update(unidade);
				setInfoMessage("Editado com sucesso!",  "Unidade " + unidade.getNome());
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
			}catch (ObjetoExistenteException e) {
				setErrorMessage("Erro!", e.getMessage());
				e.printStackTrace();
			}
			unidade = new Unidade();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<Unidade> unidades = UnidadeBO.unidades(unidadeFilter);
				
				unidadeFilter = new Unidade();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",UnidadeBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, unidades, "unidades", "Relatório de unidades "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = UnidadeBO.unidadesLazy(unidadeFilter);
			
			return null;
		}
		
		public List<Unidade> getUnidadeAutoComplete(String nome) {

			List<Unidade> categoriaComplete = new ArrayList<Unidade>();
			try {
				categoriaComplete = UnidadeBO.unidadesComplete(nome);
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return categoriaComplete;
		}

		public String goToList(){
			return LIST;
		}
		
		public String prepareUpdate(){
			return EDIT;
		}
		
		public String prepareSave(){
			unidade = new Unidade();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (unidade.getStatus()) {
					unidade.setStatus(false);
					UnidadeBO.update(unidade);
				} else {
					unidade.setStatus(true);
					UnidadeBO.update(unidade);
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
			}catch (ObjetoExistenteException e) {
				setErrorMessage("Erro!", e.getMessage());
				e.printStackTrace();
			}
			
			String status = unidade.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O unidade está " + status);

			return null;
		}

		public Unidade getUnidade() {
			return unidade;
		}

		public void setUnidade(Unidade unidade) {
			this.unidade = unidade;
		}

		public LazyEntityDataModel<Unidade> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<Unidade> lazy) {
			this.lazy = lazy;
		}

		public List<Unidade> getUnidades() {
			try {
				unidades = UnidadeBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return unidades;
		}


		public void setUnidades(List<Unidade> unidades) {
			this.unidades = unidades;
		}


		public Unidade getUnidadeFilter() {
			return unidadeFilter;
		}


		public void setUnidadeFilter(Unidade unidadeFilter) {
			this.unidadeFilter = unidadeFilter;
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
		

}
