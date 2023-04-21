package mb;

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

import bo.OrigemClienteBO;
import br.hibernateutil.exception.ObjetoNaoEncontradoHibernateException;
import br.hibernateutil.exception.SessaoNaoEncontradaParaEntidadeFornecidaException;
import br.hibernateutil.exception.ValidacaoHibernateException;
import br.hibernateutil.exception.ViolacaoChaveHibernateException;
import br.hibernateutil.paginacao.LazyEntityDataModel;
import exception.ObjetoExistenteException;
import model.OrigemCliente;
import report.GenericReport;
import util.AfterRedirect;
import util.DataUtil;
import util.RecuperarObjetoViaRequisicao;

@ManagedBean
@ViewScoped
public class OrigemClienteMB implements Serializable {
	  
	private static final long serialVersionUID = 8197346996386546555L;
		private final String CAD = "/private/cadastro/cadastrarOrigemCliente.xhtml?faces-redirect=true";
		private final String EDIT = "/private/cadastro/cadastrarOrigemCliente.xhtml";
		private final String LIST= "/private/lista/listarOrigemCliente.xhtml?faces-redirect=true";
		
		private OrigemCliente origemCliente;
		private OrigemCliente origemClienteFilter;
		private LazyEntityDataModel<OrigemCliente> lazy;
		private List<OrigemCliente> origemClientes;
		
		@PostConstruct
		public void init(){
			
			OrigemCliente aux = RecuperarObjetoViaRequisicao.getObjeto(OrigemCliente.class, "id");
			origemCliente = aux != null ? aux : new OrigemCliente();
			
			origemClienteFilter = new OrigemCliente();
			lazy = new LazyEntityDataModel<OrigemCliente>(OrigemCliente.class);
			origemClientes = new ArrayList<OrigemCliente>();
		}
		
		
		public String save(){
			try {
				OrigemClienteBO.save(origemCliente);
				setInfoMessage("Cadastrado com sucesso!",  "Origem Cliente " + origemCliente.getNome());
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
			origemCliente = new OrigemCliente();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public String update(){
			
			try {
				OrigemClienteBO.update(origemCliente);
				setInfoMessage("Editado com sucesso!",  "OrigemCliente " + origemCliente.getNome());
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
			origemCliente = new OrigemCliente();
			AfterRedirect.manterMensagem();
			return LIST;
		}
		
		public void gerarPDF(ActionEvent ev){
			try {
				Map<String, Object> mapa = new HashMap<String, Object>();
				
				List<OrigemCliente> origemClientes = OrigemClienteBO.origemClientes(origemClienteFilter);
				
				origemClienteFilter = new OrigemCliente();
//				mapa.put("logo", BuscaNoWebContent.buscaArquivo("/template/imagens/fcrs-mini.png"));
//				mapa.put("rodape", BuscaNoWebContent.buscaArquivo("/template/imagens/rodape_paisagem.jpg"));
//				mapa.put("filtro",OrigemClienteBO.dadosFiltro());
				
				GenericReport.gerarPdfComJRBeanCollectionDataSource(mapa, origemClientes, "origemClientes", "Relatório de origem Clientes "  + DataUtil.formatarData(new Date()), true);

			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
		}
		public String list(){
			
			lazy = OrigemClienteBO.origemClientesLazy(origemClienteFilter);
			
			return null;
		}
		
		public List<OrigemCliente> getOrigemClienteAutoComplete(String nome) {

			List<OrigemCliente> categoriaComplete = new ArrayList<OrigemCliente>();
			try {
				categoriaComplete = OrigemClienteBO.origemClientesComplete(nome);
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
			origemCliente = new OrigemCliente();
			return CAD;	
		}
		
		public String updateStatus() {
			try {
				
				if (origemCliente.getStatus()) {
					origemCliente.setStatus(false);
					OrigemClienteBO.update(origemCliente);
				} else {
					origemCliente.setStatus(true);
					OrigemClienteBO.update(origemCliente);
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
			
			String status = origemCliente.getStatus() ? "Ativo" : "Inativo";
			
			setInfoMessage("Status alterado com sucesso!", "O origem Cliente está " + status);

			return null;
		}

		public OrigemCliente getOrigemCliente() {
			return origemCliente;
		}

		public void setOrigemCliente(OrigemCliente origemCliente) {
			this.origemCliente = origemCliente;
		}

		public LazyEntityDataModel<OrigemCliente> getLazy() {
			return lazy;
		}

		public void setLazy(LazyEntityDataModel<OrigemCliente> lazy) {
			this.lazy = lazy;
		}

		public List<OrigemCliente> getOrigemClientes() {
			try {
				origemClientes = OrigemClienteBO.list();
			} catch (SessaoNaoEncontradaParaEntidadeFornecidaException e) {
				e.printStackTrace();
			}
			return origemClientes;
		}


		public void setOrigemClientes(List<OrigemCliente> origemClientes) {
			this.origemClientes = origemClientes;
		}


		public OrigemCliente getOrigemClienteFilter() {
			return origemClienteFilter;
		}


		public void setOrigemClienteFilter(OrigemCliente origemClienteFilter) {
			this.origemClienteFilter = origemClienteFilter;
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
