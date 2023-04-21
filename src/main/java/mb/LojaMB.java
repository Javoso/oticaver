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

import bo.LojaBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.Loja;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class LojaMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarLoja.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarLoja.xhtml";
		private final String LIST= "/private/lista/listarLoja.xhtml?faces-redirect=true";
		
		private Loja loja;
		private Loja lojaFilter;
		private LazyEntityDataModel<Loja> lazy;
		private List<Loja> lojas;
		
		@PostConstruct
		public void init(){
			
			Loja aux = RecuperarObjetoViaRequisicao.getObjeto(Loja.class, "id");
			loja = aux != null ? aux : new Loja();
			
			lojaFilter = new Loja();
			lazy = new LazyEntityDataModel<Loja>(Loja.class);
			lojas = new ArrayList<Loja>();
		}
		
		
		public String save(){
			try {
				LojaBO.save(loja);
				setInfoMessage("Cadastrado com sucesso!",  "Loja " + loja.getNome());
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
			loja = new Loja();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				LojaBO.update(loja);
				setInfoMessage("Editado com sucesso!",  "Loja " + loja.getNome());
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
			loja = new Loja();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<Loja> lojas = LojaBO.lojas(lojaFilter);
				
				lojaFilter = new Loja();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",LojaBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, lojas, "lojas", "Relatório de lojas "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = LojaBO.lojasLazy(lojaFilter);
			
			return null;
		}
		
		public List<Loja> getLojaAutoComplete(String nome) {

			List<Loja> categoriaComplete = new ArrayList<Loja>();
			try {
				categoriaComplete = LojaBO.lojasComplete(nome);
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
			loja = new Loja();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (loja.getStatus()) {
					loja.setStatus(false);
					LojaBO.update(loja);
				} else {
					loja.setStatus(true);
					LojaBO.update(loja);
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
			
			String status = loja.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O loja está " + status);
			return null;
		}

		public Loja getLoja() {
			return loja;
		}

		public void setLoja(Loja loja) {
			this.loja = loja;
		}

		public LazyEntityDataModel<Loja> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<Loja> lazy) {
			this.lazy = lazy;
		}

		public List<Loja> getLojas() {
			try {
				lojas = LojaBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return lojas;
		}


		public void setLojas(List<Loja> lojas) {
			this.lojas = lojas;
		}


		public Loja getLojaFilter() {
			return lojaFilter;
		}


		public void setLojaFilter(Loja lojaFilter) {
			this.lojaFilter = lojaFilter;
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
