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

import bo.ProfissaoBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.Profissao;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class ProfissaoMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarProfissao.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarProfissao.xhtml";
		private final String LIST= "/private/lista/listarProfissao.xhtml?faces-redirect=true";
		
		private Profissao profissao;
		private Profissao profissaoFilter;
		private LazyEntityDataModel<Profissao> lazy;
		private List<Profissao> profissaos;
		
		@PostConstruct
		public void init(){
			
			Profissao aux = RecuperarObjetoViaRequisicao.getObjeto(Profissao.class, "id");
			profissao = aux != null ? aux : new Profissao();
			
			profissaoFilter = new Profissao();
			lazy = new LazyEntityDataModel<Profissao>(Profissao.class);
			profissaos = new ArrayList<Profissao>();
		}
		
		
		public String save(){
			try {
				ProfissaoBO.save(profissao);
				setInfoMessage("Cadastrado com sucesso!",  "Profissao " + profissao.getNome());
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
			profissao = new Profissao();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				ProfissaoBO.update(profissao);
				setInfoMessage("Editado com sucesso!",  "Profissao " + profissao.getNome());
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
			profissao = new Profissao();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<Profissao> profissaos = ProfissaoBO.profissaos(profissaoFilter);
				
				profissaoFilter = new Profissao();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",ProfissaoBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, profissaos, "profissaos", "Relatório de profissaos "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = ProfissaoBO.profissaosLazy(profissaoFilter);
			
			return null;
		}
		
		public List<Profissao> getProfissaoAutoComplete(String nome) {

			List<Profissao> categoriaComplete = new ArrayList<Profissao>();
			try {
				categoriaComplete = ProfissaoBO.profissaosComplete(nome);
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
			profissao = new Profissao();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (profissao.getStatus()) {
					profissao.setStatus(false);
					ProfissaoBO.update(profissao);
				} else {
					profissao.setStatus(true);
					ProfissaoBO.update(profissao);
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
			
			String status = profissao.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O profissao está " + status);

			return null;
		}

		public Profissao getProfissao() {
			return profissao;
		}

		public void setProfissao(Profissao profissao) {
			this.profissao = profissao;
		}

		public LazyEntityDataModel<Profissao> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<Profissao> lazy) {
			this.lazy = lazy;
		}

		public List<Profissao> getProfissaos() {
			try {
				profissaos = ProfissaoBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return profissaos;
		}


		public void setProfissaos(List<Profissao> profissaos) {
			this.profissaos = profissaos;
		}


		public Profissao getProfissaoFilter() {
			return profissaoFilter;
		}


		public void setProfissaoFilter(Profissao profissaoFilter) {
			this.profissaoFilter = profissaoFilter;
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
