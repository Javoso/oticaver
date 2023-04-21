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

import bo.GrifeBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.Grife;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class GrifeMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarGrife.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarGrife.xhtml";
		private final String LIST= "/private/lista/listarGrife.xhtml?faces-redirect=true";
		
		private Grife grife;
		private Grife grifeFilter;
		private LazyEntityDataModel<Grife> lazy;
		private List<Grife> grifes;
		
		@PostConstruct
		public void init(){
			
			Grife aux = RecuperarObjetoViaRequisicao.getObjeto(Grife.class, "id");
			grife = aux != null ? aux : new Grife();
			
			grifeFilter = new Grife();
			lazy = new LazyEntityDataModel<Grife>(Grife.class);
			grifes = new ArrayList<Grife>();
		}
		
		
		public String save(){
			try {
				GrifeBO.save(grife);
				setInfoMessage("Cadastrado com sucesso!",  "Grife " + grife.getNome());
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
			grife = new Grife();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				GrifeBO.update(grife);
				setInfoMessage("Editado com sucesso!",  "Grife " + grife.getNome());
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
			grife = new Grife();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<Grife> grifes = GrifeBO.grifes(grifeFilter);
				
				grifeFilter = new Grife();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",GrifeBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, grifes, "grifes", "Relatório de grifes "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = GrifeBO.grifesLazy(grifeFilter);
			
			return null;
		}
		
		public List<Grife> getGrifeAutoComplete(String nome) {

			List<Grife> categoriaComplete = new ArrayList<Grife>();
			try {
				categoriaComplete = GrifeBO.grifesComplete(nome);
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
			grife = new Grife();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (grife.getStatus()) {
					grife.setStatus(false);
					GrifeBO.update(grife);
				} else {
					grife.setStatus(true);
					GrifeBO.update(grife);
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
			
			String status = grife.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O grife está " + status);

			return null;
		}

		public Grife getGrife() {
			return grife;
		}

		public void setGrife(Grife grife) {
			this.grife = grife;
		}

		public LazyEntityDataModel<Grife> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<Grife> lazy) {
			this.lazy = lazy;
		}

		public List<Grife> getGrifes() {
			try {
				grifes = GrifeBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return grifes;
		}


		public void setGrifes(List<Grife> grifes) {
			this.grifes = grifes;
		}


		public Grife getGrifeFilter() {
			return grifeFilter;
		}


		public void setGrifeFilter(Grife grifeFilter) {
			this.grifeFilter = grifeFilter;
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
