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

import bo.FuncaoBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.Funcao;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class FuncaoMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarFuncao.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarFuncao.xhtml";
		private final String LIST= "/private/lista/listarFuncao.xhtml?faces-redirect=true";
		
		private Funcao funcao;
		private Funcao funcaoFilter;
		private LazyEntityDataModel<Funcao> lazy;
		private List<Funcao> funcaos;
		
		@PostConstruct
		public void init(){
			
			Funcao aux = RecuperarObjetoViaRequisicao.getObjeto(Funcao.class, "id");
			funcao = aux != null ? aux : new Funcao();
			
			funcaoFilter = new Funcao();
			lazy = new LazyEntityDataModel<Funcao>(Funcao.class);
			funcaos = new ArrayList<Funcao>();
		}
		
		
		public String save(){
			try {
				FuncaoBO.save(funcao);
				setInfoMessage("Cadastrado com sucesso!",  "Funcao " + funcao.getNome());
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
			funcao = new Funcao();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				FuncaoBO.update(funcao);
				setInfoMessage("Editado com sucesso!",  "Funcao " + funcao.getNome());
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
			funcao = new Funcao();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<Funcao> funcaos = FuncaoBO.funcaos(funcaoFilter);
				
				funcaoFilter = new Funcao();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",FuncaoBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, funcaos, "funcaos", "Relatório de funcaos "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = FuncaoBO.funcaosLazy(funcaoFilter);
			
			return null;
		}
		
		public List<Funcao> getFuncaoAutoComplete(String nome) {

			List<Funcao> categoriaComplete = new ArrayList<Funcao>();
			try {
				categoriaComplete = FuncaoBO.funcaosComplete(nome);
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
			funcao = new Funcao();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (funcao.getStatus()) {
					funcao.setStatus(false);
					FuncaoBO.update(funcao);
				} else {
					funcao.setStatus(true);
					FuncaoBO.update(funcao);
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
			
			String status = funcao.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O funcao está " + status);

			return null;
		}

		public Funcao getFuncao() {
			return funcao;
		}

		public void setFuncao(Funcao funcao) {
			this.funcao = funcao;
		}

		public LazyEntityDataModel<Funcao> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<Funcao> lazy) {
			this.lazy = lazy;
		}

		public List<Funcao> getFuncaos() {
			try {
				funcaos = FuncaoBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return funcaos;
		}


		public void setFuncaos(List<Funcao> funcaos) {
			this.funcaos = funcaos;
		}


		public Funcao getFuncaoFilter() {
			return funcaoFilter;
		}


		public void setFuncaoFilter(Funcao funcaoFilter) {
			this.funcaoFilter = funcaoFilter;
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
