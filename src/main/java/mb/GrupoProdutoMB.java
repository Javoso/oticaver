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

import bo.GrupoProdutoBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.GrupoProduto;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class GrupoProdutoMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarGrupoProduto.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarGrupoProduto.xhtml";
		private final String LIST= "/private/lista/listarGrupoProduto.xhtml?faces-redirect=true";
		
		private GrupoProduto grupoProduto;
		private GrupoProduto grupoProdutoFilter;
		private LazyEntityDataModel<GrupoProduto> lazy;
		private List<GrupoProduto> grupoProdutos;
		
		@PostConstruct
		public void init(){
			
			GrupoProduto aux = RecuperarObjetoViaRequisicao.getObjeto(GrupoProduto.class, "id");
			grupoProduto = aux != null ? aux : new GrupoProduto();
			
			grupoProdutoFilter = new GrupoProduto();
			lazy = new LazyEntityDataModel<GrupoProduto>(GrupoProduto.class);
			grupoProdutos = new ArrayList<GrupoProduto>();
		}
		
		
		public String save(){
			try {
				GrupoProdutoBO.save(grupoProduto);
				setInfoMessage("Cadastrado com sucesso!",  "Grupo Produto " + grupoProduto.getNome());
			} catch (ViolacaoChaveHibernateException e) {
				setErrorMessage("Erro!", e.getMessage());
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
			grupoProduto = new GrupoProduto();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				GrupoProdutoBO.update(grupoProduto);
				setInfoMessage("Editado com sucesso!",  "Grupo Produto " + grupoProduto.getNome());
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
			grupoProduto = new GrupoProduto();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<GrupoProduto> grupoProdutos = GrupoProdutoBO.grupoProdutos(grupoProdutoFilter);
				
				grupoProdutoFilter = new GrupoProduto();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",GrupoProdutoBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, grupoProdutos, "grupoProdutos", "Relatório de grupoProdutos "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = GrupoProdutoBO.grupoProdutosLazy(grupoProdutoFilter);
			
			return null;
		}
		
		public List<GrupoProduto> getGrupoProdutoAutoComplete(String nome) {

			List<GrupoProduto> categoriaComplete = new ArrayList<GrupoProduto>();
			try {
				categoriaComplete = GrupoProdutoBO.grupoProdutosComplete(nome);
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
			grupoProduto = new GrupoProduto();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (grupoProduto.getStatus()) {
					grupoProduto.setStatus(false);
					GrupoProdutoBO.update(grupoProduto);
				} else {
					grupoProduto.setStatus(true);
					GrupoProdutoBO.update(grupoProduto);
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
			
			String status = grupoProduto.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O grupo Produto está " + status);

			return null;
		}

		public GrupoProduto getGrupoProduto() {
			return grupoProduto;
		}

		public void setGrupoProduto(GrupoProduto grupoProduto) {
			this.grupoProduto = grupoProduto;
		}

		public LazyEntityDataModel<GrupoProduto> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<GrupoProduto> lazy) {
			this.lazy = lazy;
		}

		public List<GrupoProduto> getGrupoProdutos() {
			try {
				grupoProdutos = GrupoProdutoBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return grupoProdutos;
		}


		public void setGrupoProdutos(List<GrupoProduto> grupoProdutos) {
			this.grupoProdutos = grupoProdutos;
		}


		public GrupoProduto getGrupoProdutoFilter() {
			return grupoProdutoFilter;
		}


		public void setGrupoProdutoFilter(GrupoProduto grupoProdutoFilter) {
			this.grupoProdutoFilter = grupoProdutoFilter;
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
