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

import bo.MedicoBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.Medico;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class MedicoMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarMedico.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarMedico.xhtml";
		private final String LIST= "/private/lista/listarMedico.xhtml?faces-redirect=true";
		
		private Medico medico;
		private Medico medicoFilter;
		private LazyEntityDataModel<Medico> lazy;
		private List<Medico> medicos;
		
		@PostConstruct
		public void init(){
			
			Medico aux = RecuperarObjetoViaRequisicao.getObjeto(Medico.class, "id");
			medico = aux != null ? aux : new Medico();
			
			medicoFilter = new Medico();
			lazy = new LazyEntityDataModel<Medico>(Medico.class);
			medicos = new ArrayList<Medico>();
		}
		
		
		public String save(){
			try {
				MedicoBO.save(medico);
				setInfoMessage("Cadastrado com sucesso!",  "Medico " + medico.getNome());
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
			medico = new Medico();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				MedicoBO.update(medico);
				setInfoMessage("Editado com sucesso!",  "Medico " + medico.getNome());
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
			medico = new Medico();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<Medico> medicos = MedicoBO.medicos(medicoFilter);
				
				medicoFilter = new Medico();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",MedicoBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, medicos, "medicos", "Relatório de medicos "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = MedicoBO.medicosLazy(medicoFilter);
			
			return null;
		}
		
		public List<Medico> getMedicoAutoComplete(String nome) {

			List<Medico> categoriaComplete = new ArrayList<Medico>();
			try {
				categoriaComplete = MedicoBO.medicosComplete(nome);
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
			medico = new Medico();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (medico.getStatus()) {
					medico.setStatus(false);
					MedicoBO.update(medico);
				} else {
					medico.setStatus(true);
					MedicoBO.update(medico);
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
			
			String status = medico.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O medico está " + status);

			return null;
		}

		public Medico getMedico() {
			return medico;
		}

		public void setMedico(Medico medico) {
			this.medico = medico;
		}

		public LazyEntityDataModel<Medico> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<Medico> lazy) {
			this.lazy = lazy;
		}

		public List<Medico> getMedicos() {
			try {
				medicos = MedicoBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return medicos;
		}


		public void setMedicos(List<Medico> medicos) {
			this.medicos = medicos;
		}


		public Medico getMedicoFilter() {
			return medicoFilter;
		}


		public void setMedicoFilter(Medico medicoFilter) {
			this.medicoFilter = medicoFilter;
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
